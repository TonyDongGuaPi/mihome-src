package com.lidroid.xutils.http;

import android.os.SystemClock;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.task.PriorityAsyncTask;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class HttpHandler<T> extends PriorityAsyncTask<Object, Object, Void> implements RequestCallBackHandler {
    private static final int r = 1;
    private static final int s = 2;
    private static final int t = 3;
    private static final int u = 4;
    private static final NotUseApacheRedirectHandler w = new NotUseApacheRedirectHandler((NotUseApacheRedirectHandler) null);

    /* renamed from: a  reason: collision with root package name */
    private final AbstractHttpClient f6332a;
    private final HttpContext b;
    private HttpRedirectHandler c;
    private String d;
    private String e;
    private HttpRequestBase f;
    private boolean g = true;
    private RequestCallBack<T> h;
    private int i = 0;
    private String j = null;
    private boolean k = false;
    private boolean m = false;
    private boolean n = false;
    private String o;
    private State p = State.WAITING;
    private long q = HttpCache.a();
    private long v;

    public void a(HttpRedirectHandler httpRedirectHandler) {
        if (httpRedirectHandler != null) {
            this.c = httpRedirectHandler;
        }
    }

    public HttpHandler(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str, RequestCallBack<T> requestCallBack) {
        this.f6332a = abstractHttpClient;
        this.b = httpContext;
        this.h = requestCallBack;
        this.o = str;
        this.f6332a.setRedirectHandler(w);
    }

    public State a() {
        return this.p;
    }

    public void a(long j2) {
        this.q = j2;
    }

    public void a(RequestCallBack<T> requestCallBack) {
        this.h = requestCallBack;
    }

    public RequestCallBack<T> b() {
        return this.h;
    }

    private ResponseInfo<T> a(HttpRequestBase httpRequestBase) throws HttpException {
        IOException iOException;
        boolean z;
        String a2;
        HttpRequestRetryHandler httpRequestRetryHandler = this.f6332a.getHttpRequestRetryHandler();
        do {
            if (this.m && this.k) {
                File file = new File(this.j);
                long length = (!file.isFile() || !file.exists()) ? 0 : file.length();
                if (length > 0) {
                    httpRequestBase.setHeader("RANGE", "bytes=" + length + "-");
                }
            }
            try {
                this.e = httpRequestBase.getMethod();
                if (HttpUtils.f6287a.b(this.e) && (a2 = HttpUtils.f6287a.a(this.d)) != null) {
                    return new ResponseInfo<>((HttpResponse) null, a2, true);
                }
                if (!m()) {
                    return a(this.f6332a.execute((HttpUriRequest) httpRequestBase, this.b));
                }
                return null;
            } catch (UnknownHostException e2) {
                iOException = e2;
                int i2 = this.i + 1;
                this.i = i2;
                z = httpRequestRetryHandler.retryRequest(iOException, i2, this.b);
                continue;
            } catch (IOException e3) {
                iOException = e3;
                int i3 = this.i + 1;
                this.i = i3;
                z = httpRequestRetryHandler.retryRequest(iOException, i3, this.b);
                continue;
            } catch (NullPointerException e4) {
                iOException = new IOException(e4.getMessage());
                iOException.initCause(e4);
                int i4 = this.i + 1;
                this.i = i4;
                z = httpRequestRetryHandler.retryRequest(iOException, i4, this.b);
                continue;
            } catch (HttpException e5) {
                throw e5;
            } catch (Throwable th) {
                iOException = new IOException(th.getMessage());
                iOException.initCause(th);
                int i5 = this.i + 1;
                this.i = i5;
                z = httpRequestRetryHandler.retryRequest(iOException, i5, this.b);
                continue;
            }
        } while (z);
        throw new HttpException((Throwable) iOException);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void c(Object... objArr) {
        if (this.p == State.CANCELLED || objArr == null || objArr.length == 0) {
            return null;
        }
        if (objArr.length > 3) {
            this.j = String.valueOf(objArr[1]);
            this.k = this.j != null;
            this.m = objArr[2].booleanValue();
            this.n = objArr[3].booleanValue();
        }
        try {
            if (this.p == State.CANCELLED) {
                return null;
            }
            this.f = objArr[0];
            this.d = this.f.getURI().toString();
            if (this.h != null) {
                this.h.setRequestUrl(this.d);
            }
            f(1);
            this.v = SystemClock.uptimeMillis();
            ResponseInfo a2 = a(this.f);
            if (a2 != null) {
                f(4, a2);
                return null;
            }
            return null;
        } catch (HttpException e2) {
            f(3, e2, e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void b(Object... objArr) {
        if (this.p != State.CANCELLED && objArr != null && objArr.length != 0 && this.h != null) {
            switch (objArr[0].intValue()) {
                case 1:
                    this.p = State.STARTED;
                    this.h.onStart();
                    return;
                case 2:
                    if (objArr.length == 3) {
                        this.p = State.LOADING;
                        this.h.onLoading(Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue(), this.g);
                        return;
                    }
                    return;
                case 3:
                    if (objArr.length == 3) {
                        this.p = State.FAILURE;
                        this.h.onFailure(objArr[1], objArr[2]);
                        return;
                    }
                    return;
                case 4:
                    if (objArr.length == 2) {
                        this.p = State.SUCCESS;
                        this.h.onSuccess(objArr[1]);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.lidroid.xutils.http.ResponseInfo<T> a(org.apache.http.HttpResponse r11) throws com.lidroid.xutils.exception.HttpException, java.io.IOException {
        /*
            r10 = this;
            if (r11 == 0) goto L_0x00a9
            boolean r0 = r10.m()
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            org.apache.http.StatusLine r0 = r11.getStatusLine()
            int r2 = r0.getStatusCode()
            r3 = 300(0x12c, float:4.2E-43)
            if (r2 >= r3) goto L_0x0071
            org.apache.http.HttpEntity r5 = r11.getEntity()
            r0 = 0
            if (r5 == 0) goto L_0x006b
            r10.g = r0
            boolean r2 = r10.k
            if (r2 == 0) goto L_0x004a
            boolean r2 = r10.m
            if (r2 == 0) goto L_0x002f
            boolean r2 = com.lidroid.xutils.util.OtherUtils.a((org.apache.http.HttpResponse) r11)
            if (r2 == 0) goto L_0x002f
            r2 = 1
            goto L_0x0030
        L_0x002f:
            r2 = 0
        L_0x0030:
            r10.m = r2
            boolean r2 = r10.n
            if (r2 == 0) goto L_0x003a
            java.lang.String r1 = com.lidroid.xutils.util.OtherUtils.b(r11)
        L_0x003a:
            r9 = r1
            com.lidroid.xutils.http.callback.FileDownloadHandler r4 = new com.lidroid.xutils.http.callback.FileDownloadHandler
            r4.<init>()
            java.lang.String r7 = r10.j
            boolean r8 = r10.m
            r6 = r10
            java.io.File r1 = r4.a(r5, r6, r7, r8, r9)
            goto L_0x006b
        L_0x004a:
            com.lidroid.xutils.http.callback.StringDownloadHandler r1 = new com.lidroid.xutils.http.callback.StringDownloadHandler
            r1.<init>()
            java.lang.String r2 = r10.o
            java.lang.String r1 = r1.a(r5, r10, r2)
            com.lidroid.xutils.http.HttpCache r2 = com.lidroid.xutils.HttpUtils.f6287a
            java.lang.String r3 = r10.e
            boolean r2 = r2.b(r3)
            if (r2 == 0) goto L_0x006b
            com.lidroid.xutils.http.HttpCache r2 = com.lidroid.xutils.HttpUtils.f6287a
            java.lang.String r3 = r10.d
            r4 = r1
            java.lang.String r4 = (java.lang.String) r4
            long r5 = r10.q
            r2.a(r3, r4, r5)
        L_0x006b:
            com.lidroid.xutils.http.ResponseInfo r2 = new com.lidroid.xutils.http.ResponseInfo
            r2.<init>(r11, r1, r0)
            return r2
        L_0x0071:
            r3 = 301(0x12d, float:4.22E-43)
            if (r2 == r3) goto L_0x0090
            r3 = 302(0x12e, float:4.23E-43)
            if (r2 != r3) goto L_0x007a
            goto L_0x0090
        L_0x007a:
            r11 = 416(0x1a0, float:5.83E-43)
            if (r2 != r11) goto L_0x0086
            com.lidroid.xutils.exception.HttpException r11 = new com.lidroid.xutils.exception.HttpException
            java.lang.String r0 = "maybe the file has downloaded completely"
            r11.<init>((int) r2, (java.lang.String) r0)
            throw r11
        L_0x0086:
            com.lidroid.xutils.exception.HttpException r11 = new com.lidroid.xutils.exception.HttpException
            java.lang.String r0 = r0.getReasonPhrase()
            r11.<init>((int) r2, (java.lang.String) r0)
            throw r11
        L_0x0090:
            com.lidroid.xutils.http.callback.HttpRedirectHandler r0 = r10.c
            if (r0 != 0) goto L_0x009b
            com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler r0 = new com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler
            r0.<init>()
            r10.c = r0
        L_0x009b:
            com.lidroid.xutils.http.callback.HttpRedirectHandler r0 = r10.c
            org.apache.http.client.methods.HttpRequestBase r11 = r0.a(r11)
            if (r11 == 0) goto L_0x00a8
            com.lidroid.xutils.http.ResponseInfo r11 = r10.a((org.apache.http.client.methods.HttpRequestBase) r11)
            return r11
        L_0x00a8:
            return r1
        L_0x00a9:
            com.lidroid.xutils.exception.HttpException r11 = new com.lidroid.xutils.exception.HttpException
            java.lang.String r0 = "response is null"
            r11.<init>((java.lang.String) r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.http.HttpHandler.a(org.apache.http.HttpResponse):com.lidroid.xutils.http.ResponseInfo");
    }

    public void k() {
        this.p = State.CANCELLED;
        if (this.f != null && !this.f.isAborted()) {
            try {
                this.f.abort();
            } catch (Throwable unused) {
            }
        }
        if (!m()) {
            try {
                a(true);
            } catch (Throwable unused2) {
            }
        }
        if (this.h != null) {
            this.h.onCancelled();
        }
    }

    public boolean a(long j2, long j3, boolean z) {
        if (!(this.h == null || this.p == State.CANCELLED)) {
            if (z) {
                f(2, Long.valueOf(j2), Long.valueOf(j3));
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.v >= ((long) this.h.getRate())) {
                    this.v = uptimeMillis;
                    f(2, Long.valueOf(j2), Long.valueOf(j3));
                }
            }
        }
        return this.p != State.CANCELLED;
    }

    public enum State {
        WAITING(0),
        STARTED(1),
        LOADING(2),
        FAILURE(3),
        CANCELLED(4),
        SUCCESS(5);
        
        private int value;

        private State(int i) {
            this.value = 0;
            this.value = i;
        }

        public static State valueOf(int i) {
            switch (i) {
                case 0:
                    return WAITING;
                case 1:
                    return STARTED;
                case 2:
                    return LOADING;
                case 3:
                    return FAILURE;
                case 4:
                    return CANCELLED;
                case 5:
                    return SUCCESS;
                default:
                    return FAILURE;
            }
        }

        public int value() {
            return this.value;
        }
    }

    private static final class NotUseApacheRedirectHandler implements RedirectHandler {
        public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
            return null;
        }

        public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
            return false;
        }

        private NotUseApacheRedirectHandler() {
        }

        /* synthetic */ NotUseApacheRedirectHandler(NotUseApacheRedirectHandler notUseApacheRedirectHandler) {
            this();
        }
    }
}
