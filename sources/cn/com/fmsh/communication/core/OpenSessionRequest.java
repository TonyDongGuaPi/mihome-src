package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.algorithm.RSA;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

class OpenSessionRequest {
    private static final int CIPHERTEXT_OFFSET = 5;
    private static final int KEY_INDEX_OFFSET = 4;
    private static final int PLAIN_TEST_FIXED_LENGTH = 63;
    private static final int SECURITY_CODE_OFFSET = 63;
    public static final int TEMP_KEY_LENGTH = 16;
    private static final int TEMP_KEY_OFFSET = 47;
    private static final int TERMINAL_NUMBER_LENGTH = 32;
    private static final int TERMINAL_NUMBER_OFFSET = 8;
    public static final int TERMINAL_RANDOM_LENGTH = 8;
    private static final int TERMINAL_RANDOM_OFFSET = 0;
    private static final int TERMINAL_TIME_LENGTH = 7;
    private static final int TERMINAL_TIME_OFFSET = 40;
    private static final byte TERMINAL_TYPE_FIRST = 0;
    private static final int TERMINAL_TYPE_LENGTH = 2;
    private static final int TERMINAL_TYPE_OFFSET = 0;
    private byte[] append;
    private byte businessVersion;
    private byte[] exponent;
    private FMLog fmLog = LogFactory.getInstance().getLog();
    private byte keyIndex;
    private final String logTag = OpenSessionRequest.class.getName();
    private byte[] modulus;
    private byte[] securityCode;
    private byte[] tempKey;
    private byte[] terminalNumber;
    private byte[] terminalRandom;
    private byte[] terminalTime;
    private byte[] terminalType;

    OpenSessionRequest() {
    }

    public byte getBusinessVersion() {
        return this.businessVersion;
    }

    public void setBusinessVersion(byte b) {
        this.businessVersion = b;
    }

    public byte[] getTerminalType() {
        return this.terminalType;
    }

    public void setTerminalType(byte[] bArr) {
        this.terminalType = bArr;
    }

    public byte getKeyIndex() {
        return this.keyIndex;
    }

    public void setKeyIndex(byte b) {
        this.keyIndex = b;
    }

    public byte[] getTerminalRandom() {
        return this.terminalRandom;
    }

    public void setTerminalRandom(byte[] bArr) {
        this.terminalRandom = bArr;
    }

    public byte[] getTerminalNumber() {
        return this.terminalNumber;
    }

    public void setTerminalNumber(byte[] bArr) {
        this.terminalNumber = bArr;
    }

    public byte[] getTerminalTime() {
        return this.terminalTime;
    }

    public void setTerminalTime(byte[] bArr) {
        this.terminalTime = bArr;
    }

    public byte[] getTempKey() {
        return this.tempKey;
    }

    public void setTempKey(byte[] bArr) {
        this.tempKey = bArr;
    }

    public byte[] getSecurityCode() {
        return this.securityCode;
    }

    public void setSecurityCode(byte[] bArr) {
        this.securityCode = bArr;
    }

    public byte[] getAppend() {
        return this.append;
    }

    public void setAppend(byte[] bArr) {
        this.append = bArr;
    }

    public byte[] getModulus() {
        return this.modulus;
    }

    public void setModulus(byte[] bArr) {
        this.modulus = bArr;
    }

    public byte[] getExponent() {
        return this.exponent;
    }

    public void setExponent(byte[] bArr) {
        this.exponent = bArr;
    }

    private byte[] exportPlainText() {
        int length = (this.securityCode == null || this.securityCode.length <= 0) ? 1 : this.securityCode.length + 1;
        int i = 63 + length;
        int length2 = (this.append == null || this.append.length <= 0) ? 1 : this.append.length + 1;
        int i2 = i + length2 + 1;
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i3 + 0] = this.terminalRandom[i3];
        }
        if (this.terminalNumber == null || this.terminalNumber.length != 32) {
            if (this.fmLog != null) {
                this.fmLog.warn("OpenSessionRequest", "OpenSessionRequest toByte,终端编号数据不合法");
            }
            return null;
        }
        for (int i4 = 0; i4 < 32; i4++) {
            bArr[i4 + 8] = this.terminalNumber[i4];
        }
        if (this.terminalTime == null || this.terminalTime.length < 1) {
            this.terminalTime = FM_CN.string2Bcd(Util4Java.date2string("yyyyMMddHHmmss"));
        }
        if (this.terminalTime.length != 7) {
            if (this.fmLog != null) {
                this.fmLog.warn("OpenSessionRequest", "OpenSessionRequest toByte,终端时间数据不合法");
            }
            return null;
        }
        for (int i5 = 0; i5 < 7; i5++) {
            bArr[i5 + 40] = this.terminalTime[i5];
        }
        for (int i6 = 0; i6 < 16; i6++) {
            bArr[i6 + 47] = this.tempKey[i6];
        }
        if (length > 1) {
            bArr[63] = (byte) (length - 1);
            for (int i7 = 1; i7 < length; i7++) {
                bArr[i7 + 63] = this.securityCode[i7 - 1];
            }
        } else {
            bArr[63] = 0;
        }
        if (length2 > 1) {
            bArr[i] = (byte) (length2 - 1);
            for (int i8 = 1; i8 < length2; i8++) {
                bArr[i + i8] = this.append[i8 - 1];
            }
        } else {
            bArr[i] = 0;
        }
        bArr[i2 - 1] = BCCUtil.calculateBCC(bArr);
        return bArr;
    }

    public byte[] toBytes() {
        byte[] encrtyByPublic = RSA.encrtyByPublic(this.modulus, this.exponent, exportPlainText(), true);
        if (encrtyByPublic == null) {
            return null;
        }
        int length = encrtyByPublic.length;
        byte[] bArr = new byte[(length + 5)];
        if (this.terminalType.length != 2) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.debug(str, " cipherText:" + FM_Bytes.bytesToHexString(encrtyByPublic));
            }
            return null;
        }
        bArr[0] = 0;
        bArr[1] = this.terminalType[0];
        bArr[2] = this.terminalType[1];
        bArr[3] = getBusinessVersion();
        bArr[4] = this.keyIndex;
        for (int i = 0; i < length; i++) {
            bArr[i + 5] = encrtyByPublic[i];
        }
        return bArr;
    }
}
