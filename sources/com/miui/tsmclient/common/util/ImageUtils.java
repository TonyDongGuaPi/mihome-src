package com.miui.tsmclient.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import com.miui.tsmclient.util.IOUtils;
import com.miui.tsmclient.util.LogUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtils {
    private static final int DEFAULT_QUALITY = 80;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String TAG = "ImageUtils";

    private ImageUtils() {
    }

    public static int parseImageDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (Exception e) {
            Log.e("ImageUtils", "parseImageDegree: " + e);
            return 0;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int i) {
        Bitmap bitmap2;
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            LogUtils.e("rotate failed", e);
            bitmap2 = null;
        }
        if (bitmap2 == null) {
            bitmap2 = bitmap;
        }
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public static byte[] rotate(byte[] bArr, int i, int i2, int i3) {
        if (i3 == 0) {
            return bArr;
        }
        if (i3 == 90) {
            return rotateYUV420Degree90(bArr, i, i2);
        }
        if (i3 == 180) {
            return rotateYUV420Degree180(bArr, i, i2);
        }
        if (i3 == 270) {
            return rotateYUV420Degree270(bArr, i, i2);
        }
        throw new IllegalArgumentException("invalid rotate angle");
    }

    public static byte[] rotateYUV420Degree90(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                bArr2[i5] = bArr[(i7 * i) + i6];
                i5++;
            }
        }
        int i8 = i4 - 1;
        int i9 = i - 1;
        while (i9 > 0) {
            int i10 = i8;
            for (int i11 = 0; i11 < i2 / 2; i11++) {
                int i12 = (i11 * i) + i3;
                bArr2[i10] = bArr[i12 + i9];
                int i13 = i10 - 1;
                bArr2[i13] = bArr[i12 + (i9 - 1)];
                i10 = i13 - 1;
            }
            i9 -= 2;
            i8 = i10;
        }
        return bArr2;
    }

    public static byte[] rotateYUV420Degree180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            bArr2[i5] = bArr[i6];
            i5++;
        }
        for (int i7 = i4 - 1; i7 >= i3; i7 -= 2) {
            int i8 = i5 + 1;
            bArr2[i5] = bArr[i7 - 1];
            i5 = i8 + 1;
            bArr2[i8] = bArr[i7];
        }
        return bArr2;
    }

    public static byte[] rotateYUV420Degree270(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[((i3 * 3) / 2)];
        int i4 = i - 1;
        int i5 = i4;
        int i6 = 0;
        while (i5 >= 0) {
            int i7 = i6;
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                bArr2[i7] = bArr[i8 + i5];
                i7++;
                i8 += i;
            }
            i5--;
            i6 = i7;
        }
        int i10 = i3;
        while (i4 > 0) {
            int i11 = i3;
            int i12 = i10;
            for (int i13 = 0; i13 < i2 / 2; i13++) {
                bArr2[i12] = bArr[(i4 - 1) + i11];
                int i14 = i12 + 1;
                bArr2[i14] = bArr[i11 + i4];
                i12 = i14 + 1;
                i11 += i;
            }
            i4 -= 2;
            i10 = i12;
        }
        return bArr2;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static byte[] bmpToByteArray(Bitmap bitmap) {
        return bmpToByteArray(bitmap, 80);
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, int i) {
        if (bitmap == null || bitmap.isRecycled()) {
            return EMPTY_BYTE_ARRAY;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            Log.e("ImageUtils", "bmp2byteArr failed", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap clipToRoundRectBitmap(Bitmap bitmap, int i, int i2, int i3) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        Paint paint = new Paint(1);
        paint.setColor(-65536);
        float f = (float) i3;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        bitmapDrawable.setBounds(0, 0, i, i2);
        canvas.saveLayer(rectF, paint, 31);
        bitmapDrawable.draw(canvas);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap compressBitmap(Context context, Uri uri, int i, int i2) throws IOException {
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
        openInputStream.close();
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = calculateInSampleSize(options, i, i2);
        InputStream openInputStream2 = context.getContentResolver().openInputStream(uri);
        Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, (Rect) null, options2);
        openInputStream2.close();
        return decodeStream;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        r1 = r7;
        r4 = r0;
        r0 = r6;
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0083, code lost:
        android.util.Log.e("ImageUtils", "saveJPEG error, remove the output file.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0092, code lost:
        android.util.Log.e("ImageUtils", "delete file failed");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:5:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083 A[Catch:{ Exception -> 0x009a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean save(android.graphics.Bitmap r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r2.<init>(r7)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x004b }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004b }
            r3.<init>(r2)     // Catch:{ Exception -> 0x004b }
            r7.<init>(r3)     // Catch:{ Exception -> 0x004b }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            r3 = 90
            boolean r6 = r6.compress(r1, r3, r7)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            r7.flush()     // Catch:{ Exception -> 0x0048, all -> 0x003f }
            com.miui.tsmclient.util.IOUtils.closeQuietly((java.io.OutputStream) r7)     // Catch:{ Exception -> 0x0036 }
            if (r6 != 0) goto L_0x007c
            java.lang.String r7 = "ImageUtils"
            java.lang.String r0 = "saveJPEG error, remove the output file."
            android.util.Log.e(r7, r0)     // Catch:{ Exception -> 0x0036 }
            boolean r7 = r2.delete()     // Catch:{ Exception -> 0x0036 }
            if (r7 != 0) goto L_0x007c
            java.lang.String r7 = "ImageUtils"
            java.lang.String r0 = "delete file failed"
            android.util.Log.e(r7, r0)     // Catch:{ Exception -> 0x0036 }
            goto L_0x007c
        L_0x0036:
            r7 = move-exception
            java.lang.String r0 = "ImageUtils"
            java.lang.String r1 = "close BufferedOutputStream exception"
            android.util.Log.e(r0, r1, r7)
            goto L_0x007c
        L_0x003f:
            r0 = move-exception
            r1 = r7
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x007e
        L_0x0045:
            r6 = move-exception
            r1 = r7
            goto L_0x007e
        L_0x0048:
            r6 = move-exception
            r1 = r7
            goto L_0x0052
        L_0x004b:
            r6 = move-exception
            goto L_0x0052
        L_0x004d:
            r6 = move-exception
            r2 = r1
            goto L_0x007e
        L_0x0050:
            r6 = move-exception
            r2 = r1
        L_0x0052:
            java.lang.String r7 = "ImageUtils"
            java.lang.String r3 = "save bitmap exception"
            android.util.Log.e(r7, r3, r6)     // Catch:{ all -> 0x007d }
            com.miui.tsmclient.util.IOUtils.closeQuietly((java.io.OutputStream) r1)     // Catch:{ Exception -> 0x0073 }
            java.lang.String r6 = "ImageUtils"
            java.lang.String r7 = "saveJPEG error, remove the output file."
            android.util.Log.e(r6, r7)     // Catch:{ Exception -> 0x0073 }
            if (r2 == 0) goto L_0x007b
            boolean r6 = r2.delete()     // Catch:{ Exception -> 0x0073 }
            if (r6 != 0) goto L_0x007b
            java.lang.String r6 = "ImageUtils"
            java.lang.String r7 = "delete file failed"
            android.util.Log.e(r6, r7)     // Catch:{ Exception -> 0x0073 }
            goto L_0x007b
        L_0x0073:
            r6 = move-exception
            java.lang.String r7 = "ImageUtils"
            java.lang.String r1 = "close BufferedOutputStream exception"
            android.util.Log.e(r7, r1, r6)
        L_0x007b:
            r6 = 0
        L_0x007c:
            return r6
        L_0x007d:
            r6 = move-exception
        L_0x007e:
            com.miui.tsmclient.util.IOUtils.closeQuietly((java.io.OutputStream) r1)     // Catch:{ Exception -> 0x009a }
            if (r0 != 0) goto L_0x00a2
            java.lang.String r7 = "ImageUtils"
            java.lang.String r0 = "saveJPEG error, remove the output file."
            android.util.Log.e(r7, r0)     // Catch:{ Exception -> 0x009a }
            if (r2 == 0) goto L_0x00a2
            boolean r7 = r2.delete()     // Catch:{ Exception -> 0x009a }
            if (r7 != 0) goto L_0x00a2
            java.lang.String r7 = "ImageUtils"
            java.lang.String r0 = "delete file failed"
            android.util.Log.e(r7, r0)     // Catch:{ Exception -> 0x009a }
            goto L_0x00a2
        L_0x009a:
            r7 = move-exception
            java.lang.String r0 = "ImageUtils"
            java.lang.String r1 = "close BufferedOutputStream exception"
            android.util.Log.e(r0, r1, r7)
        L_0x00a2:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.common.util.ImageUtils.save(android.graphics.Bitmap, java.lang.String):boolean");
    }

    public static Bitmap fastBlur(Context context, Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        create2.setRadius(25.0f);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        create.destroy();
        return createBitmap;
    }

    public static Bitmap getScreenShot(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static boolean saveN21(byte[] bArr, int i, int i2, String str) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, (int[]) null);
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str)));
            try {
                boolean compressToJpeg = yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 90, bufferedOutputStream2);
                IOUtils.closeQuietly((OutputStream) bufferedOutputStream2);
                return compressToJpeg;
            } catch (FileNotFoundException e) {
                e = e;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    LogUtils.e("saveN21 error occurred", e);
                    IOUtils.closeQuietly((OutputStream) bufferedOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly((OutputStream) bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                IOUtils.closeQuietly((OutputStream) bufferedOutputStream);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            LogUtils.e("saveN21 error occurred", e);
            IOUtils.closeQuietly((OutputStream) bufferedOutputStream);
            return false;
        }
    }
}
