package com.xiaomi.zxing.pdf417.decoder.ec;

final class ModulusPoly {

    /* renamed from: a  reason: collision with root package name */
    private final ModulusGF f1746a;
    private final int[] b;

    ModulusPoly(ModulusGF modulusGF, int[] iArr) {
        if (iArr.length != 0) {
            this.f1746a = modulusGF;
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
                i2 = this.f1746a.b(i2, b2);
            }
            return i2;
        }
        int i3 = this.b[0];
        for (int i4 = 1; i4 < length; i4++) {
            i3 = this.f1746a.b(this.f1746a.d(i, i3), this.b[i4]);
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly a(ModulusPoly modulusPoly) {
        if (!this.f1746a.equals(modulusPoly.f1746a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (c()) {
            return modulusPoly;
        } else {
            if (modulusPoly.c()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = modulusPoly.b;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = this.f1746a.b(iArr[i - length], iArr2[i]);
            }
            return new ModulusPoly(this.f1746a, iArr4);
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly b(ModulusPoly modulusPoly) {
        if (!this.f1746a.equals(modulusPoly.f1746a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (modulusPoly.c()) {
            return this;
        } else {
            return a(modulusPoly.d());
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly c(ModulusPoly modulusPoly) {
        if (!this.f1746a.equals(modulusPoly.f1746a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (c() || modulusPoly.c()) {
            return this.f1746a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = modulusPoly.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = this.f1746a.b(iArr3[i4], this.f1746a.d(i2, iArr2[i3]));
                }
            }
            return new ModulusPoly(this.f1746a, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly d() {
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.f1746a.c(0, this.b[i]);
        }
        return new ModulusPoly(this.f1746a, iArr);
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly c(int i) {
        if (i == 0) {
            return this.f1746a.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f1746a.d(this.b[i2], i);
        }
        return new ModulusPoly(this.f1746a, iArr);
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f1746a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f1746a.d(this.b[i3], i2);
            }
            return new ModulusPoly(this.f1746a, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly[] d(ModulusPoly modulusPoly) {
        if (!this.f1746a.equals(modulusPoly.f1746a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (!modulusPoly.c()) {
            ModulusPoly a2 = this.f1746a.a();
            int c = this.f1746a.c(modulusPoly.a(modulusPoly.b()));
            ModulusPoly modulusPoly2 = a2;
            ModulusPoly modulusPoly3 = this;
            while (modulusPoly3.b() >= modulusPoly.b() && !modulusPoly3.c()) {
                int b2 = modulusPoly3.b() - modulusPoly.b();
                int d = this.f1746a.d(modulusPoly3.a(modulusPoly3.b()), c);
                ModulusPoly a3 = modulusPoly.a(b2, d);
                modulusPoly2 = modulusPoly2.a(this.f1746a.a(b2, d));
                modulusPoly3 = modulusPoly3.b(a3);
            }
            return new ModulusPoly[]{modulusPoly2, modulusPoly3};
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
                    sb.append(a2);
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
