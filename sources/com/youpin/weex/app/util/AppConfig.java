package com.youpin.weex.app.util;

import android.content.Context;

public class AppConfig {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2540a = "AppConfig";
    private static AppPreferences b = new AppPreferences();

    public static void a(Context context) {
        b(context);
    }

    public static String a() {
        if (b().booleanValue()) {
            return b.b("local_url", "file://assets/index.js");
        }
        return b.b("launch_url", "http://127.0.0.1:8080/dist/index.js");
    }

    public static Boolean b() {
        return Boolean.valueOf(b.b("launch_locally", false));
    }

    private static void b(Context context) {
        AppConfigXmlParser appConfigXmlParser = new AppConfigXmlParser();
        appConfigXmlParser.a(context);
        b = appConfigXmlParser.a();
    }
}
