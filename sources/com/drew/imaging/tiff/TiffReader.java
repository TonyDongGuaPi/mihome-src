package com.drew.imaging.tiff;

import com.drew.lang.RandomAccessReader;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;

public class TiffReader {
    private static int a(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull TiffHandler tiffHandler, int i) throws TiffProcessingException, IOException {
        short g = randomAccessReader.g(i);
        if (g == 19789) {
            randomAccessReader.a(true);
        } else if (g == 18761) {
            randomAccessReader.a(false);
        } else {
            throw new TiffProcessingException("Unclear distinction between Motorola/Intel byte ordering: " + g);
        }
        int i2 = i + 2;
        tiffHandler.a(randomAccessReader.f(i2));
        int j = randomAccessReader.j(i + 4) + i;
        if (((long) j) >= randomAccessReader.a() - 1) {
            tiffHandler.a("First IFD offset is beyond the end of the TIFF data segment -- trying default offset");
            j = i2 + 2 + 4;
        }
        a(tiffHandler, randomAccessReader, new HashSet(), j, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ef A[Catch:{ all -> 0x01fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0119 A[SYNTHETIC, Splitter:B:59:0x0119] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(@com.drew.lang.annotations.NotNull com.drew.imaging.tiff.TiffHandler r29, @com.drew.lang.annotations.NotNull com.drew.lang.RandomAccessReader r30, @com.drew.lang.annotations.NotNull java.util.Set<java.lang.Integer> r31, int r32, int r33) throws java.io.IOException {
        /*
            r8 = r29
            r9 = r30
            r0 = r31
            r10 = r32
            r11 = r33
            r1 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r32)     // Catch:{ all -> 0x020a }
            boolean r2 = r0.contains(r2)     // Catch:{ all -> 0x020a }
            if (r2 == 0) goto L_0x0019
            r29.b()
            return
        L_0x0019:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r32)     // Catch:{ all -> 0x020a }
            r0.add(r2)     // Catch:{ all -> 0x020a }
            long r2 = (long) r10     // Catch:{ all -> 0x020a }
            long r4 = r30.a()     // Catch:{ all -> 0x020a }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0201
            if (r10 >= 0) goto L_0x002d
            goto L_0x0201
        L_0x002d:
            int r2 = r9.f(r10)     // Catch:{ all -> 0x020a }
            r3 = 255(0xff, float:3.57E-43)
            r12 = 1
            if (r2 <= r3) goto L_0x0054
            r3 = r2 & 255(0xff, float:3.57E-43)
            if (r3 != 0) goto L_0x0054
            boolean r3 = r30.b()     // Catch:{ all -> 0x020a }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x020a }
            int r2 = r2 >> 8
            boolean r1 = r30.b()     // Catch:{ all -> 0x004f }
            r1 = r1 ^ r12
            r9.a((boolean) r1)     // Catch:{ all -> 0x004f }
            r13 = r2
            r14 = r3
            goto L_0x0056
        L_0x004f:
            r0 = move-exception
            r26 = r3
            goto L_0x020d
        L_0x0054:
            r14 = r1
            r13 = r2
        L_0x0056:
            int r1 = r13 * 12
            r15 = 2
            int r1 = r1 + r15
            int r1 = r1 + 4
            int r1 = r1 + r10
            long r1 = (long) r1
            long r3 = r30.a()     // Catch:{ all -> 0x01fd }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0078
            java.lang.String r0 = "Illegally sized IFD"
            r8.b((java.lang.String) r0)     // Catch:{ all -> 0x01fd }
            r29.b()
            if (r14 == 0) goto L_0x0077
            boolean r0 = r14.booleanValue()
            r9.a((boolean) r0)
        L_0x0077:
            return
        L_0x0078:
            r16 = 0
            r7 = 0
            r17 = 0
        L_0x007d:
            if (r7 >= r13) goto L_0x01b2
            int r1 = a(r10, r7)     // Catch:{ all -> 0x01fd }
            int r6 = r9.f(r1)     // Catch:{ all -> 0x01fd }
            int r2 = r1 + 2
            int r5 = r9.f(r2)     // Catch:{ all -> 0x01fd }
            com.drew.imaging.tiff.TiffDataFormat r2 = com.drew.imaging.tiff.TiffDataFormat.a(r5)     // Catch:{ all -> 0x01fd }
            int r3 = r1 + 4
            long r3 = r9.i(r3)     // Catch:{ all -> 0x01fd }
            if (r2 != 0) goto L_0x00de
            java.lang.Long r2 = r8.a(r6, r5, r3)     // Catch:{ all -> 0x01fd }
            if (r2 != 0) goto L_0x00d7
            java.lang.String r1 = "Invalid TIFF tag format code %d for tag 0x%04X"
            java.lang.Object[] r2 = new java.lang.Object[r15]     // Catch:{ all -> 0x01fd }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x01fd }
            r2[r16] = r3     // Catch:{ all -> 0x01fd }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x01fd }
            r2[r12] = r3     // Catch:{ all -> 0x01fd }
            java.lang.String r1 = java.lang.String.format(r1, r2)     // Catch:{ all -> 0x01fd }
            r8.b((java.lang.String) r1)     // Catch:{ all -> 0x01fd }
            int r1 = r17 + 1
            r2 = 5
            if (r1 <= r2) goto L_0x00cd
            java.lang.String r0 = "Stopping processing as too many errors seen in TIFF IFD"
            r8.b((java.lang.String) r0)     // Catch:{ all -> 0x01fd }
            r29.b()
            if (r14 == 0) goto L_0x00cc
            boolean r0 = r14.booleanValue()
            r9.a((boolean) r0)
        L_0x00cc:
            return
        L_0x00cd:
            r17 = r1
            r21 = r7
            r20 = r13
        L_0x00d3:
            r26 = r14
            goto L_0x01a8
        L_0x00d7:
            long r18 = r2.longValue()     // Catch:{ all -> 0x01fd }
            r20 = r13
            goto L_0x00e7
        L_0x00de:
            int r2 = r2.a()     // Catch:{ all -> 0x01fd }
            r20 = r13
            long r12 = (long) r2     // Catch:{ all -> 0x01fd }
            long r18 = r3 * r12
        L_0x00e7:
            r12 = r18
            r18 = 4
            int r2 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r2 <= 0) goto L_0x010e
            int r1 = r1 + 8
            long r1 = r9.i(r1)     // Catch:{ all -> 0x01fd }
            r21 = 0
            long r21 = r1 + r12
            long r23 = r30.a()     // Catch:{ all -> 0x01fd }
            int r25 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r25 <= 0) goto L_0x0109
            java.lang.String r1 = "Illegal TIFF tag pointer offset"
            r8.b((java.lang.String) r1)     // Catch:{ all -> 0x01fd }
            r21 = r7
            goto L_0x00d3
        L_0x0109:
            r26 = r14
            long r14 = (long) r11
            long r14 = r14 + r1
            goto L_0x0113
        L_0x010e:
            r26 = r14
            int r1 = r1 + 8
            long r14 = (long) r1
        L_0x0113:
            r1 = 0
            int r21 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r21 < 0) goto L_0x01a1
            long r21 = r30.a()     // Catch:{ all -> 0x01fb }
            int r23 = (r14 > r21 ? 1 : (r14 == r21 ? 0 : -1))
            if (r23 <= 0) goto L_0x0123
            goto L_0x01a1
        L_0x0123:
            int r21 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r21 < 0) goto L_0x0189
            long r1 = r14 + r12
            long r21 = r30.a()     // Catch:{ all -> 0x01fb }
            int r23 = (r1 > r21 ? 1 : (r1 == r21 ? 0 : -1))
            if (r23 <= 0) goto L_0x0132
            goto L_0x0189
        L_0x0132:
            long r18 = r18 * r3
            int r1 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r1 != 0) goto L_0x0158
            r27 = r12
            r1 = 0
            r2 = 0
        L_0x013c:
            long r12 = (long) r1     // Catch:{ all -> 0x01fb }
            int r18 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r18 >= 0) goto L_0x015b
            boolean r12 = r8.b((int) r6)     // Catch:{ all -> 0x01fb }
            if (r12 == 0) goto L_0x0155
            int r2 = r1 * 4
            long r12 = (long) r2     // Catch:{ all -> 0x01fb }
            long r12 = r12 + r14
            int r2 = (int) r12     // Catch:{ all -> 0x01fb }
            int r2 = r9.j(r2)     // Catch:{ all -> 0x01fb }
            int r2 = r2 + r11
            a(r8, r9, r0, r2, r11)     // Catch:{ all -> 0x01fb }
            r2 = 1
        L_0x0155:
            int r1 = r1 + 1
            goto L_0x013c
        L_0x0158:
            r27 = r12
            r2 = 0
        L_0x015b:
            if (r2 != 0) goto L_0x0186
            int r12 = (int) r14     // Catch:{ all -> 0x01fb }
            r1 = r27
            int r13 = (int) r1     // Catch:{ all -> 0x01fb }
            r1 = r29
            r2 = r12
            r14 = r3
            r3 = r31
            r4 = r33
            r18 = r5
            r5 = r30
            r19 = r6
            r21 = r7
            r7 = r13
            boolean r1 = r1.a(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01fb }
            if (r1 != 0) goto L_0x01a8
            int r4 = (int) r14     // Catch:{ all -> 0x01fb }
            r1 = r29
            r2 = r19
            r3 = r12
            r5 = r18
            r6 = r30
            a(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x01fb }
            goto L_0x01a8
        L_0x0186:
            r21 = r7
            goto L_0x01a8
        L_0x0189:
            r21 = r7
            r1 = r12
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fb }
            r3.<init>()     // Catch:{ all -> 0x01fb }
            java.lang.String r4 = "Illegal number of bytes for TIFF tag data: "
            r3.append(r4)     // Catch:{ all -> 0x01fb }
            r3.append(r1)     // Catch:{ all -> 0x01fb }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x01fb }
            r8.b((java.lang.String) r1)     // Catch:{ all -> 0x01fb }
            goto L_0x01a8
        L_0x01a1:
            r21 = r7
            java.lang.String r1 = "Illegal TIFF tag pointer offset"
            r8.b((java.lang.String) r1)     // Catch:{ all -> 0x01fb }
        L_0x01a8:
            int r7 = r21 + 1
            r13 = r20
            r14 = r26
            r12 = 1
            r15 = 2
            goto L_0x007d
        L_0x01b2:
            r2 = r13
            r26 = r14
            int r1 = a(r10, r2)     // Catch:{ all -> 0x01fb }
            int r1 = r9.j(r1)     // Catch:{ all -> 0x01fb }
            if (r1 == 0) goto L_0x01ee
            int r1 = r1 + r11
            long r2 = (long) r1     // Catch:{ all -> 0x01fb }
            long r4 = r30.a()     // Catch:{ all -> 0x01fb }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x01d6
            r29.b()
            if (r26 == 0) goto L_0x01d5
            boolean r0 = r26.booleanValue()
            r9.a((boolean) r0)
        L_0x01d5:
            return
        L_0x01d6:
            if (r1 >= r10) goto L_0x01e5
            r29.b()
            if (r26 == 0) goto L_0x01e4
            boolean r0 = r26.booleanValue()
            r9.a((boolean) r0)
        L_0x01e4:
            return
        L_0x01e5:
            boolean r2 = r29.a()     // Catch:{ all -> 0x01fb }
            if (r2 == 0) goto L_0x01ee
            a(r8, r9, r0, r1, r11)     // Catch:{ all -> 0x01fb }
        L_0x01ee:
            r29.b()
            if (r26 == 0) goto L_0x01fa
            boolean r0 = r26.booleanValue()
            r9.a((boolean) r0)
        L_0x01fa:
            return
        L_0x01fb:
            r0 = move-exception
            goto L_0x020d
        L_0x01fd:
            r0 = move-exception
            r26 = r14
            goto L_0x020d
        L_0x0201:
            java.lang.String r0 = "Ignored IFD marked to start outside data segment"
            r8.b((java.lang.String) r0)     // Catch:{ all -> 0x020a }
            r29.b()
            return
        L_0x020a:
            r0 = move-exception
            r26 = r1
        L_0x020d:
            r29.b()
            if (r26 == 0) goto L_0x0219
            boolean r1 = r26.booleanValue()
            r9.a((boolean) r1)
        L_0x0219:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.imaging.tiff.TiffReader.a(com.drew.imaging.tiff.TiffHandler, com.drew.lang.RandomAccessReader, java.util.Set, int, int):void");
    }

    private static void a(@NotNull TiffHandler tiffHandler, int i, int i2, int i3, int i4, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int i5 = 0;
        switch (i4) {
            case 1:
                if (i3 == 1) {
                    tiffHandler.a(i, randomAccessReader.d(i2));
                    return;
                }
                short[] sArr = new short[i3];
                while (i5 < i3) {
                    sArr[i5] = randomAccessReader.d(i2 + i5);
                    i5++;
                }
                tiffHandler.a(i, sArr);
                return;
            case 2:
                tiffHandler.a(i, randomAccessReader.d(i2, i3, (Charset) null));
                return;
            case 3:
                if (i3 == 1) {
                    tiffHandler.b(i, randomAccessReader.f(i2));
                    return;
                }
                int[] iArr = new int[i3];
                while (i5 < i3) {
                    iArr[i5] = randomAccessReader.f((i5 * 2) + i2);
                    i5++;
                }
                tiffHandler.a(i, iArr);
                return;
            case 4:
                if (i3 == 1) {
                    tiffHandler.a(i, randomAccessReader.i(i2));
                    return;
                }
                long[] jArr = new long[i3];
                while (i5 < i3) {
                    jArr[i5] = randomAccessReader.i((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.a(i, jArr);
                return;
            case 5:
                if (i3 == 1) {
                    tiffHandler.a(i, new Rational(randomAccessReader.i(i2), randomAccessReader.i(i2 + 4)));
                    return;
                } else if (i3 > 1) {
                    Rational[] rationalArr = new Rational[i3];
                    while (i5 < i3) {
                        int i6 = i5 * 8;
                        rationalArr[i5] = new Rational(randomAccessReader.i(i2 + i6), randomAccessReader.i(i2 + 4 + i6));
                        i5++;
                    }
                    tiffHandler.a(i, rationalArr);
                    return;
                } else {
                    return;
                }
            case 6:
                if (i3 == 1) {
                    tiffHandler.a(i, randomAccessReader.e(i2));
                    return;
                }
                byte[] bArr = new byte[i3];
                while (i5 < i3) {
                    bArr[i5] = randomAccessReader.e(i2 + i5);
                    i5++;
                }
                tiffHandler.b(i, bArr);
                return;
            case 7:
                tiffHandler.a(i, randomAccessReader.c(i2, i3));
                return;
            case 8:
                if (i3 == 1) {
                    tiffHandler.a(i, (int) randomAccessReader.g(i2));
                    return;
                }
                short[] sArr2 = new short[i3];
                while (i5 < i3) {
                    sArr2[i5] = randomAccessReader.g((i5 * 2) + i2);
                    i5++;
                }
                tiffHandler.b(i, sArr2);
                return;
            case 9:
                if (i3 == 1) {
                    tiffHandler.c(i, randomAccessReader.j(i2));
                    return;
                }
                int[] iArr2 = new int[i3];
                while (i5 < i3) {
                    iArr2[i5] = randomAccessReader.j((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.b(i, iArr2);
                return;
            case 10:
                if (i3 == 1) {
                    tiffHandler.a(i, new Rational((long) randomAccessReader.j(i2), (long) randomAccessReader.j(i2 + 4)));
                    return;
                } else if (i3 > 1) {
                    Rational[] rationalArr2 = new Rational[i3];
                    while (i5 < i3) {
                        int i7 = i5 * 8;
                        rationalArr2[i5] = new Rational((long) randomAccessReader.j(i2 + i7), (long) randomAccessReader.j(i2 + 4 + i7));
                        i5++;
                    }
                    tiffHandler.a(i, rationalArr2);
                    return;
                } else {
                    return;
                }
            case 11:
                if (i3 == 1) {
                    tiffHandler.a(i, randomAccessReader.m(i2));
                    return;
                }
                float[] fArr = new float[i3];
                while (i5 < i3) {
                    fArr[i5] = randomAccessReader.m((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.a(i, fArr);
                return;
            case 12:
                if (i3 == 1) {
                    tiffHandler.a(i, randomAccessReader.n(i2));
                    return;
                }
                double[] dArr = new double[i3];
                while (i5 < i3) {
                    dArr[i5] = randomAccessReader.n((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.a(i, dArr);
                return;
            default:
                tiffHandler.b(String.format("Invalid TIFF tag format code %d for tag 0x%04X", new Object[]{Integer.valueOf(i4), Integer.valueOf(i)}));
                return;
        }
    }
}
