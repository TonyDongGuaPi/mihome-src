package com.xiaomi.jr.feature.photo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.xiaomi.jr.common.utils.BitmapUtils;
import com.xiaomi.jr.common.utils.MifiLog;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10419a = "photo_feature_utils";

    public static boolean a(int i, int i2, String str) {
        Bitmap b = b(i, i2, str);
        return b != null && BitmapUtils.a(b, str);
    }

    public static Bitmap b(int i, int i2, String str) {
        Bitmap decodeFile;
        if (i < 0 || i2 < 0 || (decodeFile = BitmapFactory.decodeFile(str)) == null) {
            return null;
        }
        Bitmap a2 = a(i, i2, decodeFile);
        if (a2 != decodeFile) {
            decodeFile.recycle();
        }
        return a2;
    }

    public static Bitmap a(int i, int i2, Bitmap bitmap) {
        if (i < 0 || i2 < 0) {
            return null;
        }
        if (bitmap.getWidth() <= i || bitmap.getHeight() <= i2) {
            return bitmap;
        }
        if (i > 0 && i2 > 0) {
            try {
                BitmapUtils.CropConfig a2 = BitmapUtils.a(bitmap, i, i2);
                Matrix matrix = new Matrix();
                matrix.postScale(a2.f10363a, a2.f10363a);
                bitmap = Bitmap.createBitmap(bitmap, a2.b, a2.c, a2.d, a2.e, matrix, true);
            } catch (Exception e) {
                MifiLog.d(f10419a, "exception occurs on picture scaling, " + e.getMessage());
                return null;
            }
        } else if (i > 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i, (int) (((((float) i) * 1.0f) / ((float) bitmap.getWidth())) * ((float) bitmap.getHeight())), true);
        } else if (i2 > 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (((((float) i2) * 1.0f) / ((float) bitmap.getHeight())) * ((float) bitmap.getWidth())), i2, true);
        }
        return bitmap;
    }
}
