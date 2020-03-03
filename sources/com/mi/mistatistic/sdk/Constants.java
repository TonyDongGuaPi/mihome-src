package com.mi.mistatistic.sdk;

public class Constants {

    /* renamed from: a  reason: collision with root package name */
    public static String f7315a;

    static {
        String str;
        if (BuildSetting.b()) {
            str = "http://in.stat.appmifile.com/";
        } else if (BuildSetting.d()) {
            str = "http://cn.stat.appmifile.com/";
        } else {
            str = BuildSetting.c() ? "https://ru.stat.appmifile.com/" : "http://sg.stat.appmifile.com/";
        }
        f7315a = str;
    }
}
