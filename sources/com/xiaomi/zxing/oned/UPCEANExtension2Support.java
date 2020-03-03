package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultMetadataType;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f1705a = new int[4];
    private final StringBuilder b = new StringBuilder();

    UPCEANExtension2Support() {
    }

    /* access modifiers changed from: package-private */
    public Result a(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.b;
        sb.setLength(0);
        int a2 = a(bitArray, iArr, sb);
        String sb2 = sb.toString();
        Map<ResultMetadataType, Object> a3 = a(sb2);
        float f = (float) i;
        Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, f), new ResultPoint((float) a2, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (a3 != null) {
            result.a(a3);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public int a(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f1705a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a2 = bitArray.a();
        int i = iArr[1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2 && i < a2) {
            int a3 = UPCEANReader.a(bitArray, iArr2, i, UPCEANReader.f);
            sb.append((char) ((a3 % 10) + 48));
            int i4 = i;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            if (a3 >= 10) {
                i3 = (1 << (1 - i2)) | i3;
            }
            i = i2 != 1 ? bitArray.e(bitArray.d(i4)) : i4;
            i2++;
        }
        if (sb.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        } else if (Integer.parseInt(sb.toString()) % 4 == i3) {
            return i;
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Map<ResultMetadataType, Object> a(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
