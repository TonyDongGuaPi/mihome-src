package com.xiaomi.jr.common.app;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class PhotoManager {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Bitmap> f10358a = new HashMap();
    private static Map<String, Bitmap> b = new HashMap();

    public static void a() {
        a(f10358a);
        a(b);
    }

    private static void a(Map<String, Bitmap> map) {
        for (Bitmap next : map.values()) {
            if (!next.isRecycled()) {
                next.recycle();
            }
        }
        map.clear();
    }

    public static void a(String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(str) && bitmap != null && !bitmap.isRecycled()) {
            f10358a.put(str, bitmap);
        }
    }

    public static Bitmap a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return f10358a.get(str);
        }
        return null;
    }

    public static void b(String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(str) && bitmap != null && !bitmap.isRecycled()) {
            b.put(str, bitmap);
        }
    }

    public static Bitmap b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return b.get(str);
        }
        return null;
    }
}
