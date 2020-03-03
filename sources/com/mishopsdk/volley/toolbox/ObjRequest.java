package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.VolleyError;
import com.mishopsdk.volley.toolbox.BaseRequest;
import com.xiaomi.mishopsdk.io.http.ShopApiError;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class ObjRequest<T> extends BaseRequest<T> {
    /* access modifiers changed from: protected */
    public abstract Response<T> parseApiResponse(String str, NetworkResponse networkResponse) throws VolleyError;

    public ObjRequest(BaseRequest.Builder<?> builder) {
        super(builder);
    }

    /* access modifiers changed from: protected */
    public Response<T> parseNetworkResponse(NetworkResponse networkResponse) throws VolleyError {
        try {
            String parseCharset = HttpHeaderParser.parseCharset(networkResponse.headers);
            String str = null;
            if (this.mGzipEnabled && isGzipped(networkResponse)) {
                try {
                    str = new String(decompressResponse(networkResponse.data), parseCharset);
                } catch (IOException unused) {
                }
            }
            if (str == null) {
                str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            }
            return parseApiResponse(str, networkResponse);
        } catch (UnsupportedEncodingException e) {
            throw new ShopApiError(e, networkResponse);
        }
    }
}
