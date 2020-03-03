package com.tmall.wireless.vaf.expr.engine;

import android.util.Log;
import com.libra.expr.common.ExprCode;

public class CodeReader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9345a = "CodeReader";
    private ExprCode b;
    private int c;
    private int d;

    public void a(ExprCode exprCode) {
        this.b = exprCode;
        this.d = this.b.b;
        this.c = this.d;
    }

    public void a() {
        if (this.b != null) {
            this.b = null;
        }
    }

    public int b() {
        return this.c - this.d;
    }

    public void a(int i) {
        this.c = this.d + i;
    }

    public boolean c() {
        return this.c == this.b.c;
    }

    public byte d() {
        if (this.b == null || this.c >= this.b.c) {
            Log.e(f9345a, "readByte error mCode:" + this.b + "  mCurIndex:" + this.c);
            return 0;
        }
        byte[] bArr = this.b.f6240a;
        int i = this.c;
        this.c = i + 1;
        return bArr[i];
    }

    public short e() {
        if (this.b == null || this.c >= this.b.c - 1) {
            Log.e(f9345a, "readShort error mCode:" + this.b + "  mCurIndex:" + this.c);
            return 0;
        }
        byte[] bArr = this.b.f6240a;
        int i = this.c;
        this.c = i + 1;
        byte[] bArr2 = this.b.f6240a;
        int i2 = this.c;
        this.c = i2 + 1;
        return (short) (((short) (bArr[i] & 255)) | (((short) bArr2[i2]) << 8));
    }

    public int f() {
        if (this.b == null || this.c >= this.b.c - 3) {
            Log.e(f9345a, "readInt error mCode:" + this.b + "  mCurIndex:" + this.c);
            return 0;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            byte[] bArr = this.b.f6240a;
            int i4 = this.c;
            this.c = i4 + 1;
            i2 |= (bArr[i4] & 255) << i;
            i += 8;
        }
        return i2;
    }
}
