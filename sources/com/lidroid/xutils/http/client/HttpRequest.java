package com.lidroid.xutils.http.client;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.client.entity.UploadEntity;
import com.lidroid.xutils.http.client.util.URIBuilder;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.utils.CloneUtils;

public class HttpRequest extends HttpRequestBase implements HttpEntityEnclosingRequest {

    /* renamed from: a  reason: collision with root package name */
    private HttpEntity f6340a;
    private HttpMethod b;
    private URIBuilder c;
    private Charset d;

    public HttpRequest(HttpMethod httpMethod) {
        this.b = httpMethod;
    }

    public HttpRequest(HttpMethod httpMethod, String str) {
        this.b = httpMethod;
        a(str);
    }

    public HttpRequest(HttpMethod httpMethod, URI uri) {
        this.b = httpMethod;
        setURI(uri);
    }

    public HttpRequest a(String str, String str2) {
        this.c.b(str, str2);
        return this;
    }

    public HttpRequest a(NameValuePair nameValuePair) {
        this.c.b(nameValuePair.getName(), nameValuePair.getValue());
        return this;
    }

    public HttpRequest a(List<NameValuePair> list) {
        if (list != null) {
            for (NameValuePair next : list) {
                this.c.b(next.getName(), next.getValue());
            }
        }
        return this;
    }

    public void a(RequestParams requestParams) {
        if (requestParams != null) {
            if (this.d == null) {
                this.d = Charset.forName(requestParams.b());
            }
            List<RequestParams.HeaderItem> e = requestParams.e();
            if (e != null) {
                for (RequestParams.HeaderItem next : e) {
                    if (next.f6334a) {
                        setHeader(next.b);
                    } else {
                        addHeader(next.b);
                    }
                }
            }
            a(requestParams.d());
            setEntity(requestParams.c());
        }
    }

    public void a(RequestParams requestParams, RequestCallBackHandler requestCallBackHandler) {
        if (requestParams != null) {
            if (this.d == null) {
                this.d = Charset.forName(requestParams.b());
            }
            List<RequestParams.HeaderItem> e = requestParams.e();
            if (e != null) {
                for (RequestParams.HeaderItem next : e) {
                    if (next.f6334a) {
                        setHeader(next.b);
                    } else {
                        addHeader(next.b);
                    }
                }
            }
            a(requestParams.d());
            HttpEntity c2 = requestParams.c();
            if (c2 != null) {
                if (c2 instanceof UploadEntity) {
                    ((UploadEntity) c2).a(requestCallBackHandler);
                }
                setEntity(c2);
            }
        }
    }

    public URI getURI() {
        try {
            if (this.d == null) {
                this.d = OtherUtils.a((HttpRequestBase) this);
            }
            if (this.d == null) {
                this.d = Charset.forName("UTF-8");
            }
            return this.c.a(this.d);
        } catch (URISyntaxException e) {
            LogUtils.b(e.getMessage(), e);
            return null;
        }
    }

    public void setURI(URI uri) {
        this.c = new URIBuilder(uri);
    }

    public void a(String str) {
        this.c = new URIBuilder(str);
    }

    public String getMethod() {
        return this.b.toString();
    }

    public HttpEntity getEntity() {
        return this.f6340a;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.f6340a = httpEntity;
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader("Expect");
        return firstHeader != null && "100-Continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public Object clone() throws CloneNotSupportedException {
        HttpRequest httpRequest = (HttpRequest) super.clone();
        if (this.f6340a != null) {
            httpRequest.f6340a = (HttpEntity) CloneUtils.clone(this.f6340a);
        }
        return httpRequest;
    }

    public enum HttpMethod {
        GET("GET"),
        POST("POST"),
        PUT(HttpPut.METHOD_NAME),
        HEAD("HEAD"),
        MOVE("MOVE"),
        COPY("COPY"),
        DELETE("DELETE"),
        OPTIONS(HttpOptions.METHOD_NAME),
        TRACE(HttpTrace.METHOD_NAME),
        CONNECT("CONNECT");
        
        private final String value;

        private HttpMethod(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }
}
