package com.xiaomi.zxing.pdf417.detector;

import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.List;

public final class PDF417DetectorResult {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1748a;
    private final List<ResultPoint[]> b;

    public PDF417DetectorResult(BitMatrix bitMatrix, List<ResultPoint[]> list) {
        this.f1748a = bitMatrix;
        this.b = list;
    }

    public BitMatrix a() {
        return this.f1748a;
    }

    public List<ResultPoint[]> b() {
        return this.b;
    }
}
