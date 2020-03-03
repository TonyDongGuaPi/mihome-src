package com.xiaomi.infra.galaxy.fds.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplySecretResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1366a;
    private String b;

    public ApplySecretResult() {
    }

    public ApplySecretResult(String str, String str2) {
        this.f1366a = str;
        this.b = str2;
    }

    public String a() {
        return this.f1366a;
    }

    public void a(String str) {
        this.f1366a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
