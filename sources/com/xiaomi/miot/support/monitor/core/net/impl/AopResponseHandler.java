package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

public class AopResponseHandler<T> implements ResponseHandler<T> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11473a = "AopResponseHandler";
    private final ResponseHandler<T> b;
    private final NetInfo c;

    private AopResponseHandler(ResponseHandler<T> responseHandler, NetInfo netInfo) {
        this.b = responseHandler;
        this.c = netInfo;
    }

    public T handleResponse(HttpResponse httpResponse) throws IOException {
        this.c.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        Header[] headers = httpResponse.getHeaders("Content-Length");
        if (headers != null && headers.length > 0) {
            try {
                this.c.setReceivedBytes(Long.parseLong(headers[0].getValue()));
                if (this.c.startTime > 0) {
                    this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
                }
                this.c.end();
            } catch (NumberFormatException unused) {
            }
        } else if (httpResponse.getEntity() != null) {
            httpResponse.setEntity(new AopHttpResponseEntity(httpResponse.getEntity(), this.c));
        } else {
            this.c.setReceivedBytes(0);
            if (this.c.startTime > 0) {
                this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
            }
            this.c.end();
        }
        return this.b.handleResponse(httpResponse);
    }

    public static <T> ResponseHandler<? extends T> a(ResponseHandler<? extends T> responseHandler, NetInfo netInfo) {
        return new AopResponseHandler(responseHandler, netInfo);
    }
}
