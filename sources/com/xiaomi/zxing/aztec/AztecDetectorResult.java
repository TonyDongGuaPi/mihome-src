package com.xiaomi.zxing.aztec;

import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DetectorResult;

public final class AztecDetectorResult extends DetectorResult {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f1633a;
    private final int b;
    private final int c;

    public AztecDetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr, boolean z, int i, int i2) {
        super(bitMatrix, resultPointArr);
        this.f1633a = z;
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.f1633a;
    }
}
