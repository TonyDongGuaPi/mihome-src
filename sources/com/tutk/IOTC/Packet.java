package com.tutk.IOTC;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;

public class Packet {
    public static final short byteArrayToShort(byte[] bArr, int i, boolean z) {
        if (z) {
            return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
        }
        return (short) (((bArr[i + 1] & 255) << 8) | (bArr[i] & 255));
    }

    public static final int byteArrayToInt(byte[] bArr, int i, boolean z) {
        if (z) {
            return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
        }
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static final long byteArrayToLong(byte[] bArr, int i, boolean z) {
        if (z) {
            return (long) ((bArr[i + 7] & 255) | ((bArr[i] & 255) << ScriptToolsConst.TagName.TagSerial) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << Constants.TagName.CARD_APP_BLANCE) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8));
        }
        return (long) (((bArr[i + 7] & 255) << ScriptToolsConst.TagName.TagSerial) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << Constants.TagName.CARD_APP_BLANCE) | ((bArr[i + 6] & 255) << 48));
    }

    public static final byte[] shortToByteArray(short s, boolean z) {
        if (z) {
            return new byte[]{(byte) (s >>> 8), (byte) s};
        }
        return new byte[]{(byte) s, (byte) (s >>> 8)};
    }

    public static final byte[] intToByteArray(int i, boolean z) {
        if (z) {
            return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
        }
        return new byte[]{(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }
}
