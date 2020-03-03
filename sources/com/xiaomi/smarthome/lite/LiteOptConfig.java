package com.xiaomi.smarthome.lite;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteOptConfig {

    /* renamed from: a  reason: collision with root package name */
    public String f19363a;
    public String b;
    public String c;
    public String[] d;
    public String e;
    public String f;
    public String g;

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("prop_name");
                if (!optString.contains(".")) {
                    this.f19363a = "prop." + optString;
                } else {
                    this.f19363a = optString;
                }
                this.b = jSONObject.optString("prop_value");
                this.c = jSONObject.optString("rpc_method");
                this.e = jSONObject.optString("icon");
                this.f = jSONObject.optString("next_value");
                this.g = "" + jSONObject.optString("style");
                JSONArray optJSONArray = jSONObject.optJSONArray("rpc_params");
                if (optJSONArray != null) {
                    this.d = new String[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        this.d[i] = optJSONArray.optString(i);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
