package com.xiaomi.zxing.multi.qrcode.detector;

import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DetectorResult;
import com.xiaomi.zxing.qrcode.detector.Detector;
import com.xiaomi.zxing.qrcode.detector.FinderPatternInfo;
import java.util.ArrayList;
import java.util.Map;

public final class MultiDetector extends Detector {

    /* renamed from: a  reason: collision with root package name */
    private static final DetectorResult[] f1687a = new DetectorResult[0];

    public MultiDetector(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    public DetectorResult[] a(Map<DecodeHintType, ?> map) throws NotFoundException {
        ResultPointCallback resultPointCallback;
        BitMatrix a2 = a();
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        FinderPatternInfo[] a3 = new MultiFinderPatternFinder(a2, resultPointCallback).a(map);
        if (a3.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (FinderPatternInfo a4 : a3) {
                try {
                    arrayList.add(a(a4));
                } catch (ReaderException unused) {
                }
            }
            if (arrayList.isEmpty()) {
                return f1687a;
            }
            return (DetectorResult[]) arrayList.toArray(new DetectorResult[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
