package com.xiaomi.youpin.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

public class FontManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23772a = "FontManager";
    private static final HashMap<String, Typeface> b = new HashMap<>();

    public static Typeface a(Context context, String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Method call should happen from the main thread.");
        } else if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            Typeface typeface = b.get(str);
            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(context.getAssets(), str);
                } catch (Exception unused) {
                    Log.w(f23772a, "create type face from asset error!");
                }
                if (typeface != null) {
                    b.put(str, typeface);
                }
            }
            return typeface;
        }
    }
}
