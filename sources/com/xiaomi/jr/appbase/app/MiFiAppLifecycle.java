package com.xiaomi.jr.appbase.app;

public class MiFiAppLifecycle {

    /* renamed from: a  reason: collision with root package name */
    private static MiFiAppLifecycleImpl f1396a;

    private MiFiAppLifecycle() {
    }

    public static void a(MiFiAppLifecycleImpl miFiAppLifecycleImpl) {
        f1396a = miFiAppLifecycleImpl;
    }

    public static MiFiAppLifecycleImpl a() {
        return f1396a;
    }
}
