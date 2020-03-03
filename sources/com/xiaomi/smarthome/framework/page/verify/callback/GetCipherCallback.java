package com.xiaomi.smarthome.framework.page.verify.callback;

import javax.crypto.Cipher;

public interface GetCipherCallback {
    void onGetCipherError(int i, String str);

    void onGetCipherSuccess(Cipher cipher);

    void onGetResetCipherSuccess(Cipher cipher);
}
