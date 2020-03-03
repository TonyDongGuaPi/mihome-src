package com.xiaomi.mishopsdk.utils;

import java.util.ArrayList;

public class ArrayUtils {
    public static boolean checkArrayEmpty(ArrayList<?> arrayList) {
        return arrayList == null || arrayList.isEmpty();
    }

    public static boolean checkArrayEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }
}
