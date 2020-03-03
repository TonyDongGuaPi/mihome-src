package com.xiaomi.channel.sdk;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import java.io.File;

public class ShareUtils {
    public static Bitmap a(Drawable drawable) {
        Bitmap.Config config;
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (drawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, Bitmap.Config config) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < i && height < i) {
            return bitmap;
        }
        double d = (double) width;
        double d2 = (double) i;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        double d4 = (double) height;
        double d5 = (double) i2;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        if (d3 > d6) {
            d6 = d3;
        }
        Double.isNaN(d);
        Double.isNaN(d4);
        return b(bitmap, (int) (d / d6), (int) (d4 / d6), config);
    }

    public static Bitmap b(Bitmap bitmap, int i, int i2, Bitmap.Config config) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < i && height < i) {
            return bitmap;
        }
        Rect rect = new Rect(0, 0, width, height);
        Rect rect2 = new Rect(0, 0, i, i2);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        new Canvas(createBitmap).drawBitmap(bitmap, rect, rect2, new Paint());
        return createBitmap;
    }

    public static boolean a(Bitmap bitmap, String str) {
        return a(bitmap, str, Bitmap.CompressFormat.PNG, 100);
    }

    public static void a(String str) {
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static File a() {
        try {
            File file = new File(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "miliaoshare"), "images");
            if (file.isDirectory() || file.mkdirs()) {
                return new File(file, ShareConstants.c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0027 A[SYNTHETIC, Splitter:B:18:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002d A[SYNTHETIC, Splitter:B:24:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.graphics.Bitmap r2, java.lang.String r3, android.graphics.Bitmap.CompressFormat r4, int r5) {
        /*
            r0 = 0
            a((java.lang.String) r3)     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            boolean r3 = r1.exists()     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            if (r3 != 0) goto L_0x0012
            r1.createNewFile()     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
        L_0x0012:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            boolean r2 = r2.compress(r4, r5, r3)     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r3.close()     // Catch:{ IOException -> 0x001e }
        L_0x001e:
            return r2
        L_0x001f:
            r2 = move-exception
            r0 = r3
            goto L_0x0025
        L_0x0022:
            r0 = r3
            goto L_0x002b
        L_0x0024:
            r2 = move-exception
        L_0x0025:
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            throw r2
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.sdk.ShareUtils.a(android.graphics.Bitmap, java.lang.String, android.graphics.Bitmap$CompressFormat, int):boolean");
    }
}
