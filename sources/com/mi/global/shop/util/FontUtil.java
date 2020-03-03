package com.mi.global.shop.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

public class FontUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7094a = "font/Lato-Regular.ttf";
    public static final String b = "font/Lato-Bold.ttf";
    public static Typeface c;
    public static Typeface d;

    public static void a(TextView textView, Context context) {
        try {
            if (c == null) {
                c = Typeface.createFromAsset(context.getAssets(), f7094a);
            }
            if (d == null) {
                d = Typeface.createFromAsset(context.getAssets(), b);
            }
            Typeface typeface = textView.getTypeface();
            if (typeface == null) {
                textView.setTypeface(c);
            } else if (typeface.getStyle() == 1) {
                textView.setTypeface(d);
            } else {
                textView.setTypeface(c);
            }
        } catch (Exception e) {
            String name = context.getClass().getName();
            Log.e(name, "Unable to load typeface: " + e.getMessage());
        }
    }
}
