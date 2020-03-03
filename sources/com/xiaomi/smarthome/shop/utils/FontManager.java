package com.xiaomi.smarthome.shop.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

public class FontManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22189a = "FontManager";
    private static final HashMap<String, Typeface> b = new HashMap<>();

    public static Typeface a(Context context, String str) {
        Typeface typeface;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Typeface defaultFromStyle = Typeface.defaultFromStyle(0);
            if (!TextUtils.isEmpty(str)) {
                Typeface typeface2 = b.get(str);
                if (typeface2 == null) {
                    try {
                        typeface = Typeface.createFromAsset(context.getAssets(), str);
                    } catch (Exception unused) {
                        Log.w(f22189a, "create type face from asset error!");
                        typeface = typeface2;
                    }
                    if (typeface != null) {
                        b.put(str, typeface);
                    }
                } else {
                    typeface = typeface2;
                }
                if (typeface != null) {
                    return typeface;
                }
            }
            return defaultFromStyle;
        }
        throw new IllegalStateException("Method call should happen from the main thread.");
    }
}
