package com.xiaomi.zxing.multi;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Reader;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1685a = 100;
    private static final int b = 4;
    private final Reader c;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.c = reader;
    }

    public Result[] a_(BinaryBitmap binaryBitmap) throws NotFoundException {
        return a_(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result[] a_(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        a(binaryBitmap, map, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i, int i2, int i3) {
        boolean z;
        int i4;
        int i5;
        float f;
        BinaryBitmap binaryBitmap2 = binaryBitmap;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        if (i8 <= 4) {
            try {
                Result a2 = this.c.a(binaryBitmap2, map);
                Iterator<Result> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().a().equals(a2.a())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    list.add(a(a2, i6, i7));
                } else {
                    List<Result> list2 = list;
                }
                ResultPoint[] c2 = a2.c();
                if (c2 != null && c2.length != 0) {
                    int a3 = binaryBitmap.a();
                    int b2 = binaryBitmap.b();
                    float f2 = (float) b2;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    float f5 = (float) a3;
                    for (ResultPoint resultPoint : c2) {
                        if (resultPoint != null) {
                            float a4 = resultPoint.a();
                            float b3 = resultPoint.b();
                            if (a4 < f5) {
                                f5 = a4;
                            }
                            if (b3 < f2) {
                                f2 = b3;
                            }
                            if (a4 > f3) {
                                f3 = a4;
                            }
                            if (b3 > f4) {
                                f4 = b3;
                            }
                        }
                    }
                    if (f5 > 100.0f) {
                        f = f2;
                        i5 = b2;
                        i4 = a3;
                        a(binaryBitmap2.a(0, 0, (int) f5, b2), map, list, i, i2, i8 + 1);
                    } else {
                        f = f2;
                        i5 = b2;
                        i4 = a3;
                    }
                    if (f > 100.0f) {
                        a(binaryBitmap2.a(0, 0, i4, (int) f), map, list, i, i2, i8 + 1);
                    }
                    if (f3 < ((float) (i4 - 100))) {
                        int i9 = (int) f3;
                        a(binaryBitmap2.a(i9, 0, i4 - i9, i5), map, list, i6 + i9, i2, i8 + 1);
                    }
                    if (f4 < ((float) (i5 - 100))) {
                        int i10 = (int) f4;
                        a(binaryBitmap2.a(0, i10, i4, i5 - i10), map, list, i, i7 + i10, i8 + 1);
                    }
                }
            } catch (ReaderException unused) {
            }
        }
    }

    private static Result a(Result result, int i, int i2) {
        ResultPoint[] c2 = result.c();
        if (c2 == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[c2.length];
        for (int i3 = 0; i3 < c2.length; i3++) {
            ResultPoint resultPoint = c2[i3];
            if (resultPoint != null) {
                resultPointArr[i3] = new ResultPoint(resultPoint.a() + ((float) i), resultPoint.b() + ((float) i2));
            }
        }
        Result result2 = new Result(result.a(), result.b(), resultPointArr, result.d());
        result2.a(result.e());
        return result2;
    }
}
