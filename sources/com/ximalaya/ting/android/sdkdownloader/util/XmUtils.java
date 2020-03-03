package com.ximalaya.ting.android.sdkdownloader.util;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import miuipub.reflect.Field;

public class XmUtils {
    public static String a(Collection collection) {
        String str = "";
        if (collection == null || collection.size() <= 0) {
            return str;
        }
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i == collection.size() - 1) {
                return str + it.next();
            }
            str = str + it.next() + ",";
            i++;
        }
        return str;
    }

    public static <T, E> List<T> a(List<E> list, Map<E, T> map) {
        if (list == null || list.isEmpty() || map == null || map.isEmpty()) {
            return b();
        }
        ArrayList arrayList = new ArrayList();
        for (E e : list) {
            arrayList.add(map.get(e));
        }
        return arrayList;
    }

    public static String a(String str) {
        return str + DefaultDiskStorage.FileType.TEMP;
    }

    public static String a(double d) {
        if (d < 1024.0d) {
            return String.format("%.2f", new Object[]{Double.valueOf(d)}) + Field.b;
        }
        double d2 = d / 1024.0d;
        if (d2 < 1024.0d) {
            return String.format("%.2f", new Object[]{Double.valueOf(d2)}) + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1024.0d) {
            return String.format("%.2f", new Object[]{Double.valueOf(d3)}) + "MB";
        }
        return String.format("%.2f", new Object[]{Double.valueOf(d3 / 1024.0d)}) + "G";
    }

    public static final <T> Set<T> a() {
        return new HashSet();
    }

    public static final <T> List<T> b() {
        return new ArrayList();
    }

    public static final <K, V> Map<K, V> c() {
        return new HashMap();
    }
}
