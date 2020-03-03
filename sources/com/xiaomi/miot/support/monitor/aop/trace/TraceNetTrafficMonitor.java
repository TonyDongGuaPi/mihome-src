package com.xiaomi.miot.support.monitor.aop.trace;

import com.xiaomi.miot.support.monitor.core.net.i.QHC;
import com.xiaomi.miot.support.monitor.core.net.i.QURL;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TraceNetTrafficMonitor {

    /* renamed from: a  reason: collision with root package name */
    public static final TraceNetTrafficMonitor f11450a = null;
    private static Throwable b;

    static {
        try {
            d();
        } catch (Throwable th) {
            b = th;
        }
    }

    public static TraceNetTrafficMonitor b() {
        if (f11450a != null) {
            return f11450a;
        }
        throw new NoAspectBoundException("com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor", b);
    }

    public static boolean c() {
        return f11450a != null;
    }

    private static void d() {
        f11450a = new TraceNetTrafficMonitor();
    }

    @Pointcut("(!within(com.xiaomi.miot.support.monitor.core..*) && !within(com.xiaomi.miot.support.monitor.leak..*))")
    public void a() {
    }

    @Pointcut("call(java.net.URLConnection openConnection()) && (target(url) && baseCondition())")
    public void a(URL url) {
    }

    @Pointcut("call(java.net.URLConnection openConnection(java.net.Proxy)) && (target(url) && (args(proxy) && baseCondition()))")
    public void a(URL url, Proxy proxy) {
    }

    @Pointcut("call(org.apache.http.HttpResponse org.apache.http.client.HttpClient.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest)) && (target(httpClient) && (args(target, request) && baseCondition()))")
    public void a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) {
    }

    @Pointcut("call(* org.apache.http.client.HttpClient.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.client.ResponseHandler)) && (target(httpClient) && (args(target, request, responseHandler) && baseCondition()))")
    public void a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler) {
    }

    @Pointcut("call(* org.apache.http.client.HttpClient.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.client.ResponseHandler, org.apache.http.protocol.HttpContext)) && (target(httpClient) && (args(target, request, responseHandler, context) && baseCondition()))")
    public void a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler, HttpContext httpContext) {
    }

    @Pointcut("call(org.apache.http.HttpResponse org.apache.http.client.HttpClient.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.protocol.HttpContext)) && (target(httpClient) && (args(target, request, context) && baseCondition()))")
    public void a(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
    }

    @Pointcut("call(org.apache.http.HttpResponse org.apache.http.client.HttpClient.execute(org.apache.http.client.methods.HttpUriRequest)) && (target(httpClient) && (args(request) && baseCondition()))")
    public void a(HttpClient httpClient, HttpUriRequest httpUriRequest) {
    }

    @Pointcut("call(* org.apache.http.client.HttpClient.execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.ResponseHandler)) && (target(httpClient) && (args(request, responseHandler) && baseCondition()))")
    public void a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler responseHandler) {
    }

    @Pointcut("call(* org.apache.http.client.HttpClient.execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.ResponseHandler, org.apache.http.protocol.HttpContext)) && (target(httpClient) && (args(request, responseHandler, context) && baseCondition()))")
    public void a(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler responseHandler, HttpContext httpContext) {
    }

    @Pointcut("call(org.apache.http.HttpResponse org.apache.http.client.HttpClient.execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.protocol.HttpContext)) && (target(httpClient) && (args(request, context) && baseCondition()))")
    public void a(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) {
    }

    @Around("httpClientExecuteOne(httpClient, request)")
    public HttpResponse b(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        return QHC.a(httpClient, httpUriRequest);
    }

    @Around("httpClientExecute2(httpClient, request, context)")
    public HttpResponse b(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        return QHC.a(httpClient, httpUriRequest, httpContext);
    }

    @Around("httpClientExecuteThree(httpClient, target, request)")
    public HttpResponse b(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return QHC.a(httpClient, httpHost, httpRequest);
    }

    @Around("httpClientExecuteFour(httpClient, target, request, context)")
    public HttpResponse b(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        return QHC.a(httpClient, httpHost, httpRequest, httpContext);
    }

    @Around("httpClientExecuteFive(httpClient, request, responseHandler)")
    public Object b(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler responseHandler) throws IOException {
        return QHC.a(httpClient, httpUriRequest, responseHandler);
    }

    @Around("httpClientExecuteSix(httpClient, request, responseHandler, context)")
    public Object b(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler responseHandler, HttpContext httpContext) throws IOException {
        return QHC.a(httpClient, httpUriRequest, responseHandler, httpContext);
    }

    @Around("httpClientExecuteSeven(httpClient, target, request, responseHandler)")
    public Object b(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler) throws IOException {
        return QHC.a(httpClient, httpHost, httpRequest, responseHandler);
    }

    @Around("httpClientExecuteEight(httpClient, target, request, responseHandler, context)")
    public Object b(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler, HttpContext httpContext) throws IOException {
        return QHC.a(httpClient, httpHost, httpRequest, responseHandler, httpContext);
    }

    @Around("URLOpenConnectionOne(url)")
    public URLConnection b(URL url) throws IOException {
        return QURL.a(url);
    }

    @Around("URLOpenConnectionTwo(url, proxy)")
    public URLConnection b(URL url, Proxy proxy) throws IOException {
        return QURL.a(url, proxy);
    }
}
