package com.xiaomi.zxing.pdf417.decoder;

import java.util.Formatter;

final class DetectionResult {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1739a = 2;
    private final BarcodeMetadata b;
    private final DetectionResultColumn[] c = new DetectionResultColumn[(this.e + 2)];
    private BoundingBox d;
    private final int e;

    DetectionResult(BarcodeMetadata barcodeMetadata, BoundingBox boundingBox) {
        this.b = barcodeMetadata;
        this.e = barcodeMetadata.a();
        this.d = boundingBox;
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn[] a() {
        a(this.c[0]);
        a(this.c[this.e + 1]);
        int i = 928;
        while (true) {
            int f = f();
            if (f > 0 && f < i) {
                i = f;
            }
        }
        return this.c;
    }

    private void a(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).a(this.b);
        }
    }

    private int f() {
        int g = g();
        if (g == 0) {
            return 0;
        }
        for (int i = 1; i < this.e + 1; i++) {
            Codeword[] b2 = this.c[i].b();
            for (int i2 = 0; i2 < b2.length; i2++) {
                if (b2[i2] != null && !b2[i2].a()) {
                    a(i, i2, b2);
                }
            }
        }
        return g;
    }

    private int g() {
        h();
        return j() + i();
    }

    private void h() {
        if (this.c[0] != null && this.c[this.e + 1] != null) {
            Codeword[] b2 = this.c[0].b();
            Codeword[] b3 = this.c[this.e + 1].b();
            for (int i = 0; i < b2.length; i++) {
                if (!(b2[i] == null || b3[i] == null || b2[i].h() != b3[i].h())) {
                    for (int i2 = 1; i2 <= this.e; i2++) {
                        Codeword codeword = this.c[i2].b()[i];
                        if (codeword != null) {
                            codeword.b(b2[i].h());
                            if (!codeword.a()) {
                                this.c[i2].b()[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int i() {
        if (this.c[this.e + 1] == null) {
            return 0;
        }
        Codeword[] b2 = this.c[this.e + 1].b();
        int i = 0;
        for (int i2 = 0; i2 < b2.length; i2++) {
            if (b2[i2] != null) {
                int h = b2[i2].h();
                int i3 = i;
                int i4 = 0;
                for (int i5 = this.e + 1; i5 > 0 && i4 < 2; i5--) {
                    Codeword codeword = this.c[i5].b()[i2];
                    if (codeword != null) {
                        i4 = a(h, i4, codeword);
                        if (!codeword.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private int j() {
        if (this.c[0] == null) {
            return 0;
        }
        Codeword[] b2 = this.c[0].b();
        int i = 0;
        for (int i2 = 0; i2 < b2.length; i2++) {
            if (b2[i2] != null) {
                int h = b2[i2].h();
                int i3 = i;
                int i4 = 0;
                for (int i5 = 1; i5 < this.e + 1 && i4 < 2; i5++) {
                    Codeword codeword = this.c[i5].b()[i2];
                    if (codeword != null) {
                        i4 = a(h, i4, codeword);
                        if (!codeword.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private static int a(int i, int i2, Codeword codeword) {
        if (codeword == null || codeword.a()) {
            return i2;
        }
        if (!codeword.a(i)) {
            return i2 + 1;
        }
        codeword.b(i);
        return 0;
    }

    private void a(int i, int i2, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i2];
        Codeword[] b2 = this.c[i - 1].b();
        int i3 = i + 1;
        Codeword[] b3 = this.c[i3] != null ? this.c[i3].b() : b2;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = b2[i2];
        codewordArr2[3] = b3[i2];
        int i4 = 0;
        if (i2 > 0) {
            int i5 = i2 - 1;
            codewordArr2[0] = codewordArr[i5];
            codewordArr2[4] = b2[i5];
            codewordArr2[5] = b3[i5];
        }
        if (i2 > 1) {
            int i6 = i2 - 2;
            codewordArr2[8] = codewordArr[i6];
            codewordArr2[10] = b2[i6];
            codewordArr2[11] = b3[i6];
        }
        if (i2 < codewordArr.length - 1) {
            int i7 = i2 + 1;
            codewordArr2[1] = codewordArr[i7];
            codewordArr2[6] = b2[i7];
            codewordArr2[7] = b3[i7];
        }
        if (i2 < codewordArr.length - 2) {
            int i8 = i2 + 2;
            codewordArr2[9] = codewordArr[i8];
            codewordArr2[12] = b2[i8];
            codewordArr2[13] = b3[i8];
        }
        int length = codewordArr2.length;
        while (i4 < length && !a(codeword, codewordArr2[i4])) {
            i4++;
        }
    }

    private static boolean a(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.a() || codeword2.f() != codeword.f()) {
            return false;
        }
        codeword.b(codeword2.h());
        return true;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.b.c();
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.b.b();
    }

    public void a(BoundingBox boundingBox) {
        this.d = boundingBox;
    }

    /* access modifiers changed from: package-private */
    public BoundingBox e() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public void a(int i, DetectionResultColumn detectionResultColumn) {
        this.c[i] = detectionResultColumn;
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn a(int i) {
        return this.c[i];
    }

    public String toString() {
        DetectionResultColumn detectionResultColumn = this.c[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = this.c[this.e + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < detectionResultColumn.b().length; i++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
            for (int i2 = 0; i2 < this.e + 2; i2++) {
                if (this.c[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    Codeword codeword = this.c[i2].b()[i];
                    if (codeword == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(codeword.h()), Integer.valueOf(codeword.g())});
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
