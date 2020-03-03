package com.xiaomi.connection;

import android.util.Log;
import cn.com.fmsh.tsm.business.constants.Constants;

public class CamProtocolUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10089a = 16;
    public static final String b = "3.9.1.63";
    public static final String c = "3.9.1.88";
    public static final byte[] d = {Constants.TagName.BUSINESS_ORDER_TYPE, 76};
    public static final byte[] e = {Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 77};
    public static final byte[] f = {Constants.TagName.BUSINESS_ORDER_TYPE, 76, Constants.TagName.TERMINAL_BACK_CONTENT, 77};
    public static final byte[] g = {76, Constants.TagName.CP_NO, Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_INFO_LIST};
    public static final int h = 23;
    public static final int i = 16;
    private static final String j = "CamProtocolUtils  ";
    private static final short k = 5;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    private int o = 0;
    private int p = 0;
    private int q = 5;
    private byte[] r;

    public CamProtocolUtils(int i2, int i3, int i4, byte[] bArr) {
        this.o = i2;
        this.p = i4;
        this.r = bArr;
        this.q = i3;
    }

    public int a() {
        return this.q;
    }

    public byte[] b() {
        byte[] bArr = new byte[((this.p <= 0 ? 0 : this.p) + 16)];
        ByteOperator.a(bArr, 0, d, 0, 1);
        ByteOperator.a(bArr, 2, ByteOperator.d(5), 0, 1);
        ByteOperator.a(bArr, 4, ByteOperator.d(this.o), 0, 1);
        if (this.r != null && this.p > 0) {
            ByteOperator.a(bArr, 6, ByteOperator.d(this.r.length), 0, 1);
            ByteOperator.a(bArr, 16, this.r, 0, this.p - 1);
        }
        return bArr;
    }

    public static byte[] a(int i2, int i3, byte[] bArr) {
        byte[] bArr2 = new byte[((i3 <= 0 ? 0 : i3) + 16)];
        ByteOperator.a(bArr2, 0, d, 0, 1);
        ByteOperator.a(bArr2, 2, ByteOperator.d(5), 0, 1);
        ByteOperator.a(bArr2, 4, ByteOperator.d(i2), 0, 1);
        if (bArr != null && i3 > 0) {
            ByteOperator.a(bArr2, 6, ByteOperator.d(bArr.length), 0, 1);
            ByteOperator.a(bArr2, 16, bArr, 0, i3 - 1);
        }
        return bArr2;
    }

    public static byte[] a(int i2, int i3, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[((i3 <= 0 ? 0 : i3) + 16 + bArr.length)];
        ByteOperator.a(bArr3, 0, d, 0, 1);
        ByteOperator.a(bArr3, 2, ByteOperator.d(5), 0, 1);
        ByteOperator.a(bArr3, 4, ByteOperator.d(i2), 0, 1);
        if (bArr2 != null && i3 > 0) {
            ByteOperator.a(bArr3, 6, ByteOperator.d(bArr2.length), 0, 1);
            ByteOperator.a(bArr3, 16, bArr, 0, bArr.length - 1);
            ByteOperator.a(bArr3, bArr.length + 16, bArr2, 0, i3 - 1);
        }
        return bArr3;
    }

    public static byte[] b(int i2, int i3, byte[] bArr) {
        byte[] bArr2 = new byte[((i3 <= 0 ? 0 : i3) + 23)];
        ByteOperator.a(bArr2, 0, e, 0, 3);
        ByteOperator.a(bArr2, 4, ByteOperator.d(i2), 0, 1);
        bArr2[6] = 5;
        if (bArr != null && i3 > 0) {
            ByteOperator.a(bArr2, 15, ByteOperator.a(bArr.length), 0, 3);
            ByteOperator.a(bArr2, 23, bArr, 0, i3 - 1);
        }
        return bArr2;
    }

    public static byte[] c(int i2, int i3, byte[] bArr) {
        byte[] bArr2 = new byte[((i3 <= 0 ? 0 : i3) + 16)];
        ByteOperator.a(bArr2, 0, g, 0, 3);
        ByteOperator.a(bArr2, 4, ByteOperator.a(i2), 0, 3);
        ByteOperator.a(bArr2, 8, ByteOperator.a(5), 0, 3);
        if (bArr != null && i3 > 0) {
            ByteOperator.a(bArr2, 12, ByteOperator.a(bArr.length), 0, 3);
            ByteOperator.a(bArr2, 16, bArr, 0, i3 - 1);
        }
        return bArr2;
    }

    public static int a(byte[] bArr, boolean z) {
        if (bArr.length < 16) {
            return -1;
        }
        int a2 = ByteOperator.a(bArr, 6);
        if (!z && a2 + 16 > bArr.length) {
            return -3;
        }
        return a2;
    }

    protected static int b(byte[] bArr, boolean z) {
        if (bArr.length < 23) {
            Log.d(j, "checkLocalData: too short");
            return -1;
        }
        int a2 = ByteOperator.a(bArr, 15);
        Log.d(j, "checkLocalData: textLength=" + a2 + "  data length=" + bArr.length);
        if (!z && a2 + 23 > bArr.length) {
            return -3;
        }
        return a2;
    }

    protected static int c(byte[] bArr, boolean z) {
        if (bArr.length < 16) {
            Log.d(j, "checkLogData: too short");
            return -1;
        }
        int d2 = ByteOperator.d(bArr, 12);
        Log.d(j, "checkLogData: textLength=" + d2 + "  data length=" + bArr.length);
        if (!z && d2 + 16 > bArr.length) {
            return -3;
        }
        return d2;
    }

    public static CamProtocolUtils a(byte[] bArr) {
        switch (b(bArr)) {
            case 1:
                int a2 = a(bArr, false);
                if (a2 >= 0) {
                    return new CamProtocolUtils(ByteOperator.a(bArr, 4), ByteOperator.a(bArr, 2), a2, ByteOperator.b(bArr, 16, a2 + 15));
                }
                return null;
            case 2:
                int b2 = b(bArr, false);
                Log.d(j, "getFromBytes: dataLength=" + b2);
                if (b2 < 0) {
                    return null;
                }
                int a3 = ByteOperator.a(bArr, 4);
                byte b3 = bArr[6];
                Log.d(j, "getFromBytes: code=" + a3);
                return new CamProtocolUtils(a3, b3, b2, ByteOperator.b(bArr, 23, b2 + 22));
            default:
                return null;
        }
    }

    private static int b(byte[] bArr) {
        if (ByteOperator.b(e, 0, bArr, 0, 3) || ByteOperator.b(f, 0, bArr, 0, 3)) {
            return 2;
        }
        if (ByteOperator.b(d, 0, bArr, 0, 1)) {
            return 1;
        }
        if (ByteOperator.b(g, 0, bArr, 0, 1)) {
            return 3;
        }
        return -1;
    }

    public int c() {
        return this.o;
    }

    public int d() {
        return this.p;
    }

    public byte[] e() {
        return this.r;
    }
}
