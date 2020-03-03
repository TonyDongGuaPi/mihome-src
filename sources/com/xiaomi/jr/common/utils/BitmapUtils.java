package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10362a = 70;
    private static final String b = "BitmapUtils";

    public static class CropConfig {

        /* renamed from: a  reason: collision with root package name */
        public float f10363a;
        public int b;
        public int c;
        public int d;
        public int e;
    }

    public static CropConfig a(Bitmap bitmap, int i, int i2) {
        CropConfig cropConfig = new CropConfig();
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        float f = (float) i;
        float f2 = width / f;
        float f3 = (float) i2;
        float f4 = height / f3;
        if (f2 > f4) {
            f2 = f4;
        }
        float f5 = f * f2;
        float f6 = f3 * f2;
        cropConfig.f10363a = 1.0f / f2;
        cropConfig.b = (int) ((width - f5) / 2.0f);
        cropConfig.c = (int) ((height - f6) / 2.0f);
        cropConfig.d = (int) f5;
        cropConfig.e = (int) f6;
        return cropConfig;
    }

    public static boolean a(Bitmap bitmap, String str) {
        if (!FileUtils.b(str)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(str);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream2);
                fileOutputStream2.flush();
                Utils.a((Closeable) fileOutputStream2);
                return true;
            } catch (IOException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    Utils.a((Closeable) fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    Utils.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                Utils.a((Closeable) fileOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            Utils.a((Closeable) fileOutputStream);
            return false;
        }
    }

    public static byte[] a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String b(Bitmap bitmap) {
        byte[] a2 = a(bitmap);
        if (a2 != null) {
            return Base64.encodeToString(a2, 2);
        }
        return null;
    }

    public static Bitmap a(String str) {
        byte[] decode = Base64.decode(str, 2);
        if (decode != null) {
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
        return null;
    }

    public static Bitmap a(Context context, String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return a(context, Uri.fromFile(new File(str)), i, i2);
    }

    public static Bitmap a(Context context, Uri uri, int i, int i2) {
        InputStream inputStream;
        Bitmap bitmap = null;
        if (context == null || i < 0 || i2 < 0) {
            return null;
        }
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
                openInputStream.close();
                if (options.outWidth == -1 || options.outHeight == -1) {
                    inputStream = null;
                    Utils.a((Closeable) inputStream);
                    return bitmap;
                }
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = a(options, i, i2);
                inputStream = context.getContentResolver().openInputStream(uri);
                try {
                    bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, options2);
                } catch (IOException e) {
                    e = e;
                    MifiLog.e(b, "compressBitmapWithoutCrop throw exception", e);
                    Utils.a((Closeable) inputStream);
                    return bitmap;
                }
                Utils.a((Closeable) inputStream);
                return bitmap;
            } catch (IOException e2) {
                e = e2;
                inputStream = openInputStream;
                MifiLog.e(b, "compressBitmapWithoutCrop throw exception", e);
                Utils.a((Closeable) inputStream);
                return bitmap;
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            MifiLog.e(b, "compressBitmapWithoutCrop throw exception", e);
            Utils.a((Closeable) inputStream);
            return bitmap;
        }
    }

    public static Bitmap a(Context context, byte[] bArr, int i, int i2) {
        if (context == null || bArr == null || i < 0 || i2 < 0) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (options.outWidth == -1 || options.outHeight == -1) {
                return null;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = a(options, i, i2);
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if ((i > 0 || i2 > 0) && (i3 > i2 || i4 > i)) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
