package com.xiaomi.mishopsdk.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.squareup.picasso.mishop.MishopLruCache;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.Model.PluginSyncInfo;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.utils.PluginQueueManager;

public class PluginSyncManager {
    public static final long DURATION_SYNC = 120000;
    public static final String PATH_DEXPLUGIN = "pluginOutDex";
    public static final String PATH_FASTPLUGIN = "pluginFastDex";
    public static final String PATH_PLUGIN = "plugin";
    public static final String PATH_PLUGINSIGNED = "plugin_signed";
    private static final String TAG = "com.xiaomi.mishopsdk.plugin.PluginSyncManager";
    private static final long TIMER_THREAD_LIFECYCLE = 900000;
    private static PluginSyncManager instance;
    Handler mHandler;
    Runnable mTimerRunnable;
    HandlerThread mTimerThread;
    private long mTimerThreadStartTime = 0;

    public enum SyncStatus {
        waiting,
        synced,
        discard,
        error,
        netdisconnetct,
        neterror,
        waittimeout
    }

    public static PluginSyncManager getInstance() {
        if (instance == null) {
            instance = new PluginSyncManager();
        }
        return instance;
    }

    public void initManager() {
        ClassLoaderFactory.initialize();
        PluginQueueManager.getQueueInstance().init(ShopApp.instance);
        PluginSyncUtils.getInstance().syncInit();
    }

    public void stopManager() {
        stopTimer();
    }

    public PluginSyncInfo getPluginSyncInfo(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new PluginSyncInfo(SyncStatus.error, (PluginInfo) null, str);
        }
        PluginInfo pluginInfo = PluginInfoCache.getInstance(context).getPluginInfo(context, str);
        if (pluginInfo == null || !pluginInfo.checkNeedSyncTime() || (!TextUtils.isEmpty(str2) && versionCmp(pluginInfo.version, str2) < 0)) {
            return new PluginSyncInfo(SyncStatus.waiting, (PluginInfo) null, str);
        }
        pluginInfo.visitPlugin();
        return new PluginSyncInfo(SyncStatus.synced, pluginInfo, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[SYNTHETIC, Splitter:B:9:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int versionCmp(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 0
            if (r0 != 0) goto L_0x0018
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0010 }
            int r0 = r0.intValue()     // Catch:{ NumberFormatException -> 0x0010 }
            goto L_0x0019
        L_0x0010:
            r0 = move-exception
            java.lang.String r2 = "com.xiaomi.mishopsdk.plugin.PluginSyncManager"
            java.lang.String r3 = "the version1 conver to num failed:%s."
            com.xiaomi.mishopsdk.util.Log.d(r2, r3, r5, r0)
        L_0x0018:
            r0 = 0
        L_0x0019:
            boolean r5 = android.text.TextUtils.isEmpty(r6)
            if (r5 != 0) goto L_0x0030
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)     // Catch:{ NumberFormatException -> 0x0028 }
            int r5 = r5.intValue()     // Catch:{ NumberFormatException -> 0x0028 }
            goto L_0x0031
        L_0x0028:
            r5 = move-exception
            java.lang.String r2 = "com.xiaomi.mishopsdk.plugin.PluginSyncManager"
            java.lang.String r3 = "the version2 conver to num failed:%s."
            com.xiaomi.mishopsdk.util.Log.d(r2, r3, r6, r5)
        L_0x0030:
            r5 = 0
        L_0x0031:
            int r0 = r0 - r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.plugin.PluginSyncManager.versionCmp(java.lang.String, java.lang.String):int");
    }

    public void checkPluginSyncInfo(String str) {
        PluginSyncUtils.getInstance().checkPluginSyncInfo(str);
    }

    public void clearHistory() {
        PluginSyncUtils.getInstance().clearHistory();
    }

    public void checkTimerScheduled() {
        if (this.mHandler == null || this.mTimerRunnable == null || !this.mTimerThread.isAlive()) {
            scheduleTimer();
        }
    }

    public void scheduleTimer() {
        Log.d(TAG, "scheduleTimer");
        if (ShopApp.instance != null) {
            if (this.mTimerRunnable == null) {
                this.mTimerRunnable = new Runnable() {
                    public void run() {
                        if (!PluginSyncManager.this.isTimerThreadInLifeCycle() || !ShopApp.isActivityVisible()) {
                            PluginSyncManager.this.stopTimer();
                            Log.d(PluginSyncManager.TAG, "stopTimer");
                            PluginSyncManager.this.clearHistory();
                            Log.d(PluginSyncManager.TAG, "clearHistory");
                            MishopLruCache.getInstance().trimToSize(BitmapGlobalConfig.b);
                            return;
                        }
                        PluginSyncUtils.getInstance().pullPluginVersion();
                        Log.d(PluginSyncManager.TAG, "start PluginSyncUtils.getInstance().pullPluginVersion");
                    }
                };
            }
            if (this.mTimerThread == null) {
                this.mTimerThread = new HandlerThread("pluginTimer");
            }
            if (this.mHandler == null) {
                this.mTimerThread.start();
                this.mTimerThreadStartTime = System.currentTimeMillis();
                this.mHandler = new Handler(this.mTimerThread.getLooper());
            }
            this.mHandler.removeCallbacks(this.mTimerRunnable);
            this.mHandler.post(this.mTimerRunnable);
        }
    }

    public void scheduleTimerDelay() {
        Log.d(TAG, "scheduleTimerDelay");
        if (ShopApp.instance != null && this.mTimerRunnable != null) {
            AndroidUtil.sPluginQueue.postRunnable(this.mTimerRunnable, 120000);
        }
    }

    @SuppressLint({"NewApi"})
    public void stopTimer() {
        Log.d(TAG, "stopTimer");
        if (!(ShopApp.instance == null || this.mTimerRunnable == null)) {
            if (this.mTimerThread != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.mTimerThread.quitSafely();
                } else {
                    this.mTimerThread.quit();
                }
                this.mTimerThread = null;
            }
            if (this.mHandler != null) {
                if (this.mTimerRunnable != null) {
                    this.mHandler.removeCallbacks(this.mTimerRunnable);
                    this.mTimerRunnable = null;
                }
                this.mHandler = null;
            }
        }
        this.mTimerThreadStartTime = 0;
    }

    /* access modifiers changed from: private */
    public boolean isTimerThreadInLifeCycle() {
        return System.currentTimeMillis() - this.mTimerThreadStartTime <= TIMER_THREAD_LIFECYCLE;
    }
}
