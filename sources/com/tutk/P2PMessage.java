package com.tutk;

import com.xiaomi.smarthome.camera.P2pResponse;

public class P2PMessage {
    public byte[] data;
    public int msgId = 0;
    public int reqId;
    public int resId;
    public P2pResponse resp;

    public P2PMessage(int i, int i2, int i3, byte[] bArr, P2pResponse p2pResponse) {
        this.msgId = i;
        this.resp = p2pResponse;
        this.resId = i3;
        this.reqId = i2;
        this.data = bArr;
    }

    public P2PMessage(int i, int i2, byte[] bArr, P2pResponse p2pResponse) {
        this.msgId = i;
        this.resp = p2pResponse;
        this.resId = i2;
        this.reqId = i;
        this.data = bArr;
    }
}
