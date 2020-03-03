package com.xiaomi.smarthome.core.server.internal.api;

import android.text.TextUtils;
import com.mi.global.shop.util.ConnectionHelper;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class SmartHomeOpenApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14084a = "api.io.mi.com";
    public static final String b = "api.io.mi.com";
    private static SmartHomeOpenApi c;
    private static Object d = new Object();
    private static final JoinPoint.StaticPart h = null;
    private OkHttpClient e;
    private CookieManager f;
    private boolean g = false;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return SmartHomeOpenApi.a((SmartHomeOpenApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private static void g() {
        Factory factory = new Factory("SmartHomeOpenApi.java", SmartHomeOpenApi.class);
        h = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 87);
    }

    static {
        g();
    }

    private SmartHomeOpenApi() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        CookieManager cookieManager = new CookieManager();
        this.f = cookieManager;
        OkHttpClient.Builder addNetworkInterceptor = writeTimeout.cookieJar(new JavaNetCookieJar(cookieManager)).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgentUtil.a(CoreService.getAppContext())).build());
            }
        });
        JoinPoint a2 = Factory.a(h, (Object) this, (Object) addNetworkInterceptor);
        this.e = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, addNetworkInterceptor, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(SmartHomeOpenApi smartHomeOpenApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static SmartHomeOpenApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new SmartHomeOpenApi();
                }
            }
        }
        return c;
    }

    public boolean b() {
        boolean z;
        synchronized (d) {
            z = this.g;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (d) {
            this.g = z;
        }
    }

    private String c() {
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        String f2 = GlobalDynamicSettingManager.a().f();
        if (d2 == null || ServerCompact.c(d2)) {
            return (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) ? SmartHomeOAuthHttpsApi.f14081a : "https://pv.api.io.mi.com";
        }
        if (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) {
            return "https://" + d2.f1530a + "." + "api.io.mi.com";
        }
        return "https://pv-" + d2.f1530a + "." + "api.io.mi.com";
    }

    private String a(NetRequest netRequest) {
        return c() + ConnectionHelper.I + netRequest.b();
    }

    private void d() {
        ServerBean a2 = ServerCompact.a(CoreService.getAppContext());
        if (a2 != null) {
            CookieUtil.a(this.f, c(), Constant.KEY_COUNTRY_CODE, a2.b, ".io.mi.com", "/");
        }
    }

    private void e() {
        Locale g2 = GlobalDynamicSettingManager.a().g();
        if (g2 == null) {
            CookieUtil.a(this.f, c(), "locale", LocaleUtil.a(Locale.getDefault()), ".io.mi.com", "/");
            return;
        }
        CookieUtil.a(this.f, c(), "locale", LocaleUtil.a(g2), ".io.mi.com", "/");
    }

    private void f() {
        String str;
        try {
            str = URLEncoder.encode(SystemApi.a().m(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str = "";
        }
        CookieUtil.a(this.f, c(), "timezone", str, ".io.mi.com", "/");
    }

    public NetHandle a(final NetRequest netRequest, final NetCallback<NetResult, NetError> netCallback) {
        Request request;
        if (netRequest == null) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        }
        String a2 = a(netRequest);
        if (!b()) {
            d();
            e();
            f();
            a(true);
        }
        if (netRequest.a().equals("POST")) {
            request = new Request.Builder().url(a2).post(KeyValuePairUtil.b(netRequest.e())).build();
        } else {
            request = netRequest.a().equals("GET") ? new Request.Builder().url(KeyValuePairUtil.a(a2, netRequest.e())).build() : null;
        }
        if (request == null) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        }
        Call newCall = this.e.newCall(request);
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (HostSetting.g || HostSetting.i) {
                    LogUtil.b("MIIO", netRequest.toString());
                    if (iOException != null) {
                        LogUtil.b("MIIO", iOException.toString());
                    }
                }
                if (netCallback != null) {
                    netCallback.a(new NetError(ErrorCode.INVALID.getCode(), iOException == null ? "net request failure" : iOException.getMessage()));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (HostSetting.g || HostSetting.i) {
                    LogUtil.b("MIIO", netRequest.toString());
                    LogUtil.b("MIIO", response.toString());
                }
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        if ((HostSetting.g || HostSetting.i) && !TextUtils.isEmpty(string)) {
                            LogUtil.a("MIIO", netRequest.toString());
                            LogUtil.a("MIIO", string);
                        }
                        NetResult netResult = new NetResult();
                        netResult.c = string;
                        if (netCallback != null) {
                            netCallback.a(netResult);
                        }
                    } catch (Exception e) {
                        if (netCallback != null) {
                            netCallback.a(new NetError(ErrorCode.INVALID.getCode(), e.getMessage()));
                        }
                    }
                } else if (netCallback != null) {
                    netCallback.a(new NetError(response.code(), ""));
                }
            }
        });
        return new NetHandle(newCall);
    }
}
