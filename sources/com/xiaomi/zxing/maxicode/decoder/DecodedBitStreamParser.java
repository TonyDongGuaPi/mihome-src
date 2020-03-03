package com.xiaomi.zxing.maxicode.decoder;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.common.base.Ascii;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.zxing.common.DecoderResult;
import java.text.DecimalFormat;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

final class DecodedBitStreamParser {

    /* renamed from: a  reason: collision with root package name */
    private static final char f1682a = '￰';
    private static final char b = '￱';
    private static final char c = '￲';
    private static final char d = '￳';
    private static final char e = '￴';
    private static final char f = '￵';
    private static final char g = '￶';
    private static final char h = '￷';
    private static final char i = '￸';
    private static final char j = '￹';
    private static final char k = '￺';
    private static final char l = '￻';
    private static final char m = '￼';
    private static final char n = '\u001c';
    private static final char o = '\u001d';
    private static final char p = '\u001e';
    private static final String[] q = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\u001c\u001d\u001e￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\u001c\u001d\u001e￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\u001c\u001d\u001e￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a￺￼￼\u001b￻\u001c\u001d\u001e\u001f ¢£¤¥¦§©­®¶￷ ￲￳￹￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    private DecodedBitStreamParser() {
    }

    static DecoderResult a(byte[] bArr, int i2) {
        String str;
        StringBuilder sb = new StringBuilder(144);
        switch (i2) {
            case 2:
            case 3:
                if (i2 == 2) {
                    str = new DecimalFormat("0000000000".substring(0, c(bArr))).format((long) d(bArr));
                } else {
                    str = e(bArr);
                }
                DecimalFormat decimalFormat = new DecimalFormat(Constant.DEFAULT_CVN2);
                String format = decimalFormat.format((long) a(bArr));
                String format2 = decimalFormat.format((long) b(bArr));
                sb.append(a(bArr, 10, 84));
                if (!sb.toString().startsWith("[)>\u001e01\u001d")) {
                    sb.insert(0, str + o + format + o + format2 + o);
                    break;
                } else {
                    sb.insert(9, str + o + format + o + format2 + o);
                    break;
                }
            case 4:
                sb.append(a(bArr, 1, 93));
                break;
            case 5:
                sb.append(a(bArr, 1, 77));
                break;
        }
        return new DecoderResult(bArr, sb.toString(), (List<byte[]>) null, String.valueOf(i2));
    }

    private static int a(int i2, byte[] bArr) {
        int i3 = i2 - 1;
        return ((1 << (5 - (i3 % 6))) & bArr[i3 / 6]) == 0 ? 0 : 1;
    }

    private static int a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                i2 += a((int) bArr2[i3], bArr) << ((bArr2.length - i3) - 1);
            }
            return i2;
        }
        throw new IllegalArgumentException();
    }

    private static int a(byte[] bArr) {
        return a(bArr, new byte[]{TarConstants.R, 54, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT});
    }

    private static int b(byte[] bArr) {
        return a(bArr, new byte[]{55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, 49, 50, 51, 52});
    }

    private static int c(byte[] bArr) {
        return a(bArr, new byte[]{39, Constants.TagName.CARD_APP_BLANCE, 41, 42, 31, 32});
    }

    private static int d(byte[] bArr) {
        return a(bArr, new byte[]{Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, 25, 26, 27, 28, Ascii.GS, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2});
    }

    private static String e(byte[] bArr) {
        return String.valueOf(new char[]{q[0].charAt(a(bArr, new byte[]{39, Constants.TagName.CARD_APP_BLANCE, 41, 42, 31, 32})), q[0].charAt(a(bArr, new byte[]{Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, 25, 26})), q[0].charAt(a(bArr, new byte[]{27, 28, Ascii.GS, 30, 19, 20})), q[0].charAt(a(bArr, new byte[]{21, 22, 23, 24, 13, 14})), q[0].charAt(a(bArr, new byte[]{15, 16, 17, 18, 7, 8})), q[0].charAt(a(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0055, code lost:
        r6 = r4;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        r7 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        if (r5 != 0) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        r3 = r3 + 1;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0050, code lost:
        r5 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(byte[] r12, int r13, int r14) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r2 = -1
            r3 = r13
            r4 = 0
            r5 = -1
            r6 = 0
        L_0x000b:
            int r7 = r13 + r14
            r8 = 1
            if (r3 >= r7) goto L_0x0068
            java.lang.String[] r7 = q
            r7 = r7[r4]
            byte r9 = r12[r3]
            char r7 = r7.charAt(r9)
            switch(r7) {
                case 65520: goto L_0x0058;
                case 65521: goto L_0x0058;
                case 65522: goto L_0x0058;
                case 65523: goto L_0x0058;
                case 65524: goto L_0x0058;
                case 65525: goto L_0x0054;
                case 65526: goto L_0x0052;
                case 65527: goto L_0x004f;
                case 65528: goto L_0x004d;
                case 65529: goto L_0x0050;
                case 65530: goto L_0x001d;
                case 65531: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            r0.append(r7)
            goto L_0x0060
        L_0x0021:
            int r3 = r3 + 1
            byte r7 = r12[r3]
            int r7 = r7 << 24
            int r3 = r3 + r8
            byte r9 = r12[r3]
            int r9 = r9 << 18
            int r7 = r7 + r9
            int r3 = r3 + r8
            byte r9 = r12[r3]
            int r9 = r9 << 12
            int r7 = r7 + r9
            int r3 = r3 + r8
            byte r9 = r12[r3]
            int r9 = r9 << 6
            int r7 = r7 + r9
            int r3 = r3 + r8
            byte r9 = r12[r3]
            int r7 = r7 + r9
            java.text.DecimalFormat r9 = new java.text.DecimalFormat
            java.lang.String r10 = "000000000"
            r9.<init>(r10)
            long r10 = (long) r7
            java.lang.String r7 = r9.format(r10)
            r0.append(r7)
            goto L_0x0060
        L_0x004d:
            r4 = 1
            goto L_0x0050
        L_0x004f:
            r4 = 0
        L_0x0050:
            r5 = -1
            goto L_0x0060
        L_0x0052:
            r5 = 3
            goto L_0x0055
        L_0x0054:
            r5 = 2
        L_0x0055:
            r6 = r4
            r4 = 0
            goto L_0x0060
        L_0x0058:
            r5 = 65520(0xfff0, float:9.1813E-41)
            int r5 = r7 - r5
            r6 = r4
            r4 = r5
            r5 = 1
        L_0x0060:
            int r7 = r5 + -1
            if (r5 != 0) goto L_0x0065
            r4 = r6
        L_0x0065:
            int r3 = r3 + r8
            r5 = r7
            goto L_0x000b
        L_0x0068:
            int r12 = r0.length()
            if (r12 <= 0) goto L_0x0085
            int r12 = r0.length()
            int r12 = r12 - r8
            char r12 = r0.charAt(r12)
            r13 = 65532(0xfffc, float:9.183E-41)
            if (r12 != r13) goto L_0x0085
            int r12 = r0.length()
            int r12 = r12 - r8
            r0.setLength(r12)
            goto L_0x0068
        L_0x0085:
            java.lang.String r12 = r0.toString()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.maxicode.decoder.DecodedBitStreamParser.a(byte[], int, int):java.lang.String");
    }
}
