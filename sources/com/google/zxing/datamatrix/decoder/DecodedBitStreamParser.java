package com.google.zxing.datamatrix.decoder;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.code.microlog4android.format.PatternFormatter;
import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import kotlin.text.Typography;
import org.apache.commons.lang.CharUtils;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', '\"', '#', '$', '%', Typography.c, Operators.SINGLE_QUOTE, Operators.BRACKET_START, Operators.BRACKET_END, '*', '+', ',', '-', '.', IOUtils.f15883a, Operators.CONDITION_IF_MIDDLE, ';', Typography.d, '=', Typography.e, Operators.CONDITION_IF, TemplateDom.SEPARATOR, Operators.ARRAY_START, IOUtils.b, Operators.ARRAY_END, '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z', Operators.BLOCK_START, '|', Operators.BLOCK_END, '~', Ascii.MAX};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(byte[] r6) throws com.google.zxing.FormatException {
        /*
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r0.<init>(r6)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 0
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 1
            r3.<init>(r4)
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x001a:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            if (r4 != r5) goto L_0x0023
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = decodeAsciiSegment(r0, r1, r2)
            goto L_0x0048
        L_0x0023:
            int[] r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode
            int r4 = r4.ordinal()
            r4 = r5[r4]
            switch(r4) {
                case 1: goto L_0x0043;
                case 2: goto L_0x003f;
                case 3: goto L_0x003b;
                case 4: goto L_0x0037;
                case 5: goto L_0x0033;
                default: goto L_0x002e;
            }
        L_0x002e:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L_0x0033:
            decodeBase256Segment(r0, r1, r3)
            goto L_0x0046
        L_0x0037:
            decodeEdifactSegment(r0, r1)
            goto L_0x0046
        L_0x003b:
            decodeAnsiX12Segment(r0, r1)
            goto L_0x0046
        L_0x003f:
            decodeTextSegment(r0, r1)
            goto L_0x0046
        L_0x0043:
            decodeC40Segment(r0, r1)
        L_0x0046:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x0048:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE
            if (r4 == r5) goto L_0x0052
            int r5 = r0.available()
            if (r5 > 0) goto L_0x001a
        L_0x0052:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x005b
            r1.append(r2)
        L_0x005b:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x0069
            r3 = r4
        L_0x0069:
            r0.<init>(r6, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decode(byte[]):com.google.zxing.common.DecoderResult");
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= 128) {
                if (z) {
                    readBits += 128;
                }
                sb.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            } else if (readBits == 129) {
                return Mode.PAD_ENCODE;
            } else {
                if (readBits <= 229) {
                    int i = readBits - 130;
                    if (i < 10) {
                        sb.append('0');
                    }
                    sb.append(i);
                } else {
                    switch (readBits) {
                        case TbsListener.ErrorCode.RENAME_SUCCESS /*230*/:
                            return Mode.C40_ENCODE;
                        case TbsListener.ErrorCode.RENAME_FAIL /*231*/:
                            return Mode.BASE256_ENCODE;
                        case 232:
                            sb.append(29);
                            break;
                        case 233:
                        case 234:
                        case 241:
                            break;
                        case 235:
                            z = true;
                            break;
                        case 236:
                            sb.append("[)>\u001e05\u001d");
                            sb2.insert(0, "\u001e\u0004");
                            break;
                        case 237:
                            sb.append("[)>\u001e06\u001d");
                            sb2.insert(0, "\u001e\u0004");
                            break;
                        case 238:
                            return Mode.ANSIX12_ENCODE;
                        case 239:
                            return Mode.TEXT_ENCODE;
                        case PsExtractor.VIDEO_STREAM_MASK:
                            return Mode.EDIFACT_ENCODE;
                        default:
                            if (!(readBits == 254 && bitSource.available() == 0)) {
                                throw FormatException.getFormatInstance();
                            }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeC40Segment(com.google.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.google.zxing.FormatException {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r8.available()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r8.readBits(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r8.readBits(r6)
            parseTwoBytes(r5, r6, r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x009e
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x007b;
                case 1: goto L_0x006c;
                case 2: goto L_0x003e;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x002d:
            if (r5 == 0) goto L_0x0037
            int r6 = r6 + 224
            char r4 = (char) r6
            r9.append(r4)
        L_0x0035:
            r5 = 0
            goto L_0x0079
        L_0x0037:
            int r6 = r6 + 96
            char r4 = (char) r6
            r9.append(r4)
            goto L_0x0079
        L_0x003e:
            char[] r4 = C40_SHIFT2_SET_CHARS
            int r4 = r4.length
            if (r6 >= r4) goto L_0x0055
            char[] r4 = C40_SHIFT2_SET_CHARS
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x0051
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
            r4 = 0
            goto L_0x006a
        L_0x0051:
            r9.append(r4)
            goto L_0x0069
        L_0x0055:
            r4 = 27
            if (r6 == r4) goto L_0x0064
            r4 = 30
            if (r6 != r4) goto L_0x005f
            r4 = 1
            goto L_0x006a
        L_0x005f:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0064:
            r4 = 29
            r9.append(r4)
        L_0x0069:
            r4 = r5
        L_0x006a:
            r5 = r4
            goto L_0x0079
        L_0x006c:
            if (r5 == 0) goto L_0x0075
            int r6 = r6 + 128
            char r4 = (char) r6
            r9.append(r4)
            goto L_0x0035
        L_0x0075:
            char r4 = (char) r6
            r9.append(r4)
        L_0x0079:
            r4 = 0
            goto L_0x0096
        L_0x007b:
            if (r6 >= r0) goto L_0x0080
            int r4 = r6 + 1
            goto L_0x0096
        L_0x0080:
            char[] r7 = C40_BASIC_SET_CHARS
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0099
            char[] r7 = C40_BASIC_SET_CHARS
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x0093
            int r6 = r6 + 128
            char r5 = (char) r6
            r9.append(r5)
            r5 = 0
            goto L_0x0096
        L_0x0093:
            r9.append(r6)
        L_0x0096:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x0099:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x009e:
            int r3 = r8.available()
            if (r3 > 0) goto L_0x00a5
            return
        L_0x00a5:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeC40Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextSegment(com.google.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.google.zxing.FormatException {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r8.available()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r8.readBits(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r8.readBits(r6)
            parseTwoBytes(r5, r6, r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x00aa
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x0086;
                case 1: goto L_0x0077;
                case 2: goto L_0x0049;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x002d:
            char[] r4 = TEXT_SHIFT3_SET_CHARS
            int r4 = r4.length
            if (r6 >= r4) goto L_0x0044
            char[] r4 = TEXT_SHIFT3_SET_CHARS
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x0040
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
        L_0x003e:
            r5 = 0
            goto L_0x0084
        L_0x0040:
            r9.append(r4)
            goto L_0x0084
        L_0x0044:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0049:
            char[] r4 = TEXT_SHIFT2_SET_CHARS
            int r4 = r4.length
            if (r6 >= r4) goto L_0x0060
            char[] r4 = TEXT_SHIFT2_SET_CHARS
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x005c
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
            r4 = 0
            goto L_0x0075
        L_0x005c:
            r9.append(r4)
            goto L_0x0074
        L_0x0060:
            r4 = 27
            if (r6 == r4) goto L_0x006f
            r4 = 30
            if (r6 != r4) goto L_0x006a
            r4 = 1
            goto L_0x0075
        L_0x006a:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x006f:
            r4 = 29
            r9.append(r4)
        L_0x0074:
            r4 = r5
        L_0x0075:
            r5 = r4
            goto L_0x0084
        L_0x0077:
            if (r5 == 0) goto L_0x0080
            int r6 = r6 + 128
            char r4 = (char) r6
            r9.append(r4)
            goto L_0x003e
        L_0x0080:
            char r4 = (char) r6
            r9.append(r4)
        L_0x0084:
            r4 = 0
            goto L_0x00a1
        L_0x0086:
            if (r6 >= r0) goto L_0x008b
            int r4 = r6 + 1
            goto L_0x00a1
        L_0x008b:
            char[] r7 = TEXT_BASIC_SET_CHARS
            int r7 = r7.length
            if (r6 >= r7) goto L_0x00a5
            char[] r7 = TEXT_BASIC_SET_CHARS
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x009e
            int r6 = r6 + 128
            char r5 = (char) r6
            r9.append(r5)
            r5 = 0
            goto L_0x00a1
        L_0x009e:
            r9.append(r6)
        L_0x00a1:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x00a5:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x00aa:
            int r3 = r8.available()
            if (r3 > 0) goto L_0x00b1
            return
        L_0x00b1:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeTextSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                switch (i2) {
                    case 0:
                        sb.append(CharUtils.b);
                        break;
                    case 1:
                        sb.append('*');
                        break;
                    case 2:
                        sb.append(Typography.e);
                        break;
                    case 3:
                        sb.append(' ');
                        break;
                    default:
                        if (i2 < 14) {
                            sb.append((char) (i2 + 44));
                            break;
                        } else if (i2 < 40) {
                            sb.append((char) (i2 + 51));
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder sb) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                sb.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset() + 1;
        int i = byteOffset + 1;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * 250) + unrandomize255State(bitSource.readBits(8), i);
            i++;
        }
        if (unrandomize255State >= 0) {
            byte[] bArr = new byte[unrandomize255State];
            int i2 = 0;
            while (i2 < unrandomize255State) {
                if (bitSource.available() >= 8) {
                    bArr[i2] = (byte) unrandomize255State(bitSource.readBits(8), i);
                    i2++;
                    i++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("Platform does not support required encoding: ".concat(String.valueOf(e)));
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
