package com.xiaomi.smarthome.library.apache.http.util;

public final class ByteArrayBuffer {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f18449a;
    private int b;

    public ByteArrayBuffer(int i) {
        if (i >= 0) {
            this.f18449a = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Buffer capacity may not be negative");
    }

    private void d(int i) {
        byte[] bArr = new byte[Math.max(this.f18449a.length << 1, i)];
        System.arraycopy(this.f18449a, 0, bArr, 0, this.b);
        this.f18449a = bArr;
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                int i4 = this.b + i2;
                if (i4 > this.f18449a.length) {
                    d(i4);
                }
                System.arraycopy(bArr, i, this.f18449a, this.b, i2);
                this.b = i4;
            }
        }
    }

    public void a(int i) {
        int i2 = this.b + 1;
        if (i2 > this.f18449a.length) {
            d(i2);
        }
        this.f18449a[this.b] = (byte) i;
        this.b = i2;
    }

    public void a(char[] cArr, int i, int i2) {
        int i3;
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > cArr.length) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                int i4 = this.b;
                int i5 = i2 + i4;
                if (i5 > this.f18449a.length) {
                    d(i5);
                }
                while (i4 < i5) {
                    this.f18449a[i4] = (byte) cArr[i];
                    i++;
                    i4++;
                }
                this.b = i5;
            }
        }
    }

    public void a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            a(charArrayBuffer.c(), i, i2);
        }
    }

    public void a() {
        this.b = 0;
    }

    public byte[] b() {
        byte[] bArr = new byte[this.b];
        if (this.b > 0) {
            System.arraycopy(this.f18449a, 0, bArr, 0, this.b);
        }
        return bArr;
    }

    public int b(int i) {
        return this.f18449a[i];
    }

    public int c() {
        return this.f18449a.length;
    }

    public int d() {
        return this.b;
    }

    public byte[] e() {
        return this.f18449a;
    }

    public void c(int i) {
        if (i < 0 || i > this.f18449a.length) {
            throw new IndexOutOfBoundsException();
        }
        this.b = i;
    }

    public boolean f() {
        return this.b == 0;
    }

    public boolean g() {
        return this.b == this.f18449a.length;
    }
}
