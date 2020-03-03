package com.mi.global.shop.util;

import android.app.Application;
import android.content.Context;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.mistatistic.sdk.controller.MiStatOptions;

public abstract class MiShopStatInterface {

    /* renamed from: a  reason: collision with root package name */
    private static String f7100a = "";

    public static final void b() {
    }

    public static void a(String str) {
        if (str == null) {
            f7100a = "";
        } else {
            f7100a = str;
        }
    }

    public static final void a(Application application, MiStatOptions miStatOptions) {
        MiStatInterface.a(application, miStatOptions);
    }

    public static final String a(Context context) {
        return MiStatInterface.a(context);
    }

    public static MiStatOptions a() {
        return MiStatInterface.a();
    }

    public static final void a(Context context, String str) {
        f7100a + str;
    }

    public static final void a(String str, String str2) {
        MiStatInterface.a(str, f7100a + str2);
    }

    public static final void a(String str, String str2, String str3, String str4) {
        MiStatInterface.a(str, f7100a + str2, str3, str4);
    }

    public static final void a(String str, String str2, String str3, String str4, String str5) {
        MiStatInterface.a(str, f7100a + str2, str3, str4, str5);
    }

    public static final void a(String str, String str2, String str3, String str4, long j) {
        MiStatInterface.a(str, f7100a + str2, str3, str4, j);
    }

    public static final void b(String str, String str2, String str3, String str4, String str5) {
        MiStatInterface.b(str, f7100a + str2, str3, str4, str5);
    }

    public static final void b(String str, String str2) {
        MiStatInterface.b(str, f7100a + str2);
    }

    public static final void a(String str, String str2, String str3) {
        MiStatInterface.a(str, f7100a + str2, str3);
    }

    public static final void c(String str, String str2) {
        MiStatInterface.c(str, f7100a + str2);
    }

    public static final void b(String str, String str2, String str3) {
        MiStatInterface.b(str, f7100a + str2, str3);
    }

    public static final void b(String str) {
        MiStatInterface.b(str);
    }

    public static final void c(String str) {
        MiStatInterface.c(str);
    }

    public static final void d(String str) {
        MiStatInterface.d(str);
    }

    public static final void a(int i, long j) {
        MiStatInterface.a(i, j);
    }

    public static final int c() {
        return MiStatInterface.c();
    }
}
