package com.unionpay.mobile.android.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class j {
    public static String a(JSONArray jSONArray, int i) {
        if (jSONArray != null && i >= 0 && i < jSONArray.length()) {
            try {
                return jSONArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String a(JSONObject jSONObject, String str) {
        if (f(jSONObject, str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return "";
    }

    public static int b(JSONObject jSONObject, String str) {
        if (f(jSONObject, str)) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return 0;
    }

    public static Object b(JSONArray jSONArray, int i) {
        if (jSONArray != null && i < jSONArray.length() && i >= 0) {
            try {
                return jSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject c(JSONObject jSONObject, String str) {
        if (f(jSONObject, str)) {
            try {
                return jSONObject.getJSONObject(str);
            } catch (JSONException unused) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return null;
    }

    public static JSONArray d(JSONObject jSONObject, String str) {
        if (f(jSONObject, str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (JSONException unused) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return null;
    }

    public static List<JSONArray> e(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList(1);
        JSONArray d = d(jSONObject, str);
        int i = 0;
        while (d != null && i < d.length()) {
            arrayList.add((JSONArray) b(d, i));
            i++;
        }
        return arrayList;
    }

    private static boolean f(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
