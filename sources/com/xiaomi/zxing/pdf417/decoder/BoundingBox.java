package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;

final class BoundingBox {

    /* renamed from: a  reason: collision with root package name */
    private BitMatrix f1735a;
    private ResultPoint b;
    private ResultPoint c;
    private ResultPoint d;
    private ResultPoint e;
    private int f;
    private int g;
    private int h;
    private int i;

    BoundingBox(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        if (!(resultPoint == null && resultPoint3 == null) && (!(resultPoint2 == null && resultPoint4 == null) && ((resultPoint == null || resultPoint2 != null) && (resultPoint3 == null || resultPoint4 != null)))) {
            a(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    BoundingBox(BoundingBox boundingBox) {
        a(boundingBox.f1735a, boundingBox.b, boundingBox.c, boundingBox.d, boundingBox.e);
    }

    private void a(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        this.f1735a = bitMatrix;
        this.b = resultPoint;
        this.c = resultPoint2;
        this.d = resultPoint3;
        this.e = resultPoint4;
        i();
    }

    static BoundingBox a(BoundingBox boundingBox, BoundingBox boundingBox2) throws NotFoundException {
        if (boundingBox == null) {
            return boundingBox2;
        }
        return boundingBox2 == null ? boundingBox : new BoundingBox(boundingBox.f1735a, boundingBox.b, boundingBox.c, boundingBox2.d, boundingBox2.e);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.pdf417.decoder.BoundingBox a(int r13, int r14, boolean r15) throws com.xiaomi.zxing.NotFoundException {
        /*
            r12 = this;
            com.xiaomi.zxing.ResultPoint r0 = r12.b
            com.xiaomi.zxing.ResultPoint r1 = r12.c
            com.xiaomi.zxing.ResultPoint r2 = r12.d
            com.xiaomi.zxing.ResultPoint r3 = r12.e
            if (r13 <= 0) goto L_0x002b
            if (r15 == 0) goto L_0x000f
            com.xiaomi.zxing.ResultPoint r4 = r12.b
            goto L_0x0011
        L_0x000f:
            com.xiaomi.zxing.ResultPoint r4 = r12.d
        L_0x0011:
            float r5 = r4.b()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L_0x001a
            r5 = 0
        L_0x001a:
            com.xiaomi.zxing.ResultPoint r13 = new com.xiaomi.zxing.ResultPoint
            float r4 = r4.a()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L_0x0028
            r8 = r13
            goto L_0x002c
        L_0x0028:
            r10 = r13
            r8 = r0
            goto L_0x002d
        L_0x002b:
            r8 = r0
        L_0x002c:
            r10 = r2
        L_0x002d:
            if (r14 <= 0) goto L_0x005d
            if (r15 == 0) goto L_0x0034
            com.xiaomi.zxing.ResultPoint r13 = r12.c
            goto L_0x0036
        L_0x0034:
            com.xiaomi.zxing.ResultPoint r13 = r12.e
        L_0x0036:
            float r0 = r13.b()
            int r0 = (int) r0
            int r0 = r0 + r14
            com.xiaomi.zxing.common.BitMatrix r14 = r12.f1735a
            int r14 = r14.g()
            if (r0 < r14) goto L_0x004c
            com.xiaomi.zxing.common.BitMatrix r14 = r12.f1735a
            int r14 = r14.g()
            int r0 = r14 + -1
        L_0x004c:
            com.xiaomi.zxing.ResultPoint r14 = new com.xiaomi.zxing.ResultPoint
            float r13 = r13.a()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L_0x005a
            r9 = r14
            goto L_0x005e
        L_0x005a:
            r11 = r14
            r9 = r1
            goto L_0x005f
        L_0x005d:
            r9 = r1
        L_0x005e:
            r11 = r3
        L_0x005f:
            r12.i()
            com.xiaomi.zxing.pdf417.decoder.BoundingBox r13 = new com.xiaomi.zxing.pdf417.decoder.BoundingBox
            com.xiaomi.zxing.common.BitMatrix r7 = r12.f1735a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.decoder.BoundingBox.a(int, int, boolean):com.xiaomi.zxing.pdf417.decoder.BoundingBox");
    }

    private void i() {
        if (this.b == null) {
            this.b = new ResultPoint(0.0f, this.d.b());
            this.c = new ResultPoint(0.0f, this.e.b());
        } else if (this.d == null) {
            this.d = new ResultPoint((float) (this.f1735a.f() - 1), this.b.b());
            this.e = new ResultPoint((float) (this.f1735a.f() - 1), this.c.b());
        }
        this.f = (int) Math.min(this.b.a(), this.c.a());
        this.g = (int) Math.max(this.d.a(), this.e.a());
        this.h = (int) Math.min(this.b.b(), this.d.b());
        this.i = (int) Math.max(this.c.b(), this.e.b());
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint e() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint f() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint g() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint h() {
        return this.e;
    }
}
