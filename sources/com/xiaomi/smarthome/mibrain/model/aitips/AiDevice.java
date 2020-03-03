package com.xiaomi.smarthome.mibrain.model.aitips;

import android.text.TextUtils;
import org.json.JSONObject;

public class AiDevice {

    /* renamed from: a  reason: collision with root package name */
    private String f10657a;
    private String b;
    private AiTipsDevice c;

    public static AiDevice a(JSONObject jSONObject) {
        AiDevice aiDevice = new AiDevice();
        String optString = jSONObject.optString("ctrl_did");
        String optString2 = jSONObject.optString("method");
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            return null;
        }
        aiDevice.a(optString);
        aiDevice.b(optString2);
        JSONObject optJSONObject = jSONObject.optJSONObject("device");
        if (optJSONObject != null) {
            AiTipsDevice a2 = AiTipsDevice.a(optJSONObject);
            if (a2 == null) {
                return null;
            }
            aiDevice.a(a2);
        }
        return aiDevice;
    }

    public String a() {
        return this.f10657a;
    }

    public void a(String str) {
        this.f10657a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public AiTipsDevice c() {
        return this.c;
    }

    public void a(AiTipsDevice aiTipsDevice) {
        this.c = aiTipsDevice;
    }
}
