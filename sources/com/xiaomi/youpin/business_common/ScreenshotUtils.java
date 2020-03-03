package com.xiaomi.youpin.business_common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class ScreenshotUtils {
    public static Bitmap a(Context context, String str, String str2, int i, int i2) {
        Bitmap decodeFile;
        Bitmap decodeResource;
        if (TextUtils.isEmpty(str2) || i == 0) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            decodeFile = BitmapFactory.decodeFile(str2, options);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            options2.inScaled = false;
            decodeResource = BitmapFactory.decodeResource(context.getResources(), i, options2);
            if (decodeFile != null) {
                if (decodeResource != null) {
                    Bitmap a2 = a(str, decodeResource);
                    if (a2 != null) {
                        decodeResource.recycle();
                    } else {
                        a2 = decodeResource;
                    }
                    int a3 = a(context);
                    int width = decodeFile.getWidth();
                    int height = (a2.getHeight() + decodeFile.getHeight()) - a3;
                    if (a2.getWidth() != decodeFile.getWidth()) {
                        if (width != 1080) {
                            a3 = (a3 * width) / 1080;
                        }
                        height = (decodeFile.getHeight() + ((decodeFile.getWidth() * a2.getHeight()) / a2.getWidth())) - a3;
                    }
                    Rect rect = new Rect();
                    rect.left = 0;
                    rect.right = rect.left + decodeFile.getWidth();
                    rect.top = 0;
                    rect.bottom = decodeFile.getHeight() - a3;
                    Rect rect2 = new Rect();
                    rect2.left = 0;
                    rect2.right = rect2.left + decodeFile.getWidth();
                    rect2.top = rect.bottom;
                    rect2.bottom = height;
                    Bitmap createBitmap = Bitmap.createBitmap(width, height, a2.getConfig());
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    Canvas canvas = new Canvas(createBitmap);
                    canvas.drawBitmap(decodeFile, new Rect(0, a3, decodeFile.getWidth(), decodeFile.getHeight()), rect, paint);
                    canvas.drawBitmap(a2, new Rect(0, 0, a2.getWidth(), a2.getHeight()), rect2, paint);
                    decodeFile.recycle();
                    a2.recycle();
                    return createBitmap;
                }
            }
            if (i2 <= 0) {
                if (decodeFile != null) {
                    decodeFile.recycle();
                }
                if (decodeResource != null) {
                    decodeResource.recycle();
                }
                return null;
            }
            try {
                Thread.sleep(1000);
                Bitmap a4 = a(context, str, str2, i, i2 - 1);
                if (decodeFile != null) {
                    decodeFile.recycle();
                }
                if (decodeResource != null) {
                    decodeResource.recycle();
                }
                return a4;
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (decodeFile != null) {
                    decodeFile.recycle();
                }
                if (decodeResource != null) {
                    decodeResource.recycle();
                }
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } catch (Throwable th) {
            if (decodeFile != null) {
                decodeFile.recycle();
            }
            if (decodeResource != null) {
                decodeResource.recycle();
            }
            throw th;
        }
    }

    public static Bitmap a(String str, Bitmap bitmap) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Bitmap a2 = a(str, 283, 283);
            if (a2 == null) {
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
            canvas.drawBitmap(a2, new Rect(0, 0, 283, 283), new Rect(175, 86, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 369), paint);
            a2.recycle();
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir().getAbsolutePath());
        sb.append(File.separator);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(JSMethod.NOT_SET);
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        sb.append(String.format("%d_%02d_%02d_%02d_%02d_%02d", new Object[]{Integer.valueOf(gregorianCalendar.get(1)), Integer.valueOf(gregorianCalendar.get(2) + 1), Integer.valueOf(gregorianCalendar.get(5)), Integer.valueOf(gregorianCalendar.get(11)), Integer.valueOf(gregorianCalendar.get(12)), Integer.valueOf(gregorianCalendar.get(13))}));
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        return sb.toString();
    }

    public static boolean b(String str, Bitmap bitmap) {
        if (bitmap == null || TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int a(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.screenshot_padding_top);
    }

    public static Bitmap a(String str, int i, int i2) throws WriterException {
        return a(str, -8167617, i, i2);
    }

    public static Bitmap a(String str, int i, int i2, int i3) throws WriterException {
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashtable.put(EncodeHintType.MARGIN, "0");
        BitMatrix a2 = new QRCodeWriter().a(str, BarcodeFormat.QR_CODE, i2, i3, hashtable);
        int[] iArr = new int[(i2 * i3)];
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                if (a2.a(i5, i4)) {
                    iArr[(i4 * i2) + i5] = i;
                } else {
                    iArr[(i4 * i2) + i5] = -1;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        return createBitmap;
    }
}
