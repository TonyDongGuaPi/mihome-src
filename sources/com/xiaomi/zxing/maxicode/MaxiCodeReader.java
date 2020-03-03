package com.xiaomi.zxing.maxicode;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Reader;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultMetadataType;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.maxicode.decoder.Decoder;
import java.util.Map;

public final class MaxiCodeReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    private static final ResultPoint[] f1680a = new ResultPoint[0];
    private static final int b = 30;
    private static final int c = 33;
    private final Decoder d = new Decoder();

    public void a() {
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        DecoderResult a2 = this.d.a(a(binaryBitmap.c()), map);
        Result result = new Result(a2.b(), a2.a(), f1680a, BarcodeFormat.MAXICODE);
        String d2 = a2.d();
        if (d2 != null) {
            result.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d2);
        }
        return result;
    }

    private static BitMatrix a(BitMatrix bitMatrix) throws NotFoundException {
        int[] c2 = bitMatrix.c();
        if (c2 != null) {
            int i = c2[0];
            int i2 = c2[1];
            int i3 = c2[2];
            int i4 = c2[3];
            BitMatrix bitMatrix2 = new BitMatrix(30, 33);
            for (int i5 = 0; i5 < 33; i5++) {
                int i6 = (((i5 * i4) + (i4 / 2)) / 33) + i2;
                for (int i7 = 0; i7 < 30; i7++) {
                    if (bitMatrix.a(((((i7 * i3) + (i3 / 2)) + (((i5 & 1) * i3) / 2)) / 30) + i, i6)) {
                        bitMatrix2.b(i7, i5);
                    }
                }
            }
            return bitMatrix2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
