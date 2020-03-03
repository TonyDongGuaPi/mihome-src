package com.xiaomi.smarthome.miio.miband.data;

import org.json.JSONException;
import org.json.JSONObject;

public class UserData {
    public static final String h = "userid";
    public static final String i = "avatar";
    public static final String j = "username";
    public static final String k = "age";
    public static final String l = "weight";
    public static final String m = "height";
    public static final String n = "stepsGoal";

    /* renamed from: a  reason: collision with root package name */
    public int f19466a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userid", this.f19466a);
            jSONObject.put("avatar", this.g);
            jSONObject.put("username", this.f);
            jSONObject.put("age", this.d);
            jSONObject.put(l, this.b);
            jSONObject.put("height", this.c);
            jSONObject.put(n, this.e);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static UserData a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        UserData userData = new UserData();
        userData.f19466a = jSONObject.optInt("userid");
        userData.b = jSONObject.optInt(l);
        userData.c = jSONObject.optInt("height");
        userData.d = jSONObject.optInt("age");
        userData.e = jSONObject.optInt(n, -1);
        if (userData.e == -1) {
            userData.e = Integer.decode(jSONObject.optString(n, "0")).intValue();
        }
        userData.f = jSONObject.optString("username");
        userData.g = jSONObject.optString("avatar");
        return userData;
    }
}
