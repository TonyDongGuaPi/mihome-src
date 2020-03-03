package com.xiaomi.smarthome.miio.camera.match;

import android.content.Intent;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DevicePluginOpener;
import com.xiaomi.smarthome.scene.push.ScenePushCallback;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraAlarmScenePushCallback extends ScenePushCallback {
    public String getModel() {
        return "yunyi.camera.v1";
    }

    public boolean onReceiveMessage(String str) {
        return true;
    }

    public List<String> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("alarm");
        return arrayList;
    }

    public boolean onReceiveNotifiedMessage(String str) {
        handleMessage(str);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void handleMessage(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("did");
            long j = jSONObject.getLong("time");
            jSONObject.optString("name");
            Intent intent = new Intent();
            intent.putExtra("TIME", j);
            DevicePluginOpener.a(SHApplication.getAppContext(), SmartHomeDeviceManager.a().b(string), intent);
        } catch (JSONException unused) {
        }
    }
}
