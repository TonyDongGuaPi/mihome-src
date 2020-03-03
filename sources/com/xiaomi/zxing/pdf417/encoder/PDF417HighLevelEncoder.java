package com.xiaomi.zxing.pdf417.encoder;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.compress.archivers.tar.TarConstants;

final class PDF417HighLevelEncoder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1754a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 900;
    private static final int i = 901;
    private static final int j = 902;
    private static final int k = 913;
    private static final int l = 924;
    private static final int m = 925;
    private static final int n = 926;
    private static final int o = 927;
    private static final byte[] p = {48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.QUERY_RECORD_COUNT, 13, 9, Constants.TagName.SYSTEM_NEW_VERSION, Constants.TagName.BUSINESS_ORDER_OP_TYPE, 35, 45, Constants.TagName.SIM_SEID, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.CARD_FORM, Constants.TagName.USER_LOCK_TIME, Constants.TagName.ORDER_RANGE_TYPE, 42, Constants.TagName.CARD_APP_VERSION, 94, 0, 32, 0, 0, 0};
    private static final byte[] q = {Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, 64, Constants.TagName.PREDEPOSIT_BLANCE, Constants.TagName.ORDER_TRADE_STATUSES, Constants.TagName.TERMINAL_INFO_TYPE, 95, Constants.TagName.MAIN_ORDER, Constants.TagName.ELECTRONIC_OUT_SERIAL, Framer.ENTER_FRAME_PREFIX, 13, 9, Constants.TagName.SYSTEM_NEW_VERSION, Constants.TagName.BUSINESS_ORDER_OP_TYPE, 10, 45, Constants.TagName.SIM_SEID, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.CARD_FORM, 34, Constants.TagName.PRICE, 42, Constants.TagName.CARD_APP_BLANCE, 41, Constants.TagName.CARD_APP_ACTIVATION_STATUS, Constants.TagName.ELECTRONIC_USE_TIME, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE, 39, 0};
    private static final byte[] r = new byte[128];
    private static final byte[] s = new byte[128];
    private static final Charset t = Charset.forName("ISO-8859-1");

    private static boolean a(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    private static boolean b(char c2) {
        return c2 == ' ' || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean c(char c2) {
        return c2 == ' ' || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean f(char c2) {
        return c2 == 9 || c2 == 10 || c2 == 13 || (c2 >= ' ' && c2 <= '~');
    }

    static {
        Arrays.fill(r, (byte) -1);
        for (byte b2 = 0; b2 < p.length; b2 = (byte) (b2 + 1)) {
            byte b3 = p[b2];
            if (b3 > 0) {
                r[b3] = b2;
            }
        }
        Arrays.fill(s, (byte) -1);
        for (byte b4 = 0; b4 < q.length; b4 = (byte) (b4 + 1)) {
            byte b5 = q[b4];
            if (b5 > 0) {
                s[b5] = b4;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String a(String str, Compaction compaction, Charset charset) throws WriterException {
        int i2;
        CharacterSetECI characterSetECIByName;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = t;
        } else if (!t.equals(charset) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null) {
            a(characterSetECIByName.getValue(), sb);
        }
        int length = str.length();
        if (compaction != Compaction.TEXT) {
            if (compaction != Compaction.BYTE) {
                if (compaction != Compaction.NUMERIC) {
                    int i3 = 0;
                    int i4 = 0;
                    loop0:
                    while (true) {
                        int i5 = 0;
                        while (i2 < length) {
                            int a2 = a((CharSequence) str, i2);
                            if (a2 >= 13) {
                                sb.append(902);
                                i4 = 2;
                                a(str, i2, a2, sb);
                                i3 = i2 + a2;
                            } else {
                                int b2 = b(str, i2);
                                if (b2 >= 5 || a2 == length) {
                                    if (i4 != 0) {
                                        sb.append(900);
                                        i4 = 0;
                                        i5 = 0;
                                    }
                                    i5 = a((CharSequence) str, i2, b2, sb, i5);
                                    i2 += b2;
                                } else {
                                    int a3 = a(str, i2, charset);
                                    if (a3 == 0) {
                                        a3 = 1;
                                    }
                                    int i6 = a3 + i2;
                                    byte[] bytes = str.substring(i2, i6).getBytes(charset);
                                    if (bytes.length == 1 && i4 == 0) {
                                        a(bytes, 0, 1, 0, sb);
                                    } else {
                                        a(bytes, 0, bytes.length, i4, sb);
                                        i4 = 1;
                                        i5 = 0;
                                    }
                                    i2 = i6;
                                }
                            }
                        }
                        break loop0;
                    }
                } else {
                    sb.append(902);
                    a(str, 0, length, sb);
                }
            } else {
                byte[] bytes2 = str.getBytes(charset);
                a(bytes2, 0, bytes2.length, 1, sb);
            }
        } else {
            a((CharSequence) str, 0, length, sb, 0);
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d0, code lost:
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00dc, code lost:
        r9 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ea, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ec, code lost:
        if (r8 < r1) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ee, code lost:
        r0 = r3.length();
        r1 = 0;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f4, code lost:
        if (r1 >= r0) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f8, code lost:
        if ((r1 % 2) == 0) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fa, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fc, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fd, code lost:
        if (r8 == false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ff, code lost:
        r7 = (char) ((r7 * 30) + r3.charAt(r1));
        r2.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010b, code lost:
        r7 = r3.charAt(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x010f, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0113, code lost:
        if ((r0 % 2) == 0) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0115, code lost:
        r2.append((char) ((r7 * 30) + 29));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x011c, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0120, code lost:
        r9 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 2
            r6 = 0
            r9 = r20
            r8 = 0
        L_0x0010:
            int r10 = r17 + r8
            char r11 = r0.charAt(r10)
            r12 = 26
            r13 = 32
            r14 = 28
            r15 = 27
            r5 = 29
            switch(r9) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x007b;
                case 2: goto L_0x0033;
                default: goto L_0x0023;
            }
        L_0x0023:
            boolean r10 = e(r11)
            if (r10 == 0) goto L_0x011d
            byte[] r10 = s
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x0033:
            boolean r12 = d(r11)
            if (r12 == 0) goto L_0x0043
            byte[] r10 = r
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x0043:
            boolean r12 = b(r11)
            if (r12 == 0) goto L_0x004e
            r3.append(r14)
            goto L_0x0120
        L_0x004e:
            boolean r12 = c(r11)
            if (r12 == 0) goto L_0x0059
            r3.append(r15)
            goto L_0x00d0
        L_0x0059:
            int r10 = r10 + 1
            if (r10 >= r1) goto L_0x006e
            char r10 = r0.charAt(r10)
            boolean r10 = e(r10)
            if (r10 == 0) goto L_0x006e
            r9 = 3
            r5 = 25
            r3.append(r5)
            goto L_0x0010
        L_0x006e:
            r3.append(r5)
            byte[] r10 = s
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x007b:
            boolean r10 = c(r11)
            if (r10 == 0) goto L_0x008e
            if (r11 != r13) goto L_0x0087
            r3.append(r12)
            goto L_0x00ea
        L_0x0087:
            int r11 = r11 + -97
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x008e:
            boolean r10 = b(r11)
            if (r10 == 0) goto L_0x009e
            r3.append(r15)
            int r11 = r11 + -65
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x009e:
            boolean r10 = d(r11)
            if (r10 == 0) goto L_0x00a8
            r3.append(r14)
            goto L_0x00dc
        L_0x00a8:
            r3.append(r5)
            byte[] r10 = s
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
            goto L_0x00ea
        L_0x00b4:
            boolean r10 = b(r11)
            if (r10 == 0) goto L_0x00c7
            if (r11 != r13) goto L_0x00c0
            r3.append(r12)
            goto L_0x00ea
        L_0x00c0:
            int r11 = r11 + -65
            char r10 = (char) r11
            r3.append(r10)
            goto L_0x00ea
        L_0x00c7:
            boolean r10 = c(r11)
            if (r10 == 0) goto L_0x00d3
            r3.append(r15)
        L_0x00d0:
            r9 = 1
            goto L_0x0010
        L_0x00d3:
            boolean r10 = d(r11)
            if (r10 == 0) goto L_0x00df
            r3.append(r14)
        L_0x00dc:
            r9 = 2
            goto L_0x0010
        L_0x00df:
            r3.append(r5)
            byte[] r10 = s
            byte r10 = r10[r11]
            char r10 = (char) r10
            r3.append(r10)
        L_0x00ea:
            int r8 = r8 + 1
            if (r8 < r1) goto L_0x0010
            int r0 = r3.length()
            r1 = 0
            r7 = 0
        L_0x00f4:
            if (r1 >= r0) goto L_0x0112
            int r8 = r1 % 2
            if (r8 == 0) goto L_0x00fc
            r8 = 1
            goto L_0x00fd
        L_0x00fc:
            r8 = 0
        L_0x00fd:
            if (r8 == 0) goto L_0x010b
            int r7 = r7 * 30
            char r8 = r3.charAt(r1)
            int r7 = r7 + r8
            char r7 = (char) r7
            r2.append(r7)
            goto L_0x010f
        L_0x010b:
            char r7 = r3.charAt(r1)
        L_0x010f:
            int r1 = r1 + 1
            goto L_0x00f4
        L_0x0112:
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x011c
            int r7 = r7 * 30
            int r7 = r7 + r5
            char r0 = (char) r7
            r2.append(r0)
        L_0x011c:
            return r9
        L_0x011d:
            r3.append(r5)
        L_0x0120:
            r9 = 0
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.encoder.PDF417HighLevelEncoder.a(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void a(byte[] bArr, int i2, int i3, int i4, StringBuilder sb) {
        int i5;
        if (i3 == 1 && i4 == 0) {
            sb.append(913);
        } else if (i3 % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        if (i3 >= 6) {
            char[] cArr = new char[5];
            i5 = i2;
            while ((i2 + i3) - i5 >= 6) {
                long j2 = 0;
                for (int i6 = 0; i6 < 6; i6++) {
                    j2 = (j2 << 8) + ((long) (bArr[i5 + i6] & 255));
                }
                for (int i7 = 0; i7 < 5; i7++) {
                    cArr[i7] = (char) ((int) (j2 % 900));
                    j2 /= 900;
                }
                for (int length = cArr.length - 1; length >= 0; length--) {
                    sb.append(cArr[length]);
                }
                i5 += 6;
            }
        } else {
            i5 = i2;
        }
        while (i5 < i2 + i3) {
            sb.append((char) (bArr[i5] & 255));
            i5++;
        }
    }

    private static void a(String str, int i2, int i3, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i3 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i4 = 0;
        while (i4 < i3) {
            sb2.setLength(0);
            int min = Math.min(44, i3 - i4);
            StringBuilder sb3 = new StringBuilder();
            sb3.append('1');
            int i5 = i2 + i4;
            sb3.append(str.substring(i5, i5 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i4 += min;
        }
    }

    private static boolean d(char c2) {
        return r[c2] != -1;
    }

    private static boolean e(char c2) {
        return s[c2] != -1;
    }

    private static int a(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        if (i2 < length) {
            char charAt = charSequence.charAt(i2);
            while (a(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
        }
        return i3;
    }

    private static int b(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = i2;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < 13 && a(charAt) && i3 < length) {
                i4++;
                i3++;
                if (i3 < length) {
                    charAt = charSequence.charAt(i3);
                }
            }
            if (i4 >= 13) {
                return (i3 - i2) - i4;
            }
            if (i4 <= 0) {
                if (!f(charSequence.charAt(i3))) {
                    break;
                }
                i3++;
            }
        }
        return i3 - i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r5, int r6, java.nio.charset.Charset r7) throws com.xiaomi.zxing.WriterException {
        /*
            java.nio.charset.CharsetEncoder r7 = r7.newEncoder()
            int r0 = r5.length()
            r1 = r6
        L_0x0009:
            if (r1 >= r0) goto L_0x005b
            char r2 = r5.charAt(r1)
            r3 = 0
        L_0x0010:
            r4 = 13
            if (r3 >= r4) goto L_0x0026
            boolean r2 = a(r2)
            if (r2 == 0) goto L_0x0026
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 < r0) goto L_0x0021
            goto L_0x0026
        L_0x0021:
            char r2 = r5.charAt(r2)
            goto L_0x0010
        L_0x0026:
            if (r3 < r4) goto L_0x002a
            int r1 = r1 - r6
            return r1
        L_0x002a:
            char r2 = r5.charAt(r1)
            boolean r3 = r7.canEncode(r2)
            if (r3 == 0) goto L_0x0037
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0037:
            com.xiaomi.zxing.WriterException r5 = new com.xiaomi.zxing.WriterException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Non-encodable character detected: "
            r6.append(r7)
            r6.append(r2)
            java.lang.String r7 = " (Unicode: "
            r6.append(r7)
            r6.append(r2)
            r7 = 41
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>((java.lang.String) r6)
            throw r5
        L_0x005b:
            int r1 = r1 - r6
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.encoder.PDF417HighLevelEncoder.a(java.lang.String, int, java.nio.charset.Charset):int");
    }

    private static void a(int i2, StringBuilder sb) throws WriterException {
        if (i2 >= 0 && i2 < 900) {
            sb.append(927);
            sb.append((char) i2);
        } else if (i2 < 810900) {
            sb.append(926);
            sb.append((char) ((i2 / 900) - 1));
            sb.append((char) (i2 % 900));
        } else if (i2 < 811800) {
            sb.append(925);
            sb.append((char) (810900 - i2));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i2);
        }
    }
}
