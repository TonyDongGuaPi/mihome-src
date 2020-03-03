package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.alipay.sdk.sys.a;
import com.mi.global.shop.util.ConnectionHelper;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeOAuthHttpsApi;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.notishortcut.AccountManager;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import org.json.JSONException;
import org.json.JSONObject;

public class NetUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20996a = ".io.mi.com";
    public static final String b = "POST";
    public static final String c = "GET";
    private static final String d = "api.io.mi.com";
    private static final String e = "api.io.mi.com";
    private static String f = ConnectionHelper.I;
    private static final int g = -999;
    private static final int h = -100;
    private static OkHttpClient i;
    private static volatile NetUtil q = null;
    private static final JoinPoint.StaticPart r = null;
    /* access modifiers changed from: private */
    public Context j;
    private String k;
    private Locale l;
    private String m;
    private String n;
    /* access modifiers changed from: private */
    public AccountManager.ServiceTokenInfo o;
    private CookieManager p;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return NetUtil.a((NetUtil) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    public interface MyCallback {
        void a(int i, String str);

        void b(int i, String str);
    }

    private static void f() {
        Factory factory = new Factory("NetUtil.java", NetUtil.class);
        r = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 146);
    }

    static {
        f();
    }

    private NetUtil(Context context) {
        this.j = context;
        i = c();
        a();
    }

    public void a() {
        this.k = AccountManager.a(this.j);
        this.l = InnerCookieManager.c(this.j);
        this.m = InnerCookieManager.a(this.j);
        this.n = InnerCookieManager.b(this.j);
        this.o = AccountManager.b(this.j);
        i = c();
    }

    public static NetUtil a(Context context) {
        if (q == null) {
            synchronized (NetUtil.class) {
                if (q == null) {
                    q = new NetUtil(context);
                }
            }
        }
        return q;
    }

    private OkHttpClient c() {
        OkHttpClient.Builder retryOnConnectionFailure = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(false);
        CookieManager cookieManager = new CookieManager();
        this.p = cookieManager;
        OkHttpClient.Builder addNetworkInterceptor = retryOnConnectionFailure.cookieJar(new JavaNetCookieJar(cookieManager)).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                if (request.tag() == null || !(request.tag() instanceof String) || request.tag().equals(NetUtil.this.o.b)) {
                    return chain.proceed(request.newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgentUtil.a(NetUtil.this.j)).build());
                }
                throw new IOException();
            }
        });
        JoinPoint a2 = Factory.a(r, (Object) this, (Object) addNetworkInterceptor);
        return (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, addNetworkInterceptor, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(NetUtil netUtil, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    private static RequestBody a(List<KeyValuePair> list) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        for (KeyValuePair next : list) {
            if (TextUtils.isEmpty(next.a())) {
                throw new Exception("Key may not be null");
            } else if (!TextUtils.isEmpty(next.b())) {
                builder.add(next.a(), next.b());
            }
        }
        return builder.build();
    }

    public void a(MyRequest myRequest, final MyCallback myCallback) throws Exception {
        a();
        if (TextUtils.isEmpty(this.k)) {
            throw new Exception("uid is null");
        } else if (this.o != null) {
            d();
            final Pair<List<KeyValuePair>, String> a2 = a(myRequest);
            if (a2 == null && myCallback != null) {
                myCallback.b(-999, "pair is null");
            }
            Request.Builder builder = new Request.Builder();
            if ("POST".equalsIgnoreCase(myRequest.b)) {
                builder.url(a(myRequest.f21000a)).method(myRequest.b, a((List<KeyValuePair>) (List) a2.first));
            } else if ("GET".equalsIgnoreCase(myRequest.b)) {
                builder.url(a(a(myRequest.f21000a), (List<KeyValuePair>) (List) a2.first));
            } else if (myCallback != null) {
                myCallback.b(-999, "only support post get");
                return;
            } else {
                return;
            }
            i.newCall(builder.build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (myCallback != null) {
                        MyCallback myCallback = myCallback;
                        myCallback.b(-100, "Request is onFailure " + iOException.getMessage());
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response == null && myCallback != null) {
                        myCallback.b(-999, "Response is null");
                    }
                    if (response.isSuccessful()) {
                        String a2 = NetUtil.this.a(response.body().string(), (String) a2.second);
                        try {
                            JSONObject jSONObject = new JSONObject(a2);
                            if (!jSONObject.isNull("code")) {
                                int optInt = jSONObject.optInt("code");
                                if (myCallback == null) {
                                    return;
                                }
                                if (optInt == 0) {
                                    myCallback.a(optInt, jSONObject.optString("result"));
                                    return;
                                }
                                MyCallback myCallback = myCallback;
                                myCallback.b(optInt, "business error: " + jSONObject.optString("message"));
                            }
                        } catch (JSONException unused) {
                            if (myCallback != null) {
                                MyCallback myCallback2 = myCallback;
                                int code = response.code();
                                myCallback2.b(code, "response error: " + a2);
                            }
                        }
                    } else if (myCallback != null) {
                        myCallback.b(response.code(), "response is fail!");
                    }
                }
            });
        } else {
            throw new Exception("mServiceTokenInfo is null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0162  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() throws java.lang.Exception {
        /*
            r17 = this;
            r1 = r17
            java.net.CookieManager r0 = r1.p
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r0)
            java.lang.String r0 = r1.k
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x018b
            java.net.CookieManager r2 = r1.p
            java.lang.String r3 = r17.e()
            java.lang.String r4 = "userId"
            java.lang.String r5 = r1.k
            java.lang.String r6 = ".io.mi.com"
            java.lang.String r7 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r2, r3, r4, r5, r6, r7)
            com.xiaomi.smarthome.notishortcut.AccountManager$ServiceTokenInfo r0 = r1.o
            if (r0 == 0) goto L_0x0183
            java.net.CookieManager r2 = r1.p
            java.lang.String r3 = r17.e()
            java.lang.String r4 = "serviceToken"
            com.xiaomi.smarthome.notishortcut.AccountManager$ServiceTokenInfo r0 = r1.o
            java.lang.String r5 = r0.f1558a
            com.xiaomi.smarthome.notishortcut.AccountManager$ServiceTokenInfo r0 = r1.o
            java.lang.String r6 = r0.d
            java.lang.String r7 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r2, r3, r4, r5, r6, r7)
            java.lang.String r2 = ""
            r3 = 0
            com.xiaomi.smarthome.library.commonapi.SystemApi r0 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ UnsupportedEncodingException -> 0x00f4, all -> 0x00a9 }
            java.lang.String r0 = r0.m()     // Catch:{ UnsupportedEncodingException -> 0x00f4, all -> 0x00a9 }
            java.lang.String r4 = "UTF-8"
            java.lang.String r8 = java.net.URLEncoder.encode(r0, r4)     // Catch:{ UnsupportedEncodingException -> 0x00f4, all -> 0x00a9 }
            java.util.TimeZone r0 = java.util.TimeZone.getDefault()     // Catch:{ UnsupportedEncodingException -> 0x00a7, all -> 0x00a4 }
            boolean r2 = r0.useDaylightTime()     // Catch:{ UnsupportedEncodingException -> 0x00a7, all -> 0x00a4 }
            int r0 = r0.getDSTSavings()     // Catch:{ UnsupportedEncodingException -> 0x00a2, all -> 0x009f }
            java.net.CookieManager r5 = r1.p
            java.lang.String r6 = r17.e()
            java.lang.String r7 = "timezone"
            java.lang.String r9 = ".io.mi.com"
            java.lang.String r10 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r5, r6, r7, r8, r9, r10)
            java.net.CookieManager r11 = r1.p
            java.lang.String r12 = r17.e()
            java.lang.String r13 = "is_daylight"
            if (r2 == 0) goto L_0x0073
            java.lang.String r2 = "1"
        L_0x0071:
            r14 = r2
            goto L_0x0076
        L_0x0073:
            java.lang.String r2 = "0"
            goto L_0x0071
        L_0x0076:
            java.lang.String r15 = ".io.mi.com"
            java.lang.String r16 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r11, r12, r13, r14, r15, r16)
            java.net.CookieManager r2 = r1.p
            java.lang.String r3 = r17.e()
            java.lang.String r4 = "dst_offset"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = ""
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = ".io.mi.com"
            java.lang.String r7 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r2, r3, r4, r5, r6, r7)
            goto L_0x013d
        L_0x009f:
            r0 = move-exception
            r7 = r8
            goto L_0x00ac
        L_0x00a2:
            r13 = r8
            goto L_0x00f6
        L_0x00a4:
            r0 = move-exception
            r7 = r8
            goto L_0x00ab
        L_0x00a7:
            r13 = r8
            goto L_0x00f5
        L_0x00a9:
            r0 = move-exception
            r7 = r2
        L_0x00ab:
            r2 = 0
        L_0x00ac:
            java.net.CookieManager r4 = r1.p
            java.lang.String r5 = r17.e()
            java.lang.String r6 = "timezone"
            java.lang.String r8 = ".io.mi.com"
            java.lang.String r9 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r4, r5, r6, r7, r8, r9)
            java.net.CookieManager r10 = r1.p
            java.lang.String r11 = r17.e()
            if (r2 == 0) goto L_0x00c7
            java.lang.String r2 = "1"
        L_0x00c5:
            r13 = r2
            goto L_0x00ca
        L_0x00c7:
            java.lang.String r2 = "0"
            goto L_0x00c5
        L_0x00ca:
            java.lang.String r12 = "is_daylight"
            java.lang.String r14 = ".io.mi.com"
            java.lang.String r15 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r10, r11, r12, r13, r14, r15)
            java.net.CookieManager r4 = r1.p
            java.lang.String r5 = r17.e()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = ""
            r2.append(r6)
            r2.append(r3)
            java.lang.String r7 = r2.toString()
            java.lang.String r6 = "dst_offset"
            java.lang.String r8 = ".io.mi.com"
            java.lang.String r9 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r4, r5, r6, r7, r8, r9)
            throw r0
        L_0x00f4:
            r13 = r2
        L_0x00f5:
            r2 = 0
        L_0x00f6:
            java.net.CookieManager r10 = r1.p
            java.lang.String r11 = r17.e()
            java.lang.String r12 = "timezone"
            java.lang.String r14 = ".io.mi.com"
            java.lang.String r15 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r10, r11, r12, r13, r14, r15)
            java.net.CookieManager r4 = r1.p
            java.lang.String r5 = r17.e()
            java.lang.String r6 = "is_daylight"
            if (r2 == 0) goto L_0x0113
            java.lang.String r0 = "1"
        L_0x0111:
            r7 = r0
            goto L_0x0116
        L_0x0113:
            java.lang.String r0 = "0"
            goto L_0x0111
        L_0x0116:
            java.lang.String r8 = ".io.mi.com"
            java.lang.String r9 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r4, r5, r6, r7, r8, r9)
            java.net.CookieManager r10 = r1.p
            java.lang.String r11 = r17.e()
            java.lang.String r12 = "dst_offset"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = ""
            r0.append(r2)
            r0.append(r3)
            java.lang.String r13 = r0.toString()
            java.lang.String r14 = ".io.mi.com"
            java.lang.String r15 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r10, r11, r12, r13, r14, r15)
        L_0x013d:
            java.util.Locale r0 = r1.l
            if (r0 != 0) goto L_0x0162
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r2 = "NotiSettingManager"
            java.lang.String r3 = r0.toString()
            com.xiaomi.smarthome.notishortcut.inward.LogUtil.a(r2, r3)
            java.net.CookieManager r4 = r1.p
            java.lang.String r5 = r17.e()
            java.lang.String r6 = "locale"
            java.lang.String r7 = r0.toString()
            java.lang.String r8 = ".io.mi.com"
            java.lang.String r9 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r4, r5, r6, r7, r8, r9)
            goto L_0x0182
        L_0x0162:
            java.lang.String r0 = "NotiSettingManager"
            java.util.Locale r2 = r1.l
            java.lang.String r2 = r2.toString()
            com.xiaomi.smarthome.notishortcut.inward.LogUtil.a(r0, r2)
            java.net.CookieManager r3 = r1.p
            java.lang.String r4 = r17.e()
            java.lang.String r5 = "locale"
            java.util.Locale r0 = r1.l
            java.lang.String r6 = r0.toString()
            java.lang.String r7 = ".io.mi.com"
            java.lang.String r8 = "/"
            com.xiaomi.smarthome.notishortcut.CookieUtil.a(r3, r4, r5, r6, r7, r8)
        L_0x0182:
            return
        L_0x0183:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r2 = "serviceToken is null"
            r0.<init>(r2)
            throw r0
        L_0x018b:
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r2 = "uid is null"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.notishortcut.NetUtil.d():void");
    }

    private String a(String str, List<KeyValuePair> list) {
        if (list == null || list.isEmpty()) {
            return str;
        }
        String a2 = a((List<? extends KeyValuePair>) list, "UTF-8");
        if (!str.contains("?")) {
            return str + "?" + a2;
        }
        return str + a.b + a2;
    }

    private String a(List<? extends KeyValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (KeyValuePair keyValuePair : list) {
            String b2 = b(keyValuePair.a(), str);
            String b3 = keyValuePair.b();
            String b4 = b3 != null ? b(b3, str) : "";
            if (sb.length() > 0) {
                sb.append(a.b);
            }
            sb.append(b2);
            sb.append("=");
            sb.append(b4);
        }
        return sb.toString();
    }

    private static String b(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private String a(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            throw new Exception("Path is empty");
        } else if (str.startsWith(com.mi.global.bbs.utils.ConnectionHelper.HTTP_PREFIX) || str.startsWith("https://")) {
            return str;
        } else {
            if (str.startsWith("/")) {
                return e() + f + str;
            }
            return e() + f + "/" + str;
        }
    }

    private String e() {
        if (TextUtils.isEmpty(this.m) || this.m.equalsIgnoreCase("cn")) {
            return (TextUtils.isEmpty(this.n) || this.n.equalsIgnoreCase("release") || !this.n.equalsIgnoreCase("preview")) ? SmartHomeOAuthHttpsApi.f14081a : "https://pv.api.io.mi.com";
        }
        if (TextUtils.isEmpty(this.n) || this.n.equalsIgnoreCase("release") || !this.n.equalsIgnoreCase("preview")) {
            return "https://" + this.m + "." + "api.io.mi.com";
        }
        return "https://pv-" + this.m + "." + "api.io.mi.com";
    }

    public void b() {
        if (q != null) {
            q = null;
        }
    }

    public static class MyRequest {

        /* renamed from: a  reason: collision with root package name */
        String f21000a;
        String b;
        List<KeyValuePair> c = new ArrayList(8);

        public MyRequest(String str, String str2, List<KeyValuePair> list) {
            this.f21000a = str;
            this.b = str2;
            this.c = list;
        }

        public static class Builder {

            /* renamed from: a  reason: collision with root package name */
            private String f21001a = "";
            private String b = "GET";
            private List<KeyValuePair> c = new ArrayList(8);

            public Builder a(String str) {
                this.f21001a = str;
                return this;
            }

            public Builder b(String str) throws Exception {
                if ("POST".equalsIgnoreCase(str) || "GET".equalsIgnoreCase(str)) {
                    this.b = str.toUpperCase();
                    return this;
                }
                throw new Exception("only  support POST GET!");
            }

            public Builder a(List<KeyValuePair> list) {
                if (list != null) {
                    this.c = list;
                    return this;
                }
                throw new IllegalArgumentException("queries == null");
            }

            public Builder a(Map<String, String> map) {
                if (map != null) {
                    this.c = new ArrayList();
                    for (String next : map.keySet()) {
                        this.c.add(new KeyValuePair(next, map.get(next)));
                    }
                    return this;
                }
                throw new IllegalArgumentException("queries == null");
            }

            public MyRequest a() {
                return new MyRequest(this.f21001a, this.b, this.c);
            }
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.util.List<com.xiaomi.smarthome.library.http.KeyValuePair>, java.lang.String> a(com.xiaomi.smarthome.notishortcut.NetUtil.MyRequest r10) {
        /*
            r9 = this;
            com.xiaomi.smarthome.notishortcut.AccountManager$ServiceTokenInfo r0 = r9.o
            long r0 = r0.c
            java.lang.String r0 = a((long) r0)
            r1 = 0
            com.xiaomi.smarthome.notishortcut.AccountManager$ServiceTokenInfo r2 = r9.o     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            java.lang.String r2 = r2.b     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r0)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = r9.a((byte[]) r2, (byte[]) r3)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            java.lang.String r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((byte[]) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            goto L_0x003a
        L_0x0022:
            java.lang.String r2 = "noti"
            java.lang.String r3 = "generate sessionSecurity fail"
            com.xiaomi.smarthome.notishortcut.inward.LogUtil.a(r2, r3)
            goto L_0x0039
        L_0x002a:
            java.lang.String r2 = "noti"
            java.lang.String r3 = "generate sessionSecurity fail:InvalidKeyException"
            com.xiaomi.smarthome.notishortcut.inward.LogUtil.a(r2, r3)
            goto L_0x0039
        L_0x0032:
            java.lang.String r2 = "noti"
            java.lang.String r3 = "generate sessionSecurity fail:NoSuchAlgorithmException"
            com.xiaomi.smarthome.notishortcut.inward.LogUtil.a(r2, r3)
        L_0x0039:
            r2 = r1
        L_0x003a:
            if (r2 != 0) goto L_0x003d
            return r1
        L_0x003d:
            java.util.TreeMap r1 = new java.util.TreeMap
            r1.<init>()
            java.util.TreeMap r3 = new java.util.TreeMap
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder r5 = new com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder
            r5.<init>((java.lang.String) r2)
            java.util.List<com.xiaomi.smarthome.library.http.KeyValuePair> r6 = r10.c
            if (r6 == 0) goto L_0x0085
            java.util.Iterator r6 = r6.iterator()
        L_0x0059:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0085
            java.lang.Object r7 = r6.next()
            com.xiaomi.smarthome.library.http.KeyValuePair r7 = (com.xiaomi.smarthome.library.http.KeyValuePair) r7
            java.lang.String r8 = r7.a()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x0059
            java.lang.String r8 = r7.b()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x0059
            java.lang.String r8 = r7.a()
            java.lang.String r7 = r7.b()
            r3.put(r8, r7)
            goto L_0x0059
        L_0x0085:
            java.lang.String r6 = r10.b
            java.lang.String r7 = r10.f21000a
            java.lang.String r6 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r6, r7, r3, r2)
            java.lang.String r7 = "rc4_hash__"
            r3.put(r7, r6)
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x009a:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x00c6
            java.lang.Object r6 = r3.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getValue()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r7 = r5.b((java.lang.String) r7)
            java.lang.Object r8 = r6.getKey()
            r1.put(r8, r7)
            com.xiaomi.smarthome.library.http.KeyValuePair r8 = new com.xiaomi.smarthome.library.http.KeyValuePair
            java.lang.Object r6 = r6.getKey()
            java.lang.String r6 = (java.lang.String) r6
            r8.<init>(r6, r7)
            r4.add(r8)
            goto L_0x009a
        L_0x00c6:
            java.lang.String r3 = r10.b
            java.lang.String r10 = r10.f21000a
            java.lang.String r10 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r3, r10, r1, r2)
            com.xiaomi.smarthome.library.http.KeyValuePair r1 = new com.xiaomi.smarthome.library.http.KeyValuePair
            java.lang.String r2 = "signature"
            r1.<init>(r2, r10)
            r4.add(r1)
            com.xiaomi.smarthome.library.http.KeyValuePair r10 = new com.xiaomi.smarthome.library.http.KeyValuePair
            java.lang.String r1 = "_nonce"
            r10.<init>(r1, r0)
            r4.add(r10)
            android.util.Pair r10 = android.util.Pair.create(r4, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.notishortcut.NetUtil.a(com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public String a(String str, String str2) throws SecurityException {
        try {
            String a2 = Coder.a(Coder.d(a(Coder.a(this.o.b), Coder.a(str2))));
            if (a2 == null) {
                return null;
            }
            try {
                return new RC4DropCoder(a2).a(str);
            } catch (Exception unused) {
                return null;
            }
        } catch (NoSuchAlgorithmException unused2) {
            return null;
        } catch (InvalidKeyException unused3) {
            return null;
        } catch (Exception unused4) {
            return null;
        }
    }

    private static String a(long j2) {
        SecureRandom secureRandom = new SecureRandom();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeLong(secureRandom.nextLong());
            dataOutputStream.writeInt((int) ((System.currentTimeMillis() + j2) / 60000));
            dataOutputStream.flush();
        } catch (IOException unused) {
        }
        return String.valueOf(Base64Coder.a(byteArrayOutputStream.toByteArray()));
    }

    public static class FlushHelper {

        /* renamed from: a  reason: collision with root package name */
        private String f20999a;
        private Locale b;
        private String c;
        private String d;
        private String e;
        private String f;
        private long g;
        private String h;

        public FlushHelper a(String str) {
            this.f20999a = str;
            return this;
        }

        public FlushHelper b(String str) {
            this.c = str;
            return this;
        }

        public FlushHelper c(String str) {
            this.d = str;
            return this;
        }

        public FlushHelper d(String str) {
            this.e = str;
            return this;
        }

        public FlushHelper e(String str) {
            this.f = str;
            return this;
        }

        public FlushHelper f(String str) {
            this.h = str;
            return this;
        }

        public FlushHelper a(long j) {
            this.g = j;
            return this;
        }

        public FlushHelper a(Locale locale) {
            this.b = locale;
            return this;
        }

        public FlushHelper a(AccountManager.ServiceTokenInfo serviceTokenInfo) {
            if (serviceTokenInfo == null) {
                return this;
            }
            this.e = serviceTokenInfo.f1558a;
            this.f = serviceTokenInfo.b;
            this.g = serviceTokenInfo.c;
            this.h = serviceTokenInfo.d;
            return this;
        }

        public void a(NetUtil netUtil) {
            if (!TextUtils.isEmpty(this.f20999a)) {
                AccountManager.a(netUtil.j, this.f20999a);
            }
            if (this.b != null) {
                InnerCookieManager.a(netUtil.j, this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                InnerCookieManager.a(netUtil.j, this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                InnerCookieManager.b(netUtil.j, this.d);
            }
            if (!TextUtils.isEmpty(this.e)) {
                AccountManager.a(netUtil.j, this.e, this.g, this.f, this.h);
            }
            NetUtil.a(netUtil.j).a();
        }
    }
}
