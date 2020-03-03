package com.mi.global.shop.util.fresco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, PorterDuff.Mode mode) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(bitmap2, (Rect) null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:15|14|28|29|30) */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0028 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r1, java.lang.String r2) {
        /*
            r0 = 0
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x001a, all -> 0x0018 }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x001a, all -> 0x0018 }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ IOException -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0011 }
            return r2
        L_0x0011:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0015 }
        L_0x0015:
            return r2
        L_0x0016:
            r2 = move-exception
            goto L_0x001c
        L_0x0018:
            r1 = r0
            goto L_0x0028
        L_0x001a:
            r2 = move-exception
            r1 = r0
        L_0x001c:
            r2.printStackTrace()     // Catch:{ all -> 0x0028 }
            r1.close()     // Catch:{ IOException -> 0x0023 }
            return r0
        L_0x0023:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0027 }
        L_0x0027:
            return r0
        L_0x0028:
            r1.close()     // Catch:{ IOException -> 0x002c }
            return r0
        L_0x002c:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0030 }
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.fresco.BitmapUtils.a(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static byte[] a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String b(Bitmap bitmap) {
        return Base64.encodeToString(a(bitmap), 0);
    }

    public static Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable c(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        return a(bitmap, ((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap a(Bitmap bitmap, boolean z, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i2) / ((float) width), ((float) i) / ((float) height));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static void a(Bitmap bitmap, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Bitmap bitmap, int i, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap a(Bitmap bitmap, boolean z, long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (((long) byteArrayOutputStream.toByteArray().length) > j) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
        if (z) {
            bitmap.recycle();
        }
        return decodeStream;
    }

    public static Bitmap b(Bitmap bitmap, boolean z, int i, int i2) {
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(String str, boolean z, long j, int i, int i2) {
        return a(str, (String) null, false, z, j, i, i2);
    }

    private static Bitmap a(String str, String str2, boolean z, boolean z2, long j, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        options.inJustDecodeBounds = false;
        int i5 = 1;
        while (i3 / i5 > i) {
            i5++;
        }
        while (i4 / i5 > i2) {
            i5++;
        }
        if (i5 <= 0) {
            i5 = 1;
        }
        options.inSampleSize = i5;
        Bitmap b = b(BitmapFactory.decodeFile(str, options), false, i, i2);
        if (z2) {
            b = a(b, true, j);
        }
        if (z) {
            if (!TextUtils.isEmpty(str2)) {
                str = str2;
            }
            a(b, new File(str));
        }
        return b;
    }

    public static void a(String str, String str2, boolean z, long j, int i, int i2) {
        a(str, str2, true, z, j, i, i2);
    }

    public static void b(String str, boolean z, long j, int i, int i2) {
        a(str, (String) null, z, j, i, i2);
    }

    public static void a(String str, int i, int i2) {
        a(str, (String) null, false, 0, i, i2);
    }

    public static Bitmap b(String str, int i, int i2) {
        return a(str, false, 0, i, i2);
    }

    public static void a(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        a(str, options.outWidth / i, options.outHeight / i);
    }

    public static Bitmap b(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return b(str, options.outWidth / i, options.outHeight / i);
    }

    public static void c(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        a(str, options.outWidth * i, options.outHeight * i);
    }

    public static Bitmap d(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return b(str, options.outWidth * i, options.outHeight * i);
    }

    public static void a(String str, String str2, boolean z, long j) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        a(str, str2, z, j, options.outWidth / 2, options.outHeight / 2);
    }

    public static void a(String str, boolean z, long j) {
        a(str, (String) null, z, j);
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static final int a(String str) {
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
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
