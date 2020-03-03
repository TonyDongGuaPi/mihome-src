package com.xiaomi.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {

    /* renamed from: a  reason: collision with root package name */
    private final GenericGF f1659a;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.f1659a = genericGF;
    }

    public void a(int[] iArr, int i) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.f1659a, iArr);
        int[] iArr2 = new int[i];
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < i; i3++) {
            int b = genericGFPoly.b(this.f1659a.a(this.f1659a.d() + i3));
            iArr2[(iArr2.length - 1) - i3] = b;
            if (b != 0) {
                z = false;
            }
        }
        if (!z) {
            GenericGFPoly[] a2 = a(this.f1659a.a(i, 1), new GenericGFPoly(this.f1659a, iArr2), i);
            GenericGFPoly genericGFPoly2 = a2[0];
            GenericGFPoly genericGFPoly3 = a2[1];
            int[] a3 = a(genericGFPoly2);
            int[] a4 = a(genericGFPoly3, a3);
            while (i2 < a3.length) {
                int length = (iArr.length - 1) - this.f1659a.b(a3[i2]);
                if (length >= 0) {
                    iArr[length] = GenericGF.b(iArr[length], a4[i2]);
                    i2++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private GenericGFPoly[] a(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int i) throws ReedSolomonException {
        if (genericGFPoly.b() < genericGFPoly2.b()) {
            GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
        }
        GenericGFPoly a2 = this.f1659a.a();
        GenericGFPoly b = this.f1659a.b();
        GenericGFPoly genericGFPoly4 = genericGFPoly2;
        GenericGFPoly genericGFPoly5 = genericGFPoly;
        GenericGFPoly genericGFPoly6 = genericGFPoly4;
        while (genericGFPoly6.b() >= i / 2) {
            if (!genericGFPoly6.c()) {
                GenericGFPoly a3 = this.f1659a.a();
                int c = this.f1659a.c(genericGFPoly6.a(genericGFPoly6.b()));
                while (genericGFPoly5.b() >= genericGFPoly6.b() && !genericGFPoly5.c()) {
                    int b2 = genericGFPoly5.b() - genericGFPoly6.b();
                    int c2 = this.f1659a.c(genericGFPoly5.a(genericGFPoly5.b()), c);
                    a3 = a3.a(this.f1659a.a(b2, c2));
                    genericGFPoly5 = genericGFPoly5.a(genericGFPoly6.a(b2, c2));
                }
                GenericGFPoly a4 = a3.b(b).a(a2);
                if (genericGFPoly5.b() < genericGFPoly6.b()) {
                    GenericGFPoly genericGFPoly7 = genericGFPoly5;
                    genericGFPoly5 = genericGFPoly6;
                    genericGFPoly6 = genericGFPoly7;
                    GenericGFPoly genericGFPoly8 = b;
                    b = a4;
                    a2 = genericGFPoly8;
                } else {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
        int a5 = b.a(0);
        if (a5 != 0) {
            int c3 = this.f1659a.c(a5);
            return new GenericGFPoly[]{b.c(c3), genericGFPoly6.c(c3)};
        }
        throw new ReedSolomonException("sigmaTilde(0) was zero");
    }

    private int[] a(GenericGFPoly genericGFPoly) throws ReedSolomonException {
        int b = genericGFPoly.b();
        int i = 0;
        if (b == 1) {
            return new int[]{genericGFPoly.a(1)};
        }
        int[] iArr = new int[b];
        for (int i2 = 1; i2 < this.f1659a.c() && i < b; i2++) {
            if (genericGFPoly.b(i2) == 0) {
                iArr[i] = this.f1659a.c(i2);
                i++;
            }
        }
        if (i == b) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] a(GenericGFPoly genericGFPoly, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int c = this.f1659a.c(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int c2 = this.f1659a.c(iArr[i3], c);
                    i2 = this.f1659a.c(i2, (c2 & 1) == 0 ? c2 | 1 : c2 & -2);
                }
            }
            iArr2[i] = this.f1659a.c(genericGFPoly.b(c), this.f1659a.c(i2));
            if (this.f1659a.d() != 0) {
                iArr2[i] = this.f1659a.c(iArr2[i], c);
            }
        }
        return iArr2;
    }
}
