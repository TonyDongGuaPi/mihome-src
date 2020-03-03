package com.lidroid.xutils;

import android.content.Context;
import android.text.TextUtils;
import com.lidroid.xutils.http.HttpCache;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.SyncHttpHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.client.DefaultSSLSocketFactory;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.RetryHandler;
import com.lidroid.xutils.http.client.entity.GZipDecompressingEntity;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.util.OtherUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final HttpCache f6287a = new HttpCache();
    private static final int g = 15000;
    private static final int h = 3;
    private static final String i = "Accept-Encoding";
    private static final String j = "gzip";
    private static final int k = 3;
    private static final PriorityExecutor l = new PriorityExecutor(3);
    private final DefaultHttpClient b;
    private final HttpContext c;
    private HttpRedirectHandler d;
    private String e;
    private long f;

    public HttpUtils() {
        this(15000, (String) null);
    }

    public HttpUtils(int i2) {
        this(i2, (String) null);
    }

    public HttpUtils(String str) {
        this(15000, str);
    }

    public HttpUtils(int i2, String str) {
        this.c = new BasicHttpContext();
        this.e = "UTF-8";
        this.f = HttpCache.a();
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) i2);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i2);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i2);
        HttpProtocolParams.setUserAgent(basicHttpParams, TextUtils.isEmpty(str) ? OtherUtils.a((Context) null) : str);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", DefaultSSLSocketFactory.a(), 443));
        this.b = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.b.setHttpRequestRetryHandler(new RetryHandler(3));
        this.b.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
            }
        });
        this.b.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                Header contentEncoding;
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null && (contentEncoding = entity.getContentEncoding()) != null) {
                    for (HeaderElement name : contentEncoding.getElements()) {
                        if (name.getName().equalsIgnoreCase("gzip")) {
                            httpResponse.setEntity(new GZipDecompressingEntity(httpResponse.getEntity()));
                            return;
                        }
                    }
                }
            }
        });
    }

    public HttpClient a() {
        return this.b;
    }

    public HttpUtils a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
        return this;
    }

    public HttpUtils a(HttpRedirectHandler httpRedirectHandler) {
        this.d = httpRedirectHandler;
        return this;
    }

    public HttpUtils a(int i2) {
        f6287a.a(i2);
        return this;
    }

    public HttpUtils a(long j2) {
        HttpCache.a(j2);
        this.f = HttpCache.a();
        return this;
    }

    public HttpUtils b(long j2) {
        this.f = j2;
        return this;
    }

    public HttpUtils a(CookieStore cookieStore) {
        this.c.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        return this;
    }

    public HttpUtils b(String str) {
        HttpProtocolParams.setUserAgent(this.b.getParams(), str);
        return this;
    }

    public HttpUtils b(int i2) {
        HttpParams params = this.b.getParams();
        ConnManagerParams.setTimeout(params, (long) i2);
        HttpConnectionParams.setConnectionTimeout(params, i2);
        return this;
    }

    public HttpUtils c(int i2) {
        HttpConnectionParams.setSoTimeout(this.b.getParams(), i2);
        return this;
    }

    public HttpUtils a(Scheme scheme) {
        this.b.getConnectionManager().getSchemeRegistry().register(scheme);
        return this;
    }

    public HttpUtils a(SSLSocketFactory sSLSocketFactory) {
        this.b.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
        return this;
    }

    public HttpUtils d(int i2) {
        this.b.setHttpRequestRetryHandler(new RetryHandler(i2));
        return this;
    }

    public HttpUtils e(int i2) {
        l.a(i2);
        return this;
    }

    public <T> HttpHandler<T> a(HttpRequest.HttpMethod httpMethod, String str, RequestCallBack<T> requestCallBack) {
        return a(httpMethod, str, (RequestParams) null, requestCallBack);
    }

    public <T> HttpHandler<T> a(HttpRequest.HttpMethod httpMethod, String str, RequestParams requestParams, RequestCallBack<T> requestCallBack) {
        if (str != null) {
            return a(new com.lidroid.xutils.http.client.HttpRequest(httpMethod, str), requestParams, requestCallBack);
        }
        throw new IllegalArgumentException("url may not be null");
    }

    public ResponseStream a(HttpRequest.HttpMethod httpMethod, String str) throws com.lidroid.xutils.exception.HttpException {
        return a(httpMethod, str, (RequestParams) null);
    }

    public ResponseStream a(HttpRequest.HttpMethod httpMethod, String str, RequestParams requestParams) throws com.lidroid.xutils.exception.HttpException {
        if (str != null) {
            return a(new com.lidroid.xutils.http.client.HttpRequest(httpMethod, str), requestParams);
        }
        throw new IllegalArgumentException("url may not be null");
    }

    public HttpHandler<File> a(String str, String str2, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, (RequestParams) null, false, false, requestCallBack);
    }

    public HttpHandler<File> a(String str, String str2, boolean z, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, (RequestParams) null, z, false, requestCallBack);
    }

    public HttpHandler<File> a(String str, String str2, boolean z, boolean z2, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, (RequestParams) null, z, z2, requestCallBack);
    }

    public HttpHandler<File> a(String str, String str2, RequestParams requestParams, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, requestParams, false, false, requestCallBack);
    }

    public HttpHandler<File> a(String str, String str2, RequestParams requestParams, boolean z, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, requestParams, z, false, requestCallBack);
    }

    public HttpHandler<File> a(String str, String str2, RequestParams requestParams, boolean z, boolean z2, RequestCallBack<File> requestCallBack) {
        return a(HttpRequest.HttpMethod.GET, str, str2, requestParams, z, z2, requestCallBack);
    }

    public HttpHandler<File> a(HttpRequest.HttpMethod httpMethod, String str, String str2, RequestParams requestParams, RequestCallBack<File> requestCallBack) {
        return a(httpMethod, str, str2, requestParams, false, false, requestCallBack);
    }

    public HttpHandler<File> a(HttpRequest.HttpMethod httpMethod, String str, String str2, RequestParams requestParams, boolean z, RequestCallBack<File> requestCallBack) {
        return a(httpMethod, str, str2, requestParams, z, false, requestCallBack);
    }

    public HttpHandler<File> a(HttpRequest.HttpMethod httpMethod, String str, String str2, RequestParams requestParams, boolean z, boolean z2, RequestCallBack<File> requestCallBack) {
        if (str == null) {
            throw new IllegalArgumentException("url may not be null");
        } else if (str2 != null) {
            com.lidroid.xutils.http.client.HttpRequest httpRequest = new com.lidroid.xutils.http.client.HttpRequest(httpMethod, str);
            HttpHandler<File> httpHandler = new HttpHandler<>(this.b, this.c, this.e, requestCallBack);
            httpHandler.a(this.f);
            httpHandler.a(this.d);
            if (requestParams != null) {
                httpRequest.a(requestParams, (RequestCallBackHandler) httpHandler);
                httpHandler.a(requestParams.a());
            }
            httpHandler.a((Executor) l, (Params[]) new Object[]{httpRequest, str2, Boolean.valueOf(z), Boolean.valueOf(z2)});
            return httpHandler;
        } else {
            throw new IllegalArgumentException("target may not be null");
        }
    }

    private <T> HttpHandler<T> a(com.lidroid.xutils.http.client.HttpRequest httpRequest, RequestParams requestParams, RequestCallBack<T> requestCallBack) {
        HttpHandler<T> httpHandler = new HttpHandler<>(this.b, this.c, this.e, requestCallBack);
        httpHandler.a(this.f);
        httpHandler.a(this.d);
        httpRequest.a(requestParams, (RequestCallBackHandler) httpHandler);
        if (requestParams != null) {
            httpHandler.a(requestParams.a());
        }
        httpHandler.a((Executor) l, (Params[]) new Object[]{httpRequest});
        return httpHandler;
    }

    private ResponseStream a(com.lidroid.xutils.http.client.HttpRequest httpRequest, RequestParams requestParams) throws com.lidroid.xutils.exception.HttpException {
        SyncHttpHandler syncHttpHandler = new SyncHttpHandler(this.b, this.c, this.e);
        syncHttpHandler.a(this.f);
        syncHttpHandler.a(this.d);
        httpRequest.a(requestParams);
        return syncHttpHandler.a((HttpRequestBase) httpRequest);
    }
}
