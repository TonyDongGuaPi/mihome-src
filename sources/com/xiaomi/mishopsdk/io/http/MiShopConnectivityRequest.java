package com.xiaomi.mishopsdk.io.http;

import com.mishopsdk.volley.AuthFailureError;
import com.mishopsdk.volley.DefaultRetryPolicy;
import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.toolbox.BaseRequest;
import com.mishopsdk.volley.toolbox.HttpHeaderParser;
import java.util.Collections;
import java.util.Map;

public class MiShopConnectivityRequest extends BaseRequest<byte[]> {
    public MiShopConnectivityRequest(Builder<?> builder) {
        super(builder);
        setRetryPolicy(new DefaultRetryPolicy(7000, 3, 1.1f));
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends BaseRequest.Builder<B> {
        public MiShopConnectivityRequest build() {
            return new MiShopConnectivityRequest(this);
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    /* access modifiers changed from: protected */
    public Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
