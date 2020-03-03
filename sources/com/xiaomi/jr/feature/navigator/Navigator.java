package com.xiaomi.jr.feature.navigator;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import java.util.Map;

@Feature("Navigator")
public class Navigator extends HybridFeature {
    private static final long INTERVAL_START_ACTIVITY = 500;
    private long mLastStartActivityTime;

    private static class OpenPageParam {
        @SerializedName("url")

        /* renamed from: a  reason: collision with root package name */
        String f10403a;
        @SerializedName("title")
        String b;

        private OpenPageParam() {
        }
    }

    @Action(paramClazz = OpenPageParam.class)
    public Response openPage(Request<OpenPageParam> request) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastStartActivityTime < INTERVAL_START_ACTIVITY) {
            return new Response(200, "don't start activity frequently");
        }
        this.mLastStartActivityTime = currentTimeMillis;
        if (TextUtils.isEmpty(request.c().f10403a)) {
            return new Response.InvalidParamResponse(request, "both url and id are null.");
        }
        HybridUtils.a(request, 3, (Map) new Gson().fromJson(request.d(), Map.class));
        return Response.j;
    }

    @Action
    public Response closePage(Request request) {
        HybridUtils.a(request, 4, (Object) null);
        return Response.j;
    }

    @Action
    public Response gotoStartPage(Request request) {
        HybridUtils.a(request, 22, (Object) null);
        return Response.j;
    }

    @Action
    public Response isHomePage(Request request) {
        return new Response(HybridUtils.a(request, 2));
    }

    @Action
    public Response exitApp(Request request) {
        HybridUtils.a(request, 23, (Object) null);
        return Response.j;
    }
}
