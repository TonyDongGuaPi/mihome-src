package com.alipay.security.mobile.module.b;

import android.content.Context;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f1163a = new a();

    private a() {
    }

    public static a a() {
        return f1163a;
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }
}
