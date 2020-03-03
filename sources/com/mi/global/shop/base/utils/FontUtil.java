package com.mi.global.shop.base.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

public class FontUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5679a = "fonts/Lato-Regular.ttf";
    public static final String b = "fonts/Lato-Bold.ttf";
    public static final String c = "fonts/Lato-Italic.ttf";
    public static Typeface d;
    public static Typeface e;
    public static Typeface f;

    public static void a(TextView textView, Context context) {
        try {
            if (d == null) {
                d = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
            }
            if (e == null) {
                e = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
            }
            if (f == null) {
                f = Typeface.createFromAsset(context.getAssets(), c);
            }
            Typeface typeface = textView.getTypeface();
            if (typeface == null) {
                textView.setTypeface(d);
            }
            if (typeface != null && typeface.getStyle() == 2) {
                textView.setTypeface(f);
            } else if (typeface.getStyle() == 1) {
                textView.setTypeface(e);
            } else {
                textView.setTypeface(d);
            }
        } catch (Exception e2) {
            String name = context.getClass().getName();
            Log.e(name, "Unable to load typeface: " + e2.getMessage());
        }
    }
}
