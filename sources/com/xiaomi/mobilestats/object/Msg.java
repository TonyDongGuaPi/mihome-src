package com.xiaomi.mobilestats.object;

public class Msg {
    private boolean cF;
    private String cG;
    private byte[] cH;

    public String getMsg() {
        return this.cG;
    }

    public byte[] getResponseBytes() {
        return this.cH;
    }

    public boolean isFlag() {
        return this.cF;
    }

    public void setFlag(boolean z) {
        this.cF = z;
    }

    public void setMsg(String str) {
        this.cG = str;
    }

    public void setResponseBytes(byte[] bArr) {
        this.cH = bArr;
    }
}
