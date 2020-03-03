package com.xiaomi.smarthome.library.http;

import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class ClientUtil {

    /* renamed from: a  reason: collision with root package name */
    static ConnectionPool f19098a = new ConnectionPool(5, 60000, TimeUnit.MILLISECONDS);
    static Dispatcher b = new Dispatcher(CommonApplication.getNetworkThreadPool());
    private static final String c = "ClientUtil";
    private static final JoinPoint.StaticPart d = null;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return ClientUtil.a((OkHttpClient.Builder) objArr2[0], (JoinPoint) objArr2[1]);
        }
    }

    private static void b() {
        Factory factory = new Factory("ClientUtil.java", ClientUtil.class);
        d = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 66);
    }

    public static OkHttpClient a() {
        String str;
        try {
            str = UserAgentUtil.a(CoreService.getAppContext());
        } catch (Exception e) {
            LogUtil.b(c, "create: " + e.getLocalizedMessage());
            str = null;
        }
        return a(str);
    }

    static {
        b();
    }

    public static OkHttpClient a(final String str) {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().dispatcher(b).connectionPool(f19098a).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new CookieManager()));
        if (!TextUtils.isEmpty(str)) {
            cookieJar.addNetworkInterceptor(new Interceptor() {
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", str).build());
                }
            });
        }
        JoinPoint a2 = Factory.a(d, (Object) null, (Object) cookieJar);
        return (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{cookieJar, a2}).linkClosureAndJoinPoint(16));
    }

    static final OkHttpClient a(OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }
}
