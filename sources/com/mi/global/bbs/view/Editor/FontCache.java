package com.mi.global.bbs.view.Editor;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class FontCache {
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String str, Context context) {
        Typeface typeface = fontCache.get(str);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), str);
                fontCache.put(str, typeface);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return typeface;
    }
}
