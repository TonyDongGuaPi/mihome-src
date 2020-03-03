package com.xiaomi.youpin.youpin_network;

import android.os.ConditionVariable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.mi.global.shop.util.ConnectionHelper;
import com.xiaomi.youpin.common.util.FileUtils;
import com.xiaomi.youpin.cookie.YouPinCookieHandler;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.log.NetMonitor;
import com.xiaomi.youpin.network.bean.DownloadFileInfo;
import com.xiaomi.youpin.network.bean.NetCallback;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.network.bean.NetResult;
import com.xiaomi.youpin.network.bean.RequestRecord;
import com.xiaomi.youpin.network.bean.UploadFileInfo;
import com.xiaomi.youpin.network.util.KeyValuePairUtil;
import com.xiaomi.youpin.youpin_network.NetWorkDependency;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import com.xiaomi.youpin.youpin_network.retry.HttpsEntity;
import com.xiaomi.youpin.youpin_network.retry.RNHttpEntity;
import com.xiaomi.youpin.youpin_network.retry.YouPinAuthHttpsManager;
import com.xiaomi.youpin.youpin_network.util.YouPinApiParserUtil;
import com.xiaomi.youpin.youpin_network.util.YouPinHttpsAuthCache;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONException;
import org.json.JSONObject;

public class YouPinHttpsApi implements YouPinHttpsApiInterface {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23841a = "YouPinHttpsApi";
    private static final String b = "https://shopapi.io.mi.com";
    private static final String c = "https://st.shopapi.io.mi.com";
    /* access modifiers changed from: private */
    public static final Object d = new Object();
    private static volatile YouPinHttpsApi e;
    private static String f = "https://shopapi.io.mi.com";
    private static final JoinPoint.StaticPart l = null;
    private YouPinCookieHandler g = new YouPinCookieHandler();
    private OkHttpClient h;
    private NetworkConfig i = NetworkConfigManager.a().b();
    /* access modifiers changed from: private */
    public NetWorkDependency j;
    /* access modifiers changed from: private */
    public boolean k = false;

    private static void g() {
        Factory factory = new Factory("YouPinHttpsApi.java", YouPinHttpsApi.class);
        l = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 107);
    }

    static {
        g();
    }

    private YouPinHttpsApi() {
        if (this.i != null) {
            this.j = this.i.a();
        }
        OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(this.g)).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder removeHeader = chain.request().newBuilder().removeHeader("User-Agent");
                if (YouPinHttpsApi.this.j != null) {
                    removeHeader.addHeader("User-Agent", YouPinHttpsApi.this.j.c());
                }
                return chain.proceed(removeHeader.build());
            }
        });
        NetMonitor.addNetworkInterceptor(addNetworkInterceptor);
        HawkEyeAspect.a().a(Factory.a(l, (Object) this, (Object) addNetworkInterceptor));
        this.h = addNetworkInterceptor.build();
    }

    public static YouPinHttpsApiInterface a() {
        if (e == null) {
            synchronized (YouPinHttpsApi.class) {
                if (e == null) {
                    e = new YouPinHttpsApi();
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: private */
    public static boolean b(MediaType mediaType) {
        return mediaType != null && ("json".equalsIgnoreCase(mediaType.subtype()) || "text".equalsIgnoreCase(mediaType.type()));
    }

    public OkHttpClient b() {
        return this.h;
    }

    public YouPinCookieHandler c() {
        return this.g;
    }

    private String e() {
        String str = (this.j == null || !this.j.b()) ? "https://shopapi.io.mi.com" : c;
        f = str;
        return str;
    }

    private String a(NetRequest netRequest) {
        String b2 = netRequest.b();
        if (b2.startsWith("http:") || b2.startsWith("https:")) {
            return b2;
        }
        if (!netRequest.h()) {
            return e() + b2;
        } else if (netRequest.b().startsWith("/homepage")) {
            return e() + b2;
        } else {
            return e() + ConnectionHelper.I + b2;
        }
    }

    private Pair<RequestRecord, ConditionVariable> a(NetRequest netRequest, boolean z, NetCallback<NetResult, NetError> netCallback) {
        RequestRecord requestRecord = new RequestRecord();
        ConditionVariable conditionVariable = new ConditionVariable();
        if (z) {
            YouPinHttpsAuthCache.a().a(netRequest, (YouPinHttpsAuthCache.CacheCallback) new YouPinHttpsAuthCache.CacheCallback(conditionVariable, netCallback, requestRecord) {
                private final /* synthetic */ ConditionVariable f$0;
                private final /* synthetic */ NetCallback f$1;
                private final /* synthetic */ RequestRecord f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onResult(String str) {
                    YouPinHttpsApi.a(this.f$0, this.f$1, this.f$2, str);
                }
            });
        } else {
            conditionVariable.open();
        }
        return new Pair<>(requestRecord, conditionVariable);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(ConditionVariable conditionVariable, NetCallback netCallback, RequestRecord requestRecord, String str) {
        if (TextUtils.isEmpty(str)) {
            conditionVariable.open();
            return;
        }
        NetResult netResult = new NetResult();
        netResult.b = true;
        netResult.d = str;
        LogUtils.d(f23841a, "onCache:" + str);
        if (netCallback != null && !requestRecord.a()) {
            netCallback.a(netResult);
            requestRecord.a(str);
        }
        conditionVariable.open();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x010f, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0114, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0116, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0117, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014c, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0114 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:14:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011e A[SYNTHETIC, Splitter:B:42:0x011e] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0132 A[SYNTHETIC, Splitter:B:54:0x0132] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0147 A[SYNTHETIC, Splitter:B:62:0x0147] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.youpin.network.bean.NetResult a(@android.support.annotation.NonNull com.xiaomi.youpin.network.bean.NetRequest r8, @android.support.annotation.Nullable com.xiaomi.youpin.network.bean.UploadFileInfo r9) {
        /*
            r7 = this;
            java.lang.String r0 = "YouPinHttpsApi"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Request:"
            r1.append(r2)
            java.lang.String r2 = r8.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r0, (java.lang.String) r1)
            java.lang.String r0 = r7.a((com.xiaomi.youpin.network.bean.NetRequest) r8)
            int r1 = r8.a()
            r2 = 0
            switch(r1) {
                case 1: goto L_0x00a3;
                case 2: goto L_0x007d;
                case 3: goto L_0x0053;
                case 4: goto L_0x0029;
                default: goto L_0x0026;
            }
        L_0x0026:
            r9 = r2
            goto L_0x00c4
        L_0x0029:
            okhttp3.Request$Builder r9 = new okhttp3.Request$Builder
            r9.<init>()
            java.util.List r1 = r8.d()
            java.lang.String r0 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a((java.lang.String) r0, (java.util.List<com.xiaomi.youpin.network.bean.KeyValuePair>) r1)
            okhttp3.Request$Builder r9 = r9.url((java.lang.String) r0)
            java.util.List r0 = r8.c()
            okhttp3.Headers r0 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a(r0)
            okhttp3.Request$Builder r9 = r9.headers(r0)
            okhttp3.RequestBody r0 = r7.b((com.xiaomi.youpin.network.bean.NetRequest) r8)
            okhttp3.Request$Builder r9 = r9.delete(r0)
            okhttp3.Request r9 = r9.build()
            goto L_0x00c4
        L_0x0053:
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder
            r1.<init>()
            java.util.List r3 = r8.d()
            java.lang.String r0 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a((java.lang.String) r0, (java.util.List<com.xiaomi.youpin.network.bean.KeyValuePair>) r3)
            okhttp3.Request$Builder r0 = r1.url((java.lang.String) r0)
            java.util.List r1 = r8.c()
            okhttp3.Headers r1 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a(r1)
            okhttp3.Request$Builder r0 = r0.headers(r1)
            okhttp3.RequestBody r9 = r7.a((com.xiaomi.youpin.network.bean.NetRequest) r8, (com.xiaomi.youpin.network.bean.UploadFileInfo) r9, (com.xiaomi.youpin.network.bean.NetCallback<com.xiaomi.youpin.network.bean.NetResult, com.xiaomi.youpin.network.bean.NetError>) r2)
            okhttp3.Request$Builder r9 = r0.put(r9)
            okhttp3.Request r9 = r9.build()
            goto L_0x00c4
        L_0x007d:
            okhttp3.Request$Builder r9 = new okhttp3.Request$Builder
            r9.<init>()
            java.util.List r1 = r8.d()
            java.lang.String r0 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a((java.lang.String) r0, (java.util.List<com.xiaomi.youpin.network.bean.KeyValuePair>) r1)
            okhttp3.Request$Builder r9 = r9.url((java.lang.String) r0)
            java.util.List r0 = r8.c()
            okhttp3.Headers r0 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a(r0)
            okhttp3.Request$Builder r9 = r9.headers(r0)
            okhttp3.Request$Builder r9 = r9.get()
            okhttp3.Request r9 = r9.build()
            goto L_0x00c4
        L_0x00a3:
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder
            r1.<init>()
            okhttp3.Request$Builder r0 = r1.url((java.lang.String) r0)
            java.util.List r1 = r8.c()
            okhttp3.Headers r1 = com.xiaomi.youpin.network.util.KeyValuePairUtil.a(r1)
            okhttp3.Request$Builder r0 = r0.headers(r1)
            okhttp3.RequestBody r9 = r7.a((com.xiaomi.youpin.network.bean.NetRequest) r8, (com.xiaomi.youpin.network.bean.UploadFileInfo) r9, (com.xiaomi.youpin.network.bean.NetCallback<com.xiaomi.youpin.network.bean.NetResult, com.xiaomi.youpin.network.bean.NetError>) r2)
            okhttp3.Request$Builder r9 = r0.post(r9)
            okhttp3.Request r9 = r9.build()
        L_0x00c4:
            com.xiaomi.youpin.network.bean.NetResult r0 = new com.xiaomi.youpin.network.bean.NetResult
            r0.<init>()
            java.lang.String r1 = ""
            if (r9 != 0) goto L_0x00ce
            return r2
        L_0x00ce:
            r3 = 0
            okhttp3.OkHttpClient r4 = r7.h     // Catch:{ IOException -> 0x012b }
            okhttp3.Call r9 = r4.newCall(r9)     // Catch:{ IOException -> 0x012b }
            okhttp3.Response r9 = r9.execute()     // Catch:{ IOException -> 0x012b }
            if (r9 == 0) goto L_0x0119
            okhttp3.ResponseBody r2 = r9.body()     // Catch:{ IOException -> 0x0116, all -> 0x0114 }
            if (r2 != 0) goto L_0x00e2
            goto L_0x0119
        L_0x00e2:
            int r2 = r9.code()     // Catch:{ IOException -> 0x0116, all -> 0x0114 }
            okhttp3.ResponseBody r4 = r9.body()     // Catch:{ IOException -> 0x010f, all -> 0x0114 }
            if (r4 == 0) goto L_0x010d
            okhttp3.MediaType r5 = r4.contentType()     // Catch:{ IOException -> 0x010f, all -> 0x0114 }
            boolean r5 = b((okhttp3.MediaType) r5)     // Catch:{ IOException -> 0x010f, all -> 0x0114 }
            if (r5 == 0) goto L_0x010d
            java.lang.String r4 = r4.string()     // Catch:{ IOException -> 0x010f, all -> 0x0114 }
            r1 = 401(0x191, float:5.62E-43)
            if (r2 != r1) goto L_0x010b
            boolean r8 = r8.f()     // Catch:{ IOException -> 0x0108, all -> 0x0114 }
            if (r8 == 0) goto L_0x010b
            r7.a((java.lang.String) r4)     // Catch:{ IOException -> 0x0108, all -> 0x0114 }
            goto L_0x010b
        L_0x0108:
            r8 = move-exception
            r1 = r4
            goto L_0x0110
        L_0x010b:
            r8 = r4
            goto L_0x011c
        L_0x010d:
            r8 = r1
            goto L_0x011c
        L_0x010f:
            r8 = move-exception
        L_0x0110:
            r6 = r2
            r2 = r9
            r9 = r6
            goto L_0x012d
        L_0x0114:
            r8 = move-exception
            goto L_0x0145
        L_0x0116:
            r8 = move-exception
            r2 = r9
            goto L_0x012c
        L_0x0119:
            java.lang.String r8 = ""
            r2 = 0
        L_0x011c:
            if (r9 == 0) goto L_0x0126
            r9.close()     // Catch:{ Exception -> 0x0122 }
            goto L_0x0126
        L_0x0122:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0126:
            r9 = r2
            goto L_0x013b
        L_0x0128:
            r8 = move-exception
            r9 = r2
            goto L_0x0145
        L_0x012b:
            r8 = move-exception
        L_0x012c:
            r9 = 0
        L_0x012d:
            r8.printStackTrace()     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x013a
            r2.close()     // Catch:{ Exception -> 0x0136 }
            goto L_0x013a
        L_0x0136:
            r8 = move-exception
            r8.printStackTrace()
        L_0x013a:
            r8 = r1
        L_0x013b:
            r0.f23658a = r9
            r0.d = r8
            r0.b = r3
            r8 = 1
            r0.c = r8
            return r0
        L_0x0145:
            if (r9 == 0) goto L_0x014f
            r9.close()     // Catch:{ Exception -> 0x014b }
            goto L_0x014f
        L_0x014b:
            r9 = move-exception
            r9.printStackTrace()
        L_0x014f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_network.YouPinHttpsApi.a(com.xiaomi.youpin.network.bean.NetRequest, com.xiaomi.youpin.network.bean.UploadFileInfo):com.xiaomi.youpin.network.bean.NetResult");
    }

    private RequestBody a(NetRequest netRequest, UploadFileInfo uploadFileInfo, NetCallback<NetResult, NetError> netCallback) {
        switch (netRequest.e()) {
            case 1:
                return b(netRequest);
            case 2:
                return KeyValuePairUtil.a(uploadFileInfo, netRequest.d(), netCallback);
            default:
                return b(netRequest);
        }
    }

    private RequestBody b(NetRequest netRequest) {
        String g2 = netRequest.g();
        if (TextUtils.isEmpty(g2)) {
            return KeyValuePairUtil.c(netRequest.d());
        }
        return RequestBody.create(MediaType.parse("application/json"), g2);
    }

    private void a(HttpsEntity httpsEntity, NetCallback<NetResult, NetError> netCallback) {
        final NetRequest a2 = httpsEntity.a();
        final boolean b2 = httpsEntity.b();
        UploadFileInfo e2 = httpsEntity.e();
        LogUtils.d(f23841a, "Request:" + a2.toString());
        if (this.j == null || !this.j.d() || !a2.f() || !YouPinAuthHttpsManager.a().a(false, httpsEntity)) {
            String a3 = a(a2);
            Request request = null;
            switch (a2.a()) {
                case 1:
                    request = new Request.Builder().url(a3).headers(KeyValuePairUtil.a(a2.c())).post(a(a2, e2, netCallback)).build();
                    break;
                case 2:
                    request = new Request.Builder().url(KeyValuePairUtil.a(a3, a2.d())).headers(KeyValuePairUtil.a(a2.c())).get().build();
                    break;
                case 3:
                    request = new Request.Builder().url(a3).headers(KeyValuePairUtil.a(a2.c())).put(a(a2, e2, netCallback)).build();
                    break;
                case 4:
                    request = new Request.Builder().url(a3).headers(KeyValuePairUtil.a(a2.c())).delete(b(a2)).build();
                    break;
            }
            if (request != null) {
                Pair<RequestRecord, ConditionVariable> a4 = a(a2, b2, netCallback);
                final RequestRecord requestRecord = (RequestRecord) a4.first;
                Call newCall = this.h.newCall(request);
                final ConditionVariable conditionVariable = (ConditionVariable) a4.second;
                final NetCallback<NetResult, NetError> netCallback2 = netCallback;
                final HttpsEntity httpsEntity2 = httpsEntity;
                newCall.enqueue(new Callback() {
                    public void onFailure(Call call, IOException iOException) {
                        String str;
                        conditionVariable.block(1000);
                        if (b2) {
                            requestRecord.a(true);
                        }
                        if (netCallback2 != null) {
                            NetCallback netCallback = netCallback2;
                            if (iOException == null) {
                                str = "net request failure";
                            } else {
                                str = iOException.getMessage();
                            }
                            netCallback.a(new NetError(-1, str));
                        }
                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        String str = "";
                        if (response != null) {
                            ResponseBody body = response.body();
                            if (body == null) {
                                if (netCallback2 != null) {
                                    netCallback2.a(new NetError(-1, "response body null"));
                                }
                            } else if (!response.isSuccessful()) {
                                if (!YouPinHttpsApi.b(body.contentType())) {
                                    try {
                                        body.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    if (netCallback2 != null) {
                                        netCallback2.a(new NetError(response.code(), response.message()));
                                        return;
                                    }
                                    return;
                                }
                                try {
                                    String string = body.string();
                                    try {
                                        body.close();
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                    str = string;
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                    try {
                                        body.close();
                                    } catch (Exception e5) {
                                        e5.printStackTrace();
                                    }
                                } catch (Throwable th) {
                                    try {
                                        body.close();
                                    } catch (Exception e6) {
                                        e6.printStackTrace();
                                    }
                                    throw th;
                                }
                                LogUtils.d(YouPinHttpsApi.f23841a, "onResponse:" + str);
                                if ((response.code() != 401 || !a2.f() || !YouPinHttpsApi.this.a(str, httpsEntity2, (RNHttpEntity) null)) && netCallback2 != null) {
                                    netCallback2.a(new NetError(response.code(), response.message()));
                                }
                            } else if (a2.e() == 3) {
                                YouPinHttpsApi.this.a(httpsEntity2, (NetCallback<NetResult, NetError>) netCallback2, response);
                            } else {
                                try {
                                    String string2 = body.string();
                                    try {
                                        body.close();
                                    } catch (Exception e7) {
                                        e7.printStackTrace();
                                    }
                                    str = string2;
                                } catch (Exception e8) {
                                    e8.printStackTrace();
                                    try {
                                        body.close();
                                    } catch (Exception e9) {
                                        e9.printStackTrace();
                                    }
                                } catch (Throwable th2) {
                                    try {
                                        body.close();
                                    } catch (Exception e10) {
                                        e10.printStackTrace();
                                    }
                                    throw th2;
                                }
                                LogUtils.d(YouPinHttpsApi.f23841a, "onResponse:" + str);
                                NetResult netResult = new NetResult();
                                netResult.f23658a = response.code();
                                netResult.d = str;
                                netResult.b = false;
                                String b2 = requestRecord.b();
                                if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(str) || !b2.equalsIgnoreCase(str)) {
                                    if (!TextUtils.isEmpty(str)) {
                                        YouPinHttpsAuthCache.a().a(a2, str);
                                    }
                                    netResult.c = true;
                                } else {
                                    netResult.c = false;
                                }
                                netCallback2.b(netResult);
                            }
                        } else if (netCallback2 != null) {
                            netCallback2.a(new NetError(-1, "response null"));
                        }
                    }
                });
            } else if (netCallback != null) {
                netCallback.a(new NetError(-1, "request == null"));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d9 A[SYNTHETIC, Splitter:B:54:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e4 A[SYNTHETIC, Splitter:B:59:0x00e4] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ef A[SYNTHETIC, Splitter:B:64:0x00ef] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00fe A[SYNTHETIC, Splitter:B:71:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0109 A[SYNTHETIC, Splitter:B:76:0x0109] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0114 A[SYNTHETIC, Splitter:B:81:0x0114] */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.youpin.youpin_network.retry.HttpsEntity r18, com.xiaomi.youpin.network.bean.NetCallback<com.xiaomi.youpin.network.bean.NetResult, com.xiaomi.youpin.network.bean.NetError> r19, okhttp3.Response r20) {
        /*
            r17 = this;
            r7 = r19
            com.xiaomi.youpin.network.bean.DownloadFileInfo r0 = r18.f()
            r8 = -1
            if (r0 != 0) goto L_0x0016
            if (r7 == 0) goto L_0x0015
            com.xiaomi.youpin.network.bean.NetError r0 = new com.xiaomi.youpin.network.bean.NetError
            java.lang.String r1 = "downloadFileInfo is null"
            r0.<init>(r8, r1)
            r7.a(r0)
        L_0x0015:
            return
        L_0x0016:
            okhttp3.ResponseBody r9 = r20.body()
            java.lang.String r0 = r0.a()
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            java.lang.String r2 = r1.getParent()
            boolean r2 = com.xiaomi.youpin.common.util.FileUtils.e((java.lang.String) r2)
            if (r2 != 0) goto L_0x003a
            if (r7 == 0) goto L_0x0039
            com.xiaomi.youpin.network.bean.NetError r0 = new com.xiaomi.youpin.network.bean.NetError
            java.lang.String r1 = "downloadFileInfo is null"
            r0.<init>(r8, r1)
            r7.a(r0)
        L_0x0039:
            return
        L_0x003a:
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r10 = new byte[r2]
            r2 = 0
            if (r9 != 0) goto L_0x0061
            com.xiaomi.youpin.network.bean.NetError r0 = new com.xiaomi.youpin.network.bean.NetError     // Catch:{ Exception -> 0x005d, all -> 0x0057 }
            java.lang.String r1 = "responseBody is null"
            r0.<init>(r8, r1)     // Catch:{ Exception -> 0x005d, all -> 0x0057 }
            r7.a(r0)     // Catch:{ Exception -> 0x005d, all -> 0x0057 }
            if (r9 == 0) goto L_0x0056
            r9.close()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0056
        L_0x0051:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x0056:
            return
        L_0x0057:
            r0 = move-exception
            r1 = r0
            r11 = r2
            r14 = r11
            goto L_0x00fc
        L_0x005d:
            r0 = move-exception
            r14 = r2
            goto L_0x00c8
        L_0x0061:
            java.io.InputStream r11 = r9.byteStream()     // Catch:{ Exception -> 0x005d, all -> 0x0057 }
            long r12 = r9.contentLength()     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            java.io.FileOutputStream r14 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            r14.<init>(r1)     // Catch:{ Exception -> 0x00c5, all -> 0x00c1 }
            r1 = 0
            r2 = r1
        L_0x0071:
            int r1 = r11.read(r10)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            if (r1 == r8) goto L_0x0088
            r4 = 0
            r14.write(r10, r4, r1)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            long r4 = (long) r1     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            long r15 = r2 + r4
            r6 = 0
            r1 = r19
            r2 = r15
            r4 = r12
            r1.b(r2, r4, r6)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r2 = r15
            goto L_0x0071
        L_0x0088:
            r14.flush()     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r6 = 1
            r1 = r19
            r4 = r12
            r1.b(r2, r4, r6)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            com.xiaomi.youpin.network.bean.NetResult r1 = new com.xiaomi.youpin.network.bean.NetResult     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r1.<init>()     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            int r2 = r20.code()     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r1.f23658a = r2     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r1.d = r0     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            r7.b(r1)     // Catch:{ Exception -> 0x00bf, all -> 0x00bc }
            if (r11 == 0) goto L_0x00ad
            r11.close()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00ad
        L_0x00a8:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00ad:
            r14.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b6
        L_0x00b1:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00b6:
            if (r9 == 0) goto L_0x00f8
            r9.close()     // Catch:{ Exception -> 0x00f3 }
            goto L_0x00f8
        L_0x00bc:
            r0 = move-exception
            r1 = r0
            goto L_0x00fc
        L_0x00bf:
            r0 = move-exception
            goto L_0x00c7
        L_0x00c1:
            r0 = move-exception
            r1 = r0
            r14 = r2
            goto L_0x00fc
        L_0x00c5:
            r0 = move-exception
            r14 = r2
        L_0x00c7:
            r2 = r11
        L_0x00c8:
            r0.printStackTrace()     // Catch:{ all -> 0x00f9 }
            com.xiaomi.youpin.network.bean.NetError r1 = new com.xiaomi.youpin.network.bean.NetError     // Catch:{ all -> 0x00f9 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00f9 }
            r1.<init>(r8, r0)     // Catch:{ all -> 0x00f9 }
            r7.a(r1)     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x00e2
            r2.close()     // Catch:{ IOException -> 0x00dd }
            goto L_0x00e2
        L_0x00dd:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00e2:
            if (r14 == 0) goto L_0x00ed
            r14.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x00ed
        L_0x00e8:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00ed:
            if (r9 == 0) goto L_0x00f8
            r9.close()     // Catch:{ Exception -> 0x00f3 }
            goto L_0x00f8
        L_0x00f3:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x00f8:
            return
        L_0x00f9:
            r0 = move-exception
            r1 = r0
            r11 = r2
        L_0x00fc:
            if (r11 == 0) goto L_0x0107
            r11.close()     // Catch:{ IOException -> 0x0102 }
            goto L_0x0107
        L_0x0102:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0107:
            if (r14 == 0) goto L_0x0112
            r14.close()     // Catch:{ IOException -> 0x010d }
            goto L_0x0112
        L_0x010d:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0112:
            if (r9 == 0) goto L_0x011d
            r9.close()     // Catch:{ Exception -> 0x0118 }
            goto L_0x011d
        L_0x0118:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x011d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_network.YouPinHttpsApi.a(com.xiaomi.youpin.youpin_network.retry.HttpsEntity, com.xiaomi.youpin.network.bean.NetCallback, okhttp3.Response):void");
    }

    public <T> Pair<T, NetError> a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, @Nullable YouPinJsonParser<T> youPinJsonParser) {
        netRequest.b(1);
        NetResult a2 = a(netRequest, (UploadFileInfo) null);
        if (a2 == null) {
            return new Pair<>((Object) null, new NetError(-1, "wrong request method"));
        }
        return YouPinApiParserUtil.b(a2, new PipeRequest(requestParams, youPinJsonParser, (RequestAsyncCallback) null));
    }

    public <T> void a(final RequestParams requestParams, @NonNull NetRequest netRequest, boolean z, @Nullable final YouPinJsonParser<T> youPinJsonParser, @Nullable final RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        netRequest.b(1);
        a((HttpsEntity) HttpsEntity.a(requestParams, netRequest, z, youPinJsonParser, requestAsyncCallback), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            public void a(NetResult netResult) {
                YouPinApiParserUtil.a(netResult, new PipeRequest(requestParams, youPinJsonParser, requestAsyncCallback));
            }

            public void b(NetResult netResult) {
                YouPinApiParserUtil.a(netResult, new PipeRequest(requestParams, youPinJsonParser, requestAsyncCallback));
            }

            public void a(NetError netError) {
                if (requestAsyncCallback != null) {
                    requestAsyncCallback.b(new NetError(netError.a(), netError.b()));
                }
            }

            public void a(long j, long j2, boolean z) {
                if (requestAsyncCallback != null) {
                    requestAsyncCallback.a(j, j2, z);
                }
            }

            public void b(long j, long j2, boolean z) {
                if (requestAsyncCallback != null) {
                    requestAsyncCallback.a(j, j2);
                }
            }
        });
    }

    public void a(@NonNull NetRequest netRequest, boolean z, @NonNull final List<PipeRequest> list) {
        LogUtils.d(f23841a, "Request:" + netRequest.toString());
        if (netRequest.e() == 1) {
            a(HttpsEntity.a(netRequest, z, list), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                public void a(long j, long j2, boolean z) {
                }

                public void b(long j, long j2, boolean z) {
                }

                public void a(NetResult netResult) {
                    YouPinApiParserUtil.a(netResult, (List<PipeRequest>) list);
                }

                public void b(NetResult netResult) {
                    YouPinApiParserUtil.a(netResult, (List<PipeRequest>) list);
                }

                public void a(NetError netError) {
                    for (PipeRequest pipeRequest : list) {
                        RequestAsyncCallback<T, NetError> requestAsyncCallback = pipeRequest.callback;
                        if (requestAsyncCallback != null) {
                            requestAsyncCallback.b(netError);
                        }
                    }
                }
            });
        }
    }

    public <T> Pair<T, NetError> a(@Nullable RequestParams requestParams, @NonNull NetRequest netRequest, UploadFileInfo uploadFileInfo, YouPinJsonParser<T> youPinJsonParser) {
        LogUtils.d(f23841a, "Request:" + netRequest.toString());
        if (!FileUtils.b(uploadFileInfo.b())) {
            return new Pair<>((Object) null, new NetError(-1, "File exist"));
        }
        netRequest.a(1);
        netRequest.b(2);
        NetResult a2 = a(netRequest, uploadFileInfo);
        if (a2 == null) {
            return new Pair<>((Object) null, new NetError(-1, "wrong request method"));
        }
        return YouPinApiParserUtil.b(a2, new PipeRequest(requestParams, youPinJsonParser, (RequestAsyncCallback) null));
    }

    public <T> void a(@Nullable final RequestParams requestParams, @NonNull NetRequest netRequest, UploadFileInfo uploadFileInfo, final YouPinJsonParser<T> youPinJsonParser, @Nullable final RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        LogUtils.d(f23841a, "Request:" + netRequest.toString());
        if (FileUtils.b(uploadFileInfo.b())) {
            netRequest.a(1);
            netRequest.b(2);
            a((HttpsEntity) HttpsEntity.a(requestParams, netRequest, uploadFileInfo, youPinJsonParser, requestAsyncCallback), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                public void b(long j, long j2, boolean z) {
                }

                public void a(NetResult netResult) {
                    YouPinApiParserUtil.a(netResult, new PipeRequest(requestParams, youPinJsonParser, requestAsyncCallback));
                }

                public void b(NetResult netResult) {
                    YouPinApiParserUtil.a(netResult, new PipeRequest(requestParams, youPinJsonParser, requestAsyncCallback));
                }

                public void a(NetError netError) {
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.b(new NetError(netError.a(), netError.b()));
                    }
                }

                public void a(long j, long j2, boolean z) {
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.b(j, j2);
                    }
                }
            });
        } else if (requestAsyncCallback != null) {
            requestAsyncCallback.b(new NetError(-1, "File not exist"));
        }
    }

    public void a(@NonNull NetRequest netRequest, @NonNull DownloadFileInfo downloadFileInfo, @Nullable final RequestAsyncCallback<String, NetError> requestAsyncCallback) {
        LogUtils.d(f23841a, "Request:" + netRequest.toString());
        if (!FileUtils.b(downloadFileInfo.a())) {
            netRequest.a(2);
            netRequest.b(3);
            a((HttpsEntity) HttpsEntity.a(netRequest, downloadFileInfo, requestAsyncCallback), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                public void a(long j, long j2, boolean z) {
                }

                public void a(NetResult netResult) {
                }

                public void b(NetResult netResult) {
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.a(netResult.d, false);
                    }
                }

                public void a(NetError netError) {
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.a(new NetError(netError.a(), netError.b()));
                    }
                }

                public void b(long j, long j2, boolean z) {
                    if (requestAsyncCallback != null) {
                        requestAsyncCallback.a(j, j2);
                    }
                }
            });
        } else if (requestAsyncCallback != null) {
            requestAsyncCallback.b(new NetError(-1, "File exist"));
        }
    }

    public void a(@NonNull OkHttpClient okHttpClient, @NonNull Request request, @NonNull final Callback callback, ISendRnRequest iSendRnRequest) {
        LogUtils.d(f23841a, "sendRNRequest:" + request.toString());
        final RNHttpEntity rNHttpEntity = new RNHttpEntity(okHttpClient, request, callback, iSendRnRequest);
        if (!this.j.d()) {
            okHttpClient.newCall(request).enqueue(callback);
        } else if (!YouPinAuthHttpsManager.a().a(false, rNHttpEntity)) {
            okHttpClient.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    callback.onFailure(call, iOException);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (!YouPinHttpsApi.this.a(rNHttpEntity, response)) {
                        callback.onResponse(call, response);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean a(RNHttpEntity rNHttpEntity, Response response) {
        if (response != null && !response.isSuccessful() && response.code() == 401) {
            if (YouPinAuthHttpsManager.a().a(true, rNHttpEntity)) {
                return true;
            }
            f();
        }
        return false;
    }

    private void a(String str) {
        int i2;
        if (this.j.d()) {
            try {
                i2 = new JSONObject(str).optInt("code");
            } catch (JSONException e2) {
                e2.printStackTrace();
                i2 = -100;
            }
            if (i2 != -100) {
                if (i2 == 3 || i2 == 4) {
                    boolean z = false;
                    synchronized (d) {
                        if (this.k) {
                            z = true;
                        } else {
                            this.k = true;
                        }
                    }
                    if (!z) {
                        LogUtils.d(f23841a, "refreshServiceToken");
                        this.j.a(new NetWorkDependency.ServiceTokenCallback() {
                            public void a() {
                                synchronized (YouPinHttpsApi.d) {
                                    boolean unused = YouPinHttpsApi.this.k = false;
                                }
                            }

                            public void a(int i, String str, boolean z) {
                                synchronized (YouPinHttpsApi.d) {
                                    boolean unused = YouPinHttpsApi.this.k = false;
                                }
                                if (!z) {
                                    YouPinHttpsApi.this.f();
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                f();
            }
        }
    }

    /* access modifiers changed from: private */
    public <T> boolean a(String str, HttpsEntity<T> httpsEntity, RNHttpEntity rNHttpEntity) {
        int i2;
        boolean z;
        if (!this.j.d()) {
            return false;
        }
        try {
            i2 = new JSONObject(str).optInt("code");
        } catch (JSONException e2) {
            e2.printStackTrace();
            i2 = -100;
        }
        if (i2 == -100) {
            return false;
        }
        if (httpsEntity != null) {
            z = YouPinAuthHttpsManager.a().a(true, (HttpsEntity) httpsEntity);
        } else {
            z = YouPinAuthHttpsManager.a().a(true, rNHttpEntity);
        }
        if (z) {
            return true;
        }
        f();
        return false;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.j.d()) {
            this.j.a();
        }
    }
}
