package com.lidroid.xutils.bitmap;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import java.lang.reflect.Field;

public class BitmapCommonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static BitmapSize f6290a;

    private BitmapCommonUtils() {
    }

    public static BitmapSize a(Context context) {
        if (f6290a == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            f6290a = new BitmapSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        return f6290a;
    }

    public static BitmapSize a(View view, int i, int i2) {
        if (i > 0 && i2 > 0) {
            return new BitmapSize(i, i2);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width > 0) {
                i = layoutParams.width;
            } else if (layoutParams.width != -2) {
                i = view.getWidth();
            }
            if (layoutParams.height > 0) {
                i2 = layoutParams.height;
            } else if (layoutParams.height != -2) {
                i2 = view.getHeight();
            }
        }
        if (i <= 0) {
            i = a(view, "mMaxWidth");
        }
        if (i2 <= 0) {
            i2 = a(view, "mMaxHeight");
        }
        BitmapSize a2 = a(view.getContext());
        if (i <= 0) {
            i = a2.a();
        }
        if (i2 <= 0) {
            i2 = a2.b();
        }
        return new BitmapSize(i, i2);
    }

    private static int a(Object obj, String str) {
        if (obj instanceof ImageView) {
            try {
                Field declaredField = ImageView.class.getDeclaredField(str);
                declaredField.setAccessible(true);
                int intValue = ((Integer) declaredField.get(obj)).intValue();
                if (intValue <= 0 || intValue >= Integer.MAX_VALUE) {
                    return 0;
                }
                return intValue;
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}
