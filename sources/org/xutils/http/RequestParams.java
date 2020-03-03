package org.xutils.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.common.task.Priority;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParamsHelper;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;
import org.xutils.http.app.HttpRetryHandler;
import org.xutils.http.app.ParamsBuilder;
import org.xutils.http.app.RedirectHandler;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.body.RequestBody;

public class RequestParams extends BaseParams {

    /* renamed from: a  reason: collision with root package name */
    private HttpRequest f10769a;
    private final String b;
    private final String[] c;
    private final String[] d;
    private ParamsBuilder e;
    private String f;
    private String g;
    private SSLSocketFactory h;
    private Proxy i;
    private boolean j;
    private String k;
    private long l;
    private long m;
    private Executor n;
    private Priority o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private String t;
    private boolean u;
    private int v;
    private HttpRetryHandler w;
    private RedirectHandler x;
    private RequestTracker y;
    private boolean z;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ void a(String str) {
        super.a(str);
    }

    public /* bridge */ /* synthetic */ void a(String str, File file) {
        super.a(str, file);
    }

    public /* bridge */ /* synthetic */ void a(String str, Object obj) {
        super.a(str, obj);
    }

    public /* bridge */ /* synthetic */ void a(String str, Object obj, String str2) {
        super.a(str, obj, str2);
    }

    public /* bridge */ /* synthetic */ void a(String str, Object obj, String str2, String str3) {
        super.a(str, obj, str2, str3);
    }

    public /* bridge */ /* synthetic */ void a(String str, String str2) {
        super.a(str, str2);
    }

    public /* bridge */ /* synthetic */ void a(HttpMethod httpMethod) {
        super.a(httpMethod);
    }

    public /* bridge */ /* synthetic */ void a(RequestBody requestBody) {
        super.a(requestBody);
    }

    public /* bridge */ /* synthetic */ void a(boolean z2) {
        super.a(z2);
    }

    public /* bridge */ /* synthetic */ HttpMethod b() {
        return super.b();
    }

    public /* bridge */ /* synthetic */ void b(String str) {
        super.b(str);
    }

    public /* bridge */ /* synthetic */ void b(String str, String str2) {
        super.b(str, str2);
    }

    public /* bridge */ /* synthetic */ void b(boolean z2) {
        super.b(z2);
    }

    public /* bridge */ /* synthetic */ String c(String str) {
        return super.c(str);
    }

    public /* bridge */ /* synthetic */ void c(String str, String str2) {
        super.c(str, str2);
    }

    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ List d(String str) {
        return super.d(str);
    }

    public /* bridge */ /* synthetic */ void d(String str, String str2) {
        super.d(str, str2);
    }

    public /* bridge */ /* synthetic */ boolean d() {
        return super.d();
    }

    public /* bridge */ /* synthetic */ String e() {
        return super.e();
    }

    public /* bridge */ /* synthetic */ void e(String str) {
        super.e(str);
    }

    public /* bridge */ /* synthetic */ List f() {
        return super.f();
    }

    public /* bridge */ /* synthetic */ List g() {
        return super.g();
    }

    public /* bridge */ /* synthetic */ List h() {
        return super.h();
    }

    public /* bridge */ /* synthetic */ List i() {
        return super.i();
    }

    public /* bridge */ /* synthetic */ List j() {
        return super.j();
    }

    public /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    public /* bridge */ /* synthetic */ RequestBody l() throws IOException {
        return super.l();
    }

    public /* bridge */ /* synthetic */ String m() {
        return super.m();
    }

    public RequestParams() {
        this((String) null, (ParamsBuilder) null, (String[]) null, (String[]) null);
    }

    public RequestParams(String str) {
        this(str, (ParamsBuilder) null, (String[]) null, (String[]) null);
    }

    public RequestParams(String str, ParamsBuilder paramsBuilder, String[] strArr, String[] strArr2) {
        this.j = true;
        this.o = Priority.DEFAULT;
        this.p = 15000;
        this.q = true;
        this.r = false;
        this.s = 2;
        this.u = false;
        this.v = 300;
        this.z = false;
        if (str != null && paramsBuilder == null) {
            paramsBuilder = new DefaultParamsBuilder();
        }
        this.b = str;
        this.c = strArr;
        this.d = strArr2;
        this.e = paramsBuilder;
    }

    /* access modifiers changed from: package-private */
    public void n() throws Throwable {
        if (TextUtils.isEmpty(this.f)) {
            if (!TextUtils.isEmpty(this.b) || J() != null) {
                I();
                this.f = this.b;
                HttpRequest J = J();
                if (J != null) {
                    this.e = (ParamsBuilder) J.builder().newInstance();
                    this.f = this.e.a(this, J);
                    this.e.a(this);
                    this.e.b(this, J.signs());
                    if (this.h == null) {
                        this.h = this.e.a();
                    }
                } else if (this.e != null) {
                    this.e.a(this);
                    this.e.b(this, this.c);
                    if (this.h == null) {
                        this.h = this.e.a();
                    }
                }
            } else {
                throw new IllegalStateException("uri is empty && @HttpRequest == null");
            }
        }
    }

    public String o() {
        return TextUtils.isEmpty(this.f) ? this.b : this.f;
    }

    public String p() {
        if (TextUtils.isEmpty(this.g) && this.e != null) {
            HttpRequest J = J();
            if (J != null) {
                this.g = this.e.a(this, J.cacheKeys());
            } else {
                this.g = this.e.a(this, this.d);
            }
        }
        return this.g;
    }

    public void a(SSLSocketFactory sSLSocketFactory) {
        this.h = sSLSocketFactory;
    }

    public SSLSocketFactory q() {
        return this.h;
    }

    public boolean r() {
        return this.j;
    }

    public void c(boolean z2) {
        this.j = z2;
    }

    public Proxy s() {
        return this.i;
    }

    public void a(Proxy proxy) {
        this.i = proxy;
    }

    public Priority t() {
        return this.o;
    }

    public void a(Priority priority) {
        this.o = priority;
    }

    public int u() {
        return this.p;
    }

    public void a(int i2) {
        if (i2 > 0) {
            this.p = i2;
        }
    }

    public String v() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public long w() {
        return this.l;
    }

    public void a(long j2) {
        this.l = j2;
    }

    public long x() {
        return this.m;
    }

    public void b(long j2) {
        this.m = j2;
    }

    public Executor y() {
        return this.n;
    }

    public void a(Executor executor) {
        this.n = executor;
    }

    public boolean z() {
        return this.q;
    }

    public void d(boolean z2) {
        this.q = z2;
    }

    public boolean A() {
        return this.r;
    }

    public void e(boolean z2) {
        this.r = z2;
    }

    public String B() {
        return this.t;
    }

    public void g(String str) {
        this.t = str;
    }

    public int C() {
        return this.s;
    }

    public void b(int i2) {
        this.s = i2;
    }

    public boolean D() {
        return this.u;
    }

    public void f(boolean z2) {
        this.u = z2;
    }

    public int E() {
        return this.v;
    }

    public void c(int i2) {
        this.v = i2;
    }

    public HttpRetryHandler F() {
        return this.w;
    }

    public void a(HttpRetryHandler httpRetryHandler) {
        this.w = httpRetryHandler;
    }

    public RedirectHandler G() {
        return this.x;
    }

    public void a(RedirectHandler redirectHandler) {
        this.x = redirectHandler;
    }

    public RequestTracker H() {
        return this.y;
    }

    public void a(RequestTracker requestTracker) {
        this.y = requestTracker;
    }

    private void I() {
        RequestParamsHelper.a(this, getClass(), new RequestParamsHelper.ParseKVListener() {
            public void a(String str, Object obj) {
                RequestParams.this.a(str, obj);
            }
        });
    }

    private HttpRequest J() {
        if (this.f10769a == null && !this.z) {
            this.z = true;
            Class<?> cls = getClass();
            if (cls != RequestParams.class) {
                this.f10769a = (HttpRequest) cls.getAnnotation(HttpRequest.class);
            }
        }
        return this.f10769a;
    }

    public String toString() {
        try {
            n();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        String o2 = o();
        if (TextUtils.isEmpty(o2)) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(o2);
        sb.append(o2.contains("?") ? a.b : "?");
        sb.append(super.toString());
        return sb.toString();
    }
}
