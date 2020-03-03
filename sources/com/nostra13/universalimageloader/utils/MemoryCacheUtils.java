package com.nostra13.universalimageloader.utils;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MemoryCacheUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8497a = "_";
    private static final String b = "x";

    private MemoryCacheUtils() {
    }

    public static String a(String str, ImageSize imageSize) {
        return str + "_" + imageSize.a() + "x" + imageSize.b();
    }

    public static Comparator<String> a() {
        return new Comparator<String>() {
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
            }
        };
    }

    public static List<Bitmap> a(String str, MemoryCache memoryCache) {
        ArrayList arrayList = new ArrayList();
        for (String next : memoryCache.a()) {
            if (next.startsWith(str)) {
                arrayList.add(memoryCache.a(next));
            }
        }
        return arrayList;
    }

    public static List<String> b(String str, MemoryCache memoryCache) {
        ArrayList arrayList = new ArrayList();
        for (String next : memoryCache.a()) {
            if (next.startsWith(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static void c(String str, MemoryCache memoryCache) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String next : memoryCache.a()) {
            if (next.startsWith(str)) {
                arrayList.add(next);
            }
        }
        for (String b2 : arrayList) {
            memoryCache.b(b2);
        }
    }
}
