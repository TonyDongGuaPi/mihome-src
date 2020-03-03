package com.xiaomi.mishopsdk.util;

public final class StatUtil {
    private StatUtil() {
    }

    public static String formatStatPosition(int i) {
        Object[] objArr = new Object[1];
        if (i > 999) {
            i = 999;
        }
        objArr[0] = Integer.valueOf(i);
        return String.format("%03d", objArr);
    }
}
