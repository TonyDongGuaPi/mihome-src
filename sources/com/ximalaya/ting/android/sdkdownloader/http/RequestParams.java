package com.ximalaya.ting.android.sdkdownloader.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.model.track.TrackBaseInfo;
import com.ximalaya.ting.android.opensdk.util.PayUtil;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParamsHelper;
import com.ximalaya.ting.android.sdkdownloader.http.app.HttpRetryHandler;
import com.ximalaya.ting.android.sdkdownloader.http.app.RedirectHandler;
import com.ximalaya.ting.android.sdkdownloader.http.app.RequestTracker;
import com.ximalaya.ting.android.sdkdownloader.http.loader.FileLoader;
import com.ximalaya.ting.android.sdkdownloader.task.Priority;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class RequestParams extends BaseParams {

    /* renamed from: a  reason: collision with root package name */
    private String f2358a;
    private long b;
    private Proxy c;
    private Executor d;
    private Priority e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private String j;
    private boolean k;
    private int l;
    private HttpRetryHandler m;
    private RedirectHandler n;
    private RequestTracker o;
    private int p;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ void a(String str) {
        super.a(str);
    }

    public /* bridge */ /* synthetic */ void a(String str, Object obj) {
        super.a(str, obj);
    }

    public /* bridge */ /* synthetic */ void a(String str, String str2) {
        super.a(str, str2);
    }

    public /* bridge */ /* synthetic */ List b() {
        return super.b();
    }

    public /* bridge */ /* synthetic */ void b(String str, String str2) {
        super.b(str, str2);
    }

    public /* bridge */ /* synthetic */ List c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ void c(String str, String str2) {
        super.c(str, str2);
    }

    public RequestParams() {
        this((String) null, FileLoader.c);
    }

    public RequestParams(String str, int i2) {
        this.e = Priority.DEFAULT;
        this.f = 30000;
        this.g = 30000;
        this.h = true;
        this.i = 2;
        this.k = false;
        this.l = 800;
        this.p = FileLoader.c;
        this.f2358a = str;
        this.p = i2;
    }

    /* access modifiers changed from: package-private */
    public void d() throws Throwable {
        if (TextUtils.isEmpty(this.f2358a)) {
            if (this.p == FileLoader.d) {
                this.f2358a = PayUtil.a(this.b);
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("track_id", this.b + "");
                TrackBaseInfo c2 = CommonRequest.c((Map<String, String>) hashMap);
                if (c2 != null && !TextUtils.isEmpty(c2.n())) {
                    this.f2358a = c2.n();
                }
                throw new IllegalStateException("uri is empty && @HttpRequest == null");
            }
        }
        u();
    }

    public String e() {
        return this.f2358a;
    }

    public Proxy f() {
        return this.c;
    }

    public void a(Proxy proxy) {
        this.c = proxy;
    }

    public Priority g() {
        return this.e;
    }

    public void a(Priority priority) {
        this.e = priority;
    }

    public int h() {
        return this.f;
    }

    public void a(int i2) {
        if (i2 > 0) {
            this.f = i2;
        }
    }

    public Executor i() {
        return this.d;
    }

    public void a(Executor executor) {
        this.d = executor;
    }

    public boolean j() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public String k() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public int l() {
        return this.i;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public boolean m() {
        return this.k;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public int n() {
        return this.l;
    }

    public void c(int i2) {
        this.l = i2;
    }

    public HttpRetryHandler o() {
        return this.m;
    }

    public void a(HttpRetryHandler httpRetryHandler) {
        this.m = httpRetryHandler;
    }

    public RedirectHandler p() {
        return this.n;
    }

    public void a(RedirectHandler redirectHandler) {
        this.n = redirectHandler;
    }

    public RequestTracker q() {
        return this.o;
    }

    public void a(RequestTracker requestTracker) {
        this.o = requestTracker;
    }

    private void u() {
        RequestParamsHelper.a(this, getClass(), new RequestParamsHelper.ParseKVListener() {
            public void a(String str, Object obj) {
                RequestParams.this.a(str, obj);
            }
        });
    }

    public String toString() {
        try {
            d();
        } catch (Throwable unused) {
        }
        String e2 = e();
        if (TextUtils.isEmpty(e2)) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(e2);
        sb.append(e2.contains("?") ? a.b : "?");
        sb.append(super.toString());
        return sb.toString();
    }

    public int r() {
        return this.g;
    }

    public void d(int i2) {
        this.g = i2;
    }

    public int s() {
        return this.p;
    }

    public void e(int i2) {
        this.p = i2;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public void t() {
        this.f2358a = null;
    }
}
