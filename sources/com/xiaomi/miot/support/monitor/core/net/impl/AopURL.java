package com.xiaomi.miot.support.monitor.core.net.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class AopURL {
    public static URLConnection a(URL url) throws IOException {
        if (url == null) {
            return null;
        }
        return a(url.openConnection());
    }

    public static URLConnection a(URL url, Proxy proxy) throws IOException {
        if (url == null) {
            return null;
        }
        return a(url.openConnection(proxy));
    }

    private static URLConnection a(URLConnection uRLConnection) {
        if (uRLConnection == null) {
            return null;
        }
        if (uRLConnection instanceof HttpsURLConnection) {
            return new AopHttpsURLConnection((HttpsURLConnection) uRLConnection);
        }
        return uRLConnection instanceof HttpURLConnection ? new AopHttpURLConnection((HttpURLConnection) uRLConnection) : uRLConnection;
    }
}
