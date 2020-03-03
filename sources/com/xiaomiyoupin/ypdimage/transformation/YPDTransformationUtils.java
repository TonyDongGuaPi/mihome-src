package com.xiaomiyoupin.ypdimage.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class YPDTransformationUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1805a = 6;
    private static final Paint b = new Paint(6);
    private static final Set<String> c = new HashSet(Arrays.asList(new String[]{"XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"}));
    private static final Lock d = (c.contains(Build.MODEL) ? new ReentrantLock() : new NoLock());
    private static final int e = 7;
    private static final Paint f = new Paint(7);
    private static final Paint g = new Paint(7);

    static {
        g.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    private YPDTransformationUtils() {
    }

    private static Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap) {
        Bitmap.Config a2 = a(bitmap);
        if (a2.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap a3 = bitmapPool.a(bitmap.getWidth(), bitmap.getHeight(), a2);
        new Canvas(a3).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return a3;
    }

    @NonNull
    private static Bitmap.Config a(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT < 26 || !Bitmap.Config.RGBA_F16.equals(bitmap.getConfig())) {
            return Bitmap.Config.ARGB_8888;
        }
        return Bitmap.Config.RGBA_F16;
    }

    /* JADX INFO: finally extract failed */
    public static Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2, int i3, int i4) {
        Preconditions.a(Math.min(Math.min(i4, i3), Math.min(i, i2)) >= 0, "roundingRadius must be greater than 0.");
        float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        float f2 = (float) i;
        fArr[0] = f2;
        fArr[1] = f2;
        float f3 = (float) i2;
        fArr[2] = f3;
        fArr[3] = f3;
        float f4 = (float) i3;
        fArr[4] = f4;
        fArr[5] = f4;
        float f5 = (float) i4;
        fArr[6] = f5;
        fArr[7] = f5;
        Bitmap.Config a2 = a(bitmap);
        Bitmap a3 = a(bitmapPool, bitmap);
        Bitmap a4 = bitmapPool.a(a3.getWidth(), a3.getHeight(), a2);
        a4.setHasAlpha(true);
        BitmapShader bitmapShader = new BitmapShader(a3, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, (float) a4.getWidth(), (float) a4.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        d.lock();
        try {
            Canvas canvas = new Canvas(a4);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawPath(path, paint);
            a(canvas);
            d.unlock();
            if (!a3.equals(bitmap)) {
                bitmapPool.a(a3);
            }
            return a4;
        } catch (Throwable th) {
            d.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static Bitmap a(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        int min = Math.min(i, i2);
        float f2 = (float) min;
        float f3 = f2 / 2.0f;
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        float max = Math.max(f2 / width, f2 / height);
        float f4 = width * max;
        float f5 = max * height;
        float f6 = (f2 - f4) / 2.0f;
        float f7 = (f2 - f5) / 2.0f;
        RectF rectF = new RectF(f6, f7, f4 + f6, f5 + f7);
        Bitmap a2 = a(bitmapPool, bitmap);
        Bitmap a3 = bitmapPool.a(min, min, a(bitmap));
        a3.setHasAlpha(true);
        d.lock();
        try {
            Canvas canvas = new Canvas(a3);
            canvas.drawCircle(f3, f3, f3, f);
            canvas.drawBitmap(a2, (Rect) null, rectF, g);
            a(canvas);
            d.unlock();
            if (!a2.equals(bitmap)) {
                bitmapPool.a(a2);
            }
            return a3;
        } catch (Throwable th) {
            d.unlock();
            throw th;
        }
    }

    public static Bitmap b(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        Bitmap a2 = bitmapPool.a(i, i2, b(bitmap));
        a(bitmap, a2);
        a(bitmap, a2, matrix);
        return a2;
    }

    private static void a(Bitmap bitmap, Bitmap bitmap2) {
        bitmap2.setHasAlpha(bitmap.hasAlpha());
    }

    @NonNull
    private static Bitmap.Config b(@NonNull Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    private static void a(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, Matrix matrix) {
        d.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, b);
            a(canvas);
        } finally {
            d.unlock();
        }
    }

    private static void a(Canvas canvas) {
        canvas.setBitmap((Bitmap) null);
    }

    private static final class NoLock implements Lock {
        public void lock() {
        }

        public void lockInterruptibly() throws InterruptedException {
        }

        public boolean tryLock() {
            return true;
        }

        public boolean tryLock(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
            return true;
        }

        public void unlock() {
        }

        NoLock() {
        }

        @NonNull
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }
}
