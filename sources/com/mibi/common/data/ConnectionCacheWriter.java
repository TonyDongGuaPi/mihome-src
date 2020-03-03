package com.mibi.common.data;

import com.mibi.common.exception.PaymentException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionCacheWriter implements Connection {

    /* renamed from: a  reason: collision with root package name */
    private Connection f7511a;
    private Session b;

    public ConnectionCacheWriter(Connection connection, Session session) {
        this.f7511a = connection;
        this.b = session;
    }

    public static String a(String str, String str2) {
        return Utils.a((CharSequence) str, str2);
    }

    public JSONObject a() {
        return this.f7511a.a();
    }

    public String b() {
        return this.f7511a.b();
    }

    public int c() {
        return this.f7511a.c();
    }

    public SortedParameter d() {
        return this.f7511a.d();
    }

    public void a(boolean z) {
        this.f7511a.a(z);
    }

    public void b(boolean z) {
        this.f7511a.b(z);
    }

    public JSONObject e() throws PaymentException {
        int i;
        JSONObject e = this.f7511a.e();
        try {
            i = e.getInt("errcode");
        } catch (JSONException unused) {
            i = 0;
        }
        if (i == 200) {
            this.b.k().write(Coder.b(this.f7511a.g().toString()), e.toString());
        }
        return e;
    }

    public String f() throws PaymentException {
        return this.f7511a.f();
    }

    public URL g() {
        return this.f7511a.g();
    }
}
