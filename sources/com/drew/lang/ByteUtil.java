package com.drew.lang;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;

public class ByteUtil {
    public static int a(byte[] bArr, int i, boolean z) {
        if (z) {
            return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
        }
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public static int b(byte[] bArr, int i, boolean z) {
        if (z) {
            return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
        }
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long c(byte[] bArr, int i, boolean z) {
        if (z) {
            return (long) ((bArr[i + 7] & 255) | ((bArr[i] & 255) << ScriptToolsConst.TagName.TagSerial) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << Constants.TagName.CARD_APP_BLANCE) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8));
        }
        return (long) (((bArr[i + 7] & 255) << ScriptToolsConst.TagName.TagSerial) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << Constants.TagName.CARD_APP_BLANCE) | ((bArr[i + 6] & 255) << 48));
    }
}
