package com.xiaomi.smarthome.framework.plugin.apk;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.router.miio.miioplugin.IMessageCallback;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity;
import org.json.JSONArray;

public class ApkPluginApi {
    public static void a(PluginRecord pluginRecord, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("did", str);
        bundle.putString("page_name", "main_page");
        a(a(pluginRecord.E(), "page", bundle, new IMessageCallback.Stub() {
            public void onFailure(int i, String str) throws RemoteException {
            }

            public void onHandle(int i) throws RemoteException {
            }

            public void onSuccess(Bundle bundle) throws RemoteException {
            }
        }));
    }

    public static void a(PluginRecord pluginRecord, String str, JSONArray jSONArray) {
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.gf, jSONArray.toString());
        a(a(pluginRecord.E(), DevicePushSettingActivity.EXTRA_KEY, bundle, new IMessageCallback.Stub() {
            public void onFailure(int i, String str) throws RemoteException {
            }

            public void onHandle(int i) throws RemoteException {
            }

            public void onSuccess(Bundle bundle) throws RemoteException {
            }
        }));
    }

    public static void a(PluginRecord pluginRecord, String str, String str2, long j, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("did", str);
        bundle.putString("event", str2);
        bundle.putLong("time", j);
        bundle.putString("extra", str3);
        bundle.putBoolean("is_notified", z);
        a(a(pluginRecord.E(), "scene_push", bundle, new IMessageCallback.Stub() {
            public void onFailure(int i, String str) throws RemoteException {
            }

            public void onHandle(int i) throws RemoteException {
            }

            public void onSuccess(Bundle bundle) throws RemoteException {
            }
        }));
    }

    private static Intent a(String str, String str2, Bundle bundle, IMessageCallback iMessageCallback) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str + ".MainReceiver"));
        Bundle bundle2 = new Bundle();
        bundle2.putString("msg_type", str2);
        bundle2.putBundle("msg_body", bundle);
        bundle2.putBinder("msg_callback", iMessageCallback.asBinder());
        intent.putExtras(bundle2);
        return intent;
    }

    private static void a(Intent intent) {
        intent.setAction("com.xiaomi.smarthome.plugin.message");
        if (Build.VERSION.SDK_INT >= 12) {
            intent.addFlags(32);
        }
        try {
            SHApplication.getAppContext().sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }
}
