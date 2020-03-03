package org.xutils.http.app;

import com.alipay.sdk.sys.a;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

public class DefaultParamsBuilder implements ParamsBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static SSLSocketFactory f1594a;

    public void a(RequestParams requestParams) {
    }

    public void b(RequestParams requestParams, String[] strArr) {
    }

    public String a(RequestParams requestParams, HttpRequest httpRequest) {
        return httpRequest.host() + "/" + httpRequest.path();
    }

    public String a(RequestParams requestParams, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String str = requestParams.o() + "?";
        for (String str2 : strArr) {
            String c = requestParams.c(str2);
            if (c != null) {
                str = str + str2 + "=" + c + a.b;
            }
        }
        return str;
    }

    public SSLSocketFactory a() {
        return b();
    }

    public static SSLSocketFactory b() {
        if (f1594a == null) {
            synchronized (DefaultParamsBuilder.class) {
                if (f1594a == null) {
                    TrustManager[] trustManagerArr = {new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                        }

                        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }};
                    try {
                        SSLContext instance = SSLContext.getInstance("TLS");
                        instance.init((KeyManager[]) null, trustManagerArr, (SecureRandom) null);
                        f1594a = instance.getSocketFactory();
                    } catch (Throwable th) {
                        LogUtil.b(th.getMessage(), th);
                    }
                }
            }
        }
        return f1594a;
    }
}
