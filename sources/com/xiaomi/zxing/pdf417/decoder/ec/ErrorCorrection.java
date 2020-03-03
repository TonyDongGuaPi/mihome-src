package com.xiaomi.zxing.pdf417.decoder.ec;

import com.xiaomi.zxing.ChecksumException;

public final class ErrorCorrection {

    /* renamed from: a  reason: collision with root package name */
    private final ModulusGF f1744a = ModulusGF.f1745a;

    public int a(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        ModulusPoly modulusPoly = new ModulusPoly(this.f1744a, iArr);
        int[] iArr3 = new int[i];
        int i2 = 0;
        boolean z = false;
        for (int i3 = i; i3 > 0; i3--) {
            int b = modulusPoly.b(this.f1744a.a(i3));
            iArr3[i - i3] = b;
            if (b != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        ModulusPoly b2 = this.f1744a.b();
        if (iArr2 != null) {
            ModulusPoly modulusPoly2 = b2;
            for (int length : iArr2) {
                modulusPoly2 = modulusPoly2.c(new ModulusPoly(this.f1744a, new int[]{this.f1744a.c(0, this.f1744a.a((iArr.length - 1) - length)), 1}));
            }
        }
        ModulusPoly[] a2 = a(this.f1744a.a(i, 1), new ModulusPoly(this.f1744a, iArr3), i);
        ModulusPoly modulusPoly3 = a2[0];
        ModulusPoly modulusPoly4 = a2[1];
        int[] a3 = a(modulusPoly3);
        int[] a4 = a(modulusPoly4, modulusPoly3, a3);
        while (i2 < a3.length) {
            int length2 = (iArr.length - 1) - this.f1744a.b(a3[i2]);
            if (length2 >= 0) {
                iArr[length2] = this.f1744a.c(iArr[length2], a4[i2]);
                i2++;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return a3.length;
    }

    private ModulusPoly[] a(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int i) throws ChecksumException {
        if (modulusPoly.b() < modulusPoly2.b()) {
            ModulusPoly modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
        }
        ModulusPoly a2 = this.f1744a.a();
        ModulusPoly b = this.f1744a.b();
        ModulusPoly modulusPoly4 = modulusPoly2;
        ModulusPoly modulusPoly5 = modulusPoly;
        ModulusPoly modulusPoly6 = modulusPoly4;
        while (modulusPoly6.b() >= i / 2) {
            if (!modulusPoly6.c()) {
                ModulusPoly a3 = this.f1744a.a();
                int c = this.f1744a.c(modulusPoly6.a(modulusPoly6.b()));
                while (modulusPoly5.b() >= modulusPoly6.b() && !modulusPoly5.c()) {
                    int b2 = modulusPoly5.b() - modulusPoly6.b();
                    int d = this.f1744a.d(modulusPoly5.a(modulusPoly5.b()), c);
                    a3 = a3.a(this.f1744a.a(b2, d));
                    modulusPoly5 = modulusPoly5.b(modulusPoly6.a(b2, d));
                }
                ModulusPoly modulusPoly7 = modulusPoly5;
                modulusPoly5 = modulusPoly6;
                modulusPoly6 = modulusPoly7;
                ModulusPoly modulusPoly8 = b;
                b = a3.c(b).b(a2).d();
                a2 = modulusPoly8;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        int a4 = b.a(0);
        if (a4 != 0) {
            int c2 = this.f1744a.c(a4);
            return new ModulusPoly[]{b.c(c2), modulusPoly6.c(c2)};
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] a(ModulusPoly modulusPoly) throws ChecksumException {
        int b = modulusPoly.b();
        int[] iArr = new int[b];
        int i = 0;
        for (int i2 = 1; i2 < this.f1744a.c() && i < b; i2++) {
            if (modulusPoly.b(i2) == 0) {
                iArr[i] = this.f1744a.c(i2);
                i++;
            }
        }
        if (i == b) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] a(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] iArr) {
        int b = modulusPoly2.b();
        int[] iArr2 = new int[b];
        for (int i = 1; i <= b; i++) {
            iArr2[b - i] = this.f1744a.d(i, modulusPoly2.a(i));
        }
        ModulusPoly modulusPoly3 = new ModulusPoly(this.f1744a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int c = this.f1744a.c(iArr[i2]);
            iArr3[i2] = this.f1744a.d(this.f1744a.c(0, modulusPoly.b(c)), this.f1744a.c(modulusPoly3.b(c)));
        }
        return iArr3;
    }
}
