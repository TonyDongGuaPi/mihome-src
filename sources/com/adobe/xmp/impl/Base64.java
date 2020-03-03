package com.adobe.xmp.impl;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class Base64 {

    /* renamed from: a  reason: collision with root package name */
    private static final byte f681a = -1;
    private static final byte b = -2;
    private static final byte c = -3;
    private static byte[] d = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
    private static byte[] e = new byte[255];

    static {
        for (int i = 0; i < 255; i++) {
            e[i] = -1;
        }
        for (int i2 = 0; i2 < d.length; i2++) {
            e[d[i2]] = (byte) i2;
        }
        e[9] = -2;
        e[10] = -2;
        e[13] = -2;
        e[32] = -2;
        e[61] = -3;
    }

    public static final String a(String str) {
        return new String(a(str.getBytes()));
    }

    public static final byte[] a(byte[] bArr) {
        return a(bArr, 0);
    }

    public static final byte[] a(byte[] bArr, int i) {
        int i2 = (i / 4) * 4;
        if (i2 < 0) {
            i2 = 0;
        }
        int length = ((bArr.length + 2) / 3) * 4;
        if (i2 > 0) {
            length += (length - 1) / i2;
        }
        byte[] bArr2 = new byte[length];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 + 3 <= bArr.length) {
            int i6 = i3 + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i3] & 255) << 16) | ((bArr[i6] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | ((bArr[i7] & 255) << 0);
            int i11 = i4 + 1;
            bArr2[i4] = d[(i10 & 16515072) >> 18];
            int i12 = i11 + 1;
            bArr2[i11] = d[(i10 & 258048) >> 12];
            int i13 = i12 + 1;
            bArr2[i12] = d[(i10 & 4032) >> 6];
            i4 = i13 + 1;
            bArr2[i13] = d[i10 & 63];
            i5 += 4;
            if (i4 < length && i2 > 0 && i5 % i2 == 0) {
                bArr2[i4] = 10;
                i4++;
            }
            i3 = i9;
        }
        if (bArr.length - i3 == 2) {
            int i14 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16);
            int i15 = i4 + 1;
            bArr2[i4] = d[(i14 & 16515072) >> 18];
            int i16 = i15 + 1;
            bArr2[i15] = d[(i14 & 258048) >> 12];
            bArr2[i16] = d[(i14 & 4032) >> 6];
            bArr2[i16 + 1] = Constants.TagName.CARD_APP_VERSION;
        } else if (bArr.length - i3 == 1) {
            int i17 = (bArr[i3] & 255) << 16;
            int i18 = i4 + 1;
            bArr2[i4] = d[(i17 & 16515072) >> 18];
            int i19 = i18 + 1;
            bArr2[i18] = d[(i17 & 258048) >> 12];
            bArr2[i19] = Constants.TagName.CARD_APP_VERSION;
            bArr2[i19 + 1] = Constants.TagName.CARD_APP_VERSION;
        }
        return bArr2;
    }

    public static final String b(String str) {
        return new String(b(str.getBytes()));
    }

    public static final byte[] b(byte[] bArr) throws IllegalArgumentException {
        int i = 0;
        int i2 = 0;
        for (byte b2 : bArr) {
            byte b3 = e[b2];
            if (b3 >= 0) {
                bArr[i2] = b3;
                i2++;
            } else if (b3 == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (i2 > 0 && bArr[i2 - 1] == -3) {
            i2--;
        }
        byte[] bArr2 = new byte[((i2 * 3) / 4)];
        int i3 = 0;
        while (i < bArr2.length - 2) {
            int i4 = i3 + 1;
            bArr2[i] = (byte) (((bArr[i3] << 2) & 255) | ((bArr[i4] >>> 4) & 3));
            int i5 = i3 + 2;
            bArr2[i + 1] = (byte) (((bArr[i4] << 4) & 255) | ((bArr[i5] >>> 2) & 15));
            bArr2[i + 2] = (byte) (((bArr[i5] << 6) & 255) | (bArr[i3 + 3] & Constants.TagName.CARD_APP_ACTIVATION_STATUS));
            i3 += 4;
            i += 3;
        }
        if (i < bArr2.length) {
            bArr2[i] = (byte) (((bArr[i3] << 2) & 255) | ((bArr[i3 + 1] >>> 4) & 3));
        }
        int i6 = i + 1;
        if (i6 < bArr2.length) {
            bArr2[i6] = (byte) (((bArr[i3 + 2] >>> 2) & 15) | ((bArr[i3 + 1] << 4) & 255));
        }
        return bArr2;
    }
}
