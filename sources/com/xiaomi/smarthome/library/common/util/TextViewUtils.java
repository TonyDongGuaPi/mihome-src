package com.xiaomi.smarthome.library.common.util;

import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class TextViewUtils {
    private static Field b() {
        Field field = null;
        try {
            Field declaredField = Class.forName("android.text.TextLine").getDeclaredField("sCached");
            try {
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception e) {
                Field field2 = declaredField;
                e = e;
                field = field2;
                e.printStackTrace();
                return field;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return field;
        }
    }

    public static void a() {
        Object obj;
        Field b = b();
        if (b != null) {
            try {
                obj = b.get((Object) null);
            } catch (Exception unused) {
                obj = null;
            }
            if (obj != null) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Array.set(obj, i, (Object) null);
                }
            }
        }
    }

    public static void a(TextView textView, CharSequence charSequence, float f, CharSequence charSequence2) {
        if (f > a(textView, charSequence)) {
            textView.setText(charSequence);
            return;
        }
        int length = charSequence.length() - 2;
        StringBuilder sb = new StringBuilder(charSequence);
        String sb2 = sb.toString();
        while (a(textView, (CharSequence) sb2) > f) {
            length--;
            sb2 = sb.substring(0, length);
        }
        textView.setText(sb2 + charSequence2);
    }

    public static float a(TextView textView, CharSequence charSequence) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(textView.getTextSize());
        return textPaint.measureText(charSequence, 0, charSequence.length() - 1);
    }

    public static void a(TextView textView, CharSequence charSequence, int i, int i2, CharSequence charSequence2) {
        if (charSequence.length() <= i) {
            textView.setText(charSequence);
            return;
        }
        String substring = new StringBuilder(charSequence).substring(0, i2);
        textView.setText(substring + charSequence2);
    }

    public static int a(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil((double) fArr[i2]);
        }
        return i;
    }
}
