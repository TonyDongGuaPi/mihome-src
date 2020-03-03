package com.xiaomi.jr.mipay.safekeyboard.utils;

import java.util.HashMap;
import java.util.Map;

public class KeyCode {

    /* renamed from: a  reason: collision with root package name */
    public static int f10989a = 48;
    public static int b = 57;
    public static int c = 46;
    public static int d = 13;
    public static int e = 127;
    public static int f = -1;
    public static int g = 0;
    public static int h = -1;
    private static Map<Integer, Integer> i;

    static {
        a();
    }

    private static void a() {
        i = new HashMap();
        int i2 = 7 - f10989a;
        for (int i3 = f10989a; i3 <= b; i3++) {
            i.put(Integer.valueOf(i3), Integer.valueOf(i3 + i2));
        }
        i.put(Integer.valueOf(c), 56);
        i.put(Integer.valueOf(d), 66);
        i.put(Integer.valueOf(e), 67);
        i.put(Integer.valueOf(f), Integer.valueOf(h));
        i.put(Integer.valueOf(g), 0);
    }

    public static int a(int i2) {
        Integer num = i.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("The general keyCode " + i2 + " cannot map into android keyCode");
    }
}
