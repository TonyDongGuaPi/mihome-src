package com.xiaomi.base.utils;

import java.util.ArrayList;

public class ArrayUtils {
    public static boolean a(ArrayList<?> arrayList) {
        return arrayList == null || arrayList.isEmpty();
    }

    public static boolean a(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }
}
