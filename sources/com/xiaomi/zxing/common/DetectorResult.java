package com.xiaomi.zxing.common;

import com.xiaomi.zxing.ResultPoint;

public class DetectorResult {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1649a;
    private final ResultPoint[] b;

    public DetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr) {
        this.f1649a = bitMatrix;
        this.b = resultPointArr;
    }

    public final BitMatrix d() {
        return this.f1649a;
    }

    public final ResultPoint[] e() {
        return this.b;
    }
}
