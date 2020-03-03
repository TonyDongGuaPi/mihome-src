package com.paytm.pgsdk;

import android.content.Context;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PaytmSSLSocketFactory extends SSLSocketFactory {
    private static final String b = "pkcs12";
    private static final String c = "X509";
    private static final String d = "TLS";
    private static final String e = "raw";

    /* renamed from: a  reason: collision with root package name */
    private volatile SSLContext f8547a;

    protected PaytmSSLSocketFactory(Context context, PaytmClientCertificate paytmClientCertificate) {
        AnonymousClass1 r0 = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        if (paytmClientCertificate != null) {
            try {
                if (paytmClientCertificate.b != null) {
                    PaytmUtility.a("Reading the certificate " + paytmClientCertificate.b + ".p12");
                    KeyStore instance = KeyStore.getInstance(b);
                    instance.load(context.getResources().openRawResource(context.getResources().getIdentifier(paytmClientCertificate.b, "raw", context.getPackageName())), paytmClientCertificate.f8535a.toCharArray());
                    Enumeration<String> aliases = instance.aliases();
                    while (aliases.hasMoreElements()) {
                        PaytmUtility.a(instance.getCertificate(aliases.nextElement().toString()).toString());
                    }
                    KeyManagerFactory instance2 = KeyManagerFactory.getInstance(c);
                    instance2.init(instance, paytmClientCertificate.f8535a.toCharArray());
                    this.f8547a = SSLContext.getInstance(d);
                    this.f8547a.init(instance2.getKeyManagers(), new TrustManager[]{r0}, (SecureRandom) null);
                    PaytmUtility.a("Client certificate attached.");
                    return;
                }
            } catch (Exception e2) {
                PaytmUtility.a("Exception while attaching Client certificate.");
                PaytmUtility.a(e2);
                try {
                    PaytmUtility.a("so, setting only the trust manager");
                    this.f8547a = SSLContext.getInstance(d);
                    this.f8547a.init((KeyManager[]) null, new TrustManager[]{r0}, (SecureRandom) null);
                    PaytmUtility.a("set trust manager");
                    return;
                } catch (Exception e3) {
                    PaytmUtility.a("Exception while setting the trust manager");
                    PaytmUtility.a(e3);
                    return;
                }
            }
        }
        PaytmUtility.a("Client certificate is not found");
        PaytmUtility.a("so, setting only the trust manager");
        this.f8547a = SSLContext.getInstance(d);
        this.f8547a.init((KeyManager[]) null, new TrustManager[]{r0}, (SecureRandom) null);
        PaytmUtility.a("set trust manager");
    }

    public synchronized Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.f8547a != null) {
            return this.f8547a.getSocketFactory().createSocket(socket, str, i, z);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized String[] getDefaultCipherSuites() {
        return null;
    }

    public synchronized String[] getSupportedCipherSuites() {
        return null;
    }

    public synchronized Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        if (this.f8547a != null) {
            return this.f8547a.getSocketFactory().createSocket(str, i);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        if (this.f8547a != null) {
            return this.f8547a.getSocketFactory().createSocket(inetAddress, i);
        }
        return getDefault().createSocket(inetAddress, i);
    }

    public synchronized Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        if (this.f8547a != null) {
            return this.f8547a.getSocketFactory().createSocket(str, i, inetAddress, i2);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        if (this.f8547a != null) {
            return this.f8547a.getSocketFactory().createSocket(inetAddress, i, inetAddress2, i2);
        }
        return getDefault().createSocket(inetAddress, i, inetAddress2, i2);
    }
}
