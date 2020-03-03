package com.xiaomi.smarthome.core.server.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.account.RefreshServiceTokenResult;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.IServerCallback;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;
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

@Deprecated
public class MiShopApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14071a = "MiShopApi";
    public static final String b = "https://shopapi.io.mi.com";
    private static MiShopApi c;
    private static Object d = new Object();
    private static final JoinPoint.StaticPart k = null;
    private OkHttpClient e;
    private CookieManager f;
    private MiServiceTokenInfo g;
    private boolean h = false;
    /* access modifiers changed from: private */
    public Object i = new Object();
    /* access modifiers changed from: private */
    public boolean j = false;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return MiShopApi.a((MiShopApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private static void e() {
        Factory factory = new Factory("MiShopApi.java", MiShopApi.class);
        k = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 66);
    }

    static {
        e();
    }

    private MiShopApi() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        CookieManager cookieManager = new CookieManager();
        this.f = cookieManager;
        OkHttpClient.Builder cookieJar = writeTimeout.cookieJar(new JavaNetCookieJar(cookieManager));
        JoinPoint a2 = Factory.a(k, (Object) this, (Object) cookieJar);
        this.e = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cookieJar, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(MiShopApi miShopApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static MiShopApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new MiShopApi();
                }
            }
        }
        return c;
    }

    public boolean b() {
        boolean z;
        synchronized (d) {
            z = this.h;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (d) {
            this.h = z;
        }
    }

    private String a(NetRequest netRequest) {
        return b + netRequest.b();
    }

    /* access modifiers changed from: private */
    public void c() {
        boolean z;
        String m = AccountManager.a().m();
        boolean n = AccountManager.a().n();
        String o = AccountManager.a().o();
        synchronized (this.i) {
            z = true;
            if (!this.j) {
                this.j = true;
                z = false;
            }
        }
        if (!z) {
            IClientApi a2 = CoreManager.a().a("com.xiaomi.smarthome");
            if (n) {
                try {
                    AccountManager.a().b("xiaomiio");
                } catch (Exception unused) {
                    synchronized (this.i) {
                        this.j = false;
                        if (n) {
                            AccountManager.a().b("xiaomiio");
                        }
                        d();
                        return;
                    }
                }
            }
            a2.refreshServiceToken("xiaomiio", m, n, o, new IServerCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    synchronized (MiShopApi.this.i) {
                        boolean unused = MiShopApi.this.j = false;
                    }
                    bundle.setClassLoader(RefreshServiceTokenResult.class.getClassLoader());
                    RefreshServiceTokenResult refreshServiceTokenResult = (RefreshServiceTokenResult) bundle.getParcelable("result");
                    AccountManager.a().a("xiaomiio", refreshServiceTokenResult.c, refreshServiceTokenResult.d, ".io.mi.com", refreshServiceTokenResult.e);
                    AccountManager.a().j();
                    MiShopApi.this.a(false);
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    synchronized (MiShopApi.this.i) {
                        boolean unused = MiShopApi.this.j = false;
                    }
                    MiShopApi.this.d();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        a(false);
    }

    public NetHandle a(NetRequest netRequest, NetCallback<NetResult, NetError> netCallback) {
        Request request;
        final NetCallback<NetResult, NetError> netCallback2 = netCallback;
        if (netRequest == null) {
            if (netCallback2 != null) {
                netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        } else if (GlobalDynamicSettingManager.a().e()) {
            if (netCallback2 != null) {
                netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), "international not support mirecharge-api"));
            }
            return new NetHandle((Call) null);
        } else {
            String a2 = a(netRequest);
            if (!b()) {
                if (!AccountManager.a().l()) {
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), "not loggedin"));
                    }
                    return new NetHandle((Call) null);
                }
                String m = AccountManager.a().m();
                this.g = AccountManager.a().a("xiaomiio");
                if (TextUtils.isEmpty(m) || this.g == null) {
                    c();
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
                    }
                    return new NetHandle((Call) null);
                }
                CookieUtil.a(this.f);
                CookieUtil.a(this.f, b, "userId", m, ".io.mi.com", "/");
                CookieUtil.a(this.f, b, "serviceToken", this.g.c, this.g.f, "/");
                a(true);
            }
            if (netRequest.a().equals("POST")) {
                Request.Builder url = new Request.Builder().url(a2);
                if (netRequest.d() != null) {
                    url.headers(KeyValuePairUtil.a(netRequest.d()));
                }
                if (netRequest.e() != null) {
                    url.post(KeyValuePairUtil.b(netRequest.e()));
                }
                request = url.build();
            } else if (netRequest.a().equals("GET")) {
                Request.Builder builder = new Request.Builder();
                if (netRequest.d() != null) {
                    builder.headers(KeyValuePairUtil.a(netRequest.d()));
                }
                if (netRequest.e() != null) {
                    builder.url(KeyValuePairUtil.a(a2, netRequest.e()));
                }
                request = builder.build();
            } else {
                request = null;
            }
            if (request == null) {
                if (netCallback2 != null) {
                    netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
                }
                return new NetHandle((Call) null);
            }
            Call newCall = this.e.newCall(request);
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), iOException == null ? "net request failure" : iOException.getMessage()));
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        if (response.code() == 401) {
                            MiShopApi.this.c();
                        }
                        if (netCallback2 != null) {
                            netCallback2.a(new NetError(response.code(), ""));
                            return;
                        }
                        return;
                    }
                    try {
                        String string = response.body().string();
                        NetResult netResult = new NetResult();
                        netResult.c = string;
                        if (netCallback2 != null) {
                            netCallback2.a(netResult);
                        }
                    } catch (Exception e) {
                        if (netCallback2 != null) {
                            netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), e.getMessage()));
                        }
                    }
                }
            });
            return new NetHandle(newCall);
        }
    }
}
