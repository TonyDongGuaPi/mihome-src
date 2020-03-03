package com.xiaomi.zxing.qrcode.encoder;

final class MaskUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1776a = 3;
    private static final int b = 3;
    private static final int c = 40;
    private static final int d = 10;

    private MaskUtil() {
    }

    static int a(ByteMatrix byteMatrix) {
        return a(byteMatrix, true) + a(byteMatrix, false);
    }

    static int b(ByteMatrix byteMatrix) {
        byte[][] c2 = byteMatrix.c();
        int b2 = byteMatrix.b();
        int a2 = byteMatrix.a();
        int i = 0;
        int i2 = 0;
        while (i < a2 - 1) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < b2 - 1) {
                byte b3 = c2[i][i4];
                int i5 = i4 + 1;
                if (b3 == c2[i][i5]) {
                    int i6 = i + 1;
                    if (b3 == c2[i6][i4] && b3 == c2[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return i2 * 3;
    }

    static int c(ByteMatrix byteMatrix) {
        byte[][] c2 = byteMatrix.c();
        int b2 = byteMatrix.b();
        int a2 = byteMatrix.a();
        int i = 0;
        int i2 = 0;
        while (i < a2) {
            int i3 = i2;
            for (int i4 = 0; i4 < b2; i4++) {
                byte[] bArr = c2[i];
                int i5 = i4 + 6;
                if (i5 < b2 && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (a(bArr, i4 - 4, i4) || a(bArr, i4 + 7, i4 + 11))) {
                    i3++;
                }
                int i6 = i + 6;
                if (i6 < a2 && c2[i][i4] == 1 && c2[i + 1][i4] == 0 && c2[i + 2][i4] == 1 && c2[i + 3][i4] == 1 && c2[i + 4][i4] == 1 && c2[i + 5][i4] == 0 && c2[i6][i4] == 1 && (a(c2, i4, i - 4, i) || a(c2, i4, i + 7, i + 11))) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2 * 40;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    static int d(ByteMatrix byteMatrix) {
        byte[][] c2 = byteMatrix.c();
        int b2 = byteMatrix.b();
        int a2 = byteMatrix.a();
        int i = 0;
        int i2 = 0;
        while (i < a2) {
            byte[] bArr = c2[i];
            int i3 = i2;
            for (int i4 = 0; i4 < b2; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        int a3 = byteMatrix.a() * byteMatrix.b();
        return ((Math.abs((i2 * 2) - a3) * 10) / a3) * 10;
    }

    static boolean a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 & 1) + (i5 % 3);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 & 1) + (i6 % 3)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + i);
        }
        if (i4 == 0) {
            return true;
        }
        return false;
    }

    private static int a(ByteMatrix byteMatrix, boolean z) {
        int a2 = z ? byteMatrix.a() : byteMatrix.b();
        int b2 = z ? byteMatrix.b() : byteMatrix.a();
        byte[][] c2 = byteMatrix.c();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            int i3 = i;
            int i4 = 0;
            byte b3 = -1;
            for (int i5 = 0; i5 < b2; i5++) {
                byte b4 = z ? c2[i2][i5] : c2[i5][i2];
                if (b4 == b3) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i3 += (i4 - 5) + 3;
                    }
                    i4 = 1;
                    b3 = b4;
                }
            }
            if (i4 >= 5) {
                i3 += (i4 - 5) + 3;
            }
            i = i3;
        }
        return i;
    }
}
