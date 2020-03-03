package com.xiaomi.mistatistic.sdk;

import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.controller.f;
import com.xiaomi.mistatistic.sdk.controller.k;
import com.xiaomi.mistatistic.sdk.controller.l;

public class CustomSettings {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11994a = false;
    private static boolean b = false;
    private static boolean c = true;
    private static boolean d = false;
    private static boolean e = false;

    public static void a(String str) {
        c.a(str);
    }

    public static void b(String str) {
        c.b(str);
    }

    public static void c(String str) {
        MiStatInterface.a(str);
    }

    public static void a() {
        k.b(c.a());
        d.a().a((d.a) new a());
    }

    static class a implements d.a {
        a() {
        }

        public void a() {
            new f().c(System.currentTimeMillis() + 1000);
        }
    }

    public static void a(boolean z) {
        f11994a = z;
    }

    public static boolean b() {
        return f11994a;
    }

    public static void b(boolean z) {
        if (!z || (c.a().getApplicationInfo().flags & 1) != 0) {
            b = z;
        }
    }

    public static boolean c() {
        return b;
    }

    public static void c(boolean z) {
        c = z;
        if (z) {
            new l().a();
        }
    }

    public static boolean d() {
        return c;
    }

    public static boolean e() {
        return d;
    }

    public static void d(boolean z) {
        d = z;
    }

    public static boolean f() {
        return e;
    }

    public static void e(boolean z) {
        e = z;
    }

    public static void d(String str) {
        f.b = true;
        f.f12024a = str;
    }
}
