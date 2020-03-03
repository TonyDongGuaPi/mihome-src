package com.xiaomi.zxing.qrcode;

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
import com.xiaomi.zxing.qrcode.decoder.Decoder;
import com.xiaomi.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.xiaomi.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

public class QRCodeReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    private static final ResultPoint[] f1755a = new ResultPoint[0];
    private final Decoder b = new Decoder();

    public void a() {
    }

    /* access modifiers changed from: protected */
    public final Decoder b() {
        return this.b;
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public final Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult b2 = new Detector(binaryBitmap.c()).b(map);
            DecoderResult a2 = this.b.a(b2.d(), map);
            resultPointArr = b2.e();
            decoderResult = a2;
        } else {
            decoderResult = this.b.a(a(binaryBitmap.c()), map);
            resultPointArr = f1755a;
        }
        if (decoderResult.g() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) decoderResult.g()).a(resultPointArr);
        }
        Result result = new Result(decoderResult.b(), decoderResult.a(), resultPointArr, BarcodeFormat.QR_CODE);
        List<byte[]> c = decoderResult.c();
        if (c != null) {
            result.a(ResultMetadataType.BYTE_SEGMENTS, c);
        }
        String d = decoderResult.d();
        if (d != null) {
            result.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
        }
        if (decoderResult.h()) {
            result.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderResult.j()));
            result.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderResult.i()));
        }
        return result;
    }

    private static BitMatrix a(BitMatrix bitMatrix) throws NotFoundException {
        int[] d = bitMatrix.d();
        int[] e = bitMatrix.e();
        if (d == null || e == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float a2 = a(d, bitMatrix);
        int i = d[1];
        int i2 = e[1];
        int i3 = d[0];
        int i4 = e[0];
        if (i3 >= i4 || i >= i2) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        if (i5 == i4 - i3 || (i4 = i3 + i5) < bitMatrix.f()) {
            int round = Math.round(((float) ((i4 - i3) + 1)) / a2);
            int round2 = Math.round(((float) (i5 + 1)) / a2);
            if (round <= 0 || round2 <= 0) {
                throw NotFoundException.getNotFoundInstance();
            } else if (round2 == round) {
                int i6 = (int) (a2 / 2.0f);
                int i7 = i + i6;
                int i8 = i3 + i6;
                int i9 = (((int) (((float) (round - 1)) * a2)) + i8) - i4;
                if (i9 > 0) {
                    if (i9 <= i6) {
                        i8 -= i9;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i10 = (((int) (((float) (round2 - 1)) * a2)) + i7) - i2;
                if (i10 > 0) {
                    if (i10 <= i6) {
                        i7 -= i10;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                BitMatrix bitMatrix2 = new BitMatrix(round, round2);
                for (int i11 = 0; i11 < round2; i11++) {
                    int i12 = ((int) (((float) i11) * a2)) + i7;
                    for (int i13 = 0; i13 < round; i13++) {
                        if (bitMatrix.a(((int) (((float) i13) * a2)) + i8, i12)) {
                            bitMatrix2.b(i13, i11);
                        }
                    }
                }
                return bitMatrix2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static float a(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int g = bitMatrix.g();
        int f = bitMatrix.f();
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        int i3 = 0;
        while (i < f && i2 < g) {
            if (z != bitMatrix.a(i, i2)) {
                i3++;
                if (i3 == 5) {
                    break;
                }
                z = !z;
            }
            i++;
            i2++;
        }
        if (i != f && i2 != g) {
            return ((float) (i - iArr[0])) / 7.0f;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
