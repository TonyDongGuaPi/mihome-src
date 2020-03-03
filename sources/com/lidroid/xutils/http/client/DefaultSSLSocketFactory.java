package com.lidroid.xutils.http.client;

import com.lidroid.xutils.util.LogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class DefaultSSLSocketFactory extends SSLSocketFactory {
    private static KeyStore b;
    private static DefaultSSLSocketFactory c;

    /* renamed from: a  reason: collision with root package name */
    private SSLContext f6338a = SSLContext.getInstance("TLS");

    static {
        try {
            b = KeyStore.getInstance(KeyStore.getDefaultType());
            b.load((InputStream) null, (char[]) null);
        } catch (Throwable th) {
            LogUtils.b(th.getMessage(), th);
        }
    }

    public static DefaultSSLSocketFactory a() {
        if (c == null) {
            try {
                c = new DefaultSSLSocketFactory();
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
        }
        return c;
    }

    private DefaultSSLSocketFactory() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        super(b);
        AnonymousClass1 r0 = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        this.f6338a.init((KeyManager[]) null, new TrustManager[]{r0}, (SecureRandom) null);
        setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return this.f6338a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() throws IOException {
        return this.f6338a.getSocketFactory().createSocket();
    }
}
