package com.xiaomi.jr.appbase.app;

public class MiFiAppController {

    /* renamed from: a  reason: collision with root package name */
    private static MiFiAppControllerImpl f1393a;

    private MiFiAppController() {
    }

    public static void a(MiFiAppControllerImpl miFiAppControllerImpl) {
        f1393a = miFiAppControllerImpl;
    }

    public static MiFiAppControllerImpl a() {
        return f1393a;
    }
}
