package com.xiaomi.infra.galaxy.fds.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InitMultipartUploadResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1367a;
    private String b;
    private String c;

    public String a() {
        return this.f1367a;
    }

    public void a(String str) {
        this.f1367a = str;
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
}
