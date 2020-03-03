package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        for (int i = 2; i < EXP900.length; i++) {
            BigInteger[] bigIntegerArr2 = EXP900;
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charset = StandardCharsets.ISO_8859_1;
        int i2 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        int i3 = 2;
        while (i3 < iArr[0]) {
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case 900:
                        i = textCompaction(iArr, i3, sb);
                        break;
                    case 901:
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                    case 902:
                        i = numericCompaction(iArr, i3, sb);
                        break;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /*922*/:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                break;
                            case ECI_USER_DEFINED /*925*/:
                                i = i3 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /*926*/:
                                i = i3 + 2;
                                break;
                            case ECI_CHARSET /*927*/:
                                i = i3 + 1;
                                charset = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i3]).name());
                                break;
                            case 928:
                                i = decodeMacroBlock(iArr, i3, pDF417ResultMetadata);
                                break;
                            default:
                                i = textCompaction(iArr, i3 - 1, sb);
                                break;
                        }
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                }
            } else {
                i = i3 + 1;
                sb.append((char) iArr[i3]);
            }
            if (i < iArr.length) {
                i3 = i + 1;
                i2 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            DecoderResult decoderResult = new DecoderResult((byte[]) null, sb.toString(), (List<byte[]>) null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            int i4 = iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? textCompaction + 1 : -1;
            while (textCompaction < iArr[0]) {
                switch (iArr[textCompaction]) {
                    case MACRO_PDF417_TERMINATOR /*922*/:
                        textCompaction++;
                        pDF417ResultMetadata.setLastSegment(true);
                        break;
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                        int i5 = textCompaction + 1;
                        switch (iArr[i5]) {
                            case 0:
                                StringBuilder sb2 = new StringBuilder();
                                textCompaction = textCompaction(iArr, i5 + 1, sb2);
                                pDF417ResultMetadata.setFileName(sb2.toString());
                                break;
                            case 1:
                                StringBuilder sb3 = new StringBuilder();
                                textCompaction = numericCompaction(iArr, i5 + 1, sb3);
                                pDF417ResultMetadata.setSegmentCount(Integer.parseInt(sb3.toString()));
                                break;
                            case 2:
                                StringBuilder sb4 = new StringBuilder();
                                textCompaction = numericCompaction(iArr, i5 + 1, sb4);
                                pDF417ResultMetadata.setTimestamp(Long.parseLong(sb4.toString()));
                                break;
                            case 3:
                                StringBuilder sb5 = new StringBuilder();
                                textCompaction = textCompaction(iArr, i5 + 1, sb5);
                                pDF417ResultMetadata.setSender(sb5.toString());
                                break;
                            case 4:
                                StringBuilder sb6 = new StringBuilder();
                                textCompaction = textCompaction(iArr, i5 + 1, sb6);
                                pDF417ResultMetadata.setAddressee(sb6.toString());
                                break;
                            case 5:
                                StringBuilder sb7 = new StringBuilder();
                                textCompaction = numericCompaction(iArr, i5 + 1, sb7);
                                pDF417ResultMetadata.setFileSize(Long.parseLong(sb7.toString()));
                                break;
                            case 6:
                                StringBuilder sb8 = new StringBuilder();
                                textCompaction = numericCompaction(iArr, i5 + 1, sb8);
                                pDF417ResultMetadata.setChecksum(Integer.parseInt(sb8.toString()));
                                break;
                            default:
                                throw FormatException.getFormatInstance();
                        }
                    default:
                        throw FormatException.getFormatInstance();
                }
            }
            if (i4 != -1) {
                int i6 = textCompaction - i4;
                if (pDF417ResultMetadata.isLastSegment()) {
                    i6--;
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i4, i6 + i4));
            }
            return textCompaction;
        }
        throw FormatException.getFormatInstance();
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        r4 = r1;
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d4, code lost:
        r4 = 0;
        r11 = r3;
        r3 = r1;
        r1 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00df, code lost:
        r4 = ' ';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ec, code lost:
        if (r4 == 0) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ee, code lost:
        r15.append(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f1, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r12, int[] r13, int r14, java.lang.StringBuilder r15) {
        /*
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r2 = 0
            r3 = r1
            r1 = r0
            r0 = 0
        L_0x0008:
            if (r0 >= r14) goto L_0x00f5
            r4 = r12[r0]
            int[] r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode
            int r6 = r1.ordinal()
            r5 = r5[r6]
            r6 = 32
            r7 = 29
            r8 = 26
            r9 = 913(0x391, float:1.28E-42)
            r10 = 900(0x384, float:1.261E-42)
            switch(r5) {
                case 1: goto L_0x00c4;
                case 2: goto L_0x00a3;
                case 3: goto L_0x0077;
                case 4: goto L_0x0057;
                case 5: goto L_0x0042;
                case 6: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x00eb
        L_0x0023:
            if (r4 >= r7) goto L_0x002a
            char[] r1 = PUNCT_CHARS
            char r1 = r1[r4]
            goto L_0x0047
        L_0x002a:
            if (r4 == r7) goto L_0x003e
            if (r4 == r10) goto L_0x003a
            if (r4 == r9) goto L_0x0031
            goto L_0x0037
        L_0x0031:
            r1 = r13[r0]
            char r1 = (char) r1
            r15.append(r1)
        L_0x0037:
            r1 = r3
            goto L_0x00eb
        L_0x003a:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x003e:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x0042:
            if (r4 >= r8) goto L_0x004b
            int r4 = r4 + 65
            char r1 = (char) r4
        L_0x0047:
            r4 = r1
            r1 = r3
            goto L_0x00ec
        L_0x004b:
            if (r4 == r8) goto L_0x0054
            if (r4 == r10) goto L_0x0050
            goto L_0x0037
        L_0x0050:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x0054:
            r1 = r3
            goto L_0x00df
        L_0x0057:
            if (r4 >= r7) goto L_0x005f
            char[] r5 = PUNCT_CHARS
            char r4 = r5[r4]
            goto L_0x00ec
        L_0x005f:
            if (r4 == r7) goto L_0x0073
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x0067
            goto L_0x00eb
        L_0x0067:
            r4 = r13[r0]
            char r4 = (char) r4
            r15.append(r4)
            goto L_0x00eb
        L_0x006f:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x0073:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x0077:
            r5 = 25
            if (r4 >= r5) goto L_0x0081
            char[] r5 = MIXED_CHARS
            char r4 = r5[r4]
            goto L_0x00ec
        L_0x0081:
            if (r4 == r10) goto L_0x00a0
            if (r4 == r9) goto L_0x0099
            switch(r4) {
                case 25: goto L_0x0095;
                case 26: goto L_0x00df;
                case 27: goto L_0x0091;
                case 28: goto L_0x008d;
                case 29: goto L_0x008a;
                default: goto L_0x0088;
            }
        L_0x0088:
            goto L_0x00eb
        L_0x008a:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00d4
        L_0x008d:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x0091:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00eb
        L_0x0095:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            goto L_0x00eb
        L_0x0099:
            r4 = r13[r0]
            char r4 = (char) r4
            r15.append(r4)
            goto L_0x00eb
        L_0x00a0:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x00a3:
            if (r4 >= r8) goto L_0x00a9
            int r4 = r4 + 97
            char r4 = (char) r4
            goto L_0x00ec
        L_0x00a9:
            if (r4 == r10) goto L_0x00c1
            if (r4 == r9) goto L_0x00ba
            switch(r4) {
                case 26: goto L_0x00df;
                case 27: goto L_0x00b7;
                case 28: goto L_0x00b4;
                case 29: goto L_0x00b1;
                default: goto L_0x00b0;
            }
        L_0x00b0:
            goto L_0x00eb
        L_0x00b1:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00d4
        L_0x00b4:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00eb
        L_0x00b7:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
            goto L_0x00d4
        L_0x00ba:
            r4 = r13[r0]
            char r4 = (char) r4
            r15.append(r4)
            goto L_0x00eb
        L_0x00c1:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00eb
        L_0x00c4:
            if (r4 >= r8) goto L_0x00ca
            int r4 = r4 + 65
            char r4 = (char) r4
            goto L_0x00ec
        L_0x00ca:
            if (r4 == r10) goto L_0x00e9
            if (r4 == r9) goto L_0x00e2
            switch(r4) {
                case 26: goto L_0x00df;
                case 27: goto L_0x00dc;
                case 28: goto L_0x00d9;
                case 29: goto L_0x00d2;
                default: goto L_0x00d1;
            }
        L_0x00d1:
            goto L_0x00eb
        L_0x00d2:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
        L_0x00d4:
            r4 = 0
            r11 = r3
            r3 = r1
            r1 = r11
            goto L_0x00ec
        L_0x00d9:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00eb
        L_0x00dc:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00eb
        L_0x00df:
            r4 = 32
            goto L_0x00ec
        L_0x00e2:
            r4 = r13[r0]
            char r4 = (char) r4
            r15.append(r4)
            goto L_0x00eb
        L_0x00e9:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
        L_0x00eb:
            r4 = 0
        L_0x00ec:
            if (r4 == 0) goto L_0x00f1
            r15.append(r4)
        L_0x00f1:
            int r0 = r0 + 1
            goto L_0x0008
        L_0x00f5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c A[LOOP:1: B:21:0x004a->B:22:0x004c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int byteCompaction(int r18, int[] r19, java.nio.charset.Charset r20, int r21, java.lang.StringBuilder r22) {
        /*
            r0 = r18
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
            r4 = 901(0x385, float:1.263E-42)
            r5 = 928(0x3a0, float:1.3E-42)
            r6 = 900(0x384, double:4.447E-321)
            r8 = 900(0x384, float:1.261E-42)
            r9 = 6
            r12 = 0
            r13 = 1
            if (r0 == r4) goto L_0x0060
            r4 = 924(0x39c, float:1.295E-42)
            if (r0 == r4) goto L_0x001c
            r2 = r21
            goto L_0x00c0
        L_0x001c:
            r2 = r21
            r0 = 0
            r4 = 0
            r14 = 0
        L_0x0022:
            r10 = r19[r12]
            if (r2 >= r10) goto L_0x00c0
            if (r0 != 0) goto L_0x00c0
            int r10 = r2 + 1
            r2 = r19[r2]
            if (r2 >= r8) goto L_0x0036
            int r4 = r4 + 1
            long r14 = r14 * r6
            long r6 = (long) r2
            long r14 = r14 + r6
        L_0x0034:
            r2 = r10
            goto L_0x0043
        L_0x0036:
            if (r2 == r5) goto L_0x003f
            switch(r2) {
                case 900: goto L_0x003f;
                case 901: goto L_0x003f;
                case 902: goto L_0x003f;
                default: goto L_0x003b;
            }
        L_0x003b:
            switch(r2) {
                case 922: goto L_0x003f;
                case 923: goto L_0x003f;
                case 924: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x0034
        L_0x003f:
            int r10 = r10 + -1
            r2 = r10
            r0 = 1
        L_0x0043:
            int r6 = r4 % 5
            if (r6 != 0) goto L_0x005d
            if (r4 <= 0) goto L_0x005d
            r4 = 0
        L_0x004a:
            if (r4 >= r9) goto L_0x005a
            int r6 = 5 - r4
            int r6 = r6 * 8
            long r6 = r14 >> r6
            int r6 = (int) r6
            byte r6 = (byte) r6
            r3.write(r6)
            int r4 = r4 + 1
            goto L_0x004a
        L_0x005a:
            r4 = 0
            r14 = 0
        L_0x005d:
            r6 = 900(0x384, double:4.447E-321)
            goto L_0x0022
        L_0x0060:
            int[] r0 = new int[r9]
            int r4 = r21 + 1
            r2 = r19[r21]
            r7 = r2
            r2 = 0
        L_0x0068:
            r6 = 0
            r10 = 0
        L_0x006b:
            r14 = r19[r12]
            if (r4 >= r14) goto L_0x00a8
            if (r2 != 0) goto L_0x00a8
            int r14 = r6 + 1
            r0[r6] = r7
            r16 = 900(0x384, double:4.447E-321)
            long r10 = r10 * r16
            long r6 = (long) r7
            long r10 = r10 + r6
            int r6 = r4 + 1
            r7 = r19[r4]
            if (r7 == r5) goto L_0x00a3
            switch(r7) {
                case 900: goto L_0x00a3;
                case 901: goto L_0x00a3;
                case 902: goto L_0x00a3;
                default: goto L_0x0084;
            }
        L_0x0084:
            switch(r7) {
                case 922: goto L_0x00a3;
                case 923: goto L_0x00a3;
                case 924: goto L_0x00a3;
                default: goto L_0x0087;
            }
        L_0x0087:
            int r4 = r14 % 5
            if (r4 != 0) goto L_0x00a0
            if (r14 <= 0) goto L_0x00a0
            r4 = 0
        L_0x008e:
            if (r4 >= r9) goto L_0x009e
            int r14 = 5 - r4
            int r14 = r14 * 8
            long r14 = r10 >> r14
            int r14 = (int) r14
            byte r14 = (byte) r14
            r3.write(r14)
            int r4 = r4 + 1
            goto L_0x008e
        L_0x009e:
            r4 = r6
            goto L_0x0068
        L_0x00a0:
            r4 = r6
            r6 = r14
            goto L_0x006b
        L_0x00a3:
            int r4 = r6 + -1
            r6 = r14
            r2 = 1
            goto L_0x006b
        L_0x00a8:
            r1 = r19[r12]
            if (r4 != r1) goto L_0x00b3
            if (r7 >= r8) goto L_0x00b3
            int r1 = r6 + 1
            r0[r6] = r7
            goto L_0x00b4
        L_0x00b3:
            r1 = r6
        L_0x00b4:
            if (r12 >= r1) goto L_0x00bf
            r2 = r0[r12]
            byte r2 = (byte) r2
            r3.write(r2)
            int r12 = r12 + 1
            goto L_0x00b4
        L_0x00bf:
            r2 = r4
        L_0x00c0:
            java.lang.String r0 = new java.lang.String
            byte[] r1 = r3.toByteArray()
            r3 = r20
            r0.<init>(r1, r3)
            r1 = r22
            r1.append(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], java.nio.charset.Charset, int, java.lang.StringBuilder):int");
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                        case 901:
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                            }
                    }
                }
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
