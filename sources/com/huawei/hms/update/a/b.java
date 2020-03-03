package com.huawei.hms.update.a;

import com.huawei.hms.support.log.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f5903a;
    private String b = "";
    private String c = "";

    public b(String str) {
        this.f5903a = str;
    }

    public String a() {
        if (!"0".equals(this.b) || this.c == null || this.c.isEmpty()) {
            return null;
        }
        return b(this.c);
    }

    public String toString() {
        try {
            return new JSONObject(this.f5903a).toString(2);
        } catch (JSONException unused) {
            return this.f5903a;
        }
    }

    public static b a(String str) {
        b bVar = new b(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar.b = jSONObject.getString("status");
            if ("0".equals(bVar.b)) {
                JSONArray jSONArray = jSONObject.getJSONArray("components");
                if (jSONArray.length() > 0) {
                    bVar.c = jSONArray.getJSONObject(0).getString("url");
                }
            }
            return bVar;
        } catch (JSONException e) {
            a.d("CheckResponse", "In parseResponse, Failed to parse json for check-update response." + e.getMessage());
            return new b(str);
        }
    }

    private static String b(String str) {
        int length = str.length();
        int i = -1;
        while (length > 0 && str.charAt(length - 1) == '/') {
            i = length;
            length--;
        }
        if (i != -1) {
            return str.substring(0, i);
        }
        return str + "/";
    }
}
