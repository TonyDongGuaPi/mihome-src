package com.xiaomi.infra.galaxy.fds.auth.signature;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.xiaomi.smarthome.fastvideo.IOUtils;

class Base64Util {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10151a = 128;
    private static final int b = 64;
    private static final int c = 24;
    private static final int d = 8;
    private static final int e = 16;
    private static final int f = 6;
    private static final int g = 4;
    private static final int h = -128;
    private static final char i = '=';
    private static final boolean j = false;
    private static final byte[] k = new byte[128];
    private static final char[] l = new char[64];

    protected static boolean a(char c2) {
        return c2 == ' ' || c2 == 13 || c2 == 10 || c2 == 9;
    }

    protected static boolean b(char c2) {
        return c2 == '=';
    }

    Base64Util() {
    }

    static {
        int i2;
        int i3;
        int i4 = 0;
        for (int i5 = 0; i5 < 128; i5++) {
            k[i5] = -1;
        }
        for (int i6 = 90; i6 >= 65; i6--) {
            k[i6] = (byte) (i6 - 65);
        }
        int i7 = 122;
        while (true) {
            i2 = 26;
            if (i7 < 97) {
                break;
            }
            k[i7] = (byte) ((i7 - 97) + 26);
            i7--;
        }
        int i8 = 57;
        while (true) {
            i3 = 52;
            if (i8 < 48) {
                break;
            }
            k[i8] = (byte) ((i8 - 48) + 52);
            i8--;
        }
        k[43] = Constants.TagName.CARD_BUSINESS_ORDER_STATUS;
        k[47] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        for (int i9 = 0; i9 <= 25; i9++) {
            l[i9] = (char) (i9 + 65);
        }
        int i10 = 0;
        while (i2 <= 51) {
            l[i2] = (char) (i10 + 97);
            i2++;
            i10++;
        }
        while (i3 <= 61) {
            l[i3] = (char) (i4 + 48);
            i3++;
            i4++;
        }
        l[62] = '+';
        l[63] = IOUtils.f15883a;
    }

    protected static boolean c(char c2) {
        return c2 < 128 && k[c2] != -1;
    }

    protected static boolean d(char c2) {
        return a(c2) || b(c2) || c(c2);
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i2 = length % 24;
        int i3 = length / 24;
        char[] cArr = new char[((i2 != 0 ? i3 + 1 : i3) * 4)];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i3) {
            int i7 = i5 + 1;
            byte b2 = bArr[i5];
            int i8 = i7 + 1;
            byte b3 = bArr[i7];
            int i9 = i8 + 1;
            byte b4 = bArr[i8];
            byte b5 = (byte) (b3 & 15);
            byte b6 = (byte) (b2 & 3);
            byte b7 = (byte) ((b2 & Byte.MIN_VALUE) == 0 ? b2 >> 2 : (b2 >> 2) ^ 192);
            byte b8 = (byte) ((b3 & Byte.MIN_VALUE) == 0 ? b3 >> 4 : (b3 >> 4) ^ PsExtractor.VIDEO_STREAM_MASK);
            byte b9 = (byte) ((b4 & Byte.MIN_VALUE) == 0 ? b4 >> 6 : (b4 >> 6) ^ 252);
            int i10 = i6 + 1;
            cArr[i6] = l[b7];
            int i11 = i10 + 1;
            cArr[i10] = l[b8 | (b6 << 4)];
            int i12 = i11 + 1;
            cArr[i11] = l[(b5 << 2) | b9];
            cArr[i12] = l[b4 & Constants.TagName.CARD_APP_ACTIVATION_STATUS];
            i4++;
            i6 = i12 + 1;
            i5 = i9;
        }
        if (i2 == 8) {
            byte b10 = bArr[i5];
            byte b11 = (byte) (b10 & 3);
            int i13 = i6 + 1;
            cArr[i6] = l[(byte) ((b10 & Byte.MIN_VALUE) == 0 ? b10 >> 2 : (b10 >> 2) ^ 192)];
            int i14 = i13 + 1;
            cArr[i13] = l[b11 << 4];
            cArr[i14] = i;
            cArr[i14 + 1] = i;
        } else if (i2 == 16) {
            byte b12 = bArr[i5];
            byte b13 = bArr[i5 + 1];
            byte b14 = (byte) (b13 & 15);
            byte b15 = (byte) (b12 & 3);
            byte b16 = (byte) ((b12 & Byte.MIN_VALUE) == 0 ? b12 >> 2 : (b12 >> 2) ^ 192);
            byte b17 = (byte) ((b13 & Byte.MIN_VALUE) == 0 ? b13 >> 4 : (b13 >> 4) ^ PsExtractor.VIDEO_STREAM_MASK);
            int i15 = i6 + 1;
            cArr[i6] = l[b16];
            int i16 = i15 + 1;
            cArr[i15] = l[b17 | (b15 << 4)];
            cArr[i16] = l[b14 << 2];
            cArr[i16 + 1] = i;
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int a2 = a(charArray);
        if (a2 % 4 != 0) {
            return null;
        }
        int i2 = a2 / 4;
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(i2 * 3)];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2 - 1) {
            int i6 = i4 + 1;
            char c2 = charArray[i4];
            if (c(c2)) {
                int i7 = i6 + 1;
                char c3 = charArray[i6];
                if (c(c3)) {
                    int i8 = i7 + 1;
                    char c4 = charArray[i7];
                    if (c(c4)) {
                        int i9 = i8 + 1;
                        char c5 = charArray[i8];
                        if (c(c5)) {
                            byte b2 = k[c2];
                            byte b3 = k[c3];
                            byte b4 = k[c4];
                            byte b5 = k[c5];
                            int i10 = i5 + 1;
                            bArr[i5] = (byte) ((b2 << 2) | (b3 >> 4));
                            int i11 = i10 + 1;
                            bArr[i10] = (byte) (((b3 & 15) << 4) | ((b4 >> 2) & 15));
                            i5 = i11 + 1;
                            bArr[i11] = (byte) ((b4 << 6) | b5);
                            i3++;
                            i4 = i9;
                        }
                    }
                }
            }
            return null;
        }
        int i12 = i4 + 1;
        char c6 = charArray[i4];
        if (c(c6)) {
            int i13 = i12 + 1;
            char c7 = charArray[i12];
            if (c(c7)) {
                byte b6 = k[c6];
                byte b7 = k[c7];
                int i14 = i13 + 1;
                char c8 = charArray[i13];
                char c9 = charArray[i14];
                if (c(c8) && c(c9)) {
                    byte b8 = k[c8];
                    byte b9 = k[c9];
                    int i15 = i5 + 1;
                    bArr[i5] = (byte) ((b6 << 2) | (b7 >> 4));
                    bArr[i15] = (byte) (((b7 & 15) << 4) | ((b8 >> 2) & 15));
                    bArr[i15 + 1] = (byte) (b9 | (b8 << 6));
                    return bArr;
                } else if (!b(c8) || !b(c9)) {
                    if (b(c8) || !b(c9)) {
                        return null;
                    }
                    byte b10 = k[c8];
                    if ((b10 & 3) != 0) {
                        return null;
                    }
                    int i16 = i3 * 3;
                    byte[] bArr2 = new byte[(i16 + 2)];
                    System.arraycopy(bArr, 0, bArr2, 0, i16);
                    bArr2[i5] = (byte) ((b6 << 2) | (b7 >> 4));
                    bArr2[i5 + 1] = (byte) (((b10 >> 2) & 15) | ((b7 & 15) << 4));
                    return bArr2;
                } else if ((b7 & 15) != 0) {
                    return null;
                } else {
                    int i17 = i3 * 3;
                    byte[] bArr3 = new byte[(i17 + 1)];
                    System.arraycopy(bArr, 0, bArr3, 0, i17);
                    bArr3[i5] = (byte) ((b6 << 2) | (b7 >> 4));
                    return bArr3;
                }
            }
        }
        return null;
    }

    protected static int a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (!a(cArr[i3])) {
                cArr[i2] = cArr[i3];
                i2++;
            }
        }
        return i2;
    }
}
