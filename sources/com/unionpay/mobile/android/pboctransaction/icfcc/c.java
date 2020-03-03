package com.unionpay.mobile.android.pboctransaction.icfcc;

import com.unionpay.mobile.android.pboctransaction.e;

public final class c {
    public static String a(String str, String str2) {
        int i;
        int i2;
        byte b;
        byte b2;
        int i3;
        byte b3;
        int i4;
        int i5;
        int i6;
        byte b4;
        if (str == null) {
            return null;
        }
        byte[] a2 = e.a(str);
        int i7 = 0;
        while (i7 < a2.length) {
            int i8 = 1;
            int i9 = ((byte) (a2[i7] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i9];
            System.arraycopy(a2, i7, bArr, 0, i9);
            if (e.a(bArr, i9).compareToIgnoreCase(str2) == 0) {
                int i10 = i7 + i9;
                if (((byte) (a2[i10] & 128)) != Byte.MIN_VALUE) {
                    b2 = a2[i10];
                } else {
                    i8 = 1 + (a2[i10] & Byte.MAX_VALUE);
                    if (i8 == 2) {
                        b2 = a2[i10 + 1];
                    } else {
                        if (i8 == 3) {
                            i2 = (a2[i10 + 1] & 255) << 8;
                            b = a2[i10 + 2];
                        } else if (i8 == 4) {
                            i2 = ((a2[i10 + 1] & 255) << 16) | ((a2[i10 + 2] & 255) << 8);
                            b = a2[i10 + 3];
                        } else {
                            i = 0;
                            byte[] bArr2 = new byte[i];
                            System.arraycopy(a2, i10 + i8, bArr2, 0, i);
                            return e.a(bArr2, i);
                        }
                        i = i2 | (b & 255);
                        byte[] bArr22 = new byte[i];
                        System.arraycopy(a2, i10 + i8, bArr22, 0, i);
                        return e.a(bArr22, i);
                    }
                }
                i = b2 & 255;
                byte[] bArr222 = new byte[i];
                System.arraycopy(a2, i10 + i8, bArr222, 0, i);
                return e.a(bArr222, i);
            }
            if ((a2[i7] & 32) == 32) {
                i3 = i7 + i9;
                if (i3 < a2.length && ((byte) (a2[i3] & 128)) == Byte.MIN_VALUE) {
                    i8 = 1 + (a2[i3] & Byte.MAX_VALUE);
                }
            } else {
                i3 = i7 + i9;
                if (i3 >= a2.length || ((byte) (a2[i3] & 128)) != 0) {
                    i8 = i3 < a2.length ? (a2[i3] & Byte.MAX_VALUE) + 1 : 0;
                    if (i8 == 2 && (i6 = i3 + 1) < a2.length) {
                        b4 = a2[i6];
                    } else if (i8 != 3 || (i5 = i3 + 2) >= a2.length) {
                        b3 = (i8 != 4 || (i4 = i3 + 2) >= a2.length) ? 0 : ((a2[i4] & 255) << 8) | ((a2[i3 + 1] & 255) << 16) | (a2[i3 + 3] & 255);
                        i8 += b3;
                    } else {
                        b3 = (a2[i5] & 255) | ((a2[i3 + 1] & 255) << 8);
                        i8 += b3;
                    }
                } else {
                    b4 = a2[i3];
                }
                b3 = b4 & 255;
                i8 += b3;
            }
            i7 = i3 + i8;
        }
        return null;
    }
}
