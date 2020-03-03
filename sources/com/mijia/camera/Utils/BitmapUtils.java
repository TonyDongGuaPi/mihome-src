package com.mijia.camera.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.smarthome_camera.R;

public class BitmapUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7903a = -1;
    private static final String b = "BitmapUtils";
    private static final int c = 90;

    private BitmapUtils() {
    }

    public static int a(int i) {
        if (i <= 0 || i > 1073741824) {
            throw new IllegalArgumentException();
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 16);
        int i4 = i3 | (i3 >> 8);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 2);
        return (i6 | (i6 >> 1)) + 1;
    }

    public static int b(int i) {
        if (i > 0) {
            return Integer.highestOneBit(i);
        }
        throw new IllegalArgumentException();
    }

    public static int a(int i, int i2, int i3, int i4) {
        int b2 = b(i, i2, i3, i4);
        return b2 <= 8 ? a(b2) : ((b2 + 7) / 8) * 8;
    }

    private static int b(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i4 == -1 && i3 == -1) {
            return 1;
        }
        if (i4 != -1) {
            i5 = (int) Math.ceil(Math.sqrt((double) (((float) (i * i2)) / ((float) i4))));
        }
        if (i3 == -1) {
            return i5;
        }
        return Math.max(Math.min(i / i3, i2 / i3), i5);
    }

    public static Bitmap a(Bitmap bitmap, Context context) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.camera_mijia_logo);
        if (bitmap.getWidth() <= 640) {
            Matrix matrix = new Matrix();
            matrix.setScale(0.33333334f, 0.33333334f);
            Bitmap createBitmap2 = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
            canvas.drawBitmap(createBitmap2, 0.0f, (float) (height - createBitmap2.getHeight()), (Paint) null);
            canvas.save();
            canvas.restore();
            decodeResource.recycle();
            createBitmap2.recycle();
        } else {
            canvas.drawBitmap(decodeResource, 0.0f, (float) (height - decodeResource.getHeight()), (Paint) null);
            canvas.save();
            canvas.restore();
            decodeResource.recycle();
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        if (i == 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, double d, double d2) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) d) / width, ((float) d2) / height);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }
}
