package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.Writer;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCAWriter implements Writer {

    /* renamed from: a  reason: collision with root package name */
    private final EAN13Writer f1704a = new EAN13Writer();

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return a(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return this.f1704a.a(a(str), BarcodeFormat.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + barcodeFormat);
    }

    private static String a(String str) {
        int length = str.length();
        if (length == 11) {
            int i = 0;
            for (int i2 = 0; i2 < 11; i2++) {
                i += (str.charAt(i2) - '0') * (i2 % 2 == 0 ? 3 : 1);
            }
            str = str + ((1000 - i) % 10);
        } else if (length != 12) {
            throw new IllegalArgumentException("Requested contents should be 11 or 12 digits long, but got " + str.length());
        }
        return '0' + str;
    }
}
