package com.xiaomi.jr.stats;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.jr.common.utils.MifiLog;
import java.util.Map;

public class SensorsDataManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11030a = "element_id";
    public static final String b = "element_position";
    public static final String c = "element_type";
    public static final String d = "screen_area";
    public static final String e = "screen_name";
    public static final String f = "stat";
    public static final String g = "login_result";
    public static final String h = "share_title";
    public static final String i = "share_url";
    public static final String j = "unlock_type";
    public static final String k = "unlock_result";
    public static final String l = "$AppClick";
    public static final String m = "AppInstall";
    public static final String n = "AppExpose";
    public static final String o = "Login";
    public static final String p = "Share";
    public static final String q = "Unlock";
    public static final String r = "succeed";
    public static final String s = "failed";
    public static final String t = "from";
    public static final String u = "N/A";
    private static final String v = "SensorsDataManager";
    private static SensorsDataTarget w = new EmptyAdapter();

    public static void a(SensorsDataTarget sensorsDataTarget) {
        w = sensorsDataTarget;
        MifiLog.b(v, "instance is initiated to " + sensorsDataTarget);
    }

    public static SensorsDataTarget a() {
        return w;
    }

    public static Map<String, String> a(String str) {
        return (Map) new Gson().fromJson(str, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public static String a(Map map) {
        return new Gson().toJson((Object) map);
    }
}
