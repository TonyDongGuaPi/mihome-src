package com.xiaomi.smarthome.device.bluetooth.advertise;

import android.annotation.TargetApi;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public class Pdu {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f15123a = -1;
    public static final byte b = 22;
    private static final String c = "Pdu";
    private byte d;
    private int e;
    private int f;
    private int g;
    private byte[] h;

    @TargetApi(9)
    public static Pdu a(byte[] bArr, int i) {
        byte b2;
        if (bArr.length - i >= 2 && (b2 = bArr[i]) > 0) {
            byte b3 = bArr[i + 1];
            int i2 = i + 2;
            if (i2 < bArr.length) {
                Pdu pdu = new Pdu();
                pdu.g = (i2 + b2) - 2;
                if (pdu.g >= bArr.length) {
                    pdu.g = bArr.length - 1;
                }
                pdu.d = b3;
                pdu.e = b2;
                pdu.f = i2;
                pdu.h = bArr;
                return pdu;
            }
        }
        return null;
    }

    public byte a(int i) {
        int i2 = this.f + i + 2;
        if (i2 < this.h.length) {
            return this.h[i2];
        }
        throw new IllegalArgumentException("getByte offset overflow!");
    }

    public byte[] a(int i, int i2) {
        if (i < 0 || i2 <= 0) {
            return ByteUtils.b;
        }
        int i3 = this.f + i + 2;
        if (i3 >= this.h.length) {
            return ByteUtils.b;
        }
        if (i3 + i2 > this.h.length) {
            i2 = this.h.length - i3;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = a(i + i4);
        }
        return bArr;
    }

    public byte[] a() {
        return a(0, (this.g - this.f) - 1);
    }

    public byte b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return (this.g - this.f) + 1;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public String g() {
        String str;
        switch (this.d & 255) {
            case 8:
            case 9:
                str = "%c";
                break;
            default:
                str = "%x ";
                break;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.f; i <= this.g; i++) {
            sb.append(String.format(str, new Object[]{Byte.valueOf(this.h[i])}));
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("len: " + this.e);
        sb.append(String.format(", type: 0x%x, ", new Object[]{Byte.valueOf(this.d)}));
        sb.append(g());
        return sb.toString();
    }

    public byte[] h() {
        return this.h;
    }
}
