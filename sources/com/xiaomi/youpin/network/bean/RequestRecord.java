package com.xiaomi.youpin.network.bean;

public class RequestRecord {

    /* renamed from: a  reason: collision with root package name */
    private boolean f23661a = false;
    private String b = "";

    public synchronized boolean a() {
        return this.f23661a;
    }

    public synchronized void a(boolean z) {
        this.f23661a = z;
    }

    public synchronized String b() {
        return this.b;
    }

    public synchronized void a(String str) {
        this.b = str;
    }
}
