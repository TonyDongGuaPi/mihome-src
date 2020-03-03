package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class SyncHttpHandler {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractHttpClient f6337a;
    private final HttpContext b;
    private HttpRedirectHandler c;
    private String d;
    private String e;
    private String f;
    private int g = 0;
    private long h = HttpCache.a();

    public void a(HttpRedirectHandler httpRedirectHandler) {
        this.c = httpRedirectHandler;
    }

    public SyncHttpHandler(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str) {
        this.f6337a = abstractHttpClient;
        this.b = httpContext;
        this.f = str;
    }

    public void a(long j) {
        this.h = j;
    }

    public ResponseStream a(HttpRequestBase httpRequestBase) throws HttpException {
        IOException iOException;
        boolean z;
        String a2;
        HttpRequestRetryHandler httpRequestRetryHandler = this.f6337a.getHttpRequestRetryHandler();
        do {
            try {
                this.d = httpRequestBase.getURI().toString();
                this.e = httpRequestBase.getMethod();
                if (!HttpUtils.f6287a.b(this.e) || (a2 = HttpUtils.f6287a.a(this.d)) == null) {
                    return a(this.f6337a.execute((HttpUriRequest) httpRequestBase, this.b));
                }
                return new ResponseStream(a2);
            } catch (UnknownHostException e2) {
                iOException = e2;
                int i = this.g + 1;
                this.g = i;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.b);
                continue;
            } catch (IOException e3) {
                iOException = e3;
                int i2 = this.g + 1;
                this.g = i2;
                z = httpRequestRetryHandler.retryRequest(iOException, i2, this.b);
                continue;
            } catch (NullPointerException e4) {
                iOException = new IOException(e4.getMessage());
                iOException.initCause(e4);
                int i3 = this.g + 1;
                this.g = i3;
                z = httpRequestRetryHandler.retryRequest(iOException, i3, this.b);
                continue;
            } catch (HttpException e5) {
                throw e5;
            } catch (Throwable th) {
                iOException = new IOException(th.getMessage());
                iOException.initCause(th);
                int i4 = this.g + 1;
                this.g = i4;
                z = httpRequestRetryHandler.retryRequest(iOException, i4, this.b);
                continue;
            }
        } while (z);
        throw new HttpException((Throwable) iOException);
    }

    private ResponseStream a(HttpResponse httpResponse) throws HttpException, IOException {
        if (httpResponse != null) {
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode < 300) {
                ResponseStream responseStream = new ResponseStream(httpResponse, this.f, this.d, this.h);
                responseStream.a(this.e);
                return responseStream;
            } else if (statusCode == 301 || statusCode == 302) {
                if (this.c == null) {
                    this.c = new DefaultHttpRedirectHandler();
                }
                HttpRequestBase a2 = this.c.a(httpResponse);
                if (a2 != null) {
                    return a(a2);
                }
                return null;
            } else if (statusCode == 416) {
                throw new HttpException(statusCode, "maybe the file has downloaded completely");
            } else {
                throw new HttpException(statusCode, statusLine.getReasonPhrase());
            }
        } else {
            throw new HttpException("response is null");
        }
    }
}
