package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import java.util.Map;

public final class ITFReader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    static final int[][] f1699a = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final float b = 0.38f;
    private static final float c = 0.78f;
    private static final int d = 3;
    private static final int e = 1;
    private static final int[] f = {6, 8, 10, 12, 14};
    private static final int[] h = {1, 1, 1, 1};
    private static final int[] i = {1, 1, 3};
    private int g = -1;

    public Result a(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z;
        int[] a2 = a(bitArray);
        int[] b2 = b(bitArray);
        StringBuilder sb = new StringBuilder(20);
        a(bitArray, a2[1], b2[0], sb);
        String sb2 = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = f;
        }
        int length = sb2.length();
        int length2 = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length2) {
                z = false;
                break;
            }
            int i5 = iArr[i3];
            if (length == i5) {
                z = true;
                break;
            }
            if (i5 > i4) {
                i4 = i5;
            }
            i3++;
        }
        if (!z && length > i4) {
            z = true;
        }
        if (z) {
            float f2 = (float) i2;
            return new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint((float) a2[1], f2), new ResultPoint((float) b2[0], f2)}, BarcodeFormat.ITF);
        }
        throw FormatException.getFormatInstance();
    }

    private static void a(BitArray bitArray, int i2, int i3, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i2 < i3) {
            a(bitArray, i2, iArr);
            for (int i4 = 0; i4 < 5; i4++) {
                int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            sb.append((char) (a(iArr2) + 48));
            sb.append((char) (a(iArr3) + 48));
            for (int i6 : iArr) {
                i2 += i6;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int[] a(BitArray bitArray) throws NotFoundException {
        int[] c2 = c(bitArray, c(bitArray), h);
        this.g = (c2[1] - c2[0]) / 4;
        a(bitArray, c2[0]);
        return c2;
    }

    private void a(BitArray bitArray, int i2) throws NotFoundException {
        int i3 = this.g * 10;
        if (i3 >= i2) {
            i3 = i2;
        }
        int i4 = i2 - 1;
        while (i3 > 0 && i4 >= 0 && !bitArray.a(i4)) {
            i3--;
            i4--;
        }
        if (i3 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static int c(BitArray bitArray) throws NotFoundException {
        int a2 = bitArray.a();
        int d2 = bitArray.d(0);
        if (d2 != a2) {
            return d2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: package-private */
    public int[] b(BitArray bitArray) throws NotFoundException {
        bitArray.e();
        try {
            int[] c2 = c(bitArray, c(bitArray), i);
            a(bitArray, c2[0]);
            int i2 = c2[0];
            c2[0] = bitArray.a() - c2[1];
            c2[1] = bitArray.a() - i2;
            return c2;
        } finally {
            bitArray.e();
        }
    }

    private static int[] c(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int a2 = bitArray.a();
        int i3 = i2;
        boolean z = false;
        int i4 = 0;
        while (i2 < a2) {
            if (bitArray.a(i2) ^ z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else if (a(iArr2, iArr, (float) c) < b) {
                    return new int[]{i3, i2};
                } else {
                    i3 += iArr2[0] + iArr2[1];
                    int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(int[] iArr) throws NotFoundException {
        int length = f1699a.length;
        float f2 = b;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float a2 = a(iArr, f1699a[i3], (float) c);
            if (a2 < f2) {
                i2 = i3;
                f2 = a2;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
