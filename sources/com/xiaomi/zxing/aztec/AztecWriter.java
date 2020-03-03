package com.xiaomi.zxing.aztec;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.Writer;
import com.xiaomi.zxing.aztec.encoder.AztecCode;
import com.xiaomi.zxing.aztec.encoder.Encoder;
import com.xiaomi.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {

    /* renamed from: a  reason: collision with root package name */
    private static final Charset f1634a = Charset.forName("ISO-8859-1");

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return a(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        int i3;
        int i4;
        Charset charset;
        Charset charset2 = f1634a;
        int i5 = 33;
        if (map != null) {
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset2 = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i5 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (map.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                charset = charset2;
                i4 = i5;
                i3 = Integer.parseInt(map.get(EncodeHintType.AZTEC_LAYERS).toString());
                return a(str, barcodeFormat, i, i2, charset, i4, i3);
            }
            charset = charset2;
            i4 = i5;
        } else {
            charset = charset2;
            i4 = 33;
        }
        i3 = 0;
        return a(str, barcodeFormat, i, i2, charset, i4, i3);
    }

    private static BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Charset charset, int i3, int i4) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return a(Encoder.a(str.getBytes(charset), i3, i4), i, i2);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + barcodeFormat);
    }

    private static BitMatrix a(AztecCode aztecCode, int i, int i2) {
        BitMatrix e = aztecCode.e();
        if (e != null) {
            int f = e.f();
            int g = e.g();
            int max = Math.max(i, f);
            int max2 = Math.max(i2, g);
            int min = Math.min(max / f, max2 / g);
            int i3 = (max - (f * min)) / 2;
            int i4 = (max2 - (g * min)) / 2;
            BitMatrix bitMatrix = new BitMatrix(max, max2);
            int i5 = 0;
            while (i5 < g) {
                int i6 = i3;
                int i7 = 0;
                while (i7 < f) {
                    if (e.a(i7, i5)) {
                        bitMatrix.a(i6, i4, min, min);
                    }
                    i7++;
                    i6 += min;
                }
                i5++;
                i4 += min;
            }
            return bitMatrix;
        }
        throw new IllegalStateException();
    }
}
