package com.xiaomi.youpin.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public final class ImageUtils {
    private ImageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.a().getResources(), bitmap);
    }

    public static byte[] a(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return a(a(drawable), compressFormat);
    }

    public static Drawable b(byte[] bArr) {
        return a(a(bArr));
    }

    public static Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static Bitmap a(File file) {
        if (file == null) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap a(File file, int i, int i2) {
        if (file == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap a(String str) {
        if (h(str)) {
            return null;
        }
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap a(String str, int i, int i2) {
        if (h(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    public static Bitmap a(InputStream inputStream, int i, int i2) {
        if (inputStream == null) {
            return null;
        }
        return a(c(inputStream), 0, i, i2);
    }

    public static Bitmap a(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length);
    }

    public static Bitmap a(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length == 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
    }

    public static Bitmap a(@DrawableRes int i) {
        Drawable drawable = ContextCompat.getDrawable(Utils.a(), i);
        Canvas canvas = new Canvas();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap a(@DrawableRes int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Resources resources = Utils.a().getResources();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap a(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return null;
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    public static Bitmap a(FileDescriptor fileDescriptor, int i, int i2) {
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, (Rect) null, options);
    }

    public static Bitmap a(@NonNull Bitmap bitmap, @ColorInt int i) {
        return a(bitmap, i, false);
    }

    public static Bitmap a(@NonNull Bitmap bitmap, @ColorInt int i, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        new Canvas(bitmap).drawColor(i, PorterDuff.Mode.DARKEN);
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        return a(bitmap, i, i2, false);
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2) {
        return a(bitmap, f, f2, false);
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return a(bitmap, i, i2, i3, i4, false);
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap b(Bitmap bitmap, float f, float f2) {
        return a(bitmap, f, f2, 0.0f, 0.0f, false);
    }

    public static Bitmap b(Bitmap bitmap, float f, float f2, boolean z) {
        return a(bitmap, f, f2, 0.0f, 0.0f, z);
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return a(bitmap, f, f2, f3, f4, false);
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2, float f3, float f4, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f, f2, f3, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, float f, float f2) {
        return a(bitmap, i, f, f2, false);
    }

    public static Bitmap a(Bitmap bitmap, int i, float f, float f2, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i, f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static int b(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Bitmap b(Bitmap bitmap) {
        return b(bitmap, 0, 0, false);
    }

    public static Bitmap a(Bitmap bitmap, boolean z) {
        return b(bitmap, 0, 0, z);
    }

    public static Bitmap b(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2) {
        return b(bitmap, i, i2, false);
    }

    public static Bitmap b(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        int i3 = i;
        if (e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        float f = (float) min;
        float f2 = f / 2.0f;
        float f3 = (float) width;
        float f4 = (float) height;
        RectF rectF = new RectF(0.0f, 0.0f, f3, f4);
        rectF.inset(((float) (width - min)) / 2.0f, ((float) (height - min)) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        matrix.preScale(f / f3, f / f4);
        Bitmap bitmap2 = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (i3 > 0) {
            paint.setShader((Shader) null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            float f5 = (float) i3;
            paint.setStrokeWidth(f5);
            canvas.drawCircle(f3 / 2.0f, f4 / 2.0f, f2 - (f5 / 2.0f), paint);
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        return a(bitmap, f, 0, 0, false);
    }

    public static Bitmap a(Bitmap bitmap, float f, boolean z) {
        return a(bitmap, f, 0, 0, z);
    }

    public static Bitmap a(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2) {
        return a(bitmap, f, i, i2, false);
    }

    public static Bitmap a(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        float f2 = (float) i;
        float f3 = f2 / 2.0f;
        rectF.inset(f3, f3);
        canvas.drawRoundRect(rectF, f, f, paint);
        if (i > 0) {
            paint.setShader((Shader) null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f2);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawRoundRect(rectF, f, f, paint);
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f) {
        return a(bitmap, i, i2, false, f, false);
    }

    public static Bitmap a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f, boolean z) {
        return a(bitmap, i, i2, false, f, z);
    }

    public static Bitmap c(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2) {
        return a(bitmap, i, i2, true, 0.0f, false);
    }

    public static Bitmap c(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z) {
        return a(bitmap, i, i2, true, 0.0f, z);
    }

    private static Bitmap a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z, float f, boolean z2) {
        if (e(bitmap)) {
            return null;
        }
        if (!z2) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(1);
        paint.setColor(i2);
        paint.setStyle(Paint.Style.STROKE);
        float f2 = (float) i;
        paint.setStrokeWidth(f2);
        if (z) {
            canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, (((float) Math.min(width, height)) / 2.0f) - (f2 / 2.0f), paint);
        } else {
            int i3 = i >> 1;
            float f3 = (float) i3;
            canvas.drawRoundRect(new RectF(f3, f3, (float) (width - i3), (float) (height - i3)), f, f, paint);
        }
        return bitmap;
    }

    public static Bitmap b(Bitmap bitmap, int i) {
        return b(bitmap, i, false);
    }

    public static Bitmap b(Bitmap bitmap, int i, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height - i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float f = (float) (height + 0);
        canvas.drawBitmap(createBitmap, 0.0f, f, (Paint) null);
        Paint paint = new Paint(1);
        paint.setShader(new LinearGradient(0.0f, (float) height, 0.0f, (float) (createBitmap2.getHeight() + 0), 1895825407, 16777215, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, (float) width, (float) createBitmap2.getHeight(), paint);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap2;
    }

    public static Bitmap a(Bitmap bitmap, String str, int i, @ColorInt int i2, float f, float f2) {
        return a(bitmap, str, (float) i, i2, f, f2, false);
    }

    public static Bitmap a(Bitmap bitmap, String str, float f, @ColorInt int i, float f2, float f3, boolean z) {
        if (e(bitmap) || str == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(copy);
        paint.setColor(i);
        paint.setTextSize(f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, f2, f3 + f, paint);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        return a(bitmap, bitmap2, i, i2, i3, false);
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (!e(bitmap2)) {
            Paint paint = new Paint(1);
            Canvas canvas = new Canvas(copy);
            paint.setAlpha(i3);
            canvas.drawBitmap(bitmap2, (float) i, (float) i2, paint);
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return copy;
    }

    public static Bitmap c(Bitmap bitmap) {
        return a(bitmap, (Boolean) false);
    }

    public static Bitmap a(Bitmap bitmap, Boolean bool) {
        if (e(bitmap)) {
            return null;
        }
        Bitmap extractAlpha = bitmap.extractAlpha();
        if (bool.booleanValue() && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return extractAlpha;
    }

    public static Bitmap d(Bitmap bitmap) {
        return b(bitmap, false);
    }

    public static Bitmap b(Bitmap bitmap, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap c(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2) {
        return c(bitmap, f, f2, false);
    }

    public static Bitmap c(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f2, boolean z) {
        Bitmap bitmap2;
        if (e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Paint paint = new Paint(3);
        Canvas canvas = new Canvas();
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        canvas.scale(f, f);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        if (Build.VERSION.SDK_INT >= 17) {
            bitmap2 = b(createBitmap, f2, z);
        } else {
            bitmap2 = c(createBitmap, (int) f2, z);
        }
        if (f == 1.0f) {
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return bitmap2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, width, height, true);
        if (!bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    @RequiresApi(17)
    public static Bitmap b(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float f) {
        return b(bitmap, f, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    @android.support.annotation.RequiresApi(17)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap b(android.graphics.Bitmap r3, @android.support.annotation.FloatRange(from = 0.0d, fromInclusive = false, to = 25.0d) float r4, boolean r5) {
        /*
            r0 = 1
            if (r5 == 0) goto L_0x0004
            goto L_0x000c
        L_0x0004:
            android.graphics.Bitmap$Config r5 = r3.getConfig()
            android.graphics.Bitmap r3 = r3.copy(r5, r0)
        L_0x000c:
            r5 = 0
            android.app.Application r1 = com.xiaomi.youpin.common.util.Utils.a()     // Catch:{ all -> 0x0047 }
            android.renderscript.RenderScript r1 = android.renderscript.RenderScript.create(r1)     // Catch:{ all -> 0x0047 }
            android.renderscript.RenderScript$RSMessageHandler r5 = new android.renderscript.RenderScript$RSMessageHandler     // Catch:{ all -> 0x0045 }
            r5.<init>()     // Catch:{ all -> 0x0045 }
            r1.setMessageHandler(r5)     // Catch:{ all -> 0x0045 }
            android.renderscript.Allocation$MipmapControl r5 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x0045 }
            android.renderscript.Allocation r5 = android.renderscript.Allocation.createFromBitmap(r1, r3, r5, r0)     // Catch:{ all -> 0x0045 }
            android.renderscript.Type r0 = r5.getType()     // Catch:{ all -> 0x0045 }
            android.renderscript.Allocation r0 = android.renderscript.Allocation.createTyped(r1, r0)     // Catch:{ all -> 0x0045 }
            android.renderscript.Element r2 = android.renderscript.Element.U8_4(r1)     // Catch:{ all -> 0x0045 }
            android.renderscript.ScriptIntrinsicBlur r2 = android.renderscript.ScriptIntrinsicBlur.create(r1, r2)     // Catch:{ all -> 0x0045 }
            r2.setInput(r5)     // Catch:{ all -> 0x0045 }
            r2.setRadius(r4)     // Catch:{ all -> 0x0045 }
            r2.forEach(r0)     // Catch:{ all -> 0x0045 }
            r0.copyTo(r3)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0044
            r1.destroy()
        L_0x0044:
            return r3
        L_0x0045:
            r3 = move-exception
            goto L_0x0049
        L_0x0047:
            r3 = move-exception
            r1 = r5
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.destroy()
        L_0x004e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ImageUtils.b(android.graphics.Bitmap, float, boolean):android.graphics.Bitmap");
    }

    public static Bitmap c(Bitmap bitmap, int i) {
        return c(bitmap, i, false);
    }

    public static Bitmap c(Bitmap bitmap, int i, boolean z) {
        int i2;
        int[] iArr;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        int i3 = i;
        int i4 = i3 < 1 ? 1 : i3;
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i5 = width * height;
        int[] iArr2 = new int[i5];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i6 = width - 1;
        int i7 = height - 1;
        int i8 = i4 + i4 + 1;
        int[] iArr3 = new int[i5];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[i5];
        int[] iArr6 = new int[Math.max(width, height)];
        int i9 = (i8 + 1) >> 1;
        int i10 = i9 * i9;
        int i11 = i10 * 256;
        int[] iArr7 = new int[i11];
        for (int i12 = 0; i12 < i11; i12++) {
            iArr7[i12] = i12 / i10;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, new int[]{i8, 3});
        int i13 = i4 + 1;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < height) {
            Bitmap bitmap2 = copy;
            int i17 = -i4;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            while (i17 <= i4) {
                int i27 = i7;
                int i28 = height;
                int i29 = iArr2[i15 + Math.min(i6, Math.max(i17, 0))];
                int[] iArr9 = iArr8[i17 + i4];
                iArr9[0] = (i29 & 16711680) >> 16;
                iArr9[1] = (i29 & 65280) >> 8;
                iArr9[2] = i29 & 255;
                int abs = i13 - Math.abs(i17);
                i18 += iArr9[0] * abs;
                i19 += iArr9[1] * abs;
                i20 += iArr9[2] * abs;
                if (i17 > 0) {
                    i21 += iArr9[0];
                    i22 += iArr9[1];
                    i23 += iArr9[2];
                } else {
                    i24 += iArr9[0];
                    i25 += iArr9[1];
                    i26 += iArr9[2];
                }
                i17++;
                height = i28;
                i7 = i27;
            }
            int i30 = i7;
            int i31 = height;
            int i32 = i4;
            int i33 = 0;
            while (i33 < width) {
                iArr3[i15] = iArr7[i18];
                iArr4[i15] = iArr7[i19];
                iArr5[i15] = iArr7[i20];
                int i34 = i18 - i24;
                int i35 = i19 - i25;
                int i36 = i20 - i26;
                int[] iArr10 = iArr8[((i32 - i4) + i8) % i8];
                int i37 = i24 - iArr10[0];
                int i38 = i25 - iArr10[1];
                int i39 = i26 - iArr10[2];
                if (i14 == 0) {
                    iArr = iArr7;
                    iArr6[i33] = Math.min(i33 + i4 + 1, i6);
                } else {
                    iArr = iArr7;
                }
                int i40 = iArr2[i16 + iArr6[i33]];
                iArr10[0] = (i40 & 16711680) >> 16;
                iArr10[1] = (i40 & 65280) >> 8;
                iArr10[2] = i40 & 255;
                int i41 = i21 + iArr10[0];
                int i42 = i22 + iArr10[1];
                int i43 = i23 + iArr10[2];
                i18 = i34 + i41;
                i19 = i35 + i42;
                i20 = i36 + i43;
                i32 = (i32 + 1) % i8;
                int[] iArr11 = iArr8[i32 % i8];
                i24 = i37 + iArr11[0];
                i25 = i38 + iArr11[1];
                i26 = i39 + iArr11[2];
                i21 = i41 - iArr11[0];
                i22 = i42 - iArr11[1];
                i23 = i43 - iArr11[2];
                i15++;
                i33++;
                iArr7 = iArr;
            }
            int[] iArr12 = iArr7;
            i16 += width;
            i14++;
            copy = bitmap2;
            height = i31;
            i7 = i30;
        }
        int i44 = i7;
        Bitmap bitmap3 = copy;
        int i45 = height;
        int[] iArr13 = iArr7;
        int i46 = 0;
        while (i46 < width) {
            int i47 = -i4;
            int i48 = i47 * width;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            int i57 = 0;
            while (i47 <= i4) {
                int[] iArr14 = iArr6;
                int max = Math.max(0, i48) + i46;
                int[] iArr15 = iArr8[i47 + i4];
                iArr15[0] = iArr3[max];
                iArr15[1] = iArr4[max];
                iArr15[2] = iArr5[max];
                int abs2 = i13 - Math.abs(i47);
                i49 += iArr3[max] * abs2;
                i50 += iArr4[max] * abs2;
                i51 += iArr5[max] * abs2;
                if (i47 > 0) {
                    i52 += iArr15[0];
                    i53 += iArr15[1];
                    i54 += iArr15[2];
                } else {
                    i55 += iArr15[0];
                    i56 += iArr15[1];
                    i57 += iArr15[2];
                }
                int i58 = i44;
                if (i47 < i58) {
                    i48 += width;
                }
                i47++;
                i44 = i58;
                iArr6 = iArr14;
            }
            int[] iArr16 = iArr6;
            int i59 = i44;
            int i60 = i53;
            int i61 = i54;
            int i62 = i45;
            int i63 = 0;
            int i64 = i4;
            int i65 = i52;
            int i66 = i51;
            int i67 = i50;
            int i68 = i49;
            int i69 = i46;
            while (i63 < i62) {
                iArr2[i69] = (iArr2[i69] & -16777216) | (iArr13[i68] << 16) | (iArr13[i67] << 8) | iArr13[i66];
                int i70 = i68 - i55;
                int i71 = i67 - i56;
                int i72 = i66 - i57;
                int[] iArr17 = iArr8[((i64 - i4) + i8) % i8];
                int i73 = i55 - iArr17[0];
                int i74 = i56 - iArr17[1];
                int i75 = i57 - iArr17[2];
                if (i46 == 0) {
                    i2 = i4;
                    iArr16[i63] = Math.min(i63 + i13, i59) * width;
                } else {
                    i2 = i4;
                }
                int i76 = iArr16[i63] + i46;
                iArr17[0] = iArr3[i76];
                iArr17[1] = iArr4[i76];
                iArr17[2] = iArr5[i76];
                int i77 = i65 + iArr17[0];
                int i78 = i60 + iArr17[1];
                int i79 = i61 + iArr17[2];
                i68 = i70 + i77;
                i67 = i71 + i78;
                i66 = i72 + i79;
                i64 = (i64 + 1) % i8;
                int[] iArr18 = iArr8[i64];
                i55 = i73 + iArr18[0];
                i56 = i74 + iArr18[1];
                i57 = i75 + iArr18[2];
                i65 = i77 - iArr18[0];
                i60 = i78 - iArr18[1];
                i61 = i79 - iArr18[2];
                i69 += width;
                i63++;
                i4 = i2;
            }
            int i80 = i4;
            i46++;
            i44 = i59;
            i45 = i62;
            iArr6 = iArr16;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i45);
        return bitmap3;
    }

    public static boolean a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return a(bitmap, g(str), compressFormat, false);
    }

    public static boolean a(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat) {
        return a(bitmap, file, compressFormat, false);
    }

    public static boolean a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, boolean z) {
        return a(bitmap, g(str), compressFormat, z);
    }

    public static boolean a(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, boolean z) {
        return a(bitmap, file, compressFormat, 100, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0044 A[SYNTHETIC, Splitter:B:30:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004a A[SYNTHETIC, Splitter:B:34:0x004a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.graphics.Bitmap r4, java.io.File r5, android.graphics.Bitmap.CompressFormat r6, int r7, boolean r8) {
        /*
            boolean r0 = e((android.graphics.Bitmap) r4)
            r1 = 0
            if (r0 != 0) goto L_0x0053
            boolean r0 = d((java.io.File) r5)
            if (r0 != 0) goto L_0x000e
            goto L_0x0053
        L_0x000e:
            r0 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x003d }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003d }
            r3.<init>(r5)     // Catch:{ IOException -> 0x003d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003d }
            boolean r5 = r4.compress(r6, r7, r2)     // Catch:{ IOException -> 0x0038, all -> 0x0035 }
            if (r8 == 0) goto L_0x002c
            boolean r6 = r4.isRecycled()     // Catch:{ IOException -> 0x0029, all -> 0x0035 }
            if (r6 != 0) goto L_0x002c
            r4.recycle()     // Catch:{ IOException -> 0x0029, all -> 0x0035 }
            goto L_0x002c
        L_0x0029:
            r4 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x002c:
            r2.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0047
        L_0x0030:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0047
        L_0x0035:
            r4 = move-exception
            r0 = r2
            goto L_0x0048
        L_0x0038:
            r4 = move-exception
            r0 = r2
            goto L_0x003e
        L_0x003b:
            r4 = move-exception
            goto L_0x0048
        L_0x003d:
            r4 = move-exception
        L_0x003e:
            r5 = 0
        L_0x003f:
            r4.printStackTrace()     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0047:
            return r5
        L_0x0048:
            if (r0 == 0) goto L_0x0052
            r0.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0052:
            throw r4
        L_0x0053:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ImageUtils.a(android.graphics.Bitmap, java.io.File, android.graphics.Bitmap$CompressFormat, int, boolean):boolean");
    }

    public static boolean b(File file) {
        return file != null && c(file.getPath());
    }

    public static boolean c(String str) {
        String upperCase = str.toUpperCase();
        return upperCase.endsWith(".PNG") || upperCase.endsWith(".JPG") || upperCase.endsWith(".JPEG") || upperCase.endsWith(".BMP") || upperCase.endsWith(".GIF") || upperCase.endsWith(".WEBP");
    }

    public static String d(String str) {
        return c(g(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x002e A[SYNTHETIC, Splitter:B:24:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0045 A[SYNTHETIC, Splitter:B:31:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.io.File r3) {
        /*
            if (r3 != 0) goto L_0x0005
            java.lang.String r3 = ""
            return r3
        L_0x0005:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0028 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0028 }
            java.lang.String r0 = b((java.io.InputStream) r1)     // Catch:{ IOException -> 0x0021, all -> 0x001e }
            if (r0 == 0) goto L_0x001a
            r1.close()     // Catch:{ IOException -> 0x0015 }
            goto L_0x0019
        L_0x0015:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0019:
            return r0
        L_0x001a:
            r1.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x001e:
            r3 = move-exception
            r0 = r1
            goto L_0x0043
        L_0x0021:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x0029
        L_0x0026:
            r3 = move-exception
            goto L_0x0043
        L_0x0028:
            r1 = move-exception
        L_0x0029:
            r1.printStackTrace()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0036
            r0.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0036:
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.String r3 = f((java.lang.String) r3)
            java.lang.String r3 = r3.toUpperCase()
            return r3
        L_0x0043:
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ImageUtils.c(java.io.File):java.lang.String");
    }

    private static String f(String str) {
        if (h(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    private static String b(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[8];
            if (inputStream.read(bArr, 0, 8) != -1) {
                return c(bArr);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String c(byte[] bArr) {
        if (d(bArr)) {
            return "JPEG";
        }
        if (e(bArr)) {
            return "GIF";
        }
        if (f(bArr)) {
            return "PNG";
        }
        if (g(bArr)) {
            return "BMP";
        }
        return null;
    }

    private static boolean d(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40;
    }

    private static boolean e(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 56 && (bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97;
    }

    private static boolean f(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10;
    }

    private static boolean g(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77;
    }

    private static boolean e(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static Bitmap d(Bitmap bitmap, int i, int i2) {
        return a(bitmap, i, i2, false);
    }

    public static Bitmap d(Bitmap bitmap, int i, int i2, boolean z) {
        return a(bitmap, i, i2, z);
    }

    public static Bitmap d(Bitmap bitmap, float f, float f2) {
        return a(bitmap, f, f2, false);
    }

    public static Bitmap d(Bitmap bitmap, float f, float f2, boolean z) {
        return a(bitmap, f, f2, z);
    }

    public static Bitmap d(Bitmap bitmap, @IntRange(from = 0, to = 100) int i) {
        return d(bitmap, i, false);
    }

    public static Bitmap d(Bitmap bitmap, @IntRange(from = 0, to = 100) int i, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static Bitmap a(Bitmap bitmap, long j) {
        return a(bitmap, j, false);
    }

    public static Bitmap a(Bitmap bitmap, long j, boolean z) {
        byte[] bArr;
        if (e(bitmap) || j <= 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (((long) byteArrayOutputStream.size()) <= j) {
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (((long) byteArrayOutputStream.size()) >= j) {
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                int i2 = 0;
                int i3 = 0;
                while (i2 < i) {
                    i3 = (i2 + i) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                    long size = (long) byteArrayOutputStream.size();
                    if (size == j) {
                        break;
                    } else if (size > j) {
                        i = i3 - 1;
                    } else {
                        i2 = i3 + 1;
                    }
                }
                if (i == i3 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap e(Bitmap bitmap, int i) {
        return e(bitmap, i, false);
    }

    public static Bitmap e(Bitmap bitmap, int i, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap e(Bitmap bitmap, int i, int i2) {
        return e(bitmap, i, i2, false);
    }

    public static Bitmap e(Bitmap bitmap, int i, int i2, boolean z) {
        if (e(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    private static File g(String str) {
        if (h(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean d(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !e(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean e(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    private static boolean h(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        while (true) {
            i4 >>= 1;
            if (i4 < i || (i3 = i3 >> 1) < i2) {
                return i5;
            }
            i5 <<= 1;
        }
        return i5;
    }

    private static byte[] c(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArray;
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return null;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static Bitmap f(Bitmap bitmap, int i) {
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
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return createBitmap == null ? bitmap : createBitmap;
    }

    public static Bitmap e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("data:")) {
            str = str.substring(str.indexOf("base64,") + 7);
        }
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
