package org.jacoco.agent.rt.internal_8ff85ea.asm;

public class Label {

    /* renamed from: a  reason: collision with root package name */
    static final int f3596a = 1;
    static final int b = 2;
    static final int c = 4;
    static final int d = 8;
    static final int e = 16;
    static final int f = 32;
    static final int g = 64;
    static final int h = 128;
    static final int i = 256;
    static final int j = 512;
    static final int k = 1024;
    static final int l = 2048;
    public Object m;
    int n;
    int o;
    int p;
    int q;
    int r;
    Frame s;
    Label t;
    Edge u;
    Label v;
    private int w;
    private int[] x;

    public int a() {
        if ((this.n & 2) != 0) {
            return this.p;
        }
        throw new IllegalStateException("Label offset position has not been resolved yet");
    }

    /* access modifiers changed from: package-private */
    public void a(MethodWriter methodWriter, ByteVector byteVector, int i2, boolean z) {
        if ((this.n & 2) == 0) {
            if (z) {
                a(-1 - i2, byteVector.b);
                byteVector.c(-1);
                return;
            }
            a(i2, byteVector.b);
            byteVector.b(-1);
        } else if (z) {
            byteVector.c(this.p - i2);
        } else {
            byteVector.b(this.p - i2);
        }
    }

    private void a(int i2, int i3) {
        if (this.x == null) {
            this.x = new int[6];
        }
        if (this.w >= this.x.length) {
            int[] iArr = new int[(this.x.length + 6)];
            System.arraycopy(this.x, 0, iArr, 0, this.x.length);
            this.x = iArr;
        }
        int[] iArr2 = this.x;
        int i4 = this.w;
        this.w = i4 + 1;
        iArr2[i4] = i2;
        int[] iArr3 = this.x;
        int i5 = this.w;
        this.w = i5 + 1;
        iArr3[i5] = i3;
    }

    /* access modifiers changed from: package-private */
    public boolean a(MethodWriter methodWriter, int i2, byte[] bArr) {
        this.n |= 2;
        this.p = i2;
        int i3 = 0;
        boolean z = false;
        while (i3 < this.w) {
            int i4 = i3 + 1;
            int i5 = this.x[i3];
            int i6 = i4 + 1;
            int i7 = this.x[i4];
            if (i5 >= 0) {
                int i8 = i2 - i5;
                if (i8 < -32768 || i8 > 32767) {
                    int i9 = i7 - 1;
                    byte b2 = bArr[i9] & 255;
                    if (b2 <= 168) {
                        bArr[i9] = (byte) (b2 + 49);
                    } else {
                        bArr[i9] = (byte) (b2 + 20);
                    }
                    z = true;
                }
                bArr[i7] = (byte) (i8 >>> 8);
                bArr[i7 + 1] = (byte) i8;
            } else {
                int i10 = i5 + i2 + 1;
                int i11 = i7 + 1;
                bArr[i7] = (byte) (i10 >>> 24);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (i10 >>> 16);
                bArr[i12] = (byte) (i10 >>> 8);
                bArr[i12 + 1] = (byte) i10;
            }
            i3 = i6;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public Label b() {
        return this.s == null ? this : this.s.x;
    }

    /* access modifiers changed from: package-private */
    public boolean a(long j2) {
        if ((this.n & 1024) == 0) {
            return false;
        }
        if ((((int) j2) & this.x[(int) (j2 >>> 32)]) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Label label) {
        if ((this.n & 1024) == 0 || (label.n & 1024) == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.x.length; i2++) {
            if ((this.x[i2] & label.x[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(long j2, int i2) {
        if ((this.n & 1024) == 0) {
            this.n |= 1024;
            this.x = new int[((i2 / 32) + 1)];
        }
        int[] iArr = this.x;
        int i3 = (int) (j2 >>> 32);
        iArr[i3] = ((int) j2) | iArr[i3];
    }

    /* access modifiers changed from: package-private */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    public void a(org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5, long r6, int r8) {
        /*
            r4 = this;
            r0 = r4
        L_0x0001:
            if (r0 == 0) goto L_0x0063
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r0.v
            r2 = 0
            r0.v = r2
            if (r5 == 0) goto L_0x0039
            int r2 = r0.n
            r2 = r2 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x0011
            goto L_0x003f
        L_0x0011:
            int r2 = r0.n
            r2 = r2 | 2048(0x800, float:2.87E-42)
            r0.n = r2
            int r2 = r0.n
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x0044
            boolean r2 = r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r5)
            if (r2 != 0) goto L_0x0044
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r2 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Edge
            r2.<init>()
            int r3 = r0.q
            r2.c = r3
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r3 = r5.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r3.d
            r2.d = r3
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r3 = r0.u
            r2.e = r3
            r0.u = r2
            goto L_0x0044
        L_0x0039:
            boolean r2 = r0.a((long) r6)
            if (r2 == 0) goto L_0x0041
        L_0x003f:
            r0 = r1
            goto L_0x0001
        L_0x0041:
            r0.a((long) r6, (int) r8)
        L_0x0044:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r2 = r0.u
        L_0x0046:
            if (r2 == 0) goto L_0x003f
            int r3 = r0.n
            r3 = r3 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x0054
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r3 = r0.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r3 = r3.e
            if (r2 == r3) goto L_0x0060
        L_0x0054:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r2.d
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r3.v
            if (r3 != 0) goto L_0x0060
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r2.d
            r3.v = r1
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r2.d
        L_0x0060:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r2 = r2.e
            goto L_0x0046
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.Label.a(org.jacoco.agent.rt.internal_8ff85ea.asm.Label, long, int):void");
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}
