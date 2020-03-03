package com.google.zxing.oned;

import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.download.Downloads;
import java.util.Arrays;
import java.util.Map;

public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    static final int ASTERISK_ENCODING = 148;
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, OlympusRawInfoMakernoteDirectory.l, 100, 265, 73, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, Downloads.STATUS_QUEUED_FOR_WIFI, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        String str;
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        int[] findAsteriskPattern = findAsteriskPattern(bitArray, iArr);
        int nextSet = bitArray.getNextSet(findAsteriskPattern[1]);
        int size = bitArray.getSize();
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            int narrowWidePattern = toNarrowWidePattern(iArr);
            if (narrowWidePattern >= 0) {
                char patternToChar = patternToChar(narrowWidePattern);
                sb.append(patternToChar);
                int i2 = nextSet;
                for (int i3 : iArr) {
                    i2 += i3;
                }
                int nextSet2 = bitArray.getNextSet(i2);
                if (patternToChar == '*') {
                    sb.setLength(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    int i6 = (nextSet2 - nextSet) - i4;
                    if (nextSet2 == size || (i6 << 1) >= i4) {
                        if (this.usingCheckDigit) {
                            int length = sb.length() - 1;
                            int i7 = 0;
                            for (int i8 = 0; i8 < length; i8++) {
                                i7 += ALPHABET_STRING.indexOf(this.decodeRowResult.charAt(i8));
                            }
                            if (sb.charAt(length) == ALPHABET_STRING.charAt(i7 % 43)) {
                                sb.setLength(length);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (sb.length() != 0) {
                            if (this.extendedMode) {
                                str = decodeExtended(sb);
                            } else {
                                str = sb.toString();
                            }
                            float f = (float) i;
                            return new Result(str, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (findAsteriskPattern[1] + findAsteriskPattern[0])) / 2.0f, f), new ResultPoint(((float) nextSet) + (((float) i4) / 2.0f), f)}, BarcodeFormat.CODE_39);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                nextSet = nextSet2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = iArr.length;
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else if (toNarrowWidePattern(iArr) != 148 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                    i += iArr[0] + iArr[1];
                    int i3 = i2 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i3);
                    iArr[i3] = 0;
                    iArr[i2] = 0;
                    i2--;
                } else {
                    return new int[]{i, nextSet};
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i5 |= 1 << ((length - 1) - i7);
                    i4++;
                    i6 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if ((i10 << 1) >= i6) {
                            return -1;
                        }
                    }
                }
                return i5;
            } else if (i4 <= 3) {
                return -1;
            } else {
                i = i2;
            }
        }
    }

    private static char patternToChar(int i) throws NotFoundException {
        for (int i2 = 0; i2 < CHARACTER_ENCODINGS.length; i2++) {
            if (CHARACTER_ENCODINGS[i2] == i) {
                return ALPHABET_STRING.charAt(i2);
            }
        }
        if (i == 148) {
            return '*';
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String decodeExtended(java.lang.CharSequence r10) throws com.google.zxing.FormatException {
        /*
            int r0 = r10.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r2 = 0
            r3 = 0
        L_0x000b:
            if (r3 >= r0) goto L_0x00c3
            char r4 = r10.charAt(r3)
            r5 = 47
            r6 = 43
            if (r4 == r6) goto L_0x0027
            r7 = 36
            if (r4 == r7) goto L_0x0027
            r7 = 37
            if (r4 == r7) goto L_0x0027
            if (r4 != r5) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            r1.append(r4)
            goto L_0x00ba
        L_0x0027:
            int r3 = r3 + 1
            char r7 = r10.charAt(r3)
            r8 = 90
            r9 = 65
            if (r4 == r6) goto L_0x00b0
            r6 = 79
            if (r4 == r5) goto L_0x009e
            switch(r4) {
                case 36: goto L_0x0091;
                case 37: goto L_0x003d;
                default: goto L_0x003a;
            }
        L_0x003a:
            r4 = 0
            goto L_0x00b7
        L_0x003d:
            if (r7 < r9) goto L_0x0048
            r4 = 69
            if (r7 > r4) goto L_0x0048
            int r7 = r7 + -38
            char r4 = (char) r7
            goto L_0x00b7
        L_0x0048:
            r4 = 70
            if (r7 < r4) goto L_0x0055
            r4 = 74
            if (r7 > r4) goto L_0x0055
            int r7 = r7 + -11
            char r4 = (char) r7
            goto L_0x00b7
        L_0x0055:
            r4 = 75
            if (r7 < r4) goto L_0x005f
            if (r7 > r6) goto L_0x005f
            int r7 = r7 + 16
            char r4 = (char) r7
            goto L_0x00b7
        L_0x005f:
            r4 = 80
            if (r7 < r4) goto L_0x006b
            r4 = 84
            if (r7 > r4) goto L_0x006b
            int r7 = r7 + 43
            char r4 = (char) r7
            goto L_0x00b7
        L_0x006b:
            r4 = 85
            if (r7 != r4) goto L_0x0070
            goto L_0x003a
        L_0x0070:
            r4 = 86
            if (r7 != r4) goto L_0x0077
            r4 = 64
            goto L_0x00b7
        L_0x0077:
            r4 = 87
            if (r7 != r4) goto L_0x007e
            r4 = 96
            goto L_0x00b7
        L_0x007e:
            r4 = 88
            if (r7 == r4) goto L_0x008e
            r4 = 89
            if (r7 == r4) goto L_0x008e
            if (r7 != r8) goto L_0x0089
            goto L_0x008e
        L_0x0089:
            com.google.zxing.FormatException r10 = com.google.zxing.FormatException.getFormatInstance()
            throw r10
        L_0x008e:
            r4 = 127(0x7f, float:1.78E-43)
            goto L_0x00b7
        L_0x0091:
            if (r7 < r9) goto L_0x0099
            if (r7 > r8) goto L_0x0099
            int r7 = r7 + -64
            char r4 = (char) r7
            goto L_0x00b7
        L_0x0099:
            com.google.zxing.FormatException r10 = com.google.zxing.FormatException.getFormatInstance()
            throw r10
        L_0x009e:
            if (r7 < r9) goto L_0x00a6
            if (r7 > r6) goto L_0x00a6
            int r7 = r7 + -32
            char r4 = (char) r7
            goto L_0x00b7
        L_0x00a6:
            if (r7 != r8) goto L_0x00ab
            r4 = 58
            goto L_0x00b7
        L_0x00ab:
            com.google.zxing.FormatException r10 = com.google.zxing.FormatException.getFormatInstance()
            throw r10
        L_0x00b0:
            if (r7 < r9) goto L_0x00be
            if (r7 > r8) goto L_0x00be
            int r7 = r7 + 32
            char r4 = (char) r7
        L_0x00b7:
            r1.append(r4)
        L_0x00ba:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x00be:
            com.google.zxing.FormatException r10 = com.google.zxing.FormatException.getFormatInstance()
            throw r10
        L_0x00c3:
            java.lang.String r10 = r1.toString()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.decodeExtended(java.lang.CharSequence):java.lang.String");
    }
}
