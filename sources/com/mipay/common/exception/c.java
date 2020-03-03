package com.mipay.common.exception;

import com.mipay.common.exception.b;
import java.io.IOException;
import java.net.URL;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;

public class c extends d {

    /* renamed from: a  reason: collision with root package name */
    private URL f8137a;

    public c(Throwable th) {
        super(a((URL) null, th));
    }

    public c(URL url, String str, Throwable th) {
        super(str, a(url, th));
        this.f8137a = url;
    }

    public c(URL url, Throwable th) {
        super(a(url, th));
        this.f8137a = url;
    }

    private static Throwable a(URL url, Throwable th) {
        if (th instanceof IOException) {
            for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
                if (th2 instanceof CertificateNotYetValidException) {
                    return new b(b.a.NOT_YET_VALID, th);
                }
                if (th2 instanceof CertificateExpiredException) {
                    return new b(b.a.EXPIRED, th);
                }
            }
        }
        return th;
    }

    public String a() {
        return "CN";
    }

    public URL b() {
        return this.f8137a;
    }
}
