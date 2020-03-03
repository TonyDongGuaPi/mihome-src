package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN8Writer extends UPCEANWriter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1697a = 67;

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got " + barcodeFormat);
    }

    public boolean[] a(String str) {
        if (str.length() == 8) {
            boolean[] zArr = new boolean[67];
            int b = b(zArr, 0, UPCEANReader.b, true) + 0;
            int i = 0;
            while (i <= 3) {
                int i2 = i + 1;
                b += b(zArr, b, UPCEANReader.e[Integer.parseInt(str.substring(i, i2))], false);
                i = i2;
            }
            int b2 = b + b(zArr, b, UPCEANReader.c, false);
            int i3 = 4;
            while (i3 <= 7) {
                int i4 = i3 + 1;
                b2 += b(zArr, b2, UPCEANReader.e[Integer.parseInt(str.substring(i3, i4))], true);
                i3 = i4;
            }
            b(zArr, b2, UPCEANReader.b, true);
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + str.length());
    }
}
