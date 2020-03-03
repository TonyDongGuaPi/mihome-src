package com.xiaomi.youpin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.miot.store.ui.MiotStoreMainActivity;
import com.xiaomi.pluginhost.PluginHostActivity;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlActivityManager;
import com.xiaomi.youpin.log.LogUtils;
import com.youpin.weex.app.WXPageActivity;

public class ActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    public static String f23146a = "ActivityLifecycleManager";

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        String str = f23146a;
        LogUtils.d(str, "onActivityCreated: " + activity.getLocalClassName());
        try {
            if (activity instanceof PluginHostActivity) {
                UrlActivityManager.a().a(activity.getIntent().getExtras().getString("url"), activity);
            } else if ((activity instanceof MiotStoreMainActivity) || (activity instanceof WXPageActivity)) {
                String dataString = activity.getIntent().getDataString();
                if (!TextUtils.isEmpty(dataString) && dataString.contains("&spmref")) {
                    dataString = dataString.substring(0, dataString.indexOf("&spmref"));
                }
                UrlActivityManager.a().a(dataString, activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActivityDestroyed(Activity activity) {
        String str = f23146a;
        LogUtils.d(str, "onActivityDestroyed: " + activity.getLocalClassName());
        try {
            if ((activity instanceof PluginHostActivity) || (activity instanceof MiotStoreMainActivity) || (activity instanceof WXPageActivity)) {
                UrlActivityManager.a().a(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
