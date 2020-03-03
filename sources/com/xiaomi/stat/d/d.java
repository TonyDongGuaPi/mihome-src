package com.xiaomi.stat.d;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.code.microlog4android.format.PatternFormatter;
import com.google.common.base.Ascii;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.UnsupportedEncodingException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23048a = "Base64Utils";
    private static char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.f15883a};
    private static byte[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -1, -1, -1, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            byte b2 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(b[b2 >>> 2]);
                stringBuffer.append(b[(b2 & 3) << 4]);
                stringBuffer.append(Operators.EQUAL2);
                break;
            }
            int i3 = i2 + 1;
            byte b3 = bArr[i2] & 255;
            if (i3 == length) {
                stringBuffer.append(b[b2 >>> 2]);
                stringBuffer.append(b[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
                stringBuffer.append(b[(b3 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            int i4 = i3 + 1;
            byte b4 = bArr[i3] & 255;
            stringBuffer.append(b[b2 >>> 2]);
            stringBuffer.append(b[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
            stringBuffer.append(b[((b3 & 15) << 2) | ((b4 & Constants.TagName.STATION_ENAME) >>> 6)]);
            stringBuffer.append(b[b4 & Constants.TagName.CARD_APP_ACTIVATION_STATUS]);
            i = i4;
        }
        return stringBuffer.toString();
    }

    public static byte[] a(String str) {
        try {
            return b(str);
        } catch (UnsupportedEncodingException e) {
            k.d(f23048a, "decode e", e);
            return new byte[0];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008e A[LOOP:0: B:1:0x000d->B:32:0x008e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a A[EDGE_INSN: B:36:0x009a->B:33:0x009a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a A[EDGE_INSN: B:38:0x009a->B:33:0x009a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009a A[EDGE_INSN: B:39:0x009a->B:33:0x009a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009a A[EDGE_INSN: B:40:0x009a->B:33:0x009a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0023 A[LOOP:2: B:7:0x0023->B:10:0x0030, LOOP_START, PHI: r4 
      PHI: (r4v1 int) = (r4v0 int), (r4v9 int) binds: [B:6:0x001f, B:10:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.lang.String r8) throws java.io.UnsupportedEncodingException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "US-ASCII"
            byte[] r8 = r8.getBytes(r1)
            int r1 = r8.length
            r2 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x009a
        L_0x000f:
            byte[] r3 = c
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
            if (r2 != r3) goto L_0x0023
            goto L_0x009a
        L_0x0023:
            byte[] r5 = c
            int r6 = r4 + 1
            byte r4 = r8[r4]
            byte r4 = r5[r4]
            if (r6 >= r1) goto L_0x0032
            if (r4 == r3) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r4 = r6
            goto L_0x0023
        L_0x0032:
            if (r4 != r3) goto L_0x0036
            goto L_0x009a
        L_0x0036:
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            char r2 = (char) r2
            r0.append(r2)
        L_0x0041:
            int r2 = r6 + 1
            byte r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L_0x0054
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x0054:
            byte[] r7 = c
            byte r5 = r7[r5]
            if (r2 >= r1) goto L_0x005f
            if (r5 == r3) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            r6 = r2
            goto L_0x0041
        L_0x005f:
            if (r5 != r3) goto L_0x0062
            goto L_0x009a
        L_0x0062:
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            char r4 = (char) r4
            r0.append(r4)
        L_0x006f:
            int r4 = r2 + 1
            byte r2 = r8[r2]
            if (r2 != r6) goto L_0x0080
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x0080:
            byte[] r7 = c
            byte r2 = r7[r2]
            if (r4 >= r1) goto L_0x008b
            if (r2 == r3) goto L_0x0089
            goto L_0x008b
        L_0x0089:
            r2 = r4
            goto L_0x006f
        L_0x008b:
            if (r2 != r3) goto L_0x008e
            goto L_0x009a
        L_0x008e:
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            char r2 = (char) r2
            r0.append(r2)
            r2 = r4
            goto L_0x000d
        L_0x009a:
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.d.d.b(java.lang.String):byte[]");
    }
}
