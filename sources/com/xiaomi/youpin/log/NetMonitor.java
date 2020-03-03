package com.xiaomi.youpin.log;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetMonitor {
    public static NetMonitorInterface sNetMonitorInterface;

    public static synchronized void setNetMonitorInterface(NetMonitorInterface netMonitorInterface) {
        synchronized (NetMonitor.class) {
            sNetMonitorInterface = netMonitorInterface;
        }
    }

    public static void onOkHttp(Request request, Response response, long j) {
        if (sNetMonitorInterface != null) {
            sNetMonitorInterface.onOkHttp(request, response, j);
        }
    }

    public static void onWebView(WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse, long j) {
        if (sNetMonitorInterface != null) {
            sNetMonitorInterface.onWebView(webResourceRequest, webResourceResponse, j);
        }
    }

    public static void addNetworkInterceptor(OkHttpClient.Builder builder) {
        if (builder != null && sNetMonitorInterface != null) {
            builder.addNetworkInterceptor(new OkHttpNetInterceptor());
        }
    }
}
