package com.xiaomi.smarthome.frame.crash;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.sdk.sys.a;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONException;
import org.json.JSONObject;

public class FrameCrashApi {

    /* renamed from: a  reason: collision with root package name */
    public static final int f16114a = 8;
    public static final String b = "https://api.chat.xiaomi.net/backyard/v2/user/%s/exception/app";
    private static final String e = "%d-a-%s-%d-x%d";
    private static String f = "8007236f-";
    private static String g = "a2d6-4847-ac83-";
    private static String h = "c49395ad6d65";
    private static FrameCrashApi i;
    private static Object j = new Object();
    private static String k;
    private static final JoinPoint.StaticPart l = null;
    OkHttpClient c;
    Handler d = new Handler(Looper.getMainLooper());

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return FrameCrashApi.a((OkHttpClient.Builder) objArr2[0], (JoinPoint) objArr2[1]);
        }
    }

    private static void e() {
        Factory factory = new Factory("FrameCrashApi.java", FrameCrashApi.class);
        l = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 172);
    }

    static {
        e();
    }

    private FrameCrashApi() {
    }

    public static FrameCrashApi a() {
        if (i == null) {
            synchronized (j) {
                if (i == null) {
                    i = new FrameCrashApi();
                }
            }
        }
        return i;
    }

    private static String a(List<KeyValuePair> list) {
        Collections.sort(list, new Comparator<KeyValuePair>() {
            /* renamed from: a */
            public int compare(KeyValuePair keyValuePair, KeyValuePair keyValuePair2) {
                return keyValuePair.a().compareTo(keyValuePair2.a());
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (KeyValuePair next : list) {
            if (!z) {
                sb.append(a.b);
            }
            sb.append(next.a());
            sb.append("=");
            sb.append(next.b());
            z = false;
        }
        sb.append(a.b);
        sb.append(f);
        sb.append(g);
        sb.append(h);
        return MD5Util.a(Base64Coder.a(a(sb.toString())));
    }

    public static byte[] a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String b() {
        String str;
        synchronized (j) {
            if (k == null) {
                Context c2 = FrameManager.b().c();
                boolean g2 = ServerCompact.g(c2);
                String c3 = SystemApi.a().c(c2);
                StringBuilder sb = new StringBuilder();
                sb.append(SystemApi.a().d().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().f().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().d(c2).replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().g().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().j().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().a(c2, g2).replace("-", ""));
                sb.append("-");
                sb.append((g2 ? SHA1Util.b(c3) : XMStringUtils.d(c3)).replace("-", ""));
                sb.append("-");
                sb.append(CoreApi.a().s());
                k = sb.toString();
            }
            str = k;
        }
        return str;
    }

    public static OkHttpClient c() {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).writeTimeout(3, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new CookieManager()));
        cookieJar.addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", FrameCrashApi.b()).build());
            }
        });
        JoinPoint a2 = Factory.a(l, (Object) null, (Object) cookieJar);
        return (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{cookieJar, a2}).linkClosureAndJoinPoint(16));
    }

    static final OkHttpClient a(OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public OkHttpClient d() {
        if (this.c == null) {
            synchronized (j) {
                if (this.c == null) {
                    this.c = c();
                }
            }
        }
        return this.c;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0137 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, long r15, long r17) {
        /*
            r8 = this;
            r1 = r8
            r0 = r9
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            boolean r4 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.g((android.content.Context) r9)     // Catch:{ JSONException -> 0x013b }
            com.xiaomi.smarthome.library.commonapi.SystemApi r5 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r5 = r5.c(r9)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "userId"
            com.xiaomi.smarthome.frame.core.CoreApi r7 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r7 = r7.s()     // Catch:{ JSONException -> 0x013b }
            r3.put(r6, r7)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "exception_class"
            r7 = r10
            r3.put(r6, r10)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "exception_method"
            r7 = r11
            r3.put(r6, r11)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "exception_stack"
            r7 = r12
            r3.put(r6, r12)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "version_code"
            com.xiaomi.smarthome.library.commonapi.SystemApi r7 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            int r7 = r7.e(r9)     // Catch:{ JSONException -> 0x013b }
            r3.put(r6, r7)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "version_name"
            com.xiaomi.smarthome.library.commonapi.SystemApi r7 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r7 = r7.f(r9)     // Catch:{ JSONException -> 0x013b }
            r3.put(r6, r7)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = "device_id"
            com.xiaomi.smarthome.library.commonapi.SystemApi r7 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = r7.a(r9, r4)     // Catch:{ JSONException -> 0x013b }
            r3.put(r6, r0)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "device_model"
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = r6.g()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r6)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "os_name"
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = r6.d()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r6)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "os_version"
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = r6.f()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r6)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "os_sdk_int"
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = r6.e()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r6)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "os_version_incre"
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ JSONException -> 0x013b }
            java.lang.String r6 = r6.j()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r6)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "imei"
            if (r4 == 0) goto L_0x00ab
            java.lang.String r4 = com.xiaomi.smarthome.library.crypto.SHA1Util.b(r5)     // Catch:{ JSONException -> 0x013b }
            goto L_0x00af
        L_0x00ab:
            java.lang.String r4 = com.xiaomi.smarthome.library.crypto.MD5Util.a((java.lang.String) r5)     // Catch:{ JSONException -> 0x013b }
        L_0x00af:
            r3.put(r0, r4)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "channel"
            r4 = r14
            r3.put(r0, r14)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "time"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x013b }
            r3.put(r0, r4)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "process"
            r4 = r13
            r3.put(r0, r13)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "plugin_id"
            r4 = r15
            r3.put(r0, r4)     // Catch:{ JSONException -> 0x013b }
            java.lang.String r0 = "plugin_package_id"
            r4 = r17
            r3.put(r0, r4)     // Catch:{ JSONException -> 0x013b }
            com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair r0 = new com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair
            java.lang.String r4 = "data"
            java.lang.String r3 = r3.toString()
            byte[] r3 = r3.getBytes()
            java.lang.String r3 = com.xiaomi.smarthome.library.crypto.Base64Coder.a((byte[]) r3)
            r0.<init>(r4, r3)
            r2.add(r0)
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r0.F()
            if (r0 == 0) goto L_0x0115
            boolean r3 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r0)
            if (r3 == 0) goto L_0x00fc
            goto L_0x0115
        L_0x00fc:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "https://"
            r3.append(r4)
            java.lang.String r0 = r0.f1530a
            r3.append(r0)
            java.lang.String r0 = ".api.io.mi.com/app/crashlog"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L_0x0117
        L_0x0115:
            java.lang.String r0 = "https://api.io.mi.com/app/crashlog"
        L_0x0117:
            java.lang.Object r3 = new java.lang.Object
            r3.<init>()
            com.xiaomi.smarthome.frame.crash.FrameCrashApi$3 r4 = new com.xiaomi.smarthome.frame.crash.FrameCrashApi$3
            r4.<init>(r0, r2, r3)
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto L_0x012d
            android.os.Handler r0 = r1.d
            r0.post(r4)
            goto L_0x0130
        L_0x012d:
            r4.run()
        L_0x0130:
            monitor-enter(r3)
            r3.wait()     // Catch:{ InterruptedException -> 0x0137 }
            goto L_0x0137
        L_0x0135:
            r0 = move-exception
            goto L_0x0139
        L_0x0137:
            monitor-exit(r3)     // Catch:{ all -> 0x0135 }
            return
        L_0x0139:
            monitor-exit(r3)     // Catch:{ all -> 0x0135 }
            throw r0
        L_0x013b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.crash.FrameCrashApi.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, long):void");
    }

    public void a(Context context, String str, String str2, String str3, String str4) {
        String s = CoreApi.a().s();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", s);
        } catch (JSONException unused) {
        }
        ArrayList<KeyValuePair> b2 = b(context, str3, "", jSONObject.toString(), str4);
        b2.add(new KeyValuePair("packageName", "com.xiaomi.topic.android"));
        b2.add(new KeyValuePair(LogCategory.CATEGORY_EXCEPTION, str));
        b2.add(new KeyValuePair("exception_method", str2));
        String format = String.format(b, new Object[]{s});
        b2.add(new KeyValuePair("time", String.valueOf(System.currentTimeMillis())));
        b2.add(new KeyValuePair("s", a((List<KeyValuePair>) b2)));
        final Request a2 = new Request.Builder().a("POST").b(format).a((List<KeyValuePair>) b2).a();
        if (Looper.myLooper() == null) {
            this.d.post(new Runnable() {
                public void run() {
                    try {
                        HttpApi.a(FrameCrashApi.this.d(), a2, (AsyncHandler) new TextAsyncHandler() {
                            /* renamed from: a */
                            public void onSuccess(String str, Response response) {
                            }

                            public void onFailure(Error error, Exception exc, Response response) {
                            }
                        });
                    } catch (Exception unused) {
                    }
                }
            });
        } else {
            HttpApi.a(d(), a2, (AsyncHandler) new TextAsyncHandler() {
                /* renamed from: a */
                public void onSuccess(String str, Response response) {
                }

                public void onFailure(Error error, Exception exc, Response response) {
                }
            });
        }
    }

    private ArrayList<KeyValuePair> b(Context context, String str, String str2, String str3, String str4) {
        String str5;
        boolean g2 = ServerCompact.g(context);
        String s = CoreApi.a().s();
        ArrayList<KeyValuePair> arrayList = new ArrayList<>();
        arrayList.add(new KeyValuePair("uuid", s));
        arrayList.add(new KeyValuePair("content", str));
        arrayList.add(new KeyValuePair("deviceId", SystemApi.a().a(context, g2)));
        arrayList.add(new KeyValuePair("os", "1"));
        if (str2 == null) {
            str2 = "";
        }
        arrayList.add(new KeyValuePair("logFile", str2));
        arrayList.add(new KeyValuePair("appid", String.valueOf(8)));
        if (str3 == null) {
            str3 = "";
        }
        arrayList.add(new KeyValuePair("meta", str3));
        arrayList.add(new KeyValuePair("osVersion", String.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(new KeyValuePair("channel", str4));
        if (TextUtils.isEmpty(Build.MODEL)) {
            str5 = "";
        } else {
            str5 = Build.MODEL + "-" + Build.VERSION.INCREMENTAL;
        }
        arrayList.add(new KeyValuePair("model", str5));
        arrayList.add(new KeyValuePair("miVersion", String.format(e, new Object[]{8, str4, Integer.valueOf(SystemApi.a().e(context)), 1})));
        return arrayList;
    }
}
