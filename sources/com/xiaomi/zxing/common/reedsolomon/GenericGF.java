package com.xiaomi.zxing.common.reedsolomon;

import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.taobao.weex.el.parse.Operators;

public final class GenericGF {

    /* renamed from: a  reason: collision with root package name */
    public static final GenericGF f1657a = new GenericGF(4201, 4096, 1);
    public static final GenericGF b = new GenericGF(PhotoshopDirectory.F, 1024, 1);
    public static final GenericGF c = new GenericGF(67, 64, 1);
    public static final GenericGF d = new GenericGF(19, 16, 1);
    public static final GenericGF e = new GenericGF(285, 256, 0);
    public static final GenericGF f = new GenericGF(301, 256, 1);
    public static final GenericGF g = f;
    public static final GenericGF h = c;
    private final int[] i;
    private final int[] j;
    private final GenericGFPoly k;
    private final GenericGFPoly l;
    private final int m;
    private final int n;
    private final int o;

    static int b(int i2, int i3) {
        return i2 ^ i3;
    }

    public GenericGF(int i2, int i3, int i4) {
        this.n = i2;
        this.m = i3;
        this.o = i4;
        this.i = new int[i3];
        this.j = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.i[i6] = i5;
            i5 *= 2;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.j[this.i[i7]] = i7;
        }
        this.k = new GenericGFPoly(this, new int[]{0});
        this.l = new GenericGFPoly(this, new int[]{1});
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly b() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (i3 == 0) {
            return this.k;
        } else {
            int[] iArr = new int[(i2 + 1)];
            iArr[0] = i3;
            return new GenericGFPoly(this, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i2) {
        return this.i[i2];
    }

    /* access modifiers changed from: package-private */
    public int b(int i2) {
        if (i2 != 0) {
            return this.j[i2];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int c(int i2) {
        if (i2 != 0) {
            return this.i[(this.m - this.j[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: package-private */
    public int c(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        return this.i[(this.j[i2] + this.j[i3]) % (this.m - 1)];
    }

    public int c() {
        return this.m;
    }

    public int d() {
        return this.o;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.n) + ',' + this.m + Operators.BRACKET_END;
    }
}
