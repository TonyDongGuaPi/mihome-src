package com.xiaomi.miot.support.monitor.core.net.i;

import com.xiaomi.miot.support.monitor.core.net.impl.AopHttpClient;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class QHC {
    public static HttpResponse a(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpUriRequest);
        }
        return httpClient.execute(httpUriRequest);
    }

    public static HttpResponse a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpHost, httpRequest, httpContext);
        }
        return httpClient.execute(httpHost, httpRequest, httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpHost, httpRequest, responseHandler, httpContext);
        }
        return httpClient.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpHost, httpRequest, responseHandler);
        }
        return httpClient.execute(httpHost, httpRequest, responseHandler);
    }

    public static HttpResponse a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpHost, httpRequest);
        }
        return httpClient.execute(httpHost, httpRequest);
    }

    public static HttpResponse a(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpUriRequest, httpContext);
        }
        return httpClient.execute(httpUriRequest, httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpUriRequest, responseHandler, httpContext);
        }
        return httpClient.execute(httpUriRequest, responseHandler, httpContext);
    }

    public static <T> T a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        if (a()) {
            return AopHttpClient.a(httpClient, httpUriRequest, responseHandler);
        }
        return httpClient.execute(httpUriRequest, responseHandler);
    }

    private static boolean a() {
        return TaskManager.a().b("net");
    }
}
