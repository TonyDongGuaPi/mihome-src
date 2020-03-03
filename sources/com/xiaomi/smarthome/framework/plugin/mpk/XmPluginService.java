package com.xiaomi.smarthome.framework.plugin.mpk;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.device.api.service.IXmPluginHostService;
import com.xiaomi.smarthome.device.api.service.XmPluginBaseService;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class XmPluginService extends Service implements IXmPluginHostService {
    static final String TAG = "XmPluginService";
    ArrayList<XmPluginServiceContext> mXmPluginServiceList = new ArrayList<>();
    HashMap<String, XmPluginServiceContext> mXmPluginServiceMap = new HashMap<>();

    public static class XmPluginServiceContext {
        XmPluginPackage mPluginPackage;
        XmPluginBaseService mXmPluginBaseService;
    }

    /* access modifiers changed from: package-private */
    public XmPluginServiceContext createServiceContext(Intent intent) {
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("extra_package");
        String stringExtra2 = intent.getStringExtra("extra_class");
        Log.d(TAG, "createServiceContext packageName:" + stringExtra + " className:" + stringExtra2);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            return null;
        }
        XmPluginServiceContext xmPluginServiceContext = this.mXmPluginServiceMap.get(stringExtra);
        if (xmPluginServiceContext != null) {
            return xmPluginServiceContext;
        }
        XmPluginPackage packageFromPackage = PluginRuntimeManager.getInstance().getPackageFromPackage(stringExtra);
        if (packageFromPackage == null) {
            Log.d(TAG, "XmPluginPackage null");
            return null;
        }
        try {
            XmPluginBaseService xmPluginBaseService = (XmPluginBaseService) packageFromPackage.classLoader.loadClass(stringExtra2).getConstructor(new Class[0]).newInstance(new Object[0]);
            xmPluginBaseService.attach(this, packageFromPackage);
            xmPluginBaseService.onCreate();
            XmPluginServiceContext xmPluginServiceContext2 = new XmPluginServiceContext();
            xmPluginServiceContext2.mPluginPackage = packageFromPackage;
            xmPluginServiceContext2.mXmPluginBaseService = xmPluginBaseService;
            return xmPluginServiceContext2;
        } catch (Exception e) {
            ErrorInfoActivity.showErrorInfo(this, packageFromPackage, e);
            return null;
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            createServiceContext.mXmPluginBaseService.onRebind(intent);
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        Log.d(TAG, "onStart");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            createServiceContext.mXmPluginBaseService.onStart(intent, i);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(TAG, "onStartCommand");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            createServiceContext.mXmPluginBaseService.onStartCommand(intent, i, i2);
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Log.d(TAG, "onTaskRemoved");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            createServiceContext.mXmPluginBaseService.onTaskRemoved(intent);
        }
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            return createServiceContext.mXmPluginBaseService.onBind(intent);
        }
        return null;
    }

    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        XmPluginServiceContext createServiceContext = createServiceContext(intent);
        if (createServiceContext != null) {
            return createServiceContext.mXmPluginBaseService.onUnbind(intent);
        }
        return super.onUnbind(intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged");
        Iterator<XmPluginServiceContext> it = this.mXmPluginServiceList.iterator();
        while (it.hasNext()) {
            try {
                it.next().mXmPluginBaseService.onConfigurationChanged(configuration);
            } catch (Exception unused) {
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ActivityInfo.TYPE_STR_ONDESTROY);
        Iterator<XmPluginServiceContext> it = this.mXmPluginServiceList.iterator();
        while (it.hasNext()) {
            try {
                it.next().mXmPluginBaseService.onDestroy();
            } catch (Exception unused) {
            }
        }
        this.mXmPluginServiceMap.clear();
        this.mXmPluginServiceList.clear();
    }

    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory");
        Iterator<XmPluginServiceContext> it = this.mXmPluginServiceList.iterator();
        while (it.hasNext()) {
            try {
                it.next().mXmPluginBaseService.onLowMemory();
            } catch (Exception unused) {
            }
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Log.d(TAG, "onTrimMemory");
        Iterator<XmPluginServiceContext> it = this.mXmPluginServiceList.iterator();
        while (it.hasNext()) {
            try {
                it.next().mXmPluginBaseService.onTrimMemory(i);
            } catch (Exception unused) {
            }
        }
    }
}
