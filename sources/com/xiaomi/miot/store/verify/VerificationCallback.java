package com.xiaomi.miot.store.verify;

import com.xiaomi.verificationsdk.internal.VerifyResult;

public interface VerificationCallback {
    void a();

    void a(int i, String str);

    void a(VerifyResult verifyResult);
}
