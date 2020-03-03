package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class AopHttpClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11467a = "AopHttpClient";

    public static HttpResponse a(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        NetInfo netInfo = new NetInfo();
        HttpResponse execute = httpClient.execute(a(httpUriRequest, netInfo));
        a(execute, netInfo);
        return execute;
    }

    public static HttpResponse a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        NetInfo netInfo = new NetInfo();
        HttpResponse execute = httpClient.execute(httpHost, a(httpHost, httpRequest, netInfo), httpContext);
        a(execute, netInfo);
        return execute;
    }

    public static <T> T a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        NetInfo netInfo = new NetInfo();
        return httpClient.execute(httpHost, a(httpHost, httpRequest, netInfo), AopResponseHandler.a(responseHandler, netInfo), httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        NetInfo netInfo = new NetInfo();
        return httpClient.execute(httpHost, a(httpHost, httpRequest, netInfo), AopResponseHandler.a(responseHandler, netInfo));
    }

    public static HttpResponse a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        NetInfo netInfo = new NetInfo();
        HttpResponse execute = httpClient.execute(httpHost, a(httpHost, httpRequest, netInfo));
        a(execute, netInfo);
        return execute;
    }

    public static HttpResponse a(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        NetInfo netInfo = new NetInfo();
        HttpResponse execute = httpClient.execute(a(httpUriRequest, netInfo), httpContext);
        a(execute, netInfo);
        return execute;
    }

    public static <T> T a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        NetInfo netInfo = new NetInfo();
        return httpClient.execute(a(httpUriRequest, netInfo), AopResponseHandler.a(responseHandler, netInfo), httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        NetInfo netInfo = new NetInfo();
        return httpClient.execute(a(httpUriRequest, netInfo), AopResponseHandler.a(responseHandler, netInfo));
    }

    private static HttpUriRequest a(HttpUriRequest httpUriRequest, NetInfo netInfo) {
        netInfo.setURL(httpUriRequest.getURI().toString());
        netInfo.setStartTime(System.currentTimeMillis());
        if (!(httpUriRequest instanceof HttpEntityEnclosingRequest)) {
            return httpUriRequest;
        }
        HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpUriRequest;
        if (httpEntityEnclosingRequest.getEntity() != null) {
            httpEntityEnclosingRequest.setEntity(new AopHttpRequestEntity(httpEntityEnclosingRequest.getEntity(), netInfo));
        }
        return (HttpUriRequest) httpEntityEnclosingRequest;
    }

    private static HttpRequest a(HttpHost httpHost, HttpRequest httpRequest, NetInfo netInfo) {
        RequestLine requestLine = httpRequest.getRequestLine();
        netInfo.setStartTime(System.currentTimeMillis());
        netInfo.back_type = AndroidUtils.a(MiotMonitorManager.a().h()) ? 1 : 2;
        if (requestLine != null) {
            String uri = requestLine.getUri();
            boolean z = false;
            if (uri != null && uri.length() >= 10 && uri.substring(0, 10).indexOf("://") >= 0) {
                z = true;
            }
            if (!z && uri != null && httpHost != null) {
                String str = httpHost.toURI().toString();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((str.endsWith("/") || uri.startsWith("/")) ? "" : "/");
                sb.append(uri);
                netInfo.setURL(sb.toString());
            } else if (z) {
                netInfo.setURL(uri);
            }
        }
        if (!(httpRequest instanceof HttpEntityEnclosingRequest)) {
            return httpRequest;
        }
        HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
        if (httpEntityEnclosingRequest.getEntity() != null) {
            httpEntityEnclosingRequest.setEntity(new AopHttpRequestEntity(httpEntityEnclosingRequest.getEntity(), netInfo));
        }
        return httpEntityEnclosingRequest;
    }

    private static HttpResponse a(HttpResponse httpResponse, NetInfo netInfo) {
        netInfo.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        Header[] headers = httpResponse.getHeaders("Content-Length");
        netInfo.setCostTime(System.currentTimeMillis() - netInfo.startTime);
        if (headers != null && headers.length > 0) {
            try {
                netInfo.setReceivedBytes(Long.parseLong(headers[0].getValue()));
                netInfo.end();
            } catch (NumberFormatException unused) {
            }
        } else if (httpResponse.getEntity() != null) {
            httpResponse.setEntity(new AopHttpResponseEntity(httpResponse.getEntity(), netInfo));
        } else {
            netInfo.setReceivedBytes(0);
            netInfo.end();
        }
        return httpResponse;
    }
}
