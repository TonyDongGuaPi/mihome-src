package com.xiaomi.jr.mipay.pay.verifier;

import com.xiaomi.jr.mipay.common.http.MipayHttpManager;

public class VerifierManager {

    /* renamed from: a  reason: collision with root package name */
    private static VerifierApi f10966a = ((VerifierApi) MipayHttpManager.a().a(VerifierApi.class));

    public static VerifierApi a() {
        return f10966a;
    }
}
