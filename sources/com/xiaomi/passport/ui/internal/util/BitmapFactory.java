package com.xiaomi.passport.ui.internal.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.renderscript.RenderScript;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.passport.ui.R;
import java.io.IOException;

public class BitmapFactory extends android.graphics.BitmapFactory {
    public static final int BITMAP_COLOR_MODE_DARK = 0;
    public static final int BITMAP_COLOR_MODE_LIGHT = 2;
    public static final int BITMAP_COLOR_MODE_MEDIUM = 1;
    private static byte[] PNG_HEAD_FORMAT = {Constants.TagName.COMPANY_CODE, Constants.TagName.ORDER_BRIEF_INFO_LIST, 78, Constants.TagName.ACTIVITY_INFO, 13, 10, 26, 10};
    private static final ThreadLocal<Canvas> sCanvasCache = new ThreadLocal<>();
    static Object sLockForRsContext = new Object();
    static RenderScript sRsContext;
    private static final Paint sSrcInPaint = new Paint(1);

    static {
        sSrcInPaint.setFilterBitmap(true);
        sSrcInPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    protected BitmapFactory() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static Bitmap scaleBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        scaleBitmap(bitmap, createBitmap);
        return createBitmap;
    }

    private static Bitmap scaleBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        if (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight()) {
            return bitmap;
        }
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), paint);
        return bitmap2;
    }

    static Bitmap cropBitmap(Bitmap bitmap, CropOption cropOption) {
        if (bitmap != null) {
            return cropBitmap(bitmap, copyToEmpty(bitmap), cropOption);
        }
        return null;
    }

    static Bitmap createPhoto(Context context, Bitmap bitmap) {
        return createPhoto(context, bitmap, context.getResources().getDimensionPixelSize(R.dimen.contact_photo_width));
    }

    public static Bitmap createPhoto(Context context, Bitmap bitmap, int i) {
        Resources resources = context.getResources();
        return composeBitmap(bitmap, (Bitmap) null, resources.getDrawable(R.drawable.ic_contact_photo_mask), resources.getDrawable(R.drawable.ic_contact_photo_fg), resources.getDrawable(R.drawable.ic_contact_photo_bg), i);
    }

    private static Bitmap cropBitmap(Bitmap bitmap, Bitmap bitmap2, CropOption cropOption) {
        Bitmap bitmap3 = bitmap2;
        if (bitmap == null || bitmap3 == null) {
            return null;
        }
        CropOption cropOption2 = cropOption == null ? new CropOption() : cropOption;
        Rect rect = cropOption2.srcBmpDrawingArea;
        if (rect == null) {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        int between = between(0, bitmap.getWidth() - 1, rect.left);
        int between2 = between(between, bitmap.getWidth(), rect.right);
        int between3 = between(0, bitmap.getHeight() - 1, rect.top);
        int between4 = between(between3, bitmap.getHeight(), rect.bottom);
        int i = between2 - between;
        int i2 = between4 - between3;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        cropOption2.borderWidth = between(0, Math.min(width, height) / 2, cropOption2.borderWidth);
        cropOption2.f12139rx = between(0, width / 2, cropOption2.f12139rx);
        cropOption2.ry = between(0, height / 2, cropOption2.ry);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawARGB(0, 0, 0, 0);
        if (cropOption2.f12139rx - cropOption2.borderWidth > 0 && cropOption2.ry - cropOption2.borderWidth > 0) {
            canvas.drawRoundRect(new RectF((float) cropOption2.borderWidth, (float) cropOption2.borderWidth, (float) (width - cropOption2.borderWidth), (float) (height - cropOption2.borderWidth)), (float) (cropOption2.f12139rx - cropOption2.borderWidth), (float) (cropOption2.ry - cropOption2.borderWidth), paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        float f = (float) i;
        float f2 = (float) (width - (cropOption2.borderWidth * 2));
        float f3 = (float) i2;
        float f4 = (float) (height - (cropOption2.borderWidth * 2));
        float min = Math.min((f * 1.0f) / f2, (1.0f * f3) / f4);
        int i3 = (int) ((f - (f2 * min)) / 2.0f);
        int i4 = (int) ((f3 - (f4 * min)) / 2.0f);
        canvas.drawBitmap(bitmap, new Rect(between + i3, between3 + i4, between2 - i3, between4 - i4), new Rect(cropOption2.borderWidth, cropOption2.borderWidth, width - cropOption2.borderWidth, height - cropOption2.borderWidth), paint);
        if (cropOption2.borderWidth > 0 && (cropOption2.borderColor >>> 24) != 0) {
            paint.setColor(cropOption2.borderColor);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), (float) cropOption2.f12139rx, (float) cropOption2.ry, paint);
        }
        return bitmap2;
    }

    private static int between(int i, int i2, int i3) {
        return Math.min(i2, Math.max(i, i3));
    }

    public static boolean saveToFile(Bitmap bitmap, String str) throws IOException {
        return saveToFile(bitmap, str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean saveToFile(android.graphics.Bitmap r2, java.lang.String r3, boolean r4) throws java.io.IOException {
        /*
            if (r2 == 0) goto L_0x0023
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x001b }
            r1.<init>(r3)     // Catch:{ all -> 0x001b }
            if (r4 == 0) goto L_0x000f
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x000d }
            goto L_0x0011
        L_0x000d:
            r2 = move-exception
            goto L_0x001d
        L_0x000f:
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x000d }
        L_0x0011:
            r4 = 100
            r2.compress(r3, r4, r1)     // Catch:{ all -> 0x000d }
            r1.close()
            r2 = 1
            return r2
        L_0x001b:
            r2 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()
        L_0x0022:
            throw r2
        L_0x0023:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.BitmapFactory.saveToFile(android.graphics.Bitmap, java.lang.String, boolean):boolean");
    }

    private static Bitmap copyToEmpty(Bitmap bitmap) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        return Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
    }

    private static Bitmap transferF16ToARGB(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == 0 || height == 0) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setFlags(3);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static Canvas getCachedCanvas() {
        Canvas canvas = sCanvasCache.get();
        if (canvas != null) {
            return canvas;
        }
        Canvas canvas2 = new Canvas();
        sCanvasCache.set(canvas2);
        return canvas2;
    }

    static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3) {
        Rect rect;
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (bitmap2 != null) {
            rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        } else {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        return composeBitmap(bitmap, bitmap2, drawable, drawable2, drawable3, rect2, rect);
    }

    static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, int i) {
        return composeBitmap(bitmap, bitmap2, drawable, drawable2, drawable3, (Rect) null, new Rect(0, 0, i, i));
    }

    static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, Rect rect, Rect rect2) {
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Bitmap maskOutBitmap = maskOutBitmap(bitmap, drawable, (Bitmap) null, rect, rect2);
        Canvas cachedCanvas = getCachedCanvas();
        cachedCanvas.setBitmap(bitmap2);
        if (drawable3 != null) {
            drawable3.setBounds(rect2);
            drawable3.draw(cachedCanvas);
        }
        cachedCanvas.drawBitmap(maskOutBitmap, rect2, rect2, (Paint) null);
        maskOutBitmap.recycle();
        if (drawable2 != null) {
            drawable2.setBounds(rect2);
            drawable2.draw(cachedCanvas);
        }
        return bitmap2;
    }

    private static Bitmap maskOutBitmap(Bitmap bitmap, Drawable drawable, Bitmap bitmap2, Rect rect, Rect rect2) {
        int i;
        int i2;
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        int i3 = 0;
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Canvas cachedCanvas = getCachedCanvas();
        cachedCanvas.setBitmap(bitmap2);
        cachedCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (drawable != null) {
            drawable.setBounds(rect2);
            drawable.draw(cachedCanvas);
        }
        if (rect == null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = rect2.width();
            float height2 = (float) rect2.height();
            float f = (float) width2;
            float f2 = height2 / f;
            float f3 = (float) width;
            float f4 = f3 / f;
            float f5 = (float) height;
            float f6 = f5 / height2;
            if (f4 > f6) {
                int i4 = (int) (f5 / f2);
                i3 = (width - i4) / 2;
                width = i4;
            } else if (f4 < f6) {
                i2 = (int) (f2 * f3);
                i = (height - i2) / 2;
                rect = new Rect(i3, i, width + i3, i2 + i);
            }
            i2 = height;
            i = 0;
            rect = new Rect(i3, i, width + i3, i2 + i);
        }
        cachedCanvas.drawBitmap(bitmap, rect, rect2, sSrcInPaint);
        return bitmap2;
    }

    static int getBitmapColorMode(Bitmap bitmap, int i) {
        Bitmap bitmap2 = bitmap;
        int height = bitmap.getHeight() / i;
        int width = bitmap.getWidth() / i;
        int i2 = (width * height) / 5;
        Bitmap scaleBitmap = scaleBitmap(bitmap2, width, height);
        int i3 = 2;
        int i4 = 0;
        for (int i5 = 0; i5 < width; i5++) {
            int i6 = i3;
            int i7 = 0;
            while (true) {
                if (i7 >= height) {
                    i3 = i6;
                    break;
                }
                int pixel = scaleBitmap.getPixel(i5, i7);
                double d = (double) ((float) ((16711680 & pixel) >> 16));
                Double.isNaN(d);
                double d2 = (double) ((float) ((65280 & pixel) >> 8));
                Double.isNaN(d2);
                double d3 = (double) ((float) (pixel & 255));
                Double.isNaN(d3);
                if (((int) ((d * 0.3d) + (d2 * 0.59d) + (d3 * 0.11d))) < 180) {
                    i4++;
                    if (i4 > i2) {
                        i6 = 1;
                    }
                    if (i4 > i2 * 2) {
                        i3 = 0;
                        break;
                    }
                }
                i7++;
            }
        }
        if (scaleBitmap != bitmap2) {
            scaleBitmap.recycle();
        }
        return i3;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|7|8|9|11) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File saveAsFile(android.content.Context r5, java.io.InputStream r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            r0.<init>(r6)
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream
            r1 = 0
            java.io.FileOutputStream r2 = r5.openFileOutput(r7, r1)
            r6.<init>(r2)
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]
        L_0x0013:
            int r3 = r0.read(r2)     // Catch:{ all -> 0x0029 }
            r4 = -1
            if (r3 == r4) goto L_0x001e
            r6.write(r2, r1, r3)     // Catch:{ all -> 0x0029 }
            goto L_0x0013
        L_0x001e:
            r6.flush()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            r6.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            java.io.File r5 = r5.getFileStreamPath(r7)
            return r5
        L_0x0029:
            r5 = move-exception
            r6.flush()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            r6.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.BitmapFactory.saveAsFile(android.content.Context, java.io.InputStream, java.lang.String):java.io.File");
    }

    public static class CropOption {
        public int borderColor;
        public int borderWidth;

        /* renamed from: rx  reason: collision with root package name */
        public int f12139rx;
        public int ry;
        public Rect srcBmpDrawingArea;

        public CropOption() {
        }

        public CropOption(int i, int i2, int i3, int i4) {
            this.f12139rx = i;
            this.ry = i2;
            this.borderWidth = i3;
            this.borderColor = i4;
        }

        public CropOption(CropOption cropOption) {
            this.f12139rx = cropOption.f12139rx;
            this.ry = cropOption.ry;
            this.borderWidth = cropOption.borderWidth;
            this.borderColor = cropOption.borderColor;
            this.srcBmpDrawingArea = cropOption.srcBmpDrawingArea;
        }
    }
}
