package com.xiaomi.smarthome.framework.statistic;

import com.xiaomi.smarthome.frame.core.CoreApi;

public class StatUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17686a = "mitv";
    public static final String b = "yunyi";
    public static final String c = "mirouter";
    public static final String d = "mplugin";
    public static final String e = "mihome";
    public static final String f = "mibrain";
    public static final String g = "micro";
    public static final String h = "miui_splash";
    public static final String i = "youpin_popup";
    private static final String j = " ";

    public static String a(String str, String str2) {
        String s = CoreApi.a().s();
        long currentTimeMillis = System.currentTimeMillis();
        return s + " " + currentTimeMillis + " " + str + " " + str2;
    }
}
