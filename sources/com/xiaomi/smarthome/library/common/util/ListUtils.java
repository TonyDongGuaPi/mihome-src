package com.xiaomi.smarthome.library.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static boolean a(List<?> list) {
        return list == null || list.size() <= 0;
    }

    public static <E> List<E> a() {
        return new ArrayList();
    }

    public static <E> List<List<E>> a(List<E> list, int i) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (list.size() > i) {
            int i2 = 0;
            while (i2 < list.size()) {
                int i3 = i2 + i;
                arrayList.add(list.subList(i2, Math.min(i3, list.size())));
                i2 = i3;
            }
        } else {
            arrayList.add(list);
        }
        return arrayList;
    }
}
