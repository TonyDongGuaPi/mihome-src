package cn.com.fmsh.tsm.business.bean;

public class IssuerPrepareResult {
    private byte[] failDesc;
    private byte[] sir;

    public byte[] getSir() {
        return this.sir;
    }

    public void setSir(byte[] bArr) {
        this.sir = bArr;
    }

    public byte[] getFailDesc() {
        return this.failDesc;
    }

    public void setFailDesc(byte[] bArr) {
        this.failDesc = bArr;
    }
}
