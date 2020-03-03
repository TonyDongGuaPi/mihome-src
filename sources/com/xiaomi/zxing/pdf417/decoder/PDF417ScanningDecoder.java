package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.pdf417.PDF417Common;
import com.xiaomi.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;

public final class PDF417ScanningDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1743a = 2;
    private static final int b = 3;
    private static final int c = 512;
    private static final ErrorCorrection d = new ErrorCorrection();

    private static int a(int i) {
        return 2 << i;
    }

    private static boolean a(int i, int i2, int i3) {
        return i2 + -2 <= i && i <= i3 + 2;
    }

    private PDF417ScanningDecoder() {
    }

    public static DecoderResult a(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn;
        DetectionResultColumn detectionResultColumn;
        int i3;
        int i4;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        DetectionResult detectionResult = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        int i5 = 0;
        while (true) {
            if (i5 >= 2) {
                detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn2;
                break;
            }
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn2 = a(bitMatrix, boundingBox, resultPoint, true, i, i2);
            }
            detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn2;
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn3 = a(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResult = a(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn3);
            if (detectionResult == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i5 != 0 || detectionResult.e() == null || (detectionResult.e().c() >= boundingBox.c() && detectionResult.e().d() <= boundingBox.d())) {
                detectionResult.a(boundingBox);
            } else {
                boundingBox = detectionResult.e();
                i5++;
                detectionResultRowIndicatorColumn2 = detectionResultRowIndicatorColumn;
            }
        }
        int b2 = detectionResult.b() + 1;
        detectionResult.a(0, (DetectionResultColumn) detectionResultRowIndicatorColumn);
        detectionResult.a(b2, (DetectionResultColumn) detectionResultRowIndicatorColumn3);
        boolean z = detectionResultRowIndicatorColumn != null;
        int i6 = i;
        int i7 = i2;
        for (int i8 = 1; i8 <= b2; i8++) {
            int i9 = z ? i8 : b2 - i8;
            if (detectionResult.a(i9) == null) {
                if (i9 == 0 || i9 == b2) {
                    detectionResultColumn = new DetectionResultRowIndicatorColumn(boundingBox, i9 == 0);
                } else {
                    detectionResultColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.a(i9, detectionResultColumn);
                int c2 = boundingBox.c();
                int i10 = i7;
                int i11 = i6;
                int i12 = -1;
                while (c2 <= boundingBox.d()) {
                    int a2 = a(detectionResult, i9, c2, z);
                    if (a2 >= 0 && a2 <= boundingBox.b()) {
                        i4 = a2;
                    } else if (i12 == -1) {
                        i3 = i10;
                        i10 = i3;
                        c2++;
                    } else {
                        i4 = i12;
                    }
                    int i13 = i10;
                    Codeword a3 = a(bitMatrix, boundingBox.a(), boundingBox.b(), z, i4, c2, i11, i13);
                    if (a3 != null) {
                        detectionResultColumn.a(c2, a3);
                        i11 = Math.min(i11, a3.c());
                        i10 = Math.max(i13, a3.c());
                        i12 = i4;
                        c2++;
                    } else {
                        i3 = i13;
                        i10 = i3;
                        c2++;
                    }
                }
                i6 = i11;
                i7 = i10;
            }
        }
        return a(detectionResult);
    }

    private static DetectionResult a(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException, FormatException {
        BarcodeMetadata b2;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (b2 = b(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(b2, BoundingBox.a(a(detectionResultRowIndicatorColumn), a(detectionResultRowIndicatorColumn2)));
    }

    private static BoundingBox a(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException, FormatException {
        int[] d2;
        if (detectionResultRowIndicatorColumn == null || (d2 = detectionResultRowIndicatorColumn.d()) == null) {
            return null;
        }
        int a2 = a(d2);
        int i = 0;
        int i2 = 0;
        for (int i3 : d2) {
            i2 += a2 - i3;
            if (i3 > 0) {
                break;
            }
        }
        Codeword[] b2 = detectionResultRowIndicatorColumn.b();
        int i4 = 0;
        while (i2 > 0 && b2[i4] == null) {
            i2--;
            i4++;
        }
        for (int length = d2.length - 1; length >= 0; length--) {
            i += a2 - d2[length];
            if (d2[length] > 0) {
                break;
            }
        }
        int length2 = b2.length - 1;
        while (i > 0 && b2[length2] == null) {
            i--;
            length2--;
        }
        return detectionResultRowIndicatorColumn.a().a(i2, i, detectionResultRowIndicatorColumn.f());
    }

    private static int a(int[] iArr) {
        int i = -1;
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    private static BarcodeMetadata b(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata e;
        BarcodeMetadata e2;
        if (detectionResultRowIndicatorColumn == null || (e = detectionResultRowIndicatorColumn.e()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.e();
        } else if (detectionResultRowIndicatorColumn2 == null || (e2 = detectionResultRowIndicatorColumn2.e()) == null || e.a() == e2.a() || e.b() == e2.b() || e.c() == e2.c()) {
            return e;
        } else {
            return null;
        }
    }

    private static DetectionResultRowIndicatorColumn a(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        boolean z2 = z;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z2);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int a2 = (int) resultPoint.a();
            int b2 = (int) resultPoint.b();
            while (b2 <= boundingBox.d() && b2 >= boundingBox.c()) {
                Codeword a3 = a(bitMatrix, 0, bitMatrix.f(), z, a2, b2, i, i2);
                if (a3 != null) {
                    detectionResultRowIndicatorColumn.a(b2, a3);
                    if (z2) {
                        a2 = a3.d();
                    } else {
                        a2 = a3.e();
                    }
                }
                b2 += i4;
            }
            i3++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static void a(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        int[] a2 = barcodeValueArr[0][1].a();
        int b2 = (detectionResult.b() * detectionResult.c()) - a(detectionResult.d());
        if (a2.length == 0) {
            if (b2 < 1 || b2 > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].a(b2);
        } else if (a2[0] != b2) {
            barcodeValueArr[0][1].a(b2);
        }
    }

    private static DecoderResult a(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] b2 = b(detectionResult);
        a(detectionResult, b2);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[(detectionResult.c() * detectionResult.b())];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < detectionResult.c(); i++) {
            int i2 = 0;
            while (i2 < detectionResult.b()) {
                int i3 = i2 + 1;
                int[] a2 = b2[i][i3].a();
                int b3 = (detectionResult.b() * i) + i2;
                if (a2.length == 0) {
                    arrayList.add(Integer.valueOf(b3));
                } else if (a2.length == 1) {
                    iArr[b3] = a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(b3));
                    arrayList2.add(a2);
                }
                i2 = i3;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return a(detectionResult.d(), iArr, PDF417Common.a((Collection<Integer>) arrayList), PDF417Common.a((Collection<Integer>) arrayList3), iArr2);
    }

    private static DecoderResult a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < iArr5.length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return a(iArr, i, iArr2);
                } catch (ChecksumException unused) {
                    if (iArr5.length != 0) {
                        int i5 = 0;
                        while (true) {
                            if (i5 >= iArr5.length) {
                                break;
                            } else if (iArr5[i5] < iArr4[i5].length - 1) {
                                iArr5[i5] = iArr5[i5] + 1;
                                break;
                            } else {
                                iArr5[i5] = 0;
                                if (i5 != iArr5.length - 1) {
                                    i5++;
                                } else {
                                    throw ChecksumException.getChecksumInstance();
                                }
                            }
                        }
                        i2 = i3;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private static BarcodeValue[][] b(DetectionResult detectionResult) {
        int h;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, new int[]{detectionResult.c(), detectionResult.b() + 2});
        for (int i = 0; i < barcodeValueArr.length; i++) {
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                barcodeValueArr[i][i2] = new BarcodeValue();
            }
        }
        int i3 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.a()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.b()) {
                    if (codeword != null && (h = codeword.h()) >= 0 && h < barcodeValueArr.length) {
                        barcodeValueArr[h][i3].a(codeword.g());
                    }
                }
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static boolean a(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.b() + 1;
    }

    private static int a(DetectionResult detectionResult, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        Codeword codeword = null;
        int i4 = i - i3;
        if (a(detectionResult, i4)) {
            codeword = detectionResult.a(i4).c(i2);
        }
        if (codeword != null) {
            return z ? codeword.e() : codeword.d();
        }
        Codeword a2 = detectionResult.a(i).a(i2);
        if (a2 != null) {
            return z ? a2.d() : a2.e();
        }
        if (a(detectionResult, i4)) {
            a2 = detectionResult.a(i4).a(i2);
        }
        if (a2 != null) {
            return z ? a2.e() : a2.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!a(detectionResult, i)) {
                return z ? detectionResult.e().a() : detectionResult.e().b();
            }
            for (Codeword codeword2 : detectionResult.a(i).b()) {
                if (codeword2 != null) {
                    return (z ? codeword2.e() : codeword2.d()) + (i3 * i5 * (codeword2.e() - codeword2.d()));
                }
            }
            i5++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        r7 = com.xiaomi.zxing.pdf417.decoder.PDF417CodewordDecoder.a(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.xiaomi.zxing.pdf417.decoder.Codeword a(com.xiaomi.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12, int r13, int r14) {
        /*
            int r11 = b(r7, r8, r9, r10, r11, r12)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            int[] r7 = a((com.xiaomi.zxing.common.BitMatrix) r0, (int) r1, (int) r2, (boolean) r3, (int) r4, (int) r5)
            r8 = 0
            if (r7 != 0) goto L_0x0012
            return r8
        L_0x0012:
            int r9 = com.xiaomi.zxing.common.detector.MathUtils.a((int[]) r7)
            if (r10 == 0) goto L_0x001e
            int r10 = r11 + r9
            r6 = r11
            r11 = r10
            r10 = r6
            goto L_0x0039
        L_0x001e:
            r10 = 0
        L_0x001f:
            int r12 = r7.length
            int r12 = r12 / 2
            if (r10 >= r12) goto L_0x0037
            r12 = r7[r10]
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r0 = r7[r0]
            r7[r10] = r0
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r7[r0] = r12
            int r10 = r10 + 1
            goto L_0x001f
        L_0x0037:
            int r10 = r11 - r9
        L_0x0039:
            boolean r9 = a((int) r9, (int) r13, (int) r14)
            if (r9 != 0) goto L_0x0040
            return r8
        L_0x0040:
            int r7 = com.xiaomi.zxing.pdf417.decoder.PDF417CodewordDecoder.a(r7)
            int r9 = com.xiaomi.zxing.pdf417.PDF417Common.a((int) r7)
            r12 = -1
            if (r9 != r12) goto L_0x004c
            return r8
        L_0x004c:
            com.xiaomi.zxing.pdf417.decoder.Codeword r8 = new com.xiaomi.zxing.pdf417.decoder.Codeword
            int r7 = c(r7)
            r8.<init>(r10, r11, r7, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.decoder.PDF417ScanningDecoder.a(com.xiaomi.zxing.common.BitMatrix, int, int, boolean, int, int, int, int):com.xiaomi.zxing.pdf417.decoder.Codeword");
    }

    private static int[] a(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (!z) {
                if (i3 < i) {
                    break;
                }
            } else if (i3 >= i2) {
                break;
            }
            if (i6 >= iArr.length) {
                break;
            } else if (bitMatrix.a(i3, i4) == z2) {
                iArr[i6] = iArr[i6] + 1;
                i3 += i5;
            } else {
                i6++;
                z2 = !z2;
            }
        }
        if (i6 != iArr.length) {
            if (z) {
                i = i2;
            }
            if (!(i3 == i && i6 == iArr.length - 1)) {
                return null;
            }
        }
        return iArr;
    }

    private static int b(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        boolean z2 = z;
        int i5 = z ? -1 : 1;
        int i6 = i3;
        for (int i7 = 0; i7 < 2; i7++) {
            while (true) {
                if (!z2) {
                    if (i6 >= i2) {
                        break;
                    }
                } else if (i6 < i) {
                    break;
                }
                if (z2 != bitMatrix.a(i6, i4)) {
                    break;
                } else if (Math.abs(i3 - i6) > 2) {
                    return i3;
                } else {
                    i6 += i5;
                }
            }
            i5 = -i5;
            z2 = !z2;
        }
        return i6;
    }

    private static DecoderResult a(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int a2 = a(iArr, iArr2, i2);
            a(iArr, i2);
            DecoderResult a3 = DecodedBitStreamParser.a(iArr, String.valueOf(i));
            a3.a(Integer.valueOf(a2));
            a3.b(Integer.valueOf(iArr2.length));
            return a3;
        }
        throw FormatException.getFormatInstance();
    }

    private static int a(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return d.a(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void a(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (i2 != 0) {
            } else {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] b(int i) {
        int[] iArr = new int[8];
        int length = iArr.length - 1;
        int i2 = 0;
        while (true) {
            int i3 = i & 1;
            if (i3 != i2) {
                length--;
                if (length < 0) {
                    return iArr;
                }
                i2 = i3;
            }
            iArr[length] = iArr[length] + 1;
            i >>= 1;
        }
    }

    private static int c(int i) {
        return b(b(i));
    }

    private static int b(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    public static String a(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < barcodeValueArr.length; i++) {
            formatter.format("Row %2d: ", new Object[]{Integer.valueOf(i)});
            for (BarcodeValue barcodeValue : barcodeValueArr[i]) {
                if (barcodeValue.a().length == 0) {
                    formatter.format("        ", (Object[]) null);
                } else {
                    formatter.format("%4d(%2d)", new Object[]{Integer.valueOf(barcodeValue.a()[0]), barcodeValue.b(barcodeValue.a()[0])});
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
