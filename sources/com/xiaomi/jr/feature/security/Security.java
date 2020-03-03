package com.xiaomi.jr.feature.security;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.http.utils.SignatureUtils;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import java.util.Map;

@Feature("Security")
public class Security extends HybridFeature {

    private static class SignParam {
        @SerializedName("method")

        /* renamed from: a  reason: collision with root package name */
        String f10423a;
        @SerializedName("url")
        String b;
        @SerializedName("params")
        Map<String, String> c;

        private SignParam() {
        }
    }

    @Action(paramClazz = String.class)
    public Response makeMd5(Request<String> request) {
        return new Response(HashUtils.a(request.c()));
    }

    @Action(paramClazz = SignParam.class)
    public Response makeSignature(Request<SignParam> request) {
        return new Response(SignatureUtils.a(request.c().c, request.c().b, request.c().f10423a));
    }
}
