package com.xiaomi.smarthome.device.authorization;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.authorization.BaseAuthData;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceAuthData extends BaseAuthData {
    public static final int e = 2;
    public AtomicBoolean f = new AtomicBoolean(true);
    public boolean g = true;
    private int h = 2;
    private int i;

    public static DeviceAuthData a(String str, List<BaseAuthData.VoiceContrlData> list) {
        DeviceAuthData deviceAuthData = new DeviceAuthData();
        deviceAuthData.f15013a = str;
        deviceAuthData.c = list;
        return deviceAuthData;
    }

    public static DeviceAuthData a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        DeviceAuthData deviceAuthData = new DeviceAuthData();
        if (!jSONObject.isNull("voice_did")) {
            deviceAuthData.a(jSONObject.optString("voice_did"));
        }
        if (!jSONObject.isNull("valid_till")) {
            deviceAuthData.a(jSONObject.optInt("valid_till"));
        }
        if (!jSONObject.isNull("ctrl_devices") && (optJSONArray = jSONObject.optJSONArray("ctrl_devices")) != null && optJSONArray.length() > 0) {
            deviceAuthData.c.clear();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (!optJSONObject.isNull("did") && !optJSONObject.isNull("ctrlable")) {
                    BaseAuthData.VoiceContrlData voiceContrlData = new BaseAuthData.VoiceContrlData();
                    voiceContrlData.i = i2;
                    voiceContrlData.f15014a = optJSONObject.optString("did");
                    voiceContrlData.b = optJSONObject.optString("ctrlable");
                    voiceContrlData.c = optJSONObject.optString("name");
                    voiceContrlData.d = optJSONObject.optString("model");
                    deviceAuthData.c.add(voiceContrlData);
                }
            }
        }
        if (!jSONObject.isNull("auth_all_device")) {
            deviceAuthData.f.set(jSONObject.optBoolean("auth_all_device"));
        }
        if (!jSONObject.isNull("auto_auth_switch")) {
            deviceAuthData.g = jSONObject.optBoolean("auto_auth_switch");
        }
        return deviceAuthData;
    }

    /* renamed from: b */
    public DeviceAuthData clone() {
        DeviceAuthData deviceAuthData = new DeviceAuthData();
        deviceAuthData.a(this.f15013a);
        deviceAuthData.b(this.h);
        deviceAuthData.a(this.i);
        deviceAuthData.c.addAll(this.c);
        return deviceAuthData;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("voicedevid", this.f15013a);
            jSONObject.put("add_allow", new JSONArray());
            jSONObject.put("remove_allow", new JSONArray());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public List<BaseAuthData.VoiceContrlData> d() {
        return this.c;
    }

    public String e() {
        return this.f15013a;
    }

    public void a(String str) {
        this.f15013a = str;
    }

    public void a(List<BaseAuthData.VoiceContrlData> list) {
        this.c = list;
    }

    public int f() {
        return this.i;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public void b(int i2) {
        this.h = i2;
    }

    public String toString() {
        return "DeviceAuthData{did='" + this.f15013a + Operators.SINGLE_QUOTE + ", defaultAuth=" + this.h + ", validTill=" + this.i + Operators.BLOCK_END;
    }

    public static void b(List<BaseAuthData.VoiceContrlData> list) {
        Collections.sort(list);
    }
}
