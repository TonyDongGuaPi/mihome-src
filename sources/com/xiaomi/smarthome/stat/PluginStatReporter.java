package com.xiaomi.smarthome.stat;

import android.text.TextUtils;
import com.xiaomi.smarthome.stat.report.StatReporter;
import org.json.JSONObject;

public class PluginStatReporter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22747a = "click";
    public static final String b = "result";
    private static StatReporter c = new StatReporter("plugin");
    private static StatReporter d = new StatReporter("click");
    private static StatReporter e = new StatReporter("result");

    public static void a(String str, long j, String str2) {
    }

    public static void c(String str, long j, String str2) {
    }

    public static void e(String str, long j, String str2) {
    }

    public static final String a(long j, long j2) {
        return "plugin." + j + "." + j2;
    }

    public static long a(String str) {
        return c.a("entry", str, new Object[0]);
    }

    public static void b(String str, long j, String str2) {
        c.a("rn_plugin_hot_start", str, "time", Long.valueOf(j), "model", str2);
    }

    public static void d(String str, long j, String str2) {
        c.a("rn_plugin_hot_loadtime", str, "time", Long.valueOf(j), "model", str2);
    }

    public static void f(String str, long j, String str2) {
        c.a("rn_plugin_rendering_opentime", str, "time", Long.valueOf(j), "model", str2);
    }

    public static long a(String str, long j) {
        return c.a("exit", str, "stay_time", Long.valueOf((System.currentTimeMillis() - j) / 1000));
    }

    public static void a(String str, long j, String str2, int i) {
        c.a("plugin_launcher_time", str, "time", Long.valueOf(j), "model", str2, "version", Integer.valueOf(i));
    }

    public static void b(String str, long j, String str2, int i) {
        c.a("plugin_open_time", str, "time", Long.valueOf(j), "model", str2, "version", Integer.valueOf(i));
    }

    public static void a(String str, long j, long j2, long j3, String str2, int i) {
        c.a("plugin_init_time", str, "time", Long.valueOf(j), "startTime", Long.valueOf(j2), "loadTime", Long.valueOf(j3), "model", str2, "version", Integer.valueOf(i));
    }

    public static long a(String str, String str2, String str3, Object obj, String str4) {
        if (obj == null) {
            return 0;
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = obj.getClass().toString();
        }
        return c.a("page_start", str, "model", str2, "name", str4, "did", str3);
    }

    public static long a(String str, String str2, String str3, Object obj, long j, String str4) {
        if (obj == null) {
            return 0;
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = obj.getClass().toString();
        }
        return c.a("page_end", str, "model", str2, "name", str4, "did", str3, "stay_time", Long.valueOf((System.currentTimeMillis() - j) / 1000));
    }

    public static void a(String str, String str2, String str3, String str4, JSONObject jSONObject) {
        StatReporter statReporter;
        if ("click".equals(str2)) {
            statReporter = d;
        } else if ("result".equals(str2)) {
            statReporter = e;
        } else {
            return;
        }
        statReporter.a(str3, str, StatReporter.a("plugin", jSONObject), str4);
    }
}
