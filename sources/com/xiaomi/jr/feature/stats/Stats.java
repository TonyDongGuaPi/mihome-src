package com.xiaomi.jr.feature.stats;

import android.os.Bundle;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.http.utils.JsonUtils;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.stats.StatUtils;
import java.util.HashMap;
import java.util.Map;

@Feature("Stats")
public class Stats extends HybridFeature {

    private static class RecordEventParam {
        @SerializedName("category")

        /* renamed from: a  reason: collision with root package name */
        String f10425a;
        @SerializedName("key")
        String b;
        @SerializedName("json")
        String c;

        private RecordEventParam() {
        }
    }

    @Action(paramClazz = RecordEventParam.class)
    public Response recordEvent(Request<RecordEventParam> request) {
        StatUtils.a(request.c().f10425a, request.c().b, JsonUtils.a(request.c().c));
        return Response.j;
    }

    @Action(paramClazz = RecordEventParam.class)
    public Response recordCountEvent(Request<RecordEventParam> request) {
        StatUtils.a(request.a().d(), request.c().f10425a, request.c().b, JsonUtils.a(request.c().c), (Bundle) null);
        return Response.j;
    }

    @Action(paramClazz = String.class)
    public Response recordLog(Request<String> request) {
        HashMap hashMap = new HashMap();
        hashMap.put("log", request.c());
        StatUtils.a("log", "log", (Map<String, String>) hashMap);
        return Response.j;
    }
}
