package com.xiaomi.zxing.datamatrix;

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
import com.xiaomi.zxing.common.DetectorResult;
import com.xiaomi.zxing.datamatrix.decoder.Decoder;
import com.xiaomi.zxing.datamatrix.detector.Detector;
import java.util.List;
import java.util.Map;

public final class DataMatrixReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    private static final ResultPoint[] f1661a = new ResultPoint[0];
    private final Decoder b = new Decoder();

    public void a() {
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult a2 = new Detector(binaryBitmap.c()).a();
            DecoderResult a3 = this.b.a(a2.d());
            resultPointArr = a2.e();
            decoderResult = a3;
        } else {
            decoderResult = this.b.a(a(binaryBitmap.c()));
            resultPointArr = f1661a;
        }
        Result result = new Result(decoderResult.b(), decoderResult.a(), resultPointArr, BarcodeFormat.DATA_MATRIX);
        List<byte[]> c = decoderResult.c();
        if (c != null) {
            result.a(ResultMetadataType.BYTE_SEGMENTS, c);
        }
        String d = decoderResult.d();
        if (d != null) {
            result.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
        }
        return result;
    }

    private static BitMatrix a(BitMatrix bitMatrix) throws NotFoundException {
        int[] d = bitMatrix.d();
        int[] e = bitMatrix.e();
        if (d == null || e == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int a2 = a(d, bitMatrix);
        int i = d[1];
        int i2 = e[1];
        int i3 = d[0];
        int i4 = ((e[0] - i3) + 1) / a2;
        int i5 = ((i2 - i) + 1) / a2;
        if (i4 <= 0 || i5 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i6 = a2 / 2;
        int i7 = i + i6;
        int i8 = i3 + i6;
        BitMatrix bitMatrix2 = new BitMatrix(i4, i5);
        for (int i9 = 0; i9 < i5; i9++) {
            int i10 = (i9 * a2) + i7;
            for (int i11 = 0; i11 < i4; i11++) {
                if (bitMatrix.a((i11 * a2) + i8, i10)) {
                    bitMatrix2.b(i11, i9);
                }
            }
        }
        return bitMatrix2;
    }

    private static int a(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int f = bitMatrix.f();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < f && bitMatrix.a(i, i2)) {
            i++;
        }
        if (i != f) {
            int i3 = i - iArr[0];
            if (i3 != 0) {
                return i3;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
