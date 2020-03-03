package com.unionpay.mobile.android.net;

import android.content.Context;
import java.io.IOException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private SSLContext f9578a = null;
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    private static SSLContext a(Context context) throws IOException {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new b(context)}, (SecureRandom) null);
            return instance;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public final SSLContext a() throws IOException {
        if (this.f9578a == null) {
            this.f9578a = a(this.b);
        }
        return this.f9578a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(a.class);
    }

    public int hashCode() {
        return a.class.hashCode();
    }
}
