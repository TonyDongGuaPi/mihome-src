package com.xiaomi.youpin.log;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import okhttp3.Request;
import okhttp3.Response;

public interface NetMonitorInterface {
    void onOkHttp(Request request, Response response, long j);

    void onWebView(WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse, long j);
}
