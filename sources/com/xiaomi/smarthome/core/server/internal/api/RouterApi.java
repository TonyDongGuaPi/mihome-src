package com.xiaomi.smarthome.core.server.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.account.RefreshServiceTokenResult;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
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
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder;
import com.xiaomi.smarthome.library.http.util.CookieUtil;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.io.IOException;
import java.net.CookieManager;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class RouterApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14073a = "RouterApi";
    private static final String b = "https://api.miwifi.com";
    private static RouterApi c;
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
            return RouterApi.a((RouterApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private static void e() {
        Factory factory = new Factory("RouterApi.java", RouterApi.class);
        k = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 84);
    }

    static {
        e();
    }

    private RouterApi() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        CookieManager cookieManager = new CookieManager();
        this.f = cookieManager;
        OkHttpClient.Builder cookieJar = writeTimeout.cookieJar(new JavaNetCookieJar(cookieManager));
        JoinPoint a2 = Factory.a(k, (Object) this, (Object) cookieJar);
        this.e = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cookieJar, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(RouterApi routerApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static RouterApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new RouterApi();
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

    private String a(NetRequest netRequest, boolean z) {
        if (z) {
            return b + netRequest.b();
        }
        return "https://api.miwifi.com/r" + netRequest.b();
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>, java.lang.String> a(com.xiaomi.smarthome.core.entity.net.NetRequest r12, java.lang.String r13, boolean r14) {
        /*
            r11 = this;
            if (r14 == 0) goto L_0x0005
            java.lang.String r14 = ""
            goto L_0x0007
        L_0x0005:
            java.lang.String r14 = "/r"
        L_0x0007:
            java.lang.String r0 = r12.b()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r14)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r1 = r11.g
            long r1 = r1.e
            java.lang.String r1 = com.xiaomi.smarthome.library.crypto.CloudCoder.a((long) r1)
            r2 = 0
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r3 = r11.g     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            java.lang.String r3 = r3.d     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r3)     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            byte[] r4 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r1)     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            byte[] r3 = r11.a((byte[]) r3, (byte[]) r4)     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r3)     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            java.lang.String r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((byte[]) r3)     // Catch:{ NoSuchAlgorithmException -> 0x004c, InvalidKeyException -> 0x0044, Exception -> 0x003c }
            goto L_0x0054
        L_0x003c:
            java.lang.String r3 = "RouterApi"
            java.lang.String r4 = "generate sessionSecurity fail"
            android.util.Log.d(r3, r4)
            goto L_0x0053
        L_0x0044:
            java.lang.String r3 = "RouterApi"
            java.lang.String r4 = "generate sessionSecurity fail:InvalidKeyException"
            android.util.Log.d(r3, r4)
            goto L_0x0053
        L_0x004c:
            java.lang.String r3 = "RouterApi"
            java.lang.String r4 = "generate sessionSecurity fail:NoSuchAlgorithmException"
            android.util.Log.d(r3, r4)
        L_0x0053:
            r3 = r2
        L_0x0054:
            if (r3 != 0) goto L_0x0057
            return r2
        L_0x0057:
            java.util.TreeMap r2 = new java.util.TreeMap
            r2.<init>()
            java.util.TreeMap r4 = new java.util.TreeMap
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder r6 = new com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder
            r6.<init>((java.lang.String) r3)
            java.util.List r7 = r12.e()
            if (r7 == 0) goto L_0x00a1
            java.util.Iterator r8 = r7.iterator()
        L_0x0075:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00a1
            java.lang.Object r9 = r8.next()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r9 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r9
            java.lang.String r10 = r9.a()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x0075
            java.lang.String r10 = r9.b()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x0075
            java.lang.String r10 = r9.a()
            java.lang.String r9 = r9.b()
            r4.put(r10, r9)
            goto L_0x0075
        L_0x00a1:
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x00e7
            boolean r14 = android.text.TextUtils.isEmpty(r13)
            if (r14 != 0) goto L_0x00e7
            java.lang.String r14 = "miwifi."
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x00ba
            r14 = 7
            java.lang.String r13 = r13.substring(r14)
        L_0x00ba:
            if (r7 == 0) goto L_0x00dc
            java.util.Iterator r14 = r7.iterator()
        L_0x00c0:
            boolean r7 = r14.hasNext()
            if (r7 == 0) goto L_0x00dc
            java.lang.Object r7 = r14.next()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r7 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r7
            java.lang.String r8 = r7.a()
            java.lang.String r9 = "deviceId"
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x00c0
            java.lang.String r13 = r7.b()
        L_0x00dc:
            boolean r14 = android.text.TextUtils.isEmpty(r13)
            if (r14 != 0) goto L_0x00e7
            java.lang.String r14 = "deviceId"
            r4.put(r14, r13)
        L_0x00e7:
            java.lang.String r13 = r12.a()
            java.lang.String r13 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r13, r0, r4, r3)
            java.lang.String r14 = "rc4_hash__"
            r4.put(r14, r13)
            java.util.Set r13 = r4.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x00fc:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x0128
            java.lang.Object r14 = r13.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r4 = r14.getValue()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = r6.b((java.lang.String) r4)
            java.lang.Object r7 = r14.getKey()
            r2.put(r7, r4)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r7 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.Object r14 = r14.getKey()
            java.lang.String r14 = (java.lang.String) r14
            r7.<init>(r14, r4)
            r5.add(r7)
            goto L_0x00fc
        L_0x0128:
            java.lang.String r12 = r12.a()
            java.lang.String r12 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r12, r0, r2, r3)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r13 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r14 = "signature"
            r13.<init>(r14, r12)
            r5.add(r13)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r12 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r13 = "_nonce"
            r12.<init>(r13, r1)
            r5.add(r12)
            android.util.Pair r12 = android.util.Pair.create(r5, r1)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.api.RouterApi.a(com.xiaomi.smarthome.core.entity.net.NetRequest, java.lang.String, boolean):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public String a(String str, String str2) throws SecurityException {
        try {
            String a2 = Coder.a(Coder.d(a(Coder.a(this.g.d), Coder.a(str2))));
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
                    AccountManager.a().b("xiaoqiang");
                } catch (Exception unused) {
                    synchronized (this.i) {
                        this.j = false;
                        if (n) {
                            AccountManager.a().b("xiaoqiang");
                        }
                        d();
                        return;
                    }
                }
            }
            a2.refreshServiceToken("xiaoqiang", m, n, o, new IServerCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    synchronized (RouterApi.this.i) {
                        boolean unused = RouterApi.this.j = false;
                    }
                    bundle.setClassLoader(RefreshServiceTokenResult.class.getClassLoader());
                    RefreshServiceTokenResult refreshServiceTokenResult = (RefreshServiceTokenResult) bundle.getParcelable("result");
                    AccountManager.a().a("xiaoqiang", refreshServiceTokenResult.c, refreshServiceTokenResult.d, "api.gorouter.info", refreshServiceTokenResult.e);
                    AccountManager.a().j();
                    RouterApi.this.a(false);
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    synchronized (RouterApi.this.i) {
                        boolean unused = RouterApi.this.j = false;
                    }
                    RouterApi.this.d();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        a(false);
    }

    public NetHandle a(NetRequest netRequest, String str, boolean z, NetCallback<NetResult, NetError> netCallback) {
        Request request;
        NetRequest netRequest2 = netRequest;
        final NetCallback<NetResult, NetError> netCallback2 = netCallback;
        if (netRequest2 == null) {
            if (netCallback2 != null) {
                netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
            }
            return new NetHandle((Call) null);
        } else if (GlobalDynamicSettingManager.a().e()) {
            if (netCallback2 != null) {
                netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), "international not support router-api"));
            }
            return new NetHandle((Call) null);
        } else {
            String a2 = a(netRequest2, z);
            if (!b()) {
                if (!AccountManager.a().l()) {
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), "not loggedin"));
                    }
                    return new NetHandle((Call) null);
                }
                String m = AccountManager.a().m();
                this.g = AccountManager.a().a("xiaoqiang");
                if (TextUtils.isEmpty(m) || this.g == null) {
                    c();
                    if (netCallback2 != null) {
                        netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
                    }
                    return new NetHandle((Call) null);
                }
                CookieUtil.a(this.f);
                CookieUtil.a(this.f, b, "userId", m, "api.gorouter.info", "/");
                CookieUtil.a(this.f, b, "serviceToken", this.g.c, this.g.f, "/");
                a(true);
            }
            final Pair<List<KeyValuePair>, String> a3 = a(netRequest, str, z);
            if (a3 == null) {
                if (netCallback2 != null) {
                    netCallback2.a(new NetError(ErrorCode.INVALID.getCode(), ""));
                }
                return new NetHandle((Call) null);
            }
            if (netRequest.a().equals("POST")) {
                request = new Request.Builder().url(a2).headers(KeyValuePairUtil.a(netRequest.d())).post(KeyValuePairUtil.b((List) a3.first)).build();
            } else {
                request = netRequest.a().equals("GET") ? new Request.Builder().url(KeyValuePairUtil.a(a2, (List<KeyValuePair>) (List) a3.first)).headers(KeyValuePairUtil.a(netRequest.d())).build() : null;
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
                            RouterApi.this.c();
                        }
                        if (netCallback2 != null) {
                            netCallback2.a(new NetError(response.code(), ""));
                            return;
                        }
                        return;
                    }
                    try {
                        String a2 = RouterApi.this.a(response.body().string(), (String) a3.second);
                        NetResult netResult = new NetResult();
                        netResult.c = a2;
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
