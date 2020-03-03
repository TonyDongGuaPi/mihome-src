package com.xiaomi.smarthome.library.common;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

public class FontManager {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<String, Typeface> f18559a = new HashMap<>();

    public static Typeface a(Context context, String str) {
        Typeface typeface;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Typeface defaultFromStyle = Typeface.defaultFromStyle(0);
            if (!TextUtils.isEmpty(str)) {
                Typeface typeface2 = f18559a.get(str);
                if (typeface2 == null) {
                    try {
                        typeface = Typeface.createFromAsset(context.getAssets(), str);
                    } catch (Exception e) {
                        Log.e("FontManger", "createTypeface exception");
                        e.printStackTrace();
                        typeface = typeface2;
                    }
                    if (typeface != null) {
                        f18559a.put(str, typeface);
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

    public static void a() {
        f18559a.clear();
    }
}
