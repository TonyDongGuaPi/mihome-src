package com.xiaomi.zxing.qrcode;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.Writer;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaomi.zxing.qrcode.encoder.ByteMatrix;
import com.xiaomi.zxing.qrcode.encoder.Encoder;
import com.xiaomi.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter implements Writer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1756a = 4;

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return a(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int i3 = 4;
            if (map != null) {
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
            }
            return a(Encoder.a(str, errorCorrectionLevel, map), i, i2, i3);
        }
    }

    private static BitMatrix a(QRCode qRCode, int i, int i2, int i3) {
        ByteMatrix e = qRCode.e();
        if (e != null) {
            int b = e.b();
            int a2 = e.a();
            int i4 = i3 * 2;
            int i5 = b + i4;
            int i6 = i4 + a2;
            int max = Math.max(i, i5);
            int max2 = Math.max(i2, i6);
            int min = Math.min(max / i5, max2 / i6);
            int i7 = (max - (b * min)) / 2;
            int i8 = (max2 - (a2 * min)) / 2;
            BitMatrix bitMatrix = new BitMatrix(max, max2);
            int i9 = 0;
            while (i9 < a2) {
                int i10 = i7;
                int i11 = 0;
                while (i11 < b) {
                    if (e.a(i11, i9) == 1) {
                        bitMatrix.a(i10, i8, min, min);
                    }
                    i11++;
                    i10 += min;
                }
                i9++;
                i8 += min;
            }
            return bitMatrix;
        }
        throw new IllegalStateException();
    }
}
