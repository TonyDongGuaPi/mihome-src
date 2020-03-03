package net.qiujuer.genius.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.facebook.react.modules.appstate.AppStateModule;

public class Ui {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f3118a = (Build.VERSION.SDK_INT >= 21);
    public static final int b = 805306368;
    public static final int c = 520093696;
    public static final int d = 1023410176;
    public static final float e = 0.0f;
    public static final float f = 1.8f;
    public static final float g = 3.75f;
    public static final int h = 4;
    private static final String i = "http://schemas.android.com/apk/res/android";

    public static int a(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    public static int b(int i2, int i3) {
        return (i2 & 255) | ((((i2 >>> 24) * (i3 + (i3 >> 7))) >> 8) << 24) | (((i2 >> 16) & 255) << 16) | (((i2 >> 8) & 255) << 8);
    }

    public static int c(int i2, int i3) {
        return (i2 & 255) | (i3 << 24) | (((i2 >> 16) & 255) << 16) | (((i2 >> 8) & 255) << 8);
    }

    public static Typeface a(Context context, String str) {
        String str2 = "fonts/" + str;
        try {
            return Typeface.createFromAsset(context.getAssets(), str2);
        } catch (Exception unused) {
            Log.e("Genius Ui", "Font file at " + str2 + " cannot be found or the file is not a valid font file. Please be sure that library assets are included to project. If not, copy assets/fonts folder of the library to your projects assets folder.");
            return null;
        }
    }

    public static float a(Resources resources, float f2) {
        return TypedValue.applyDimension(1, f2, resources.getDisplayMetrics());
    }

    public static float b(Resources resources, float f2) {
        return TypedValue.applyDimension(2, f2, resources.getDisplayMetrics());
    }

    public static boolean a(AttributeSet attributeSet, String str, boolean z) {
        return attributeSet.getAttributeBooleanValue(i, str, z);
    }

    public static boolean a(Context context, AttributeSet attributeSet, int i2, int i3, int i4, boolean z) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{i2}, i3, i4);
        if (obtainStyledAttributes.length() > 0) {
            z = obtainStyledAttributes.getBoolean(0, z);
        }
        obtainStyledAttributes.recycle();
        return z;
    }

    public static int a(Context context, AttributeSet attributeSet) {
        int i2 = 0;
        if (a(attributeSet, AppStateModule.APP_STATE_BACKGROUND)) {
            try {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet.getStyleAttribute(), new int[]{16842964});
                if (obtainStyledAttributes.length() > 0) {
                    i2 = obtainStyledAttributes.getColor(0, 0);
                }
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return i2;
    }

    public static int[] a(Resources resources, int i2) {
        try {
            TypedArray obtainTypedArray = resources.obtainTypedArray(i2);
            if (obtainTypedArray.length() <= 0) {
                return null;
            }
            int length = obtainTypedArray.length();
            int[] iArr = new int[length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = obtainTypedArray.getColor(i3, 0);
            }
            return iArr;
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    public static boolean a(AttributeSet attributeSet, String str) {
        return attributeSet.getAttributeValue(i, str) != null;
    }
}
