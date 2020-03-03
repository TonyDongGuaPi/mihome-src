package com.xiaomi.smarthome.screenshot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.View;
import java.util.List;

public class ScreenshotUtils {
    private ScreenshotUtils() {
    }

    public static final Bitmap a(@NonNull View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap createBitmap = Bitmap.createBitmap(a(view, true));
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static Bitmap a(View view, boolean z) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) (-view.getScrollX()), (float) (-view.getScrollY()));
        view.draw(canvas);
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0012 A[LOOP:0: B:3:0x0012->B:6:0x001f, LOOP_START, PHI: r3 
      PHI: (r3v2 int) = (r3v1 int), (r3v5 int) binds: [B:2:0x0004, B:6:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.Bitmap a(@android.support.annotation.NonNull android.graphics.Bitmap r3) {
        /*
            if (r3 != 0) goto L_0x0004
            r3 = 0
            return r3
        L_0x0004:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 100
            r3.compress(r1, r2, r0)
            r3 = 100
        L_0x0012:
            byte[] r1 = r0.toByteArray()
            int r1 = r1.length
            int r1 = r1 / 1024
            if (r1 <= r2) goto L_0x0021
            int r3 = r3 + -10
            r1 = 10
            if (r3 >= r1) goto L_0x0012
        L_0x0021:
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
            byte[] r0 = r0.toByteArray()
            r3.<init>(r0)
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.screenshot.ScreenshotUtils.a(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public static final Bitmap a(@NonNull List<Bitmap> list, int i) {
        return a(list, i, 0);
    }

    public static final Bitmap a(@NonNull List<Bitmap> list, int i, int i2) {
        Rect rect;
        RectF rectF;
        if (list == null || list.size() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(list.get(0).getWidth(), i, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Bitmap bitmap = list.get(i3);
            float height = (float) (bitmap.getHeight() * i3);
            if (i3 != size - 1 || i2 <= 0) {
                rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                rectF = new RectF(0.0f, height, (float) bitmap.getWidth(), ((float) bitmap.getHeight()) + height);
            } else {
                rect = new Rect(0, bitmap.getHeight() - i2, bitmap.getWidth(), bitmap.getHeight());
                rectF = new RectF(0.0f, height, (float) bitmap.getWidth(), ((float) i2) + height);
            }
            canvas.drawBitmap(bitmap, rect, rectF, paint);
        }
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0050 A[SYNTHETIC, Splitter:B:29:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005b A[SYNTHETIC, Splitter:B:34:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(@android.support.annotation.NonNull android.graphics.Bitmap r2, @android.support.annotation.NonNull java.lang.String r3) {
        /*
            if (r2 == 0) goto L_0x0064
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0009
            goto L_0x0064
        L_0x0009:
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r3 = r0.exists()
            if (r3 == 0) goto L_0x0020
            r0.delete()
            r0.createNewFile()     // Catch:{ IOException -> 0x001b }
            goto L_0x002d
        L_0x001b:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x002d
        L_0x0020:
            java.io.File r3 = r0.getParentFile()
            boolean r1 = r3.exists()
            if (r1 != 0) goto L_0x002d
            r3.mkdirs()
        L_0x002d:
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2)
            r3 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x004a }
            r1.<init>(r0)     // Catch:{ FileNotFoundException -> 0x004a }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r0 = 100
            r2.compress(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0042:
            r2 = move-exception
            r3 = r1
            goto L_0x0059
        L_0x0045:
            r2 = move-exception
            r3 = r1
            goto L_0x004b
        L_0x0048:
            r2 = move-exception
            goto L_0x0059
        L_0x004a:
            r2 = move-exception
        L_0x004b:
            r2.printStackTrace()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0058
            r3.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0058:
            return
        L_0x0059:
            if (r3 == 0) goto L_0x0063
            r3.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0063:
            throw r2
        L_0x0064:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.screenshot.ScreenshotUtils.a(android.graphics.Bitmap, java.lang.String):void");
    }
}
