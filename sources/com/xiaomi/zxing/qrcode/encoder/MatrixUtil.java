package com.xiaomi.zxing.qrcode.encoder;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaomi.zxing.qrcode.decoder.Version;

final class MatrixUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final int[][] f1777a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    private static final int e = 7973;
    private static final int f = 1335;
    private static final int g = 21522;

    private static boolean b(int i) {
        return i == -1;
    }

    private MatrixUtil() {
    }

    static void a(ByteMatrix byteMatrix) {
        byteMatrix.a((byte) -1);
    }

    static void a(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int i, ByteMatrix byteMatrix) throws WriterException {
        a(byteMatrix);
        a(version, byteMatrix);
        a(errorCorrectionLevel, i, byteMatrix);
        b(version, byteMatrix);
        a(bitArray, i, byteMatrix);
    }

    static void a(Version version, ByteMatrix byteMatrix) throws WriterException {
        d(byteMatrix);
        c(byteMatrix);
        c(version, byteMatrix);
        b(byteMatrix);
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) throws WriterException {
        BitArray bitArray = new BitArray();
        a(errorCorrectionLevel, i, bitArray);
        for (int i2 = 0; i2 < bitArray.a(); i2++) {
            boolean a2 = bitArray.a((bitArray.a() - 1) - i2);
            byteMatrix.a(d[i2][0], d[i2][1], a2);
            if (i2 < 8) {
                byteMatrix.a((byteMatrix.b() - i2) - 1, 8, a2);
            } else {
                byteMatrix.a(8, (byteMatrix.a() - 7) + (i2 - 8), a2);
            }
        }
    }

    static void b(Version version, ByteMatrix byteMatrix) throws WriterException {
        if (version.a() >= 7) {
            BitArray bitArray = new BitArray();
            a(version, bitArray);
            int i = 0;
            int i2 = 17;
            while (i < 6) {
                int i3 = i2;
                for (int i4 = 0; i4 < 3; i4++) {
                    boolean a2 = bitArray.a(i3);
                    i3--;
                    byteMatrix.a(i, (byteMatrix.a() - 11) + i4, a2);
                    byteMatrix.a((byteMatrix.a() - 11) + i4, i, a2);
                }
                i++;
                i2 = i3;
            }
        }
    }

    static void a(BitArray bitArray, int i, ByteMatrix byteMatrix) throws WriterException {
        boolean z;
        int b2 = byteMatrix.b() - 1;
        int a2 = byteMatrix.a() - 1;
        int i2 = 0;
        int i3 = -1;
        while (b2 > 0) {
            if (b2 == 6) {
                b2--;
            }
            while (a2 >= 0 && a2 < byteMatrix.a()) {
                int i4 = i2;
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = b2 - i5;
                    if (b((int) byteMatrix.a(i6, a2))) {
                        if (i4 < bitArray.a()) {
                            z = bitArray.a(i4);
                            i4++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && MaskUtil.a(i, i6, a2)) {
                            z = !z;
                        }
                        byteMatrix.a(i6, a2, z);
                    }
                }
                a2 += i3;
                i2 = i4;
            }
            i3 = -i3;
            a2 += i3;
            b2 -= 2;
        }
        if (i2 != bitArray.a()) {
            throw new WriterException("Not all bits consumed: " + i2 + IOUtils.f15883a + bitArray.a());
        }
    }

    static int a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    static int a(int i, int i2) {
        if (i2 != 0) {
            int a2 = a(i2);
            int i3 = i << (a2 - 1);
            while (a(i3) >= a2) {
                i3 ^= i2 << (a(i3) - a2);
            }
            return i3;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, BitArray bitArray) throws WriterException {
        if (QRCode.b(i)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i;
            bitArray.c(bits, 5);
            bitArray.c(a(bits, (int) f), 10);
            BitArray bitArray2 = new BitArray();
            bitArray2.c(g, 15);
            bitArray.b(bitArray2);
            if (bitArray.a() != 15) {
                throw new WriterException("should not happen but we got: " + bitArray.a());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    static void a(Version version, BitArray bitArray) throws WriterException {
        bitArray.c(version.a(), 6);
        bitArray.c(a(version.a(), (int) e), 12);
        if (bitArray.a() != 18) {
            throw new WriterException("should not happen but we got: " + bitArray.a());
        }
    }

    private static void b(ByteMatrix byteMatrix) {
        int i = 8;
        while (i < byteMatrix.b() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (b((int) byteMatrix.a(i, 6))) {
                byteMatrix.a(i, 6, i3);
            }
            if (b((int) byteMatrix.a(6, i))) {
                byteMatrix.a(6, i, i3);
            }
            i = i2;
        }
    }

    private static void c(ByteMatrix byteMatrix) throws WriterException {
        if (byteMatrix.a(8, byteMatrix.a() - 8) != 0) {
            byteMatrix.a(8, byteMatrix.a() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    private static void a(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 8) {
            int i4 = i + i3;
            if (b((int) byteMatrix.a(i4, i2))) {
                byteMatrix.a(i4, i2, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void b(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 7) {
            int i4 = i2 + i3;
            if (b((int) byteMatrix.a(i, i4))) {
                byteMatrix.a(i, i4, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void c(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 5; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                byteMatrix.a(i + i4, i2 + i3, b[i3][i4]);
            }
        }
    }

    private static void d(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                byteMatrix.a(i + i4, i2 + i3, f1777a[i3][i4]);
            }
        }
    }

    private static void d(ByteMatrix byteMatrix) throws WriterException {
        int length = f1777a[0].length;
        d(0, 0, byteMatrix);
        d(byteMatrix.b() - length, 0, byteMatrix);
        d(0, byteMatrix.b() - length, byteMatrix);
        a(0, 7, byteMatrix);
        a(byteMatrix.b() - 8, 7, byteMatrix);
        a(0, byteMatrix.b() - 8, byteMatrix);
        b(7, 0, byteMatrix);
        b((byteMatrix.a() - 7) - 1, 0, byteMatrix);
        b(7, byteMatrix.a() - 7, byteMatrix);
    }

    private static void c(Version version, ByteMatrix byteMatrix) {
        if (version.a() >= 2) {
            int a2 = version.a() - 1;
            int[] iArr = c[a2];
            int length = c[a2].length;
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = iArr[i];
                    int i4 = iArr[i2];
                    if (!(i4 == -1 || i3 == -1 || !b((int) byteMatrix.a(i4, i3)))) {
                        c(i4 - 2, i3 - 2, byteMatrix);
                    }
                }
            }
        }
    }
}
