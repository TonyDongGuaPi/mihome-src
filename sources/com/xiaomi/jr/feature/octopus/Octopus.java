package com.xiaomi.jr.feature.octopus;

import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.bean.OctopusParam;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.main.OctopusTaskCallBack;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;

@Feature("Octopus")
public class Octopus extends HybridFeature {

    private static class GetChannelParam {
        @SerializedName("passbackParams")

        /* renamed from: a  reason: collision with root package name */
        String f10405a;
        @SerializedName("realName")
        String b;
        @SerializedName("mobile")
        String c;
        @SerializedName("identityCode")
        String d;
        @SerializedName("channelCode")
        String e;
        @SerializedName("channelType")
        String f;

        private GetChannelParam() {
        }
    }

    private static class GetChannelResult {
        @SerializedName("errorCode")

        /* renamed from: a  reason: collision with root package name */
        public int f10406a;
        @SerializedName("message")
        public String b;

        private GetChannelResult() {
        }
    }

    @Action(paramClazz = GetChannelParam.class)
    public Response getChannel(Request<GetChannelParam> request) {
        GetChannelParam c = request.c();
        if (TextUtils.isEmpty(c.e)) {
            return new Response.InvalidParamResponse(request);
        }
        OctopusParam octopusParam = new OctopusParam();
        if (!TextUtils.isEmpty(c.f10405a)) {
            octopusParam.passbackarams = c.f10405a;
        }
        if (!TextUtils.isEmpty(c.b)) {
            octopusParam.realName = c.b;
        }
        if (!TextUtils.isEmpty(c.d)) {
            octopusParam.identityCode = c.d;
        }
        if (!TextUtils.isEmpty(c.c)) {
            octopusParam.mobile = c.c;
        }
        OctopusManager.b().b(R.drawable.miuisupport_action_bar_back);
        OctopusManager.b().a(HybridUtils.b(request), c.e, c.f, octopusParam, new OctopusTaskCallBack() {
            public final void onCallBack(int i, String str) {
                Octopus.lambda$getChannel$0(Request.this, i, str);
            }
        });
        return Response.j;
    }

    static /* synthetic */ void lambda$getChannel$0(Request request, int i, String str) {
        GetChannelResult getChannelResult = new GetChannelResult();
        getChannelResult.f10406a = i;
        getChannelResult.b = str;
        if (i == 0) {
            HybridUtils.a(request, new Response(getChannelResult));
        } else {
            HybridUtils.a(request, new Response(200, getChannelResult, "get channel error"));
        }
    }
}
