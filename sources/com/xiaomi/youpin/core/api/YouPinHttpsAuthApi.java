package com.xiaomi.youpin.core.api;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.mi.global.shop.util.ConnectionHelper;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.util.WebViewCookieManager;
import com.xiaomi.youpin.core.apicache.YouPinHttpsAuthCache;
import com.xiaomi.youpin.core.net.KeyValuePairUtil;
import com.xiaomi.youpin.core.net.NetCallback;
import com.xiaomi.youpin.core.net.NetError;
import com.xiaomi.youpin.core.net.NetHandle;
import com.xiaomi.youpin.core.net.NetRequest;
import com.xiaomi.youpin.core.net.NetResult;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.utils.LogUtils;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public class YouPinHttpsAuthApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23330a = "https://shopapi.io.mi.com";
    private static final String b = "https://st.shopapi.io.mi.com";
    private static YouPinHttpsAuthApi c;
    private static Object d = new Object();
    private boolean e = false;
    private OkHttpClient f = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(this.g)).addNetworkInterceptor(new Interceptor() {
        public Response intercept(Interceptor.Chain chain) throws IOException {
            IMiotStoreApi a2 = MiotStoreApi.a();
            if (a2 == null) {
                return chain.proceed(chain.request());
            }
            return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", a2.getUserAgent()).build());
        }
    }).build();
    private CookieHandler g = new CookieHandler();
    /* access modifiers changed from: private */
    public MiServiceTokenInfo h;
    /* access modifiers changed from: private */
    public Object i = new Object();
    /* access modifiers changed from: private */
    public boolean j = false;

    private YouPinHttpsAuthApi() {
    }

    public static YouPinHttpsAuthApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new YouPinHttpsAuthApi();
                }
            }
        }
        return c;
    }

    public boolean b() {
        boolean z;
        synchronized (d) {
            z = this.e;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (d) {
            this.e = z;
        }
    }

    private String c() {
        return GlobalSetting.C ? b : "https://shopapi.io.mi.com";
    }

    private String a(NetRequest netRequest) {
        if (netRequest.b().startsWith("http:") || netRequest.b().startsWith("https:")) {
            return netRequest.b();
        }
        if (netRequest.b().startsWith("/homepage")) {
            return c() + netRequest.b();
        }
        return c() + ConnectionHelper.I + netRequest.b();
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public void a(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.h()
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r0.y()
            if (r0 != 0) goto L_0x002c
            java.lang.String r11 = "***********401 but miAccount null***********"
            com.xiaomi.smarthome.framework.log.MyLog.d(r11)
            java.lang.String r11 = "info"
            java.lang.String r12 = "401 but miAccount null"
            com.tencent.bugly.crashreport.BuglyLog.d(r11, r12)
            java.lang.Throwable r11 = new java.lang.Throwable
            java.lang.String r12 = "401 but miAccount null"
            r11.<init>(r12)
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r11)
            return
        L_0x002c:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r0.s()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r4 = r0.v()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r5 = r0.w()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0287 }
            r0.<init>(r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "code"
            int r0 = r0.optInt(r1)     // Catch:{ JSONException -> 0x0287 }
            r1 = 2
            if (r0 != r1) goto L_0x00d8
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ JSONException -> 0x0287 }
            r1.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = "url"
            r1.put(r2, r11)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = "response"
            r1.put(r2, r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = "uid"
            r1.put(r2, r3)     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r10.h     // Catch:{ JSONException -> 0x0287 }
            if (r2 != 0) goto L_0x006d
            java.lang.String r2 = ""
            goto L_0x0071
        L_0x006d:
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r2 = r10.h     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = r2.c     // Catch:{ JSONException -> 0x0287 }
        L_0x0071:
            java.lang.String r6 = "serviceToken"
            r1.put(r6, r2)     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            java.util.List r6 = r6.x()     // Catch:{ JSONException -> 0x0287 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r7.<init>()     // Catch:{ JSONException -> 0x0287 }
            r7.append(r11)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = ";"
            r7.append(r11)     // Catch:{ JSONException -> 0x0287 }
            r7.append(r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = ";"
            r7.append(r11)     // Catch:{ JSONException -> 0x0287 }
            r7.append(r3)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = ";"
            r7.append(r11)     // Catch:{ JSONException -> 0x0287 }
            r7.append(r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = ";"
            r7.append(r11)     // Catch:{ JSONException -> 0x0287 }
            java.util.Iterator r11 = r6.iterator()     // Catch:{ JSONException -> 0x0287 }
        L_0x00a7:
            boolean r12 = r11.hasNext()     // Catch:{ JSONException -> 0x0287 }
            if (r12 == 0) goto L_0x00c8
            java.lang.Object r12 = r11.next()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r12 = (com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo) r12     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = r12.f23514a     // Catch:{ JSONException -> 0x0287 }
            r7.append(r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = ":"
            r7.append(r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r12 = r12.c     // Catch:{ JSONException -> 0x0287 }
            r7.append(r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r12 = ";"
            r7.append(r12)     // Catch:{ JSONException -> 0x0287 }
            goto L_0x00a7
        L_0x00c8:
            java.lang.String r11 = "all"
            java.lang.String r12 = r7.toString()     // Catch:{ JSONException -> 0x0287 }
            r1.put(r11, r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = "click"
            java.lang.String r12 = "app_401_errorcode2"
            com.xiaomi.mistatistic.sdk.MiStatInterface.a((java.lang.String) r11, (java.lang.String) r12, (java.util.Map<java.lang.String, java.lang.String>) r1)     // Catch:{ JSONException -> 0x0287 }
        L_0x00d8:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r11.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r12 = "processUnAuthorized code:"
            r11.append(r12)     // Catch:{ JSONException -> 0x0287 }
            r11.append(r0)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.framework.log.MyLog.d(r11)     // Catch:{ JSONException -> 0x0287 }
            r11 = 3
            if (r0 == r11) goto L_0x0256
            r11 = 4
            if (r0 != r11) goto L_0x00f4
            goto L_0x0256
        L_0x00f4:
            com.xiaomi.smarthome.frame.core.CoreApi r11 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r11 = r11.y()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r12 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "miotstore"
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r12 = r12.a((java.lang.String) r1)     // Catch:{ JSONException -> 0x0287 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r1.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = ""
            r1.append(r2)     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            boolean r2 = r2.q()     // Catch:{ JSONException -> 0x0287 }
            r1.append(r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            r2.s()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = r2.u()     // Catch:{ JSONException -> 0x0287 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r3.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r5 = ""
            r3.append(r5)     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreApi r5 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            boolean r5 = r5.v()     // Catch:{ JSONException -> 0x0287 }
            r3.append(r5)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.miot.store.api.IMiotStoreApi r5 = com.xiaomi.miot.store.api.MiotStoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r5 = r5.getUserAgent()     // Catch:{ JSONException -> 0x0287 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r6.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = ""
            r6.append(r7)     // Catch:{ JSONException -> 0x0287 }
            android.content.Context r7 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.framework.redpoint.ServerTimerManager r7 = com.xiaomi.smarthome.framework.redpoint.ServerTimerManager.a((android.content.Context) r7)     // Catch:{ JSONException -> 0x0287 }
            long r7 = r7.c()     // Catch:{ JSONException -> 0x0287 }
            r6.append(r7)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "-----------------youpin 401 code error start----------------- "
            com.xiaomi.smarthome.framework.log.MyLog.d(r7)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "code"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r8.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r9 = ""
            r8.append(r9)     // Catch:{ JSONException -> 0x0287 }
            r8.append(r0)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r8 = r8.toString()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "isLogin"
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r1)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "ua"
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r5)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "cUserId"
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "timediff"
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "serviceToken"
            if (r12 != 0) goto L_0x01a2
            java.lang.String r8 = "serviceToken is null "
            goto L_0x01a6
        L_0x01a2:
            java.lang.String r8 = r12.toString()     // Catch:{ JSONException -> 0x0287 }
        L_0x01a6:
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "isSystemAccount"
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.String) r3)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "-----------------youpin 401 code error end----------------- "
            com.xiaomi.smarthome.framework.log.MyLog.d(r7)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "mijia"
            java.lang.String r8 = "-----------------youpin 401 code error start----------------- "
            com.tencent.bugly.crashreport.BuglyLog.d(r7, r8)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "code"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0287 }
            r8.<init>()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r9 = ""
            r8.append(r9)     // Catch:{ JSONException -> 0x0287 }
            r8.append(r0)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r8 = r8.toString()     // Catch:{ JSONException -> 0x0287 }
            com.tencent.bugly.crashreport.BuglyLog.d(r7, r8)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r7 = "isLogin"
            com.tencent.bugly.crashreport.BuglyLog.d(r7, r1)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "ua"
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r5)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "cUserId"
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "timediff"
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r6)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = "serviceToken"
            if (r12 != 0) goto L_0x01eb
            java.lang.String r12 = "serviceToken is null "
            goto L_0x01ef
        L_0x01eb:
            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0287 }
        L_0x01ef:
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r12 = "isSystemAccount"
            com.tencent.bugly.crashreport.BuglyLog.d(r12, r3)     // Catch:{ JSONException -> 0x0287 }
            if (r11 == 0) goto L_0x0238
            java.util.List r11 = r11.d()     // Catch:{ JSONException -> 0x0287 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ JSONException -> 0x0287 }
        L_0x0201:
            boolean r12 = r11.hasNext()     // Catch:{ JSONException -> 0x0287 }
            if (r12 == 0) goto L_0x0238
            java.lang.Object r12 = r11.next()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r12 = (com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo) r12     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = r12.f23514a     // Catch:{ JSONException -> 0x0287 }
            if (r1 == 0) goto L_0x0214
            java.lang.String r1 = r12.f23514a     // Catch:{ JSONException -> 0x0287 }
            goto L_0x0216
        L_0x0214:
            java.lang.String r1 = ""
        L_0x0216:
            java.lang.String r2 = r12.c     // Catch:{ JSONException -> 0x0287 }
            if (r2 == 0) goto L_0x021d
            java.lang.String r2 = r12.c     // Catch:{ JSONException -> 0x0287 }
            goto L_0x021f
        L_0x021d:
            java.lang.String r2 = ""
        L_0x021f:
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r2)     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r1 = r12.f23514a     // Catch:{ JSONException -> 0x0287 }
            if (r1 == 0) goto L_0x0229
            java.lang.String r1 = r12.f23514a     // Catch:{ JSONException -> 0x0287 }
            goto L_0x022b
        L_0x0229:
            java.lang.String r1 = ""
        L_0x022b:
            java.lang.String r2 = r12.f     // Catch:{ JSONException -> 0x0287 }
            if (r2 == 0) goto L_0x0232
            java.lang.String r12 = r12.f     // Catch:{ JSONException -> 0x0287 }
            goto L_0x0234
        L_0x0232:
            java.lang.String r12 = ""
        L_0x0234:
            com.tencent.bugly.crashreport.BuglyLog.d(r1, r12)     // Catch:{ JSONException -> 0x0287 }
            goto L_0x0201
        L_0x0238:
            java.lang.String r11 = "mijia"
            java.lang.String r12 = "-----------------youpin 401 code error end----------------- "
            com.tencent.bugly.crashreport.BuglyLog.d(r11, r12)     // Catch:{ JSONException -> 0x0287 }
            java.lang.Throwable r11 = new java.lang.Throwable     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r12 = "youpin 401 code error"
            r11.<init>(r12)     // Catch:{ JSONException -> 0x0287 }
            com.tencent.bugly.crashreport.CrashReport.postCatchedException(r11)     // Catch:{ JSONException -> 0x0287 }
            if (r4 == 0) goto L_0x0252
            com.xiaomi.smarthome.frame.core.CoreApi r11 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0287 }
            r11.A()     // Catch:{ JSONException -> 0x0287 }
        L_0x0252:
            r10.a((int) r0)     // Catch:{ JSONException -> 0x0287 }
            goto L_0x0287
        L_0x0256:
            java.lang.Object r11 = r10.i     // Catch:{ JSONException -> 0x0287 }
            monitor-enter(r11)     // Catch:{ JSONException -> 0x0287 }
            boolean r12 = r10.j     // Catch:{ all -> 0x0284 }
            r1 = 1
            r7 = 0
            if (r12 == 0) goto L_0x0260
            goto L_0x0263
        L_0x0260:
            r10.j = r1     // Catch:{ all -> 0x0284 }
            r1 = 0
        L_0x0263:
            monitor-exit(r11)     // Catch:{ all -> 0x0284 }
            if (r1 == 0) goto L_0x0267
            return
        L_0x0267:
            com.xiaomi.smarthome.frame.FrameManager r11 = com.xiaomi.smarthome.frame.FrameManager.b()     // Catch:{ JSONException -> 0x0287 }
            com.xiaomi.smarthome.frame.core.CoreHostApi r1 = r11.j()     // Catch:{ JSONException -> 0x0287 }
            java.lang.String r2 = "miotstore"
            com.xiaomi.youpin.core.api.YouPinHttpsAuthApi$2 r6 = new com.xiaomi.youpin.core.api.YouPinHttpsAuthApi$2     // Catch:{ Exception -> 0x027a }
            r6.<init>(r0)     // Catch:{ Exception -> 0x027a }
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x027a }
            goto L_0x0287
        L_0x027a:
            java.lang.Object r11 = r10.i     // Catch:{ JSONException -> 0x0287 }
            monitor-enter(r11)     // Catch:{ JSONException -> 0x0287 }
            r10.j = r7     // Catch:{ all -> 0x0281 }
            monitor-exit(r11)     // Catch:{ all -> 0x0281 }
            goto L_0x0287
        L_0x0281:
            r12 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0281 }
            throw r12     // Catch:{ JSONException -> 0x0287 }
        L_0x0284:
            r12 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0284 }
            throw r12     // Catch:{ JSONException -> 0x0287 }
        L_0x0287:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.core.api.YouPinHttpsAuthApi.a(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        try {
            CoreApi.a().a((AsyncCallback<Void, Error>) null);
            a(false);
            LogUtilGrey.a("YouPinHttpsAuthApi", "doUnAuthorized " + i2);
        } catch (Throwable unused) {
        }
    }

    public NetHandle a(NetRequest netRequest, boolean z, NetCallback<NetResult, NetError> netCallback) {
        return a(netRequest, z, false, netCallback);
    }

    public NetHandle a(NetRequest netRequest, boolean z, boolean z2, final NetCallback<NetResult, NetError> netCallback) {
        Request request;
        if (netRequest == null || ServerCompact.e(SHApplication.getAppContext())) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        }
        String a2 = a(netRequest);
        if (!b()) {
            if (CoreApi.a().q()) {
                String s = CoreApi.a().s();
                this.h = CoreApi.a().a("miotstore");
                if (!TextUtils.isEmpty(s) && this.h != null) {
                    WebViewCookieManager.a().a("serviceToken", this.h.c, this.h.f);
                }
            }
            a(true);
        }
        if (netRequest.a().equals("POST")) {
            request = new Request.Builder().url(a2).headers(KeyValuePairUtil.a(netRequest.c())).post(KeyValuePairUtil.a(netRequest.d(), z2, netCallback)).build();
        } else {
            request = netRequest.a().equals("GET") ? new Request.Builder().url(KeyValuePairUtil.a(a2, netRequest.d())).headers(KeyValuePairUtil.a(netRequest.c())).build() : null;
        }
        if (request == null) {
            if (netCallback != null) {
                netCallback.a(new NetError(ErrorCode.INVALID.getCode(), "request == null"));
            }
            return new NetHandle((Call) null);
        }
        LogUtils.b("MijiaShop", "Request:" + netRequest.toString());
        final RequestRecord requestRecord = new RequestRecord();
        final ConditionVariable conditionVariable = new ConditionVariable();
        if (z) {
            YouPinHttpsAuthCache.a().a(netRequest, (YouPinHttpsAuthCache.CacheCallback) new YouPinHttpsAuthCache.CacheCallback() {
                public void a(String str) {
                    if (TextUtils.isEmpty(str)) {
                        conditionVariable.open();
                        return;
                    }
                    NetResult netResult = new NetResult();
                    netResult.b = true;
                    netResult.d = str;
                    if (HostSetting.g || HostSetting.i) {
                        LogUtils.b("MijiaShop", "onCache:" + str);
                    }
                    if (netCallback != null && !requestRecord.a()) {
                        netCallback.b(netResult);
                        requestRecord.a(str);
                    }
                    conditionVariable.open();
                }
            });
        } else {
            conditionVariable.open();
        }
        Call newCall = this.f.newCall(request);
        final boolean z3 = z;
        final NetCallback<NetResult, NetError> netCallback2 = netCallback;
        final NetRequest netRequest2 = netRequest;
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                conditionVariable.block(1000);
                if (z3) {
                    requestRecord.a(true);
                }
                if ((HostSetting.g || HostSetting.i) && iOException != null) {
                    LogUtils.e("MijiaShop", "onFailure:" + iOException.toString());
                }
                if (netCallback2 != null) {
                    netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), iOException == null ? "net request failure" : iOException.getMessage()));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                String str;
                conditionVariable.block(1000);
                if (z3) {
                    requestRecord.a(true);
                }
                if (!response.isSuccessful()) {
                    if (HostSetting.g || HostSetting.i) {
                        LogUtils.e("MijiaShop", "onResponse:" + response.toString());
                    }
                    if (response.code() == 401) {
                        if (YouPinHttpsAuthApi.this.h != null) {
                            LogUtils.b("MijiaShop", YouPinHttpsAuthApi.this.h.c);
                        }
                        try {
                            str = response.body().string();
                        } catch (Exception unused) {
                            str = "";
                        }
                        YouPinHttpsAuthApi.this.a(netRequest2.b(), str);
                    }
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(response.code(), response.message()));
                        return;
                    }
                    return;
                }
                try {
                    String string = response.body().string();
                    if ((HostSetting.g || HostSetting.i) && !TextUtils.isEmpty(string)) {
                        LogUtils.b("MijiaShop", "onResponse:" + string);
                    }
                    NetResult netResult = new NetResult();
                    netResult.b = false;
                    netResult.d = string;
                    if (netCallback2 != null) {
                        String b2 = requestRecord.b();
                        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(string) || !b2.equalsIgnoreCase(string)) {
                            if (!TextUtils.isEmpty(string)) {
                                YouPinHttpsAuthCache.a().a(netRequest2, string);
                            }
                            netResult.c = true;
                        } else {
                            netResult.c = false;
                        }
                        netCallback2.a(netResult);
                    }
                } catch (Exception e2) {
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), e2.getMessage()));
                    }
                }
            }
        });
        return new NetHandle(newCall);
    }

    class RequestRecord {

        /* renamed from: a  reason: collision with root package name */
        boolean f23334a = false;
        String b = "";

        RequestRecord() {
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean a() {
            return this.f23334a;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(boolean z) {
            this.f23334a = z;
        }

        /* access modifiers changed from: package-private */
        public synchronized String b() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(String str) {
            this.b = str;
        }
    }
}
