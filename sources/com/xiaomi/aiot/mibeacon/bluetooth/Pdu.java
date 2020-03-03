package com.xiaomi.aiot.mibeacon.bluetooth;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import com.xiaomi.aiot.mibeacon.utils.ByteUtils;

public class Pdu {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9973a = 255;
    public static final int b = 22;
    private static final String c = "Pdu";
    private int d;
    private int e;
    private int f;
    private byte[] g;

    @TargetApi(9)
    public static Pdu a(byte[] bArr, int i) {
        byte b2;
        if (bArr.length - i >= 2 && (b2 = bArr[i]) > 0) {
            byte b3 = bArr[i + 1];
            int i2 = i + 2;
            if (i2 < bArr.length) {
                Pdu pdu = new Pdu();
                int i3 = i + b2;
                if (i3 >= bArr.length) {
                    i3 = bArr.length - 1;
                }
                pdu.d = b3 & 255;
                pdu.e = b2;
                pdu.g = ByteUtils.a(bArr, i2, i3);
                pdu.f = (i3 - i) + 1;
                return pdu;
            }
        }
        return null;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public byte[] d() {
        return this.g;
    }

    @NonNull
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Type: 0x%02x, ", new Object[]{Integer.valueOf(this.d)}));
        sb.append(String.format("Len: %d, ", new Object[]{Integer.valueOf(this.e)}));
        switch (this.d) {
            case 8:
            case 9:
                str = "%c";
                break;
            default:
                str = "%02x ";
                break;
        }
        try {
            byte[] bArr = this.g;
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format(str, new Object[]{Byte.valueOf(bArr[i])}));
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    public String e() {
        String str;
        StringBuilder sb = new StringBuilder();
        switch (this.d) {
            case 8:
            case 9:
                str = "%c";
                break;
            default:
                str = "%02x";
                break;
        }
        try {
            byte[] bArr = this.g;
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format(str, new Object[]{Byte.valueOf(bArr[i])}));
            }
        } catch (Throwable unused) {
        }
        return sb.toString().toUpperCase();
    }
}
