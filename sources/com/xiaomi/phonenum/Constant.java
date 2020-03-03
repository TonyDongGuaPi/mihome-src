package com.xiaomi.phonenum;

import java.io.File;

public class Constant {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12545a = "utf-8";
    public static final boolean b = false;
    public static final String c = "com.xiaomi.simactivate.service.ACTION_BIND_SYSTEM_PHONE_SERVICE";
    public static final String d = "com.xiaomi.simactivate.service";
    public static final String e = (g ? "http://act.preview.account.xiaomi.com/pass/activator" : "https://act.account.xiaomi.com/pass/activator");
    public static final String f = (e + "/getCloudControl");
    private static boolean g = new File("/data/system/xiaomi_account_preview").exists();

    public static void a(boolean z) {
        g = z;
    }
}
