package com.tencent.wxop.stat.common;

public class g {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f9319a = (!g.class.desiredAssertionStatus());

    private g() {
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        i iVar = new i(i3, new byte[((i2 * 3) / 4)]);
        if (!iVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (iVar.b == iVar.f9320a.length) {
            return iVar.f9320a;
        } else {
            byte[] bArr2 = new byte[iVar.b];
            System.arraycopy(iVar.f9320a, 0, bArr2, 0, iVar.b);
            return bArr2;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        return b(bArr, 0, bArr.length, i);
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) {
        j jVar = new j(i3, (byte[]) null);
        int i4 = (i2 / 3) * 4;
        if (!jVar.d) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (jVar.e && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (jVar.f ? 2 : 1);
        }
        jVar.f9320a = new byte[i4];
        jVar.a(bArr, i, i2, true);
        if (f9319a || jVar.b == i4) {
            return jVar.f9320a;
        }
        throw new AssertionError();
    }
}
