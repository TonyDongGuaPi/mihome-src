package com.xiaomi.shopviews.model;

import android.os.Bundle;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeThemeItemClick {

    /* renamed from: a  reason: collision with root package name */
    public static String f13164a = "dialog";
    public static String b = "open_web";
    public static String c = "category";
    public static String d = "full_web";
    public static String e = "inner_scroll";
    public static String f = "inner_web";
    public static String g = "keyword";
    public static String h = "native";
    public static String i = "plugin";
    public static String j = "recharge";
    private static final String k = "HomeThemeItemClick";

    public static Bundle a(String str) {
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return bundle;
    }
}
