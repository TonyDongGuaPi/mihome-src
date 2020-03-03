package com.xiaomi.jr.feature.antifraud;

import com.xiaomi.jr.antifraud.Tongdun;
import com.xiaomi.jr.antifraud.por.PorData;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;

@Feature("AntiFraud")
public class AntiFraud extends HybridFeature {
    @Action
    public Response getFraudmetrixBlackbox(Request request) {
        return new Response(Tongdun.b(HybridUtils.a(request)));
    }

    @Action
    public Response getPorData(Request request) {
        return new Response(new PorData(HybridUtils.a(request)).b().c().toString());
    }
}
