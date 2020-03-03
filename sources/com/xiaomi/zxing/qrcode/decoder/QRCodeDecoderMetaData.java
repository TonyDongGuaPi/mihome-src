package com.xiaomi.zxing.qrcode.decoder;

import com.xiaomi.zxing.ResultPoint;

public final class QRCodeDecoderMetaData {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f1762a;

    QRCodeDecoderMetaData(boolean z) {
        this.f1762a = z;
    }

    public boolean a() {
        return this.f1762a;
    }

    public void a(ResultPoint[] resultPointArr) {
        if (this.f1762a && resultPointArr != null && resultPointArr.length >= 3) {
            ResultPoint resultPoint = resultPointArr[0];
            resultPointArr[0] = resultPointArr[2];
            resultPointArr[2] = resultPoint;
        }
    }
}
