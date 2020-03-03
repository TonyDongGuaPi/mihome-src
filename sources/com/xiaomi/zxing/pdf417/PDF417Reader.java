package com.xiaomi.zxing.pdf417;

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
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.multi.MultipleBarcodeReader;
import com.xiaomi.zxing.pdf417.decoder.PDF417ScanningDecoder;
import com.xiaomi.zxing.pdf417.detector.Detector;
import com.xiaomi.zxing.pdf417.detector.PDF417DetectorResult;
import java.util.ArrayList;
import java.util.Map;

public final class PDF417Reader implements Reader, MultipleBarcodeReader {
    public void a() {
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException, ChecksumException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        Result[] a2 = a(binaryBitmap, map, false);
        if (a2 != null && a2.length != 0 && a2[0] != null) {
            return a2[0];
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result[] a_(BinaryBitmap binaryBitmap) throws NotFoundException {
        return a_(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result[] a_(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        try {
            return a(binaryBitmap, map, true);
        } catch (ChecksumException | FormatException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Result[] a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException, FormatException, ChecksumException {
        ArrayList arrayList = new ArrayList();
        PDF417DetectorResult a2 = Detector.a(binaryBitmap, map, z);
        for (ResultPoint[] next : a2.b()) {
            DecoderResult a3 = PDF417ScanningDecoder.a(a2.a(), next[4], next[5], next[6], next[7], b(next), a(next));
            Result result = new Result(a3.b(), a3.a(), next, BarcodeFormat.PDF_417);
            result.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, a3.d());
            PDF417ResultMetadata pDF417ResultMetadata = (PDF417ResultMetadata) a3.g();
            if (pDF417ResultMetadata != null) {
                result.a(ResultMetadataType.PDF417_EXTRA_METADATA, pDF417ResultMetadata);
            }
            arrayList.add(result);
        }
        return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
    }

    private static int a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        if (resultPoint == null || resultPoint2 == null) {
            return 0;
        }
        return (int) Math.abs(resultPoint.a() - resultPoint2.a());
    }

    private static int b(ResultPoint resultPoint, ResultPoint resultPoint2) {
        if (resultPoint == null || resultPoint2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(resultPoint.a() - resultPoint2.a());
    }

    private static int a(ResultPoint[] resultPointArr) {
        return Math.max(Math.max(a(resultPointArr[0], resultPointArr[4]), (a(resultPointArr[6], resultPointArr[2]) * 17) / 18), Math.max(a(resultPointArr[1], resultPointArr[5]), (a(resultPointArr[7], resultPointArr[3]) * 17) / 18));
    }

    private static int b(ResultPoint[] resultPointArr) {
        return Math.min(Math.min(b(resultPointArr[0], resultPointArr[4]), (b(resultPointArr[6], resultPointArr[2]) * 17) / 18), Math.min(b(resultPointArr[1], resultPointArr[5]), (b(resultPointArr[7], resultPointArr[3]) * 17) / 18));
    }
}
