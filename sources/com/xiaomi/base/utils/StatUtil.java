package com.xiaomi.base.utils;

public final class StatUtil {
    public static String a(int i) {
        Object[] objArr = {null};
        if (i > 999) {
            i = 999;
        }
        objArr[0] = Integer.valueOf(i);
        return String.format("%03d", objArr);
    }
}
