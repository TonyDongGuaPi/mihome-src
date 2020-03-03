package com.xiaomi.smarthome.family.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.api.MiShopApi;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.smarthome.messagecenter.shopmessage.ShopTypeMsgManager;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class ShopApi {
    private static ShopApi c = null;
    private static BroadcastReceiver e;
    private static Object f = new Object();
    private static final JoinPoint.StaticPart j = null;

    /* renamed from: a  reason: collision with root package name */
    private OkHttpClient f15875a;
    private CookieManager b = new CookieManager();
    private MiServiceTokenInfo d;
    private boolean g = false;
    /* access modifiers changed from: private */
    public Object h = new Object();
    /* access modifiers changed from: private */
    public boolean i = false;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return ShopApi.a((ShopApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private String e() {
        return MiShopApi.b;
    }

    private static void g() {
        Factory factory = new Factory("ShopApi.java", ShopApi.class);
        j = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 72);
    }

    static {
        g();
    }

    public static ShopApi a() {
        if (c == null) {
            synchronized (ShopApi.class) {
                if (c == null) {
                    c = new ShopApi();
                }
            }
        }
        return c;
    }

    private ShopApi() {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(this.b));
        JoinPoint a2 = Factory.a(j, (Object) this, (Object) cookieJar);
        this.f15875a = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cookieJar, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(ShopApi shopApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public void a(Context context, long j2, AsyncCallback<Integer, Error> asyncCallback) {
        if (CoreApi.a().D()) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(0, "International server"));
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(0, "not support"));
        }
    }

    public void a(Context context, AsyncCallback<ShopTypeMsgManager.ShopMessageData, Error> asyncCallback) {
        if (CoreApi.a().D()) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(0, "International server"));
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(0, "not support"));
        }
    }

    private void c() {
        String str;
        try {
            str = URLEncoder.encode(SystemApi.a().m(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str = "";
        }
        CookieUtil.a(this.b, e(), "timezone", str, ".io.mi.com", "/");
    }

    private void d() {
        Locale g2 = GlobalDynamicSettingManager.a().g();
        if (g2 == null) {
            CookieUtil.a(this.b, e(), "locale", LocaleUtil.a(Locale.getDefault()), ".io.mi.com", "/");
            return;
        }
        CookieUtil.a(this.b, e(), "locale", LocaleUtil.a(g2), ".io.mi.com", "/");
    }

    public boolean b() {
        boolean z;
        synchronized (f) {
            z = this.g;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (f) {
            this.g = z;
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        AccountManager.a().i();
        a(false);
        for (IClientApi onUnAuthorized : CoreManager.a().d()) {
            try {
                onUnAuthorized.onUnAuthorized();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:26|27|69|(2:33|34)|35|57) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0069, code lost:
        monitor-enter(r8.h);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r8.i = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006d, code lost:
        if (r0 != false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        com.xiaomi.smarthome.core.server.internal.account.AccountManager.a().p();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0076, code lost:
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0067 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r9) {
        /*
            r8 = this;
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r3 = r0.m()
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r0 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            boolean r0 = r0.n()
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r1 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            java.lang.String r5 = r1.o()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0080 }
            r1.<init>(r9)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r9 = "code"
            int r9 = r1.optInt(r9)     // Catch:{ JSONException -> 0x0080 }
            r1 = 3
            if (r9 == r1) goto L_0x0037
            r1 = 4
            if (r9 != r1) goto L_0x002a
            goto L_0x0037
        L_0x002a:
            if (r0 == 0) goto L_0x0033
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r9 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ JSONException -> 0x0080 }
            r9.p()     // Catch:{ JSONException -> 0x0080 }
        L_0x0033:
            r8.f()     // Catch:{ JSONException -> 0x0080 }
            goto L_0x008c
        L_0x0037:
            java.lang.Object r9 = r8.h     // Catch:{ JSONException -> 0x0080 }
            monitor-enter(r9)     // Catch:{ JSONException -> 0x0080 }
            boolean r1 = r8.i     // Catch:{ all -> 0x007d }
            r2 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0041
            goto L_0x0044
        L_0x0041:
            r8.i = r2     // Catch:{ all -> 0x007d }
            r2 = 0
        L_0x0044:
            monitor-exit(r9)     // Catch:{ all -> 0x007d }
            if (r2 == 0) goto L_0x0048
            return
        L_0x0048:
            com.xiaomi.smarthome.core.server.CoreManager r9 = com.xiaomi.smarthome.core.server.CoreManager.a()     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r1 = "com.xiaomi.smarthome"
            com.xiaomi.smarthome.core.client.IClientApi r1 = r9.a((java.lang.String) r1)     // Catch:{ JSONException -> 0x0080 }
            if (r0 == 0) goto L_0x005b
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r9 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ Exception -> 0x0067 }
            r9.p()     // Catch:{ Exception -> 0x0067 }
        L_0x005b:
            java.lang.String r2 = "xiaomiio"
            com.xiaomi.smarthome.family.api.ShopApi$1 r6 = new com.xiaomi.smarthome.family.api.ShopApi$1     // Catch:{ Exception -> 0x0067 }
            r6.<init>()     // Catch:{ Exception -> 0x0067 }
            r4 = r0
            r1.refreshServiceToken(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0067 }
            goto L_0x008c
        L_0x0067:
            java.lang.Object r9 = r8.h     // Catch:{ JSONException -> 0x0080 }
            monitor-enter(r9)     // Catch:{ JSONException -> 0x0080 }
            r8.i = r7     // Catch:{ all -> 0x007a }
            monitor-exit(r9)     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0076
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r9 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()     // Catch:{ JSONException -> 0x0080 }
            r9.p()     // Catch:{ JSONException -> 0x0080 }
        L_0x0076:
            r8.f()     // Catch:{ JSONException -> 0x0080 }
            goto L_0x008c
        L_0x007a:
            r1 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x007a }
            throw r1     // Catch:{ JSONException -> 0x0080 }
        L_0x007d:
            r1 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x007d }
            throw r1     // Catch:{ JSONException -> 0x0080 }
        L_0x0080:
            if (r0 == 0) goto L_0x0089
            com.xiaomi.smarthome.core.server.internal.account.AccountManager r9 = com.xiaomi.smarthome.core.server.internal.account.AccountManager.a()
            r9.p()
        L_0x0089:
            r8.f()
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.family.api.ShopApi.a(java.lang.String):void");
    }
}
