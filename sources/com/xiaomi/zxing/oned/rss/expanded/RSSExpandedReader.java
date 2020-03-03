package com.xiaomi.zxing.oned.rss.expanded;

import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.oned.rss.AbstractRSSReader;
import com.xiaomi.zxing.oned.rss.DataCharacter;
import com.xiaomi.zxing.oned.rss.FinderPattern;
import com.xiaomi.zxing.oned.rss.RSSUtils;
import com.xiaomi.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public final class RSSExpandedReader extends AbstractRSSReader {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1718a = {7, 5, 4, 3, 1};
    private static final int[] b = {4, 20, 52, 104, 204};
    private static final int[] c = {0, 348, 1388, 2948, 3988};
    private static final int[][] d = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] e = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, Opcodes.cW, 136, Opcodes.dg, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, Downloads.STATUS_QUEUED_FOR_WIFI, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, Opcodes.dh, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int j = 4;
    private static final int k = 5;
    private static final int[][] l = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int m = 11;
    private final List<ExpandedPair> n = new ArrayList(11);
    private final List<ExpandedRow> o = new ArrayList();
    private final int[] p = new int[2];
    private boolean q;

    public Result a(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.n.clear();
        this.q = false;
        try {
            return a(a(i2, bitArray));
        } catch (NotFoundException unused) {
            this.n.clear();
            this.q = true;
            return a(a(i2, bitArray));
        }
    }

    public void a() {
        this.n.clear();
        this.o.clear();
    }

    /* access modifiers changed from: package-private */
    public List<ExpandedPair> a(int i2, BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                this.n.add(a(bitArray, this.n, i2));
            } catch (NotFoundException e2) {
                if (this.n.isEmpty()) {
                    throw e2;
                } else if (i()) {
                    return this.n;
                } else {
                    boolean z = !this.o.isEmpty();
                    a(i2, false);
                    if (z) {
                        List<ExpandedPair> a2 = a(false);
                        if (a2 != null) {
                            return a2;
                        }
                        List<ExpandedPair> a3 = a(true);
                        if (a3 != null) {
                            return a3;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    private List<ExpandedPair> a(boolean z) {
        List<ExpandedPair> list;
        if (this.o.size() > 25) {
            this.o.clear();
            return null;
        }
        this.n.clear();
        if (z) {
            Collections.reverse(this.o);
        }
        try {
            list = a((List<ExpandedRow>) new ArrayList(), 0);
        } catch (NotFoundException unused) {
            list = null;
        }
        if (z) {
            Collections.reverse(this.o);
        }
        return list;
    }

    private List<ExpandedPair> a(List<ExpandedRow> list, int i2) throws NotFoundException {
        while (i2 < this.o.size()) {
            ExpandedRow expandedRow = this.o.get(i2);
            this.n.clear();
            for (ExpandedRow a2 : list) {
                this.n.addAll(a2.a());
            }
            this.n.addAll(expandedRow.a());
            if (b(this.n)) {
                if (i()) {
                    return this.n;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return a((List<ExpandedRow>) arrayList, i2 + 1);
                } catch (NotFoundException unused) {
                }
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean b(List<ExpandedPair> list) {
        boolean z;
        for (int[] iArr : l) {
            if (list.size() <= iArr.length) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(i2).d().a() != iArr[i2]) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(int i2, boolean z) {
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i3 >= this.o.size()) {
                break;
            }
            ExpandedRow expandedRow = this.o.get(i3);
            if (expandedRow.b() > i2) {
                z2 = expandedRow.a(this.n);
                break;
            } else {
                z3 = expandedRow.a(this.n);
                i3++;
            }
        }
        if (!z2 && !z3 && !a((Iterable<ExpandedPair>) this.n, (Iterable<ExpandedRow>) this.o)) {
            this.o.add(i3, new ExpandedRow(this.n, i2, z));
            a(this.n, this.o);
        }
    }

    private static void a(List<ExpandedPair> list, List<ExpandedRow> list2) {
        boolean z;
        Iterator<ExpandedRow> it = list2.iterator();
        while (it.hasNext()) {
            ExpandedRow next = it.next();
            if (next.a().size() != list.size()) {
                Iterator<ExpandedPair> it2 = next.a().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    ExpandedPair next2 = it2.next();
                    Iterator<ExpandedPair> it3 = list.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (next2.equals(it3.next())) {
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static boolean a(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        boolean z;
        boolean z2;
        Iterator<ExpandedRow> it = iterable2.iterator();
        do {
            z = false;
            if (it.hasNext()) {
                ExpandedRow next = it.next();
                Iterator<ExpandedPair> it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        continue;
                        break;
                    }
                    ExpandedPair next2 = it2.next();
                    Iterator<ExpandedPair> it3 = next.a().iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (next2.equals(it3.next())) {
                                z2 = true;
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        continue;
                        break;
                    }
                }
            } else {
                return false;
            }
        } while (!z);
        return true;
    }

    /* access modifiers changed from: package-private */
    public List<ExpandedRow> h() {
        return this.o;
    }

    static Result a(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String a2 = AbstractExpandedDecoder.a(BitArrayBuilder.a(list)).a();
        ResultPoint[] c2 = list.get(0).d().c();
        ResultPoint[] c3 = list.get(list.size() - 1).d().c();
        return new Result(a2, (byte[]) null, new ResultPoint[]{c2[0], c2[1], c3[0], c3[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean i() {
        ExpandedPair expandedPair = this.n.get(0);
        DataCharacter b2 = expandedPair.b();
        DataCharacter c2 = expandedPair.c();
        if (c2 == null) {
            return false;
        }
        int b3 = c2.b();
        int i2 = 2;
        for (int i3 = 1; i3 < this.n.size(); i3++) {
            ExpandedPair expandedPair2 = this.n.get(i3);
            b3 += expandedPair2.b().b();
            i2++;
            DataCharacter c3 = expandedPair2.c();
            if (c3 != null) {
                b3 += c3.b();
                i2++;
            }
        }
        if (((i2 - 4) * 211) + (b3 % 211) == b2.a()) {
            return true;
        }
        return false;
    }

    private static int a(BitArray bitArray, int i2) {
        if (bitArray.a(i2)) {
            return bitArray.d(bitArray.e(i2));
        }
        return bitArray.e(bitArray.d(i2));
    }

    /* access modifiers changed from: package-private */
    public ExpandedPair a(BitArray bitArray, List<ExpandedPair> list, int i2) throws NotFoundException {
        FinderPattern a2;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.q) {
            z = !z;
        }
        int i3 = -1;
        boolean z2 = true;
        do {
            b(bitArray, list, i3);
            a2 = a(bitArray, i2, z);
            if (a2 == null) {
                i3 = a(bitArray, this.p[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        DataCharacter a3 = a(bitArray, a2, z, true);
        if (list.isEmpty() || !list.get(list.size() - 1).e()) {
            try {
                dataCharacter = a(bitArray, a2, z, false);
            } catch (NotFoundException unused) {
                dataCharacter = null;
            }
            return new ExpandedPair(a3, dataCharacter, a2, true);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void b(BitArray bitArray, List<ExpandedPair> list, int i2) throws NotFoundException {
        int[] b2 = b();
        b2[0] = 0;
        b2[1] = 0;
        b2[2] = 0;
        b2[3] = 0;
        int a2 = bitArray.a();
        if (i2 < 0) {
            if (list.isEmpty()) {
                i2 = 0;
            } else {
                i2 = list.get(list.size() - 1).d().b()[1];
            }
        }
        boolean z = list.size() % 2 != 0;
        if (this.q) {
            z = !z;
        }
        boolean z2 = false;
        while (i2 < a2) {
            z2 = !bitArray.a(i2);
            if (!z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < a2) {
            if (bitArray.a(i2) ^ z2) {
                b2[i4] = b2[i4] + 1;
            } else {
                if (i4 == 3) {
                    if (z) {
                        c(b2);
                    }
                    if (b(b2)) {
                        this.p[0] = i3;
                        this.p[1] = i2;
                        return;
                    }
                    if (z) {
                        c(b2);
                    }
                    i3 += b2[0] + b2[1];
                    b2[0] = b2[2];
                    b2[1] = b2[3];
                    b2[2] = 0;
                    b2[3] = 0;
                    i4--;
                } else {
                    i4++;
                }
                b2[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void c(int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = iArr[i2];
            int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    private FinderPattern a(BitArray bitArray, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        if (z) {
            int i6 = this.p[0] - 1;
            while (i6 >= 0 && !bitArray.a(i6)) {
                i6--;
            }
            i3 = i6 + 1;
            i4 = this.p[0] - i3;
            i5 = this.p[1];
        } else {
            i3 = this.p[0];
            i5 = bitArray.e(this.p[1] + 1);
            i4 = i5 - this.p[1];
        }
        int i7 = i3;
        int i8 = i5;
        int[] b2 = b();
        System.arraycopy(b2, 0, b2, 1, b2.length - 1);
        b2[0] = i4;
        try {
            return new FinderPattern(a(b2, d), new int[]{i7, i8}, i7, i8, i2);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public DataCharacter a(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        int[] c2 = c();
        c2[0] = 0;
        c2[1] = 0;
        c2[2] = 0;
        c2[3] = 0;
        c2[4] = 0;
        c2[5] = 0;
        c2[6] = 0;
        c2[7] = 0;
        if (z2) {
            b(bitArray2, finderPattern.b()[0], c2);
        } else {
            a(bitArray2, finderPattern.b()[1], c2);
            int i2 = 0;
            for (int length = c2.length - 1; i2 < length; length--) {
                int i3 = c2[i2];
                c2[i2] = c2[length];
                c2[length] = i3;
                i2++;
            }
        }
        float a2 = ((float) MathUtils.a(c2)) / ((float) 17);
        float f2 = ((float) (finderPattern.b()[1] - finderPattern.b()[0])) / 15.0f;
        if (Math.abs(a2 - f2) / f2 <= 0.3f) {
            int[] f3 = f();
            int[] g2 = g();
            float[] d2 = d();
            float[] e2 = e();
            for (int i4 = 0; i4 < c2.length; i4++) {
                float f4 = (((float) c2[i4]) * 1.0f) / a2;
                int i5 = (int) (0.5f + f4);
                if (i5 < 1) {
                    if (f4 >= 0.3f) {
                        i5 = 1;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (i5 > 8) {
                    if (f4 <= 8.7f) {
                        i5 = 8;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i6 = i4 / 2;
                if ((i4 & 1) == 0) {
                    f3[i6] = i5;
                    d2[i6] = f4 - ((float) i5);
                } else {
                    g2[i6] = i5;
                    e2[i6] = f4 - ((float) i5);
                }
            }
            a(17);
            int a3 = (((finderPattern.a() * 4) + (z ? 0 : 2)) + (z2 ^ true ? 1 : 0)) - 1;
            int i7 = 0;
            int i8 = 0;
            for (int length2 = f3.length - 1; length2 >= 0; length2--) {
                if (a(finderPattern, z, z2)) {
                    i7 += f3[length2] * e[a3][length2 * 2];
                }
                i8 += f3[length2];
            }
            int i9 = 0;
            for (int length3 = g2.length - 1; length3 >= 0; length3--) {
                if (a(finderPattern, z, z2)) {
                    i9 += g2[length3] * e[a3][(length3 * 2) + 1];
                }
            }
            int i10 = i7 + i9;
            if ((i8 & 1) != 0 || i8 > 13 || i8 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i11 = (13 - i8) / 2;
            int i12 = f1718a[i11];
            return new DataCharacter((RSSUtils.a(f3, i12, true) * b[i11]) + RSSUtils.a(g2, 9 - i12, false) + c[i11], i10);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean a(FinderPattern finderPattern, boolean z, boolean z2) {
        return finderPattern.a() != 0 || !z || !z2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r11) throws com.xiaomi.zxing.NotFoundException {
        /*
            r10 = this;
            int[] r0 = r10.f()
            int r0 = com.xiaomi.zxing.common.detector.MathUtils.a((int[]) r0)
            int[] r1 = r10.g()
            int r1 = com.xiaomi.zxing.common.detector.MathUtils.a((int[]) r1)
            int r2 = r0 + r1
            int r2 = r2 - r11
            r11 = r0 & 1
            r3 = 0
            r4 = 1
            if (r11 != r4) goto L_0x001b
            r11 = 1
            goto L_0x001c
        L_0x001b:
            r11 = 0
        L_0x001c:
            r5 = r1 & 1
            if (r5 != 0) goto L_0x0022
            r5 = 1
            goto L_0x0023
        L_0x0022:
            r5 = 0
        L_0x0023:
            r6 = 4
            r7 = 13
            if (r0 <= r7) goto L_0x002b
            r8 = 0
            r9 = 1
            goto L_0x0031
        L_0x002b:
            if (r0 >= r6) goto L_0x002f
            r8 = 1
            goto L_0x0030
        L_0x002f:
            r8 = 0
        L_0x0030:
            r9 = 0
        L_0x0031:
            if (r1 <= r7) goto L_0x0035
            r6 = 1
            goto L_0x0039
        L_0x0035:
            if (r1 >= r6) goto L_0x0038
            r3 = 1
        L_0x0038:
            r6 = 0
        L_0x0039:
            if (r2 != r4) goto L_0x004f
            if (r11 == 0) goto L_0x0046
            if (r5 != 0) goto L_0x0041
        L_0x003f:
            r9 = 1
            goto L_0x0079
        L_0x0041:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0046:
            if (r5 == 0) goto L_0x004a
            r6 = 1
            goto L_0x0079
        L_0x004a:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x004f:
            r7 = -1
            if (r2 != r7) goto L_0x0066
            if (r11 == 0) goto L_0x005d
            if (r5 != 0) goto L_0x0058
        L_0x0056:
            r8 = 1
            goto L_0x0079
        L_0x0058:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x005d:
            if (r5 == 0) goto L_0x0061
            r3 = 1
            goto L_0x0079
        L_0x0061:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0066:
            if (r2 != 0) goto L_0x00c3
            if (r11 == 0) goto L_0x0077
            if (r5 == 0) goto L_0x0072
            if (r0 >= r1) goto L_0x0070
            r6 = 1
            goto L_0x0056
        L_0x0070:
            r3 = 1
            goto L_0x003f
        L_0x0072:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0077:
            if (r5 != 0) goto L_0x00be
        L_0x0079:
            if (r8 == 0) goto L_0x008e
            if (r9 != 0) goto L_0x0089
            int[] r11 = r10.f()
            float[] r0 = r10.d()
            a((int[]) r11, (float[]) r0)
            goto L_0x008e
        L_0x0089:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x008e:
            if (r9 == 0) goto L_0x009b
            int[] r11 = r10.f()
            float[] r0 = r10.d()
            b(r11, r0)
        L_0x009b:
            if (r3 == 0) goto L_0x00b0
            if (r6 != 0) goto L_0x00ab
            int[] r11 = r10.g()
            float[] r0 = r10.d()
            a((int[]) r11, (float[]) r0)
            goto L_0x00b0
        L_0x00ab:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00b0:
            if (r6 == 0) goto L_0x00bd
            int[] r11 = r10.g()
            float[] r0 = r10.e()
            b(r11, r0)
        L_0x00bd:
            return
        L_0x00be:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00c3:
            com.xiaomi.zxing.NotFoundException r11 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.oned.rss.expanded.RSSExpandedReader.a(int):void");
    }
}
