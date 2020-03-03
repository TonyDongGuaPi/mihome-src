package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.CharacterSetECI;
import com.xiaomi.zxing.common.DecoderResult;
import com.xiaomi.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

final class DecodedBitStreamParser {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1737a = 900;
    private static final int b = 901;
    private static final int c = 902;
    private static final int d = 924;
    private static final int e = 925;
    private static final int f = 926;
    private static final int g = 927;
    private static final int h = 928;
    private static final int i = 923;
    private static final int j = 922;
    private static final int k = 913;
    private static final int l = 15;
    private static final int m = 25;
    private static final int n = 27;
    private static final int o = 27;
    private static final int p = 28;
    private static final int q = 28;
    private static final int r = 29;
    private static final int s = 29;
    private static final char[] t = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] u = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final Charset v = Charset.forName("ISO-8859-1");
    private static final BigInteger[] w = new BigInteger[16];
    private static final int x = 2;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        w[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        w[1] = valueOf;
        for (int i2 = 2; i2 < w.length; i2++) {
            w[i2] = w[i2 - 1].multiply(valueOf);
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult a(int[] iArr, String str) throws FormatException {
        int i2;
        int i3 = 2;
        StringBuilder sb = new StringBuilder(iArr.length * 2);
        Charset charset = v;
        int i4 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (i3 < iArr[0]) {
            if (i4 != k) {
                switch (i4) {
                    case 900:
                        i2 = a(iArr, i3, sb);
                        break;
                    case 901:
                        i2 = a(i4, iArr, charset, i3, sb);
                        break;
                    case 902:
                        i2 = b(iArr, i3, sb);
                        break;
                    default:
                        switch (i4) {
                            case j /*922*/:
                            case i /*923*/:
                                throw FormatException.getFormatInstance();
                            case d /*924*/:
                                break;
                            case e /*925*/:
                                i2 = i3 + 1;
                                break;
                            case f /*926*/:
                                i2 = i3 + 2;
                                break;
                            case g /*927*/:
                                Charset forName = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i3]).name());
                                i2 = i3 + 1;
                                charset = forName;
                                break;
                            case 928:
                                i2 = a(iArr, i3, pDF417ResultMetadata);
                                break;
                            default:
                                i2 = a(iArr, i3 - 1, sb);
                                break;
                        }
                        i2 = a(i4, iArr, charset, i3, sb);
                        break;
                }
            } else {
                sb.append((char) iArr[i3]);
                i2 = i3 + 1;
            }
            if (i2 < iArr.length) {
                int i5 = i2 + 1;
                i4 = iArr[i2];
                i3 = i5;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            DecoderResult decoderResult = new DecoderResult((byte[]) null, sb.toString(), (List<byte[]>) null, str);
            decoderResult.a((Object) pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int a(int[] iArr, int i2, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i2 + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i3 = i2;
            int i4 = 0;
            while (i4 < 2) {
                iArr2[i4] = iArr[i3];
                i4++;
                i3++;
            }
            pDF417ResultMetadata.a(Integer.parseInt(a(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int a2 = a(iArr, i3, sb);
            pDF417ResultMetadata.a(sb.toString());
            if (iArr[a2] == i) {
                int i5 = a2 + 1;
                int[] iArr3 = new int[(iArr[0] - i5)];
                boolean z = false;
                int i6 = 0;
                while (i5 < iArr[0] && !z) {
                    int i7 = i5 + 1;
                    int i8 = iArr[i5];
                    if (i8 < 900) {
                        iArr3[i6] = i8;
                        i5 = i7;
                        i6++;
                    } else if (i8 == j) {
                        pDF417ResultMetadata.a(true);
                        i5 = i7 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.a(Arrays.copyOf(iArr3, i6));
                return i5;
            } else if (iArr[a2] != j) {
                return a2;
            } else {
                pDF417ResultMetadata.a(true);
                return a2 + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int a(int[] iArr, int i2, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i2) * 2)];
        int[] iArr3 = new int[((iArr[0] - i2) * 2)];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 != k) {
                if (i5 != 928) {
                    switch (i5) {
                        case 900:
                            iArr2[i3] = 900;
                            i3++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i5) {
                                case j /*922*/:
                                case i /*923*/:
                                case d /*924*/:
                                    break;
                            }
                    }
                }
                i2 = i4 - 1;
                z = true;
            } else {
                iArr2[i3] = k;
                i2 = i4 + 1;
                iArr3[i3] = iArr[i4];
                i3++;
            }
            i2 = i4;
        }
        a(iArr2, iArr3, i3, sb);
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b4, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d6, code lost:
        r3 = ' ';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00f6, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00f7, code lost:
        if (r3 == 0) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f9, code lost:
        r0.append(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00fc, code lost:
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(int[] r14, int[] r15, int r16, java.lang.StringBuilder r17) {
        /*
            r0 = r17
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r4 = r1
            r5 = r2
            r2 = 0
            r1 = r16
        L_0x000b:
            if (r2 >= r1) goto L_0x0100
            r6 = r14[r2]
            int[] r7 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.f1738a
            int r8 = r4.ordinal()
            r7 = r7[r8]
            r8 = 28
            r9 = 27
            r10 = 32
            r11 = 913(0x391, float:1.28E-42)
            r12 = 900(0x384, float:1.261E-42)
            r13 = 29
            r3 = 26
            switch(r7) {
                case 1: goto L_0x00ce;
                case 2: goto L_0x00a7;
                case 3: goto L_0x0075;
                case 4: goto L_0x0059;
                case 5: goto L_0x0044;
                case 6: goto L_0x002a;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x00f6
        L_0x002a:
            if (r6 >= r13) goto L_0x0031
            char[] r3 = t
            char r3 = r3[r6]
            goto L_0x0049
        L_0x0031:
            if (r6 != r13) goto L_0x0036
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0036:
            if (r6 != r11) goto L_0x003f
            r3 = r15[r2]
            char r3 = (char) r3
            r0.append(r3)
            goto L_0x0056
        L_0x003f:
            if (r6 != r12) goto L_0x0056
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0044:
            if (r6 >= r3) goto L_0x004c
            int r6 = r6 + 65
            char r3 = (char) r6
        L_0x0049:
            r4 = r5
            goto L_0x00f7
        L_0x004c:
            if (r6 != r3) goto L_0x0051
            r4 = r5
            goto L_0x00d6
        L_0x0051:
            if (r6 != r12) goto L_0x0056
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0056:
            r4 = r5
            goto L_0x00f6
        L_0x0059:
            if (r6 >= r13) goto L_0x0061
            char[] r3 = t
            char r3 = r3[r6]
            goto L_0x00f7
        L_0x0061:
            if (r6 != r13) goto L_0x0066
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0066:
            if (r6 != r11) goto L_0x0070
            r3 = r15[r2]
            char r3 = (char) r3
            r0.append(r3)
            goto L_0x00f6
        L_0x0070:
            if (r6 != r12) goto L_0x00f6
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0075:
            r7 = 25
            if (r6 >= r7) goto L_0x007f
            char[] r3 = u
            char r3 = r3[r6]
            goto L_0x00f7
        L_0x007f:
            if (r6 != r7) goto L_0x0086
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
        L_0x0083:
            r4 = r3
            goto L_0x00f6
        L_0x0086:
            if (r6 != r3) goto L_0x0089
            goto L_0x00d6
        L_0x0089:
            if (r6 != r9) goto L_0x008e
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x0083
        L_0x008e:
            if (r6 != r8) goto L_0x0093
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x0093:
            if (r6 != r13) goto L_0x0098
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b4
        L_0x0098:
            if (r6 != r11) goto L_0x00a2
            r3 = r15[r2]
            char r3 = (char) r3
            r0.append(r3)
            goto L_0x00f6
        L_0x00a2:
            if (r6 != r12) goto L_0x00f6
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x00a7:
            if (r6 >= r3) goto L_0x00ad
            int r6 = r6 + 97
            char r3 = (char) r6
            goto L_0x00f7
        L_0x00ad:
            if (r6 != r3) goto L_0x00b0
            goto L_0x00d6
        L_0x00b0:
            if (r6 != r9) goto L_0x00b6
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
        L_0x00b4:
            r5 = r4
            goto L_0x0083
        L_0x00b6:
            if (r6 != r8) goto L_0x00bb
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x0083
        L_0x00bb:
            if (r6 != r13) goto L_0x00c0
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b4
        L_0x00c0:
            if (r6 != r11) goto L_0x00c9
            r3 = r15[r2]
            char r3 = (char) r3
            r0.append(r3)
            goto L_0x00f6
        L_0x00c9:
            if (r6 != r12) goto L_0x00f6
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x00ce:
            if (r6 >= r3) goto L_0x00d4
            int r6 = r6 + 65
            char r3 = (char) r6
            goto L_0x00f7
        L_0x00d4:
            if (r6 != r3) goto L_0x00d9
        L_0x00d6:
            r3 = 32
            goto L_0x00f7
        L_0x00d9:
            if (r6 != r9) goto L_0x00de
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x0083
        L_0x00de:
            if (r6 != r8) goto L_0x00e3
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x0083
        L_0x00e3:
            if (r6 != r13) goto L_0x00e8
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b4
        L_0x00e8:
            if (r6 != r11) goto L_0x00f1
            r3 = r15[r2]
            char r3 = (char) r3
            r0.append(r3)
            goto L_0x00f6
        L_0x00f1:
            if (r6 != r12) goto L_0x00f6
            com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0083
        L_0x00f6:
            r3 = 0
        L_0x00f7:
            if (r3 == 0) goto L_0x00fc
            r0.append(r3)
        L_0x00fc:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x0100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.decoder.DecodedBitStreamParser.a(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int a(int i2, int[] iArr, Charset charset, int i3, StringBuilder sb) {
        int i4;
        long j2;
        int i5;
        int i6;
        int i7 = i2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i8 = j;
        int i9 = i;
        int i10 = 928;
        int i11 = 902;
        long j3 = 900;
        if (i7 == 901) {
            int[] iArr2 = new int[6];
            int i12 = iArr[i3];
            int i13 = i3 + 1;
            boolean z = false;
            loop0:
            while (true) {
                i5 = 0;
                long j4 = 0;
                while (i4 < iArr[0] && !z) {
                    int i14 = i5 + 1;
                    iArr2[i5] = i12;
                    j4 = (j4 * j2) + ((long) i12);
                    int i15 = i4 + 1;
                    i12 = iArr[i4];
                    if (i12 == 900 || i12 == 901 || i12 == 902 || i12 == d || i12 == 928 || i12 == i9 || i12 == i8) {
                        i4 = i15 - 1;
                        i5 = i14;
                        i8 = j;
                        i9 = i;
                        j2 = 900;
                        z = true;
                    } else if (i14 % 5 != 0 || i14 <= 0) {
                        i4 = i15;
                        i5 = i14;
                        i8 = j;
                        i9 = i;
                        j2 = 900;
                    } else {
                        int i16 = 0;
                        while (i16 < 6) {
                            byteArrayOutputStream.write((byte) ((int) (j4 >> ((5 - i16) * 8))));
                            i16++;
                            i8 = j;
                            i9 = i;
                        }
                        i13 = i15;
                        j3 = 900;
                    }
                }
            }
            if (i4 != iArr[0] || i12 >= 900) {
                i6 = i5;
            } else {
                i6 = i5 + 1;
                iArr2[i5] = i12;
            }
            for (int i17 = 0; i17 < i6; i17++) {
                byteArrayOutputStream.write((byte) iArr2[i17]);
            }
        } else if (i7 == d) {
            int i18 = i3;
            boolean z2 = false;
            int i19 = 0;
            long j5 = 0;
            while (i4 < iArr[0] && !z2) {
                int i20 = i4 + 1;
                int i21 = iArr[i4];
                if (i21 < 900) {
                    i19++;
                    j5 = (j5 * 900) + ((long) i21);
                    i18 = i20;
                } else {
                    if (i21 != 900 && i21 != 901 && i21 != i11 && i21 != d && i21 != i10) {
                        if (i21 != i) {
                            if (i21 != j) {
                                i18 = i20;
                            }
                            i18 = i20 - 1;
                            z2 = true;
                        }
                    }
                    i18 = i20 - 1;
                    z2 = true;
                }
                if (i19 % 5 == 0 && i19 > 0) {
                    for (int i22 = 0; i22 < 6; i22++) {
                        byteArrayOutputStream.write((byte) ((int) (j5 >> ((5 - i22) * 8))));
                    }
                    i19 = 0;
                    j5 = 0;
                }
                i10 = 928;
                i11 = 902;
            }
        } else {
            i4 = i3;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i4;
    }

    private static int b(int[] iArr, int i2, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i4 == iArr[0]) {
                z = true;
            }
            if (i5 < 900) {
                iArr2[i3] = i5;
                i3++;
            } else if (i5 == 900 || i5 == 901 || i5 == d || i5 == 928 || i5 == i || i5 == j) {
                i4--;
                z = true;
            }
            if ((i3 % 15 == 0 || i5 == 902 || z) && i3 > 0) {
                sb.append(a(iArr2, i3));
                i3 = 0;
            }
            i2 = i4;
        }
        return i2;
    }

    private static String a(int[] iArr, int i2) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i3 = 0; i3 < i2; i3++) {
            bigInteger = bigInteger.add(w[(i2 - i3) - 1].multiply(BigInteger.valueOf((long) iArr[i3])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
