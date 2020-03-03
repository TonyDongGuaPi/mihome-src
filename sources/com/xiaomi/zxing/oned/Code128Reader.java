package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    static final int[][] f1690a = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final float b = 0.25f;
    private static final float c = 0.7f;
    private static final int d = 98;
    private static final int e = 99;
    private static final int f = 100;
    private static final int g = 101;
    private static final int h = 102;
    private static final int i = 97;
    private static final int j = 96;
    private static final int k = 101;
    private static final int l = 100;
    private static final int m = 103;
    private static final int n = 104;
    private static final int o = 105;
    private static final int p = 106;

    private static int[] a(BitArray bitArray) throws NotFoundException {
        int a2 = bitArray.a();
        int d2 = bitArray.d(0);
        int[] iArr = new int[6];
        int length = iArr.length;
        int i2 = d2;
        boolean z = false;
        int i3 = 0;
        while (d2 < a2) {
            if (bitArray.a(d2) ^ z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 == i4) {
                    float f2 = b;
                    int i5 = -1;
                    for (int i6 = 103; i6 <= 105; i6++) {
                        float a3 = a(iArr, f1690a[i6], 0.7f);
                        if (a3 < f2) {
                            i5 = i6;
                            f2 = a3;
                        }
                    }
                    if (i5 < 0 || !bitArray.a(Math.max(0, i2 - ((d2 - i2) / 2)), i2, false)) {
                        i2 += iArr[0] + iArr[1];
                        int i7 = length - 2;
                        System.arraycopy(iArr, 2, iArr, 0, i7);
                        iArr[i7] = 0;
                        iArr[i4] = 0;
                        i3--;
                    } else {
                        return new int[]{i2, d2, i5};
                    }
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z = !z;
            }
            d2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(BitArray bitArray, int[] iArr, int i2) throws NotFoundException {
        a(bitArray, i2, iArr);
        float f2 = b;
        int i3 = -1;
        for (int i4 = 0; i4 < f1690a.length; i4++) {
            float a2 = a(iArr, f1690a[i4], 0.7f);
            if (a2 < f2) {
                i3 = i4;
                f2 = a2;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x018a, code lost:
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x018b, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x018c, code lost:
        if (r8 == false) goto L_0x0198;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0190, code lost:
        if (r3 != 'e') goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0192, code lost:
        r3 = com.google.code.microlog4android.format.PatternFormatter.DATE_CONVERSION_CHAR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0195, code lost:
        r3 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x019a, code lost:
        r8 = r5;
        r5 = r9;
        r9 = r12;
        r12 = r2;
        r2 = r14;
        r14 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        if (r5 != false) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e6, code lost:
        r10 = com.google.code.microlog4android.format.PatternFormatter.DATE_CONVERSION_CHAR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fd, code lost:
        r5 = false;
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0129, code lost:
        r10 = r3;
        r3 = false;
        r5 = false;
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0130, code lost:
        if (r5 != false) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0132, code lost:
        r10 = r3;
        r3 = false;
        r5 = false;
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0137, code lost:
        r10 = r3;
        r3 = false;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x013b, code lost:
        r3 = false;
        r10 = com.google.code.microlog4android.format.PatternFormatter.CATEGORY_CONVERSION_CHAR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0140, code lost:
        r10 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0143, code lost:
        r10 = r3;
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0146, code lost:
        r10 = r3;
        r3 = false;
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0149, code lost:
        r9 = r5;
        r5 = r3;
        r3 = r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.Result a(int r22, com.xiaomi.zxing.common.BitArray r23, java.util.Map<com.xiaomi.zxing.DecodeHintType, ?> r24) throws com.xiaomi.zxing.NotFoundException, com.xiaomi.zxing.FormatException, com.xiaomi.zxing.ChecksumException {
        /*
            r21 = this;
            r0 = r23
            r1 = r24
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.xiaomi.zxing.DecodeHintType r4 = com.xiaomi.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            int[] r4 = a(r23)
            r5 = 2
            r6 = r4[r5]
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 20
            r7.<init>(r8)
            byte r9 = (byte) r6
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r7.add(r9)
            switch(r6) {
                case 103: goto L_0x0037;
                case 104: goto L_0x0034;
                case 105: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0031:
            r12 = 99
            goto L_0x0039
        L_0x0034:
            r12 = 100
            goto L_0x0039
        L_0x0037:
            r12 = 101(0x65, float:1.42E-43)
        L_0x0039:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r8)
            r8 = r4[r3]
            r14 = r4[r2]
            r15 = 6
            int[] r15 = new int[r15]
            r16 = r6
            r2 = r8
            r3 = r12
            r5 = 0
            r6 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r17 = 0
            r18 = 1
        L_0x0053:
            if (r6 != 0) goto L_0x01a3
            int r2 = a((com.xiaomi.zxing.common.BitArray) r0, (int[]) r15, (int) r14)
            byte r9 = (byte) r2
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r7.add(r9)
            r9 = 106(0x6a, float:1.49E-43)
            if (r2 == r9) goto L_0x0067
            r18 = 1
        L_0x0067:
            if (r2 == r9) goto L_0x006f
            int r17 = r17 + 1
            int r19 = r17 * r2
            int r16 = r16 + r19
        L_0x006f:
            int r10 = r15.length
            r19 = r14
            r9 = 0
        L_0x0073:
            if (r9 >= r10) goto L_0x007c
            r20 = r15[r9]
            int r19 = r19 + r20
            int r9 = r9 + 1
            goto L_0x0073
        L_0x007c:
            switch(r2) {
                case 103: goto L_0x0088;
                case 104: goto L_0x0088;
                case 105: goto L_0x0088;
                default: goto L_0x007f;
            }
        L_0x007f:
            r9 = 96
            switch(r3) {
                case 99: goto L_0x014d;
                case 100: goto L_0x00ea;
                case 101: goto L_0x008d;
                default: goto L_0x0084;
            }
        L_0x0084:
            r10 = 100
            goto L_0x018a
        L_0x0088:
            com.xiaomi.zxing.FormatException r0 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x008d:
            r10 = 64
            if (r2 >= r10) goto L_0x00a4
            if (r5 != r11) goto L_0x009b
            int r5 = r2 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fd
        L_0x009b:
            int r5 = r2 + 32
            int r5 = r5 + 128
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fd
        L_0x00a4:
            if (r2 >= r9) goto L_0x00b6
            if (r5 != r11) goto L_0x00af
            int r5 = r2 + -64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fd
        L_0x00af:
            int r5 = r2 + 64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fd
        L_0x00b6:
            r9 = 106(0x6a, float:1.49E-43)
            if (r2 == r9) goto L_0x00bc
            r18 = 0
        L_0x00bc:
            if (r2 == r9) goto L_0x0146
            switch(r2) {
                case 96: goto L_0x0143;
                case 97: goto L_0x0143;
                case 98: goto L_0x00e5;
                case 99: goto L_0x013b;
                case 100: goto L_0x00e3;
                case 101: goto L_0x00d9;
                case 102: goto L_0x00c3;
                default: goto L_0x00c1;
            }
        L_0x00c1:
            goto L_0x0143
        L_0x00c3:
            if (r1 == 0) goto L_0x0143
            int r9 = r13.length()
            if (r9 != 0) goto L_0x00d2
            java.lang.String r9 = "]C1"
            r13.append(r9)
            goto L_0x0143
        L_0x00d2:
            r9 = 29
            r13.append(r9)
            goto L_0x0143
        L_0x00d9:
            if (r11 != 0) goto L_0x00de
            if (r5 == 0) goto L_0x00de
            goto L_0x0129
        L_0x00de:
            if (r11 == 0) goto L_0x0137
            if (r5 == 0) goto L_0x0137
            goto L_0x0132
        L_0x00e3:
            r3 = 0
            goto L_0x00e6
        L_0x00e5:
            r3 = 1
        L_0x00e6:
            r10 = 100
            goto L_0x0149
        L_0x00ea:
            if (r2 >= r9) goto L_0x0103
            if (r5 != r11) goto L_0x00f5
            int r5 = r2 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fd
        L_0x00f5:
            int r5 = r2 + 32
            int r5 = r5 + 128
            char r5 = (char) r5
            r13.append(r5)
        L_0x00fd:
            r5 = 0
            r9 = 0
        L_0x00ff:
            r10 = 100
            goto L_0x018c
        L_0x0103:
            r9 = 106(0x6a, float:1.49E-43)
            if (r2 == r9) goto L_0x0109
            r18 = 0
        L_0x0109:
            if (r2 == r9) goto L_0x0146
            switch(r2) {
                case 96: goto L_0x0143;
                case 97: goto L_0x0143;
                case 98: goto L_0x013f;
                case 99: goto L_0x013b;
                case 100: goto L_0x0125;
                case 101: goto L_0x0123;
                case 102: goto L_0x010f;
                default: goto L_0x010e;
            }
        L_0x010e:
            goto L_0x0143
        L_0x010f:
            if (r1 == 0) goto L_0x0143
            int r9 = r13.length()
            if (r9 != 0) goto L_0x011d
            java.lang.String r9 = "]C1"
            r13.append(r9)
            goto L_0x0143
        L_0x011d:
            r9 = 29
            r13.append(r9)
            goto L_0x0143
        L_0x0123:
            r3 = 0
            goto L_0x0140
        L_0x0125:
            if (r11 != 0) goto L_0x012e
            if (r5 == 0) goto L_0x012e
        L_0x0129:
            r10 = r3
            r3 = 0
            r5 = 0
            r11 = 1
            goto L_0x0149
        L_0x012e:
            if (r11 == 0) goto L_0x0137
            if (r5 == 0) goto L_0x0137
        L_0x0132:
            r10 = r3
            r3 = 0
            r5 = 0
            r11 = 0
            goto L_0x0149
        L_0x0137:
            r10 = r3
            r3 = 0
            r5 = 1
            goto L_0x0149
        L_0x013b:
            r3 = 0
            r10 = 99
            goto L_0x0149
        L_0x013f:
            r3 = 1
        L_0x0140:
            r10 = 101(0x65, float:1.42E-43)
            goto L_0x0149
        L_0x0143:
            r10 = r3
            r3 = 0
            goto L_0x0149
        L_0x0146:
            r10 = r3
            r3 = 0
            r6 = 1
        L_0x0149:
            r9 = r5
            r5 = r3
            r3 = r10
            goto L_0x00ff
        L_0x014d:
            r10 = 100
            if (r2 >= r10) goto L_0x015e
            r9 = 10
            if (r2 >= r9) goto L_0x015a
            r9 = 48
            r13.append(r9)
        L_0x015a:
            r13.append(r2)
            goto L_0x018a
        L_0x015e:
            r9 = 106(0x6a, float:1.49E-43)
            if (r2 == r9) goto L_0x0164
            r18 = 0
        L_0x0164:
            if (r2 == r9) goto L_0x0186
            switch(r2) {
                case 100: goto L_0x0182;
                case 101: goto L_0x017e;
                case 102: goto L_0x016a;
                default: goto L_0x0169;
            }
        L_0x0169:
            goto L_0x018a
        L_0x016a:
            if (r1 == 0) goto L_0x018a
            int r9 = r13.length()
            if (r9 != 0) goto L_0x0178
            java.lang.String r9 = "]C1"
            r13.append(r9)
            goto L_0x018a
        L_0x0178:
            r9 = 29
            r13.append(r9)
            goto L_0x018a
        L_0x017e:
            r9 = r5
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x018b
        L_0x0182:
            r9 = r5
            r3 = 100
            goto L_0x018b
        L_0x0186:
            r9 = r5
            r5 = 0
            r6 = 1
            goto L_0x018c
        L_0x018a:
            r9 = r5
        L_0x018b:
            r5 = 0
        L_0x018c:
            if (r8 == 0) goto L_0x0198
            r8 = 101(0x65, float:1.42E-43)
            if (r3 != r8) goto L_0x0195
            r3 = 100
            goto L_0x019a
        L_0x0195:
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x019a
        L_0x0198:
            r8 = 101(0x65, float:1.42E-43)
        L_0x019a:
            r8 = r5
            r5 = r9
            r9 = r12
            r12 = r2
            r2 = r14
            r14 = r19
            goto L_0x0053
        L_0x01a3:
            int r1 = r14 - r2
            int r5 = r0.e(r14)
            int r6 = r23.a()
            int r8 = r5 - r2
            r10 = 2
            int r8 = r8 / r10
            int r8 = r8 + r5
            int r6 = java.lang.Math.min(r6, r8)
            r8 = 0
            boolean r0 = r0.a(r5, r6, r8)
            if (r0 == 0) goto L_0x0231
            int r17 = r17 * r9
            int r16 = r16 - r17
            int r0 = r16 % 103
            if (r0 != r9) goto L_0x022c
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0227
            if (r0 <= 0) goto L_0x01de
            if (r18 == 0) goto L_0x01de
            r5 = 99
            if (r3 != r5) goto L_0x01d9
            int r3 = r0 + -2
            r13.delete(r3, r0)
            goto L_0x01de
        L_0x01d9:
            int r3 = r0 + -1
            r13.delete(r3, r0)
        L_0x01de:
            r0 = 1
            r3 = r4[r0]
            r0 = 0
            r4 = r4[r0]
            int r3 = r3 + r4
            float r0 = (float) r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r3
            float r2 = (float) r2
            float r1 = (float) r1
            float r1 = r1 / r3
            float r2 = r2 + r1
            int r1 = r7.size()
            byte[] r3 = new byte[r1]
            r4 = 0
        L_0x01f4:
            if (r4 >= r1) goto L_0x0205
            java.lang.Object r5 = r7.get(r4)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x01f4
        L_0x0205:
            com.xiaomi.zxing.Result r1 = new com.xiaomi.zxing.Result
            java.lang.String r4 = r13.toString()
            r5 = 2
            com.xiaomi.zxing.ResultPoint[] r5 = new com.xiaomi.zxing.ResultPoint[r5]
            com.xiaomi.zxing.ResultPoint r6 = new com.xiaomi.zxing.ResultPoint
            r7 = r22
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r5[r0] = r6
            com.xiaomi.zxing.ResultPoint r0 = new com.xiaomi.zxing.ResultPoint
            r0.<init>(r2, r7)
            r2 = 1
            r5[r2] = r0
            com.xiaomi.zxing.BarcodeFormat r0 = com.xiaomi.zxing.BarcodeFormat.CODE_128
            r1.<init>(r4, r3, r5, r0)
            return r1
        L_0x0227:
            com.xiaomi.zxing.NotFoundException r0 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x022c:
            com.xiaomi.zxing.ChecksumException r0 = com.xiaomi.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x0231:
            com.xiaomi.zxing.NotFoundException r0 = com.xiaomi.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.oned.Code128Reader.a(int, com.xiaomi.zxing.common.BitArray, java.util.Map):com.xiaomi.zxing.Result");
    }
}
