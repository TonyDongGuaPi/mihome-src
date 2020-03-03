package cn.com.fmsh.communication.core;

public class TerminalInfo {
    private byte[] append;
    private byte[] exponent;
    private byte keyIndex;
    private byte[] modulus;
    private byte[] securityCode;
    private byte[] terminalNumber;
    private byte[] terminalType;

    public byte[] getTerminalType() {
        return this.terminalType;
    }

    public void setTerminalType(byte[] bArr) {
        this.terminalType = bArr;
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

    public byte getKeyIndex() {
        return this.keyIndex;
    }

    public void setKeyIndex(byte b) {
        this.keyIndex = b;
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

    public byte[] getTerminalNumber() {
        return this.terminalNumber;
    }

    public void setTerminalNumber(byte[] bArr) {
        this.terminalNumber = bArr;
    }
}
