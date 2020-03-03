package com.xiaomi.base.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontUtils {

    /* renamed from: a  reason: collision with root package name */
    static Typeface f10028a;

    public static void a(Context context, TextView textView) {
        if (textView != null && context != null) {
            try {
                if (f10028a == null) {
                    f10028a = Typeface.createFromAsset(context.getAssets(), "Fonts/Avenir.ttf");
                }
                textView.setTypeface(f10028a);
                textView.setPaintFlags(textView.getPaintFlags() | 128 | 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
