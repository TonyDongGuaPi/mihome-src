package com.xiaomi.smarthome.core.server.internal.api;

import android.content.BroadcastReceiver;
import android.text.TextUtils;
import android.util.Log;
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
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class SmartHomeOAuthHttpsApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14081a = "https://api.io.mi.com";
    private static SmartHomeOAuthHttpsApi b;
    private static Object c = new Object();
    private static BroadcastReceiver d;
    private static final JoinPoint.StaticPart h = null;
    private boolean e = false;
    private OkHttpClient f;
    private CookieManager g;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return SmartHomeOAuthHttpsApi.a((SmartHomeOAuthHttpsApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private static void e() {
        Factory factory = new Factory("SmartHomeOAuthHttpsApi.java", SmartHomeOAuthHttpsApi.class);
        h = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 85);
    }

    static {
        e();
    }

    private SmartHomeOAuthHttpsApi() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        CookieManager cookieManager = new CookieManager();
        this.g = cookieManager;
        OkHttpClient.Builder addNetworkInterceptor = writeTimeout.cookieJar(new JavaNetCookieJar(cookieManager)).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgentUtil.a(CoreService.getAppContext())).build());
            }
        });
        JoinPoint a2 = Factory.a(h, (Object) this, (Object) addNetworkInterceptor);
        this.f = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, addNetworkInterceptor, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(SmartHomeOAuthHttpsApi smartHomeOAuthHttpsApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static SmartHomeOAuthHttpsApi a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new SmartHomeOAuthHttpsApi();
                }
            }
        }
        return b;
    }

    public boolean b() {
        boolean z;
        synchronized (c) {
            z = this.e;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (c) {
            this.e = z;
        }
    }

    private String a(NetRequest netRequest) {
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        if (d2 == null || ServerCompact.c(d2)) {
            return "https://api.io.mi.com/app" + netRequest.b();
        }
        return "https://" + d2.f1530a + ".api.io.mi.com/app" + netRequest.b();
    }

    private void c() {
        Locale g2 = GlobalDynamicSettingManager.a().g();
        if (g2 == null) {
            CookieUtil.a(this.g, f14081a, "locale", LocaleUtil.a(Locale.getDefault()), ".io.mi.com", "/");
            return;
        }
        CookieUtil.a(this.g, f14081a, "locale", LocaleUtil.a(g2), ".io.mi.com", "/");
    }

    private void d() {
        String str;
        try {
            str = URLEncoder.encode(SystemApi.a().m(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str = "";
        }
        CookieUtil.a(this.g, f14081a, "timezone", str, ".io.mi.com", "/");
    }

    public NetHandle a(NetRequest netRequest, final NetCallback<NetResult, NetError> netCallback) {
        Request request;
        if (netRequest == null) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        }
        String a2 = a(netRequest);
        if (netRequest.a().equals("POST")) {
            RequestBody b2 = KeyValuePairUtil.b(netRequest.e());
            Request.Builder headers = new Request.Builder().url(a2).headers(KeyValuePairUtil.a(netRequest.d()));
            if (b2 == null) {
                b2 = new FormBody.Builder().build();
            }
            request = headers.post(b2).build();
        } else {
            request = netRequest.a().equals("GET") ? new Request.Builder().url(KeyValuePairUtil.a(a2, netRequest.e())).headers(KeyValuePairUtil.a(netRequest.d())).build() : null;
        }
        if (request == null) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        }
        Call newCall = this.f.newCall(request);
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (netCallback != null) {
                    netCallback.a(new NetError(ErrorCode.INVALID.getCode(), iOException == null ? "net request failure" : iOException.getMessage()));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if (response.code() == 401) {
                        try {
                            response.body().string();
                        } catch (Exception unused) {
                        }
                    }
                    if (netCallback != null) {
                        netCallback.a(new NetError(response.code(), ""));
                        return;
                    }
                    return;
                }
                try {
                    String string = response.body().string();
                    if ((HostSetting.g || HostSetting.i) && !TextUtils.isEmpty(string)) {
                        Log.d("CoreService", string);
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
            }
        });
        return new NetHandle(newCall);
    }
}
