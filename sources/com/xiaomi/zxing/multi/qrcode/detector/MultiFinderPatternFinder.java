package com.xiaomi.zxing.multi.qrcode.detector;

import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.detector.FinderPattern;
import com.xiaomi.zxing.qrcode.detector.FinderPatternFinder;
import com.xiaomi.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final FinderPatternInfo[] c = new FinderPatternInfo[0];
    private static final float d = 180.0f;
    private static final float e = 9.0f;
    private static final float f = 0.05f;
    private static final float g = 0.5f;

    private static final class ModuleSizeComparator implements Serializable, Comparator<FinderPattern> {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            double c = (double) (finderPattern2.c() - finderPattern.c());
            if (c < 0.0d) {
                return -1;
            }
            return c > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    private FinderPattern[][] c() throws NotFoundException {
        List<FinderPattern> b = b();
        int size = b.size();
        int i = 3;
        if (size >= 3) {
            char c2 = 0;
            if (size == 3) {
                return new FinderPattern[][]{new FinderPattern[]{b.get(0), b.get(1), b.get(2)}};
            }
            Collections.sort(b, new ModuleSizeComparator());
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < size - 2) {
                FinderPattern finderPattern = b.get(i2);
                if (finderPattern != null) {
                    int i3 = i2 + 1;
                    while (i3 < size - 1) {
                        FinderPattern finderPattern2 = b.get(i3);
                        if (finderPattern2 != null) {
                            float c3 = (finderPattern.c() - finderPattern2.c()) / Math.min(finderPattern.c(), finderPattern2.c());
                            float f2 = 0.5f;
                            float f3 = 0.05f;
                            if (Math.abs(finderPattern.c() - finderPattern2.c()) > 0.5f && c3 >= 0.05f) {
                                break;
                            }
                            int i4 = i3 + 1;
                            while (i4 < size) {
                                FinderPattern finderPattern3 = b.get(i4);
                                if (finderPattern3 != null) {
                                    float c4 = (finderPattern2.c() - finderPattern3.c()) / Math.min(finderPattern2.c(), finderPattern3.c());
                                    if (Math.abs(finderPattern2.c() - finderPattern3.c()) > f2 && c4 >= f3) {
                                        break;
                                    }
                                    FinderPattern[] finderPatternArr = new FinderPattern[i];
                                    finderPatternArr[c2] = finderPattern;
                                    finderPatternArr[1] = finderPattern2;
                                    finderPatternArr[2] = finderPattern3;
                                    ResultPoint.a(finderPatternArr);
                                    FinderPatternInfo finderPatternInfo = new FinderPatternInfo(finderPatternArr);
                                    float a2 = ResultPoint.a(finderPatternInfo.b(), finderPatternInfo.a());
                                    float a3 = ResultPoint.a(finderPatternInfo.c(), finderPatternInfo.a());
                                    float a4 = ResultPoint.a(finderPatternInfo.b(), finderPatternInfo.c());
                                    float c5 = (a2 + a4) / (finderPattern.c() * 2.0f);
                                    if (c5 <= d && c5 >= e && Math.abs((a2 - a4) / Math.min(a2, a4)) < 0.1f) {
                                        float sqrt = (float) Math.sqrt((double) ((a2 * a2) + (a4 * a4)));
                                        if (Math.abs((a3 - sqrt) / Math.min(a3, sqrt)) < 0.1f) {
                                            arrayList.add(finderPatternArr);
                                        }
                                    }
                                }
                                i4++;
                                i = 3;
                                c2 = 0;
                                f2 = 0.5f;
                                f3 = 0.05f;
                            }
                        }
                        i3++;
                        i = 3;
                        c2 = 0;
                    }
                }
                i2++;
                i = 3;
                c2 = 0;
            }
            if (!arrayList.isEmpty()) {
                return (FinderPattern[][]) arrayList.toArray(new FinderPattern[arrayList.size()][]);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public FinderPatternInfo[] a(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        BitMatrix a2 = a();
        int g2 = a2.g();
        int f2 = a2.f();
        int i = (int) ((((float) g2) / 228.0f) * 3.0f);
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        for (int i2 = i - 1; i2 < g2; i2 += i) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < f2; i4++) {
                if (a2.a(i4, i2)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (!a(iArr) || !a(iArr, i2, i4, z2)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                } else {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i3 = 0;
                }
            }
            if (a(iArr)) {
                a(iArr, i2, f2, z2);
            }
        }
        FinderPattern[][] c2 = c();
        ArrayList arrayList = new ArrayList();
        for (FinderPattern[] finderPatternArr : c2) {
            ResultPoint.a(finderPatternArr);
            arrayList.add(new FinderPatternInfo(finderPatternArr));
        }
        if (arrayList.isEmpty()) {
            return c;
        }
        return (FinderPatternInfo[]) arrayList.toArray(new FinderPatternInfo[arrayList.size()]);
    }
}
