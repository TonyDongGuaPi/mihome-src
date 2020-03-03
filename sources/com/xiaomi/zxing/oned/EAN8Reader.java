package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

public final class EAN8Reader extends UPCEANReader {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f1696a = new int[4];

    /* access modifiers changed from: protected */
    public int a(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f1696a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a2 = bitArray.a();
        int i = iArr[1];
        int i2 = 0;
        while (i2 < 4 && i < a2) {
            sb.append((char) (a(bitArray, iArr2, i, e) + 48));
            int i3 = i;
            for (int i4 : iArr2) {
                i3 += i4;
            }
            i2++;
            i = i3;
        }
        int i5 = a(bitArray, i, true, c)[1];
        int i6 = 0;
        while (i6 < 4 && i5 < a2) {
            sb.append((char) (a(bitArray, iArr2, i5, e) + 48));
            int i7 = i5;
            for (int i8 : iArr2) {
                i7 += i8;
            }
            i6++;
            i5 = i7;
        }
        return i5;
    }

    /* access modifiers changed from: package-private */
    public BarcodeFormat b() {
        return BarcodeFormat.EAN_8;
    }
}
