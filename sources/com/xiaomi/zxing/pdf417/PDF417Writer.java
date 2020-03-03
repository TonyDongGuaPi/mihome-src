package com.xiaomi.zxing.pdf417;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.Writer;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.pdf417.encoder.Compaction;
import com.xiaomi.zxing.pdf417.encoder.Dimensions;
import com.xiaomi.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer implements Writer {

    /* renamed from: a  reason: collision with root package name */
    static final int f1732a = 30;
    static final int b = 2;

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        int i3;
        int i4;
        if (barcodeFormat == BarcodeFormat.PDF_417) {
            PDF417 pdf417 = new PDF417();
            int i5 = 30;
            int i6 = 2;
            if (map != null) {
                if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                    pdf417.a(Boolean.valueOf(map.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue());
                }
                if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                    pdf417.a(Compaction.valueOf(map.get(EncodeHintType.PDF417_COMPACTION).toString()));
                }
                if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                    Dimensions dimensions = (Dimensions) map.get(EncodeHintType.PDF417_DIMENSIONS);
                    pdf417.a(dimensions.b(), dimensions.a(), dimensions.d(), dimensions.c());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    i5 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    i6 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                    pdf417.a(Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString()));
                }
                i3 = i5;
                i4 = i6;
            } else {
                i4 = 2;
                i3 = 30;
            }
            return a(pdf417, str, i4, i, i2, i3);
        }
        throw new IllegalArgumentException("Can only encode PDF_417, but got " + barcodeFormat);
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return a(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    private static BitMatrix a(PDF417 pdf417, String str, int i, int i2, int i3, int i4) throws WriterException {
        boolean z;
        pdf417.a(str, i);
        byte[][] a2 = pdf417.a().a(1, 4);
        if ((i3 > i2) ^ (a2[0].length < a2.length)) {
            a2 = a(a2);
            z = true;
        } else {
            z = false;
        }
        int length = i2 / a2[0].length;
        int length2 = i3 / a2.length;
        if (length >= length2) {
            length = length2;
        }
        if (length <= 1) {
            return a(a2, i4);
        }
        byte[][] a3 = pdf417.a().a(length, length * 4);
        if (z) {
            a3 = a(a3);
        }
        return a(a3, i4);
    }

    private static BitMatrix a(byte[][] bArr, int i) {
        int i2 = i * 2;
        BitMatrix bitMatrix = new BitMatrix(bArr[0].length + i2, bArr.length + i2);
        bitMatrix.a();
        int g = (bitMatrix.g() - i) - 1;
        int i3 = 0;
        while (i3 < bArr.length) {
            for (int i4 = 0; i4 < bArr[0].length; i4++) {
                if (bArr[i3][i4] == 1) {
                    bitMatrix.b(i4 + i, g);
                }
            }
            i3++;
            g--;
        }
        return bitMatrix;
    }

    private static byte[][] a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, new int[]{bArr[0].length, bArr.length});
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
