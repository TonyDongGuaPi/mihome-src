package com.mibi.common.data;

import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import java.net.URL;
import org.json.JSONObject;

public class ConnectionCacheReader implements Connection {

    /* renamed from: a  reason: collision with root package name */
    private Connection f7510a;
    private Session b;
    private URL c;
    private String d;
    private JSONObject e;
    private int f;

    public void a(boolean z) {
    }

    public void b(boolean z) {
    }

    public ConnectionCacheReader(Connection connection, Session session) {
        this.f7510a = connection;
        this.b = session;
    }

    public static String a(String str, String str2) {
        return Utils.a((CharSequence) str, str2);
    }

    public JSONObject a() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public int c() {
        return this.f;
    }

    public SortedParameter d() {
        return this.f7510a.d();
    }

    public JSONObject e() throws PaymentException {
        f();
        try {
            this.e = new JSONObject(this.d);
            return this.e;
        } catch (Exception e2) {
            throw new ResultException((Throwable) e2);
        }
    }

    public String f() throws PaymentException {
        StorageDir k = this.b.k();
        String b2 = Coder.b(this.f7510a.g().toString());
        if (k.hasFile(b2)) {
            this.d = k.read(b2);
        } else {
            this.d = null;
        }
        return this.d;
    }

    public URL g() {
        return this.c;
    }
}
