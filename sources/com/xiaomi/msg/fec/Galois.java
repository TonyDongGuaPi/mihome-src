package com.xiaomi.msg.fec;

public class Galois {
    private static volatile Galois f;

    /* renamed from: a  reason: collision with root package name */
    private int f12110a = 8;
    private int b = (1 << this.f12110a);
    private int c = 285;
    private int[] d = new int[((int) Math.pow(2.0d, (double) this.f12110a))];
    private int[] e = new int[((int) Math.pow(2.0d, (double) this.f12110a))];

    public int a(int i, int i2) {
        return i ^ i2;
    }

    public int b(int i, int i2) {
        return i ^ i2;
    }

    private Galois() {
        this.d[0] = 1;
        this.d[this.b - 1] = this.d[0];
        this.e[0] = 0;
        this.e[1] = 0;
        for (int i = 1; i < this.b - 1; i++) {
            int i2 = i - 1;
            this.d[i] = ((0 - (this.d[i2] >> (this.f12110a - 1))) & this.c) ^ (this.d[i2] << 1);
            this.e[this.d[i]] = i;
        }
    }

    public static Galois a() {
        if (f == null) {
            synchronized (Galois.class) {
                if (f == null) {
                    f = new Galois();
                }
            }
        }
        return f;
    }

    public int a(int i) {
        return this.d[i % (this.b - 1)];
    }

    public int b(int i) {
        return this.e[i];
    }

    public int c(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return a(this.e[i] + this.e[i2]);
    }

    public int d(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return i / i2;
        }
        return a(((this.b - 1) + this.e[i]) - this.e[i2]);
    }

    public int e(int i, int i2) {
        if (i2 == 0) {
            return 1;
        }
        int i3 = i;
        for (int i4 = 1; i4 < i2; i4++) {
            i3 = c(i3, i);
        }
        return i3;
    }
}
