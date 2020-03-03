package com.xiaomi.miot.store.verify;

import com.xiaomi.verificationsdk.internal.VerifyResult;

public class VerificationRemovableCallback implements VerificationCallback {

    /* renamed from: a  reason: collision with root package name */
    private VerificationCallback f11428a = null;

    public VerificationRemovableCallback(VerificationCallback verificationCallback) {
        this.f11428a = verificationCallback;
    }

    public void b() {
        this.f11428a = null;
    }

    public void a(VerifyResult verifyResult) {
        if (this.f11428a != null) {
            this.f11428a.a(verifyResult);
        }
    }

    public void a() {
        if (this.f11428a != null) {
            this.f11428a.a();
        }
    }

    public void a(int i, String str) {
        if (this.f11428a != null) {
            this.f11428a.a(i, str);
        }
    }
}
