package com.xiaomi.push;

import android.content.Context;
import com.coloros.mcssdk.c.a;

public class af {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f12624a = a.f.toCharArray();

    public static String a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 * 2);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3] & 255;
            sb.append(f12624a[b >> 4]);
            sb.append(f12624a[b & 15]);
        }
        return sb.toString();
    }

    public static boolean a(Context context) {
        return ae.f12623a;
    }
}
