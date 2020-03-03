package com.xiaomi.infra.galaxy.fds.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UploadPartResult {

    /* renamed from: a  reason: collision with root package name */
    private int f1376a;
    private String b;
    private long c;

    public UploadPartResult() {
    }

    public UploadPartResult(int i, long j, String str) {
        this.f1376a = i;
        this.b = str;
        this.c = j;
    }

    public int a() {
        return this.f1376a;
    }

    public void a(int i) {
        this.f1376a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }
}
