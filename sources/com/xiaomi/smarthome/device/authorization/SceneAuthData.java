package com.xiaomi.smarthome.device.authorization;

import com.xiaomi.smarthome.scene.GoLeaveHomeSceneCreateEditActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneAuthData {

    /* renamed from: a  reason: collision with root package name */
    public String f15017a;
    public Map<String, Boolean> b = new HashMap();

    public static SceneAuthData a(JSONObject jSONObject) {
        SceneAuthData sceneAuthData = new SceneAuthData();
        sceneAuthData.b.clear();
        sceneAuthData.f15017a = jSONObject.optString("voicedevid");
        JSONArray optJSONArray = jSONObject.optJSONArray("ctrl_scenes");
        int i = 0;
        while (i < optJSONArray.length()) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                Map<String, Boolean> map = sceneAuthData.b;
                String optString = jSONObject2.optString(GoLeaveHomeSceneCreateEditActivity.SCENE_ID);
                boolean z = true;
                if (jSONObject2.optInt("ctrlable") != 1) {
                    z = false;
                }
                map.put(optString, Boolean.valueOf(z));
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sceneAuthData;
    }
}
