package com.lidroid.xutils.http.callback;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

public class DefaultHttpRedirectHandler implements HttpRedirectHandler {
    public HttpRequestBase a(HttpResponse httpResponse) {
        if (!httpResponse.containsHeader("Location")) {
            return null;
        }
        HttpGet httpGet = new HttpGet(httpResponse.getFirstHeader("Location").getValue());
        if (httpResponse.containsHeader("Set-Cookie")) {
            httpGet.addHeader("Cookie", httpResponse.getFirstHeader("Set-Cookie").getValue());
        }
        return httpGet;
    }
}
