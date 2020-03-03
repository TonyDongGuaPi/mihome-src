package com.sina.weibo.sdk.utils;

import cn.com.fmsh.tsm.business.constants.Constants;

public final class Base64 {

    /* renamed from: a  reason: collision with root package name */
    private static char[] f8843a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] b = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) ((i3 + 26) - 97);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) ((i4 + 52) - 48);
        }
        b[43] = Constants.TagName.CARD_BUSINESS_ORDER_STATUS;
        b[47] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
    }

    public static byte[] a(byte[] bArr) {
        int length = ((bArr.length + 3) / 4) * 3;
        if (bArr.length > 0 && bArr[bArr.length - 1] == 61) {
            length--;
        }
        if (bArr.length > 1 && bArr[bArr.length - 2] == 61) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        int i = 0;
        byte b2 = 0;
        int i2 = 0;
        for (byte b3 : bArr) {
            byte b4 = b[b3 & 255];
            if (b4 >= 0) {
                i2 += 6;
                b2 = (b2 << 6) | b4;
                if (i2 >= 8) {
                    i2 -= 8;
                    bArr2[i] = (byte) ((b2 >> i2) & 255);
                    i++;
                }
            }
        }
        if (i == bArr2.length) {
            return bArr2;
        }
        throw new RuntimeException("miscalculated data length!");
    }

    public static char[] b(byte[] bArr) {
        boolean z;
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
            } else {
                z2 = false;
            }
            int i7 = 64;
            cArr[i2 + 3] = f8843a[z2 ? i5 & 63 : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            char[] cArr2 = f8843a;
            if (z) {
                i7 = i8 & 63;
            }
            cArr[i9] = cArr2[i7];
            int i10 = i8 >> 6;
            cArr[i2 + 1] = f8843a[i10 & 63];
            cArr[i2 + 0] = f8843a[(i10 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return cArr;
    }

    public static byte[] c(byte[] bArr) {
        boolean z;
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
            } else {
                z2 = false;
            }
            int i7 = 64;
            bArr2[i2 + 3] = (byte) f8843a[z2 ? i5 & 63 : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            char[] cArr = f8843a;
            if (z) {
                i7 = i8 & 63;
            }
            bArr2[i9] = (byte) cArr[i7];
            int i10 = i8 >> 6;
            bArr2[i2 + 1] = (byte) f8843a[i10 & 63];
            bArr2[i2 + 0] = (byte) f8843a[(i10 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return bArr2;
    }
}
