package com.xiaomi.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement {

    /* renamed from: a  reason: collision with root package name */
    private final CharSequence f1672a;
    private final int b;
    private final int c;
    private final byte[] d;

    public DefaultPlacement(CharSequence charSequence, int i, int i2) {
        this.f1672a = charSequence;
        this.c = i;
        this.b = i2;
        this.d = new byte[(i * i2)];
        Arrays.fill(this.d, (byte) -1);
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public final byte[] c() {
        return this.d;
    }

    public final boolean a(int i, int i2) {
        return this.d[(i2 * this.c) + i] == 1;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, int i2, boolean z) {
        this.d[(i2 * this.c) + i] = z ? (byte) 1 : 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean b(int i, int i2) {
        return this.d[(i2 * this.c) + i] >= 0;
    }

    public final void d() {
        int i = 4;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i == this.b && i2 == 0) {
                a(i3);
                i3++;
            }
            if (i == this.b - 2 && i2 == 0 && this.c % 4 != 0) {
                b(i3);
                i3++;
            }
            if (i == this.b - 2 && i2 == 0 && this.c % 8 == 4) {
                c(i3);
                i3++;
            }
            if (i == this.b + 4 && i2 == 2 && this.c % 8 == 0) {
                d(i3);
                i3++;
            }
            do {
                if (i < this.b && i2 >= 0 && !b(i2, i)) {
                    a(i, i2, i3);
                    i3++;
                }
                i -= 2;
                i2 += 2;
                if (i < 0 || i2 >= this.c) {
                    int i4 = i + 1;
                    int i5 = i2 + 3;
                }
                a(i, i2, i3);
                i3++;
                i -= 2;
                i2 += 2;
                break;
            } while (i2 >= this.c);
            int i42 = i + 1;
            int i52 = i2 + 3;
            do {
                if (i42 >= 0 && i52 < this.c && !b(i52, i42)) {
                    a(i42, i52, i3);
                    i3++;
                }
                i42 += 2;
                i52 -= 2;
                if (i42 >= this.b) {
                    break;
                }
            } while (i52 >= 0);
            i = i42 + 3;
            i2 = i52 + 1;
            if (i >= this.b && i2 >= this.c) {
                break;
            }
        }
        if (!b(this.c - 1, this.b - 1)) {
            a(this.c - 1, this.b - 1, true);
            a(this.c - 2, this.b - 2, true);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += this.b;
            i2 += 4 - ((this.b + 4) % 8);
        }
        if (i2 < 0) {
            i2 += this.c;
            i += 4 - ((this.c + 4) % 8);
        }
        boolean z = true;
        if ((this.f1672a.charAt(i3) & (1 << (8 - i4))) == 0) {
            z = false;
        }
        a(i2, i, z);
    }

    private void a(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        a(i4, i5, i3, 1);
        int i6 = i2 - 1;
        a(i4, i6, i3, 2);
        int i7 = i - 1;
        a(i7, i5, i3, 3);
        a(i7, i6, i3, 4);
        a(i7, i2, i3, 5);
        a(i, i5, i3, 6);
        a(i, i6, i3, 7);
        a(i, i2, i3, 8);
    }

    private void a(int i) {
        a(this.b - 1, 0, i, 1);
        a(this.b - 1, 1, i, 2);
        a(this.b - 1, 2, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 1, i, 6);
        a(2, this.c - 1, i, 7);
        a(3, this.c - 1, i, 8);
    }

    private void b(int i) {
        a(this.b - 3, 0, i, 1);
        a(this.b - 2, 0, i, 2);
        a(this.b - 1, 0, i, 3);
        a(0, this.c - 4, i, 4);
        a(0, this.c - 3, i, 5);
        a(0, this.c - 2, i, 6);
        a(0, this.c - 1, i, 7);
        a(1, this.c - 1, i, 8);
    }

    private void c(int i) {
        a(this.b - 3, 0, i, 1);
        a(this.b - 2, 0, i, 2);
        a(this.b - 1, 0, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 1, i, 6);
        a(2, this.c - 1, i, 7);
        a(3, this.c - 1, i, 8);
    }

    private void d(int i) {
        a(this.b - 1, 0, i, 1);
        a(this.b - 1, this.c - 1, i, 2);
        a(0, this.c - 3, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 3, i, 6);
        a(1, this.c - 2, i, 7);
        a(1, this.c - 1, i, 8);
    }
}
