package com.unionpay.a;

import android.content.Context;
import java.io.IOException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private SSLContext f9535a = null;
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    private static SSLContext a(Context context) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new b(context)}, (SecureRandom) null);
            return instance;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public final SSLContext a() {
        if (this.f9535a == null) {
            this.f9535a = a(this.b);
        }
        return this.f9535a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(a.class);
    }

    public int hashCode() {
        return a.class.hashCode();
    }
}
