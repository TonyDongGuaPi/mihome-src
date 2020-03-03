package com.xiaomi.smarthome.miio;

import com.xiaomi.infrared.activity.IRStudyTipActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class MiioMessageFactory {
    public static String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 1);
            jSONObject.put("method", "miIO.info");
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 1);
            jSONObject.put("method", "miIO.reboot");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("firmware", "ghost");
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 1);
            jSONObject.put("method", IRStudyTipActivity.IR_API_JSON_MIIO_LEARN);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("key", str);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static String b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 1);
            jSONObject.put("method", IRStudyTipActivity.IR_API_JSON_MIIO_READ);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("key", str);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static String c(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 1);
            jSONObject.put("method", "miIO.ir_play");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", str);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }
}
