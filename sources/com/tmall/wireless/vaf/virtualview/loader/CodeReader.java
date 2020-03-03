package com.tmall.wireless.vaf.virtualview.loader;

import android.util.Log;

public class CodeReader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9399a = "CodeReader_TMTEST";
    private int b;
    private byte[] c;
    private int d;
    private int e;

    public void a(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
        if (this.c != null) {
            this.e = this.c.length;
        } else {
            this.e = 0;
        }
        this.d = 0;
    }

    public boolean b(int i) {
        return c(this.d + i);
    }

    public boolean c(int i) {
        if (i > this.e) {
            this.d = this.e;
            return false;
        } else if (i < 0) {
            this.d = 0;
            return false;
        } else {
            this.d = i;
            return true;
        }
    }

    public void b() {
        if (this.c != null) {
            this.c = null;
        }
    }

    public byte[] c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean f() {
        return this.d == this.e;
    }

    public byte g() {
        if (this.c == null || this.d >= this.e) {
            Log.e(f9399a, "readByte error mCode:" + this.c + "  mCurIndex:" + this.d + "  mCount:" + this.e);
            return -1;
        }
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        return bArr[i];
    }

    public short h() {
        if (this.c == null || this.d >= this.e - 1) {
            Log.e(f9399a, "readShort error mCode:" + this.c + "  mCurIndex:" + this.d + "  mCount:" + this.e);
            return -1;
        }
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        byte[] bArr2 = this.c;
        int i2 = this.d;
        this.d = i2 + 1;
        return (short) (((bArr[i] & 255) << 8) | (bArr2[i2] & 255));
    }

    public int i() {
        if (this.c == null || this.d >= this.e - 3) {
            Log.e(f9399a, "readInt error mCode:" + this.c + "  mCurIndex:" + this.d + "  mCount:" + this.e);
            return -1;
        }
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        byte[] bArr2 = this.c;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b2 = ((bArr[i] & 255) << 24) | ((bArr2[i2] & 255) << 16);
        byte[] bArr3 = this.c;
        int i3 = this.d;
        this.d = i3 + 1;
        byte b3 = b2 | ((bArr3[i3] & 255) << 8);
        byte[] bArr4 = this.c;
        int i4 = this.d;
        this.d = i4 + 1;
        return b3 | (bArr4[i4] & 255);
    }
}
