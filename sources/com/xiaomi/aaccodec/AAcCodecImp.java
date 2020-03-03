package com.xiaomi.aaccodec;

import com.xiaomi.smarthome.camera.XmAAcCodec;

public class AAcCodecImp implements XmAAcCodec {
    AACDecodeEx aacDecode;
    AACEncodeEx aacEncode;

    public AAcCodecImp() {
        this.aacEncode = null;
        this.aacDecode = null;
        this.aacDecode = new AACDecodeEx();
        this.aacDecode.initial();
    }

    public AAcCodecImp(int i, int i2, int i3) {
        this.aacEncode = null;
        this.aacDecode = null;
        this.aacEncode = new AACEncodeEx();
        this.aacEncode.initial(i, i2, i3);
    }

    public int decode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.aacDecode != null) {
            return this.aacDecode.decode(bArr, i, i2, bArr2, i3);
        }
        return 0;
    }

    public byte[] encode(byte[] bArr, int i, int i2) {
        if (this.aacEncode != null) {
            return this.aacEncode.encode(bArr, i, i2);
        }
        return null;
    }

    public int getOneFrameSamplesCount() {
        return this.aacEncode != null ? 1 : 0;
    }

    public void release() {
        if (this.aacEncode != null) {
            this.aacEncode.release();
        }
        if (this.aacDecode != null) {
            this.aacDecode.release();
        }
    }
}
