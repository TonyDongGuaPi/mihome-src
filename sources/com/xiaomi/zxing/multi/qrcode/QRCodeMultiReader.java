package com.xiaomi.zxing.multi.qrcode;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultMetadataType;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.common.DetectorResult;
import com.xiaomi.zxing.multi.MultipleBarcodeReader;
import com.xiaomi.zxing.multi.qrcode.detector.MultiDetector;
import com.xiaomi.zxing.qrcode.QRCodeReader;
import com.xiaomi.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {

    /* renamed from: a  reason: collision with root package name */
    private static final Result[] f1686a = new Result[0];
    private static final ResultPoint[] b = new ResultPoint[0];

    public Result[] a_(BinaryBitmap binaryBitmap) throws NotFoundException {
        return a_(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result[] a_(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        for (DetectorResult detectorResult : new MultiDetector(binaryBitmap.c()).a(map)) {
            try {
                DecoderResult a2 = b().a(detectorResult.d(), map);
                ResultPoint[] e = detectorResult.e();
                if (a2.g() instanceof QRCodeDecoderMetaData) {
                    ((QRCodeDecoderMetaData) a2.g()).a(e);
                }
                Result result = new Result(a2.b(), a2.a(), e, BarcodeFormat.QR_CODE);
                List<byte[]> c = a2.c();
                if (c != null) {
                    result.a(ResultMetadataType.BYTE_SEGMENTS, c);
                }
                String d = a2.d();
                if (d != null) {
                    result.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
                }
                if (a2.h()) {
                    result.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a2.j()));
                    result.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(a2.i()));
                }
                arrayList.add(result);
            } catch (ReaderException unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return f1686a;
        }
        List<Result> a3 = a(arrayList);
        return (Result[]) a3.toArray(new Result[a3.size()]);
    }

    private static List<Result> a(List<Result> list) {
        boolean z;
        Iterator<Result> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().e().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<Result> arrayList2 = new ArrayList<>();
        for (Result next : list) {
            arrayList.add(next);
            if (next.e().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(next);
            }
        }
        Collections.sort(arrayList2, new SAComparator());
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (Result result : arrayList2) {
            sb.append(result.a());
            i += result.b().length;
            if (result.e().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] length : (Iterable) result.e().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    i2 += length.length;
                }
            }
        }
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        for (Result result2 : arrayList2) {
            System.arraycopy(result2.b(), 0, bArr, i3, result2.b().length);
            i3 += result2.b().length;
            if (result2.e().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] bArr3 : (Iterable) result2.e().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    System.arraycopy(bArr3, 0, bArr2, i4, bArr3.length);
                    i4 += bArr3.length;
                }
            }
        }
        Result result3 = new Result(sb.toString(), bArr, b, BarcodeFormat.QR_CODE);
        if (i2 > 0) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(bArr2);
            result3.a(ResultMetadataType.BYTE_SEGMENTS, arrayList3);
        }
        arrayList.add(result3);
        return arrayList;
    }

    private static final class SAComparator implements Serializable, Comparator<Result> {
        private SAComparator() {
        }

        public int compare(Result result, Result result2) {
            int intValue = ((Integer) result.e().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            int intValue2 = ((Integer) result2.e().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            if (intValue < intValue2) {
                return -1;
            }
            return intValue > intValue2 ? 1 : 0;
        }
    }
}
