package com.xiaomi.zxing.common.reedsolomon;

final class GenericGFPoly {

    /* renamed from: a  reason: collision with root package name */
    private final GenericGF f1658a;
    private final int[] b;

    GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length != 0) {
            this.f1658a = genericGF;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.b = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.b = new int[]{0};
                return;
            }
            this.b = new int[(length - i)];
            System.arraycopy(iArr, i, this.b, 0, this.b.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int[] a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.b.length - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.b[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    /* access modifiers changed from: package-private */
    public int b(int i) {
        if (i == 0) {
            return a(0);
        }
        int length = this.b.length;
        if (i == 1) {
            int i2 = 0;
            for (int b2 : this.b) {
                i2 = GenericGF.b(i2, b2);
            }
            return i2;
        }
        int i3 = this.b[0];
        for (int i4 = 1; i4 < length; i4++) {
            i3 = GenericGF.b(this.f1658a.c(i, i3), this.b[i4]);
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a(GenericGFPoly genericGFPoly) {
        if (!this.f1658a.equals(genericGFPoly.f1658a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.c()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = genericGFPoly.b;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = GenericGF.b(iArr[i - length], iArr2[i]);
            }
            return new GenericGFPoly(this.f1658a, iArr4);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly b(GenericGFPoly genericGFPoly) {
        if (!this.f1658a.equals(genericGFPoly.f1658a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c() || genericGFPoly.c()) {
            return this.f1658a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = genericGFPoly.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = GenericGF.b(iArr3[i4], this.f1658a.c(i2, iArr2[i3]));
                }
            }
            return new GenericGFPoly(this.f1658a, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly c(int i) {
        if (i == 0) {
            return this.f1658a.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f1658a.c(this.b[i2], i);
        }
        return new GenericGFPoly(this.f1658a, iArr);
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f1658a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f1658a.c(this.b[i3], i2);
            }
            return new GenericGFPoly(this.f1658a, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly[] c(GenericGFPoly genericGFPoly) {
        if (!this.f1658a.equals(genericGFPoly.f1658a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!genericGFPoly.c()) {
            GenericGFPoly a2 = this.f1658a.a();
            int c = this.f1658a.c(genericGFPoly.a(genericGFPoly.b()));
            GenericGFPoly genericGFPoly2 = a2;
            GenericGFPoly genericGFPoly3 = this;
            while (genericGFPoly3.b() >= genericGFPoly.b() && !genericGFPoly3.c()) {
                int b2 = genericGFPoly3.b() - genericGFPoly.b();
                int c2 = this.f1658a.c(genericGFPoly3.a(genericGFPoly3.b()), c);
                GenericGFPoly a3 = genericGFPoly.a(b2, c2);
                genericGFPoly2 = genericGFPoly2.a(this.f1658a.a(b2, c2));
                genericGFPoly3 = genericGFPoly3.a(a3);
            }
            return new GenericGFPoly[]{genericGFPoly2, genericGFPoly3};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int b2 = b(); b2 >= 0; b2--) {
            int a2 = a(b2);
            if (a2 != 0) {
                if (a2 < 0) {
                    sb.append(" - ");
                    a2 = -a2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b2 == 0 || a2 != 1) {
                    int b3 = this.f1658a.b(a2);
                    if (b3 == 0) {
                        sb.append('1');
                    } else if (b3 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(b3);
                    }
                }
                if (b2 != 0) {
                    if (b2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b2);
                    }
                }
            }
        }
        return sb.toString();
    }
}
