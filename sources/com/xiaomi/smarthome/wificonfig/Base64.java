package com.xiaomi.smarthome.wificonfig;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class Base64 {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f22829a = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
    private static final byte[] b = new byte[128];

    static {
        for (int i = 0; i < 128; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) ((i4 - 48) + 52);
        }
        b[43] = Constants.TagName.CARD_BUSINESS_ORDER_STATUS;
        b[47] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length % 3;
        if (length == 0) {
            bArr2 = new byte[((bArr.length * 4) / 3)];
        } else {
            bArr2 = new byte[(((bArr.length / 3) + 1) * 4)];
        }
        int length2 = bArr.length - length;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            byte b2 = bArr[i] & 255;
            byte b3 = bArr[i + 1] & 255;
            byte b4 = bArr[i + 2] & 255;
            bArr2[i2] = f22829a[(b2 >>> 2) & 63];
            bArr2[i2 + 1] = f22829a[((b2 << 4) | (b3 >>> 4)) & 63];
            bArr2[i2 + 2] = f22829a[((b3 << 2) | (b4 >>> 6)) & 63];
            bArr2[i2 + 3] = f22829a[b4 & Constants.TagName.CARD_APP_ACTIVATION_STATUS];
            i += 3;
            i2 += 4;
        }
        switch (length) {
            case 1:
                byte b5 = bArr[bArr.length - 1] & 255;
                bArr2[bArr2.length - 4] = f22829a[(b5 >>> 2) & 63];
                bArr2[bArr2.length - 3] = f22829a[(b5 << 4) & 63];
                bArr2[bArr2.length - 2] = Constants.TagName.CARD_APP_VERSION;
                bArr2[bArr2.length - 1] = Constants.TagName.CARD_APP_VERSION;
                break;
            case 2:
                byte b6 = bArr[bArr.length - 2] & 255;
                byte b7 = bArr[bArr.length - 1] & 255;
                bArr2[bArr2.length - 4] = f22829a[(b6 >>> 2) & 63];
                bArr2[bArr2.length - 3] = f22829a[((b6 << 4) | (b7 >>> 4)) & 63];
                bArr2[bArr2.length - 2] = f22829a[(b7 << 2) & 63];
                bArr2[bArr2.length - 1] = Constants.TagName.CARD_APP_VERSION;
                break;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2;
        byte[] c = c(bArr);
        if (c[c.length - 2] == 61) {
            bArr2 = new byte[((((c.length / 4) - 1) * 3) + 1)];
        } else if (c[c.length - 1] == 61) {
            bArr2 = new byte[((((c.length / 4) - 1) * 3) + 2)];
        } else {
            bArr2 = new byte[((c.length / 4) * 3)];
        }
        int i = 0;
        int i2 = 0;
        while (i < c.length - 4) {
            byte b2 = b[c[i]];
            byte b3 = b[c[i + 1]];
            byte b4 = b[c[i + 2]];
            byte b5 = b[c[i + 3]];
            bArr2[i2] = (byte) ((b2 << 2) | (b3 >> 4));
            bArr2[i2 + 1] = (byte) ((b3 << 4) | (b4 >> 2));
            bArr2[i2 + 2] = (byte) ((b4 << 6) | b5);
            i += 4;
            i2 += 3;
        }
        if (c[c.length - 2] == 61) {
            byte b6 = b[c[c.length - 4]];
            bArr2[bArr2.length - 1] = (byte) ((b[c[c.length - 3]] >> 4) | (b6 << 2));
        } else if (c[c.length - 1] == 61) {
            byte b7 = b[c[c.length - 4]];
            byte b8 = b[c[c.length - 3]];
            byte b9 = b[c[c.length - 2]];
            bArr2[bArr2.length - 2] = (byte) ((b7 << 2) | (b8 >> 4));
            bArr2[bArr2.length - 1] = (byte) ((b9 >> 2) | (b8 << 4));
        } else {
            byte b10 = b[c[c.length - 4]];
            byte b11 = b[c[c.length - 3]];
            byte b12 = b[c[c.length - 2]];
            byte b13 = b[c[c.length - 1]];
            bArr2[bArr2.length - 3] = (byte) ((b10 << 2) | (b11 >> 4));
            bArr2[bArr2.length - 2] = (byte) ((b11 << 4) | (b12 >> 2));
            bArr2[bArr2.length - 1] = (byte) (b13 | (b12 << 6));
        }
        return bArr2;
    }

    public static byte[] a(String str) {
        byte[] bArr;
        String b2 = b(str);
        if (b2.charAt(b2.length() - 2) == '=') {
            bArr = new byte[((((b2.length() / 4) - 1) * 3) + 1)];
        } else if (b2.charAt(b2.length() - 1) == '=') {
            bArr = new byte[((((b2.length() / 4) - 1) * 3) + 2)];
        } else {
            bArr = new byte[((b2.length() / 4) * 3)];
        }
        int i = 0;
        int i2 = 0;
        while (i < b2.length() - 4) {
            byte b3 = b[b2.charAt(i)];
            byte b4 = b[b2.charAt(i + 1)];
            byte b5 = b[b2.charAt(i + 2)];
            byte b6 = b[b2.charAt(i + 3)];
            bArr[i2] = (byte) ((b3 << 2) | (b4 >> 4));
            bArr[i2 + 1] = (byte) ((b4 << 4) | (b5 >> 2));
            bArr[i2 + 2] = (byte) ((b5 << 6) | b6);
            i += 4;
            i2 += 3;
        }
        if (b2.charAt(b2.length() - 2) == '=') {
            byte b7 = b[b2.charAt(b2.length() - 4)];
            bArr[bArr.length - 1] = (byte) ((b[b2.charAt(b2.length() - 3)] >> 4) | (b7 << 2));
        } else if (b2.charAt(b2.length() - 1) == '=') {
            byte b8 = b[b2.charAt(b2.length() - 4)];
            byte b9 = b[b2.charAt(b2.length() - 3)];
            byte b10 = b[b2.charAt(b2.length() - 2)];
            bArr[bArr.length - 2] = (byte) ((b8 << 2) | (b9 >> 4));
            bArr[bArr.length - 1] = (byte) ((b10 >> 2) | (b9 << 4));
        } else {
            byte b11 = b[b2.charAt(b2.length() - 4)];
            byte b12 = b[b2.charAt(b2.length() - 3)];
            byte b13 = b[b2.charAt(b2.length() - 2)];
            byte b14 = b[b2.charAt(b2.length() - 1)];
            bArr[bArr.length - 3] = (byte) ((b11 << 2) | (b12 >> 4));
            bArr[bArr.length - 2] = (byte) ((b12 << 4) | (b13 >> 2));
            bArr[bArr.length - 1] = (byte) (b14 | (b13 << 6));
        }
        return bArr;
    }

    private static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (a(bArr[i2])) {
                bArr2[i] = bArr[i2];
                i++;
            }
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        return bArr3;
    }

    private static String b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (a((byte) str.charAt(i))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    private static boolean a(byte b2) {
        if (b2 == 61) {
            return true;
        }
        return b2 >= 0 && b2 < 128 && b[b2] != -1;
    }
}
