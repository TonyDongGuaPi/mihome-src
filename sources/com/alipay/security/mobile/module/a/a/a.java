package com.alipay.security.mobile.module.a.a;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.code.microlog4android.format.PatternFormatter;
import com.google.common.base.Ascii;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static char[] f1161a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.f15883a};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -1, -1, -1, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0045 A[EDGE_INSN: B:33:0x0045->B:15:0x0045 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0045 A[EDGE_INSN: B:34:0x0045->B:15:0x0045 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0045 A[EDGE_INSN: B:36:0x0045->B:15:0x0045 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0021 A[LOOP:2: B:7:0x0021->B:10:0x002e, LOOP_START, PHI: r4 
      PHI: (r4v1 int) = (r4v0 int), (r4v9 int) binds: [B:6:0x001f, B:10:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r8) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "US-ASCII"
            byte[] r8 = r8.getBytes(r1)
            int r1 = r8.length
            r2 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x0045
        L_0x000f:
            byte[] r3 = b
            int r4 = r2 + 1
            byte r2 = r8[r2]
            byte r2 = r3[r2]
            r3 = -1
            if (r4 >= r1) goto L_0x001f
            if (r2 == r3) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r2 = r4
            goto L_0x000f
        L_0x001f:
            if (r2 == r3) goto L_0x0045
        L_0x0021:
            byte[] r5 = b
            int r6 = r4 + 1
            byte r4 = r8[r4]
            byte r4 = r5[r4]
            if (r6 >= r1) goto L_0x0030
            if (r4 == r3) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            r4 = r6
            goto L_0x0021
        L_0x0030:
            if (r4 == r3) goto L_0x0045
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            char r2 = (char) r2
            r0.append(r2)
        L_0x003d:
            int r2 = r6 + 1
            byte r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L_0x0050
        L_0x0045:
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x0050:
            byte[] r7 = b
            byte r5 = r7[r5]
            if (r2 >= r1) goto L_0x005b
            if (r5 == r3) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r6 = r2
            goto L_0x003d
        L_0x005b:
            if (r5 == r3) goto L_0x0045
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            char r4 = (char) r4
            r0.append(r4)
        L_0x006a:
            int r4 = r2 + 1
            byte r2 = r8[r2]
            if (r2 != r6) goto L_0x0071
            goto L_0x0045
        L_0x0071:
            byte[] r7 = b
            byte r2 = r7[r2]
            if (r4 >= r1) goto L_0x007c
            if (r2 == r3) goto L_0x007a
            goto L_0x007c
        L_0x007a:
            r2 = r4
            goto L_0x006a
        L_0x007c:
            if (r2 == r3) goto L_0x0045
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            char r2 = (char) r2
            r0.append(r2)
            r2 = r4
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.a.a(java.lang.String):byte[]");
    }
}
