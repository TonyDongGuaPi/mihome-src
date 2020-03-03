package com.xiaomi.jr.mipay.common;

import com.xiaomi.jr.account.AccountChangeEvent;
import java.util.UUID;
import org.greenrobot.eventbus.Subscribe;

public class SessionManager {

    /* renamed from: a  reason: collision with root package name */
    private static String f1448a;
    private static boolean b;
    private static int c;
    private static String d;

    public static class FinishCode {

        /* renamed from: a  reason: collision with root package name */
        public static int f10941a = 0;
        public static int b = 1;
        public static int c = 2;
    }

    @Subscribe
    public static void onAccountChange(AccountChangeEvent accountChangeEvent) {
        if (accountChangeEvent.a() == 1) {
            a(FinishCode.b, (String) null);
        }
    }

    public static void a() {
        f1448a = UUID.randomUUID().toString();
        c = 0;
        b = false;
    }

    public static void a(int i, String str) {
        f1448a = null;
        DeviceManager.a();
        c = i;
        d = str;
        b = true;
    }

    public static boolean b() {
        return b;
    }

    public static int c() {
        return c;
    }

    public static String d() {
        return d;
    }

    public static String e() {
        return f1448a;
    }
}
