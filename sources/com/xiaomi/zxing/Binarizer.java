package com.xiaomi.zxing;

import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.BitMatrix;

public abstract class Binarizer {

    /* renamed from: a  reason: collision with root package name */
    private final LuminanceSource f1621a;

    public abstract Binarizer a(LuminanceSource luminanceSource);

    public abstract BitArray a(int i, BitArray bitArray) throws NotFoundException;

    public abstract BitMatrix b() throws NotFoundException;

    protected Binarizer(LuminanceSource luminanceSource) {
        this.f1621a = luminanceSource;
    }

    public final LuminanceSource a() {
        return this.f1621a;
    }

    public final int c() {
        return this.f1621a.g();
    }

    public final int d() {
        return this.f1621a.h();
    }
}
