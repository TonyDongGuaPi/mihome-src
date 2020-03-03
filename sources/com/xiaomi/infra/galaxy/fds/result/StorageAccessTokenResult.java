package com.xiaomi.infra.galaxy.fds.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StorageAccessTokenResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1375a;
    private long b;

    public StorageAccessTokenResult() {
    }

    public StorageAccessTokenResult(String str, long j) {
        this.f1375a = str;
        this.b = j;
    }

    public String a() {
        return this.f1375a;
    }

    public void a(String str) {
        this.f1375a = str;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }
}
