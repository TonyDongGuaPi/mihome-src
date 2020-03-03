package cn.com.fmsh.tsm.business.core;

public class ConfigKey {
    private int index = 0;
    private byte[] privateKey;
    private byte[] publicKey;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(byte[] bArr) {
        this.publicKey = bArr;
    }

    public byte[] getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(byte[] bArr) {
        this.privateKey = bArr;
    }
}
