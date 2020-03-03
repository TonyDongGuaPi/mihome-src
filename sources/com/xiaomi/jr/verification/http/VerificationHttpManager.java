package com.xiaomi.jr.verification.http;

import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Response;

public class VerificationHttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11046a = "VerificationHttpManager";
    private static VerificationApi b;

    public static VerificationApi a() {
        if (b == null) {
            b = (VerificationApi) MifiHttpManager.a().a(VerificationApi.class);
        }
        return b;
    }

    public static <T> T a(Response<MiFiResponse<T>> response) {
        MiFiResponse body;
        if (response == null || !response.isSuccessful() || (body = response.body()) == null || !body.c()) {
            return null;
        }
        return body.d();
    }
}
