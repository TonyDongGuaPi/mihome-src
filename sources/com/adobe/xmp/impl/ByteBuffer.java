package com.adobe.xmp.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteBuffer {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f682a;
    private int b;
    private String c;

    public ByteBuffer(int i) {
        this.c = null;
        this.f682a = new byte[i];
        this.b = 0;
    }

    public ByteBuffer(InputStream inputStream) throws IOException {
        this.c = null;
        this.b = 0;
        this.f682a = new byte[16384];
        while (true) {
            int read = inputStream.read(this.f682a, this.b, 16384);
            if (read > 0) {
                this.b += read;
                if (read == 16384) {
                    c(this.b + 16384);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public ByteBuffer(byte[] bArr) {
        this.c = null;
        this.f682a = bArr;
        this.b = bArr.length;
    }

    public ByteBuffer(byte[] bArr, int i) {
        this.c = null;
        if (i <= bArr.length) {
            this.f682a = bArr;
            this.b = i;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }

    public ByteBuffer(byte[] bArr, int i, int i2) {
        this.c = null;
        if (i2 <= bArr.length - i) {
            this.f682a = new byte[i2];
            System.arraycopy(bArr, i, this.f682a, 0, i2);
            this.b = i2;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }

    private void c(int i) {
        if (i > this.f682a.length) {
            byte[] bArr = this.f682a;
            this.f682a = new byte[(bArr.length * 2)];
            System.arraycopy(bArr, 0, this.f682a, 0, bArr.length);
        }
    }

    public byte a(int i) {
        if (i < this.b) {
            return this.f682a[i];
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public InputStream a() {
        return new ByteArrayInputStream(this.f682a, 0, this.b);
    }

    public void a(byte b2) {
        c(this.b + 1);
        byte[] bArr = this.f682a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = b2;
    }

    public void a(ByteBuffer byteBuffer) {
        a(byteBuffer.f682a, 0, byteBuffer.b);
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i, int i2) {
        c(this.b + i2);
        System.arraycopy(bArr, i, this.f682a, this.b, i2);
        this.b += i2;
    }

    public int b() {
        return this.b;
    }

    public int b(int i) {
        if (i < this.b) {
            return this.f682a[i] & 255;
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public String c() {
        String str;
        if (this.c == null) {
            if (this.b >= 2) {
                if (this.f682a[0] == 0) {
                    if (this.b < 4 || this.f682a[1] != 0) {
                        str = "UTF-16BE";
                        this.c = str;
                    } else if ((this.f682a[2] & 255) == 254 && (this.f682a[3] & 255) == 255) {
                        str = "UTF-32BE";
                        this.c = str;
                    }
                } else if ((this.f682a[0] & 255) < 128) {
                    if (this.f682a[1] == 0) {
                        str = (this.b < 4 || this.f682a[2] != 0) ? "UTF-16LE" : "UTF-32LE";
                        this.c = str;
                    }
                } else if ((this.f682a[0] & 255) != 239) {
                    if ((this.f682a[0] & 255) == 254 || this.b < 4 || this.f682a[2] != 0) {
                        str = "UTF-16";
                        this.c = str;
                    }
                }
                str = "UTF-32";
                this.c = str;
            }
            str = "UTF-8";
            this.c = str;
        }
        return this.c;
    }
}
