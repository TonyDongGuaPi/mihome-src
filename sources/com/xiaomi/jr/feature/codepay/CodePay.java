package com.xiaomi.jr.feature.codepay;

import android.app.Activity;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.hybrid.FeatureUtil;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.mipay.codepay.CodePayManager;

@Feature("CodePay")
public class CodePay extends HybridFeature {
    @Action(mode = FeatureUtil.Mode.ASYNC, paramClazz = String.class)
    public Response startCodePay(Request<String> request) {
        Activity b = HybridUtils.b(request);
        if (!ActivityChecker.a(b)) {
            return new Response.RuntimeErrorResponse((Request) request, "activity not available");
        }
        DeeplinkUtils.openDeeplink(b, (String) null, UrlUtils.a(CodePayManager.f1446a, CodePayManager.b, request.c()));
        return Response.j;
    }
}
