package com.xiaomi.zxing.oned.rss;

import com.alipay.mobile.security.bio.api.BioError;
import com.drew.metadata.iptc.IptcDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1715a = {1, 10, 34, 70, 126};
    private static final int[] b = {4, 20, 48, 81};
    private static final int[] c = {0, 161, 961, BioError.RESULT_ALG_HIGH_RISK, 2715};
    private static final int[] d = {0, IptcDirectory.n, PhotoshopDirectory.I, 1516};
    private static final int[] e = {8, 6, 4, 3, 1};
    private static final int[] f = {2, 4, 6, 8};
    private static final int[][] g = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private final List<Pair> h = new ArrayList();
    private final List<Pair> i = new ArrayList();

    public Result a(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        a((Collection<Pair>) this.h, a(bitArray, false, i2, map));
        bitArray.e();
        a((Collection<Pair>) this.i, a(bitArray, true, i2, map));
        bitArray.e();
        for (Pair next : this.h) {
            if (next.d() > 1) {
                for (Pair next2 : this.i) {
                    if (next2.d() > 1 && b(next, next2)) {
                        return a(next, next2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void a(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            boolean z = false;
            Iterator<Pair> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair next = it.next();
                if (next.a() == pair.a()) {
                    next.e();
                    z = true;
                    break;
                }
            }
            if (!z) {
                collection.add(pair);
            }
        }
    }

    public void a() {
        this.h.clear();
        this.i.clear();
    }

    private static Result a(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((((long) pair.a()) * 4537077) + ((long) pair2.a()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int charAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        ResultPoint[] c2 = pair.c().c();
        ResultPoint[] c3 = pair2.c().c();
        return new Result(String.valueOf(sb.toString()), (byte[]) null, new ResultPoint[]{c2[0], c2[1], c3[0], c3[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean b(Pair pair, Pair pair2) {
        int b2 = (pair.b() + (pair2.b() * 16)) % 79;
        int a2 = (pair.c().a() * 9) + pair2.c().a();
        if (a2 > 72) {
            a2--;
        }
        if (a2 > 8) {
            a2--;
        }
        return b2 == a2;
    }

    private Pair a(BitArray bitArray, boolean z, int i2, Map<DecodeHintType, ?> map) {
        ResultPointCallback resultPointCallback;
        try {
            int[] a2 = a(bitArray, 0, z);
            FinderPattern a3 = a(bitArray, i2, z, a2);
            if (map == null) {
                resultPointCallback = null;
            } else {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            }
            if (resultPointCallback != null) {
                float f2 = ((float) (a2[0] + a2[1])) / 2.0f;
                if (z) {
                    f2 = ((float) (bitArray.a() - 1)) - f2;
                }
                resultPointCallback.a(new ResultPoint(f2, (float) i2));
            }
            DataCharacter a4 = a(bitArray, a3, true);
            DataCharacter a5 = a(bitArray, a3, false);
            return new Pair((a4.a() * 1597) + a5.a(), a4.b() + (a5.b() * 4), a3);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private DataCharacter a(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        boolean z2 = z;
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
            a(bitArray2, finderPattern.b()[1] + 1, c2);
            int i2 = 0;
            for (int length = c2.length - 1; i2 < length; length--) {
                int i3 = c2[i2];
                c2[i2] = c2[length];
                c2[length] = i3;
                i2++;
            }
        }
        int i4 = z2 ? 16 : 15;
        float a2 = ((float) MathUtils.a(c2)) / ((float) i4);
        int[] f2 = f();
        int[] g2 = g();
        float[] d2 = d();
        float[] e2 = e();
        for (int i5 = 0; i5 < c2.length; i5++) {
            float f3 = ((float) c2[i5]) / a2;
            int i6 = (int) (0.5f + f3);
            if (i6 < 1) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                f2[i7] = i6;
                d2[i7] = f3 - ((float) i6);
            } else {
                g2[i7] = i6;
                e2[i7] = f3 - ((float) i6);
            }
        }
        a(z2, i4);
        int i8 = 0;
        int i9 = 0;
        for (int length2 = f2.length - 1; length2 >= 0; length2--) {
            i8 = (i8 * 9) + f2[length2];
            i9 += f2[length2];
        }
        int i10 = 0;
        int i11 = 0;
        for (int length3 = g2.length - 1; length3 >= 0; length3--) {
            i10 = (i10 * 9) + g2[length3];
            i11 += g2[length3];
        }
        int i12 = i8 + (i10 * 3);
        if (z2) {
            if ((i9 & 1) != 0 || i9 > 12 || i9 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i13 = (12 - i9) / 2;
            int i14 = e[i13];
            return new DataCharacter((RSSUtils.a(f2, i14, false) * f1715a[i13]) + RSSUtils.a(g2, 9 - i14, true) + c[i13], i12);
        } else if ((i11 & 1) != 0 || i11 > 10 || i11 < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int i15 = (10 - i11) / 2;
            int i16 = f[i15];
            return new DataCharacter((RSSUtils.a(g2, 9 - i16, false) * b[i15]) + RSSUtils.a(f2, i16, true) + d[i15], i12);
        }
    }

    private int[] a(BitArray bitArray, int i2, boolean z) throws NotFoundException {
        int[] b2 = b();
        b2[0] = 0;
        b2[1] = 0;
        b2[2] = 0;
        b2[3] = 0;
        int a2 = bitArray.a();
        boolean z2 = false;
        while (i2 < a2) {
            z2 = !bitArray.a(i2);
            if (z == z2) {
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
                if (i4 != 3) {
                    i4++;
                } else if (b(b2)) {
                    return new int[]{i3, i2};
                } else {
                    i3 += b2[0] + b2[1];
                    b2[0] = b2[2];
                    b2[1] = b2[3];
                    b2[2] = 0;
                    b2[3] = 0;
                    i4--;
                }
                b2[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern a(BitArray bitArray, int i2, boolean z, int[] iArr) throws NotFoundException {
        int i3;
        int i4;
        boolean a2 = bitArray.a(iArr[0]);
        int i5 = iArr[0] - 1;
        while (i5 >= 0 && (bitArray.a(i5) ^ a2)) {
            i5--;
        }
        int i6 = i5 + 1;
        int[] b2 = b();
        System.arraycopy(b2, 0, b2, 1, b2.length - 1);
        b2[0] = iArr[0] - i6;
        int a3 = a(b2, g);
        int i7 = iArr[1];
        if (z) {
            i3 = (bitArray.a() - 1) - i7;
            i4 = (bitArray.a() - 1) - i6;
        } else {
            i3 = i7;
            i4 = i6;
        }
        return new FinderPattern(a3, new int[]{i6, iArr[1]}, i4, i3, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0056, code lost:
        if (r1 < 4) goto L_0x003d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r10, int r11) throws com.xiaomi.zxing.NotFoundException {
        /*
            r9 = this;
            int[] r0 = r9.f()
            int r0 = com.xiaomi.zxing.common.detector.MathUtils.a((int[]) r0)
            int[] r1 = r9.g()
            int r1 = com.xiaomi.zxing.common.detector.MathUtils.a((int[]) r1)
            int r2 = r0 + r1
            int r2 = r2 - r11
            r11 = r0 & 1
            r3 = 0
            r4 = 1
            if (r11 != r10) goto L_0x001b
            r11 = 1
            goto L_0x001c
        L_0x001b:
            r11 = 0
        L_0x001c:
            r5 = r1 & 1
            if (r5 != r4) goto L_0x0022
            r5 = 1
            goto L_0x0023
        L_0x0022:
            r5 = 0
        L_0x0023:
            r6 = 4
            if (r10 == 0) goto L_0x0043
            r10 = 12
            if (r0 <= r10) goto L_0x002d
            r7 = 0
            r8 = 1
            goto L_0x0033
        L_0x002d:
            if (r0 >= r6) goto L_0x0031
            r7 = 1
            goto L_0x0032
        L_0x0031:
            r7 = 0
        L_0x0032:
            r8 = 0
        L_0x0033:
            if (r1 <= r10) goto L_0x0039
            r10 = r7
            r7 = r8
        L_0x0037:
            r6 = 1
            goto L_0x0059
        L_0x0039:
            if (r1 >= r6) goto L_0x003f
            r10 = r7
            r7 = r8
        L_0x003d:
            r3 = 1
            goto L_0x0041
        L_0x003f:
            r10 = r7
            r7 = r8
        L_0x0041:
            r6 = 0
            goto L_0x0059
        L_0x0043:
            r10 = 11
            if (r0 <= r10) goto L_0x004a
            r10 = 0
            r7 = 1
            goto L_0x0051
        L_0x004a:
            r10 = 5
            if (r0 >= r10) goto L_0x004f
            r10 = 1
            goto L_0x0050
        L_0x004f:
            r10 = 0
        L_0x0050:
            r7 = 0
        L_0x0051:
            r8 = 10
            if (r1 <= r8) goto L_0x0056
            goto L_0x0037
        L_0x0056:
            if (r1 >= r6) goto L_0x0041
            goto L_0x003d
        L_0x0059:
            if (r2 != r4) goto L_0x006f
            if (r11 == 0) goto L_0x0066
            if (r5 != 0) goto L_0x0061
        L_0x005f:
            r7 = 1
            goto L_0x0099
        L_0x0061:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0066:
            if (r5 == 0) goto L_0x006a
        L_0x0068:
            r6 = 1
            goto L_0x0099
        L_0x006a:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x006f:
            r8 = -1
            if (r2 != r8) goto L_0x0086
            if (r11 == 0) goto L_0x007d
            if (r5 != 0) goto L_0x0078
            r10 = 1
            goto L_0x0099
        L_0x0078:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x007d:
            if (r5 == 0) goto L_0x0081
            r3 = 1
            goto L_0x0099
        L_0x0081:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0086:
            if (r2 != 0) goto L_0x00e3
            if (r11 == 0) goto L_0x0097
            if (r5 == 0) goto L_0x0092
            if (r0 >= r1) goto L_0x0090
            r10 = 1
            goto L_0x0068
        L_0x0090:
            r3 = 1
            goto L_0x005f
        L_0x0092:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0097:
            if (r5 != 0) goto L_0x00de
        L_0x0099:
            if (r10 == 0) goto L_0x00ae
            if (r7 != 0) goto L_0x00a9
            int[] r10 = r9.f()
            float[] r11 = r9.d()
            a((int[]) r10, (float[]) r11)
            goto L_0x00ae
        L_0x00a9:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00ae:
            if (r7 == 0) goto L_0x00bb
            int[] r10 = r9.f()
            float[] r11 = r9.d()
            b(r10, r11)
        L_0x00bb:
            if (r3 == 0) goto L_0x00d0
            if (r6 != 0) goto L_0x00cb
            int[] r10 = r9.g()
            float[] r11 = r9.d()
            a((int[]) r10, (float[]) r11)
            goto L_0x00d0
        L_0x00cb:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00d0:
            if (r6 == 0) goto L_0x00dd
            int[] r10 = r9.g()
            float[] r11 = r9.e()
            b(r10, r11)
        L_0x00dd:
            return
        L_0x00de:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00e3:
            com.xiaomi.zxing.NotFoundException r10 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.oned.rss.RSS14Reader.a(boolean, int):void");
    }
}
