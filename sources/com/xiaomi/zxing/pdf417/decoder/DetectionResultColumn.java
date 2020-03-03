package com.xiaomi.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1740a = 5;
    private final BoundingBox b;
    private final Codeword[] c;

    DetectionResultColumn(BoundingBox boundingBox) {
        this.b = new BoundingBox(boundingBox);
        this.c = new Codeword[((boundingBox.d() - boundingBox.c()) + 1)];
    }

    /* access modifiers changed from: package-private */
    public final Codeword a(int i) {
        Codeword codeword;
        Codeword codeword2;
        Codeword c2 = c(i);
        if (c2 != null) {
            return c2;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int b2 = b(i) - i2;
            if (b2 >= 0 && (codeword2 = this.c[b2]) != null) {
                return codeword2;
            }
            int b3 = b(i) + i2;
            if (b3 < this.c.length && (codeword = this.c[b3]) != null) {
                return codeword;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final int b(int i) {
        return i - this.b.c();
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, Codeword codeword) {
        this.c[b(i)] = codeword;
    }

    /* access modifiers changed from: package-private */
    public final Codeword c(int i) {
        return this.c[b(i)];
    }

    /* access modifiers changed from: package-private */
    public final BoundingBox a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final Codeword[] b() {
        return this.c;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        int i = 0;
        for (Codeword codeword : this.c) {
            if (codeword == null) {
                formatter.format("%3d:    |   %n", new Object[]{Integer.valueOf(i)});
                i++;
            } else {
                formatter.format("%3d: %3d|%3d%n", new Object[]{Integer.valueOf(i), Integer.valueOf(codeword.h()), Integer.valueOf(codeword.g())});
                i++;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
