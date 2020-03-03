package com.mi.mistatistic.sdk;

import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;
import com.mi.mistatistic.sdk.controller.EventDAO;
import com.mi.mistatistic.sdk.controller.PrefPersistUtils;

public class CustomSettings {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f7316a = false;
    private static boolean b = false;
    private static boolean c = true;
    private static boolean d = false;
    private static boolean e = false;

    public static void a(String str) {
        ApplicationContextHolder.c(str);
    }

    public static void b(String str) {
        ApplicationContextHolder.d(str);
    }

    public static void a() {
        PrefPersistUtils.b(ApplicationContextHolder.a());
        AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new ClearDataJob());
    }

    static class ClearDataJob implements AsyncJobDispatcher.AsyncJob {
        public void a() {
        }

        ClearDataJob() {
        }
    }

    public static void a(boolean z) {
        f7316a = z;
    }

    public static boolean b() {
        return f7316a;
    }

    public static void b(boolean z) {
        if (!z || (ApplicationContextHolder.a().getApplicationInfo().flags & 1) != 0) {
            b = z;
        }
    }

    public static boolean c() {
        return b;
    }

    public static boolean d() {
        return c;
    }

    public static boolean e() {
        return d;
    }

    public static void c(boolean z) {
        d = z;
    }

    public static boolean f() {
        return e;
    }

    public static void d(boolean z) {
        e = z;
    }

    public static void c(String str) {
        EventDAO.b = true;
        EventDAO.f7329a = str;
    }
}
