package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.toolbox.BaseRequest;
import java.io.InputStream;

public class InputStreamRequest extends BaseRequest<InputStream> {
    public InputStreamRequest(Builder<?> builder) {
        super(builder);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.mishopsdk.volley.Response<java.io.InputStream> parseNetworkResponse(com.mishopsdk.volley.NetworkResponse r4) {
        /*
            r3 = this;
            byte[] r0 = r4.data
            boolean r1 = r3.mGzipEnabled
            if (r1 == 0) goto L_0x0016
            boolean r1 = r3.isGzipped(r4)
            if (r1 == 0) goto L_0x0016
            byte[] r1 = r3.decompressResponse(r0)     // Catch:{ IOException -> 0x0016 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0016 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0016 }
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            if (r2 != 0) goto L_0x001e
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r0)
        L_0x001e:
            com.mishopsdk.volley.Cache$Entry r4 = com.mishopsdk.volley.toolbox.HttpHeaderParser.parseCacheHeaders(r4)
            com.mishopsdk.volley.Response r4 = com.mishopsdk.volley.Response.success(r2, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mishopsdk.volley.toolbox.InputStreamRequest.parseNetworkResponse(com.mishopsdk.volley.NetworkResponse):com.mishopsdk.volley.Response");
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends BaseRequest.Builder<B> {
        public InputStreamRequest build() {
            return new InputStreamRequest(this);
        }
    }
}
