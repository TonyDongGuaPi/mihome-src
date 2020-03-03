package com.xiaomi.smarthome.framework.login.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class CaptchaLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final JoinPoint.StaticPart f16549a = null;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return CaptchaLoader.a((OkHttpClient.Builder) objArr2[0], (JoinPoint) objArr2[1]);
        }
    }

    public static class CaptchaResult {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f16551a;
        public String b;
        public String c;
        public String d;
    }

    static {
        a();
    }

    private static void a() {
        Factory factory = new Factory("CaptchaLoader.java", CaptchaLoader.class);
        f16549a = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 38);
    }

    public static void a(String str, final AsyncCallback<CaptchaResult, Error> asyncCallback) {
        final CookieManager cookieManager = new CookieManager();
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager));
        JoinPoint a2 = Factory.a(f16549a, (Object) null, (Object) cookieJar);
        ((OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{cookieJar, a2}).linkClosureAndJoinPoint(16))).newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(-1, ""));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response == null) {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(-1, ""));
                    }
                } else if (response.isSuccessful()) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(response.body().byteStream());
                    CaptchaResult captchaResult = new CaptchaResult();
                    captchaResult.f16551a = decodeStream;
                    HttpCookie a2 = CookieUtil.a(cookieManager, "ick");
                    captchaResult.b = a2.getValue();
                    captchaResult.c = a2.getDomain();
                    captchaResult.d = a2.getPath();
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(captchaResult);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(-1, ""));
                }
            }
        });
    }

    static final OkHttpClient a(OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }
}
