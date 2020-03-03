package com.miuipub.internal.util;

import android.app.Application;
import java.io.File;

public class PackageConstants {

    /* renamed from: a  reason: collision with root package name */
    public static Application f8284a = null;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static int f = 0;
    public static final String g = "miuisdk";
    public static final String h = "/system/app/miuipub.apk";
    public static final boolean i = new File(h).exists();

    private PackageConstants() {
    }

    public static Application a() {
        return f8284a;
    }

    public static void a(Application application) {
        f8284a = application;
    }
}
