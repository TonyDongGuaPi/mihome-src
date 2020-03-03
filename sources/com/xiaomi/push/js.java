package com.xiaomi.push;

public class js extends ju {

    /* renamed from: a  reason: collision with root package name */
    private jc f12833a;
    private int b;

    public js(int i) {
        this.f12833a = new jc(i);
    }

    public int I_() {
        return this.f12833a.size();
    }

    public int a(byte[] bArr, int i, int i2) {
        byte[] a2 = this.f12833a.a();
        if (i2 > this.f12833a.b() - this.b) {
            i2 = this.f12833a.b() - this.b;
        }
        if (i2 > 0) {
            System.arraycopy(a2, this.b, bArr, i, i2);
            this.b += i2;
        }
        return i2;
    }

    public void b(byte[] bArr, int i, int i2) {
        this.f12833a.write(bArr, i, i2);
    }
}
