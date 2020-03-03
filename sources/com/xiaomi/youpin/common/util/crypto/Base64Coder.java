package com.xiaomi.youpin.common.util.crypto;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.common.base.Ascii;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class Base64Coder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23293a = 0;
    public static final int b = 1;
    public static final int c = 0;
    public static final int d = 2;
    public static final int e = 8;
    public static final int f = 16;
    public static final int g = 32;
    private static final int h = 76;
    private static final byte i = 61;
    private static final byte j = 10;
    private static final String k = "UTF-8";
    private static final byte l = -5;
    private static final byte m = -1;
    private static final byte[] n = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
    private static final byte[] o = {-9, -9, -9, -9, -9, -9, -9, -9, -9, l, l, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -9, -9, -9, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -9, -9, -9, -9};
    private static final byte[] p = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, 45, 95};
    private static final byte[] q = {-9, -9, -9, -9, -9, -9, -9, -9, -9, l, l, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -9, -9, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, Constants.TagName.CARD_APP_ACTIVATION_STATUS, -9, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -9, -9, -9, -9};
    private static final byte[] r = {45, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 95, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE};
    private static final byte[] s = {-9, -9, -9, -9, -9, -9, -9, -9, -9, l, l, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, l, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, -9, -9, -9, -9, Constants.TagName.ORDER_RANGE_TYPE, -9, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, 61, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, Constants.TagName.CARD_APP_ACTIVATION_STATUS, -9, -9, -9, -9};

    private Base64Coder() {
    }

    /* access modifiers changed from: private */
    public static byte[] c(int i2) {
        if ((i2 & 16) == 16) {
            return p;
        }
        if ((i2 & 32) == 32) {
            return r;
        }
        return n;
    }

    /* access modifiers changed from: private */
    public static byte[] d(int i2) {
        if ((i2 & 16) == 16) {
            return q;
        }
        if ((i2 & 32) == 32) {
            return s;
        }
        return o;
    }

    public static void a(String[] strArr) {
        if (strArr.length < 3) {
            e("Not enough arguments.");
            return;
        }
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        if (str.equals("-e")) {
            b(str2, str3);
        } else if (str.equals("-d")) {
            c(str2, str3);
        } else {
            e("Unknown flag: " + str);
        }
    }

    private static void e(String str) {
        System.err.println(str);
        System.err.println("Usage: java Base64Coder -e|-d inputfile outputfile");
    }

    /* access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i2, int i3) {
        b(bArr2, 0, i2, bArr, 0, i3);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        byte[] c2 = c(i5);
        int i6 = 0;
        int i7 = (i3 > 0 ? (bArr[i2] << 24) >>> 8 : 0) | (i3 > 1 ? (bArr[i2 + 1] << 24) >>> 16 : 0);
        if (i3 > 2) {
            i6 = (bArr[i2 + 2] << 24) >>> 24;
        }
        int i8 = i7 | i6;
        switch (i3) {
            case 1:
                bArr2[i4] = c2[i8 >>> 18];
                bArr2[i4 + 1] = c2[(i8 >>> 12) & 63];
                bArr2[i4 + 2] = 61;
                bArr2[i4 + 3] = 61;
                return bArr2;
            case 2:
                bArr2[i4] = c2[i8 >>> 18];
                bArr2[i4 + 1] = c2[(i8 >>> 12) & 63];
                bArr2[i4 + 2] = c2[(i8 >>> 6) & 63];
                bArr2[i4 + 3] = 61;
                return bArr2;
            case 3:
                bArr2[i4] = c2[i8 >>> 18];
                bArr2[i4 + 1] = c2[(i8 >>> 12) & 63];
                bArr2[i4 + 2] = c2[(i8 >>> 6) & 63];
                bArr2[i4 + 3] = c2[i8 & 63];
                return bArr2;
            default:
                return bArr2;
        }
    }

    public static String a(Serializable serializable) {
        return a(serializable, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:47|48|49|50|51|52|53|54|55|56|57) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:59|60|61|62|63|64|65|66|67|68|69) */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Can't wrap try/catch for region: R(19:1|2|3|4|5|(5:7|8|9|10|11)(3:19|20|21)|22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|(5:7|8|9|10|11)(3:19|20|21)|22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0059, code lost:
        return new java.lang.String(r2.toByteArray());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x003b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x003e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0041 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0075 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0078 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x007b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0085 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0088 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x008b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.Serializable r5, int r6) {
        /*
            r0 = r6 & 2
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
            r2.<init>()     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
            com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream r3 = new com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream     // Catch:{ IOException -> 0x0060, all -> 0x005c }
            r6 = r6 | 1
            r3.<init>(r2, r6)     // Catch:{ IOException -> 0x0060, all -> 0x005c }
            r6 = 2
            if (r0 != r6) goto L_0x002f
            java.util.zip.GZIPOutputStream r6 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r0.<init>(r6)     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x0035
        L_0x0020:
            r5 = move-exception
            goto L_0x0082
        L_0x0023:
            r5 = move-exception
            r0 = r6
            r6 = r1
            goto L_0x006f
        L_0x0027:
            r5 = move-exception
            r6 = r1
            goto L_0x0082
        L_0x002b:
            r5 = move-exception
            r6 = r1
            r0 = r6
            goto L_0x006f
        L_0x002f:
            java.io.ObjectOutputStream r6 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r0 = r1
        L_0x0035:
            r6.writeObject(r5)     // Catch:{ IOException -> 0x005a }
            r6.close()     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            r0.close()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            r3.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            r2.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            byte[] r6 = r2.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            java.lang.String r0 = "UTF-8"
            r5.<init>(r6, r0)     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            return r5
        L_0x0050:
            java.lang.String r5 = new java.lang.String
            byte[] r6 = r2.toByteArray()
            r5.<init>(r6)
            return r5
        L_0x005a:
            r5 = move-exception
            goto L_0x006f
        L_0x005c:
            r5 = move-exception
            r6 = r1
            r3 = r6
            goto L_0x0082
        L_0x0060:
            r5 = move-exception
            r6 = r1
            r0 = r6
            r3 = r0
            goto L_0x006f
        L_0x0065:
            r5 = move-exception
            r6 = r1
            r2 = r6
            r3 = r2
            goto L_0x0082
        L_0x006a:
            r5 = move-exception
            r6 = r1
            r0 = r6
            r2 = r0
            r3 = r2
        L_0x006f:
            r5.printStackTrace()     // Catch:{ all -> 0x007f }
            r6.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            r0.close()     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            r3.close()     // Catch:{ Exception -> 0x007b }
        L_0x007b:
            r2.close()     // Catch:{ Exception -> 0x007e }
        L_0x007e:
            return r1
        L_0x007f:
            r5 = move-exception
            r1 = r6
            r6 = r0
        L_0x0082:
            r1.close()     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            r6.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            r3.close()     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            r2.close()     // Catch:{ Exception -> 0x008e }
        L_0x008e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.a(java.io.Serializable, int):java.lang.String");
    }

    public static String a(byte[] bArr) {
        return b(bArr);
    }

    public static String b(byte[] bArr) {
        return a(bArr, 0, bArr.length, 8);
    }

    public static String a(byte[] bArr, int i2) {
        return a(bArr, 0, bArr.length, i2);
    }

    public static String a(byte[] bArr, int i2, int i3) {
        return a(bArr, i2, i3, 8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream} */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:10|11|12|13|14|15|16|17|18|19|20) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:26|27|37|38|39|40|41|42|43|44|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:47|48|49|50|51|52|53|54|55) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x002f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0060 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0063 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x006f */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(byte[] r17, int r18, int r19, int r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20 & 8
            r3 = r20 & 2
            r4 = 1
            r5 = 2
            if (r3 != r5) goto L_0x0073
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0056, all -> 0x0052 }
            r3.<init>()     // Catch:{ IOException -> 0x0056, all -> 0x0052 }
            com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream r5 = new com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            r4 = r20 | 1
            r5.<init>(r3, r4)     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r8 = r17
            r4.write(r8, r0, r1)     // Catch:{ IOException -> 0x0045 }
            r4.close()     // Catch:{ IOException -> 0x0045 }
            r4.close()     // Catch:{ Exception -> 0x0029 }
        L_0x0029:
            r5.close()     // Catch:{ Exception -> 0x002c }
        L_0x002c:
            r3.close()     // Catch:{ Exception -> 0x002f }
        L_0x002f:
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x003b }
            byte[] r1 = r3.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x003b }
            java.lang.String r2 = "UTF-8"
            r0.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x003b }
            return r0
        L_0x003b:
            java.lang.String r0 = new java.lang.String
            byte[] r1 = r3.toByteArray()
            r0.<init>(r1)
            return r0
        L_0x0045:
            r0 = move-exception
            goto L_0x005a
        L_0x0047:
            r0 = move-exception
            goto L_0x0069
        L_0x0049:
            r0 = move-exception
            r4 = r2
            goto L_0x005a
        L_0x004c:
            r0 = move-exception
            r5 = r2
            goto L_0x0069
        L_0x004f:
            r0 = move-exception
            r4 = r2
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r3 = r2
            r5 = r3
            goto L_0x0069
        L_0x0056:
            r0 = move-exception
            r3 = r2
            r4 = r3
        L_0x0059:
            r5 = r4
        L_0x005a:
            r0.printStackTrace()     // Catch:{ all -> 0x0067 }
            r4.close()     // Catch:{ Exception -> 0x0060 }
        L_0x0060:
            r5.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r3.close()     // Catch:{ Exception -> 0x0066 }
        L_0x0066:
            return r2
        L_0x0067:
            r0 = move-exception
            r2 = r4
        L_0x0069:
            r2.close()     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r5.close()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            r3.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0072:
            throw r0
        L_0x0073:
            r8 = r17
            if (r2 != 0) goto L_0x0079
            r10 = 1
            goto L_0x007a
        L_0x0079:
            r10 = 0
        L_0x007a:
            int r2 = r1 * 4
            int r2 = r2 / 3
            int r3 = r1 % 3
            r11 = 4
            if (r3 <= 0) goto L_0x0085
            r3 = 4
            goto L_0x0086
        L_0x0085:
            r3 = 0
        L_0x0086:
            int r3 = r3 + r2
            r12 = 76
            if (r10 == 0) goto L_0x008d
            int r2 = r2 / r12
            goto L_0x008e
        L_0x008d:
            r2 = 0
        L_0x008e:
            int r3 = r3 + r2
            byte[] r13 = new byte[r3]
            int r14 = r1 + -2
            r7 = 0
            r15 = 0
            r16 = 0
        L_0x0097:
            if (r7 >= r14) goto L_0x00bd
            int r3 = r7 + r0
            r4 = 3
            r2 = r17
            r5 = r13
            r6 = r15
            r9 = r7
            r7 = r20
            b(r2, r3, r4, r5, r6, r7)
            int r2 = r16 + 4
            if (r10 == 0) goto L_0x00b7
            if (r2 != r12) goto L_0x00b7
            int r2 = r15 + 4
            r3 = 10
            r13[r2] = r3
            int r15 = r15 + 1
            r16 = 0
            goto L_0x00b9
        L_0x00b7:
            r16 = r2
        L_0x00b9:
            int r7 = r9 + 3
            int r15 = r15 + r11
            goto L_0x0097
        L_0x00bd:
            r9 = r7
            if (r9 >= r1) goto L_0x00e4
            int r2 = r9 + r0
            int r6 = r1 - r9
            r0 = r17
            r1 = r2
            r2 = r6
            r3 = r13
            r4 = r15
            r5 = r20
            b(r0, r1, r2, r3, r4, r5)
            r0 = 16
            r1 = r20 & 16
            if (r1 != r0) goto L_0x00e2
            switch(r6) {
                case 0: goto L_0x00df;
                case 1: goto L_0x00dc;
                case 2: goto L_0x00d9;
                default: goto L_0x00d8;
            }
        L_0x00d8:
            goto L_0x00e4
        L_0x00d9:
            int r15 = r15 + 3
            goto L_0x00e4
        L_0x00dc:
            int r15 = r15 + 2
            goto L_0x00e4
        L_0x00df:
            int r15 = r15 + 1
            goto L_0x00e4
        L_0x00e2:
            int r15 = r15 + 4
        L_0x00e4:
            r0 = r15
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x00ee }
            java.lang.String r2 = "UTF-8"
            r3 = 0
            r1.<init>(r13, r3, r0, r2)     // Catch:{ UnsupportedEncodingException -> 0x00ef }
            return r1
        L_0x00ee:
            r3 = 0
        L_0x00ef:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r13, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.a(byte[], int, int, int):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static int b(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        byte[] d2 = d(i4);
        int i5 = i2 + 2;
        if (bArr[i5] == 61) {
            bArr2[i3] = (byte) ((((d2[bArr[i2 + 1]] & 255) << 12) | ((d2[bArr[i2]] & 255) << 18)) >>> 16);
            return 1;
        }
        int i6 = i2 + 3;
        if (bArr[i6] == 61) {
            int i7 = (d2[bArr[i2 + 1]] & 255) << 12;
            int i8 = ((d2[bArr[i5]] & 255) << 6) | i7 | ((d2[bArr[i2]] & 255) << 18);
            bArr2[i3] = (byte) (i8 >>> 16);
            bArr2[i3 + 1] = (byte) (i8 >>> 8);
            return 2;
        }
        try {
            byte b2 = ((d2[bArr[i2]] & 255) << 18) | ((d2[bArr[i2 + 1]] & 255) << 12) | ((d2[bArr[i5]] & 255) << 6) | (d2[bArr[i6]] & 255);
            bArr2[i3] = (byte) (b2 >> 16);
            bArr2[i3 + 1] = (byte) (b2 >> 8);
            bArr2[i3 + 2] = (byte) b2;
            return 3;
        } catch (Exception unused) {
            PrintStream printStream = System.out;
            printStream.println("" + bArr[i2] + ": " + d2[bArr[i2]]);
            PrintStream printStream2 = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i9 = i2 + 1;
            sb.append(bArr[i9]);
            sb.append(": ");
            sb.append(d2[bArr[i9]]);
            printStream2.println(sb.toString());
            PrintStream printStream3 = System.out;
            printStream3.println("" + bArr[i5] + ": " + d2[bArr[i5]]);
            PrintStream printStream4 = System.out;
            printStream4.println("" + bArr[i6] + ": " + d2[bArr[i6]]);
            return -1;
        }
    }

    public static byte[] b(byte[] bArr, int i2, int i3, int i4) {
        byte[] d2 = d(i4);
        byte[] bArr2 = new byte[((i3 * 3) / 4)];
        byte[] bArr3 = new byte[4];
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i2 + i3) {
            byte b2 = (byte) (bArr[i5] & Byte.MAX_VALUE);
            byte b3 = d2[b2];
            if (b3 >= -5) {
                if (b3 >= -1) {
                    int i8 = i6 + 1;
                    bArr3[i6] = b2;
                    if (i8 > 3) {
                        i7 += b(bArr3, 0, bArr2, i7, i4);
                        if (b2 == 61) {
                            break;
                        }
                        i6 = 0;
                    } else {
                        i6 = i8;
                    }
                }
                i5++;
            } else {
                PrintStream printStream = System.err;
                printStream.println("Bad Base64Coder input character at " + i5 + ": " + bArr[i5] + "(decimal)");
                return null;
            }
        }
        byte[] bArr4 = new byte[i7];
        System.arraycopy(bArr2, 0, bArr4, 0, i7);
        return bArr4;
    }

    public static byte[] a(String str) {
        return a(str, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:16|17|(3:18|19|(1:21)(1:59))|22|23|24|25|26|(2:27|28)|29|65) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:31|32|42|43|44|45|46|47|48) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:34|33|52|53|54|55|56|57|60) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0052 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0069 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0072 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0075 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x0078 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r5, int r6) {
        /*
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r5.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x0007 }
            goto L_0x000b
        L_0x0007:
            byte[] r0 = r5.getBytes()
        L_0x000b:
            int r5 = r0.length
            r1 = 0
            byte[] r5 = b((byte[]) r0, (int) r1, (int) r5, (int) r6)
            if (r5 == 0) goto L_0x007b
            int r6 = r5.length
            r0 = 4
            if (r6 < r0) goto L_0x007b
            byte r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r0 = 1
            byte r0 = r5[r0]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto L_0x007b
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0070, all -> 0x0063 }
            r2.<init>()     // Catch:{ IOException -> 0x0070, all -> 0x0063 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0072, all -> 0x005c }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0072, all -> 0x005c }
        L_0x003e:
            int r0 = r4.read(r6)     // Catch:{ IOException -> 0x005a, all -> 0x0057 }
            if (r0 < 0) goto L_0x0048
            r2.write(r6, r1, r0)     // Catch:{ IOException -> 0x005a, all -> 0x0057 }
            goto L_0x003e
        L_0x0048:
            byte[] r6 = r2.toByteArray()     // Catch:{ IOException -> 0x005a, all -> 0x0057 }
            r2.close()     // Catch:{ Exception -> 0x004f }
        L_0x004f:
            r4.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            r3.close()     // Catch:{ Exception -> 0x0055 }
        L_0x0055:
            r5 = r6
            goto L_0x007b
        L_0x0057:
            r5 = move-exception
            r0 = r4
            goto L_0x0066
        L_0x005a:
            r0 = r4
            goto L_0x0072
        L_0x005c:
            r5 = move-exception
            goto L_0x0066
        L_0x005e:
            r5 = move-exception
            r3 = r0
            goto L_0x0066
        L_0x0061:
            r3 = r0
            goto L_0x0072
        L_0x0063:
            r5 = move-exception
            r2 = r0
            r3 = r2
        L_0x0066:
            r2.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0069:
            r0.close()     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r3.close()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            throw r5
        L_0x0070:
            r2 = r0
            r3 = r2
        L_0x0072:
            r2.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            r0.close()     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            r3.close()     // Catch:{ Exception -> 0x007b }
        L_0x007b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.a(java.lang.String, int):byte[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:1|2|3|4|5|6|7|8|9|10|11|44) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:15|37|38|39|40|41) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:14|32|33|26|27|28|29|45) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0046 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0016 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x003a=Splitter:B:32:0x003a, B:24:0x002d=Splitter:B:24:0x002d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object b(java.lang.String r4) {
        /*
            byte[] r4 = a((java.lang.String) r4)
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0037, ClassNotFoundException -> 0x002a, all -> 0x0027 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0037, ClassNotFoundException -> 0x002a, all -> 0x0027 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0024, ClassNotFoundException -> 0x0021, all -> 0x001f }
            r4.<init>(r1)     // Catch:{ IOException -> 0x0024, ClassNotFoundException -> 0x0021, all -> 0x001f }
            java.lang.Object r2 = r4.readObject()     // Catch:{ IOException -> 0x001d, ClassNotFoundException -> 0x001b }
            r1.close()     // Catch:{ Exception -> 0x0016 }
        L_0x0016:
            r4.close()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            r0 = r2
            goto L_0x003e
        L_0x001b:
            r2 = move-exception
            goto L_0x002d
        L_0x001d:
            r2 = move-exception
            goto L_0x003a
        L_0x001f:
            r4 = move-exception
            goto L_0x0043
        L_0x0021:
            r2 = move-exception
            r4 = r0
            goto L_0x002d
        L_0x0024:
            r2 = move-exception
            r4 = r0
            goto L_0x003a
        L_0x0027:
            r4 = move-exception
            r1 = r0
            goto L_0x0043
        L_0x002a:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x002d:
            r2.printStackTrace()     // Catch:{ all -> 0x003f }
        L_0x0030:
            r1.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            r4.close()     // Catch:{ Exception -> 0x003e }
            goto L_0x003e
        L_0x0037:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x003a:
            r2.printStackTrace()     // Catch:{ all -> 0x003f }
            goto L_0x0030
        L_0x003e:
            return r0
        L_0x003f:
            r0 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L_0x0043:
            r1.close()     // Catch:{ Exception -> 0x0046 }
        L_0x0046:
            r0.close()     // Catch:{ Exception -> 0x0049 }
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.b(java.lang.String):java.lang.Object");
    }

    public static boolean a(byte[] bArr, String str) {
        boolean z = true;
        OutputStream outputStream = null;
        try {
            OutputStream outputStream2 = new OutputStream(new FileOutputStream(str), 1);
            try {
                outputStream2.write(bArr);
                try {
                    outputStream2.close();
                } catch (Exception unused) {
                }
            } catch (IOException unused2) {
                outputStream = outputStream2;
                z = false;
                outputStream.close();
                return z;
            } catch (Throwable th) {
                th = th;
                outputStream = outputStream2;
                try {
                    outputStream.close();
                } catch (Exception unused3) {
                }
                throw th;
            }
        } catch (IOException unused4) {
            z = false;
            outputStream.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            outputStream.close();
            throw th;
        }
        return z;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream r2 = new com.xiaomi.youpin.common.util.crypto.Base64Coder$OutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r2.<init>(r3, r0)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r2.write(r4)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r0 = 1
            r2.close()     // Catch:{ Exception -> 0x0027 }
            goto L_0x0027
        L_0x001a:
            r4 = move-exception
            r1 = r2
            goto L_0x0020
        L_0x001d:
            r1 = r2
            goto L_0x0024
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            throw r4
        L_0x0024:
            r1.close()     // Catch:{ Exception -> 0x0027 }
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.a(java.lang.String, java.lang.String):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream} */
    /* JADX WARNING: type inference failed for: r0v0, types: [com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream, byte[]] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] c(java.lang.String r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0067 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0067 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0067 }
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0034
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ IOException -> 0x0067 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0067 }
            r3.<init>()     // Catch:{ IOException -> 0x0067 }
            java.lang.String r4 = "File is too big for this convenience method ("
            r3.append(r4)     // Catch:{ IOException -> 0x0067 }
            long r4 = r1.length()     // Catch:{ IOException -> 0x0067 }
            r3.append(r4)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r1 = " bytes)."
            r3.append(r1)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x0067 }
            r2.println(r1)     // Catch:{ IOException -> 0x0067 }
            r0.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            return r0
        L_0x0034:
            long r2 = r1.length()     // Catch:{ IOException -> 0x0067 }
            int r2 = (int) r2     // Catch:{ IOException -> 0x0067 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0067 }
            com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream r3 = new com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream     // Catch:{ IOException -> 0x0067 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0067 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0067 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0067 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0067 }
            r1 = 0
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x0067 }
            r4 = 0
        L_0x004c:
            r5 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.read(r2, r4, r5)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            if (r5 < 0) goto L_0x0056
            int r4 = r4 + r5
            goto L_0x004c
        L_0x0056:
            byte[] r5 = new byte[r4]     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.System.arraycopy(r2, r1, r5, r1, r4)     // Catch:{ IOException -> 0x0063, all -> 0x005f }
            r3.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0081
        L_0x005f:
            r7 = move-exception
            r0 = r3
            goto L_0x0082
        L_0x0062:
            r5 = r0
        L_0x0063:
            r0 = r3
            goto L_0x0068
        L_0x0065:
            r7 = move-exception
            goto L_0x0082
        L_0x0067:
            r5 = r0
        L_0x0068:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0065 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r2.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "Error decoding from file "
            r2.append(r3)     // Catch:{ all -> 0x0065 }
            r2.append(r7)     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0065 }
            r1.println(r7)     // Catch:{ all -> 0x0065 }
            r0.close()     // Catch:{ Exception -> 0x0081 }
        L_0x0081:
            return r5
        L_0x0082:
            r0.close()     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.c(java.lang.String):byte[]");
    }

    public static String d(String str) {
        return b(str, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        java.lang.System.err.println("Error encoding from file " + r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0049 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r6, int r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r1.<init>(r6)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            double r2 = (double) r2
            r4 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            java.lang.Double.isNaN(r2)
            double r2 = r2 * r4
            int r2 = (int) r2
            r3 = 40
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream r3 = new com.xiaomi.youpin.common.util.crypto.Base64Coder$InputStream     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r3.<init>(r4, r7)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r7 = 0
            r1 = 0
        L_0x002f:
            r4 = 4096(0x1000, float:5.74E-42)
            int r4 = r3.read(r2, r1, r4)     // Catch:{ IOException -> 0x0049 }
            if (r4 < 0) goto L_0x0039
            int r1 = r1 + r4
            goto L_0x002f
        L_0x0039:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x0049 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r2, r7, r1, r5)     // Catch:{ IOException -> 0x0049 }
            r3.close()     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            r0 = r4
            goto L_0x0062
        L_0x0045:
            r6 = move-exception
            r3 = r0
            goto L_0x0064
        L_0x0048:
            r3 = r0
        L_0x0049:
            java.io.PrintStream r7 = java.lang.System.err     // Catch:{ all -> 0x0063 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0063 }
            r1.<init>()     // Catch:{ all -> 0x0063 }
            java.lang.String r2 = "Error encoding from file "
            r1.append(r2)     // Catch:{ all -> 0x0063 }
            r1.append(r6)     // Catch:{ all -> 0x0063 }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x0063 }
            r7.println(r6)     // Catch:{ all -> 0x0063 }
            r3.close()     // Catch:{ Exception -> 0x0062 }
        L_0x0062:
            return r0
        L_0x0063:
            r6 = move-exception
        L_0x0064:
            r3.close()     // Catch:{ Exception -> 0x0067 }
        L_0x0067:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.b(java.lang.String, int):java.lang.String");
    }

    public static void b(String str, String str2) {
        String d2 = d(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream2.write(d2.getBytes("US-ASCII"));
            } catch (IOException e2) {
                e = e2;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    e.printStackTrace();
                    bufferedOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                bufferedOutputStream.close();
                throw th;
            }
            try {
                bufferedOutputStream2.close();
            } catch (Exception unused2) {
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            bufferedOutputStream.close();
        }
    }

    public static void c(String str, String str2) {
        byte[] c2 = c(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream2.write(c2);
            } catch (IOException e2) {
                e = e2;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    e.printStackTrace();
                    bufferedOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                bufferedOutputStream.close();
                throw th;
            }
            try {
                bufferedOutputStream2.close();
            } catch (Exception unused2) {
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            bufferedOutputStream.close();
        }
    }

    public static class InputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f23294a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private int f;
        private boolean g;
        private int h;
        private byte[] i;
        private byte[] j;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        public InputStream(java.io.InputStream inputStream, int i2) {
            super(inputStream);
            boolean z = true;
            this.g = (i2 & 8) != 8;
            this.f23294a = (i2 & 1) != 1 ? false : z;
            this.d = this.f23294a ? 4 : 3;
            this.c = new byte[this.d];
            this.b = -1;
            this.f = 0;
            this.h = i2;
            this.i = Base64Coder.c(i2);
            this.j = Base64Coder.d(i2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[LOOP:1: B:19:0x003b->B:25:0x0051, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0057 A[EDGE_INSN: B:58:0x0057->B:26:0x0057 ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read() throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10.b
                r1 = -1
                r2 = 0
                if (r0 >= 0) goto L_0x0071
                boolean r0 = r10.f23294a
                r3 = 4
                if (r0 == 0) goto L_0x0038
                r0 = 3
                byte[] r4 = new byte[r0]
                r5 = 0
                r6 = 0
            L_0x0010:
                if (r5 >= r0) goto L_0x0027
                java.io.InputStream r7 = r10.in     // Catch:{ IOException -> 0x0020 }
                int r7 = r7.read()     // Catch:{ IOException -> 0x0020 }
                if (r7 < 0) goto L_0x0023
                byte r7 = (byte) r7     // Catch:{ IOException -> 0x0020 }
                r4[r5] = r7     // Catch:{ IOException -> 0x0020 }
                int r6 = r6 + 1
                goto L_0x0023
            L_0x0020:
                r7 = move-exception
                if (r5 == 0) goto L_0x0026
            L_0x0023:
                int r5 = r5 + 1
                goto L_0x0010
            L_0x0026:
                throw r7
            L_0x0027:
                if (r6 <= 0) goto L_0x0037
                r5 = 0
                byte[] r7 = r10.c
                r8 = 0
                int r9 = r10.h
                byte[] unused = com.xiaomi.youpin.common.util.crypto.Base64Coder.b(r4, r5, r6, r7, r8, r9)
                r10.b = r2
                r10.e = r3
                goto L_0x0071
            L_0x0037:
                return r1
            L_0x0038:
                byte[] r0 = new byte[r3]
                r4 = 0
            L_0x003b:
                if (r4 >= r3) goto L_0x0057
            L_0x003d:
                java.io.InputStream r5 = r10.in
                int r5 = r5.read()
                if (r5 < 0) goto L_0x004e
                byte[] r6 = r10.j
                r7 = r5 & 127(0x7f, float:1.78E-43)
                byte r6 = r6[r7]
                r7 = -5
                if (r6 <= r7) goto L_0x003d
            L_0x004e:
                if (r5 >= 0) goto L_0x0051
                goto L_0x0057
            L_0x0051:
                byte r5 = (byte) r5
                r0[r4] = r5
                int r4 = r4 + 1
                goto L_0x003b
            L_0x0057:
                if (r4 != r3) goto L_0x0066
                byte[] r3 = r10.c
                int r4 = r10.h
                int r0 = com.xiaomi.youpin.common.util.crypto.Base64Coder.b(r0, r2, r3, r2, r4)
                r10.e = r0
                r10.b = r2
                goto L_0x0071
            L_0x0066:
                if (r4 != 0) goto L_0x0069
                return r1
            L_0x0069:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Improperly padded Base64Coder input."
                r0.<init>(r1)
                throw r0
            L_0x0071:
                int r0 = r10.b
                if (r0 < 0) goto L_0x00aa
                int r0 = r10.b
                int r3 = r10.e
                if (r0 < r3) goto L_0x007c
                return r1
            L_0x007c:
                boolean r0 = r10.f23294a
                if (r0 == 0) goto L_0x008f
                boolean r0 = r10.g
                if (r0 == 0) goto L_0x008f
                int r0 = r10.f
                r3 = 76
                if (r0 < r3) goto L_0x008f
                r10.f = r2
                r0 = 10
                return r0
            L_0x008f:
                int r0 = r10.f
                int r0 = r0 + 1
                r10.f = r0
                byte[] r0 = r10.c
                int r2 = r10.b
                int r3 = r2 + 1
                r10.b = r3
                byte r0 = r0[r2]
                int r2 = r10.b
                int r3 = r10.d
                if (r2 < r3) goto L_0x00a7
                r10.b = r1
            L_0x00a7:
                r0 = r0 & 255(0xff, float:3.57E-43)
                return r0
            L_0x00aa:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "YouPinError in Base64Coder code reading stream."
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.Base64Coder.InputStream.read():int");
        }

        public int read(byte[] bArr, int i2, int i3) throws IOException {
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i2 + i4] = (byte) read;
                    i4++;
                } else if (i4 == 0) {
                    return -1;
                }
            }
            return i4;
        }
    }

    public static class OutputStream extends FilterOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f23295a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private boolean f;
        private byte[] g;
        private boolean h;
        private int i;
        private byte[] j;
        private byte[] k;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i2) {
            super(outputStream);
            boolean z = true;
            this.f = (i2 & 8) != 8;
            this.f23295a = (i2 & 1) != 1 ? false : z;
            this.d = this.f23295a ? 3 : 4;
            this.c = new byte[this.d];
            this.b = 0;
            this.e = 0;
            this.h = false;
            this.g = new byte[4];
            this.i = i2;
            this.j = Base64Coder.c(i2);
            this.k = Base64Coder.d(i2);
        }

        public void write(int i2) throws IOException {
            if (this.h) {
                this.out.write(i2);
            } else if (this.f23295a) {
                byte[] bArr = this.c;
                int i3 = this.b;
                this.b = i3 + 1;
                bArr[i3] = (byte) i2;
                if (this.b >= this.d) {
                    this.out.write(Base64Coder.b(this.g, this.c, this.d, this.i));
                    this.e += 4;
                    if (this.f && this.e >= 76) {
                        this.out.write(10);
                        this.e = 0;
                    }
                    this.b = 0;
                }
            } else {
                int i4 = i2 & 127;
                if (this.k[i4] > -5) {
                    byte[] bArr2 = this.c;
                    int i5 = this.b;
                    this.b = i5 + 1;
                    bArr2[i5] = (byte) i2;
                    if (this.b >= this.d) {
                        this.out.write(this.g, 0, Base64Coder.b(this.c, 0, this.g, 0, this.i));
                        this.b = 0;
                    }
                } else if (this.k[i4] != -5) {
                    throw new IOException("Invalid character in Base64Coder data.");
                }
            }
        }

        public void write(byte[] bArr, int i2, int i3) throws IOException {
            if (this.h) {
                this.out.write(bArr, i2, i3);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                write(bArr[i2 + i4]);
            }
        }

        public void a() throws IOException {
            if (this.b <= 0) {
                return;
            }
            if (this.f23295a) {
                this.out.write(Base64Coder.b(this.g, this.c, this.b, this.i));
                this.b = 0;
                return;
            }
            throw new IOException("Base64Coder input not properly padded.");
        }

        public void close() throws IOException {
            a();
            super.close();
            this.c = null;
            this.out = null;
        }

        public void b() throws IOException {
            a();
            this.h = true;
        }

        public void c() {
            this.h = false;
        }
    }
}
