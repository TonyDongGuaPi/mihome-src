package com.xiaomi.smarthome.frame.plugin.runtime.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.plugin.service.PluginBaseService;

public abstract class PluginHostService extends Service {
    public static final String PLUGIN_EXTRA_CLASS = "plugin_extra_class";
    public static final String PLUGIN_EXTRA_PACKAGE_ID = "plugin_extra_packageId";
    public static final String PLUGIN_EXTRA_START_INTENT = "plugin_extra_start_intent";
    private XmPluginPackage mLoadedInfo;
    private PluginBaseService mPluginService;

    public void onCreate() {
        super.onCreate();
        PluginBridgeService.StartServiceRecord startServiceRecord = PluginBridgeService.getStartServiceRecord(getClass().getName());
        if (startServiceRecord == null || startServiceRecord.intent == null) {
            exit();
            return;
        }
        Intent intent = startServiceRecord.intent;
        long longExtra = intent.getLongExtra(PLUGIN_EXTRA_PACKAGE_ID, 0);
        String stringExtra = intent.getStringExtra(PLUGIN_EXTRA_CLASS);
        if (longExtra <= 0 || TextUtils.isEmpty(stringExtra)) {
            exit();
            return;
        }
        PluginPackageInfo a2 = CoreApi.a().a(longExtra);
        if (a2 == null) {
            exit();
            return;
        }
        this.mLoadedInfo = PluginRuntimeManager.getInstance().loadApk(a2);
        intent.setExtrasClassLoader(this.mLoadedInfo.classLoader);
        if (this.mLoadedInfo != null) {
            try {
                this.mPluginService = (PluginBaseService) this.mLoadedInfo.classLoader.loadClass(stringExtra).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.mPluginService.attach(this, this);
            } catch (Throwable unused) {
                exit();
                return;
            }
        } else {
            try {
                this.mPluginService = (PluginBaseService) this.mLoadedInfo.classLoader.loadClass(stringExtra).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.mPluginService.attach(this, this);
            } catch (Exception unused2) {
                exit();
                return;
            }
        }
        this.mPluginService.onCreate();
    }

    public Resources getResources() {
        if (this.mLoadedInfo == null) {
            return super.getResources();
        }
        return this.mLoadedInfo.resources;
    }

    public AssetManager getAssets() {
        if (this.mLoadedInfo == null) {
            return super.getAssets();
        }
        return this.mLoadedInfo.assetManager;
    }

    public ClassLoader getClassLoader() {
        if (this.mLoadedInfo == null) {
            return super.getClassLoader();
        }
        return this.mLoadedInfo.classLoader;
    }

    private void exit() {
        stopSelf();
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        if (this.mPluginService != null) {
            return this.mPluginService.onBind(intent);
        }
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        Intent intent2 = (Intent) intent.getParcelableExtra(PLUGIN_EXTRA_START_INTENT);
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onStart(intent2, i);
            } catch (Exception unused) {
                exit();
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Intent intent2 = intent == null ? null : (Intent) intent.getParcelableExtra(PLUGIN_EXTRA_START_INTENT);
        if (this.mPluginService == null) {
            return 0;
        }
        try {
            return this.mPluginService.onStartCommand(intent2, i, i2);
        } catch (Exception unused) {
            exit();
            return 0;
        }
    }

    public void onDestroy() {
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onDestroy();
            } catch (Exception unused) {
                exit();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onConfigurationChanged(configuration);
            } catch (Exception unused) {
                exit();
            }
        }
    }

    public void onLowMemory() {
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onLowMemory();
            } catch (Exception unused) {
                exit();
            }
        }
    }

    public void onTrimMemory(int i) {
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onTrimMemory(i);
            } catch (Exception unused) {
                exit();
            }
        }
    }

    public void onTaskRemoved(Intent intent) {
        if (this.mPluginService != null) {
            try {
                this.mPluginService.onTaskRemoved(intent);
            } catch (Exception unused) {
                exit();
            }
        }
    }
}
