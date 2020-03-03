package com.xiaomi.zxing.datamatrix.decoder;

import com.google.code.microlog4android.format.PatternFormatter;
import com.google.common.base.Ascii;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitSource;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import kotlin.text.Typography;
import org.apache.commons.lang.CharUtils;

final class DecodedBitStreamParser {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f1664a = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] b = {'!', '\"', '#', '$', '%', Typography.c, Operators.SINGLE_QUOTE, Operators.BRACKET_START, Operators.BRACKET_END, '*', '+', ',', '-', '.', IOUtils.f15883a, Operators.CONDITION_IF_MIDDLE, ';', Typography.d, '=', Typography.e, Operators.CONDITION_IF, TemplateDom.SEPARATOR, Operators.ARRAY_START, IOUtils.b, Operators.ARRAY_END, '^', '_'};
    private static final char[] c = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] d = b;
    private static final char[] e = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z', Operators.BLOCK_START, '|', Operators.BLOCK_END, '~', Ascii.MAX};

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
    static com.xiaomi.zxing.common.DecoderResult a(byte[] r6) throws com.xiaomi.zxing.FormatException {
        /*
            com.xiaomi.zxing.common.BitSource r0 = new com.xiaomi.zxing.common.BitSource
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
            com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x001a:
            com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            if (r4 != r5) goto L_0x0023
            com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = a((com.xiaomi.zxing.common.BitSource) r0, (java.lang.StringBuilder) r1, (java.lang.StringBuilder) r2)
            goto L_0x0048
        L_0x0023:
            int[] r5 = com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.f1665a
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
            com.xiaomi.zxing.FormatException r6 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r6
        L_0x0033:
            a((com.xiaomi.zxing.common.BitSource) r0, (java.lang.StringBuilder) r1, (java.util.Collection<byte[]>) r3)
            goto L_0x0046
        L_0x0037:
            d(r0, r1)
            goto L_0x0046
        L_0x003b:
            c(r0, r1)
            goto L_0x0046
        L_0x003f:
            b(r0, r1)
            goto L_0x0046
        L_0x0043:
            a((com.xiaomi.zxing.common.BitSource) r0, (java.lang.StringBuilder) r1)
        L_0x0046:
            com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x0048:
            com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE
            if (r4 == r5) goto L_0x0052
            int r5 = r0.c()
            if (r5 > 0) goto L_0x001a
        L_0x0052:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x005b
            r1.append(r2)
        L_0x005b:
            com.xiaomi.zxing.common.DecoderResult r0 = new com.xiaomi.zxing.common.DecoderResult
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x0069
            r3 = r4
        L_0x0069:
            r0.<init>(r6, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.a(byte[]):com.xiaomi.zxing.common.DecoderResult");
    }

    private static Mode a(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int a2 = bitSource.a(8);
            if (a2 == 0) {
                throw FormatException.getFormatInstance();
            } else if (a2 <= 128) {
                if (z) {
                    a2 += 128;
                }
                sb.append((char) (a2 - 1));
                return Mode.ASCII_ENCODE;
            } else if (a2 == 129) {
                return Mode.PAD_ENCODE;
            } else {
                if (a2 <= 229) {
                    int i = a2 - 130;
                    if (i < 10) {
                        sb.append('0');
                    }
                    sb.append(i);
                } else if (a2 == 230) {
                    return Mode.C40_ENCODE;
                } else {
                    if (a2 == 231) {
                        return Mode.BASE256_ENCODE;
                    }
                    if (a2 == 232) {
                        sb.append(29);
                    } else if (!(a2 == 233 || a2 == 234)) {
                        if (a2 == 235) {
                            z = true;
                        } else if (a2 == 236) {
                            sb.append("[)>\u001e05\u001d");
                            sb2.insert(0, "\u001e\u0004");
                        } else if (a2 == 237) {
                            sb.append("[)>\u001e06\u001d");
                            sb2.insert(0, "\u001e\u0004");
                        } else if (a2 == 238) {
                            return Mode.ANSIX12_ENCODE;
                        } else {
                            if (a2 == 239) {
                                return Mode.TEXT_ENCODE;
                            }
                            if (a2 == 240) {
                                return Mode.EDIFACT_ENCODE;
                            }
                            if (!(a2 == 241 || a2 < 242 || (a2 == 254 && bitSource.c() == 0))) {
                                throw FormatException.getFormatInstance();
                            }
                        }
                    }
                }
            }
        } while (bitSource.c() > 0);
        return Mode.ASCII_ENCODE;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0071, code lost:
        r5 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(com.xiaomi.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.xiaomi.zxing.FormatException {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r8.c()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r8.a(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r8.a(r6)
            a((int) r5, (int) r6, (int[]) r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x009c
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0069;
                case 2: goto L_0x003d;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x002d:
            if (r5 == 0) goto L_0x0036
            int r6 = r6 + 224
            char r4 = (char) r6
            r9.append(r4)
            goto L_0x0071
        L_0x0036:
            int r6 = r6 + 96
            char r4 = (char) r6
            r9.append(r4)
            goto L_0x0077
        L_0x003d:
            char[] r4 = b
            int r4 = r4.length
            if (r6 >= r4) goto L_0x0053
            char[] r4 = b
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x004f
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
            goto L_0x0071
        L_0x004f:
            r9.append(r4)
            goto L_0x0077
        L_0x0053:
            r4 = 27
            if (r6 != r4) goto L_0x005d
            r4 = 29
            r9.append(r4)
            goto L_0x0077
        L_0x005d:
            r4 = 30
            if (r6 != r4) goto L_0x0064
            r4 = 1
            r5 = 1
            goto L_0x0077
        L_0x0064:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0069:
            if (r5 == 0) goto L_0x0073
            int r6 = r6 + 128
            char r4 = (char) r6
            r9.append(r4)
        L_0x0071:
            r5 = 0
            goto L_0x0077
        L_0x0073:
            char r4 = (char) r6
            r9.append(r4)
        L_0x0077:
            r4 = 0
            goto L_0x0094
        L_0x0079:
            if (r6 >= r0) goto L_0x007e
            int r4 = r6 + 1
            goto L_0x0094
        L_0x007e:
            char[] r7 = f1664a
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0097
            char[] r7 = f1664a
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x0091
            int r6 = r6 + 128
            char r5 = (char) r6
            r9.append(r5)
            r5 = 0
            goto L_0x0094
        L_0x0091:
            r9.append(r6)
        L_0x0094:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x0097:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x009c:
            int r3 = r8.c()
            if (r3 > 0) goto L_0x00a3
            return
        L_0x00a3:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.a(com.xiaomi.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007c, code lost:
        r5 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(com.xiaomi.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.xiaomi.zxing.FormatException {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r8.c()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r8.a(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r8.a(r6)
            a((int) r5, (int) r6, (int[]) r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x00a8
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x0084;
                case 1: goto L_0x0074;
                case 2: goto L_0x0048;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x002d:
            char[] r4 = e
            int r4 = r4.length
            if (r6 >= r4) goto L_0x0043
            char[] r4 = e
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x003f
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
            goto L_0x007c
        L_0x003f:
            r9.append(r4)
            goto L_0x0082
        L_0x0043:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0048:
            char[] r4 = d
            int r4 = r4.length
            if (r6 >= r4) goto L_0x005e
            char[] r4 = d
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x005a
            int r4 = r4 + 128
            char r4 = (char) r4
            r9.append(r4)
            goto L_0x007c
        L_0x005a:
            r9.append(r4)
            goto L_0x0082
        L_0x005e:
            r4 = 27
            if (r6 != r4) goto L_0x0068
            r4 = 29
            r9.append(r4)
            goto L_0x0082
        L_0x0068:
            r4 = 30
            if (r6 != r4) goto L_0x006f
            r4 = 1
            r5 = 1
            goto L_0x0082
        L_0x006f:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0074:
            if (r5 == 0) goto L_0x007e
            int r6 = r6 + 128
            char r4 = (char) r6
            r9.append(r4)
        L_0x007c:
            r5 = 0
            goto L_0x0082
        L_0x007e:
            char r4 = (char) r6
            r9.append(r4)
        L_0x0082:
            r4 = 0
            goto L_0x009f
        L_0x0084:
            if (r6 >= r0) goto L_0x0089
            int r4 = r6 + 1
            goto L_0x009f
        L_0x0089:
            char[] r7 = c
            int r7 = r7.length
            if (r6 >= r7) goto L_0x00a3
            char[] r7 = c
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x009c
            int r6 = r6 + 128
            char r5 = (char) r6
            r9.append(r5)
            r5 = 0
            goto L_0x009f
        L_0x009c:
            r9.append(r6)
        L_0x009f:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x00a3:
            com.xiaomi.zxing.FormatException r8 = com.xiaomi.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x00a8:
            int r3 = r8.c()
            if (r3 > 0) goto L_0x00af
            return
        L_0x00af:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.datamatrix.decoder.DecodedBitStreamParser.b(com.xiaomi.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void c(BitSource bitSource, StringBuilder sb) throws FormatException {
        int a2;
        int[] iArr = new int[3];
        while (bitSource.c() != 8 && (a2 = bitSource.a(8)) != 254) {
            a(a2, bitSource.a(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    sb.append(CharUtils.b);
                } else if (i2 == 1) {
                    sb.append('*');
                } else if (i2 == 2) {
                    sb.append(Typography.e);
                } else if (i2 == 3) {
                    sb.append(' ');
                } else if (i2 < 14) {
                    sb.append((char) (i2 + 44));
                } else if (i2 < 40) {
                    sb.append((char) (i2 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.c() <= 0) {
                return;
            }
        }
    }

    private static void a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static void d(BitSource bitSource, StringBuilder sb) {
        while (bitSource.c() > 16) {
            for (int i = 0; i < 4; i++) {
                int a2 = bitSource.a(6);
                if (a2 == 31) {
                    int a3 = 8 - bitSource.a();
                    if (a3 != 8) {
                        bitSource.a(a3);
                        return;
                    }
                    return;
                }
                if ((a2 & 32) == 0) {
                    a2 |= 64;
                }
                sb.append((char) a2);
            }
            if (bitSource.c() <= 0) {
                return;
            }
        }
    }

    private static void a(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int b2 = bitSource.b() + 1;
        int i = b2 + 1;
        int a2 = a(bitSource.a(8), b2);
        if (a2 == 0) {
            a2 = bitSource.c() / 8;
        } else if (a2 >= 250) {
            a2 = ((a2 - 249) * 250) + a(bitSource.a(8), i);
            i++;
        }
        if (a2 >= 0) {
            byte[] bArr = new byte[a2];
            int i2 = 0;
            while (i2 < a2) {
                if (bitSource.c() >= 8) {
                    bArr[i2] = (byte) a(bitSource.a(8), i);
                    i2++;
                    i++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
            } catch (UnsupportedEncodingException e2) {
                throw new IllegalStateException("Platform does not support required encoding: " + e2);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
