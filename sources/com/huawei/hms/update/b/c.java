package com.huawei.hms.update.b;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

final class c {
    public static void a(HttpsURLConnection httpsURLConnection) {
        SocketFactory a2 = e.a();
        if (a2 instanceof SSLSocketFactory) {
            httpsURLConnection.setSSLSocketFactory((SSLSocketFactory) a2);
        }
    }
}
