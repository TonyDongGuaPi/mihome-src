package com.xiaomi.smarthome.framework.api;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class UserConfig {
    public static final int A = 1;

    /* renamed from: a  reason: collision with root package name */
    public static final int f16436a = 0;
    public static final String b = "0";
    public static final String c = "2";
    public static final String d = "3";
    public static final String e = "4";
    public static final String f = "5";
    public static final String g = "20";
    public static final int h = 1000;
    public static final int i = 2000;
    public static final int j = 3000;
    public static final int k = 4000;
    public static final int l = 6000;
    public static final String m = "7";
    public static final String n = "8";
    public static final String o = "9";
    public static final int p = 7;
    public static final int q = 100;
    public static final int r = 1001;
    public static final int s = 2001;
    public static final int t = 3001;
    public static final int u = 4001;
    public static final int v = 5001;
    public static final int w = 6001;
    public static final int x = 7001;
    public static final int y = 1000;
    public static final int z = 1;
    public int B;
    public String C;
    public ArrayList<UserConfigAttr> D;

    public static UserConfig a(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("component_id");
        String optString = jSONObject.optString("key");
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (TextUtils.isEmpty(optString) || optJSONObject == null) {
            return null;
        }
        UserConfig userConfig = new UserConfig();
        userConfig.B = optInt;
        userConfig.C = optString;
        userConfig.D = new ArrayList<>();
        Iterator<String> keys = optJSONObject.keys();
        while (keys != null && keys.hasNext()) {
            String next = keys.next();
            userConfig.D.add(new UserConfigAttr(next, optJSONObject.optString(next)));
        }
        return userConfig;
    }

    public static JSONObject a(UserConfig userConfig) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (userConfig == null) {
            return jSONObject;
        }
        jSONObject.put("component_id", userConfig.B);
        jSONObject.put("key", userConfig.C);
        JSONObject jSONObject2 = new JSONObject();
        for (int i2 = 0; i2 < userConfig.D.size(); i2++) {
            jSONObject2.put(userConfig.D.get(i2).f16437a, userConfig.D.get(i2).b);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject;
    }

    public static class UserConfigAttr {

        /* renamed from: a  reason: collision with root package name */
        public String f16437a;
        public String b;

        public UserConfigAttr(String str, String str2) {
            this.f16437a = str;
            this.b = str2;
        }
    }

    public static class UserConfigData {

        /* renamed from: a  reason: collision with root package name */
        public String f16438a;
        public int b;
        public String c;

        public static UserConfigData a(JSONObject jSONObject) {
            UserConfigData userConfigData = new UserConfigData();
            userConfigData.f16438a = jSONObject.optString("component_id");
            try {
                userConfigData.b = Integer.valueOf(jSONObject.optString("key")).intValue();
                userConfigData.c = jSONObject.optString("data");
                if (TextUtils.isEmpty(userConfigData.c)) {
                    return null;
                }
                return userConfigData;
            } catch (Exception unused) {
                return null;
            }
        }

        public static JSONObject a(UserConfigData userConfigData) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("component_id", userConfigData.f16438a);
            jSONObject.put("key", userConfigData.b);
            jSONObject.put("data", userConfigData.c);
            return jSONObject;
        }
    }
}
