package com.xiaomi.smarthome.library.common.util;

import java.util.Random;

public class RandUtils {

    /* renamed from: a  reason: collision with root package name */
    private static Random f18701a = new Random(System.currentTimeMillis());

    public static double a() {
        return f18701a.nextDouble();
    }
}
