package com.xiaomi.mishopsdk.plugin;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.plugin.Model.PluginRuntimeEnv;
import com.xiaomi.mishopsdk.plugin.Model.PluginSyncInfo;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import de.greenrobot.event.EventBus;

public abstract class BasePluginService extends Service {
    private static final long DEFAULT_UPDATE_INTERVAL = 43200000;
    private static final String INFO_DIR = "plugin_sync_info";
    private static final long INSTALL_DELAY_TIME = 1000;
    private static final long INSTALL_FAILED_MAX_TIMES = 5;
    private static final String PREF_LAST_UPDATE_TIME_ = "BasePluginService.last_update_time_";
    private static final String TAG = "BasePluginService";
    private static final long UPDATE_DELAY_TIME = 16000;
    protected Handler mHandler;
    protected int mInstallFailedTimes;
    protected boolean mIsInstalledPlugin;
    protected PluginRuntimeEnv mPluginEnv;
    protected PluginInfo mPluginInfo;

    /* access modifiers changed from: protected */
    public abstract String getPluginId();

    /* access modifiers changed from: protected */
    public long getUpdateInterval() {
        return DEFAULT_UPDATE_INTERVAL;
    }

    /* access modifiers changed from: protected */
    public abstract void onLoadPluginSuccess();

    public void onCreate() {
        this.mHandler = new Handler();
        this.mInstallFailedTimes = 0;
        this.mIsInstalledPlugin = false;
        installPlugin();
        updatePlugin();
        EventBus.getDefault().register(this);
        super.onCreate();
    }

    /* access modifiers changed from: private */
    public void installPlugin() {
        boolean z = this.mIsInstalledPlugin;
        if (!doInstallPlugin()) {
            this.mInstallFailedTimes++;
            Log.d(TAG, "install plugin failed %s times.", (Object) Integer.valueOf(this.mInstallFailedTimes));
            if (((long) this.mInstallFailedTimes) >= 5) {
                Log.d(TAG, "install failed max times=%s, will not install.", (Object) 5L);
                return;
            }
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    BasePluginService.this.installPlugin();
                }
            }, ((long) this.mInstallFailedTimes) * 1000);
        }
        if (!z && this.mIsInstalledPlugin) {
            onLoadPluginSuccess();
        }
    }

    private boolean doInstallPlugin() {
        String pluginId = getPluginId();
        this.mPluginInfo = getPluginInfoFromDisk(pluginId);
        if (this.mPluginInfo == null || TextUtils.isEmpty(this.mPluginInfo.id)) {
            Log.d(TAG, "error: getPluginInfoFromDisk failed, the plugin id is %s", (Object) pluginId);
            this.mPluginInfo = new PluginInfo();
            this.mPluginInfo.id = pluginId;
        } else {
            this.mIsInstalledPlugin = installRunEnv(this.mPluginInfo);
        }
        return this.mIsInstalledPlugin;
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    public void onEventMainThread(PluginSyncInfo pluginSyncInfo) {
        if (pluginSyncInfo == null || pluginSyncInfo.pluginInfo == null) {
            Log.e(TAG, "onEventMainThread, the syncInfo is null, return.");
        } else if (!this.mPluginInfo.id.equals(pluginSyncInfo.pluginId)) {
            Log.d(TAG, "onEventMainThread, get an other pluginid(%s), curId=%s, return.", pluginSyncInfo.pluginId, this.mPluginInfo.id);
        } else if (AnonymousClass4.$SwitchMap$com$xiaomi$mishopsdk$plugin$PluginSyncManager$SyncStatus[pluginSyncInfo.status.ordinal()] != 1) {
            Log.d(TAG, "get a status, no dispose. status=%s", (Object) pluginSyncInfo.status);
        } else if (TextUtils.isEmpty(pluginSyncInfo.pluginInfo.version) || pluginSyncInfo.pluginInfo.checkVersion(this.mPluginInfo)) {
            Log.d(TAG, "plugin synced, but the version is the same. %s=%s", this.mPluginInfo.version, pluginSyncInfo.pluginInfo.version);
        } else {
            restartSelf();
            Log.d(TAG, "plugin synced, and get new version %s->%s, restart itself.", this.mPluginInfo.version, pluginSyncInfo.pluginInfo.version);
        }
    }

    /* renamed from: com.xiaomi.mishopsdk.plugin.BasePluginService$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$mishopsdk$plugin$PluginSyncManager$SyncStatus = new int[PluginSyncManager.SyncStatus.values().length];

        static {
            try {
                $SwitchMap$com$xiaomi$mishopsdk$plugin$PluginSyncManager$SyncStatus[PluginSyncManager.SyncStatus.synced.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void restartSelf() {
        Class<?> cls = getClass();
        Context applicationContext = getApplicationContext();
        Intent intent = new Intent(applicationContext, cls);
        stopSelf();
        applicationContext.startService(intent);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    private void updatePlugin() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                BasePluginService.this.updatePluginForever();
            }
        }, UPDATE_DELAY_TIME);
    }

    /* access modifiers changed from: private */
    public void updatePluginForever() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                BasePluginService.this.updatePluginForever();
            }
        }, getUpdateInterval());
        doUpdatePlugin(false);
    }

    /* access modifiers changed from: protected */
    public boolean doUpdatePlugin(boolean z) {
        String str = PREF_LAST_UPDATE_TIME_ + this.mPluginInfo.id;
        long longPref = PreferenceUtil.getLongPref(this, str, 0);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long updateInterval = getUpdateInterval() / 1000;
        if (currentTimeMillis - updateInterval > longPref || z) {
            Log.d(TAG, "doUpdatePlugin, start detect update...curTime=%s, lastUpdateTime=%s, updateInterval=%s.id=%s, force=%s.", Long.valueOf(currentTimeMillis), Long.valueOf(longPref), Long.valueOf(updateInterval), this.mPluginInfo.id, Boolean.valueOf(z));
            PluginSyncUtils.getInstance().pullSinglePluginVersion(this.mPluginInfo.id);
            PreferenceUtil.setLongPref(this, str, Long.valueOf(currentTimeMillis));
            return true;
        }
        Log.d(TAG, "doUpdatePlugin, need not update, curTime=%s, lastUpdateTime=%s, updateInterval=%s, force=%s.", Long.valueOf(currentTimeMillis), Long.valueOf(longPref), Long.valueOf(updateInterval), Boolean.valueOf(z));
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean installRunEnv(PluginInfo pluginInfo) {
        Exception e;
        boolean z = true;
        if (this.mIsInstalledPlugin) {
            Log.d(TAG, "plugin has beeb installed, installRunEnv return true.");
            return true;
        } else if (pluginInfo == null) {
            Log.d(TAG, "pluginInfo is null, installRunEnv return false.");
            return false;
        } else {
            try {
                this.mPluginEnv = PluginInstallUtils.installRunEnv(pluginInfo, this);
                try {
                    Log.d(TAG, "installRunEnv succecss, the id = %s", (Object) pluginInfo.id);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                z = false;
                Log.e(TAG, "installRunEnv failed, the id=%s", pluginInfo.id, e);
                return z;
            }
            return z;
        }
    }

    public AssetManager getAssets() {
        return this.mPluginEnv == null ? super.getAssets() : this.mPluginEnv.pluginAsset;
    }

    public ClassLoader getClassLoader() {
        return this.mPluginEnv == null ? super.getClassLoader() : this.mPluginEnv.pluginClassLoader;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0074, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.mishopsdk.plugin.lib.PluginInfo getPluginInfoFromDisk(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "plugin_sync_info"
            r1 = 0
            java.io.File r0 = r4.getDir(r0, r1)     // Catch:{ all -> 0x0075 }
            r0.mkdirs()     // Catch:{ all -> 0x0075 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
            r1.<init>()     // Catch:{ all -> 0x0075 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x0075 }
            r1.append(r0)     // Catch:{ all -> 0x0075 }
            java.lang.String r0 = "/"
            r1.append(r0)     // Catch:{ all -> 0x0075 }
            r1.append(r5)     // Catch:{ all -> 0x0075 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0075 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0075 }
            r1.<init>(r0)     // Catch:{ all -> 0x0075 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0075 }
            r0 = 0
            if (r5 != 0) goto L_0x0073
            boolean r5 = r1.exists()     // Catch:{ all -> 0x0075 }
            if (r5 != 0) goto L_0x0036
            goto L_0x0073
        L_0x0036:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0055, all -> 0x0050 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0055, all -> 0x0050 }
            java.lang.Object r2 = r1.readObject()     // Catch:{ Exception -> 0x004e }
            com.xiaomi.mishopsdk.plugin.lib.PluginInfo r2 = (com.xiaomi.mishopsdk.plugin.lib.PluginInfo) r2     // Catch:{ Exception -> 0x004e }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)     // Catch:{ all -> 0x0075 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x0075 }
            r0 = r2
            goto L_0x0069
        L_0x004e:
            r2 = move-exception
            goto L_0x0060
        L_0x0050:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x006c
        L_0x0055:
            r2 = move-exception
            r1 = r0
            goto L_0x0060
        L_0x0058:
            r5 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
            goto L_0x006c
        L_0x005d:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L_0x0060:
            r2.printStackTrace()     // Catch:{ all -> 0x006b }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)     // Catch:{ all -> 0x0075 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x0075 }
        L_0x0069:
            monitor-exit(r4)
            return r0
        L_0x006b:
            r0 = move-exception
        L_0x006c:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r1)     // Catch:{ all -> 0x0075 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x0075 }
            throw r0     // Catch:{ all -> 0x0075 }
        L_0x0073:
            monitor-exit(r4)
            return r0
        L_0x0075:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.plugin.BasePluginService.getPluginInfoFromDisk(java.lang.String):com.xiaomi.mishopsdk.plugin.lib.PluginInfo");
    }

    /* access modifiers changed from: protected */
    public void serviceStartForeground() {
        startForeground(0, new Notification());
    }

    /* access modifiers changed from: protected */
    public void serviceStopForeground() {
        stopForeground(false);
    }
}
