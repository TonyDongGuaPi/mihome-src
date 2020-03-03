package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN13Writer extends UPCEANWriter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1695a = 95;

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + barcodeFormat);
    }

    public boolean[] a(String str) {
        if (str.length() == 13) {
            try {
                if (UPCEANReader.a((CharSequence) str)) {
                    int i = EAN13Reader.f1694a[Integer.parseInt(str.substring(0, 1))];
                    boolean[] zArr = new boolean[95];
                    int b = b(zArr, 0, UPCEANReader.b, true) + 0;
                    int i2 = 1;
                    while (i2 <= 6) {
                        int i3 = i2 + 1;
                        int parseInt = Integer.parseInt(str.substring(i2, i3));
                        if (((i >> (6 - i2)) & 1) == 1) {
                            parseInt += 10;
                        }
                        b += b(zArr, b, UPCEANReader.f[parseInt], false);
                        i2 = i3;
                    }
                    int b2 = b + b(zArr, b, UPCEANReader.c, false);
                    int i4 = 7;
                    while (i4 <= 12) {
                        int i5 = i4 + 1;
                        b2 += b(zArr, b2, UPCEANReader.e[Integer.parseInt(str.substring(i4, i5))], true);
                        i4 = i5;
                    }
                    b(zArr, b2, UPCEANReader.b, true);
                    return zArr;
                }
                throw new IllegalArgumentException("Contents do not pass checksum");
            } catch (FormatException unused) {
                throw new IllegalArgumentException("Illegal contents");
            }
        } else {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + str.length());
        }
    }
}
