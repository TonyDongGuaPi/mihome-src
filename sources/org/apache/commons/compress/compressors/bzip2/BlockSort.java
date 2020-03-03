package org.apache.commons.compress.compressors.bzip2;

import java.util.BitSet;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

class BlockSort {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3316a = 1000;
    private static final int b = 100;
    private static final int c = 1000;
    private static final int o = 10;
    private static final int[] q = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int r = 20;
    private static final int s = 10;
    private static final int t = 30;
    private static final int u = 2097152;
    private static final int v = -2097153;
    private int d;
    private int e;
    private boolean f;
    private final int[] g = new int[1000];
    private final int[] h = new int[1000];
    private final int[] i = new int[1000];
    private final int[] j = new int[256];
    private final int[] k = new int[256];
    private final boolean[] l = new boolean[256];
    private final int[] m = new int[65537];
    private final char[] n;
    private int[] p;

    private static byte a(byte b2, byte b3, byte b4) {
        if (b2 < b3) {
            if (b3 >= b4) {
                if (b2 >= b4) {
                    return b2;
                }
                return b4;
            }
        } else if (b3 <= b4) {
            if (b2 <= b4) {
                return b2;
            }
            return b4;
        }
        return b3;
    }

    private int a(int i2, int i3) {
        return i2 < i3 ? i2 : i3;
    }

    BlockSort(BZip2CompressorOutputStream.Data data) {
        this.n = data.s;
    }

    /* access modifiers changed from: package-private */
    public void a(BZip2CompressorOutputStream.Data data, int i2) {
        this.e = i2 * 30;
        this.d = 0;
        this.f = true;
        if (i2 + 1 < 10000) {
            b(data, i2);
        } else {
            c(data, i2);
            if (this.f && this.d > this.e) {
                b(data, i2);
            }
        }
        int[] iArr = data.r;
        data.t = -1;
        for (int i3 = 0; i3 <= i2; i3++) {
            if (iArr[i3] == 0) {
                data.t = i3;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(BZip2CompressorOutputStream.Data data, int i2) {
        int i3 = i2 + 1;
        data.q[0] = data.q[i3];
        a(data.r, data.q, i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int[] iArr = data.r;
            iArr[i4] = iArr[i4] - 1;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            if (data.r[i5] == -1) {
                data.r[i5] = i2;
                return;
            }
        }
    }

    private void a(int[] iArr, int[] iArr2, int i2, int i3) {
        if (i2 != i3) {
            if (i3 - i2 > 3) {
                for (int i4 = i3 - 4; i4 >= i2; i4--) {
                    int i5 = iArr[i4];
                    int i6 = iArr2[i5];
                    int i7 = i4 + 4;
                    while (i7 <= i3 && i6 > iArr2[iArr[i7]]) {
                        iArr[i7 - 4] = iArr[i7];
                        i7 += 4;
                    }
                    iArr[i7 - 4] = i5;
                }
            }
            for (int i8 = i3 - 1; i8 >= i2; i8--) {
                int i9 = iArr[i8];
                int i10 = iArr2[i9];
                int i11 = i8 + 1;
                while (i11 <= i3 && i10 > iArr2[iArr[i11]]) {
                    iArr[i11 - 1] = iArr[i11];
                    i11++;
                }
                iArr[i11 - 1] = i9;
            }
        }
    }

    private void a(int[] iArr, int i2, int i3) {
        int i4 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = i4;
    }

    private void a(int[] iArr, int i2, int i3, int i4) {
        while (i4 > 0) {
            a(iArr, i2, i3);
            i2++;
            i3++;
            i4--;
        }
    }

    private void a(int i2, int i3, int i4) {
        this.g[i2] = i3;
        this.h[i2] = i4;
    }

    private int[] a(int i2) {
        return new int[]{this.g[i2], this.h[i2]};
    }

    private void b(int[] iArr, int[] iArr2, int i2, int i3) {
        long j2;
        int i4;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        char c2 = 0;
        a(0, i2, i3);
        long j3 = 0;
        int i5 = 1;
        long j4 = 0;
        int i6 = 1;
        while (i6 > 0) {
            i6--;
            int[] a2 = a(i6);
            int i7 = a2[c2];
            int i8 = a2[i5];
            if (i8 - i7 < 10) {
                a(iArr3, iArr4, i7, i8);
            } else {
                j4 = ((j4 * 7621) + 1) % 32768;
                long j5 = j4 % 3;
                if (j5 == j3) {
                    j2 = (long) iArr4[iArr3[i7]];
                } else if (j5 == 1) {
                    j2 = (long) iArr4[iArr3[(i7 + i8) >>> i5]];
                } else {
                    j2 = (long) iArr4[iArr3[i8]];
                }
                int i9 = i8;
                int i10 = i9;
                int i11 = i7;
                int i12 = i11;
                while (true) {
                    if (i12 <= i9) {
                        int i13 = iArr4[iArr3[i12]] - ((int) j2);
                        if (i13 == 0) {
                            a(iArr3, i12, i11);
                            i11++;
                            i12++;
                        } else if (i13 <= 0) {
                            i12++;
                        }
                    }
                    i4 = i10;
                    while (i12 <= i9) {
                        int i14 = iArr4[iArr3[i9]] - ((int) j2);
                        if (i14 == 0) {
                            a(iArr3, i9, i4);
                            i4--;
                        } else if (i14 < 0) {
                            break;
                        }
                        i9--;
                    }
                    if (i12 > i9) {
                        break;
                    }
                    a(iArr3, i12, i9);
                    i12++;
                    i9--;
                    i10 = i4;
                }
                if (i4 >= i11) {
                    int a3 = a(i11 - i7, i12 - i11);
                    a(iArr3, i7, i12 - a3, a3);
                    int i15 = i8 - i4;
                    int i16 = i4 - i9;
                    int a4 = a(i15, i16);
                    a(iArr3, i9 + 1, (i8 - a4) + 1, a4);
                    int i17 = ((i12 + i7) - i11) - 1;
                    int i18 = (i8 - i16) + 1;
                    if (i17 - i7 > i8 - i18) {
                        int i19 = i6 + 1;
                        a(i6, i7, i17);
                        a(i19, i18, i8);
                        i6 = i19 + 1;
                    } else {
                        int i20 = i6 + 1;
                        a(i6, i18, i8);
                        a(i20, i7, i17);
                        i6 = i20 + 1;
                    }
                }
                c2 = 0;
                j3 = 0;
                i5 = 1;
            }
        }
    }

    private int[] a() {
        if (this.p == null) {
            this.p = new int[(this.n.length / 2)];
        }
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public final void a(int[] iArr, byte[] bArr, int i2) {
        int i3;
        int[] iArr2 = new int[257];
        int[] a2 = a();
        for (int i4 = 0; i4 < i2; i4++) {
            a2[i4] = 0;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            byte b2 = bArr[i5] & 255;
            iArr2[b2] = iArr2[b2] + 1;
        }
        for (int i6 = 1; i6 < 257; i6++) {
            iArr2[i6] = iArr2[i6] + iArr2[i6 - 1];
        }
        for (int i7 = 0; i7 < i2; i7++) {
            byte b3 = bArr[i7] & 255;
            int i8 = iArr2[b3] - 1;
            iArr2[b3] = i8;
            iArr[i8] = i7;
        }
        BitSet bitSet = new BitSet(i2 + 64);
        for (int i9 = 0; i9 < 256; i9++) {
            bitSet.set(iArr2[i9]);
        }
        for (int i10 = 0; i10 < 32; i10++) {
            int i11 = (i10 * 2) + i2;
            bitSet.set(i11);
            bitSet.clear(i11 + 1);
        }
        int i12 = 1;
        do {
            int i13 = 0;
            for (int i14 = 0; i14 < i2; i14++) {
                if (bitSet.get(i14)) {
                    i13 = i14;
                }
                int i15 = iArr[i14] - i12;
                if (i15 < 0) {
                    i15 += i2;
                }
                a2[i15] = i13;
            }
            int i16 = -1;
            i3 = 0;
            while (true) {
                int nextClearBit = bitSet.nextClearBit(i16 + 1);
                int i17 = nextClearBit - 1;
                if (i17 < i2 && (i16 = bitSet.nextSetBit(nextClearBit + 1) - 1) < i2) {
                    if (i16 > i17) {
                        i3 += (i16 - i17) + 1;
                        b(iArr, a2, i17, i16);
                        int i18 = -1;
                        while (i17 <= i16) {
                            int i19 = a2[iArr[i17]];
                            if (i18 != i19) {
                                bitSet.set(i17);
                                i18 = i19;
                            }
                            i17++;
                        }
                    }
                }
            }
            i12 *= 2;
            if (i12 > i2) {
                return;
            }
        } while (i3 != 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        r29 = r15;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.Data r32, int r33, int r34, int r35, int r36) {
        /*
            r31 = this;
            r0 = r31
            r1 = r32
            r3 = r34
            int r5 = r3 - r33
            r6 = 1
            int r5 = r5 + r6
            r8 = 2
            if (r5 >= r8) goto L_0x001a
            boolean r1 = r0.f
            if (r1 == 0) goto L_0x0018
            int r1 = r0.d
            int r2 = r0.e
            if (r1 <= r2) goto L_0x0018
            goto L_0x0019
        L_0x0018:
            r6 = 0
        L_0x0019:
            return r6
        L_0x001a:
            r8 = 0
        L_0x001b:
            int[] r9 = q
            r9 = r9[r8]
            if (r9 >= r5) goto L_0x0024
            int r8 = r8 + 1
            goto L_0x001b
        L_0x0024:
            int[] r5 = r1.r
            char[] r9 = r0.n
            byte[] r1 = r1.q
            int r11 = r36 + 1
            boolean r12 = r0.f
            int r13 = r0.e
            int r14 = r0.d
        L_0x0032:
            int r8 = r8 + -1
            if (r8 < 0) goto L_0x01eb
            int[] r15 = q
            r15 = r15[r8]
            int r16 = r33 + r15
            int r6 = r16 + -1
            r30 = r16
            r16 = r14
            r14 = r30
        L_0x0044:
            if (r14 > r3) goto L_0x01e4
            r18 = 3
            r7 = r16
        L_0x004a:
            if (r14 > r3) goto L_0x01cc
            int r18 = r18 + -1
            if (r18 < 0) goto L_0x01cc
            r16 = r5[r14]
            int r19 = r16 + r35
            r22 = r7
            r20 = r14
            r7 = 0
            r21 = 0
        L_0x005b:
            if (r7 == 0) goto L_0x0072
            r5[r20] = r21
            int r2 = r20 - r15
            if (r2 > r6) goto L_0x006f
            r20 = r2
            r25 = r6
            r27 = r8
        L_0x0069:
            r29 = r15
        L_0x006b:
            r7 = r22
            goto L_0x01c0
        L_0x006f:
            r20 = r2
            goto L_0x0073
        L_0x0072:
            r7 = 1
        L_0x0073:
            int r2 = r20 - r15
            r21 = r5[r2]
            int r2 = r21 + r35
            int r23 = r2 + 1
            byte r4 = r1[r23]
            int r24 = r19 + 1
            r25 = r6
            byte r6 = r1[r24]
            if (r4 != r6) goto L_0x01a6
            int r4 = r2 + 2
            byte r6 = r1[r4]
            int r23 = r19 + 2
            r26 = r7
            byte r7 = r1[r23]
            if (r6 != r7) goto L_0x0197
            int r4 = r2 + 3
            byte r6 = r1[r4]
            int r7 = r19 + 3
            r27 = r8
            byte r8 = r1[r7]
            if (r6 != r8) goto L_0x018a
            int r4 = r2 + 4
            byte r6 = r1[r4]
            int r7 = r19 + 4
            byte r8 = r1[r7]
            if (r6 != r8) goto L_0x017d
            int r4 = r2 + 5
            byte r6 = r1[r4]
            int r7 = r19 + 5
            byte r8 = r1[r7]
            if (r6 != r8) goto L_0x0170
            int r2 = r2 + 6
            byte r4 = r1[r2]
            int r6 = r19 + 6
            byte r7 = r1[r6]
            if (r4 != r7) goto L_0x0163
            r4 = r2
            r2 = r36
        L_0x00be:
            if (r2 <= 0) goto L_0x0069
            int r2 = r2 + -4
            int r7 = r4 + 1
            byte r8 = r1[r7]
            int r23 = r6 + 1
            r28 = r2
            byte r2 = r1[r23]
            if (r8 != r2) goto L_0x0156
            char r2 = r9[r4]
            char r8 = r9[r6]
            if (r2 != r8) goto L_0x014d
            int r2 = r4 + 2
            byte r8 = r1[r2]
            int r24 = r6 + 2
            byte r10 = r1[r24]
            if (r8 != r10) goto L_0x0140
            char r8 = r9[r7]
            char r10 = r9[r23]
            if (r8 != r10) goto L_0x0137
            int r7 = r4 + 3
            byte r8 = r1[r7]
            int r10 = r6 + 3
            r29 = r15
            byte r15 = r1[r10]
            if (r8 != r15) goto L_0x012c
            char r8 = r9[r2]
            char r15 = r9[r24]
            if (r8 != r15) goto L_0x0125
            int r4 = r4 + 4
            byte r2 = r1[r4]
            int r6 = r6 + 4
            byte r8 = r1[r6]
            if (r2 != r8) goto L_0x011a
            char r2 = r9[r7]
            char r8 = r9[r10]
            if (r2 != r8) goto L_0x0113
            if (r4 < r11) goto L_0x0109
            int r4 = r4 - r11
        L_0x0109:
            if (r6 < r11) goto L_0x010c
            int r6 = r6 - r11
        L_0x010c:
            int r22 = r22 + 1
            r2 = r28
            r15 = r29
            goto L_0x00be
        L_0x0113:
            char r2 = r9[r7]
            char r4 = r9[r10]
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x011a:
            byte r2 = r1[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r6]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x0125:
            char r2 = r9[r2]
            char r4 = r9[r24]
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x012c:
            byte r2 = r1[r7]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r10]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x0137:
            r29 = r15
            char r2 = r9[r7]
            char r4 = r9[r23]
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x0140:
            r29 = r15
            byte r2 = r1[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r24]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x014d:
            r29 = r15
            char r2 = r9[r4]
            char r4 = r9[r6]
            if (r2 <= r4) goto L_0x006b
            goto L_0x0162
        L_0x0156:
            r29 = r15
            byte r2 = r1[r7]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r23]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
        L_0x0162:
            goto L_0x01b6
        L_0x0163:
            r29 = r15
            byte r2 = r1[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r6]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x01b6
        L_0x0170:
            r29 = r15
            byte r2 = r1[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r7]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x01b6
        L_0x017d:
            r29 = r15
            byte r2 = r1[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r7]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x01b6
        L_0x018a:
            r29 = r15
            byte r2 = r1[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r7]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x01b6
        L_0x0197:
            r27 = r8
            r29 = r15
            byte r2 = r1[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r23]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
            goto L_0x01b6
        L_0x01a6:
            r26 = r7
            r27 = r8
            r29 = r15
            byte r2 = r1[r23]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r1[r24]
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r4) goto L_0x006b
        L_0x01b6:
            r6 = r25
            r7 = r26
            r8 = r27
            r15 = r29
            goto L_0x005b
        L_0x01c0:
            r5[r20] = r16
            int r14 = r14 + 1
            r6 = r25
            r8 = r27
            r15 = r29
            goto L_0x004a
        L_0x01cc:
            r25 = r6
            r27 = r8
            r29 = r15
            if (r12 == 0) goto L_0x01da
            if (r14 > r3) goto L_0x01da
            if (r7 <= r13) goto L_0x01da
            r14 = r7
            goto L_0x01eb
        L_0x01da:
            r16 = r7
            r6 = r25
            r8 = r27
            r15 = r29
            goto L_0x0044
        L_0x01e4:
            r27 = r8
            r14 = r16
            r6 = 1
            goto L_0x0032
        L_0x01eb:
            r0.d = r14
            if (r12 == 0) goto L_0x01f4
            if (r14 <= r13) goto L_0x01f4
            r17 = 1
            goto L_0x01f6
        L_0x01f4:
            r17 = 0
        L_0x01f6:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.a(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data, int, int, int, int):boolean");
    }

    private static void b(int[] iArr, int i2, int i3, int i4) {
        int i5 = i4 + i2;
        while (i2 < i5) {
            int i6 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i6;
            i3++;
            i2++;
        }
    }

    private void b(BZip2CompressorOutputStream.Data data, int i2, int i3, int i4, int i5) {
        int i6;
        BZip2CompressorOutputStream.Data data2 = data;
        int[] iArr = this.g;
        int[] iArr2 = this.h;
        int[] iArr3 = this.i;
        int[] iArr4 = data2.r;
        byte[] bArr = data2.q;
        iArr[0] = i2;
        iArr2[0] = i3;
        iArr3[0] = i4;
        int i7 = 1;
        int i8 = 1;
        while (true) {
            int i9 = i8 - 1;
            if (i9 >= 0) {
                int i10 = iArr[i9];
                int i11 = iArr2[i9];
                int i12 = iArr3[i9];
                if (i11 - i10 >= 20 && i12 <= 10) {
                    int i13 = i12 + 1;
                    byte a2 = a(bArr[iArr4[i10] + i13], bArr[iArr4[i11] + i13], bArr[iArr4[(i10 + i11) >>> i7] + i13]) & 255;
                    int i14 = i10;
                    int i15 = i14;
                    int i16 = i11;
                    int i17 = i16;
                    while (true) {
                        if (i14 <= i16) {
                            int i18 = (bArr[iArr4[i14] + i13] & 255) - a2;
                            if (i18 == 0) {
                                int i19 = iArr4[i14];
                                iArr4[i14] = iArr4[i15];
                                iArr4[i15] = i19;
                                i15++;
                                i14++;
                            } else if (i18 < 0) {
                                i14++;
                            }
                        }
                        i6 = i17;
                        while (i14 <= i16) {
                            int i20 = (bArr[iArr4[i16] + i13] & 255) - a2;
                            if (i20 != 0) {
                                if (i20 <= 0) {
                                    break;
                                }
                                i16--;
                            } else {
                                int i21 = iArr4[i16];
                                iArr4[i16] = iArr4[i6];
                                iArr4[i6] = i21;
                                i6--;
                                i16--;
                            }
                            BZip2CompressorOutputStream.Data data3 = data;
                        }
                        if (i14 > i16) {
                            break;
                        }
                        int i22 = iArr4[i14];
                        iArr4[i14] = iArr4[i16];
                        iArr4[i16] = i22;
                        i16--;
                        i14++;
                        BZip2CompressorOutputStream.Data data4 = data;
                        i17 = i6;
                    }
                    if (i6 < i15) {
                        iArr[i9] = i10;
                        iArr2[i9] = i11;
                        iArr3[i9] = i13;
                        i8 = i9 + 1;
                        BZip2CompressorOutputStream.Data data5 = data;
                        i7 = 1;
                    } else {
                        int i23 = i15 - i10;
                        int i24 = i14 - i15;
                        if (i23 >= i24) {
                            i23 = i24;
                        }
                        b(iArr4, i10, i14 - i23, i23);
                        int i25 = i11 - i6;
                        int i26 = i6 - i16;
                        if (i25 >= i26) {
                            i25 = i26;
                        }
                        b(iArr4, i14, (i11 - i25) + 1, i25);
                        int i27 = ((i14 + i10) - i15) - 1;
                        int i28 = (i11 - i26) + 1;
                        iArr[i9] = i10;
                        iArr2[i9] = i27;
                        iArr3[i9] = i12;
                        int i29 = i9 + 1;
                        iArr[i29] = i27 + 1;
                        iArr2[i29] = i28 - 1;
                        iArr3[i29] = i13;
                        int i30 = i29 + 1;
                        iArr[i30] = i28;
                        iArr2[i30] = i11;
                        iArr3[i30] = i12;
                        i9 = i30 + 1;
                    }
                } else if (a(data, i10, i11, i12, i5)) {
                    return;
                }
                i8 = i9;
                BZip2CompressorOutputStream.Data data52 = data;
                i7 = 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(BZip2CompressorOutputStream.Data data, int i2) {
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        BZip2CompressorOutputStream.Data data2 = data;
        int i9 = i2;
        int[] iArr2 = this.j;
        int[] iArr3 = this.k;
        boolean[] zArr = this.l;
        int[] iArr4 = this.m;
        byte[] bArr = data2.q;
        int[] iArr5 = data2.r;
        char[] cArr = this.n;
        int i10 = this.e;
        boolean z = this.f;
        int i11 = 65537;
        while (true) {
            i11--;
            if (i11 < 0) {
                break;
            }
            iArr4[i11] = 0;
        }
        for (int i12 = 0; i12 < 20; i12++) {
            bArr[i9 + i12 + 2] = bArr[(i12 % (i9 + 1)) + 1];
        }
        int i13 = i9 + 20 + 1;
        while (true) {
            i13--;
            if (i13 < 0) {
                break;
            }
            cArr[i13] = 0;
        }
        int i14 = i9 + 1;
        bArr[0] = bArr[i14];
        boolean z2 = z;
        int i15 = 255;
        byte b2 = bArr[0] & 255;
        int i16 = 0;
        while (i16 <= i9) {
            i16++;
            byte b3 = bArr[i16] & 255;
            int i17 = (b2 << 8) + b3;
            iArr4[i17] = iArr4[i17] + 1;
            b2 = b3;
        }
        for (int i18 = 1; i18 <= 65536; i18++) {
            iArr4[i18] = iArr4[i18] + iArr4[i18 - 1];
        }
        byte b4 = bArr[1] & 255;
        int i19 = 0;
        while (i19 < i9) {
            byte b5 = bArr[i19 + 2] & 255;
            int i20 = (b4 << 8) + b5;
            int i21 = iArr4[i20] - 1;
            iArr4[i20] = i21;
            iArr5[i21] = i19;
            i19++;
            b4 = b5;
        }
        int i22 = ((bArr[i14] & 255) << 8) + (bArr[1] & 255);
        int i23 = iArr4[i22] - 1;
        iArr4[i22] = i23;
        iArr5[i23] = i9;
        int i24 = 256;
        while (true) {
            i24--;
            if (i24 < 0) {
                break;
            }
            zArr[i24] = false;
            iArr2[i24] = i24;
        }
        int i25 = 364;
        while (i25 != 1) {
            i25 /= 3;
            int i26 = i25;
            while (i26 <= i15) {
                int i27 = iArr2[i26];
                int i28 = iArr4[(i27 + 1) << 8] - iArr4[i27 << 8];
                int i29 = i25 - 1;
                int i30 = iArr2[i26 - i25];
                int i31 = i26;
                while (true) {
                    i7 = i10;
                    if (iArr4[(i30 + 1) << 8] - iArr4[i30 << 8] <= i28) {
                        i8 = i31;
                        break;
                    }
                    iArr2[i31] = i30;
                    i8 = i31 - i25;
                    if (i8 <= i29) {
                        break;
                    }
                    i30 = iArr2[i8 - i25];
                    i31 = i8;
                    i10 = i7;
                }
                iArr2[i8] = i27;
                i26++;
                i10 = i7;
                i15 = 255;
            }
        }
        int i32 = i10;
        int i33 = 0;
        while (i33 <= i15) {
            int i34 = iArr2[i33];
            int i35 = 0;
            while (i35 <= i15) {
                int i36 = (i34 << 8) + i35;
                int i37 = iArr4[i36];
                if ((i37 & 2097152) != 2097152) {
                    int i38 = i37 & v;
                    int i39 = (iArr4[i36 + 1] & v) - 1;
                    if (i39 > i38) {
                        i6 = 2097152;
                        i3 = i35;
                        iArr = iArr2;
                        i5 = i32;
                        i4 = i33;
                        b(data, i38, i39, 2, i2);
                        if (z2 && this.d > i5) {
                            return;
                        }
                    } else {
                        i3 = i35;
                        iArr = iArr2;
                        i5 = i32;
                        i6 = 2097152;
                        i4 = i33;
                    }
                    iArr4[i36] = i37 | i6;
                } else {
                    i3 = i35;
                    iArr = iArr2;
                    i5 = i32;
                    i4 = i33;
                }
                i35 = i3 + 1;
                i32 = i5;
                i33 = i4;
                iArr2 = iArr;
                i15 = 255;
                BZip2CompressorOutputStream.Data data3 = data;
            }
            int[] iArr6 = iArr2;
            int i40 = i32;
            int i41 = i33;
            for (int i42 = 0; i42 <= 255; i42++) {
                iArr3[i42] = iArr4[(i42 << 8) + i34] & v;
            }
            int i43 = i34 << 8;
            int i44 = iArr4[i43] & v;
            int i45 = (i34 + 1) << 8;
            int i46 = iArr4[i45] & v;
            while (i44 < i46) {
                int i47 = iArr5[i44];
                int i48 = i46;
                byte b6 = bArr[i47] & 255;
                if (!zArr[b6]) {
                    iArr5[iArr3[b6]] = i47 == 0 ? i9 : i47 - 1;
                    iArr3[b6] = iArr3[b6] + 1;
                }
                i44++;
                i46 = i48;
            }
            int i49 = 256;
            while (true) {
                i49--;
                if (i49 < 0) {
                    break;
                }
                int i50 = (i49 << 8) + i34;
                iArr4[i50] = iArr4[i50] | 2097152;
            }
            zArr[i34] = true;
            if (i41 < 255) {
                int i51 = iArr4[i43] & v;
                int i52 = (v & iArr4[i45]) - i51;
                int i53 = 0;
                while ((i52 >> i53) > 65534) {
                    i53++;
                }
                int i54 = 0;
                while (i54 < i52) {
                    int i55 = iArr5[i51 + i54];
                    char c2 = (char) (i54 >> i53);
                    cArr[i55] = c2;
                    int i56 = i51;
                    if (i55 < 20) {
                        cArr[i55 + i9 + 1] = c2;
                    }
                    i54++;
                    i51 = i56;
                }
            }
            i33 = i41 + 1;
            i32 = i40;
            iArr2 = iArr6;
            i15 = 255;
            BZip2CompressorOutputStream.Data data4 = data;
        }
    }
}
