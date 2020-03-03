package com.xiaomi.smarthome.library.common.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;

public class ImageUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final float f18685a = 1.0E-7f;
    private static final double b = 1.0E-7d;

    private ImageUtils() {
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        int i2 = i % 360;
        if (i2 == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i2, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap == createBitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError unused) {
            return bitmap;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.graphics.Matrix r13, android.graphics.Bitmap r14, int r15, int r16, boolean r17) {
        /*
            r0 = r13
            r7 = r14
            r8 = r15
            r9 = r16
            int r1 = r14.getWidth()
            int r1 = r1 - r8
            int r2 = r14.getHeight()
            int r2 = r2 - r9
            r3 = 0
            r10 = 0
            if (r17 != 0) goto L_0x0060
            if (r1 < 0) goto L_0x0017
            if (r2 >= 0) goto L_0x0060
        L_0x0017:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r15, r9, r0)
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r0)
            int r1 = r1 / 2
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r2 / 2
            int r2 = java.lang.Math.max(r10, r2)
            android.graphics.Rect r5 = new android.graphics.Rect
            int r6 = r14.getWidth()
            int r6 = java.lang.Math.min(r15, r6)
            int r6 = r6 + r1
            int r10 = r14.getHeight()
            int r10 = java.lang.Math.min(r9, r10)
            int r10 = r10 + r2
            r5.<init>(r1, r2, r6, r10)
            int r1 = r5.width()
            int r1 = r8 - r1
            int r1 = r1 / 2
            int r2 = r5.height()
            int r2 = r9 - r2
            int r2 = r2 / 2
            android.graphics.Rect r6 = new android.graphics.Rect
            int r8 = r8 - r1
            int r9 = r9 - r2
            r6.<init>(r1, r2, r8, r9)
            r4.drawBitmap(r14, r5, r6, r3)
            return r0
        L_0x0060:
            int r1 = r14.getWidth()
            float r1 = (float) r1
            int r2 = r14.getHeight()
            float r2 = (float) r2
            float r4 = r1 / r2
            float r5 = (float) r8
            float r6 = (float) r9
            float r11 = r5 / r6
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            r11 = 1065353216(0x3f800000, float:1.0)
            r12 = 1063675494(0x3f666666, float:0.9)
            if (r4 <= 0) goto L_0x0089
            float r6 = r6 / r2
            int r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r1 < 0) goto L_0x0085
            int r1 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r1 <= 0) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r0 = r3
            goto L_0x0098
        L_0x0085:
            r13.setScale(r6, r6)
            goto L_0x0098
        L_0x0089:
            float r5 = r5 / r1
            int r1 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r1 < 0) goto L_0x0095
            int r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r1 <= 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r5 = r3
            goto L_0x0099
        L_0x0095:
            r13.setScale(r5, r5)
        L_0x0098:
            r5 = r0
        L_0x0099:
            if (r5 == 0) goto L_0x00ac
            r1 = 0
            r2 = 0
            int r3 = r14.getWidth()
            int r4 = r14.getHeight()
            r6 = 1
            r0 = r14
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x00ad
        L_0x00ac:
            r0 = r7
        L_0x00ad:
            int r1 = r0.getWidth()
            int r1 = r1 - r8
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r0.getHeight()
            int r2 = r2 - r9
            int r2 = java.lang.Math.max(r10, r2)
            int r1 = r1 / 2
            int r2 = r2 / 2
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r15, r9)
            if (r0 == r7) goto L_0x00cc
            r0.recycle()
        L_0x00cc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.ImageUtils.a(android.graphics.Matrix, android.graphics.Bitmap, int, int, boolean):android.graphics.Bitmap");
    }

    public static <T> int a(T[] tArr, T t) {
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean a(float f, float f2) {
        return Math.abs(f - f2) <= f18685a;
    }

    public static boolean a(double d, double d2) {
        return Math.abs(d - d2) <= b;
    }

    public static void a(Bitmap bitmap, Bitmap bitmap2) {
        try {
            Canvas canvas = new Canvas(bitmap2);
            Paint paint = new Paint();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width >= height) {
                width = height;
            }
            Rect rect = new Rect(0, 0, width, width);
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, (float) (width / 2), (float) (width / 2), paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (OutOfMemoryError unused) {
        }
    }

    public static void a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static Bitmap a(Bitmap bitmap, String str) {
        int a2 = a(str);
        if (a2 <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) a2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap == null) {
            return bitmap;
        }
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static int a(String str) {
        ExifInterface exifInterface;
        int attributeInt;
        try {
            exifInterface = new ExifInterface(str);
        } catch (Exception unused) {
            exifInterface = null;
        }
        if (!(exifInterface == null || (attributeInt = exifInterface.getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, -1)) == -1)) {
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        }
    }

    public static Bitmap b(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (width > height) {
            float f = ((float) i) / ((float) width);
            matrix.postScale(f, f);
        } else {
            float f2 = ((float) i) / ((float) height);
            matrix.postScale(f2, f2);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
