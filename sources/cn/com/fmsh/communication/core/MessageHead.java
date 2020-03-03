package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.FM_Bytes;

public class MessageHead {
    public static final int MESSAGE_HEAD_LENGTH = 12;
    public static final long SERIAL_MAK = 4294967295L;
    private final int SECURITY_LEVEL_LENGTH = 2;
    private final int SECURITY_LEVEL_OFFSET = 2;
    private final int SERIAL_NUMBER_LENGTH = 4;
    private final int SERIAL_NUMBER_OFFSET = 4;
    private final int SESSION_NUMBER_LENGTH = 4;
    private final int SESSION_NUMBER_OFFSET = 8;
    private ControlWord controlWord = new ControlWord();
    private byte protocolVersion;
    private byte[] securityLevel = new byte[4];
    private long serialNumber = 0;
    private byte[] sessionNumber = new byte[4];

    public byte getProtocolVersion() {
        return this.protocolVersion;
    }

    public void setProtocolVersion(byte b) {
        this.protocolVersion = b;
    }

    public ControlWord getControlWord() {
        return this.controlWord;
    }

    public void setControlWord(ControlWord controlWord2) {
        this.controlWord = controlWord2;
    }

    public byte[] getSecurityLevel() {
        return this.securityLevel;
    }

    public void setSecurityLevel(byte[] bArr) {
        this.securityLevel = bArr;
    }

    public long getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(long j) {
        this.serialNumber = j;
    }

    public byte[] getSessionNumber() {
        return this.sessionNumber;
    }

    public void setSessionNumber(byte[] bArr) {
        this.sessionNumber = bArr;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[12];
        bArr[0] = this.protocolVersion;
        bArr[1] = this.controlWord.toBytes();
        for (int i = 0; i < 2; i++) {
            bArr[i + 2] = this.securityLevel[i];
        }
        byte[] longToBytes = FM_Bytes.longToBytes(this.serialNumber, 4);
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2 + 4] = longToBytes[i2];
        }
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i3 + 8] = this.sessionNumber[i3];
        }
        return bArr;
    }

    public void fromBytes(byte[] bArr) {
        if (bArr != null && bArr.length == 12) {
            this.protocolVersion = bArr[0];
            this.controlWord.fromBytes(bArr[1]);
            for (int i = 0; i < 2; i++) {
                this.securityLevel[i] = bArr[i + 2];
            }
            byte[] bArr2 = new byte[4];
            for (int i2 = 0; i2 < 4; i2++) {
                bArr2[i2] = bArr[i2 + 4];
            }
            this.serialNumber = FM_Bytes.bytesToLong(FM_Bytes.join(new byte[1], bArr2));
            for (int i3 = 0; i3 < 4; i3++) {
                this.sessionNumber[i3] = bArr[i3 + 8];
            }
        }
    }

    public enum SecurityLevel {
        PLAIN(0),
        CIPHER(1);
        
        private int value;

        private SecurityLevel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum CheckType {
        NOCHECK(0),
        MAC(1),
        CRC16(2);
        
        private int value;

        private CheckType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
