package com.xiaomi.smarthome.core.server.internal.api;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mi.global.shop.util.ConnectionHelper;
import com.tencent.bugly.crashreport.CrashReport;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.log.MyLogger;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.smarthome.sdk.R;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class SmartHomeRc4Api {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14087a = "SmartHomeApi";
    public static final String b = "api.io.mi.com";
    public static final String c = "api.io.mi.com";
    private static final String d = "business.smartcamera";
    private static final String e = "processor.smartcamera";
    private static final String f = "connect.camera";
    private static final String g = "camera";
    private static SmartHomeRc4Api h;
    private static Object i = new Object();
    private static final JoinPoint.StaticPart u = null;
    /* access modifiers changed from: private */
    public MiServiceTokenInfo j;
    private boolean k = false;
    private OkHttpClient l;
    private CookieManager m;
    /* access modifiers changed from: private */
    public Object n = new Object();
    /* access modifiers changed from: private */
    public boolean o = false;
    private long p = 0;
    private long q = 10000;
    private Object r = new Object();
    private Dispatcher s;
    /* access modifiers changed from: private */
    public Context t;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return SmartHomeRc4Api.a((SmartHomeRc4Api) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private static void k() {
        Factory factory = new Factory("SmartHomeRc4Api.java", SmartHomeRc4Api.class);
        u = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 167);
    }

    static {
        k();
    }

    private SmartHomeRc4Api(Context context) {
        this.t = context;
        String str = this.t.getFilesDir().getPath() + File.separator + "okhttp3" + File.separator + "cache";
        ArrayList arrayList = new ArrayList();
        arrayList.add(Protocol.HTTP_1_1);
        if (Build.VERSION.SDK_INT > 20) {
            arrayList.add(Protocol.HTTP_2);
        }
        this.s = new Dispatcher(CommonApplication.getNetworkThreadPool());
        OkHttpClient.Builder protocols = new OkHttpClient.Builder().dispatcher(this.s).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(false).protocols(arrayList);
        CookieManager cookieManager = new CookieManager();
        this.m = cookieManager;
        OkHttpClient.Builder cache = protocols.cookieJar(new JavaNetCookieJar(cookieManager)).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                if (request.tag() == null || !(request.tag() instanceof String) || request.tag().equals(SmartHomeRc4Api.this.j.d)) {
                    Request build = request.newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgentUtil.a(SmartHomeRc4Api.this.t)).build();
                    long currentTimeMillis = System.currentTimeMillis();
                    Response proceed = chain.proceed(build);
                    SmartHomeRc4Api.this.a(build, Math.abs(System.currentTimeMillis() - currentTimeMillis));
                    return proceed;
                }
                throw new IOException();
            }
        }).cache(new Cache(new File(str), 104857600));
        this.l = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cache, Factory.a(u, (Object) this, (Object) cache)}).linkClosureAndJoinPoint(4112));
        j();
    }

    static final OkHttpClient a(SmartHomeRc4Api smartHomeRc4Api, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static SmartHomeRc4Api a() {
        if (h == null) {
            synchronized (i) {
                if (h == null) {
                    h = new SmartHomeRc4Api(CoreService.getAppContext());
                }
            }
        }
        return h;
    }

    public static SmartHomeRc4Api a(Context context) {
        if (h == null) {
            synchronized (i) {
                if (h == null) {
                    h = new SmartHomeRc4Api(context);
                }
            }
        }
        return h;
    }

    public boolean b() {
        boolean z;
        synchronized (i) {
            z = this.k;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (i) {
            this.k = z;
        }
    }

    private String a(String str) {
        String str2;
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        String f2 = GlobalDynamicSettingManager.a().f();
        if (TextUtils.isEmpty(str)) {
            str = "";
        } else if (!str.endsWith(".")) {
            str = str + ".";
        }
        if (d2 == null || ServerCompact.c(d2)) {
            if (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) {
                str2 = str + "api.io.mi.com";
            } else if (c(str)) {
                str2 = str + "api.io.mi.com";
            } else {
                str2 = "pv." + str + "api.io.mi.com";
            }
        } else if (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) {
            str2 = d2.f1530a + "." + str + "api.io.mi.com";
        } else if (c(str)) {
            str2 = d2.f1530a + "." + str + "api.io.mi.com";
        } else {
            str2 = "pv-" + d2.f1530a + "." + str + "api.io.mi.com";
        }
        return "https://" + b(str2);
    }

    private String c() {
        String str;
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        String f2 = GlobalDynamicSettingManager.a().f();
        if (d2 == null || ServerCompact.c(d2)) {
            str = (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) ? "api.io.mi.com" : "pv.api.io.mi.com";
        } else if (TextUtils.isEmpty(f2) || f2.equalsIgnoreCase("release") || !f2.equalsIgnoreCase("preview")) {
            str = d2.f1530a + "." + "api.io.mi.com";
        } else {
            str = "pv-" + d2.f1530a + "." + "api.io.mi.com";
        }
        return "https://" + b(str);
    }

    private String b(String str) {
        if ((!AccountManager.a().r() && !AccountManager.a().s()) || d(str)) {
            return str;
        }
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        if (!str.startsWith("pv.") && !str.startsWith("pv-")) {
            if (d2 == null || ServerCompact.c(d2)) {
                return "pv." + str;
            }
            return "pv-" + d2.f1530a + "." + str;
        }
        return str;
    }

    private boolean c(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("processor.smartcamera") || str.startsWith("business.smartcamera") || str.startsWith("camera") || str.startsWith(f);
        }
        return false;
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ServerBean d2 = GlobalDynamicSettingManager.a().d();
        if (str.startsWith("processor.smartcamera") || str.startsWith("business.smartcamera") || str.startsWith("camera") || str.startsWith(f)) {
            return true;
        }
        if (d2 == null || TextUtils.isEmpty(d2.f1530a)) {
            return false;
        }
        if (!str.startsWith(d2.f1530a + "." + "processor.smartcamera")) {
            if (!str.startsWith(d2.f1530a + "." + "business.smartcamera")) {
                if (!str.startsWith(d2.f1530a + "." + "camera")) {
                    if (str.startsWith(d2.f1530a + "." + f)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private String a(NetRequest netRequest) {
        if (netRequest == null || TextUtils.isEmpty(netRequest.c())) {
            return c() + ConnectionHelper.I + netRequest.b();
        }
        return a(netRequest.c()) + netRequest.b();
    }

    private void d() {
        ServerBean a2 = ServerCompact.a(this.t);
        if (a2 != null) {
            CookieUtil.a(this.m, c(), Constant.KEY_COUNTRY_CODE, a2.b, ".io.mi.com", "/");
        }
    }

    private void e() {
        Locale g2 = GlobalDynamicSettingManager.a().g();
        if (g2 == null) {
            Locale b2 = LocaleUtil.b();
            Log.i("SmartHomeApi", "setLocaleCookie locale=null getDefault:" + b2);
            CookieUtil.a(this.m, c(), "locale", LocaleUtil.a(b2), ".io.mi.com", "/");
            return;
        }
        Log.i("SmartHomeApi", "setLocaleCookie:" + g2);
        CookieUtil.a(this.m, c(), "locale", LocaleUtil.a(g2), ".io.mi.com", "/");
    }

    private void f() {
        CookieUtil.a(this.m, c(), "channel", GlobalSetting.v, ".io.mi.com", "/");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        /*
            r17 = this;
            r0 = r17
            java.lang.String r1 = ""
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()
            r3 = 0
            com.xiaomi.smarthome.library.commonapi.SystemApi r4 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            java.lang.String r4 = r4.m()     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            java.lang.String r5 = "UTF-8"
            java.lang.String r4 = java.net.URLEncoder.encode(r4, r5)     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            boolean r1 = r2.useDaylightTime()     // Catch:{ UnsupportedEncodingException -> 0x0023 }
            int r5 = r2.getDSTSavings()     // Catch:{ UnsupportedEncodingException -> 0x0024 }
            r8 = r4
            r3 = r5
            goto L_0x0025
        L_0x0022:
            r4 = r1
        L_0x0023:
            r1 = 0
        L_0x0024:
            r8 = r4
        L_0x0025:
            if (r2 == 0) goto L_0x003a
            java.net.CookieManager r9 = r0.m
            java.lang.String r10 = r17.c()
            java.lang.String r11 = "timezone_id"
            java.lang.String r12 = r2.getID()
            java.lang.String r13 = ".io.mi.com"
            java.lang.String r14 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r9, r10, r11, r12, r13, r14)
        L_0x003a:
            java.net.CookieManager r5 = r0.m
            java.lang.String r6 = r17.c()
            java.lang.String r7 = "timezone"
            java.lang.String r9 = ".io.mi.com"
            java.lang.String r10 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r5, r6, r7, r8, r9, r10)
            java.net.CookieManager r11 = r0.m
            java.lang.String r12 = r17.c()
            java.lang.String r13 = "is_daylight"
            if (r1 == 0) goto L_0x0057
            java.lang.String r1 = "1"
        L_0x0055:
            r14 = r1
            goto L_0x005a
        L_0x0057:
            java.lang.String r1 = "0"
            goto L_0x0055
        L_0x005a:
            java.lang.String r15 = ".io.mi.com"
            java.lang.String r16 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r11, r12, r13, r14, r15, r16)
            java.net.CookieManager r4 = r0.m
            java.lang.String r5 = r17.c()
            java.lang.String r6 = "dst_offset"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r3)
            java.lang.String r7 = r1.toString()
            java.lang.String r8 = ".io.mi.com"
            java.lang.String r9 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r4, r5, r6, r7, r8, r9)
            com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil r1 = new com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil
            android.content.Context r2 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()
            r1.<init>(r2)
            java.lang.String r6 = r1.getHashedDeviceIdNoThrow()
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L_0x00a3
            java.net.CookieManager r3 = r0.m
            java.lang.String r4 = r17.c()
            java.lang.String r5 = "PassportDeviceId"
            java.lang.String r7 = ".io.mi.com"
            java.lang.String r8 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r3, r4, r5, r6, r7, r8)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api.g():void");
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static String b(String str, String str2) {
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(Coder.a(str2), "HmacSHA256"));
            return Coder.a(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0051 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>, java.lang.String> b(com.xiaomi.smarthome.core.entity.net.NetRequest r12) {
        /*
            r11 = this;
            java.util.List r0 = r12.d()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r1 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r2 = "X-XIAOMI-PROTOCAL-FLAG-CLI"
            java.lang.String r3 = "PROTOCAL-HTTP2"
            r1.<init>(r2, r3)
            r0.add(r1)
            long r0 = java.lang.System.currentTimeMillis()
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r11.j
            long r2 = r2.e
            long r0 = r0 + r2
            java.lang.String r0 = com.xiaomi.smarthome.library.crypto.CloudCoder.b(r0)
            r1 = 0
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r11.j     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            java.lang.String r2 = r2.d     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r0)     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            byte[] r2 = r11.a((byte[]) r2, (byte[]) r3)     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            java.lang.String r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((byte[]) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0047, InvalidKeyException -> 0x003f, Exception -> 0x0037 }
            goto L_0x004f
        L_0x0037:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail"
            android.util.Log.d(r2, r3)
            goto L_0x004e
        L_0x003f:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail:InvalidKeyException"
            android.util.Log.d(r2, r3)
            goto L_0x004e
        L_0x0047:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail:NoSuchAlgorithmException"
            android.util.Log.d(r2, r3)
        L_0x004e:
            r2 = r1
        L_0x004f:
            if (r2 != 0) goto L_0x0052
            return r1
        L_0x0052:
            java.util.TreeMap r1 = new java.util.TreeMap
            r1.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r4 = r12.e()
            if (r4 == 0) goto L_0x0092
            java.util.Iterator r4 = r4.iterator()
        L_0x0066:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0092
            java.lang.Object r5 = r4.next()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r5 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r5
            java.lang.String r6 = r5.a()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0066
            java.lang.String r6 = r5.b()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0066
            java.lang.String r6 = r5.a()
            java.lang.String r5 = r5.b()
            r1.put(r6, r5)
            goto L_0x0066
        L_0x0092:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.String r5 = r12.b()
            if (r5 == 0) goto L_0x00a4
            java.lang.String r5 = r12.b()
            r4.add(r5)
        L_0x00a4:
            r4.add(r2)
            r4.add(r0)
            boolean r5 = r1.isEmpty()
            r6 = 0
            r7 = 1
            if (r5 != 0) goto L_0x00e4
            java.util.TreeMap r5 = new java.util.TreeMap
            r5.<init>(r1)
            java.util.Set r1 = r5.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00bf:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x00e9
            java.lang.Object r5 = r1.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.String r8 = "%s=%s"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.Object r10 = r5.getKey()
            r9[r6] = r10
            java.lang.Object r5 = r5.getValue()
            r9[r7] = r5
            java.lang.String r5 = java.lang.String.format(r8, r9)
            r4.add(r5)
            goto L_0x00bf
        L_0x00e4:
            java.lang.String r1 = "data="
            r4.add(r1)
        L_0x00e9:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x00f2:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x010a
            java.lang.Object r5 = r4.next()
            java.lang.String r5 = (java.lang.String) r5
            if (r7 != 0) goto L_0x0105
            r7 = 38
            r1.append(r7)
        L_0x0105:
            r1.append(r5)
            r7 = 0
            goto L_0x00f2
        L_0x010a:
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = b(r1, r2)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r2 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r4 = "signature"
            r2.<init>(r4, r1)
            r3.add(r2)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r1 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r2 = "_nonce"
            r1.<init>(r2, r0)
            r3.add(r1)
            java.util.List r12 = r12.e()
            r3.addAll(r12)
            android.util.Pair r12 = android.util.Pair.create(r3, r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api.b(com.xiaomi.smarthome.core.entity.net.NetRequest):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>, java.lang.String> c(com.xiaomi.smarthome.core.entity.net.NetRequest r10) {
        /*
            r9 = this;
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r9.j
            long r0 = r0.e
            java.lang.String r0 = com.xiaomi.smarthome.library.crypto.CloudCoder.a((long) r0)
            r1 = 0
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r9.j     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            java.lang.String r2 = r2.d     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r0)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = r9.a((byte[]) r2, (byte[]) r3)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            byte[] r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            java.lang.String r2 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((byte[]) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0032, InvalidKeyException -> 0x002a, Exception -> 0x0022 }
            goto L_0x003a
        L_0x0022:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail"
            android.util.Log.d(r2, r3)
            goto L_0x0039
        L_0x002a:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail:InvalidKeyException"
            android.util.Log.d(r2, r3)
            goto L_0x0039
        L_0x0032:
            java.lang.String r2 = "SmartHomeApi"
            java.lang.String r3 = "generate sessionSecurity fail:NoSuchAlgorithmException"
            android.util.Log.d(r2, r3)
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
            java.util.List r6 = r10.e()
            if (r6 == 0) goto L_0x0087
            java.util.Iterator r6 = r6.iterator()
        L_0x005b:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0087
            java.lang.Object r7 = r6.next()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r7 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r7
            java.lang.String r8 = r7.a()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x005b
            java.lang.String r8 = r7.b()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x005b
            java.lang.String r8 = r7.a()
            java.lang.String r7 = r7.b()
            r3.put(r8, r7)
            goto L_0x005b
        L_0x0087:
            java.lang.String r6 = r10.a()
            java.lang.String r7 = r10.b()
            java.lang.String r6 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r6, r7, r3, r2)
            java.lang.String r7 = "rc4_hash__"
            r3.put(r7, r6)
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x00a0:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x00cc
            java.lang.Object r6 = r3.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getValue()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r7 = r5.b((java.lang.String) r7)
            java.lang.Object r8 = r6.getKey()
            r1.put(r8, r7)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.Object r6 = r6.getKey()
            java.lang.String r6 = (java.lang.String) r6
            r8.<init>(r6, r7)
            r4.add(r8)
            goto L_0x00a0
        L_0x00cc:
            java.lang.String r3 = r10.a()
            java.lang.String r10 = r10.b()
            java.lang.String r10 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r3, r10, r1, r2)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r1 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r2 = "signature"
            r1.<init>(r2, r10)
            r4.add(r1)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r10 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "_nonce"
            r10.<init>(r1, r0)
            r4.add(r10)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r10 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "ssecurity"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r9.j
            java.lang.String r2 = r2.d
            r10.<init>(r1, r2)
            r4.add(r10)
            android.util.Pair r10 = android.util.Pair.create(r4, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api.c(com.xiaomi.smarthome.core.entity.net.NetRequest):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public String a(String str, String str2) throws SecurityException {
        try {
            String a2 = Coder.a(Coder.d(a(Coder.a(this.j.d), Coder.a(str2))));
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

    /* access modifiers changed from: private */
    public void c(String str, String str2) {
        boolean z;
        String str3;
        Throwable th;
        try {
            ServerBean d2 = GlobalDynamicSettingManager.a().d();
            if (!GlobalSetting.u) {
                ServerCompact.b(d2);
                z = true;
            } else {
                z = false;
            }
            if (ServerCompact.d(d2)) {
                z = false;
            }
            if (z) {
                StringBuilder sb = new StringBuilder();
                sb.append("url:" + str + "\r\n");
                sb.append("userId:" + str2 + "\r\n");
                sb.append("UA:" + UserAgentUtil.a(ServiceApplication.getAppContext()) + "\r\n");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("server:");
                if (d2 == null) {
                    str3 = null;
                } else {
                    str3 = d2.b + ":" + d2.f1530a;
                }
                sb2.append(str3);
                sb.append(sb2.toString());
                if (ServerCompact.b(d2)) {
                    th = new InternationalUnauthorizedException(sb.toString());
                } else {
                    th = new UnauthorizedException(sb.toString());
                }
                CrashReport.postCatchedException(th);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:42|43|128|(2:49|50)|51|74) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0128, code lost:
        monitor-enter(r11.n);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r11.o = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012c, code lost:
        if (r0 != false) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        com.xiaomi.smarthome.core.server.internal.account.AccountManager.a().p();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0135, code lost:
        a(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x0126 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(final java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0019
            java.lang.String r1 = "0"
            boolean r1 = android.text.TextUtils.equals(r1, r0)
            if (r1 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r3 = r0
            goto L_0x0022
        L_0x0019:
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r0 = r0.m()
            goto L_0x0017
        L_0x0022:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.v()
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r5 = r1.w()
            r7 = 4
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x013f }
            r1.<init>(r13)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r2 = "code"
            int r1 = r1.optInt(r2)     // Catch:{ JSONException -> 0x013f }
            r8 = 2
            if (r1 != r8) goto L_0x00e3
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ JSONException -> 0x013f }
            r2.<init>()     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = "url"
            r2.put(r4, r12)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = "response"
            r2.put(r4, r13)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = "uid"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x013f }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r4 = r11.j     // Catch:{ JSONException -> 0x013f }
            if (r4 != 0) goto L_0x005c
            java.lang.String r4 = ""
            goto L_0x0060
        L_0x005c:
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r4 = r11.j     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = r4.c     // Catch:{ JSONException -> 0x013f }
        L_0x0060:
            java.lang.String r6 = "serviceToken"
            r2.put(r6, r4)     // Catch:{ JSONException -> 0x013f }
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x013f }
            java.util.List r6 = r6.x()     // Catch:{ JSONException -> 0x013f }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x013f }
            r9.<init>()     // Catch:{ JSONException -> 0x013f }
            r9.append(r12)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r10 = ";"
            r9.append(r10)     // Catch:{ JSONException -> 0x013f }
            r9.append(r13)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r13 = ";"
            r9.append(r13)     // Catch:{ JSONException -> 0x013f }
            r9.append(r3)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r13 = ";"
            r9.append(r13)     // Catch:{ JSONException -> 0x013f }
            r9.append(r4)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r13 = ";"
            r9.append(r13)     // Catch:{ JSONException -> 0x013f }
            java.util.Iterator r13 = r6.iterator()     // Catch:{ JSONException -> 0x013f }
        L_0x0096:
            boolean r4 = r13.hasNext()     // Catch:{ JSONException -> 0x013f }
            if (r4 == 0) goto L_0x00b7
            java.lang.Object r4 = r13.next()     // Catch:{ JSONException -> 0x013f }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r4 = (com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo) r4     // Catch:{ JSONException -> 0x013f }
            java.lang.String r6 = r4.f23514a     // Catch:{ JSONException -> 0x013f }
            r9.append(r6)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r6 = ":"
            r9.append(r6)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = r4.c     // Catch:{ JSONException -> 0x013f }
            r9.append(r4)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r4 = ";"
            r9.append(r4)     // Catch:{ JSONException -> 0x013f }
            goto L_0x0096
        L_0x00b7:
            java.lang.String r13 = "all"
            java.lang.String r4 = r9.toString()     // Catch:{ JSONException -> 0x013f }
            r2.put(r13, r4)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r13 = "click"
            java.lang.String r4 = "app_401_errorcode2"
            com.xiaomi.mistatistic.sdk.MiStatInterface.a((java.lang.String) r13, (java.lang.String) r4, (java.util.Map<java.lang.String, java.lang.String>) r2)     // Catch:{ JSONException -> 0x013f }
            com.xiaomi.smarthome.frame.log.MyLogger r13 = com.xiaomi.smarthome.frame.log.MyLogger.a()     // Catch:{ JSONException -> 0x013f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x013f }
            r4.<init>()     // Catch:{ JSONException -> 0x013f }
            java.lang.String r6 = "app_401_errorcode2 "
            r4.append(r6)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x013f }
            r4.append(r2)     // Catch:{ JSONException -> 0x013f }
            java.lang.String r2 = r4.toString()     // Catch:{ JSONException -> 0x013f }
            r13.a((java.lang.String) r2)     // Catch:{ JSONException -> 0x013f }
        L_0x00e3:
            r13 = 3
            if (r1 == r13) goto L_0x00f6
            if (r1 != r7) goto L_0x00e9
            goto L_0x00f6
        L_0x00e9:
            if (r0 == 0) goto L_0x00f2
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r12 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ JSONException -> 0x013f }
            r12.p()     // Catch:{ JSONException -> 0x013f }
        L_0x00f2:
            r11.a((int) r13)     // Catch:{ JSONException -> 0x013f }
            goto L_0x014b
        L_0x00f6:
            java.lang.Object r13 = r11.n     // Catch:{ JSONException -> 0x013f }
            monitor-enter(r13)     // Catch:{ JSONException -> 0x013f }
            boolean r1 = r11.o     // Catch:{ all -> 0x013c }
            r2 = 1
            r9 = 0
            if (r1 == 0) goto L_0x0100
            goto L_0x0103
        L_0x0100:
            r11.o = r2     // Catch:{ all -> 0x013c }
            r2 = 0
        L_0x0103:
            monitor-exit(r13)     // Catch:{ all -> 0x013c }
            if (r2 == 0) goto L_0x0107
            return
        L_0x0107:
            com.xiaomi.smarthome.core.server.CoreManager r13 = com.xiaomi.smarthome.core.server.CoreManager.a()     // Catch:{ JSONException -> 0x013f }
            java.lang.String r1 = "com.xiaomi.smarthome"
            com.xiaomi.smarthome.core.client.IClientApi r1 = r13.a((java.lang.String) r1)     // Catch:{ JSONException -> 0x013f }
            if (r0 == 0) goto L_0x011a
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r13 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ Exception -> 0x0126 }
            r13.p()     // Catch:{ Exception -> 0x0126 }
        L_0x011a:
            java.lang.String r2 = "xiaomiio"
            com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$2 r6 = new com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$2     // Catch:{ Exception -> 0x0126 }
            r6.<init>(r12, r3)     // Catch:{ Exception -> 0x0126 }
            r4 = r0
            r1.refreshServiceToken(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0126 }
            goto L_0x014b
        L_0x0126:
            java.lang.Object r12 = r11.n     // Catch:{ JSONException -> 0x013f }
            monitor-enter(r12)     // Catch:{ JSONException -> 0x013f }
            r11.o = r9     // Catch:{ all -> 0x0139 }
            monitor-exit(r12)     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x0135
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r12 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ JSONException -> 0x013f }
            r12.p()     // Catch:{ JSONException -> 0x013f }
        L_0x0135:
            r11.a((int) r8)     // Catch:{ JSONException -> 0x013f }
            goto L_0x014b
        L_0x0139:
            r13 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0139 }
            throw r13     // Catch:{ JSONException -> 0x013f }
        L_0x013c:
            r12 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x013c }
            throw r12     // Catch:{ JSONException -> 0x013f }
        L_0x013f:
            if (r0 == 0) goto L_0x0148
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r12 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            r12.p()
        L_0x0148:
            r11.a((int) r7)
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api.d(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        MyLogger a2 = MyLogger.a();
        a2.a("app_401_errorcode3 doUnAuthorized from=" + i2);
        AccountManager.a().i();
        a(false);
        for (IClientApi onUnAuthorized : CoreManager.a().d()) {
            try {
                onUnAuthorized.onUnAuthorized();
            } catch (Exception unused) {
            }
        }
    }

    private void h() {
        if (GlobalSetting.u || GlobalSetting.q) {
            try {
                int queuedCallsCount = this.s.queuedCallsCount();
                if (queuedCallsCount >= 15) {
                    int runningCallsCount = this.s.runningCallsCount();
                    MyLogger a2 = MyLogger.a();
                    a2.a("okhttp queuedCallsCount=" + queuedCallsCount + ",runningCallsCount=" + runningCallsCount);
                    Log.d("forceUpdateAllData", "okhttp queuedCallsCount=" + queuedCallsCount + ",runningCallsCount=" + runningCallsCount);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Request request, long j2) {
        if (j2 < 600) {
            return;
        }
        if (GlobalSetting.u || GlobalSetting.q) {
            try {
                String str = request.url().toString() + " takes " + j2 + "ms to get response";
                MyLogger.a().a(str);
                Log.d("forceUpdateAllData", str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0292  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.core.server.internal.NetHandle a(com.xiaomi.smarthome.core.entity.net.NetRequest r23, com.xiaomi.smarthome.core.server.internal.NetCallback<com.xiaomi.smarthome.core.entity.net.NetResult, com.xiaomi.smarthome.core.entity.net.NetError> r24) {
        /*
            r22 = this;
            r9 = r22
            r6 = r24
            r22.h()
            r1 = 0
            if (r23 != 0) goto L_0x0022
            if (r6 == 0) goto L_0x001c
            com.xiaomi.smarthome.core.entity.net.NetError r0 = new com.xiaomi.smarthome.core.entity.net.NetError
            com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r2 = r2.getCode()
            java.lang.String r3 = "netRequest is null"
            r0.<init>(r2, r3)
            r6.a(r0)
        L_0x001c:
            com.xiaomi.smarthome.core.server.internal.NetHandle r0 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r0.<init>(r1)
            return r0
        L_0x0022:
            java.lang.String r2 = r22.a((com.xiaomi.smarthome.core.entity.net.NetRequest) r23)
            boolean r0 = r22.b()
            r3 = 1
            if (r0 != 0) goto L_0x00f5
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            boolean r0 = r0.l()
            if (r0 != 0) goto L_0x004f
            if (r6 == 0) goto L_0x0049
            com.xiaomi.smarthome.core.entity.net.NetError r0 = new com.xiaomi.smarthome.core.entity.net.NetError
            com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r2 = r2.getCode()
            java.lang.String r3 = "not loggedin"
            r0.<init>(r2, r3)
            r6.a(r0)
        L_0x0049:
            com.xiaomi.smarthome.core.server.internal.NetHandle r0 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r0.<init>(r1)
            return r0
        L_0x004f:
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r13 = r0.m()
            com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager r0 = com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r0.d()
            boolean r0 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.l((com.xiaomi.smarthome.frame.server_compact.ServerBean) r0)
            if (r0 == 0) goto L_0x0072
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r4 = "miot-third-test"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r0.a((java.lang.String) r4)
            r9.j = r0
            goto L_0x007e
        L_0x0072:
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r4 = "xiaomiio"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r0.a((java.lang.String) r4)
            r9.j = r0
        L_0x007e:
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L_0x00dd
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r9.j
            if (r0 != 0) goto L_0x0089
            goto L_0x00dd
        L_0x0089:
            java.net.CookieManager r0 = r9.m
            com.xiaomi.smarthome.library.http.util.CookieUtil.a((java.net.CookieManager) r0)
            java.net.CookieManager r10 = r9.m
            java.lang.String r11 = r22.c()
            java.lang.String r12 = "userId"
            java.lang.String r14 = ".io.mi.com"
            java.lang.String r15 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r10, r11, r12, r13, r14, r15)
            java.net.CookieManager r0 = r9.m
            java.lang.String r17 = r22.c()
            java.lang.String r18 = "yetAnotherServiceToken"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r4 = r9.j
            java.lang.String r4 = r4.c
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r7 = r9.j
            java.lang.String r7 = r7.f
            java.lang.String r21 = "/"
            r16 = r0
            r19 = r4
            r20 = r7
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r16, r17, r18, r19, r20, r21)
            java.net.CookieManager r10 = r9.m
            java.lang.String r11 = r22.c()
            java.lang.String r12 = "serviceToken"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r9.j
            java.lang.String r13 = r0.c
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r9.j
            java.lang.String r14 = r0.f
            java.lang.String r15 = "/"
            com.xiaomi.smarthome.library.http.util.CookieUtil.a(r10, r11, r12, r13, r14, r15)
            r22.d()
            r22.e()
            r22.g()
            r22.f()
            r9.a((boolean) r3)
            goto L_0x00f5
        L_0x00dd:
            if (r6 == 0) goto L_0x00ef
            com.xiaomi.smarthome.core.entity.net.NetError r0 = new com.xiaomi.smarthome.core.entity.net.NetError
            com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r2 = r2.getCode()
            java.lang.String r3 = "uid or serviceToken is null!"
            r0.<init>(r2, r3)
            r6.a(r0)
        L_0x00ef:
            com.xiaomi.smarthome.core.server.internal.NetHandle r0 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r0.<init>(r1)
            return r0
        L_0x00f5:
            java.lang.String r0 = r23.c()
            if (r0 == 0) goto L_0x0133
            java.lang.String r0 = r23.c()
            java.lang.String r4 = "connect.camera"
            boolean r0 = r0.startsWith(r4)
            if (r0 != 0) goto L_0x012b
            java.lang.String r0 = r23.c()
            java.lang.String r4 = "processor.smartcamera"
            boolean r0 = r0.startsWith(r4)
            if (r0 != 0) goto L_0x012b
            java.lang.String r0 = r23.c()
            java.lang.String r4 = "business.smartcamera"
            boolean r0 = r0.startsWith(r4)
            if (r0 != 0) goto L_0x012b
            java.lang.String r0 = r23.c()
            java.lang.String r4 = "camera"
            boolean r0 = r0.startsWith(r4)
            if (r0 == 0) goto L_0x0133
        L_0x012b:
            android.util.Pair r0 = r22.c((com.xiaomi.smarthome.core.entity.net.NetRequest) r23)
            r3 = 0
            r7 = r0
            r8 = 0
            goto L_0x0139
        L_0x0133:
            android.util.Pair r0 = r22.b((com.xiaomi.smarthome.core.entity.net.NetRequest) r23)
            r7 = r0
            r8 = 1
        L_0x0139:
            if (r7 != 0) goto L_0x0153
            if (r6 == 0) goto L_0x014d
            com.xiaomi.smarthome.core.entity.net.NetError r0 = new com.xiaomi.smarthome.core.entity.net.NetError
            com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r2 = r2.getCode()
            java.lang.String r3 = "pair == null"
            r0.<init>(r2, r3)
            r6.a(r0)
        L_0x014d:
            com.xiaomi.smarthome.core.server.internal.NetHandle r0 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r0.<init>(r1)
            return r0
        L_0x0153:
            java.lang.String r0 = r23.a()
            java.lang.String r3 = "POST"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0249
            java.util.List r0 = r23.f()
            if (r0 == 0) goto L_0x021a
            java.util.List r0 = r23.f()
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x021a
            okhttp3.MultipartBody$Builder r3 = new okhttp3.MultipartBody$Builder
            r3.<init>()
            okhttp3.MediaType r0 = okhttp3.MultipartBody.FORM
            r3.setType(r0)
            java.util.List r0 = r23.f()     // Catch:{ Exception -> 0x01be }
            if (r0 == 0) goto L_0x01d9
            int r4 = r0.size()     // Catch:{ Exception -> 0x01be }
            if (r4 <= 0) goto L_0x01d9
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x01be }
        L_0x0189:
            boolean r4 = r0.hasNext()     // Catch:{ Exception -> 0x01be }
            if (r4 == 0) goto L_0x01d9
            java.lang.Object r4 = r0.next()     // Catch:{ Exception -> 0x01be }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r4 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r4     // Catch:{ Exception -> 0x01be }
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x01be }
            java.lang.String r4 = r4.b()     // Catch:{ Exception -> 0x01be }
            r10.<init>(r4)     // Catch:{ Exception -> 0x01be }
            boolean r4 = r10.exists()     // Catch:{ Exception -> 0x01be }
            if (r4 == 0) goto L_0x0189
            java.lang.String r4 = r10.getName()     // Catch:{ Exception -> 0x01be }
            java.lang.String r11 = com.xiaomi.smarthome.library.mime.MimeUtils.e(r4)     // Catch:{ Exception -> 0x01be }
            java.lang.String r11 = com.xiaomi.smarthome.library.mime.MimeUtils.b(r11)     // Catch:{ Exception -> 0x01be }
            java.lang.String r12 = "img"
            okhttp3.MediaType r11 = okhttp3.MediaType.parse(r11)     // Catch:{ Exception -> 0x01be }
            okhttp3.RequestBody r10 = okhttp3.MultipartBody.create((okhttp3.MediaType) r11, (java.io.File) r10)     // Catch:{ Exception -> 0x01be }
            r3.addFormDataPart(r12, r4, r10)     // Catch:{ Exception -> 0x01be }
            goto L_0x0189
        L_0x01be:
            r0 = move-exception
            java.lang.String r4 = "SmartHomeApi"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "MultipartBody exception:"
            r10.append(r11)
            java.lang.String r0 = r0.getLocalizedMessage()
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r4, r0)
        L_0x01d9:
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.Object r2 = r7.first
            java.util.List r2 = (java.util.List) r2
            java.lang.String r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.c(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            okhttp3.Request$Builder r0 = r0.url((java.lang.String) r2)
            java.util.List r2 = r23.d()
            okhttp3.Headers r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a(r2)
            okhttp3.Request$Builder r0 = r0.headers(r2)
            okhttp3.MultipartBody r2 = r3.build()
            okhttp3.Request$Builder r0 = r0.post(r2)
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r9.j
            java.lang.String r2 = r2.d
            okhttp3.Request$Builder r0 = r0.tag(r2)
            okhttp3.Request r0 = r0.build()
            goto L_0x0247
        L_0x021a:
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            okhttp3.Request$Builder r0 = r0.url((java.lang.String) r2)
            java.util.List r2 = r23.d()
            okhttp3.Headers r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a(r2)
            okhttp3.Request$Builder r0 = r0.headers(r2)
            java.lang.Object r2 = r7.first
            java.util.List r2 = (java.util.List) r2
            okhttp3.RequestBody r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.b(r2)
            okhttp3.Request$Builder r0 = r0.post(r2)
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r9.j
            java.lang.String r2 = r2.d
            okhttp3.Request$Builder r0 = r0.tag(r2)
            okhttp3.Request r0 = r0.build()
        L_0x0247:
            r4 = r0
            goto L_0x0278
        L_0x0249:
            java.lang.String r0 = r23.a()
            java.lang.String r3 = "GET"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0277
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            java.lang.Object r3 = r7.first
            java.util.List r3 = (java.util.List) r3
            java.lang.String r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r2, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r3)
            okhttp3.Request$Builder r0 = r0.url((java.lang.String) r2)
            java.util.List r2 = r23.d()
            okhttp3.Headers r2 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a(r2)
            okhttp3.Request$Builder r0 = r0.headers(r2)
            okhttp3.Request r0 = r0.build()
            goto L_0x0247
        L_0x0277:
            r4 = r1
        L_0x0278:
            if (r4 != 0) goto L_0x0292
            if (r6 == 0) goto L_0x028c
            com.xiaomi.smarthome.core.entity.net.NetError r0 = new com.xiaomi.smarthome.core.entity.net.NetError
            com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r2 = r2.getCode()
            java.lang.String r3 = "request == null"
            r0.<init>(r2, r3)
            r6.a(r0)
        L_0x028c:
            com.xiaomi.smarthome.core.server.internal.NetHandle r0 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r0.<init>(r1)
            return r0
        L_0x0292:
            boolean r0 = com.xiaomi.smarthome.frame.HostSetting.g
            if (r0 != 0) goto L_0x029a
            boolean r0 = com.xiaomi.smarthome.frame.HostSetting.i
            if (r0 == 0) goto L_0x02c9
        L_0x029a:
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = "BootRequestCheck"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = " SmartHomeRc4Api.sendRequest "
            r3.append(r0)
            java.lang.String r0 = r4.toString()
            r3.append(r0)
            java.lang.String r0 = " "
            r3.append(r0)
            android.content.Context r0 = r9.t
            java.lang.String r0 = com.xiaomi.smarthome.frame.process.ProcessUtil.g(r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r2, r0)
        L_0x02c9:
            com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$RequestRecord r3 = new com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$RequestRecord
            r3.<init>()
            okhttp3.OkHttpClient r0 = r9.l
            okhttp3.Call r0 = r0.newCall(r4)
            com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$3 r10 = new com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$3
            r1 = r10
            r2 = r22
            r5 = r23
            r6 = r24
            r1.<init>(r3, r4, r5, r6, r7, r8)
            r0.enqueue(r10)
            com.xiaomi.smarthome.core.server.internal.NetHandle r1 = new com.xiaomi.smarthome.core.server.internal.NetHandle
            r1.<init>(r0)
            android.os.Handler r0 = com.xiaomi.smarthome.application.ServiceApplication.getGlobalWorkerHandler()
            com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$4 r2 = new com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api$4
            r2.<init>()
            r3 = 1000(0x3e8, double:4.94E-321)
            r0.postDelayed(r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api.a(com.xiaomi.smarthome.core.entity.net.NetRequest, com.xiaomi.smarthome.core.server.internal.NetCallback):com.xiaomi.smarthome.core.server.internal.NetHandle");
    }

    /* access modifiers changed from: private */
    public void i() {
        boolean z;
        if (ProcessUtil.n(this.t)) {
            synchronized (this.r) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.p == 0 || currentTimeMillis >= this.p + this.q) {
                    this.p = System.currentTimeMillis();
                    this.q *= 2;
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                CommonApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        Toast.makeText(SmartHomeRc4Api.this.t, R.string.network_fake_connected, 0).show();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        synchronized (this.r) {
            this.p = 0;
            this.q = 10000;
        }
    }

    class RequestRecord {

        /* renamed from: a  reason: collision with root package name */
        boolean f14092a = false;

        RequestRecord() {
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean a() {
            return this.f14092a;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(boolean z) {
            this.f14092a = z;
        }
    }
}
