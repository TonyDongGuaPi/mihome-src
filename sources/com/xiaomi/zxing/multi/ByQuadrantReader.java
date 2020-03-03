package com.xiaomi.zxing.multi;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Reader;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import java.util.Map;

public final class ByQuadrantReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    private final Reader f1684a;

    public ByQuadrantReader(Reader reader) {
        this.f1684a = reader;
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return a(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2 = r5.f1684a.a(r6.a(r0, r1, r0, r1), r7);
        a(r2.c(), r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        r2 = r0 / 2;
        r3 = r1 / 2;
        r6 = r5.f1684a.a(r6.a(r2, r3, r0, r1), r7);
        a(r6.c(), r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0063, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        r3 = r5.f1684a.a(r6.a(r0, 0, r0, r1), r7);
        a(r3.c(), r0, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0029, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3 = r5.f1684a.a(r6.a(0, r1, r0, r1), r7);
        a(r3.c(), 0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0018 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.Result a(com.xiaomi.zxing.BinaryBitmap r6, java.util.Map<com.xiaomi.zxing.DecodeHintType, ?> r7) throws com.xiaomi.zxing.NotFoundException, com.xiaomi.zxing.ChecksumException, com.xiaomi.zxing.FormatException {
        /*
            r5 = this;
            int r0 = r6.a()
            int r1 = r6.b()
            int r0 = r0 / 2
            int r1 = r1 / 2
            r2 = 0
            com.xiaomi.zxing.Reader r3 = r5.f1684a     // Catch:{ NotFoundException -> 0x0018 }
            com.xiaomi.zxing.BinaryBitmap r4 = r6.a(r2, r2, r0, r1)     // Catch:{ NotFoundException -> 0x0018 }
            com.xiaomi.zxing.Result r3 = r3.a(r4, r7)     // Catch:{ NotFoundException -> 0x0018 }
            return r3
        L_0x0018:
            com.xiaomi.zxing.Reader r3 = r5.f1684a     // Catch:{ NotFoundException -> 0x002a }
            com.xiaomi.zxing.BinaryBitmap r4 = r6.a(r0, r2, r0, r1)     // Catch:{ NotFoundException -> 0x002a }
            com.xiaomi.zxing.Result r3 = r3.a(r4, r7)     // Catch:{ NotFoundException -> 0x002a }
            com.xiaomi.zxing.ResultPoint[] r4 = r3.c()     // Catch:{ NotFoundException -> 0x002a }
            a(r4, r0, r2)     // Catch:{ NotFoundException -> 0x002a }
            return r3
        L_0x002a:
            com.xiaomi.zxing.Reader r3 = r5.f1684a     // Catch:{ NotFoundException -> 0x003c }
            com.xiaomi.zxing.BinaryBitmap r4 = r6.a(r2, r1, r0, r1)     // Catch:{ NotFoundException -> 0x003c }
            com.xiaomi.zxing.Result r3 = r3.a(r4, r7)     // Catch:{ NotFoundException -> 0x003c }
            com.xiaomi.zxing.ResultPoint[] r4 = r3.c()     // Catch:{ NotFoundException -> 0x003c }
            a(r4, r2, r1)     // Catch:{ NotFoundException -> 0x003c }
            return r3
        L_0x003c:
            com.xiaomi.zxing.Reader r2 = r5.f1684a     // Catch:{ NotFoundException -> 0x004e }
            com.xiaomi.zxing.BinaryBitmap r3 = r6.a(r0, r1, r0, r1)     // Catch:{ NotFoundException -> 0x004e }
            com.xiaomi.zxing.Result r2 = r2.a(r3, r7)     // Catch:{ NotFoundException -> 0x004e }
            com.xiaomi.zxing.ResultPoint[] r3 = r2.c()     // Catch:{ NotFoundException -> 0x004e }
            a(r3, r0, r1)     // Catch:{ NotFoundException -> 0x004e }
            return r2
        L_0x004e:
            int r2 = r0 / 2
            int r3 = r1 / 2
            com.xiaomi.zxing.BinaryBitmap r6 = r6.a(r2, r3, r0, r1)
            com.xiaomi.zxing.Reader r0 = r5.f1684a
            com.xiaomi.zxing.Result r6 = r0.a(r6, r7)
            com.xiaomi.zxing.ResultPoint[] r7 = r6.c()
            a(r7, r2, r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.multi.ByQuadrantReader.a(com.xiaomi.zxing.BinaryBitmap, java.util.Map):com.xiaomi.zxing.Result");
    }

    public void a() {
        this.f1684a.a();
    }

    private static void a(ResultPoint[] resultPointArr, int i, int i2) {
        if (resultPointArr != null) {
            for (int i3 = 0; i3 < resultPointArr.length; i3++) {
                ResultPoint resultPoint = resultPointArr[i3];
                resultPointArr[i3] = new ResultPoint(resultPoint.a() + ((float) i), resultPoint.b() + ((float) i2));
            }
        }
    }
}
