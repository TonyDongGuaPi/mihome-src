package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Reader;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultMetadataType;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result a(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void a() {
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return b(binaryBitmap, map);
        } catch (NotFoundException e) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !binaryBitmap.e()) {
                throw e;
            }
            BinaryBitmap f = binaryBitmap.f();
            Result b = b(f, map);
            Map<ResultMetadataType, Object> e2 = b.e();
            int i = 270;
            if (e2 != null && e2.containsKey(ResultMetadataType.ORIENTATION)) {
                i = (((Integer) e2.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            b.a(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
            ResultPoint[] c = b.c();
            if (c != null) {
                int b2 = f.b();
                for (int i2 = 0; i2 < c.length; i2++) {
                    c[i2] = new ResultPoint((((float) b2) - c[i2].b()) - 1.0f, c[i2].a());
                }
            }
            return b;
        }
    }

    private Result b(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        int i;
        int i2;
        Map<DecodeHintType, ?> map2 = map;
        int a2 = binaryBitmap.a();
        int b = binaryBitmap.b();
        BitArray bitArray = new BitArray(a2);
        int i3 = b >> 1;
        char c = 0;
        int i4 = 1;
        boolean z = map2 != null && map2.containsKey(DecodeHintType.TRY_HARDER);
        int max = Math.max(1, b >> (z ? 8 : 5));
        int i5 = z ? b : 15;
        EnumMap enumMap = map2;
        int i6 = 0;
        while (i6 < i5) {
            int i7 = i6 + 1;
            int i8 = i7 / 2;
            if (!((i6 & 1) == 0)) {
                i8 = -i8;
            }
            int i9 = (i8 * max) + i3;
            if (i9 < 0 || i9 >= b) {
                break;
            }
            try {
                BitArray a3 = binaryBitmap.a(i9, bitArray);
                int i10 = 0;
                while (i10 < 2) {
                    if (i10 == i4) {
                        a3.e();
                        if (enumMap != null && enumMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                            EnumMap enumMap2 = new EnumMap(DecodeHintType.class);
                            enumMap2.putAll(enumMap);
                            enumMap2.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                            enumMap = enumMap2;
                        }
                    }
                    try {
                        Result a4 = a(i9, a3, enumMap);
                        if (i10 == i4) {
                            a4.a(ResultMetadataType.ORIENTATION, 180);
                            ResultPoint[] c2 = a4.c();
                            if (c2 != null) {
                                float f = (float) a2;
                                i2 = a2;
                                try {
                                    c2[0] = new ResultPoint((f - c2[c].a()) - 1.0f, c2[c].b());
                                    try {
                                        c2[1] = new ResultPoint((f - c2[1].a()) - 1.0f, c2[1].b());
                                    } catch (ReaderException unused) {
                                        continue;
                                    }
                                } catch (ReaderException unused2) {
                                    i10++;
                                    a2 = i2;
                                    BinaryBitmap binaryBitmap2 = binaryBitmap;
                                    c = 0;
                                    i4 = 1;
                                }
                            }
                        }
                        return a4;
                    } catch (ReaderException unused3) {
                        i2 = a2;
                        i10++;
                        a2 = i2;
                        BinaryBitmap binaryBitmap22 = binaryBitmap;
                        c = 0;
                        i4 = 1;
                    }
                }
                i = a2;
                bitArray = a3;
            } catch (NotFoundException unused4) {
                i = a2;
            }
            i6 = i7;
            a2 = i;
            c = 0;
            i4 = 1;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void a(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int a2 = bitArray.a();
        if (i < a2) {
            boolean z = !bitArray.a(i);
            int i2 = 0;
            while (i < a2) {
                if (bitArray.a(i) ^ z) {
                    iArr[i2] = iArr[i2] + 1;
                } else {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    iArr[i2] = 1;
                    z = !z;
                }
                i++;
            }
            if (i2 == length) {
                return;
            }
            if (i2 != length - 1 || i != a2) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void b(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean a2 = bitArray.a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.a(i) != a2) {
                length--;
                a2 = !a2;
            }
        }
        if (length < 0) {
            a(bitArray, i + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static float a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i;
        float f3 = f2 / ((float) i2);
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            float f6 = ((float) iArr2[i4]) * f3;
            float f7 = (float) i5;
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
