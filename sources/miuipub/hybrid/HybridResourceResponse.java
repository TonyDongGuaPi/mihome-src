package miuipub.hybrid;

import java.io.InputStream;

public class HybridResourceResponse {

    /* renamed from: a  reason: collision with root package name */
    private String f2944a;
    private String b;
    private InputStream c;

    public HybridResourceResponse(String str, String str2, InputStream inputStream) {
        this.f2944a = str;
        this.b = str2;
        this.c = inputStream;
    }

    public void a(String str) {
        this.f2944a = str;
    }

    public String a() {
        return this.f2944a;
    }

    public void b(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void a(InputStream inputStream) {
        this.c = inputStream;
    }

    public InputStream c() {
        return this.c;
    }
}
