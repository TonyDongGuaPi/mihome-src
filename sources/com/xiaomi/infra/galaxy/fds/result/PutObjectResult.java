package com.xiaomi.infra.galaxy.fds.result;

import com.alipay.sdk.sys.a;
import com.xiaomi.infra.galaxy.fds.Common;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PutObjectResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1373a;
    private String b;
    private String c;
    private String d;
    private long e;

    public String a() {
        return this.f1373a;
    }

    public void a(String str) {
        this.f1373a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public String f() throws URISyntaxException {
        return new URI((String) null, (String) null, (String) null, -1, "/" + this.f1373a + "/" + this.b, "GalaxyAccessKeyId=" + this.c + a.b + "Expires" + "=" + this.e + a.b + "Signature" + "=" + this.d, (String) null).toString();
    }

    private static String g(String str) {
        return (str == null || str.isEmpty() || str.charAt(str.length() + -1) != '/') ? str : str.substring(0, str.length() - 1);
    }

    public String g() throws URISyntaxException {
        return e(Common.A);
    }

    public String e(String str) throws URISyntaxException {
        return g(str) + f();
    }

    public String h() throws URISyntaxException {
        return f(Common.B);
    }

    public String f(String str) throws URISyntaxException {
        return g(str) + f();
    }
}
