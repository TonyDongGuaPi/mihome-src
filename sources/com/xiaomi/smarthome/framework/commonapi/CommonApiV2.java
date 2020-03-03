package com.xiaomi.smarthome.framework.commonapi;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.config.SHSetting;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class CommonApiV2 {
    private static CommonApiV2 b;
    private static final JoinPoint.StaticPart f = null;

    /* renamed from: a  reason: collision with root package name */
    Handler f1533a = new Handler(Looper.getMainLooper());
    private final Object c = new Object();
    private Context d = CommonApplication.getAppContext();
    private OkHttpClient e = null;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return CommonApiV2.a((CommonApiV2) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    static {
        c();
    }

    private static void c() {
        Factory factory = new Factory("CommonApiV2.java", CommonApiV2.class);
        f = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 79);
    }

    private CommonApiV2() {
    }

    public static CommonApiV2 a() {
        if (b == null) {
            b = new CommonApiV2();
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public OkHttpClient b() {
        if (this.e == null) {
            synchronized (this.c) {
                if (this.e == null) {
                    OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).writeTimeout(3, TimeUnit.SECONDS).addNetworkInterceptor(new UserAgentInterceptor(SHSetting.a(true)));
                    JoinPoint a2 = Factory.a(f, (Object) this, (Object) addNetworkInterceptor);
                    this.e = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, addNetworkInterceptor, a2}).linkClosureAndJoinPoint(4112));
                }
            }
        }
        return this.e;
    }

    static final OkHttpClient a(CommonApiV2 commonApiV2, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public AsyncHandle a(Context context, long j, long j2, String str, String str2, String str3, AsyncResponseCallback<Void> asyncResponseCallback) {
        return a(context, j, j2, str, str2, str3, asyncResponseCallback, -1);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:20|(2:22|23)(1:26)|27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0155, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0162, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x015a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.frame.AsyncHandle a(android.content.Context r13, long r14, long r16, java.lang.String r18, java.lang.String r19, java.lang.String r20, com.xiaomi.smarthome.framework.api.AsyncResponseCallback<java.lang.Void> r21, long r22) {
        /*
            r12 = this;
            r1 = r12
            r0 = r13
            r2 = r21
            r3 = r22
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            r7 = 0
            boolean r8 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.g((android.content.Context) r13)     // Catch:{ JSONException -> 0x0163 }
            com.xiaomi.smarthome.library.commonapi.SystemApi r9 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r9 = r9.c(r13)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "userId"
            com.xiaomi.smarthome.frame.core.CoreApi r11 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r11 = r11.s()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "exception_class"
            r11 = r18
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "exception_method"
            r11 = r19
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "exception_stack"
            r11 = r20
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "version_code"
            com.xiaomi.smarthome.library.commonapi.SystemApi r11 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            int r11 = r11.e(r13)     // Catch:{ JSONException -> 0x0163 }
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "version_name"
            com.xiaomi.smarthome.library.commonapi.SystemApi r11 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r11 = r11.f(r13)     // Catch:{ JSONException -> 0x0163 }
            r6.put(r10, r11)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = "device_id"
            com.xiaomi.smarthome.library.commonapi.SystemApi r11 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = r11.a(r13, r8)     // Catch:{ JSONException -> 0x0163 }
            r6.put(r10, r0)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "device_model"
            com.xiaomi.smarthome.library.commonapi.SystemApi r10 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = r10.g()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r10)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "os_name"
            com.xiaomi.smarthome.library.commonapi.SystemApi r10 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = r10.d()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r10)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "os_version"
            com.xiaomi.smarthome.library.commonapi.SystemApi r10 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = r10.f()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r10)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "os_sdk_int"
            com.xiaomi.smarthome.library.commonapi.SystemApi r10 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = r10.e()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r10)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "os_version_incre"
            com.xiaomi.smarthome.library.commonapi.SystemApi r10 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r10 = r10.j()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r10)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "imei"
            if (r8 == 0) goto L_0x00b3
            java.lang.String r8 = com.xiaomi.smarthome.library.crypto.SHA1Util.b(r9)     // Catch:{ JSONException -> 0x0163 }
            goto L_0x00b7
        L_0x00b3:
            java.lang.String r8 = com.xiaomi.smarthome.library.common.util.XMStringUtils.d(r9)     // Catch:{ JSONException -> 0x0163 }
        L_0x00b7:
            r6.put(r0, r8)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "channel"
            java.lang.String r8 = com.xiaomi.smarthome.globalsetting.GlobalSetting.v     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r8)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "time"
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0163 }
            r6.put(r0, r8)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "plugin_id"
            r8 = r14
            r6.put(r0, r14)     // Catch:{ JSONException -> 0x0163 }
            java.lang.String r0 = "plugin_package_id"
            r8 = r16
            r6.put(r0, r8)     // Catch:{ JSONException -> 0x0163 }
            com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair r0 = new com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "data"
            java.lang.String r6 = r6.toString()
            byte[] r6 = r6.getBytes()
            java.lang.String r6 = com.xiaomi.smarthome.library.crypto.Base64Coder.a((byte[]) r6)
            r0.<init>(r2, r6)
            r5.add(r0)
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.D()
            if (r0 == 0) goto L_0x012f
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r0.F()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = "https://"
            r2.append(r6)
            if (r0 == 0) goto L_0x0120
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = r0.f1530a
            r6.append(r0)
            java.lang.String r0 = "."
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            goto L_0x0122
        L_0x0120:
            java.lang.String r0 = ""
        L_0x0122:
            r2.append(r0)
            java.lang.String r0 = "api.io.mi.com/app/crashlog"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto L_0x0131
        L_0x012f:
            java.lang.String r0 = "https://api.io.mi.com/app/crashlog"
        L_0x0131:
            java.lang.Object r6 = new java.lang.Object
            r6.<init>()
            com.xiaomi.smarthome.framework.commonapi.CommonApiV2$1 r2 = new com.xiaomi.smarthome.framework.commonapi.CommonApiV2$1
            r2.<init>(r0, r5, r6)
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto L_0x0147
            android.os.Handler r0 = r1.f1533a
            r0.post(r2)
            goto L_0x014a
        L_0x0147:
            r2.run()
        L_0x014a:
            monitor-enter(r6)
            r8 = -1
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0157
            r6.wait()     // Catch:{ InterruptedException -> 0x015a }
            goto L_0x015a
        L_0x0155:
            r0 = move-exception
            goto L_0x0161
        L_0x0157:
            r6.wait(r3)     // Catch:{ InterruptedException -> 0x015a }
        L_0x015a:
            monitor-exit(r6)     // Catch:{ all -> 0x0155 }
            com.xiaomi.smarthome.frame.AsyncHandle r0 = new com.xiaomi.smarthome.frame.AsyncHandle
            r0.<init>(r7)
            return r0
        L_0x0161:
            monitor-exit(r6)     // Catch:{ all -> 0x0155 }
            throw r0
        L_0x0163:
            if (r2 == 0) goto L_0x016e
            com.xiaomi.smarthome.frame.ErrorCode r0 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r0 = r0.getCode()
            r2.a((int) r0)
        L_0x016e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.commonapi.CommonApiV2.a(android.content.Context, long, long, java.lang.String, java.lang.String, java.lang.String, com.xiaomi.smarthome.framework.api.AsyncResponseCallback, long):com.xiaomi.smarthome.frame.AsyncHandle");
    }

    private class UserAgentInterceptor implements Interceptor {
        private final String b;

        public UserAgentInterceptor(String str) {
            this.b = str;
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", this.b).build());
        }
    }
}
