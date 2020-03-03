package com.libra.sinvoice;

public class BufferData {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f6241a;
    private int b;
    private int c;

    public BufferData(int i) {
        this.c = i;
        a();
        if (i > 0) {
            this.c = i;
            this.f6241a = new byte[this.c];
            return;
        }
        this.f6241a = null;
    }

    public void a(byte[] bArr, int i) {
        if (i <= this.c) {
            for (int i2 = 0; i2 < i; i2++) {
                this.f6241a[i2] = bArr[i2];
            }
            this.b = i;
        }
    }

    public final void a() {
        this.b = 0;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final int b() {
        return this.b;
    }
}
