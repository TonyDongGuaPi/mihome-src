package com.amap.api.services.a;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.common.base.Ascii;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class f {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f4422a = (!f.class.desiredAssertionStatus());
    private static final byte[] b = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
    private static final byte[] c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -9, -9, -9, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] d = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, 45, 95};
    private static final byte[] e = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -9, -9, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, Constants.TagName.CARD_APP_ACTIVATION_STATUS, -9, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] f = {45, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 95, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE};
    private static final byte[] g = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, -9, -9, -9, -9, Constants.TagName.ORDER_RANGE_TYPE, -9, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, Constants.TagName.CARD_APP_ACTIVATION_STATUS, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return d;
        }
        if ((i & 32) == 32) {
            return f;
        }
        return b;
    }

    /* access modifiers changed from: private */
    public static final byte[] c(int i) {
        if ((i & 16) == 16) {
            return e;
        }
        if ((i & 32) == 32) {
            return g;
        }
        return c;
    }

    private f() {
    }

    /* access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] b2 = b(i4);
        int i5 = 0;
        int i6 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        int i7 = i6 | i5;
        switch (i2) {
            case 1:
                bArr2[i3] = b2[i7 >>> 18];
                bArr2[i3 + 1] = b2[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = Constants.TagName.CARD_APP_VERSION;
                bArr2[i3 + 3] = Constants.TagName.CARD_APP_VERSION;
                return bArr2;
            case 2:
                bArr2[i3] = b2[i7 >>> 18];
                bArr2[i3 + 1] = b2[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = b2[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = Constants.TagName.CARD_APP_VERSION;
                return bArr2;
            case 3:
                bArr2[i3] = b2[i7 >>> 18];
                bArr2[i3 + 1] = b2[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = b2[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = b2[i7 & 63];
                return bArr2;
            default:
                return bArr2;
        }
    }

    public static String a(byte[] bArr) {
        String str;
        try {
            str = a(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (f4422a) {
                str = null;
            } else {
                throw new AssertionError(e2.getMessage());
            }
        }
        if (f4422a || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] b2 = b(bArr, i, i2, i3);
        try {
            return new String(b2, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(b2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX WARNING: type inference failed for: r2v16, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v21 */
    /* JADX WARNING: type inference failed for: r2v22 */
    /* JADX WARNING: type inference failed for: r2v23 */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:13|14|15|16|17|18|19|20|21|22|23|25) */
    /* JADX WARNING: Can't wrap try/catch for region: R(17:8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|25) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:31|32|45|46|47|48|49|50|51) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0034 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x005b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x005e */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] b(byte[] r17, int r18, int r19, int r20) throws java.io.IOException {
        /*
            r0 = r17
            r7 = r18
            r8 = r19
            if (r0 == 0) goto L_0x011c
            if (r7 < 0) goto L_0x0105
            if (r8 < 0) goto L_0x00ee
            int r1 = r7 + r8
            int r2 = r0.length
            r9 = 1
            if (r1 > r2) goto L_0x00ca
            r1 = r20 & 2
            if (r1 == 0) goto L_0x0062
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0051, all -> 0x004d }
            r2.<init>()     // Catch:{ IOException -> 0x0051, all -> 0x004d }
            com.amap.api.services.a.f$a r3 = new com.amap.api.services.a.f$a     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            r4 = r20 | 1
            r3.<init>(r2, r4)     // Catch:{ IOException -> 0x0048, all -> 0x0045 }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r4.write(r0, r7, r8)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r4.close()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r4.close()     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            r3.close()     // Catch:{ Exception -> 0x0034 }
        L_0x0034:
            r2.close()     // Catch:{ Exception -> 0x0037 }
        L_0x0037:
            byte[] r0 = r2.toByteArray()
            return r0
        L_0x003c:
            r0 = move-exception
            goto L_0x0057
        L_0x003e:
            r0 = move-exception
            goto L_0x004b
        L_0x0040:
            r0 = move-exception
            goto L_0x0058
        L_0x0042:
            r0 = move-exception
            r4 = r1
            goto L_0x004b
        L_0x0045:
            r0 = move-exception
            r3 = r1
            goto L_0x0058
        L_0x0048:
            r0 = move-exception
            r3 = r1
            r4 = r3
        L_0x004b:
            r1 = r2
            goto L_0x0054
        L_0x004d:
            r0 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x0058
        L_0x0051:
            r0 = move-exception
            r3 = r1
            r4 = r3
        L_0x0054:
            throw r0     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            r2 = r1
        L_0x0057:
            r1 = r4
        L_0x0058:
            r1.close()     // Catch:{ Exception -> 0x005b }
        L_0x005b:
            r3.close()     // Catch:{ Exception -> 0x005e }
        L_0x005e:
            r2.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            throw r0
        L_0x0062:
            r1 = r20 & 8
            if (r1 == 0) goto L_0x0068
            r11 = 1
            goto L_0x0069
        L_0x0068:
            r11 = 0
        L_0x0069:
            int r1 = r8 / 3
            r12 = 4
            int r1 = r1 * 4
            int r2 = r8 % 3
            if (r2 <= 0) goto L_0x0074
            r2 = 4
            goto L_0x0075
        L_0x0074:
            r2 = 0
        L_0x0075:
            int r1 = r1 + r2
            if (r11 == 0) goto L_0x007b
            int r2 = r1 / 76
            int r1 = r1 + r2
        L_0x007b:
            byte[] r13 = new byte[r1]
            int r14 = r8 + -2
            r6 = 0
            r15 = 0
            r16 = 0
        L_0x0083:
            if (r6 >= r14) goto L_0x00ab
            int r2 = r6 + r7
            r3 = 3
            r1 = r17
            r4 = r13
            r5 = r15
            r10 = r6
            r6 = r20
            a(r1, r2, r3, r4, r5, r6)
            int r1 = r16 + 4
            if (r11 == 0) goto L_0x00a5
            r2 = 76
            if (r1 < r2) goto L_0x00a5
            int r1 = r15 + 4
            r2 = 10
            r13[r1] = r2
            int r15 = r15 + 1
            r16 = 0
            goto L_0x00a7
        L_0x00a5:
            r16 = r1
        L_0x00a7:
            int r6 = r10 + 3
            int r15 = r15 + r12
            goto L_0x0083
        L_0x00ab:
            r10 = r6
            if (r10 >= r8) goto L_0x00bd
            int r2 = r10 + r7
            int r3 = r8 - r10
            r1 = r17
            r4 = r13
            r5 = r15
            r6 = r20
            a(r1, r2, r3, r4, r5, r6)
            int r15 = r15 + 4
        L_0x00bd:
            r0 = r15
            int r1 = r13.length
            int r1 = r1 - r9
            if (r0 > r1) goto L_0x00c9
            byte[] r1 = new byte[r0]
            r2 = 0
            java.lang.System.arraycopy(r13, r2, r1, r2, r0)
            return r1
        L_0x00c9:
            return r13
        L_0x00ca:
            r2 = 0
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            r3[r2] = r4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r19)
            r3[r9] = r2
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2 = 2
            r3[r2] = r0
            java.lang.String r0 = "Cannot have offset of %d and length of %d with array of length %d"
            java.lang.String r0 = java.lang.String.format(r0, r3)
            r1.<init>(r0)
            throw r1
        L_0x00ee:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot have length offset: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0105:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot have negative offset: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x011c:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Cannot serialize a null array."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.f.b(byte[], int, int, int):byte[]");
    }

    /* access modifiers changed from: private */
    public static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        } else if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(i2)}));
        } else {
            byte[] c2 = c(i3);
            int i6 = i + 2;
            if (bArr[i6] == 61) {
                bArr2[i2] = (byte) ((((c2[bArr[i + 1]] & 255) << 12) | ((c2[bArr[i]] & 255) << 18)) >>> 16);
                return 1;
            } else if (bArr[i4] == 61) {
                int i7 = (c2[bArr[i + 1]] & 255) << 12;
                int i8 = ((c2[bArr[i6]] & 255) << 6) | i7 | ((c2[bArr[i]] & 255) << 18);
                bArr2[i2] = (byte) (i8 >>> 16);
                bArr2[i2 + 1] = (byte) (i8 >>> 8);
                return 2;
            } else {
                int i9 = (c2[bArr[i + 1]] & 255) << 12;
                byte b2 = (c2[bArr[i4]] & 255) | i9 | ((c2[bArr[i]] & 255) << 18) | ((c2[bArr[i6]] & 255) << 6);
                bArr2[i2] = (byte) (b2 >> 16);
                bArr2[i2 + 1] = (byte) (b2 >> 8);
                bArr2[i5] = (byte) b2;
                return 3;
            }
        }
    }

    public static byte[] c(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (i < 0 || (i4 = i + i2) > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (i2 == 0) {
            return new byte[0];
        } else {
            if (i2 >= 4) {
                byte[] c2 = c(i3);
                byte[] bArr2 = new byte[((i2 * 3) / 4)];
                byte[] bArr3 = new byte[4];
                int i5 = 0;
                int i6 = 0;
                while (i < i4) {
                    byte b2 = c2[bArr[i] & 255];
                    if (b2 >= -5) {
                        if (b2 >= -1) {
                            int i7 = i5 + 1;
                            bArr3[i5] = bArr[i];
                            if (i7 > 3) {
                                i6 += b(bArr3, 0, bArr2, i6, i3);
                                if (bArr[i] == 61) {
                                    break;
                                }
                                i5 = 0;
                            } else {
                                i5 = i7;
                            }
                        }
                        i++;
                    } else {
                        throw new IOException(String.format("Bad Base64Util input character decimal %d in array position %d", new Object[]{Integer.valueOf(bArr[i] & 255), Integer.valueOf(i)}));
                    }
                }
                byte[] bArr4 = new byte[i6];
                System.arraycopy(bArr2, 0, bArr4, 0, i6);
                return bArr4;
            }
            throw new IllegalArgumentException("Base64Util-encoded string must have at least four characters, but length specified was " + i2);
        }
    }

    public static byte[] a(String str) throws IOException {
        return a(str, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.util.zip.GZIPInputStream} */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:58|59|60|61|62|63|64|65|66|67) */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:21|22|(3:23|24|(1:26)(1:72))|27|28|29|30|31|32|33|34|78) */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:43|44|45|50|51|52|53|54|55|56|57|79) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:50|51|52|53|54|55|56|57|79) */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0059 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x005c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x007f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x0082 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x008c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x008f */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L_0x0094
            java.lang.String r0 = "US-ASCII"
            byte[] r0 = r5.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x0009 }
            goto L_0x000d
        L_0x0009:
            byte[] r0 = r5.getBytes()
        L_0x000d:
            int r5 = r0.length
            r1 = 0
            byte[] r5 = c(r0, r1, r5, r6)
            r0 = 4
            r6 = r6 & r0
            r2 = 1
            if (r6 == 0) goto L_0x001a
            r6 = 1
            goto L_0x001b
        L_0x001a:
            r6 = 0
        L_0x001b:
            if (r5 == 0) goto L_0x0093
            int r3 = r5.length
            if (r3 < r0) goto L_0x0093
            if (r6 != 0) goto L_0x0093
            byte r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r0 = r5[r2]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto L_0x0093
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0076, all -> 0x0072 }
            r2.<init>()     // Catch:{ IOException -> 0x0076, all -> 0x0072 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r3.<init>(r5)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0067, all -> 0x0065 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0067, all -> 0x0065 }
        L_0x0048:
            int r0 = r4.read(r6)     // Catch:{ IOException -> 0x0063, all -> 0x0061 }
            if (r0 < 0) goto L_0x0052
            r2.write(r6, r1, r0)     // Catch:{ IOException -> 0x0063, all -> 0x0061 }
            goto L_0x0048
        L_0x0052:
            byte[] r6 = r2.toByteArray()     // Catch:{ IOException -> 0x0063, all -> 0x0061 }
            r2.close()     // Catch:{ Exception -> 0x0059 }
        L_0x0059:
            r4.close()     // Catch:{ Exception -> 0x005c }
        L_0x005c:
            r3.close()     // Catch:{ Exception -> 0x005f }
        L_0x005f:
            r5 = r6
            goto L_0x0093
        L_0x0061:
            r5 = move-exception
            goto L_0x0088
        L_0x0063:
            r6 = move-exception
            goto L_0x0070
        L_0x0065:
            r5 = move-exception
            goto L_0x0089
        L_0x0067:
            r6 = move-exception
            r4 = r0
            goto L_0x0070
        L_0x006a:
            r5 = move-exception
            r3 = r0
            goto L_0x0089
        L_0x006d:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L_0x0070:
            r0 = r2
            goto L_0x0079
        L_0x0072:
            r5 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x0089
        L_0x0076:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L_0x0079:
            r6.printStackTrace()     // Catch:{ all -> 0x0086 }
            r0.close()     // Catch:{ Exception -> 0x007f }
        L_0x007f:
            r4.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r3.close()     // Catch:{ Exception -> 0x0093 }
            goto L_0x0093
        L_0x0086:
            r5 = move-exception
            r2 = r0
        L_0x0088:
            r0 = r4
        L_0x0089:
            r2.close()     // Catch:{ Exception -> 0x008c }
        L_0x008c:
            r0.close()     // Catch:{ Exception -> 0x008f }
        L_0x008f:
            r3.close()     // Catch:{ Exception -> 0x0092 }
        L_0x0092:
            throw r5
        L_0x0093:
            return r5
        L_0x0094:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Input string was null."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.f.a(java.lang.String, int):byte[]");
    }

    public static class a extends FilterOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4423a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private boolean f;
        private byte[] g;
        private boolean h;
        private int i;
        private byte[] j;

        public a(OutputStream outputStream, int i2) {
            super(outputStream);
            boolean z = true;
            this.f = (i2 & 8) != 0;
            this.f4423a = (i2 & 1) == 0 ? false : z;
            this.d = this.f4423a ? 3 : 4;
            this.c = new byte[this.d];
            this.b = 0;
            this.e = 0;
            this.h = false;
            this.g = new byte[4];
            this.i = i2;
            this.j = f.c(i2);
        }

        public void write(int i2) throws IOException {
            if (this.h) {
                this.out.write(i2);
            } else if (this.f4423a) {
                byte[] bArr = this.c;
                int i3 = this.b;
                this.b = i3 + 1;
                bArr[i3] = (byte) i2;
                if (this.b >= this.d) {
                    this.out.write(f.b(this.g, this.c, this.d, this.i));
                    this.e += 4;
                    if (this.f && this.e >= 76) {
                        this.out.write(10);
                        this.e = 0;
                    }
                    this.b = 0;
                }
            } else {
                int i4 = i2 & 127;
                if (this.j[i4] > -5) {
                    byte[] bArr2 = this.c;
                    int i5 = this.b;
                    this.b = i5 + 1;
                    bArr2[i5] = (byte) i2;
                    if (this.b >= this.d) {
                        this.out.write(this.g, 0, f.b(this.c, 0, this.g, 0, this.i));
                        this.b = 0;
                    }
                } else if (this.j[i4] != -5) {
                    throw new IOException("Invalid character in Base64Util data.");
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
            if (this.f4423a) {
                this.out.write(f.b(this.g, this.c, this.b, this.i));
                this.b = 0;
                return;
            }
            throw new IOException("Base64Util input not properly padded.");
        }

        public void close() throws IOException {
            a();
            super.close();
            this.c = null;
            this.out = null;
        }
    }
}
