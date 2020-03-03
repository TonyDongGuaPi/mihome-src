package com.ximalaya.ting.android.opensdk.httputil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.drew.metadata.iptc.IptcDirectory;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.httputil.util.freeflow.FreeFlowServiceUtil;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class BaseCall {

    /* renamed from: a  reason: collision with root package name */
    public static int f1983a = 0;
    public static int b = 3000;
    public static int c = 603;
    public static String d = "x-a1-httpdns-switch";
    public static String e = "x-a1-xdcs-collector-switch";
    public static boolean f = false;
    public static boolean g = true;
    public static final String h = "网络请求失败";
    private static volatile BaseCall i;
    private static Context j;
    private static Class n;
    private OkHttpClient k = new OkHttpClient();
    private OkHttpClient l;
    private IIgnoreProxyUrl m;

    public interface IIgnoreProxyUrl {
        boolean a(URL url);
    }

    private BaseCall() {
    }

    public static synchronized BaseCall a() {
        BaseCall baseCall;
        synchronized (BaseCall.class) {
            if (i == null) {
                synchronized (BaseCall.class) {
                    if (i == null) {
                        i = new BaseCall();
                    }
                }
            }
            baseCall = i;
        }
        return baseCall;
    }

    public static synchronized void b() {
        synchronized (BaseCall.class) {
            if (i != null) {
                i = null;
            }
        }
    }

    public static void a(Context context) {
        j = context;
    }

    public synchronized void c() {
        OkHttpClient.Builder newBuilder = this.k.newBuilder();
        newBuilder.connectionPool(new ConnectionPool());
        this.k = newBuilder.build();
    }

    public synchronized void d() {
        this.k = new OkHttpClient();
    }

    public void a(Config config) {
        OkHttpClient.Builder newBuilder = this.k.newBuilder();
        a(j, config, newBuilder, false);
        this.k = newBuilder.build();
    }

    private void a(Context context, Config config, OkHttpClient.Builder builder, boolean z) {
        FreeFlowServiceUtil.a(context, config, builder, z);
        if (j != null) {
            builder.cache(new Cache(new File(j.getCacheDir(), "request_cache"), 52428800));
        }
    }

    public synchronized void a(Interceptor interceptor) {
        if (this.k != null) {
            OkHttpClient.Builder newBuilder = this.k.newBuilder();
            if (!newBuilder.interceptors().contains(interceptor)) {
                newBuilder.addInterceptor(interceptor);
            }
            this.k = newBuilder.build();
        }
    }

    public static Response a(OkHttpClient okHttpClient, Request request) throws Exception {
        return okHttpClient.newCall(request).execute();
    }

    @NonNull
    private OkHttpClient b(@NonNull Request request) {
        if (this.m == null || request.url() == null || !this.m.a(request.url().url())) {
            return request.isHttps() ? f() : this.k;
        }
        return f();
    }

    public Response a(Request request) throws Exception {
        if (this.k == null) {
            return null;
        }
        return b(request).newCall(request).execute();
    }

    public Response a(Request request, int i2) throws IOException {
        OkHttpClient b2 = b(request);
        if (i2 != f1983a) {
            OkHttpClient.Builder newBuilder = b2.newBuilder();
            long j2 = (long) i2;
            newBuilder.connectTimeout(j2, TimeUnit.MILLISECONDS);
            newBuilder.readTimeout(j2, TimeUnit.MILLISECONDS);
            newBuilder.writeTimeout(j2, TimeUnit.MILLISECONDS);
            b2 = newBuilder.build();
        }
        return b2.newCall(request).execute();
    }

    public void a(Request request, IHttpCallBack iHttpCallBack, int i2) {
        OkHttpClient b2 = b(request);
        if (i2 != f1983a) {
            OkHttpClient.Builder newBuilder = b2.newBuilder();
            long j2 = (long) i2;
            newBuilder.connectTimeout(j2, TimeUnit.MILLISECONDS);
            newBuilder.readTimeout(j2, TimeUnit.MILLISECONDS);
            newBuilder.writeTimeout(j2, TimeUnit.MILLISECONDS);
            b2 = newBuilder.build();
        }
        a(b2, request, iHttpCallBack);
    }

    public void a(OkHttpClient okHttpClient, Request request, final IHttpCallBack iHttpCallBack) {
        if (okHttpClient == null) {
            a(request, iHttpCallBack);
            return;
        }
        try {
            okHttpClient.newCall(request).enqueue(new Callback() {
                public void onResponse(Call call, Response response) throws IOException {
                    if (iHttpCallBack == null) {
                        response.body().close();
                        return;
                    }
                    if (response != null) {
                        String header = response.header(BaseCall.d);
                        String header2 = response.header(BaseCall.e);
                        Logger.c("SAVE_LIFE", header + "   " + header2);
                        if (!TextUtils.isEmpty(header)) {
                            if (header.equals("on")) {
                                BaseCall.f = true;
                            } else if (header.equals("off")) {
                                BaseCall.f = false;
                            }
                        }
                        if (!TextUtils.isEmpty(header2)) {
                            if (header2.equals("on")) {
                                BaseCall.g = true;
                            } else if (header2.equals("off")) {
                                BaseCall.g = false;
                            }
                        }
                    }
                    if (!BaseUtil.d()) {
                        iHttpCallBack.a(response);
                    } else if (response.code() >= 400) {
                        String c = new BaseResponse(response).c();
                        if (TextUtils.isEmpty(c) || !c.contains("ret")) {
                            IHttpCallBack iHttpCallBack = iHttpCallBack;
                            int code = response.code();
                            iHttpCallBack.a(code, "网络请求失败(" + response.code() + Operators.BRACKET_END_STR);
                        } else {
                            iHttpCallBack.a(response.code(), c);
                        }
                    } else {
                        iHttpCallBack.a(response);
                    }
                    response.body().close();
                }

                public void onFailure(Call call, IOException iOException) {
                    if (iHttpCallBack != null) {
                        String str = BaseCall.h;
                        if (ConstantsOpenSdk.b) {
                            str = iOException.getMessage();
                            if (TextUtils.isEmpty(str)) {
                                str = BaseCall.h;
                            }
                        }
                        iHttpCallBack.a(IptcDirectory.Z, str);
                    }
                }
            });
        } catch (Exception unused) {
            if (iHttpCallBack != null) {
                iHttpCallBack.a(IptcDirectory.Z, h);
            }
        }
    }

    public void a(Request request, final IHttpCallBack iHttpCallBack) {
        if (this.k != null) {
            try {
                b(request).newCall(request).enqueue(new Callback() {
                    public void onResponse(Call call, Response response) throws IOException {
                        if (iHttpCallBack == null) {
                            response.body().close();
                            return;
                        }
                        if (!BaseUtil.d()) {
                            iHttpCallBack.a(response);
                        } else if (response.code() >= 400) {
                            String c = new BaseResponse(response).c();
                            if (TextUtils.isEmpty(c) || !c.contains("ret")) {
                                IHttpCallBack iHttpCallBack = iHttpCallBack;
                                int code = response.code();
                                iHttpCallBack.a(code, "网络请求失败(" + response.code() + Operators.BRACKET_END_STR);
                            } else {
                                iHttpCallBack.a(response.code(), c);
                            }
                        } else {
                            iHttpCallBack.a(response);
                        }
                        response.body().close();
                    }

                    public void onFailure(Call call, IOException iOException) {
                        if (iHttpCallBack != null) {
                            String str = BaseCall.h;
                            if (ConstantsOpenSdk.b) {
                                str = iOException.getMessage();
                                if (TextUtils.isEmpty(str)) {
                                    str = BaseCall.h;
                                }
                            }
                            iHttpCallBack.a(IptcDirectory.Z, str);
                        }
                    }
                });
            } catch (Exception unused) {
                if (iHttpCallBack != null) {
                    iHttpCallBack.a(IptcDirectory.Z, h);
                }
            }
        }
    }

    private static String a(Response response, String str) {
        try {
            JSONObject jSONObject = new JSONObject(response.body().toString());
            if (jSONObject.has("msg")) {
                return jSONObject.getString("msg");
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    @Nullable
    public static Class e() {
        if (n != null) {
            return n;
        }
        try {
            Class<?> cls = Class.forName(ConstantsOpenSdk.K);
            n = cls;
            return cls;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0067 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            okhttp3.OkHttpClient r0 = r4.k     // Catch:{ all -> 0x0067 }
            if (r0 == 0) goto L_0x0065
            okhttp3.Dispatcher r0 = r0.dispatcher()     // Catch:{ all -> 0x0067 }
            if (r0 == 0) goto L_0x0065
            java.util.List r1 = r0.runningCalls()     // Catch:{ all -> 0x0067 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0067 }
        L_0x001b:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x003c
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0067 }
            okhttp3.Call r2 = (okhttp3.Call) r2     // Catch:{ all -> 0x0067 }
            okhttp3.Request r3 = r2.request()     // Catch:{ all -> 0x0067 }
            if (r3 == 0) goto L_0x001b
            java.lang.Object r3 = r3.tag()     // Catch:{ all -> 0x0067 }
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x0067 }
            if (r3 == 0) goto L_0x001b
            r2.cancel()     // Catch:{ all -> 0x0067 }
            monitor-exit(r4)
            return
        L_0x003c:
            java.util.List r0 = r0.queuedCalls()     // Catch:{ all -> 0x0067 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0067 }
        L_0x0044:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0067 }
            okhttp3.Call r1 = (okhttp3.Call) r1     // Catch:{ all -> 0x0067 }
            okhttp3.Request r2 = r1.request()     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x0044
            java.lang.Object r2 = r2.tag()     // Catch:{ all -> 0x0067 }
            boolean r2 = r5.equals(r2)     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x0044
            r1.cancel()     // Catch:{ all -> 0x0067 }
            monitor-exit(r4)
            return
        L_0x0065:
            monitor-exit(r4)
            return
        L_0x0067:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.httputil.BaseCall.a(java.lang.String):void");
    }

    public void a(IIgnoreProxyUrl iIgnoreProxyUrl) {
        this.m = iIgnoreProxyUrl;
    }

    public OkHttpClient f() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new OkHttpClient.Builder().connectionPool(new ConnectionPool(1, 1, TimeUnit.MINUTES)).build();
        return this.l;
    }

    public OkHttpClient a(@Nullable URL url) {
        if (url == null) {
            return this.k;
        }
        if (this.m == null || !this.m.a(url)) {
            return url.getPath().startsWith("https") ? f() : this.k;
        }
        return f();
    }
}
