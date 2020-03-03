package com.unionpay.mobile.android.net;

import android.content.Context;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.text.TextUtils;
import com.unionpay.mobile.android.utils.c;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class b implements X509TrustManager {

    /* renamed from: a  reason: collision with root package name */
    private X509TrustManager f9579a = null;
    private X509TrustManagerExtensions b = null;
    private Context c;

    public b(Context context) throws NoSuchAlgorithmException, KeyStoreException {
        this.c = context;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers.length != 0) {
            this.f9579a = (X509TrustManager) trustManagers[0];
            if (Build.VERSION.SDK_INT > 23) {
                this.b = new X509TrustManagerExtensions(this.f9579a);
                return;
            }
            return;
        }
        throw new NoSuchAlgorithmException("no trust manager found");
    }

    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.f9579a.checkClientTrusted(x509CertificateArr, str);
    }

    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        boolean z;
        if (Build.VERSION.SDK_INT <= 23 || this.b == null) {
            this.f9579a.checkServerTrusted(x509CertificateArr, str);
        } else {
            this.b.checkServerTrusted(x509CertificateArr, str, "");
        }
        boolean z2 = false;
        try {
            X500Principal issuerX500Principal = x509CertificateArr[0].getIssuerX500Principal();
            ArrayList arrayList = new ArrayList(0);
            arrayList.add(".*(GeoTrust|VeriSign|Symantec|GlobalSign|Entrust|Thawte|DigiCert).*");
            String a2 = c.a(this.c);
            if (!TextUtils.isEmpty(a2)) {
                arrayList.add(a2);
            }
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    z = false;
                    break;
                } else if (Pattern.compile((String) arrayList.get(i), 2).matcher(issuerX500Principal.getName()).matches()) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                X500Principal subjectX500Principal = x509CertificateArr[0].getSubjectX500Principal();
                ArrayList arrayList2 = new ArrayList(0);
                arrayList2.add(".*CN=.*(\\.95516\\.com|\\.chinaunionpay\\.com|\\.unionpay\\.com|\\.unionpaysecure\\.com|\\.95516\\.net)(,.*|$)");
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList2.size()) {
                        break;
                    } else if (Pattern.compile((String) arrayList2.get(i2), 2).matcher(subjectX500Principal.getName()).matches()) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z2) {
                    throw new CertificateException();
                }
                return;
            }
            throw new CertificateException();
        } catch (Exception unused) {
            throw new CertificateException();
        }
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.f9579a.getAcceptedIssuers();
    }
}
