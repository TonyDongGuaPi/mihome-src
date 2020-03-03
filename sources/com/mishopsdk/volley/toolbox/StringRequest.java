package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.toolbox.BaseRequest;
import java.io.UnsupportedEncodingException;

public class StringRequest extends BaseRequest<String> {
    public StringRequest(Builder<?> builder) {
        super(builder);
    }

    /* access modifiers changed from: protected */
    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String str;
        try {
            str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            str = new String(networkResponse.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends BaseRequest.Builder<B> {
        public StringRequest build() {
            return new StringRequest(this);
        }
    }
}
