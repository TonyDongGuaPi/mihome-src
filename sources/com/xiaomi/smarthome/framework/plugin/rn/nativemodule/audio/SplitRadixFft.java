package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio;

public class SplitRadixFft {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17442a = 512;

    public void a(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3 = iArr[0];
        if (i > (i3 << 2)) {
            i3 = i >> 2;
            a(i3, iArr, dArr2);
        }
        int i4 = i3;
        if (i2 >= 0) {
            a(i, dArr, iArr, 2, i4, dArr2);
        } else {
            b(i, dArr, iArr, 2, i4, dArr2);
        }
    }

    public void b(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3 = i;
        int[] iArr2 = iArr;
        double[] dArr3 = dArr2;
        int i4 = iArr2[0];
        if (i3 > (i4 << 2)) {
            i4 = i3 >> 2;
            a(i4, iArr2, dArr3);
        }
        int i5 = i4;
        int i6 = iArr2[1];
        if (i3 > (i6 << 2)) {
            i6 = i3 >> 2;
            a(i6, iArr2, dArr3, i5);
        }
        int i7 = i6;
        if (i2 >= 0) {
            if (i3 > 4) {
                int i8 = i;
                double[] dArr4 = dArr;
                int i9 = i5;
                a(i8, dArr4, iArr, 2, i9, dArr2);
                c(i8, dArr4, i7, dArr2, i9);
            } else if (i3 == 4) {
                a(i, dArr, iArr, 2, i5, dArr2);
            }
            double d = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            dArr[1] = d;
            return;
        }
        dArr[1] = (dArr[0] - dArr[1]) * 0.5d;
        dArr[0] = dArr[0] - dArr[1];
        if (i3 > 4) {
            int i10 = i;
            double[] dArr5 = dArr;
            int i11 = i5;
            d(i10, dArr5, i7, dArr2, i11);
            b(i10, dArr5, iArr, 2, i11, dArr2);
        } else if (i3 == 4) {
            b(i, dArr, iArr, 2, i5, dArr2);
        }
    }

    public void c(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4;
        int i5 = i;
        int[] iArr2 = iArr;
        double[] dArr3 = dArr2;
        int i6 = iArr2[0];
        if (i5 > (i6 << 2)) {
            i6 = i5 >> 2;
            a(i6, iArr2, dArr3);
        }
        int i7 = i6;
        int i8 = iArr2[1];
        if (i5 > i8) {
            a(i5, iArr2, dArr3, i7);
            i3 = i5;
        } else {
            i3 = i8;
        }
        if (i2 < 0) {
            double d = dArr[i5 - 1];
            for (int i9 = i5 - 2; i9 >= 2; i9 -= 2) {
                int i10 = i9 - 1;
                dArr[i9 + 1] = dArr[i9] - dArr[i10];
                dArr[i9] = dArr[i9] + dArr[i10];
            }
            dArr[1] = dArr[0] - d;
            dArr[0] = dArr[0] + d;
            if (i5 > 4) {
                int i11 = i;
                double[] dArr4 = dArr;
                int i12 = i7;
                d(i11, dArr4, i3, dArr2, i12);
                i4 = 4;
                b(i11, dArr4, iArr, 2, i12, dArr2);
            } else {
                i4 = 4;
                if (i5 == 4) {
                    b(i, dArr, iArr, 2, i7, dArr2);
                }
            }
        } else {
            i4 = 4;
        }
        e(i, dArr, i3, dArr2, i7);
        if (i2 >= 0) {
            if (i5 > i4) {
                int i13 = i;
                double[] dArr5 = dArr;
                int i14 = i7;
                a(i13, dArr5, iArr, 2, i14, dArr2);
                c(i13, dArr5, i3, dArr2, i14);
            } else if (i5 == i4) {
                a(i, dArr, iArr, 2, i7, dArr2);
            }
            double d2 = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            for (int i15 = 2; i15 < i5; i15 += 2) {
                int i16 = i15 + 1;
                dArr[i15 - 1] = dArr[i15] - dArr[i16];
                dArr[i15] = dArr[i15] + dArr[i16];
            }
            dArr[i5 - 1] = d2;
        }
    }

    public void d(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4;
        int i5 = i;
        int[] iArr2 = iArr;
        double[] dArr3 = dArr2;
        int i6 = iArr2[0];
        if (i5 > (i6 << 2)) {
            i6 = i5 >> 2;
            a(i6, iArr2, dArr3);
        }
        int i7 = i6;
        int i8 = iArr2[1];
        if (i5 > i8) {
            a(i5, iArr2, dArr3, i7);
            i3 = i5;
        } else {
            i3 = i8;
        }
        if (i2 < 0) {
            double d = dArr[i5 - 1];
            int i9 = i5 - 2;
            for (int i10 = 2; i9 >= i10; i10 = 2) {
                int i11 = i9 - 1;
                dArr[i9 + 1] = (-dArr[i9]) - dArr[i11];
                dArr[i9] = dArr[i9] - dArr[i11];
                i9 -= 2;
            }
            dArr[1] = dArr[0] + d;
            dArr[0] = dArr[0] - d;
            if (i5 > 4) {
                int i12 = i;
                double[] dArr4 = dArr;
                int i13 = i7;
                d(i12, dArr4, i3, dArr2, i13);
                i4 = 4;
                b(i12, dArr4, iArr, 2, i13, dArr2);
            } else {
                i4 = 4;
                if (i5 == 4) {
                    b(i, dArr, iArr, 2, i7, dArr2);
                }
            }
        } else {
            i4 = 4;
        }
        f(i, dArr, i3, dArr2, i7);
        if (i2 >= 0) {
            if (i5 > i4) {
                int i14 = i;
                double[] dArr5 = dArr;
                int i15 = i7;
                a(i14, dArr5, iArr, 2, i15, dArr2);
                c(i14, dArr5, i3, dArr2, i15);
            } else if (i5 == i4) {
                a(i, dArr, iArr, 2, i7, dArr2);
            }
            double d2 = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            for (int i16 = 2; i16 < i5; i16 += 2) {
                int i17 = i16 + 1;
                dArr[i16 - 1] = (-dArr[i16]) - dArr[i17];
                dArr[i16] = dArr[i16] - dArr[i17];
            }
            dArr[i5 - 1] = -d2;
        }
    }

    public void a(int i, double[] dArr, double[] dArr2, int[] iArr, double[] dArr3) {
        int i2;
        int i3;
        int i4 = i;
        int[] iArr2 = iArr;
        double[] dArr4 = dArr3;
        int i5 = iArr2[0];
        if (i4 > (i5 << 3)) {
            i5 = i4 >> 3;
            a(i5, iArr2, dArr4);
        }
        int i6 = i5;
        int i7 = iArr2[1];
        if (i4 > (i7 << 1)) {
            i7 = i4 >> 1;
            a(i7, iArr2, dArr4, i6);
        }
        int i8 = i7;
        int i9 = i4 >> 1;
        double d = dArr[i9];
        double d2 = dArr[0] + dArr[i4];
        dArr[0] = dArr[0] - dArr[i4];
        dArr2[0] = d2 - d;
        dArr2[i9] = d2 + d;
        if (i4 > 2) {
            int i10 = i9 >> 1;
            for (int i11 = 1; i11 < i10; i11++) {
                int i12 = i9 - i11;
                int i13 = i4 - i11;
                double d3 = dArr[i11] - dArr[i13];
                double d4 = dArr[i11] + dArr[i13];
                int i14 = i4 - i12;
                double d5 = dArr[i12] - dArr[i14];
                double d6 = dArr[i12] + dArr[i14];
                dArr[i11] = d3;
                dArr[i12] = d5;
                dArr2[i11] = d4 - d6;
                dArr2[i12] = d4 + d6;
            }
            int i15 = i4 - i10;
            dArr2[i10] = dArr[i10] + dArr[i15];
            dArr[i10] = dArr[i10] - dArr[i15];
            int i16 = i10;
            e(i9, dArr, i8, dArr3, i6);
            if (i9 > 4) {
                double[] dArr5 = dArr;
                i3 = 4;
                int i17 = i6;
                int i18 = i9;
                a(i9, dArr5, iArr, 2, i17, dArr3);
                c(i18, dArr5, i8, dArr3, i17);
                i2 = i18;
            } else {
                i3 = 4;
                if (i9 == 4) {
                    i2 = i9;
                    a(i9, dArr, iArr, 2, i6, dArr3);
                } else {
                    i2 = i9;
                }
            }
            dArr[i4 - 1] = dArr[0] - dArr[1];
            dArr[1] = dArr[0] + dArr[1];
            for (int i19 = i2 - 2; i19 >= 2; i19 -= 2) {
                int i20 = i19 * 2;
                int i21 = i19 + 1;
                dArr[i20 + 1] = dArr[i19] + dArr[i21];
                dArr[i20 - 1] = dArr[i19] - dArr[i21];
            }
            int i22 = i16;
            int i23 = 2;
            for (int i24 = 2; i22 >= i24; i24 = 2) {
                int i25 = i22;
                e(i22, dArr2, i8, dArr3, i6);
                if (i25 > i3) {
                    int i26 = i25;
                    double[] dArr6 = dArr2;
                    int i27 = i6;
                    a(i26, dArr6, iArr, 2, i27, dArr3);
                    c(i26, dArr6, i8, dArr3, i27);
                } else if (i25 == i3) {
                    a(i25, dArr2, iArr, 2, i6, dArr3);
                }
                dArr[i4 - i23] = dArr2[0] - dArr2[1];
                dArr[i23] = dArr2[0] + dArr2[1];
                int i28 = 0;
                for (int i29 = 2; i29 < i25; i29 += 2) {
                    i28 += i23 << 2;
                    int i30 = i29 + 1;
                    dArr[i28 - i23] = dArr2[i29] - dArr2[i30];
                    dArr[i28 + i23] = dArr2[i29] + dArr2[i30];
                }
                i23 <<= 1;
                i22 = i25 >> 1;
                for (int i31 = 0; i31 < i22; i31++) {
                    int i32 = i25 - i31;
                    int i33 = i25 + i32;
                    int i34 = i25 + i31;
                    dArr2[i31] = dArr2[i33] - dArr2[i34];
                    dArr2[i32] = dArr2[i33] + dArr2[i34];
                }
                dArr2[i22] = dArr2[i25 + i22];
            }
            dArr[i23] = dArr2[0];
            dArr[i4] = dArr2[2] - dArr2[1];
            dArr[0] = dArr2[2] + dArr2[1];
            return;
        }
        dArr[1] = dArr[0];
        dArr[2] = dArr2[0];
        dArr[0] = dArr2[1];
    }

    public void b(int i, double[] dArr, double[] dArr2, int[] iArr, double[] dArr3) {
        char c;
        int i2;
        int i3 = i;
        int[] iArr2 = iArr;
        double[] dArr4 = dArr3;
        int i4 = iArr2[0];
        if (i3 > (i4 << 3)) {
            i4 = i3 >> 3;
            a(i4, iArr2, dArr4);
        }
        int i5 = i4;
        int i6 = iArr2[1];
        if (i3 > (i6 << 1)) {
            i6 = i3 >> 1;
            a(i6, iArr2, dArr4, i5);
        }
        int i7 = i6;
        if (i3 > 2) {
            int i8 = i3 >> 1;
            int i9 = i8 >> 1;
            for (int i10 = 1; i10 < i9; i10++) {
                int i11 = i8 - i10;
                int i12 = i3 - i10;
                double d = dArr[i10] + dArr[i12];
                double d2 = dArr[i10] - dArr[i12];
                int i13 = i3 - i11;
                double d3 = dArr[i11] + dArr[i13];
                double d4 = dArr[i11] - dArr[i13];
                dArr[i10] = d;
                dArr[i11] = d3;
                dArr2[i10] = d2 + d4;
                dArr2[i11] = d2 - d4;
            }
            int i14 = i3 - i9;
            dArr2[0] = dArr[i9] - dArr[i14];
            dArr[i9] = dArr[i9] + dArr[i14];
            dArr[0] = dArr[i8];
            int i15 = i9;
            int i16 = i8;
            f(i8, dArr, i7, dArr3, i5);
            if (i16 > 4) {
                int i17 = i16;
                double[] dArr5 = dArr;
                i2 = 4;
                int i18 = i5;
                a(i17, dArr5, iArr, 2, i18, dArr3);
                c(i17, dArr5, i7, dArr3, i18);
            } else {
                i2 = 4;
                if (i16 == 4) {
                    a(i16, dArr, iArr, 2, i5, dArr3);
                }
            }
            dArr[i3 - 1] = dArr[1] - dArr[0];
            dArr[1] = dArr[0] + dArr[1];
            for (int i19 = i16 - 2; i19 >= 2; i19 -= 2) {
                int i20 = i19 * 2;
                int i21 = i19 + 1;
                dArr[i20 + 1] = dArr[i19] - dArr[i21];
                dArr[i20 - 1] = (-dArr[i19]) - dArr[i21];
            }
            int i22 = i15;
            int i23 = 2;
            for (int i24 = 2; i22 >= i24; i24 = 2) {
                f(i22, dArr2, i7, dArr3, i5);
                if (i22 > i2) {
                    int i25 = i22;
                    double[] dArr6 = dArr2;
                    int i26 = i5;
                    a(i25, dArr6, iArr, 2, i26, dArr3);
                    c(i25, dArr6, i7, dArr3, i26);
                } else if (i22 == i2) {
                    a(i22, dArr2, iArr, 2, i5, dArr3);
                }
                dArr[i3 - i23] = dArr2[1] - dArr2[0];
                dArr[i23] = dArr2[0] + dArr2[1];
                int i27 = 0;
                for (int i28 = 2; i28 < i22; i28 += 2) {
                    i27 += i23 << 2;
                    int i29 = i28 + 1;
                    dArr[i27 - i23] = (-dArr2[i28]) - dArr2[i29];
                    dArr[i27 + i23] = dArr2[i28] - dArr2[i29];
                }
                i23 <<= 1;
                int i30 = i22 >> 1;
                for (int i31 = 1; i31 < i30; i31++) {
                    int i32 = i22 - i31;
                    int i33 = i22 + i32;
                    int i34 = i22 + i31;
                    dArr2[i31] = dArr2[i33] + dArr2[i34];
                    dArr2[i32] = dArr2[i33] - dArr2[i34];
                }
                dArr2[0] = dArr2[i22 + i30];
                i22 = i30;
            }
            c = 0;
            dArr[i23] = dArr2[0];
        } else {
            c = 0;
        }
        dArr[c] = 0.0d;
    }

    private void a(int i, int[] iArr, double[] dArr) {
        int i2 = i;
        iArr[0] = i2;
        iArr[1] = 1;
        if (i2 > 2) {
            int i3 = i2 >> 1;
            double d = (double) i3;
            Double.isNaN(d);
            double d2 = 0.7853981633974483d / d;
            Double.isNaN(d);
            double cos = Math.cos(d * d2);
            dArr[0] = 1.0d;
            dArr[1] = cos;
            if (i3 >= 4) {
                dArr[2] = 0.5d / Math.cos(2.0d * d2);
                dArr[3] = 0.5d / Math.cos(6.0d * d2);
            }
            for (int i4 = 4; i4 < i3; i4 += 4) {
                double d3 = (double) i4;
                Double.isNaN(d3);
                double d4 = d2 * d3;
                dArr[i4] = Math.cos(d4);
                dArr[i4 + 1] = Math.sin(d4);
                Double.isNaN(d3);
                double d5 = 3.0d * d2 * d3;
                dArr[i4 + 2] = Math.cos(d5);
                dArr[i4 + 3] = Math.sin(d5);
            }
            int i5 = 0;
            while (i3 > 2) {
                int i6 = i5 + i3;
                i3 >>= 1;
                dArr[i6] = 1.0d;
                dArr[i6 + 1] = cos;
                if (i3 >= 4) {
                    double d6 = dArr[i5 + 4];
                    double d7 = dArr[i5 + 6];
                    dArr[i6 + 2] = 0.5d / d6;
                    dArr[i6 + 3] = 0.5d / d7;
                }
                for (int i7 = 4; i7 < i3; i7 += 4) {
                    int i8 = (i7 * 2) + i5;
                    double d8 = dArr[i8];
                    double d9 = dArr[i8 + 1];
                    double d10 = dArr[i8 + 2];
                    double d11 = dArr[i8 + 3];
                    int i9 = i6 + i7;
                    dArr[i9] = d8;
                    dArr[i9 + 1] = d9;
                    dArr[i9 + 2] = d10;
                    dArr[i9 + 3] = d11;
                }
                i5 = i6;
            }
        }
    }

    private void a(int i, int[] iArr, double[] dArr, int i2) {
        iArr[1] = i;
        if (i > 1) {
            int i3 = i >> 1;
            double d = (double) i3;
            Double.isNaN(d);
            double d2 = 0.7853981633974483d / d;
            int i4 = i2 + 0;
            Double.isNaN(d);
            dArr[i4] = Math.cos(d * d2);
            dArr[i2 + i3] = dArr[i4] * 0.5d;
            for (int i5 = 1; i5 < i3; i5++) {
                double d3 = (double) i5;
                Double.isNaN(d3);
                double d4 = d3 * d2;
                dArr[i2 + i5] = Math.cos(d4) * 0.5d;
                dArr[(i2 + i) - i5] = Math.sin(d4) * 0.5d;
            }
        }
    }

    private void a(int i, double[] dArr, int[] iArr, int i2, int i3, double[] dArr2) {
        int i4 = i;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (i4 > 32) {
            int i5 = i4 >> 2;
            a(i, dArr, dArr4, i3 - i5);
            if (i4 > 512) {
                int i6 = i5;
                double[] dArr5 = dArr;
                int i7 = i3;
                double[] dArr6 = dArr2;
                a(i6, dArr5, 0, i7, dArr6);
                b(i6, dArr5, i5, i7, dArr6);
                a(i6, dArr5, i5 * 2, i7, dArr6);
                a(i6, dArr5, i5 * 3, i7, dArr6);
            } else if (i5 > 32) {
                c(i, dArr, 0, i3, dArr2);
            } else {
                e(i, dArr, 0, i3, dArr2);
            }
            int[] iArr2 = iArr;
            int i8 = i2;
            a(i, iArr, i2, dArr);
        } else if (i4 > 8) {
            if (i4 == 32) {
                a(dArr, 0, dArr4, i3 - 8);
                a(dArr);
                return;
            }
            c(dArr, 0, dArr4, 0);
            c(dArr);
        } else if (i4 == 8) {
            e(dArr);
        } else if (i4 == 4) {
            g(dArr);
        }
    }

    private void b(int i, double[] dArr, int[] iArr, int i2, int i3, double[] dArr2) {
        int i4 = i;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (i4 > 32) {
            int i5 = i4 >> 2;
            b(i, dArr, dArr4, i3 - i5);
            if (i4 > 512) {
                int i6 = i5;
                double[] dArr5 = dArr;
                int i7 = i3;
                double[] dArr6 = dArr2;
                a(i6, dArr5, 0, i7, dArr6);
                b(i6, dArr5, i5, i7, dArr6);
                a(i6, dArr5, i5 * 2, i7, dArr6);
                a(i6, dArr5, i5 * 3, i7, dArr6);
            } else if (i5 > 32) {
                c(i, dArr, 0, i3, dArr2);
            } else {
                e(i, dArr, 0, i3, dArr2);
            }
            int[] iArr2 = iArr;
            int i8 = i2;
            b(i, iArr, i2, dArr);
        } else if (i4 > 8) {
            if (i4 == 32) {
                a(dArr, 0, dArr4, i3 - 8);
                b(dArr);
                return;
            }
            c(dArr, 0, dArr4, 0);
            d(dArr);
        } else if (i4 == 8) {
            f(dArr);
        } else if (i4 == 4) {
            g(dArr);
        }
    }

    private final void a(int i, int[] iArr, int i2, double[] dArr) {
        int i3;
        iArr[i2 + 0] = 0;
        int i4 = i;
        int i5 = 1;
        while (true) {
            i3 = i5 << 3;
            if (i3 >= i4) {
                break;
            }
            i4 >>= 1;
            for (int i6 = 0; i6 < i5; i6++) {
                iArr[i2 + i5 + i6] = iArr[i2 + i6] + i4;
            }
            i5 <<= 1;
        }
        int i7 = i5 * 2;
        if (i3 == i4) {
            for (int i8 = 0; i8 < i5; i8++) {
                for (int i9 = 0; i9 < i8; i9++) {
                    int i10 = (i9 * 2) + iArr[i2 + i8];
                    int i11 = (i8 * 2) + iArr[i2 + i9];
                    double d = dArr[i10];
                    int i12 = i10 + 1;
                    double d2 = dArr[i12];
                    double d3 = dArr[i11];
                    int i13 = i11 + 1;
                    double d4 = dArr[i13];
                    dArr[i10] = d3;
                    dArr[i12] = d4;
                    dArr[i11] = d;
                    dArr[i13] = d2;
                    int i14 = i10 + i7;
                    int i15 = i7 * 2;
                    int i16 = i11 + i15;
                    double d5 = dArr[i14];
                    int i17 = i14 + 1;
                    double d6 = dArr[i17];
                    double d7 = dArr[i16];
                    int i18 = i16 + 1;
                    double d8 = dArr[i18];
                    dArr[i14] = d7;
                    dArr[i17] = d8;
                    dArr[i16] = d5;
                    dArr[i18] = d6;
                    int i19 = i14 + i7;
                    int i20 = i16 - i7;
                    double d9 = dArr[i19];
                    int i21 = i19 + 1;
                    double d10 = dArr[i21];
                    double d11 = dArr[i20];
                    int i22 = i20 + 1;
                    double d12 = dArr[i22];
                    dArr[i19] = d11;
                    dArr[i21] = d12;
                    dArr[i20] = d9;
                    dArr[i22] = d10;
                    int i23 = i19 + i7;
                    int i24 = i20 + i15;
                    double d13 = dArr[i23];
                    int i25 = i23 + 1;
                    double d14 = dArr[i25];
                    double d15 = dArr[i24];
                    int i26 = i24 + 1;
                    double d16 = dArr[i26];
                    dArr[i23] = d15;
                    dArr[i25] = d16;
                    dArr[i24] = d13;
                    dArr[i26] = d14;
                }
                int i27 = (i8 * 2) + i7 + iArr[i2 + i8];
                int i28 = i27 + i7;
                double d17 = dArr[i27];
                int i29 = i27 + 1;
                double d18 = dArr[i29];
                double d19 = dArr[i28];
                int i30 = i28 + 1;
                double d20 = dArr[i30];
                dArr[i27] = d19;
                dArr[i29] = d20;
                dArr[i28] = d17;
                dArr[i30] = d18;
            }
            return;
        }
        for (int i31 = 1; i31 < i5; i31++) {
            for (int i32 = 0; i32 < i31; i32++) {
                int i33 = (i32 * 2) + iArr[i2 + i31];
                int i34 = (i31 * 2) + iArr[i2 + i32];
                double d21 = dArr[i33];
                int i35 = i33 + 1;
                double d22 = dArr[i35];
                double d23 = dArr[i34];
                int i36 = i34 + 1;
                double d24 = dArr[i36];
                dArr[i33] = d23;
                dArr[i35] = d24;
                dArr[i34] = d21;
                dArr[i36] = d22;
                int i37 = i33 + i7;
                int i38 = i34 + i7;
                double d25 = dArr[i37];
                int i39 = i37 + 1;
                double d26 = dArr[i39];
                double d27 = dArr[i38];
                int i40 = i38 + 1;
                double d28 = dArr[i40];
                dArr[i37] = d27;
                dArr[i39] = d28;
                dArr[i38] = d25;
                dArr[i40] = d26;
            }
        }
    }

    private final void b(int i, int[] iArr, int i2, double[] dArr) {
        int i3;
        iArr[i2 + 0] = 0;
        int i4 = i;
        int i5 = 1;
        while (true) {
            i3 = i5 << 3;
            if (i3 >= i4) {
                break;
            }
            i4 >>= 1;
            for (int i6 = 0; i6 < i5; i6++) {
                iArr[i2 + i5 + i6] = iArr[i2 + i6] + i4;
            }
            i5 <<= 1;
        }
        int i7 = i5 * 2;
        if (i3 == i4) {
            int i8 = 0;
            while (i8 < i5) {
                int i9 = 0;
                while (i9 < i8) {
                    int i10 = (i9 * 2) + iArr[i2 + i8];
                    int i11 = (i8 * 2) + iArr[i2 + i9];
                    double d = dArr[i10];
                    int i12 = i10 + 1;
                    int i13 = i11 + 1;
                    int i14 = i5;
                    dArr[i10] = dArr[i11];
                    dArr[i12] = -dArr[i13];
                    dArr[i11] = d;
                    dArr[i13] = -dArr[i12];
                    int i15 = i10 + i7;
                    int i16 = i7 * 2;
                    int i17 = i11 + i16;
                    double d2 = dArr[i15];
                    int i18 = i15 + 1;
                    int i19 = i17 + 1;
                    dArr[i15] = dArr[i17];
                    dArr[i18] = -dArr[i19];
                    dArr[i17] = d2;
                    dArr[i19] = -dArr[i18];
                    int i20 = i15 + i7;
                    int i21 = i17 - i7;
                    double d3 = dArr[i20];
                    int i22 = i20 + 1;
                    int i23 = i21 + 1;
                    dArr[i20] = dArr[i21];
                    dArr[i22] = -dArr[i23];
                    dArr[i21] = d3;
                    dArr[i23] = -dArr[i22];
                    int i24 = i20 + i7;
                    int i25 = i21 + i16;
                    double d4 = dArr[i24];
                    int i26 = i24 + 1;
                    int i27 = i25 + 1;
                    dArr[i24] = dArr[i25];
                    dArr[i26] = -dArr[i27];
                    dArr[i25] = d4;
                    dArr[i27] = -dArr[i26];
                    i9++;
                    i5 = i14;
                }
                int i28 = i5;
                int i29 = (i8 * 2) + iArr[i2 + i8];
                int i30 = i29 + 1;
                dArr[i30] = -dArr[i30];
                int i31 = i29 + i7;
                int i32 = i31 + i7;
                double d5 = dArr[i31];
                int i33 = i31 + 1;
                double d6 = dArr[i32];
                int i34 = i32 + 1;
                double d7 = -dArr[i33];
                dArr[i31] = d6;
                dArr[i33] = -dArr[i34];
                dArr[i32] = d5;
                dArr[i34] = d7;
                int i35 = i32 + i7 + 1;
                dArr[i35] = -dArr[i35];
                i8++;
                i5 = i28;
            }
            return;
        }
        dArr[1] = -dArr[1];
        int i36 = i7 + 1;
        dArr[i36] = -dArr[i36];
        int i37 = i5;
        int i38 = 1;
        while (i38 < i37) {
            int i39 = 0;
            while (i39 < i38) {
                int i40 = (i39 * 2) + iArr[i2 + i38];
                int i41 = (i38 * 2) + iArr[i2 + i39];
                double d8 = dArr[i40];
                int i42 = i40 + 1;
                int i43 = i41 + 1;
                int i44 = i37;
                dArr[i40] = dArr[i41];
                dArr[i42] = -dArr[i43];
                dArr[i41] = d8;
                dArr[i43] = -dArr[i42];
                int i45 = i40 + i7;
                int i46 = i41 + i7;
                double d9 = dArr[i45];
                int i47 = i45 + 1;
                int i48 = i46 + 1;
                dArr[i45] = dArr[i46];
                dArr[i47] = -dArr[i48];
                dArr[i46] = d9;
                dArr[i48] = -dArr[i47];
                i39++;
                i37 = i44;
                i38 = i38;
            }
            int i49 = i38;
            int i50 = i37;
            int i51 = (i49 * 2) + iArr[i2 + i49];
            int i52 = i51 + 1;
            dArr[i52] = -dArr[i52];
            int i53 = i51 + i7 + 1;
            dArr[i53] = -dArr[i53];
            i38 = i49 + 1;
            i37 = i50;
        }
    }

    private void a(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[14];
        double d12 = dArr[15];
        double d13 = dArr[16];
        double d14 = dArr[17];
        double d15 = dArr[20];
        double d16 = dArr[21];
        double d17 = dArr[22];
        double d18 = dArr[23];
        double d19 = dArr[24];
        double d20 = dArr[25];
        double d21 = dArr[26];
        double d22 = dArr[27];
        double d23 = dArr[28];
        double d24 = dArr[29];
        dArr[2] = d13;
        dArr[3] = d14;
        dArr[4] = d7;
        dArr[5] = d8;
        dArr[6] = d19;
        dArr[7] = d20;
        dArr[8] = d3;
        dArr[9] = d4;
        dArr[10] = d15;
        dArr[11] = d16;
        dArr[14] = d23;
        dArr[15] = d24;
        dArr[16] = d;
        dArr[17] = d2;
        dArr[20] = d9;
        dArr[21] = d10;
        dArr[22] = d21;
        dArr[23] = d22;
        dArr[24] = d5;
        dArr[25] = d6;
        dArr[26] = d17;
        dArr[27] = d18;
        dArr[28] = d11;
        dArr[29] = d12;
    }

    private void b(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[12];
        double d12 = dArr[13];
        double d13 = dArr[14];
        double d14 = dArr[15];
        double d15 = dArr[16];
        double d16 = dArr[17];
        double d17 = dArr[18];
        double d18 = dArr[19];
        double d19 = dArr[20];
        double d20 = dArr[21];
        double d21 = dArr[22];
        double d22 = dArr[23];
        double d23 = dArr[24];
        double d24 = dArr[25];
        double d25 = dArr[26];
        double d26 = dArr[27];
        double d27 = dArr[28];
        double d28 = dArr[29];
        double d29 = dArr[30];
        double d30 = dArr[31];
        dArr[2] = d29;
        dArr[3] = d30;
        dArr[4] = d13;
        dArr[5] = d14;
        dArr[6] = d21;
        dArr[7] = d22;
        dArr[8] = d5;
        dArr[9] = d6;
        dArr[10] = d25;
        dArr[11] = d26;
        dArr[12] = d9;
        dArr[13] = d10;
        dArr[14] = d17;
        dArr[15] = d18;
        dArr[16] = d;
        dArr[17] = d2;
        dArr[18] = d27;
        dArr[19] = d28;
        dArr[20] = d11;
        dArr[21] = d12;
        dArr[22] = d19;
        dArr[23] = d20;
        dArr[24] = d3;
        dArr[25] = d4;
        dArr[26] = d23;
        dArr[27] = d24;
        dArr[28] = d7;
        dArr[29] = d8;
        dArr[30] = d15;
        dArr[31] = d16;
    }

    private void c(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[6];
        double d4 = dArr[7];
        double d5 = dArr[8];
        double d6 = dArr[9];
        double d7 = dArr[12];
        double d8 = dArr[13];
        dArr[2] = d5;
        dArr[3] = d6;
        dArr[6] = d7;
        dArr[7] = d8;
        dArr[8] = d;
        dArr[9] = d2;
        dArr[12] = d3;
        dArr[13] = d4;
    }

    private void d(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[12];
        double d12 = dArr[13];
        double d13 = dArr[14];
        double d14 = dArr[15];
        dArr[2] = d13;
        dArr[3] = d14;
        dArr[4] = d5;
        dArr[5] = d6;
        dArr[6] = d9;
        dArr[7] = d10;
        dArr[8] = d;
        dArr[9] = d2;
        dArr[10] = d11;
        dArr[11] = d12;
        dArr[12] = d3;
        dArr[13] = d4;
        dArr[14] = d7;
        dArr[15] = d8;
    }

    private void a(int i, double[] dArr, double[] dArr2, int i2) {
        int i3 = i >> 3;
        int i4 = i3 * 2;
        int i5 = i4 + i4;
        int i6 = i5 + i4;
        double d = dArr[0] + dArr[i5];
        int i7 = i5 + 1;
        double d2 = dArr[1] + dArr[i7];
        double d3 = dArr[0] - dArr[i5];
        double d4 = dArr[1] - dArr[i7];
        double d5 = dArr[i4] + dArr[i6];
        int i8 = i4 + 1;
        int i9 = i6 + 1;
        double d6 = dArr[i8] + dArr[i9];
        double d7 = dArr[i4] - dArr[i6];
        double d8 = dArr[i8] - dArr[i9];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[i4] = d - d5;
        dArr[i8] = d2 - d6;
        dArr[i5] = d3 - d8;
        dArr[i7] = d4 + d7;
        dArr[i6] = d3 + d8;
        dArr[i9] = d4 - d7;
        double d9 = dArr2[i2 + 1];
        double d10 = dArr2[i2 + 2];
        double d11 = dArr2[i2 + 3];
        double d12 = 1.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 1.0d;
        int i10 = 2;
        int i11 = 0;
        while (true) {
            int i12 = i3 - 2;
            if (i10 < i12) {
                int i13 = i11 + 4;
                int i14 = i2 + i13;
                double d16 = (d12 + dArr2[i14]) * d10;
                int i15 = i14 + 1;
                double d17 = (d13 + dArr2[i15]) * d10;
                int i16 = i14 + 2;
                double d18 = (d15 + dArr2[i16]) * d11;
                int i17 = i14 + 3;
                double d19 = (d14 - dArr2[i17]) * d11;
                double d20 = dArr2[i14];
                double d21 = dArr2[i15];
                double d22 = dArr2[i16];
                int i18 = i13;
                double d23 = -dArr2[i17];
                int i19 = i10 + i4;
                int i20 = i19 + i4;
                int i21 = i20 + i4;
                double d24 = dArr[i10] + dArr[i20];
                int i22 = i10 + 1;
                int i23 = i20 + 1;
                double d25 = dArr[i22] + dArr[i23];
                double d26 = dArr[i10] - dArr[i20];
                double d27 = dArr[i22] - dArr[i23];
                int i24 = i10 + 2;
                int i25 = i20 + 2;
                double d28 = dArr[i24] + dArr[i25];
                int i26 = i10 + 3;
                int i27 = i20 + 3;
                double d29 = dArr[i26] + dArr[i27];
                double d30 = dArr[i24] - dArr[i25];
                double d31 = dArr[i26] - dArr[i27];
                double d32 = dArr[i19] + dArr[i21];
                int i28 = i19 + 1;
                int i29 = i21 + 1;
                double d33 = dArr[i28] + dArr[i29];
                double d34 = dArr[i19] - dArr[i21];
                double d35 = dArr[i28] - dArr[i29];
                int i30 = i19 + 2;
                int i31 = i21 + 2;
                double d36 = dArr[i30] + dArr[i31];
                int i32 = i19 + 3;
                int i33 = i21 + 3;
                double d37 = dArr[i32] + dArr[i33];
                double d38 = dArr[i30] - dArr[i31];
                double d39 = dArr[i32] - dArr[i33];
                dArr[i10] = d24 + d32;
                dArr[i22] = d25 + d33;
                dArr[i24] = d28 + d36;
                dArr[i26] = d29 + d37;
                dArr[i19] = d24 - d32;
                dArr[i28] = d25 - d33;
                dArr[i30] = d28 - d36;
                dArr[i32] = d29 - d37;
                double d40 = d26 - d35;
                double d41 = d27 + d34;
                dArr[i20] = (d16 * d40) - (d17 * d41);
                dArr[i23] = (d41 * d16) + (d40 * d17);
                double d42 = d30 - d39;
                double d43 = d31 + d38;
                dArr[i25] = (d20 * d42) - (d21 * d43);
                dArr[i27] = (d43 * d20) + (d42 * d21);
                double d44 = d26 + d35;
                double d45 = d27 - d34;
                dArr[i21] = (d18 * d44) + (d19 * d45);
                dArr[i29] = (d45 * d18) - (d44 * d19);
                double d46 = d30 + d39;
                double d47 = d31 - d38;
                dArr[i31] = (d22 * d46) + (d23 * d47);
                dArr[i33] = (d47 * d22) - (d46 * d23);
                int i34 = i4 - i10;
                int i35 = i34 + i4;
                int i36 = i35 + i4;
                int i37 = i36 + i4;
                double d48 = dArr[i34] + dArr[i36];
                int i38 = i34 + 1;
                int i39 = i36 + 1;
                double d49 = dArr[i38] + dArr[i39];
                double d50 = dArr[i34] - dArr[i36];
                double d51 = dArr[i38] - dArr[i39];
                int i40 = i34 - 2;
                int i41 = i36 - 2;
                double d52 = dArr[i40] + dArr[i41];
                int i42 = i34 - 1;
                int i43 = i36 - 1;
                double d53 = dArr[i42] + dArr[i43];
                double d54 = dArr[i40] - dArr[i41];
                double d55 = dArr[i42] - dArr[i43];
                double d56 = dArr[i35] + dArr[i37];
                int i44 = i35 + 1;
                int i45 = i37 + 1;
                double d57 = dArr[i44] + dArr[i45];
                double d58 = dArr[i35] - dArr[i37];
                double d59 = dArr[i44] - dArr[i45];
                int i46 = i35 - 2;
                int i47 = i37 - 2;
                double d60 = dArr[i46] + dArr[i47];
                int i48 = i35 - 1;
                int i49 = i37 - 1;
                double d61 = dArr[i48] + dArr[i49];
                double d62 = dArr[i46] - dArr[i47];
                double d63 = dArr[i48] - dArr[i49];
                dArr[i34] = d48 + d56;
                dArr[i38] = d49 + d57;
                dArr[i40] = d52 + d60;
                dArr[i42] = d53 + d61;
                dArr[i35] = d48 - d56;
                dArr[i44] = d49 - d57;
                dArr[i46] = d52 - d60;
                dArr[i48] = d53 - d61;
                double d64 = d50 - d59;
                double d65 = d51 + d58;
                dArr[i36] = (d17 * d64) - (d16 * d65);
                dArr[i39] = (d17 * d65) + (d16 * d64);
                double d66 = d54 - d63;
                double d67 = d55 + d62;
                dArr[i41] = (d21 * d66) - (d20 * d67);
                dArr[i43] = (d67 * d21) + (d66 * d20);
                double d68 = d50 + d59;
                double d69 = d51 - d58;
                dArr[i37] = (d19 * d68) + (d18 * d69);
                dArr[i45] = (d19 * d69) - (d18 * d68);
                double d70 = d54 + d63;
                double d71 = d55 - d62;
                dArr[i47] = (d23 * d70) + (d22 * d71);
                dArr[i49] = (d71 * d23) - (d70 * d22);
                i10 += 4;
                d14 = d23;
                d15 = d22;
                d12 = d20;
                d13 = d21;
                i11 = i18;
            } else {
                double d72 = (d12 + d9) * d10;
                double d73 = d10 * (d13 + d9);
                double d74 = (d15 - d9) * d11;
                double d75 = d11 * (d14 - d9);
                int i50 = i3 + i4;
                int i51 = i50 + i4;
                int i52 = i4 + i51;
                int i53 = i51 - 2;
                double d76 = dArr[i12] + dArr[i53];
                int i54 = i3 - 1;
                int i55 = i51 - 1;
                double d77 = dArr[i54] + dArr[i55];
                double d78 = dArr[i12] - dArr[i53];
                double d79 = dArr[i54] - dArr[i55];
                int i56 = i50 - 2;
                int i57 = i52 - 2;
                double d80 = dArr[i56] + dArr[i57];
                int i58 = i50 - 1;
                int i59 = i52 - 1;
                double d81 = dArr[i58] + dArr[i59];
                double d82 = dArr[i56] - dArr[i57];
                double d83 = dArr[i58] - dArr[i59];
                dArr[i12] = d76 + d80;
                dArr[i54] = d77 + d81;
                dArr[i56] = d76 - d80;
                dArr[i58] = d77 - d81;
                double d84 = d78 - d83;
                double d85 = d79 + d82;
                dArr[i53] = (d72 * d84) - (d73 * d85);
                dArr[i55] = (d85 * d72) + (d84 * d73);
                double d86 = d78 + d83;
                double d87 = d79 - d82;
                dArr[i57] = (d74 * d86) + (d75 * d87);
                dArr[i59] = (d87 * d74) - (d86 * d75);
                double d88 = dArr[i3] + dArr[i51];
                int i60 = i3 + 1;
                int i61 = i51 + 1;
                double d89 = dArr[i60] + dArr[i61];
                double d90 = dArr[i3] - dArr[i51];
                double d91 = dArr[i60] - dArr[i61];
                double d92 = dArr[i50] + dArr[i52];
                int i62 = i50 + 1;
                int i63 = i52 + 1;
                double d93 = dArr[i62] + dArr[i63];
                double d94 = dArr[i50] - dArr[i52];
                double d95 = dArr[i62] - dArr[i63];
                dArr[i3] = d88 + d92;
                dArr[i60] = d89 + d93;
                dArr[i50] = d88 - d92;
                dArr[i62] = d89 - d93;
                double d96 = d90 - d95;
                double d97 = d91 + d94;
                dArr[i51] = (d96 - d97) * d9;
                dArr[i61] = (d97 + d96) * d9;
                double d98 = d90 + d95;
                double d99 = d91 - d94;
                double d100 = -d9;
                dArr[i52] = (d98 + d99) * d100;
                dArr[i63] = d100 * (d99 - d98);
                int i64 = i3 + 2;
                int i65 = i51 + 2;
                double d101 = dArr[i64] + dArr[i65];
                int i66 = i3 + 3;
                int i67 = i51 + 3;
                double d102 = dArr[i66] + dArr[i67];
                double d103 = dArr[i64] - dArr[i65];
                double d104 = dArr[i66] - dArr[i67];
                int i68 = i50 + 2;
                int i69 = i52 + 2;
                double d105 = dArr[i68] + dArr[i69];
                int i70 = i50 + 3;
                int i71 = i52 + 3;
                double d106 = dArr[i70] + dArr[i71];
                double d107 = dArr[i68] - dArr[i69];
                double d108 = dArr[i70] - dArr[i71];
                dArr[i64] = d101 + d105;
                dArr[i66] = d102 + d106;
                dArr[i68] = d101 - d105;
                dArr[i70] = d102 - d106;
                double d109 = d103 - d108;
                double d110 = d104 + d107;
                dArr[i65] = (d73 * d109) - (d72 * d110);
                dArr[i67] = (d73 * d110) + (d72 * d109);
                double d111 = d103 + d108;
                double d112 = d104 - d107;
                dArr[i69] = (d75 * d111) + (d74 * d112);
                dArr[i71] = (d75 * d112) - (d74 * d111);
                return;
            }
        }
    }

    private final void b(int i, double[] dArr, double[] dArr2, int i2) {
        int i3 = i >> 3;
        int i4 = i3 * 2;
        int i5 = i4 + i4;
        int i6 = i5 + i4;
        int i7 = i5 + 1;
        double d = (-dArr[1]) - dArr[i7];
        double d2 = dArr[0] - dArr[i5];
        double d3 = dArr[0] + dArr[i5];
        double d4 = (-dArr[1]) + dArr[i7];
        double d5 = dArr[i4] + dArr[i6];
        int i8 = i4 + 1;
        int i9 = i6 + 1;
        double d6 = dArr[i8] + dArr[i9];
        double d7 = dArr[i4] - dArr[i6];
        double d8 = dArr[i8] - dArr[i9];
        int i10 = 0;
        dArr[0] = d3 + d5;
        dArr[1] = d - d6;
        dArr[i4] = d3 - d5;
        dArr[i8] = d + d6;
        dArr[i5] = d2 + d8;
        dArr[i7] = d4 + d7;
        dArr[i6] = d2 - d8;
        dArr[i9] = d4 - d7;
        double d9 = dArr2[i2 + 1];
        double d10 = dArr2[i2 + 2];
        double d11 = dArr2[i2 + 3];
        double d12 = 0.0d;
        double d13 = 1.0d;
        int i11 = 2;
        double d14 = 0.0d;
        double d15 = 1.0d;
        while (true) {
            double d16 = d9;
            int i12 = i3 - 2;
            if (i11 < i12) {
                i10 += 4;
                int i13 = i2 + i10;
                double d17 = (d13 + dArr2[i13]) * d10;
                int i14 = i13 + 1;
                double d18 = (d12 + dArr2[i14]) * d10;
                int i15 = i13 + 2;
                double d19 = (d15 + dArr2[i15]) * d11;
                int i16 = i13 + 3;
                double d20 = (d14 - dArr2[i16]) * d11;
                double d21 = dArr2[i13];
                double d22 = dArr2[i14];
                double d23 = dArr2[i15];
                int i17 = i3;
                int i18 = i11 + i4;
                int i19 = i18 + i4;
                int i20 = i19 + i4;
                double d24 = dArr[i11] + dArr[i19];
                int i21 = i11 + 1;
                double d25 = d11;
                int i22 = i19 + 1;
                double d26 = (-dArr[i21]) - dArr[i22];
                double d27 = dArr[i11] - dArr[i19];
                double d28 = d10;
                double d29 = (-dArr[i21]) + dArr[i22];
                int i23 = i11 + 2;
                int i24 = i19 + 2;
                double d30 = dArr[i23] + dArr[i24];
                int i25 = i11 + 3;
                double d31 = -dArr2[i16];
                int i26 = i19 + 3;
                double d32 = (-dArr[i25]) - dArr[i26];
                double d33 = dArr[i23] - dArr[i24];
                int i27 = i4;
                double d34 = d22;
                double d35 = (-dArr[i25]) + dArr[i26];
                double d36 = dArr[i18] + dArr[i20];
                int i28 = i18 + 1;
                int i29 = i20 + 1;
                double d37 = dArr[i28] + dArr[i29];
                double d38 = dArr[i18] - dArr[i20];
                double d39 = dArr[i28] - dArr[i29];
                int i30 = i18 + 2;
                int i31 = i20 + 2;
                double d40 = dArr[i30] + dArr[i31];
                int i32 = i18 + 3;
                int i33 = i20 + 3;
                double d41 = dArr[i32] + dArr[i33];
                double d42 = dArr[i30] - dArr[i31];
                double d43 = dArr[i32] - dArr[i33];
                dArr[i11] = d24 + d36;
                dArr[i21] = d26 - d37;
                dArr[i23] = d30 + d40;
                dArr[i25] = d32 - d41;
                dArr[i18] = d24 - d36;
                dArr[i28] = d26 + d37;
                dArr[i30] = d30 - d40;
                dArr[i32] = d32 + d41;
                double d44 = d27 + d39;
                double d45 = d29 + d38;
                dArr[i19] = (d17 * d44) - (d18 * d45);
                dArr[i22] = (d45 * d17) + (d44 * d18);
                double d46 = d33 + d43;
                double d47 = d35 + d42;
                dArr[i24] = (d21 * d46) - (d34 * d47);
                dArr[i26] = (d47 * d21) + (d46 * d34);
                double d48 = d27 - d39;
                double d49 = d29 - d38;
                dArr[i20] = (d19 * d48) + (d20 * d49);
                dArr[i29] = (d49 * d19) - (d48 * d20);
                double d50 = d33 - d43;
                double d51 = d35 - d42;
                dArr[i31] = (d23 * d50) + (d31 * d51);
                dArr[i33] = (d51 * d23) - (d31 * d50);
                int i34 = i27 - i11;
                int i35 = i34 + i27;
                int i36 = i35 + i27;
                int i37 = i36 + i27;
                double d52 = dArr[i34] + dArr[i36];
                int i38 = i34 + 1;
                int i39 = i36 + 1;
                double d53 = (-dArr[i38]) - dArr[i39];
                double d54 = dArr[i34] - dArr[i36];
                double d55 = d17;
                double d56 = (-dArr[i38]) + dArr[i39];
                int i40 = i34 - 2;
                int i41 = i36 - 2;
                double d57 = dArr[i40] + dArr[i41];
                int i42 = i34 - 1;
                int i43 = i39;
                double d58 = d18;
                int i44 = i36 - 1;
                double d59 = (-dArr[i42]) - dArr[i44];
                double d60 = dArr[i40] - dArr[i41];
                double d61 = d56;
                double d62 = (-dArr[i42]) + dArr[i44];
                double d63 = dArr[i35] + dArr[i37];
                int i45 = i35 + 1;
                int i46 = i37 + 1;
                double d64 = dArr[i45] + dArr[i46];
                double d65 = dArr[i35] - dArr[i37];
                double d66 = dArr[i45] - dArr[i46];
                int i47 = i35 - 2;
                int i48 = i37 - 2;
                double d67 = dArr[i47] + dArr[i48];
                int i49 = i35 - 1;
                int i50 = i37 - 1;
                double d68 = dArr[i49] + dArr[i50];
                double d69 = dArr[i47] - dArr[i48];
                double d70 = dArr[i49] - dArr[i50];
                dArr[i34] = d52 + d63;
                dArr[i38] = d53 - d64;
                dArr[i40] = d57 + d67;
                dArr[i42] = d59 - d68;
                dArr[i35] = d52 - d63;
                dArr[i45] = d53 + d64;
                dArr[i47] = d57 - d67;
                dArr[i49] = d59 + d68;
                double d71 = d54 + d66;
                double d72 = d61 + d65;
                dArr[i36] = (d58 * d71) - (d55 * d72);
                dArr[i43] = (d58 * d72) + (d71 * d55);
                double d73 = d60 + d70;
                double d74 = d62 + d69;
                dArr[i41] = (d34 * d73) - (d21 * d74);
                dArr[i44] = (d34 * d74) + (d73 * d21);
                double d75 = d54 - d66;
                double d76 = d61 - d65;
                dArr[i37] = (d20 * d75) + (d19 * d76);
                dArr[i46] = (d20 * d76) - (d19 * d75);
                double d77 = d60 - d70;
                double d78 = d62 - d69;
                dArr[i48] = (d31 * d77) + (d23 * d78);
                dArr[i50] = (d31 * d78) - (d77 * d23);
                i11 += 4;
                d13 = d21;
                d15 = d23;
                d9 = d16;
                i3 = i17;
                d11 = d25;
                d10 = d28;
                d14 = d31;
                i4 = i27;
                d12 = d34;
            } else {
                int i51 = i3;
                int i52 = i4;
                double d79 = d10;
                double d80 = d11;
                double d81 = d79 * (d13 + d16);
                double d82 = d79 * (d12 + d16);
                double d83 = d80 * (d15 - d16);
                int i53 = i51 + i52;
                int i54 = i53 + i52;
                int i55 = i54 + i52;
                int i56 = i54 - 2;
                double d84 = dArr[i12] + dArr[i56];
                int i57 = i51 - 1;
                double d85 = d80 * (d14 - d16);
                int i58 = i54 - 1;
                double d86 = (-dArr[i57]) - dArr[i58];
                double d87 = dArr[i12] - dArr[i56];
                double d88 = d83;
                double d89 = (-dArr[i57]) + dArr[i58];
                int i59 = i53 - 2;
                int i60 = i55 - 2;
                double d90 = dArr[i59] + dArr[i60];
                int i61 = i53 - 1;
                int i62 = i55 - 1;
                double d91 = dArr[i61] + dArr[i62];
                double d92 = dArr[i59] - dArr[i60];
                double d93 = dArr[i61] - dArr[i62];
                dArr[i12] = d84 + d90;
                dArr[i57] = d86 - d91;
                dArr[i59] = d84 - d90;
                dArr[i61] = d86 + d91;
                double d94 = d87 + d93;
                double d95 = d89 + d92;
                dArr[i56] = (d81 * d94) - (d82 * d95);
                dArr[i58] = (d95 * d81) + (d94 * d82);
                double d96 = d87 - d93;
                double d97 = d89 - d92;
                dArr[i60] = (d88 * d96) + (d85 * d97);
                dArr[i62] = (d97 * d88) - (d85 * d96);
                double d98 = dArr[i51] + dArr[i54];
                int i63 = i51 + 1;
                int i64 = i54 + 1;
                double d99 = (-dArr[i63]) - dArr[i64];
                double d100 = dArr[i51] - dArr[i54];
                double d101 = d81;
                double d102 = (-dArr[i63]) + dArr[i64];
                double d103 = dArr[i53] + dArr[i55];
                int i65 = i53 + 1;
                int i66 = i55 + 1;
                double d104 = dArr[i65] + dArr[i66];
                double d105 = dArr[i53] - dArr[i55];
                double d106 = dArr[i65] - dArr[i66];
                dArr[i51] = d98 + d103;
                dArr[i63] = d99 - d104;
                dArr[i53] = d98 - d103;
                dArr[i65] = d99 + d104;
                double d107 = d100 + d106;
                double d108 = d102 + d105;
                dArr[i54] = (d107 - d108) * d16;
                dArr[i64] = d16 * (d108 + d107);
                double d109 = d100 - d106;
                double d110 = d102 - d105;
                double d111 = -d16;
                dArr[i55] = (d109 + d110) * d111;
                dArr[i66] = d111 * (d110 - d109);
                int i67 = i51 + 2;
                int i68 = i54 + 2;
                double d112 = dArr[i67] + dArr[i68];
                int i69 = i51 + 3;
                int i70 = i54 + 3;
                double d113 = (-dArr[i69]) - dArr[i70];
                double d114 = dArr[i67] - dArr[i68];
                double d115 = d82;
                double d116 = (-dArr[i69]) + dArr[i70];
                int i71 = i53 + 2;
                int i72 = i55 + 2;
                double d117 = dArr[i71] + dArr[i72];
                int i73 = i53 + 3;
                int i74 = i55 + 3;
                double d118 = dArr[i73] + dArr[i74];
                double d119 = dArr[i71] - dArr[i72];
                double d120 = dArr[i73] - dArr[i74];
                dArr[i67] = d112 + d117;
                dArr[i69] = d113 - d118;
                dArr[i71] = d112 - d117;
                dArr[i73] = d113 + d118;
                double d121 = d114 + d120;
                double d122 = d116 + d119;
                dArr[i68] = (d115 * d121) - (d101 * d122);
                dArr[i70] = (d122 * d115) + (d101 * d121);
                double d123 = d114 - d120;
                double d124 = d116 - d119;
                dArr[i72] = (d85 * d123) + (d88 * d124);
                dArr[i74] = (d85 * d124) - (d88 * d123);
                return;
            }
        }
    }

    private void a(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 2;
        int i5 = i4 * 2;
        a(i, dArr, i2, dArr2, i3 - i5);
        if (i > 512) {
            int i6 = i4;
            double[] dArr3 = dArr;
            int i7 = i3;
            double[] dArr4 = dArr2;
            a(i6, dArr3, i2, i7, dArr4);
            b(i6, dArr3, i2 + i4, i7, dArr4);
            a(i6, dArr3, i2 + i5, i7, dArr4);
            a(i6, dArr3, i2 + (i4 * 3), i7, dArr4);
            return;
        }
        c(i, dArr, i2, i3, dArr2);
    }

    private void b(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 2;
        b(i, dArr, i2, dArr2, i3 - i);
        if (i > 512) {
            int i5 = i4;
            double[] dArr3 = dArr;
            int i6 = i3;
            double[] dArr4 = dArr2;
            a(i5, dArr3, i2, i6, dArr4);
            b(i5, dArr3, i2 + i4, i6, dArr4);
            a(i5, dArr3, i2 + (i4 * 2), i6, dArr4);
            b(i5, dArr3, i2 + (i4 * 3), i6, dArr4);
            return;
        }
        d(i, dArr, i2, i3, dArr2);
    }

    private void c(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i;
        int i5 = i4 >> 2;
        while (i5 > 128) {
            for (int i6 = i5; i6 < i4; i6 <<= 2) {
                for (int i7 = i6 - i5; i7 < i4; i7 += i6 * 4) {
                    int i8 = i3 - (i5 >> 1);
                    int i9 = i5;
                    double[] dArr3 = dArr;
                    double[] dArr4 = dArr2;
                    a(i9, dArr3, i2 + i7, dArr4, i8);
                    b(i9, dArr3, i2 + i6 + i7, dArr4, i3 - i5);
                    a(i9, dArr3, i2 + (i6 * 2) + i7, dArr4, i8);
                }
            }
            a(i5, dArr, (i2 + i4) - i5, dArr2, i3 - (i5 >> 1));
            i5 >>= 2;
        }
        int i10 = i5;
        while (i10 < i4) {
            int i11 = i10 - i5;
            while (i11 < i4) {
                int i12 = i2 + i11;
                int i13 = i3 - (i5 >> 1);
                int i14 = i5;
                double[] dArr5 = dArr;
                double[] dArr6 = dArr2;
                a(i14, dArr5, i12, dArr6, i13);
                int i15 = i5;
                double[] dArr7 = dArr;
                int i16 = i11;
                int i17 = i3;
                int i18 = i10;
                double[] dArr8 = dArr2;
                e(i15, dArr7, i12, i17, dArr8);
                int i19 = i2 + i18 + i16;
                b(i14, dArr5, i19, dArr6, i3 - i5);
                f(i15, dArr7, i19, i17, dArr8);
                int i20 = i2 + (i18 * 2) + i16;
                a(i14, dArr5, i20, dArr6, i13);
                e(i15, dArr7, i20, i17, dArr2);
                i11 = i16 + (i18 * 4);
                i10 = i18;
            }
            i10 <<= 2;
        }
        int i21 = (i2 + i4) - i5;
        a(i5, dArr, i21, dArr2, i3 - (i5 >> 1));
        e(i5, dArr, i21, i3, dArr2);
    }

    private void d(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 1;
        int i5 = i >> 2;
        while (i5 > 128) {
            for (int i6 = i5; i6 < i4; i6 <<= 2) {
                for (int i7 = i6 - i5; i7 < i4; i7 += i6 * 2) {
                    int i8 = i5;
                    double[] dArr3 = dArr;
                    double[] dArr4 = dArr2;
                    int i9 = i3 - (i5 >> 1);
                    a(i8, dArr3, i2 + i7, dArr4, i9);
                    a(i8, dArr3, i2 + i4 + i7, dArr4, i9);
                }
                for (int i10 = (i6 * 2) - i5; i10 < i4; i10 += i6 * 4) {
                    int i11 = i5;
                    double[] dArr5 = dArr;
                    double[] dArr6 = dArr2;
                    int i12 = i3 - i5;
                    b(i11, dArr5, i2 + i10, dArr6, i12);
                    b(i11, dArr5, i2 + i4 + i10, dArr6, i12);
                }
            }
            i5 >>= 2;
        }
        for (int i13 = i5; i13 < i4; i13 <<= 2) {
            for (int i14 = i13 - i5; i14 < i4; i14 += i13 * 2) {
                int i15 = i3 - (i5 >> 1);
                int i16 = i5;
                double[] dArr7 = dArr;
                int i17 = i2 + i14;
                a(i16, dArr7, i17, dArr2, i15);
                e(i16, dArr7, i17, i3, dArr2);
                int i18 = i2 + i4 + i14;
                a(i16, dArr7, i18, dArr2, i15);
                e(i16, dArr7, i18, i3, dArr2);
            }
            for (int i19 = (i13 * 2) - i5; i19 < i4; i19 += i13 * 4) {
                int i20 = i3 - i5;
                int i21 = i5;
                double[] dArr8 = dArr;
                int i22 = i2 + i19;
                b(i21, dArr8, i22, dArr2, i20);
                f(i21, dArr8, i22, i3, dArr2);
                int i23 = i2 + i4 + i19;
                b(i21, dArr8, i23, dArr2, i20);
                f(i21, dArr8, i23, i3, dArr2);
            }
        }
    }

    private final void a(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 3;
        int i5 = i4 * 2;
        int i6 = i5 + i5;
        int i7 = i6 + i5;
        int i8 = i2 + 0;
        int i9 = i2 + i6;
        double d = dArr[i8] + dArr[i9];
        int i10 = i2 + 1;
        int i11 = i9 + 1;
        double d2 = dArr[i10] + dArr[i11];
        double d3 = dArr[i8] - dArr[i9];
        double d4 = dArr[i10] - dArr[i11];
        int i12 = i2 + i5;
        int i13 = i2 + i7;
        double d5 = dArr[i12] + dArr[i13];
        int i14 = i12 + 1;
        int i15 = i13 + 1;
        double d6 = dArr[i14] + dArr[i15];
        double d7 = dArr[i12] - dArr[i13];
        double d8 = dArr[i14] - dArr[i15];
        dArr[i8] = d + d5;
        dArr[i10] = d2 + d6;
        dArr[i12] = d - d5;
        dArr[i14] = d2 - d6;
        dArr[i9] = d3 - d8;
        dArr[i11] = d4 + d7;
        dArr[i13] = d3 + d8;
        dArr[i15] = d4 - d7;
        double d9 = dArr2[i3 + 1];
        int i16 = 0;
        int i17 = 2;
        while (i17 < i4) {
            i16 += 4;
            int i18 = i3 + i16;
            double d10 = dArr2[i18];
            double d11 = dArr2[i18 + 1];
            double d12 = dArr2[i18 + 2];
            double d13 = d9;
            double d14 = -dArr2[i18 + 3];
            int i19 = i17 + i5;
            int i20 = i19 + i5;
            int i21 = i20 + i5;
            int i22 = i2 + i17;
            int i23 = i2 + i20;
            double d15 = dArr[i22] + dArr[i23];
            int i24 = i22 + 1;
            int i25 = i23 + 1;
            double d16 = dArr[i24] + dArr[i25];
            double d17 = dArr[i22] - dArr[i23];
            double d18 = dArr[i24] - dArr[i25];
            int i26 = i2 + i19;
            int i27 = i2 + i21;
            double d19 = dArr[i26] + dArr[i27];
            int i28 = i26 + 1;
            int i29 = i27 + 1;
            double d20 = dArr[i28] + dArr[i29];
            double d21 = dArr[i26] - dArr[i27];
            double d22 = dArr[i28] - dArr[i29];
            dArr[i22] = d15 + d19;
            dArr[i24] = d16 + d20;
            dArr[i26] = d15 - d19;
            dArr[i28] = d16 - d20;
            double d23 = d17 - d22;
            double d24 = d18 + d21;
            dArr[i23] = (d10 * d23) - (d11 * d24);
            dArr[i25] = (d24 * d10) + (d23 * d11);
            double d25 = d17 + d22;
            double d26 = d18 - d21;
            dArr[i27] = (d12 * d25) + (d14 * d26);
            dArr[i29] = (d26 * d12) - (d25 * d14);
            int i30 = i5 - i17;
            int i31 = i30 + i5;
            int i32 = i31 + i5;
            int i33 = i32 + i5;
            int i34 = i2 + i30;
            int i35 = i2 + i32;
            double d27 = dArr[i34] + dArr[i35];
            int i36 = i34 + 1;
            int i37 = i35 + 1;
            double d28 = dArr[i36] + dArr[i37];
            double d29 = dArr[i34] - dArr[i35];
            double d30 = dArr[i36] - dArr[i37];
            int i38 = i2 + i31;
            int i39 = i2 + i33;
            double d31 = dArr[i38] + dArr[i39];
            int i40 = i38 + 1;
            int i41 = i39 + 1;
            double d32 = dArr[i40] + dArr[i41];
            double d33 = dArr[i38] - dArr[i39];
            double d34 = dArr[i40] - dArr[i41];
            dArr[i34] = d27 + d31;
            dArr[i36] = d28 + d32;
            dArr[i38] = d27 - d31;
            dArr[i40] = d28 - d32;
            double d35 = d29 - d34;
            double d36 = d30 + d33;
            dArr[i35] = (d11 * d35) - (d10 * d36);
            dArr[i37] = (d11 * d36) + (d10 * d35);
            double d37 = d29 + d34;
            double d38 = d30 - d33;
            dArr[i39] = (d14 * d37) + (d12 * d38);
            dArr[i41] = (d14 * d38) - (d12 * d37);
            i17 += 2;
            d9 = d13;
        }
        double d39 = d9;
        int i42 = i4 + i5;
        int i43 = i42 + i5;
        int i44 = i5 + i43;
        int i45 = i2 + i4;
        int i46 = i2 + i43;
        double d40 = dArr[i45] + dArr[i46];
        int i47 = i45 + 1;
        int i48 = i46 + 1;
        double d41 = dArr[i47] + dArr[i48];
        double d42 = dArr[i45] - dArr[i46];
        double d43 = dArr[i47] - dArr[i48];
        int i49 = i2 + i42;
        int i50 = i2 + i44;
        double d44 = dArr[i49] + dArr[i50];
        int i51 = i49 + 1;
        int i52 = i50 + 1;
        double d45 = dArr[i51] + dArr[i52];
        double d46 = dArr[i49] - dArr[i50];
        double d47 = dArr[i51] - dArr[i52];
        dArr[i45] = d40 + d44;
        dArr[i47] = d41 + d45;
        dArr[i49] = d40 - d44;
        dArr[i51] = d41 - d45;
        double d48 = d42 - d47;
        double d49 = d43 + d46;
        dArr[i46] = (d48 - d49) * d39;
        dArr[i48] = (d49 + d48) * d39;
        double d50 = d42 + d47;
        double d51 = d43 - d46;
        double d52 = -d39;
        dArr[i50] = (d50 + d51) * d52;
        dArr[i52] = d52 * (d51 - d50);
    }

    private final void b(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 3;
        int i5 = i4 * 2;
        double d = dArr2[i3 + 1];
        int i6 = i5 + i5;
        int i7 = i6 + i5;
        int i8 = i2 + 0;
        int i9 = i2 + i6;
        int i10 = i9 + 1;
        double d2 = dArr[i8] - dArr[i10];
        int i11 = i2 + 1;
        double d3 = dArr[i11] + dArr[i9];
        double d4 = dArr[i8] + dArr[i10];
        double d5 = dArr[i11] - dArr[i9];
        int i12 = i2 + i5;
        int i13 = i2 + i7;
        int i14 = i13 + 1;
        double d6 = dArr[i12] - dArr[i14];
        int i15 = i12 + 1;
        double d7 = dArr[i15] + dArr[i13];
        double d8 = dArr[i12] + dArr[i14];
        double d9 = dArr[i15] - dArr[i13];
        double d10 = (d6 - d7) * d;
        double d11 = (d7 + d6) * d;
        dArr[i8] = d2 + d10;
        dArr[i11] = d3 + d11;
        dArr[i12] = d2 - d10;
        dArr[i15] = d3 - d11;
        double d12 = (d8 - d9) * d;
        double d13 = d * (d9 + d8);
        dArr[i9] = d4 - d13;
        dArr[i10] = d5 + d12;
        dArr[i13] = d4 + d13;
        dArr[i14] = d5 - d12;
        int i16 = i5 * 2;
        int i17 = 0;
        int i18 = 2;
        while (i18 < i4) {
            int i19 = i17 + 4;
            int i20 = i3 + i19;
            double d14 = dArr2[i20];
            double d15 = dArr2[i20 + 1];
            double d16 = dArr2[i20 + 2];
            double d17 = -dArr2[i20 + 3];
            int i21 = i16 - 4;
            int i22 = i3 + i21;
            double d18 = dArr2[i22];
            double d19 = dArr2[i22 + 1];
            double d20 = dArr2[i22 + 2];
            int i23 = i21;
            double d21 = -dArr2[i22 + 3];
            int i24 = i18 + i5;
            int i25 = i24 + i5;
            int i26 = i25 + i5;
            int i27 = i2 + i18;
            int i28 = i2 + i25;
            int i29 = i28 + 1;
            double d22 = dArr[i27] - dArr[i29];
            int i30 = i27 + 1;
            double d23 = dArr[i30] + dArr[i28];
            double d24 = dArr[i27] + dArr[i29];
            double d25 = dArr[i30] - dArr[i28];
            int i31 = i2 + i24;
            int i32 = i2 + i26;
            int i33 = i32 + 1;
            double d26 = dArr[i31] - dArr[i33];
            int i34 = i31 + 1;
            double d27 = dArr[i34] + dArr[i32];
            double d28 = dArr[i31] + dArr[i33];
            double d29 = dArr[i34] - dArr[i32];
            double d30 = (d14 * d22) - (d15 * d23);
            double d31 = (d23 * d14) + (d22 * d15);
            double d32 = (d19 * d26) - (d18 * d27);
            double d33 = (d27 * d19) + (d26 * d18);
            dArr[i27] = d30 + d32;
            dArr[i30] = d31 + d33;
            dArr[i31] = d30 - d32;
            dArr[i34] = d31 - d33;
            double d34 = (d16 * d24) + (d17 * d25);
            double d35 = (d16 * d25) - (d24 * d17);
            double d36 = (d21 * d28) + (d20 * d29);
            double d37 = (d29 * d21) - (d28 * d20);
            dArr[i28] = d34 + d36;
            dArr[i29] = d35 + d37;
            dArr[i32] = d34 - d36;
            dArr[i33] = d35 - d37;
            int i35 = i5 - i18;
            int i36 = i35 + i5;
            int i37 = i36 + i5;
            int i38 = i37 + i5;
            int i39 = i2 + i35;
            int i40 = i2 + i37;
            int i41 = i40 + 1;
            double d38 = dArr[i39] - dArr[i41];
            int i42 = i39 + 1;
            double d39 = dArr[i42] + dArr[i40];
            double d40 = dArr[i39] + dArr[i41];
            double d41 = dArr[i42] - dArr[i40];
            int i43 = i2 + i36;
            int i44 = i2 + i38;
            int i45 = i44 + 1;
            double d42 = dArr[i43] - dArr[i45];
            int i46 = i43 + 1;
            double d43 = dArr[i46] + dArr[i44];
            double d44 = dArr[i43] + dArr[i45];
            double d45 = dArr[i46] - dArr[i44];
            double d46 = (d18 * d38) - (d19 * d39);
            double d47 = (d18 * d39) + (d19 * d38);
            double d48 = (d15 * d42) - (d14 * d43);
            double d49 = (d15 * d43) + (d14 * d42);
            dArr[i39] = d46 + d48;
            dArr[i42] = d47 + d49;
            dArr[i43] = d46 - d48;
            dArr[i46] = d47 - d49;
            double d50 = (d20 * d40) + (d21 * d41);
            double d51 = (d20 * d41) - (d21 * d40);
            double d52 = (d17 * d44) + (d16 * d45);
            double d53 = (d17 * d45) - (d16 * d44);
            dArr[i40] = d50 + d52;
            dArr[i41] = d51 + d53;
            dArr[i44] = d50 - d52;
            dArr[i45] = d51 - d53;
            i18 += 2;
            i17 = i19;
            i16 = i23;
        }
        int i47 = i3 + i5;
        double d54 = dArr2[i47];
        double d55 = dArr2[i47 + 1];
        int i48 = i4 + i5;
        int i49 = i48 + i5;
        int i50 = i5 + i49;
        int i51 = i2 + i4;
        int i52 = i2 + i49;
        int i53 = i52 + 1;
        double d56 = dArr[i51] - dArr[i53];
        int i54 = i51 + 1;
        double d57 = dArr[i54] + dArr[i52];
        double d58 = dArr[i51] + dArr[i53];
        double d59 = dArr[i54] - dArr[i52];
        int i55 = i2 + i48;
        int i56 = i2 + i50;
        int i57 = i56 + 1;
        double d60 = dArr[i55] - dArr[i57];
        int i58 = i55 + 1;
        double d61 = dArr[i58] + dArr[i56];
        double d62 = dArr[i55] + dArr[i57];
        double d63 = dArr[i58] - dArr[i56];
        double d64 = (d54 * d56) - (d55 * d57);
        double d65 = (d57 * d54) + (d56 * d55);
        double d66 = (d55 * d60) - (d54 * d61);
        double d67 = (d61 * d55) + (d60 * d54);
        dArr[i51] = d64 + d66;
        dArr[i54] = d65 + d67;
        dArr[i55] = d64 - d66;
        dArr[i58] = d65 - d67;
        double d68 = (d55 * d58) - (d54 * d59);
        double d69 = (d59 * d55) + (d58 * d54);
        double d70 = (d54 * d62) - (d55 * d63);
        double d71 = (d54 * d63) + (d55 * d62);
        dArr[i52] = d68 - d70;
        dArr[i53] = d69 - d71;
        dArr[i56] = d68 + d70;
        dArr[i57] = d69 + d71;
    }

    private void e(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        if (i == 128) {
            int i4 = i3 - 8;
            a(dArr, i2, dArr2, i4);
            b(dArr, i2 + 32, dArr2, i3 - 32);
            a(dArr, i2 + 64, dArr2, i4);
            a(dArr, i2 + 96, dArr2, i4);
            return;
        }
        int i5 = i3 - 16;
        c(dArr, i2, dArr2, i5);
        d(dArr, i2 + 16, dArr2, i5);
        c(dArr, i2 + 32, dArr2, i5);
        c(dArr, i2 + 48, dArr2, i5);
    }

    private void f(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        if (i == 128) {
            int i4 = i3 - 8;
            a(dArr, i2, dArr2, i4);
            int i5 = i3 - 32;
            b(dArr, i2 + 32, dArr2, i5);
            a(dArr, i2 + 64, dArr2, i4);
            b(dArr, i2 + 96, dArr2, i5);
            return;
        }
        int i6 = i3 - 16;
        c(dArr, i2, dArr2, i6);
        d(dArr, i2 + 16, dArr2, i6);
        c(dArr, i2 + 32, dArr2, i6);
        d(dArr, i2 + 48, dArr2, i6);
    }

    private void a(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        int i3 = i2 + 2;
        double d2 = dArr2[i3] * d;
        double d3 = dArr2[i3] + d2;
        int i4 = i + 0;
        int i5 = i + 16;
        double d4 = dArr[i4] + dArr[i5];
        int i6 = i + 1;
        int i7 = i + 17;
        double d5 = dArr[i6] + dArr[i7];
        double d6 = dArr[i4] - dArr[i5];
        double d7 = dArr[i6] - dArr[i7];
        int i8 = i + 8;
        int i9 = i + 24;
        double d8 = dArr[i8] + dArr[i9];
        int i10 = i + 9;
        int i11 = i + 25;
        double d9 = dArr[i10] + dArr[i11];
        double d10 = dArr[i8] - dArr[i9];
        double d11 = dArr[i10] - dArr[i11];
        double d12 = d4 + d8;
        double d13 = d5 + d9;
        double d14 = d4 - d8;
        double d15 = d5 - d9;
        double d16 = d6 - d11;
        double d17 = d7 + d10;
        double d18 = d6 + d11;
        double d19 = d7 - d10;
        int i12 = i + 2;
        int i13 = i + 18;
        double d20 = dArr[i12] + dArr[i13];
        int i14 = i + 3;
        int i15 = i + 19;
        double d21 = dArr[i14] + dArr[i15];
        double d22 = dArr[i12] - dArr[i13];
        double d23 = dArr[i14] - dArr[i15];
        int i16 = i + 10;
        int i17 = i + 26;
        double d24 = dArr[i16] + dArr[i17];
        int i18 = i + 11;
        int i19 = i + 27;
        double d25 = dArr[i18] + dArr[i19];
        double d26 = dArr[i16] - dArr[i17];
        double d27 = dArr[i18] - dArr[i19];
        double d28 = d20 + d24;
        double d29 = d21 + d25;
        double d30 = d20 - d24;
        double d31 = d21 - d25;
        double d32 = d22 - d27;
        double d33 = d23 + d26;
        double d34 = (d3 * d32) - (d2 * d33);
        double d35 = (d33 * d3) + (d32 * d2);
        double d36 = d22 + d27;
        double d37 = d23 - d26;
        double d38 = (d2 * d36) - (d3 * d37);
        double d39 = (d37 * d2) + (d36 * d3);
        int i20 = i + 4;
        int i21 = i + 20;
        double d40 = dArr[i20] + dArr[i21];
        int i22 = i + 5;
        int i23 = i + 21;
        double d41 = dArr[i22] + dArr[i23];
        double d42 = dArr[i20] - dArr[i21];
        double d43 = dArr[i22] - dArr[i23];
        int i24 = i + 12;
        int i25 = i + 28;
        double d44 = dArr[i24] + dArr[i25];
        int i26 = i + 13;
        int i27 = i + 29;
        double d45 = dArr[i26] + dArr[i27];
        double d46 = dArr[i24] - dArr[i25];
        double d47 = dArr[i26] - dArr[i27];
        double d48 = d40 + d44;
        double d49 = d41 + d45;
        double d50 = d40 - d44;
        double d51 = d41 - d45;
        double d52 = d42 - d47;
        double d53 = d43 + d46;
        double d54 = (d52 - d53) * d;
        double d55 = (d53 + d52) * d;
        double d56 = d42 + d47;
        double d57 = d43 - d46;
        double d58 = (d56 + d57) * d;
        double d59 = (d57 - d56) * d;
        int i28 = i + 6;
        int i29 = i + 22;
        double d60 = dArr[i28] + dArr[i29];
        int i30 = i + 7;
        int i31 = i + 23;
        double d61 = dArr[i30] + dArr[i31];
        double d62 = dArr[i28] - dArr[i29];
        double d63 = dArr[i30] - dArr[i31];
        int i32 = i + 14;
        int i33 = i + 30;
        double d64 = dArr[i32] + dArr[i33];
        int i34 = i + 15;
        int i35 = i + 31;
        double d65 = dArr[i34] + dArr[i35];
        double d66 = dArr[i32] - dArr[i33];
        double d67 = dArr[i34] - dArr[i35];
        double d68 = d60 + d64;
        double d69 = d61 + d65;
        double d70 = d60 - d64;
        double d71 = d61 - d65;
        double d72 = d62 - d67;
        double d73 = d63 + d66;
        double d74 = (d2 * d72) - (d3 * d73);
        double d75 = (d73 * d2) + (d72 * d3);
        double d76 = d62 + d67;
        double d77 = d63 - d66;
        double d78 = (d3 * d76) - (d2 * d77);
        double d79 = (d3 * d77) + (d2 * d76);
        double d80 = d18 - d58;
        double d81 = d19 - d59;
        double d82 = d18 + d58;
        double d83 = d19 + d59;
        double d84 = d38 - d78;
        double d85 = d39 - d79;
        double d86 = d38 + d78;
        double d87 = d39 + d79;
        dArr[i9] = d80 + d84;
        dArr[i11] = d81 + d85;
        dArr[i17] = d80 - d84;
        dArr[i19] = d81 - d85;
        dArr[i25] = d82 - d87;
        dArr[i27] = d83 + d86;
        dArr[i33] = d82 + d87;
        dArr[i35] = d83 - d86;
        double d88 = d16 + d54;
        double d89 = d17 + d55;
        double d90 = d16 - d54;
        double d91 = d17 - d55;
        double d92 = d34 + d74;
        double d93 = d35 + d75;
        double d94 = d34 - d74;
        double d95 = d35 - d75;
        dArr[i5] = d88 + d92;
        dArr[i7] = d89 + d93;
        dArr[i13] = d88 - d92;
        dArr[i15] = d89 - d93;
        dArr[i21] = d90 - d95;
        dArr[i23] = d91 + d94;
        dArr[i29] = d90 + d95;
        dArr[i31] = d91 - d94;
        double d96 = d30 - d71;
        double d97 = d31 + d70;
        double d98 = (d96 - d97) * d;
        double d99 = (d97 + d96) * d;
        double d100 = d30 + d71;
        double d101 = d31 - d70;
        double d102 = (d100 - d101) * d;
        double d103 = d * (d101 + d100);
        double d104 = d14 - d51;
        double d105 = d15 + d50;
        double d106 = d14 + d51;
        double d107 = d15 - d50;
        dArr[i8] = d104 + d98;
        dArr[i10] = d105 + d99;
        dArr[i16] = d104 - d98;
        dArr[i18] = d105 - d99;
        dArr[i24] = d106 - d103;
        dArr[i26] = d107 + d102;
        dArr[i32] = d106 + d103;
        dArr[i34] = d107 - d102;
        double d108 = d12 + d48;
        double d109 = d13 + d49;
        double d110 = d12 - d48;
        double d111 = d13 - d49;
        double d112 = d28 + d68;
        double d113 = d29 + d69;
        double d114 = d28 - d68;
        double d115 = d29 - d69;
        dArr[i4] = d108 + d112;
        dArr[i6] = d109 + d113;
        dArr[i12] = d108 - d112;
        dArr[i14] = d109 - d113;
        dArr[i20] = d110 - d115;
        dArr[i22] = d111 + d114;
        dArr[i28] = d110 + d115;
        dArr[i30] = d111 - d114;
    }

    private void b(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        double d2 = dArr2[i2 + 4];
        double d3 = dArr2[i2 + 5];
        double d4 = dArr2[i2 + 6];
        double d5 = dArr2[i2 + 7];
        double d6 = dArr2[i2 + 8];
        double d7 = dArr2[i2 + 9];
        int i3 = i + 0;
        int i4 = i + 17;
        double d8 = dArr[i3] - dArr[i4];
        int i5 = i + 1;
        int i6 = i + 16;
        double d9 = dArr[i5] + dArr[i6];
        int i7 = i + 8;
        int i8 = i + 25;
        double d10 = dArr[i7] - dArr[i8];
        int i9 = i + 9;
        int i10 = i + 24;
        double d11 = dArr[i9] + dArr[i10];
        double d12 = (d10 - d11) * d;
        double d13 = (d11 + d10) * d;
        double d14 = d8 + d12;
        double d15 = d9 + d13;
        double d16 = d8 - d12;
        double d17 = d9 - d13;
        double d18 = dArr[i3] + dArr[i4];
        double d19 = dArr[i5] - dArr[i6];
        double d20 = dArr[i7] + dArr[i8];
        double d21 = dArr[i9] - dArr[i10];
        double d22 = (d20 - d21) * d;
        double d23 = (d21 + d20) * d;
        double d24 = d18 - d23;
        double d25 = d19 + d22;
        double d26 = d18 + d23;
        double d27 = d19 - d22;
        int i11 = i + 2;
        int i12 = i + 19;
        double d28 = dArr[i11] - dArr[i12];
        int i13 = i + 3;
        int i14 = i + 18;
        double d29 = dArr[i13] + dArr[i14];
        double d30 = (d2 * d28) - (d3 * d29);
        double d31 = (d29 * d2) + (d28 * d3);
        int i15 = i + 10;
        int i16 = i + 27;
        double d32 = dArr[i15] - dArr[i16];
        int i17 = i + 11;
        int i18 = i + 26;
        double d33 = dArr[i17] + dArr[i18];
        double d34 = (d5 * d32) - (d4 * d33);
        double d35 = (d33 * d5) + (d32 * d4);
        double d36 = d30 + d34;
        double d37 = d31 + d35;
        double d38 = d30 - d34;
        double d39 = d31 - d35;
        double d40 = dArr[i11] + dArr[i12];
        double d41 = dArr[i13] - dArr[i14];
        double d42 = (d4 * d40) - (d5 * d41);
        double d43 = (d41 * d4) + (d40 * d5);
        double d44 = dArr[i15] + dArr[i16];
        double d45 = dArr[i17] - dArr[i18];
        double d46 = (d2 * d44) + (d3 * d45);
        double d47 = (d45 * d2) - (d44 * d3);
        double d48 = d42 - d46;
        double d49 = d43 - d47;
        double d50 = d42 + d46;
        double d51 = d43 + d47;
        int i19 = i + 4;
        int i20 = i + 21;
        double d52 = dArr[i19] - dArr[i20];
        int i21 = i + 5;
        int i22 = i + 20;
        double d53 = dArr[i21] + dArr[i22];
        double d54 = (d6 * d52) - (d7 * d53);
        double d55 = (d53 * d6) + (d52 * d7);
        int i23 = i + 12;
        int i24 = i + 29;
        double d56 = dArr[i23] - dArr[i24];
        int i25 = i + 13;
        int i26 = i + 28;
        double d57 = dArr[i25] + dArr[i26];
        double d58 = (d7 * d56) - (d6 * d57);
        double d59 = (d57 * d7) + (d56 * d6);
        double d60 = d54 + d58;
        double d61 = d55 + d59;
        double d62 = d54 - d58;
        double d63 = d55 - d59;
        double d64 = dArr[i19] + dArr[i20];
        double d65 = dArr[i21] - dArr[i22];
        double d66 = (d7 * d64) - (d6 * d65);
        double d67 = (d65 * d7) + (d64 * d6);
        double d68 = dArr[i23] + dArr[i24];
        double d69 = dArr[i25] - dArr[i26];
        double d70 = (d6 * d68) - (d7 * d69);
        double d71 = (d6 * d69) + (d7 * d68);
        double d72 = d66 - d70;
        double d73 = d67 - d71;
        double d74 = d66 + d70;
        double d75 = d67 + d71;
        int i27 = i + 6;
        int i28 = i + 23;
        double d76 = dArr[i27] - dArr[i28];
        int i29 = i + 7;
        int i30 = i + 22;
        double d77 = dArr[i29] + dArr[i30];
        double d78 = (d4 * d76) - (d5 * d77);
        double d79 = (d77 * d4) + (d76 * d5);
        int i31 = i + 14;
        int i32 = i + 31;
        double d80 = dArr[i31] - dArr[i32];
        int i33 = i + 15;
        int i34 = i + 30;
        double d81 = dArr[i33] + dArr[i34];
        double d82 = (d3 * d80) - (d2 * d81);
        double d83 = (d81 * d3) + (d80 * d2);
        double d84 = d78 + d82;
        double d85 = d79 + d83;
        double d86 = d78 - d82;
        double d87 = d79 - d83;
        double d88 = dArr[i27] + dArr[i28];
        double d89 = dArr[i29] - dArr[i30];
        double d90 = (d3 * d88) + (d2 * d89);
        double d91 = (d3 * d89) - (d2 * d88);
        double d92 = dArr[i31] + dArr[i32];
        double d93 = dArr[i33] - dArr[i34];
        double d94 = (d5 * d92) - (d4 * d93);
        double d95 = (d5 * d93) + (d4 * d92);
        double d96 = d90 + d94;
        double d97 = d91 + d95;
        double d98 = d90 - d94;
        double d99 = d91 - d95;
        double d100 = d14 + d60;
        double d101 = d15 + d61;
        double d102 = d36 + d84;
        double d103 = d37 + d85;
        dArr[i3] = d100 + d102;
        dArr[i5] = d101 + d103;
        dArr[i11] = d100 - d102;
        dArr[i13] = d101 - d103;
        double d104 = d14 - d60;
        double d105 = d15 - d61;
        double d106 = d36 - d84;
        double d107 = d37 - d85;
        dArr[i19] = d104 - d107;
        dArr[i21] = d105 + d106;
        dArr[i27] = d104 + d107;
        dArr[i29] = d105 - d106;
        double d108 = d16 - d63;
        double d109 = d17 + d62;
        double d110 = d38 - d87;
        double d111 = d39 + d86;
        double d112 = (d110 - d111) * d;
        double d113 = (d111 + d110) * d;
        dArr[i7] = d108 + d112;
        dArr[i9] = d109 + d113;
        dArr[i15] = d108 - d112;
        dArr[i17] = d109 - d113;
        double d114 = d16 + d63;
        double d115 = d17 - d62;
        double d116 = d38 + d87;
        double d117 = d39 - d86;
        double d118 = (d116 - d117) * d;
        double d119 = (d117 + d116) * d;
        dArr[i23] = d114 - d119;
        dArr[i25] = d115 + d118;
        dArr[i31] = d114 + d119;
        dArr[i33] = d115 - d118;
        double d120 = d24 + d72;
        double d121 = d25 + d73;
        double d122 = d48 - d96;
        double d123 = d49 - d97;
        dArr[i6] = d120 + d122;
        dArr[i4] = d121 + d123;
        dArr[i14] = d120 - d122;
        dArr[i12] = d121 - d123;
        double d124 = d24 - d72;
        double d125 = d25 - d73;
        double d126 = d48 + d96;
        double d127 = d49 + d97;
        dArr[i22] = d124 - d127;
        dArr[i20] = d125 + d126;
        dArr[i30] = d124 + d127;
        dArr[i28] = d125 - d126;
        double d128 = d26 - d75;
        double d129 = d27 + d74;
        double d130 = d50 + d99;
        double d131 = d51 - d98;
        double d132 = (d130 - d131) * d;
        double d133 = (d131 + d130) * d;
        dArr[i10] = d128 + d132;
        dArr[i8] = d129 + d133;
        dArr[i18] = d128 - d132;
        dArr[i16] = d129 - d133;
        double d134 = d26 + d75;
        double d135 = d27 - d74;
        double d136 = d50 - d99;
        double d137 = d51 + d98;
        double d138 = (d136 - d137) * d;
        double d139 = d * (d137 + d136);
        dArr[i26] = d134 - d139;
        dArr[i24] = d135 + d138;
        dArr[i34] = d134 + d139;
        dArr[i32] = d135 - d138;
    }

    private void c(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        int i3 = i + 0;
        int i4 = i + 8;
        double d2 = dArr[i3] + dArr[i4];
        int i5 = i + 1;
        int i6 = i + 9;
        double d3 = dArr[i5] + dArr[i6];
        double d4 = dArr[i3] - dArr[i4];
        double d5 = dArr[i5] - dArr[i6];
        int i7 = i + 4;
        int i8 = i + 12;
        double d6 = dArr[i7] + dArr[i8];
        int i9 = i + 5;
        int i10 = i + 13;
        double d7 = dArr[i9] + dArr[i10];
        double d8 = dArr[i7] - dArr[i8];
        double d9 = dArr[i9] - dArr[i10];
        double d10 = d2 + d6;
        double d11 = d3 + d7;
        double d12 = d2 - d6;
        double d13 = d3 - d7;
        double d14 = d4 - d9;
        double d15 = d5 + d8;
        double d16 = d4 + d9;
        double d17 = d5 - d8;
        int i11 = i + 2;
        int i12 = i + 10;
        double d18 = dArr[i11] + dArr[i12];
        int i13 = i + 3;
        int i14 = i + 11;
        double d19 = dArr[i13] + dArr[i14];
        double d20 = dArr[i11] - dArr[i12];
        double d21 = dArr[i13] - dArr[i14];
        int i15 = i + 6;
        int i16 = i + 14;
        double d22 = dArr[i15] + dArr[i16];
        int i17 = i + 7;
        int i18 = i + 15;
        double d23 = dArr[i17] + dArr[i18];
        double d24 = dArr[i15] - dArr[i16];
        double d25 = dArr[i17] - dArr[i18];
        double d26 = d18 + d22;
        double d27 = d19 + d23;
        double d28 = d18 - d22;
        double d29 = d19 - d23;
        double d30 = d20 - d25;
        double d31 = d21 + d24;
        double d32 = d20 + d25;
        double d33 = d21 - d24;
        double d34 = (d30 - d31) * d;
        double d35 = (d30 + d31) * d;
        double d36 = (d32 - d33) * d;
        double d37 = d * (d32 + d33);
        dArr[i4] = d14 + d34;
        dArr[i6] = d15 + d35;
        dArr[i12] = d14 - d34;
        dArr[i14] = d15 - d35;
        dArr[i8] = d16 - d37;
        dArr[i10] = d17 + d36;
        dArr[i16] = d16 + d37;
        dArr[i18] = d17 - d36;
        dArr[i3] = d10 + d26;
        dArr[i5] = d11 + d27;
        dArr[i11] = d10 - d26;
        dArr[i13] = d11 - d27;
        dArr[i7] = d12 - d29;
        dArr[i9] = d13 + d28;
        dArr[i15] = d12 + d29;
        dArr[i17] = d13 - d28;
    }

    private void d(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        double d2 = dArr2[i2 + 4];
        double d3 = dArr2[i2 + 5];
        int i3 = i + 0;
        int i4 = i + 9;
        double d4 = dArr[i3] - dArr[i4];
        int i5 = i + 1;
        int i6 = i + 8;
        double d5 = dArr[i5] + dArr[i6];
        double d6 = dArr[i3] + dArr[i4];
        double d7 = dArr[i5] - dArr[i6];
        int i7 = i + 4;
        int i8 = i + 13;
        double d8 = dArr[i7] - dArr[i8];
        int i9 = i + 5;
        int i10 = i + 12;
        double d9 = dArr[i9] + dArr[i10];
        double d10 = (d8 - d9) * d;
        double d11 = (d9 + d8) * d;
        double d12 = dArr[i7] + dArr[i8];
        double d13 = dArr[i9] - dArr[i10];
        double d14 = (d12 - d13) * d;
        double d15 = d * (d13 + d12);
        int i11 = i + 2;
        int i12 = i + 11;
        double d16 = dArr[i11] - dArr[i12];
        int i13 = i + 3;
        int i14 = i + 10;
        double d17 = dArr[i13] + dArr[i14];
        double d18 = (d2 * d16) - (d3 * d17);
        double d19 = (d17 * d2) + (d16 * d3);
        double d20 = dArr[i11] + dArr[i12];
        double d21 = dArr[i13] - dArr[i14];
        double d22 = (d3 * d20) - (d2 * d21);
        double d23 = (d21 * d3) + (d20 * d2);
        int i15 = i + 6;
        int i16 = i + 15;
        double d24 = dArr[i15] - dArr[i16];
        int i17 = i + 7;
        int i18 = i + 14;
        double d25 = dArr[i17] + dArr[i18];
        double d26 = (d3 * d24) - (d2 * d25);
        double d27 = (d25 * d3) + (d24 * d2);
        double d28 = dArr[i15] + dArr[i16];
        double d29 = dArr[i17] - dArr[i18];
        double d30 = (d2 * d28) - (d3 * d29);
        double d31 = (d2 * d29) + (d3 * d28);
        double d32 = d4 + d10;
        double d33 = d5 + d11;
        double d34 = d18 + d26;
        double d35 = d19 + d27;
        dArr[i3] = d32 + d34;
        dArr[i5] = d33 + d35;
        dArr[i11] = d32 - d34;
        dArr[i13] = d33 - d35;
        double d36 = d4 - d10;
        double d37 = d5 - d11;
        double d38 = d18 - d26;
        double d39 = d19 - d27;
        dArr[i7] = d36 - d39;
        dArr[i9] = d37 + d38;
        dArr[i15] = d36 + d39;
        dArr[i17] = d37 - d38;
        double d40 = d6 - d15;
        double d41 = d7 + d14;
        double d42 = d22 - d30;
        double d43 = d23 - d31;
        dArr[i6] = d40 + d42;
        dArr[i4] = d41 + d43;
        dArr[i14] = d40 - d42;
        dArr[i12] = d41 - d43;
        double d44 = d6 + d15;
        double d45 = d7 - d14;
        double d46 = d22 + d30;
        double d47 = d23 + d31;
        dArr[i10] = d44 - d47;
        dArr[i8] = d45 + d46;
        dArr[i18] = d44 + d47;
        dArr[i16] = d45 - d46;
    }

    private void e(double[] dArr) {
        double d = dArr[0] + dArr[4];
        double d2 = dArr[1] + dArr[5];
        double d3 = dArr[0] - dArr[4];
        double d4 = dArr[1] - dArr[5];
        double d5 = dArr[2] + dArr[6];
        double d6 = dArr[3] + dArr[7];
        double d7 = dArr[2] - dArr[6];
        double d8 = dArr[3] - dArr[7];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[4] = d - d5;
        dArr[5] = d2 - d6;
        dArr[2] = d3 - d8;
        dArr[3] = d4 + d7;
        dArr[6] = d3 + d8;
        dArr[7] = d4 - d7;
    }

    private void f(double[] dArr) {
        double d = dArr[0] + dArr[4];
        double d2 = dArr[1] + dArr[5];
        double d3 = dArr[0] - dArr[4];
        double d4 = dArr[1] - dArr[5];
        double d5 = dArr[2] + dArr[6];
        double d6 = dArr[3] + dArr[7];
        double d7 = dArr[2] - dArr[6];
        double d8 = dArr[3] - dArr[7];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[4] = d - d5;
        dArr[5] = d2 - d6;
        dArr[2] = d3 + d8;
        dArr[3] = d4 - d7;
        dArr[6] = d3 - d8;
        dArr[7] = d4 + d7;
    }

    private void g(double[] dArr) {
        double d = dArr[1] - dArr[3];
        dArr[0] = dArr[0] + dArr[2];
        dArr[1] = dArr[1] + dArr[3];
        dArr[2] = dArr[0] - dArr[2];
        dArr[3] = d;
    }

    private void c(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = (i2 * 2) / i4;
        int i6 = 0;
        for (int i7 = 2; i7 < i4; i7 += 2) {
            int i8 = i - i7;
            i6 += i5;
            double d = 0.5d - dArr2[(i3 + i2) - i6];
            double d2 = dArr2[i3 + i6];
            double d3 = dArr[i7] - dArr[i8];
            int i9 = i7 + 1;
            int i10 = i8 + 1;
            double d4 = dArr[i9] + dArr[i10];
            double d5 = (d * d3) - (d2 * d4);
            double d6 = (d * d4) + (d2 * d3);
            dArr[i7] = dArr[i7] - d5;
            dArr[i9] = dArr[i9] - d6;
            dArr[i8] = dArr[i8] + d5;
            dArr[i10] = dArr[i10] - d6;
        }
    }

    private void d(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = (i2 * 2) / i4;
        int i6 = 0;
        for (int i7 = 2; i7 < i4; i7 += 2) {
            int i8 = i - i7;
            i6 += i5;
            double d = 0.5d - dArr2[(i3 + i2) - i6];
            double d2 = dArr2[i3 + i6];
            double d3 = dArr[i7] - dArr[i8];
            int i9 = i7 + 1;
            int i10 = i8 + 1;
            double d4 = dArr[i9] + dArr[i10];
            double d5 = (d * d3) + (d2 * d4);
            double d6 = (d * d4) - (d2 * d3);
            dArr[i7] = dArr[i7] - d5;
            dArr[i9] = dArr[i9] - d6;
            dArr[i8] = dArr[i8] + d5;
            dArr[i10] = dArr[i10] - d6;
        }
    }

    private void e(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = i2 / i;
        int i6 = 0;
        for (int i7 = 1; i7 < i4; i7++) {
            int i8 = i - i7;
            i6 += i5;
            int i9 = i3 + i6;
            int i10 = (i3 + i2) - i6;
            double d = dArr2[i9] - dArr2[i10];
            double d2 = dArr2[i9] + dArr2[i10];
            double d3 = (dArr[i7] * d2) - (dArr[i8] * d);
            dArr[i7] = (d * dArr[i7]) + (d2 * dArr[i8]);
            dArr[i8] = d3;
        }
        dArr[i4] = dArr[i4] * dArr2[i3 + 0];
    }

    private void f(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = i2 / i;
        int i6 = 0;
        for (int i7 = 1; i7 < i4; i7++) {
            int i8 = i - i7;
            i6 += i5;
            int i9 = i3 + i6;
            int i10 = (i3 + i2) - i6;
            double d = dArr2[i9] - dArr2[i10];
            double d2 = dArr2[i9] + dArr2[i10];
            double d3 = (dArr[i8] * d2) - (dArr[i7] * d);
            dArr[i8] = (d * dArr[i8]) + (d2 * dArr[i7]);
            dArr[i7] = d3;
        }
        dArr[i4] = dArr[i4] * dArr2[i3 + 0];
    }
}
