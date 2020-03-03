package com.xiaomi.smarthome.framework.push.listener;

import android.os.Bundle;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.homeroom.ManageDeviceRoomActivity;
import org.json.JSONObject;

public class DeviceNewPushListener extends PushListener {
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
            jSONObject.optString("model");
            jSONObject.optString("did");
            Bundle bundle = new Bundle();
            bundle.putString("device_id", "75386720");
            OpenApi.a(ManageDeviceRoomActivity.class, bundle, true, 335544320);
        } catch (Exception unused) {
        }
    }
}
