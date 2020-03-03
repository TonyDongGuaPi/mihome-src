package com.mi.global.shop.util;

public class ClickUtil {

    /* renamed from: a  reason: collision with root package name */
    private static long f7051a;

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - f7051a < 800) {
            return true;
        }
        f7051a = currentTimeMillis;
        return false;
    }
}
