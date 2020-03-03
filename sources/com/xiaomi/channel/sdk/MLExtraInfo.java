package com.xiaomi.channel.sdk;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MLExtraInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f10066a = "";
    public String b = "";
    public int c = 0;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", this.f10066a);
            jSONObject.put("extraUri", this.b);
            jSONObject.put("availableVersion", this.c);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f10066a = jSONObject.optString("action");
                this.b = jSONObject.optString("extraUri");
                this.c = jSONObject.optInt("availableVersion");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
