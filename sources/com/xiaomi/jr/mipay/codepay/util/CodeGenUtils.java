package com.xiaomi.jr.mipay.codepay.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaomi.jr.common.utils.MifiLog;
import java.util.HashMap;

public class CodeGenUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10933a = "CodeGenUtils";
    private static final int b = 4;
    private static final int c = 3;
    private static final float d = 6.0f;
    private static final int e = -592138;

    private CodeGenUtils() {
    }

    public static Bitmap a(String str, int i, int i2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(EncodeHintType.MARGIN, 4);
            return a(new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, i, i2, hashMap), -1);
        } catch (WriterException e2) {
            MifiLog.e(f10933a, "Create barCode failed", e2);
            return null;
        }
    }

    public static Bitmap b(String str, int i, int i2) {
        return a(str, i, i2, (Bitmap) null);
    }

    public static Bitmap a(String str, int i, int i2, Bitmap bitmap) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hashMap.put(EncodeHintType.MARGIN, 3);
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashMap);
            int width = encode.getWidth();
            int height = encode.getHeight();
            Bitmap a2 = a(encode, e);
            Bitmap a3 = a(a2, width, height, bitmap);
            if (a2 != a3) {
                a2.recycle();
            }
            return a3;
        } catch (WriterException e2) {
            MifiLog.e(f10933a, "Create qrCode failed", e2);
            return null;
        }
    }

    private static Bitmap a(BitMatrix bitMatrix, int i) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[(width * height)];
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                if (bitMatrix.get(i3, i2)) {
                    iArr[(i2 * width) + i3] = -16777216;
                } else {
                    iArr[(i2 * width) + i3] = i;
                }
            }
        }
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }

    private static Bitmap a(Bitmap bitmap, int i, int i2, Bitmap bitmap2) {
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();
            float f = (((float) i) / d) / ((float) width);
            try {
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                canvas.scale(f, f, (float) (i / 2), (float) (i2 / 2));
                canvas.drawBitmap(bitmap2, (float) ((i - width) / 2), (float) ((i2 - height) / 2), (Paint) null);
                canvas.save();
                return createBitmap;
            } catch (Exception e2) {
                MifiLog.e(f10933a, "Add logo to qrCode failed", e2);
            }
        }
        return bitmap;
    }
}
