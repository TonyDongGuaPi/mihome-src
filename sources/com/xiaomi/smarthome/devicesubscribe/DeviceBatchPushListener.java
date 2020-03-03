package com.xiaomi.smarthome.devicesubscribe;

import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import org.json.JSONObject;

public class DeviceBatchPushListener extends PushListener {

    /* renamed from: a  reason: collision with root package name */
    private static DeviceBatchPushListener f15536a;

    public static DeviceBatchPushListener a() {
        if (f15536a == null) {
            f15536a = new DeviceBatchPushListener();
        }
        return f15536a;
    }

    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        a(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            DeviceSubscribeManager.a().b(jSONObject.optString("subid"), jSONObject.optString("did"), jSONObject.optString("model"), jSONObject.optJSONArray("attrs"));
            SmartNotiApi.a(SHApplication.getAppContext()).b(str);
        } catch (Exception unused) {
        }
    }
}
