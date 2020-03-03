package miuipub.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

class DefaultHttpResponse implements HttpResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2974a;
    private InputStream b;
    private String c;
    private String d;
    private long e;
    private Map<String, String> f;

    public DefaultHttpResponse(int i, Map<String, String> map, InputStream inputStream, long j, String str, String str2) {
        this.f2974a = i;
        this.b = inputStream;
        this.e = j;
        this.c = str;
        this.d = str2;
        this.f = map;
    }

    public void a(InputStream inputStream, long j) {
        this.b = inputStream;
        this.e = j;
    }

    public int a() {
        return this.f2974a;
    }

    public InputStream b() {
        return this.b;
    }

    public long c() {
        return this.e;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public Map<String, String> f() {
        return this.f;
    }

    public void g() {
        try {
            if (this.b != null) {
                this.b.close();
            }
        } catch (IOException unused) {
        }
        this.b = null;
    }
}
