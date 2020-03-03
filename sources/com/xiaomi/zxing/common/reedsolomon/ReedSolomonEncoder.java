package com.xiaomi.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {

    /* renamed from: a  reason: collision with root package name */
    private final GenericGF f1660a;
    private final List<GenericGFPoly> b = new ArrayList();

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.f1660a = genericGF;
        this.b.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly a(int i) {
        if (i >= this.b.size()) {
            GenericGFPoly genericGFPoly = this.b.get(this.b.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                genericGFPoly = genericGFPoly.b(new GenericGFPoly(this.f1660a, new int[]{1, this.f1660a.a((size - 1) + this.f1660a.d())}));
                this.b.add(genericGFPoly);
            }
        }
        return this.b.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                GenericGFPoly a2 = a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] a3 = new GenericGFPoly(this.f1660a, iArr2).a(i, 1).c(a2)[1].a();
                int length2 = i - a3.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(a3, 0, iArr, length + length2, a3.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
