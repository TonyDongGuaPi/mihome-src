package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 == 5) {
                    float f = MAX_AVG_VARIANCE;
                    int i3 = -1;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i4], 0.7f);
                        if (patternMatchVariance < f) {
                            i3 = i4;
                            f = patternMatchVariance;
                        }
                    }
                    if (i3 < 0 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        i += iArr[0] + iArr[1];
                        int i5 = i2 - 1;
                        System.arraycopy(iArr, 2, iArr, 0, i5);
                        iArr[i5] = 0;
                        iArr[i2] = 0;
                        i2--;
                    } else {
                        return new int[]{i, nextSet, i3};
                    }
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        for (int i3 = 0; i3 < CODE_PATTERNS.length; i3++) {
            float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i3], 0.7f);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x018d, code lost:
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x018e, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x018f, code lost:
        if (r8 == false) goto L_0x019b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0193, code lost:
        if (r3 != 'e') goto L_0x0198;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0195, code lost:
        r3 = com.google.code.microlog4android.format.PatternFormatter.DATE_CONVERSION_CHAR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0198, code lost:
        r3 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x019d, code lost:
        r8 = r5;
        r5 = r15;
        r15 = 6;
        r21 = r12;
        r12 = r9;
        r9 = r14;
        r14 = r18;
        r18 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        if (r5 != false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0100, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0103, code lost:
        r15 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012e, code lost:
        r5 = false;
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0135, code lost:
        if (r5 != false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0137, code lost:
        r5 = false;
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x013c, code lost:
        r5 = false;
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0141, code lost:
        r15 = r5;
        r3 = com.google.code.microlog4android.format.PatternFormatter.CATEGORY_CONVERSION_CHAR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0148, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x014a, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x014b, code lost:
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x014c, code lost:
        r5 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r23, com.google.zxing.common.BitArray r24, java.util.Map<com.google.zxing.DecodeHintType, ?> r25) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r22 = this;
            r0 = r24
            r1 = r25
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.google.zxing.DecodeHintType r4 = com.google.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            int[] r4 = findStartPattern(r24)
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
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
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
            int[] r2 = new int[r15]
            r16 = r6
            r9 = r8
            r3 = r12
            r5 = 0
            r6 = 0
            r8 = 0
            r11 = 0
            r12 = 0
            r17 = 0
            r18 = 0
            r19 = 1
        L_0x0054:
            if (r6 != 0) goto L_0x01aa
            int r9 = decodeCode(r0, r2, r14)
            byte r10 = (byte) r9
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            r7.add(r10)
            r10 = 106(0x6a, float:1.49E-43)
            if (r9 == r10) goto L_0x0068
            r19 = 1
        L_0x0068:
            if (r9 == r10) goto L_0x0070
            int r17 = r17 + 1
            int r18 = r17 * r9
            int r16 = r16 + r18
        L_0x0070:
            r18 = r14
            r10 = 0
        L_0x0073:
            if (r10 >= r15) goto L_0x007c
            r20 = r2[r10]
            int r18 = r18 + r20
            int r10 = r10 + 1
            goto L_0x0073
        L_0x007c:
            switch(r9) {
                case 103: goto L_0x0088;
                case 104: goto L_0x0088;
                case 105: goto L_0x0088;
                default: goto L_0x007f;
            }
        L_0x007f:
            r10 = 96
            switch(r3) {
                case 99: goto L_0x0150;
                case 100: goto L_0x00ed;
                case 101: goto L_0x008d;
                default: goto L_0x0084;
            }
        L_0x0084:
            r10 = 100
            goto L_0x018d
        L_0x0088:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x008d:
            r15 = 64
            if (r9 >= r15) goto L_0x00a4
            if (r5 != r11) goto L_0x009b
            int r5 = r9 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x0100
        L_0x009b:
            int r5 = r9 + 32
            int r5 = r5 + 128
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x0100
        L_0x00a4:
            if (r9 >= r10) goto L_0x00b6
            if (r5 != r11) goto L_0x00af
            int r5 = r9 + -64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x0100
        L_0x00af:
            int r5 = r9 + 64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x0100
        L_0x00b6:
            r10 = 106(0x6a, float:1.49E-43)
            if (r9 == r10) goto L_0x00bc
            r19 = 0
        L_0x00bc:
            if (r9 == r10) goto L_0x014a
            switch(r9) {
                case 96: goto L_0x014b;
                case 97: goto L_0x014b;
                case 98: goto L_0x00e8;
                case 99: goto L_0x0141;
                case 100: goto L_0x00e3;
                case 101: goto L_0x00d9;
                case 102: goto L_0x00c3;
                default: goto L_0x00c1;
            }
        L_0x00c1:
            goto L_0x014b
        L_0x00c3:
            if (r1 == 0) goto L_0x014b
            int r10 = r13.length()
            if (r10 != 0) goto L_0x00d2
            java.lang.String r10 = "]C1"
            r13.append(r10)
            goto L_0x014b
        L_0x00d2:
            r10 = 29
            r13.append(r10)
            goto L_0x014b
        L_0x00d9:
            if (r11 != 0) goto L_0x00de
            if (r5 == 0) goto L_0x00de
            goto L_0x012e
        L_0x00de:
            if (r11 == 0) goto L_0x013c
            if (r5 == 0) goto L_0x013c
            goto L_0x0137
        L_0x00e3:
            r15 = r5
            r3 = 100
            goto L_0x014c
        L_0x00e8:
            r15 = r5
            r3 = 100
            goto L_0x0148
        L_0x00ed:
            if (r9 >= r10) goto L_0x0106
            if (r5 != r11) goto L_0x00f8
            int r5 = r9 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x0100
        L_0x00f8:
            int r5 = r9 + 32
            int r5 = r5 + 128
            char r5 = (char) r5
            r13.append(r5)
        L_0x0100:
            r5 = 0
            r10 = 100
        L_0x0103:
            r15 = 0
            goto L_0x018f
        L_0x0106:
            r10 = 106(0x6a, float:1.49E-43)
            if (r9 == r10) goto L_0x010c
            r19 = 0
        L_0x010c:
            if (r9 == r10) goto L_0x014a
            switch(r9) {
                case 96: goto L_0x014b;
                case 97: goto L_0x014b;
                case 98: goto L_0x0145;
                case 99: goto L_0x0141;
                case 100: goto L_0x012a;
                case 101: goto L_0x0126;
                case 102: goto L_0x0112;
                default: goto L_0x0111;
            }
        L_0x0111:
            goto L_0x014b
        L_0x0112:
            if (r1 == 0) goto L_0x014b
            int r10 = r13.length()
            if (r10 != 0) goto L_0x0120
            java.lang.String r10 = "]C1"
            r13.append(r10)
            goto L_0x014b
        L_0x0120:
            r10 = 29
            r13.append(r10)
            goto L_0x014b
        L_0x0126:
            r15 = r5
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x014c
        L_0x012a:
            if (r11 != 0) goto L_0x0133
            if (r5 == 0) goto L_0x0133
        L_0x012e:
            r5 = 0
            r10 = 100
            r11 = 1
            goto L_0x0103
        L_0x0133:
            if (r11 == 0) goto L_0x013c
            if (r5 == 0) goto L_0x013c
        L_0x0137:
            r5 = 0
            r10 = 100
            r11 = 0
            goto L_0x0103
        L_0x013c:
            r5 = 0
            r10 = 100
            r15 = 1
            goto L_0x018f
        L_0x0141:
            r15 = r5
            r3 = 99
            goto L_0x014c
        L_0x0145:
            r15 = r5
            r3 = 101(0x65, float:1.42E-43)
        L_0x0148:
            r5 = 1
            goto L_0x014d
        L_0x014a:
            r6 = 1
        L_0x014b:
            r15 = r5
        L_0x014c:
            r5 = 0
        L_0x014d:
            r10 = 100
            goto L_0x018f
        L_0x0150:
            r10 = 100
            if (r9 >= r10) goto L_0x0161
            r15 = 10
            if (r9 >= r15) goto L_0x015d
            r15 = 48
            r13.append(r15)
        L_0x015d:
            r13.append(r9)
            goto L_0x018d
        L_0x0161:
            r15 = 106(0x6a, float:1.49E-43)
            if (r9 == r15) goto L_0x0167
            r19 = 0
        L_0x0167:
            if (r9 == r15) goto L_0x0189
            switch(r9) {
                case 100: goto L_0x0185;
                case 101: goto L_0x0181;
                case 102: goto L_0x016d;
                default: goto L_0x016c;
            }
        L_0x016c:
            goto L_0x018d
        L_0x016d:
            if (r1 == 0) goto L_0x018d
            int r15 = r13.length()
            if (r15 != 0) goto L_0x017b
            java.lang.String r15 = "]C1"
            r13.append(r15)
            goto L_0x018d
        L_0x017b:
            r15 = 29
            r13.append(r15)
            goto L_0x018d
        L_0x0181:
            r15 = r5
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x018e
        L_0x0185:
            r15 = r5
            r3 = 100
            goto L_0x018e
        L_0x0189:
            r15 = r5
            r5 = 0
            r6 = 1
            goto L_0x018f
        L_0x018d:
            r15 = r5
        L_0x018e:
            r5 = 0
        L_0x018f:
            if (r8 == 0) goto L_0x019b
            r8 = 101(0x65, float:1.42E-43)
            if (r3 != r8) goto L_0x0198
            r3 = 100
            goto L_0x019d
        L_0x0198:
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x019d
        L_0x019b:
            r8 = 101(0x65, float:1.42E-43)
        L_0x019d:
            r8 = r5
            r5 = r15
            r15 = 6
            r21 = r12
            r12 = r9
            r9 = r14
            r14 = r18
            r18 = r21
            goto L_0x0054
        L_0x01aa:
            int r1 = r14 - r9
            int r2 = r0.getNextUnset(r14)
            int r5 = r24.getSize()
            int r6 = r2 - r9
            r8 = 2
            int r6 = r6 / r8
            int r6 = r6 + r2
            int r5 = java.lang.Math.min(r5, r6)
            r6 = 0
            boolean r0 = r0.isRange(r2, r5, r6)
            if (r0 == 0) goto L_0x023a
            r12 = r18
            int r17 = r17 * r12
            int r16 = r16 - r17
            int r0 = r16 % 103
            if (r0 != r12) goto L_0x0235
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0230
            if (r0 <= 0) goto L_0x01e7
            if (r19 == 0) goto L_0x01e7
            r2 = 99
            if (r3 != r2) goto L_0x01e2
            int r2 = r0 + -2
            r13.delete(r2, r0)
            goto L_0x01e7
        L_0x01e2:
            int r2 = r0 + -1
            r13.delete(r2, r0)
        L_0x01e7:
            r0 = 1
            r2 = r4[r0]
            r0 = 0
            r3 = r4[r0]
            int r2 = r2 + r3
            float r0 = (float) r2
            r2 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r2
            float r3 = (float) r9
            float r1 = (float) r1
            float r1 = r1 / r2
            float r3 = r3 + r1
            int r1 = r7.size()
            byte[] r2 = new byte[r1]
            r4 = 0
        L_0x01fd:
            if (r4 >= r1) goto L_0x020e
            java.lang.Object r5 = r7.get(r4)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r2[r4] = r5
            int r4 = r4 + 1
            goto L_0x01fd
        L_0x020e:
            com.google.zxing.Result r1 = new com.google.zxing.Result
            java.lang.String r4 = r13.toString()
            r5 = 2
            com.google.zxing.ResultPoint[] r5 = new com.google.zxing.ResultPoint[r5]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r23
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r5[r0] = r6
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r3, r7)
            r3 = 1
            r5[r3] = r0
            com.google.zxing.BarcodeFormat r0 = com.google.zxing.BarcodeFormat.CODE_128
            r1.<init>(r4, r2, r5, r0)
            return r1
        L_0x0230:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x0235:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x023a:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
