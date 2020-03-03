package com.xiaomi.qrcode2;

public interface QrCodeCallback {
    void onFail(int i, String str);

    void onSuccess(String str);
}
