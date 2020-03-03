package com.xiaomi.jr.appbase.accounts;

public class MiFiAccountNotifier {

    /* renamed from: a  reason: collision with root package name */
    private static MiFiAccountNotifierImpl f10312a = new MiFiAccountNotifierImpl();

    private MiFiAccountNotifier() {
    }

    public static void a(MiFiAccountNotifierImpl miFiAccountNotifierImpl) {
        f10312a = miFiAccountNotifierImpl;
    }

    public static MiFiAccountNotifierImpl a() {
        return f10312a;
    }
}
