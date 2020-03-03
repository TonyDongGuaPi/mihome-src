package com.xiaomi.smarthome.framework.push.listener;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.SceneStatusManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SceneStatusPushListener extends PushListener {
    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    private void a(String str) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray(Tags.MiPhoneDetails.DETAILS);
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(a(optJSONArray.optJSONObject(i)));
                }
                if (arrayList.size() > 0) {
                    SceneStatusManager.a().a((List<SceneApi.SmartHomeScene>) arrayList);
                }
            }
        } catch (Exception unused) {
        }
    }

    private SceneApi.SmartHomeScene a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SceneApi.SmartHomeScene smartHomeScene = new SceneApi.SmartHomeScene();
        smartHomeScene.f = jSONObject.optString("us_id");
        smartHomeScene.n = jSONObject.optBoolean(AutoSceneAction.f21495a);
        return smartHomeScene;
    }
}
