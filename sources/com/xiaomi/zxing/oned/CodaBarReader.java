package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

public final class CodaBarReader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f1688a = e.toCharArray();
    static final int[] b = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final float c = 2.0f;
    private static final float d = 1.5f;
    private static final String e = "0123456789-$:/.+ABCD";
    private static final int f = 3;
    private static final char[] g = {'A', 'B', 'C', 'D'};
    private final StringBuilder h = new StringBuilder(20);
    private int[] i = new int[80];
    private int j = 0;

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.Result a(int r11, com.xiaomi.zxing.common.BitArray r12, java.util.Map<com.xiaomi.zxing.DecodeHintType, ?> r13) throws com.xiaomi.zxing.NotFoundException {
        /*
            r10 = this;
            int[] r0 = r10.i
            r1 = 0
            java.util.Arrays.fill(r0, r1)
            r10.a((com.xiaomi.zxing.common.BitArray) r12)
            int r12 = r10.b()
            java.lang.StringBuilder r0 = r10.h
            r0.setLength(r1)
            r0 = r12
        L_0x0013:
            int r2 = r10.c(r0)
            r3 = -1
            if (r2 == r3) goto L_0x010a
            java.lang.StringBuilder r4 = r10.h
            char r5 = (char) r2
            r4.append(r5)
            int r0 = r0 + 8
            java.lang.StringBuilder r4 = r10.h
            int r4 = r4.length()
            r5 = 1
            if (r4 <= r5) goto L_0x0038
            char[] r4 = g
            char[] r6 = f1688a
            char r2 = r6[r2]
            boolean r2 = a(r4, r2)
            if (r2 == 0) goto L_0x0038
            goto L_0x003c
        L_0x0038:
            int r2 = r10.j
            if (r0 < r2) goto L_0x0013
        L_0x003c:
            int[] r2 = r10.i
            int r4 = r0 + -1
            r2 = r2[r4]
            r6 = -8
            r7 = 0
        L_0x0044:
            if (r6 >= r3) goto L_0x0050
            int[] r8 = r10.i
            int r9 = r0 + r6
            r8 = r8[r9]
            int r7 = r7 + r8
            int r6 = r6 + 1
            goto L_0x0044
        L_0x0050:
            int r3 = r10.j
            r6 = 2
            if (r0 >= r3) goto L_0x005e
            int r7 = r7 / r6
            if (r2 < r7) goto L_0x0059
            goto L_0x005e
        L_0x0059:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x005e:
            r10.a((int) r12)
            r0 = 0
        L_0x0062:
            java.lang.StringBuilder r2 = r10.h
            int r2 = r2.length()
            if (r0 >= r2) goto L_0x007c
            java.lang.StringBuilder r2 = r10.h
            char[] r3 = f1688a
            java.lang.StringBuilder r7 = r10.h
            char r7 = r7.charAt(r0)
            char r3 = r3[r7]
            r2.setCharAt(r0, r3)
            int r0 = r0 + 1
            goto L_0x0062
        L_0x007c:
            java.lang.StringBuilder r0 = r10.h
            char r0 = r0.charAt(r1)
            char[] r2 = g
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x0105
            java.lang.StringBuilder r0 = r10.h
            java.lang.StringBuilder r2 = r10.h
            int r2 = r2.length()
            int r2 = r2 - r5
            char r0 = r0.charAt(r2)
            char[] r2 = g
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x0100
            java.lang.StringBuilder r0 = r10.h
            int r0 = r0.length()
            r2 = 3
            if (r0 <= r2) goto L_0x00fb
            if (r13 == 0) goto L_0x00b2
            com.xiaomi.zxing.DecodeHintType r0 = com.xiaomi.zxing.DecodeHintType.RETURN_CODABAR_START_END
            boolean r13 = r13.containsKey(r0)
            if (r13 != 0) goto L_0x00c3
        L_0x00b2:
            java.lang.StringBuilder r13 = r10.h
            java.lang.StringBuilder r0 = r10.h
            int r0 = r0.length()
            int r0 = r0 - r5
            r13.deleteCharAt(r0)
            java.lang.StringBuilder r13 = r10.h
            r13.deleteCharAt(r1)
        L_0x00c3:
            r13 = 0
            r0 = 0
        L_0x00c5:
            if (r13 >= r12) goto L_0x00cf
            int[] r2 = r10.i
            r2 = r2[r13]
            int r0 = r0 + r2
            int r13 = r13 + 1
            goto L_0x00c5
        L_0x00cf:
            float r13 = (float) r0
        L_0x00d0:
            if (r12 >= r4) goto L_0x00da
            int[] r2 = r10.i
            r2 = r2[r12]
            int r0 = r0 + r2
            int r12 = r12 + 1
            goto L_0x00d0
        L_0x00da:
            float r12 = (float) r0
            com.xiaomi.zxing.Result r0 = new com.xiaomi.zxing.Result
            java.lang.StringBuilder r2 = r10.h
            java.lang.String r2 = r2.toString()
            r3 = 0
            com.xiaomi.zxing.ResultPoint[] r4 = new com.xiaomi.zxing.ResultPoint[r6]
            com.xiaomi.zxing.ResultPoint r6 = new com.xiaomi.zxing.ResultPoint
            float r11 = (float) r11
            r6.<init>(r13, r11)
            r4[r1] = r6
            com.xiaomi.zxing.ResultPoint r13 = new com.xiaomi.zxing.ResultPoint
            r13.<init>(r12, r11)
            r4[r5] = r13
            com.xiaomi.zxing.BarcodeFormat r11 = com.xiaomi.zxing.BarcodeFormat.CODABAR
            r0.<init>(r2, r3, r4, r11)
            return r0
        L_0x00fb:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0100:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0105:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x010a:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.oned.CodaBarReader.a(int, com.xiaomi.zxing.common.BitArray, java.util.Map):com.xiaomi.zxing.Result");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.h.length() - 1;
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        while (true) {
            int i6 = b[this.h.charAt(i5)];
            for (int i7 = 6; i7 >= 0; i7--) {
                int i8 = (i7 & 1) + ((i6 & 1) * 2);
                iArr[i8] = iArr[i8] + this.i[i4 + i7];
                iArr2[i8] = iArr2[i8] + 1;
                i6 >>= 1;
            }
            if (i5 >= length) {
                break;
            }
            i4 += 8;
            i5++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i9 = 0; i9 < 2; i9++) {
            fArr2[i9] = 0.0f;
            int i10 = i9 + 2;
            fArr2[i10] = ((((float) iArr[i9]) / ((float) iArr2[i9])) + (((float) iArr[i10]) / ((float) iArr2[i10]))) / c;
            fArr[i9] = fArr2[i10];
            fArr[i10] = ((((float) iArr[i10]) * c) + d) / ((float) iArr2[i10]);
        }
        loop3:
        while (true) {
            int i11 = b[this.h.charAt(i3)];
            int i12 = 6;
            while (i12 >= 0) {
                int i13 = (i12 & 1) + ((i11 & 1) * 2);
                float f2 = (float) this.i[i2 + i12];
                if (f2 >= fArr2[i13] && f2 <= fArr[i13]) {
                    i11 >>= 1;
                    i12--;
                }
            }
            if (i3 < length) {
                i2 += 8;
                i3++;
            } else {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void a(BitArray bitArray) throws NotFoundException {
        this.j = 0;
        int e2 = bitArray.e(0);
        int a2 = bitArray.a();
        if (e2 < a2) {
            boolean z = true;
            int i2 = 0;
            while (e2 < a2) {
                if (bitArray.a(e2) ^ z) {
                    i2++;
                } else {
                    b(i2);
                    z = !z;
                    i2 = 1;
                }
                e2++;
            }
            b(i2);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void b(int i2) {
        this.i[this.j] = i2;
        this.j++;
        if (this.j >= this.i.length) {
            int[] iArr = new int[(this.j * 2)];
            System.arraycopy(this.i, 0, iArr, 0, this.j);
            this.i = iArr;
        }
    }

    private int b() throws NotFoundException {
        for (int i2 = 1; i2 < this.j; i2 += 2) {
            int c2 = c(i2);
            if (c2 != -1 && a(g, f1688a[c2])) {
                int i3 = 0;
                for (int i4 = i2; i4 < i2 + 7; i4++) {
                    i3 += this.i[i4];
                }
                if (i2 == 1 || this.i[i2 - 1] >= i3 / 2) {
                    return i2;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static boolean a(char[] cArr, char c2) {
        if (cArr != null) {
            for (char c3 : cArr) {
                if (c3 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private int c(int i2) {
        int i3 = i2 + 7;
        if (i3 >= this.j) {
            return -1;
        }
        int[] iArr = this.i;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        for (int i7 = i2; i7 < i3; i7 += 2) {
            int i8 = iArr[i7];
            if (i8 < i5) {
                i5 = i8;
            }
            if (i8 > i6) {
                i6 = i8;
            }
        }
        int i9 = (i5 + i6) / 2;
        int i10 = 0;
        for (int i11 = i2 + 1; i11 < i3; i11 += 2) {
            int i12 = iArr[i11];
            if (i12 < i4) {
                i4 = i12;
            }
            if (i12 > i10) {
                i10 = i12;
            }
        }
        int i13 = (i4 + i10) / 2;
        int i14 = 128;
        int i15 = 0;
        for (int i16 = 0; i16 < 7; i16++) {
            i14 >>= 1;
            if (iArr[i2 + i16] > ((i16 & 1) == 0 ? i9 : i13)) {
                i15 |= i14;
            }
        }
        for (int i17 = 0; i17 < b.length; i17++) {
            if (b[i17] == i15) {
                return i17;
            }
        }
        return -1;
    }
}
