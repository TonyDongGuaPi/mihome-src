package com.xiaomi.jr.feature.reload;

import android.os.Bundle;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;

@Feature("Reload")
public class Reload extends HybridFeature {

    private static class SetReloadParam {
        @SerializedName("reload")

        /* renamed from: a  reason: collision with root package name */
        boolean f10421a;
        @SerializedName("endTag")
        String b;

        private SetReloadParam() {
        }
    }

    @Action(paramClazz = String.class)
    public Response setPageTag(Request<String> request) {
        HybridUtils.a(request, 10, request.c());
        return Response.j;
    }

    @Action(paramClazz = SetReloadParam.class)
    public Response setReload(Request<SetReloadParam> request) {
        if (((Boolean) HybridUtils.a((Request) request, 2)).booleanValue()) {
            return new Response(200, "setReload should NOT be called on HomePage.");
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("reload", request.c().f10421a);
        bundle.putString(WebEvent.D, request.c().b);
        bundle.putInt(WebEvent.E, HybridUtils.b(request).getTaskId());
        HybridUtils.a(request, 11, bundle);
        return Response.j;
    }

    @Action
    public Response reload(Request request) {
        HybridUtils.a(request, 12, (Object) null);
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response disablePullToRefresh(Request<Boolean> request) {
        HybridUtils.a(request, 2, Boolean.valueOf(!request.c().booleanValue()));
        return Response.j;
    }
}
