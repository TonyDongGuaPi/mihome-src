package com.xiaomi.jr.feature.data;

import android.location.Location;
import com.alipay.android.phone.a.a.a;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.BlockingTask;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.DeviceHelper;
import com.xiaomi.jr.common.utils.DeviceInfoHelper;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiClient;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.personaldata.DataCollector;
import java.util.concurrent.Callable;

@Feature("Data")
public class Data extends HybridFeature {

    private static class GetLocationResult {
        @SerializedName("longitude")

        /* renamed from: a  reason: collision with root package name */
        public double f10390a;
        @SerializedName("latitude")
        public double b;

        private GetLocationResult() {
        }
    }

    private static class GetWebviewMetricsResult {
        @SerializedName("width")

        /* renamed from: a  reason: collision with root package name */
        public int f10391a;
        @SerializedName("height")
        public int b;

        private GetWebviewMetricsResult() {
        }
    }

    @Action
    public Response getApplicationId(Request request) {
        return new Response(HybridUtils.a(request).getPackageName());
    }

    @Action
    public Response getDeviceId(Request request) {
        return new Response(Client.b(HybridUtils.a(request)));
    }

    @Action
    public Response getDeviceIdMd5(Request request) {
        return new Response(Client.c(HybridUtils.a(request)));
    }

    @Action
    public Response getDeviceIdSha1(Request request) {
        return new Response(Client.d(HybridUtils.a(request)));
    }

    @Action
    public Response getDeviceInfo(Request request) {
        return new Response(DeviceInfoHelper.a(HybridUtils.a(request)));
    }

    @Action
    public Response getImei(Request request) {
        return new Response(Client.e(HybridUtils.a(request)));
    }

    @Action
    @Deprecated
    public Response getImeiMd5(Request request) {
        return new Response(Client.f(HybridUtils.a(request)));
    }

    @Action
    @Deprecated
    public Response getImeiSha1(Request request) {
        return new Response(Client.g(HybridUtils.a(request)));
    }

    @Action
    public Response getImsiMd5(Request request) {
        return new Response(Client.p(HybridUtils.a(request)));
    }

    @Action
    public Response getFontScale(Request request) {
        return new Response(1);
    }

    @Action
    public Response getLocation(Request request) {
        Location a2 = Utils.a(HybridUtils.a(request));
        if (a2 == null) {
            return new Response.RuntimeErrorResponse(request, "get location null");
        }
        GetLocationResult getLocationResult = new GetLocationResult();
        getLocationResult.f10390a = a2.getLongitude();
        getLocationResult.b = a2.getLatitude();
        return new Response(getLocationResult);
    }

    @Action
    public Response getMiuiVersionName(Request request) {
        return new Response(MiuiClient.c());
    }

    @Action
    public Response getNetworkType(Request request) {
        return new Response(NetworkUtils.d(HybridUtils.a(request)));
    }

    @Action
    public Response getPhoneNumberMd5(Request request) {
        return new Response(Client.r(HybridUtils.a(request)));
    }

    @Action
    public Response getSystemPlatform(Request request) {
        return new Response(a.f813a);
    }

    @Action
    public Response getUserAgent(Request request) {
        try {
            return new Response((String) new BlockingTask(request.a().e(), new Callable() {
                public final Object call() {
                    return Data.lambda$getUserAgent$0(Request.this);
                }
            }).get());
        } catch (Exception e) {
            return new Response.RuntimeErrorResponse(request, e);
        }
    }

    static /* synthetic */ String lambda$getUserAgent$0(Request request) throws Exception {
        return (String) HybridUtils.a(request, 0);
    }

    @Action
    public Response getVersionCode(Request request) {
        return new Response(Integer.valueOf(AppUtils.f(HybridUtils.a(request))));
    }

    @Action
    public Response getVersionName(Request request) {
        return new Response(AppUtils.g(HybridUtils.a(request)));
    }

    @Action
    public Response getWebviewMetrics(Request request) {
        int[] iArr = (int[]) HybridUtils.a(request, 1);
        GetWebviewMetricsResult getWebviewMetricsResult = new GetWebviewMetricsResult();
        getWebviewMetricsResult.f10391a = iArr[0];
        getWebviewMetricsResult.b = iArr[1];
        return new Response(getWebviewMetricsResult);
    }

    @Action
    public Response hasIccCard(Request request) {
        return new Response(Boolean.valueOf(MiuiClient.a(HybridUtils.a(request))));
    }

    @Action
    public Response isLogEnabled(Request request) {
        return new Response(Boolean.valueOf(MifiLog.f1417a));
    }

    @Action
    public Response getGaid(Request request) {
        return new Response(GoogleAds.a(HybridUtils.a(request)));
    }

    @Action
    public Response getSessionId(Request request) {
        return new Response(Client.e());
    }

    @Action(paramClazz = int[].class)
    public Response uploadPersonalData(Request<int[]> request) {
        if (!Constants.f1410a) {
            return new Response(205, "not support in sdk");
        }
        int[] c = request.c();
        if (c == null || c.length == 0) {
            return new Response.InvalidParamResponse(request);
        }
        DataCollector.a(HybridUtils.a((Request) request)).a(request.c(), true, true);
        return Response.j;
    }

    @Action
    public Response getPushRegId(Request request) {
        return new Response(Client.d());
    }

    @Action
    public Response isTablet(Request request) {
        return new Response(Boolean.valueOf(DeviceHelper.f10365a));
    }
}
