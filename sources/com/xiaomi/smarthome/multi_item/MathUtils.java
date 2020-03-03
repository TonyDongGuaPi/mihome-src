package com.xiaomi.smarthome.multi_item;

public class MathUtils {
    public static int a(int i, int i2) {
        while (true) {
            int i3 = i % i2;
            if (i3 == 0) {
                return i2;
            }
            int i4 = i2;
            i2 = i3;
            i = i4;
        }
    }

    public static int b(int i, int i2) {
        return (i * i2) / a(i, i2);
    }

    public static int a(int[] iArr) {
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            i = b(i, iArr[i2]);
        }
        return i;
    }
}
