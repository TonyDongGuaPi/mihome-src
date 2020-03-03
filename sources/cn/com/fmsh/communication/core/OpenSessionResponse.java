package cn.com.fmsh.communication.core;

import cn.com.fmsh.communication.exception.session.OpenSessionException;

public class OpenSessionResponse {
    public final int DATA_LENGTH = 32;
    private final int SERIAL_NUMBER_LENGTH = 4;
    private final int SERIAL_NUMBER_OFFSET = 28;
    public final int SESSION_KEY_LENGTH = 16;
    private final int SESSION_KEY_OFFSET = 12;
    private final int SESSION_NUMBER_LENGTH = 4;
    private final int SESSION_NUMBER_OFFSET = 8;
    private final int TERMINAL_RANDOM_LENGTH = 8;
    private final int TERMINAL_RANDOM_OFFSET = 0;
    private byte[] serialNumber = new byte[4];
    private byte[] sessionKey = new byte[16];
    private byte[] sessionNumber = new byte[4];
    private byte[] terminalRandom = new byte[8];

    public byte[] getTerminalRandom() {
        return this.terminalRandom;
    }

    public void setTerminalRandom(byte[] bArr) {
        this.terminalRandom = bArr;
    }

    public byte[] getSessionNumber() {
        return this.sessionNumber;
    }

    public void setSessionNumber(byte[] bArr) {
        this.sessionNumber = bArr;
    }

    public byte[] getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(byte[] bArr) {
        this.sessionKey = bArr;
    }

    public byte[] getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(byte[] bArr) {
        this.serialNumber = bArr;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 8; i++) {
            bArr[i + 0] = this.terminalRandom[i];
        }
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2 + 8] = this.sessionNumber[i2];
        }
        for (int i3 = 0; i3 < 16; i3++) {
            bArr[i3 + 12] = this.sessionKey[i3];
        }
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[i4 + 28] = this.serialNumber[i4];
        }
        return bArr;
    }

    public void fromBytes(byte[] bArr) throws OpenSessionException {
        if (bArr != null) {
            if (bArr.length == 32) {
                for (int i = 0; i < 8; i++) {
                    this.terminalRandom[i] = bArr[i + 0];
                }
                for (int i2 = 0; i2 < 4; i2++) {
                    this.sessionNumber[i2] = bArr[i2 + 8];
                }
                for (int i3 = 0; i3 < 16; i3++) {
                    this.sessionKey[i3] = bArr[i3 + 12];
                }
                for (int i4 = 0; i4 < 4; i4++) {
                    this.serialNumber[i4] = bArr[i4 + 28];
                }
                return;
            }
            throw new OpenSessionException("签到响应数据长度不合法");
        }
    }
}
