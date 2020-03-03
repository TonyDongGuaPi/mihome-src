package com.xiaomi.mimc.data;

public class ConnectionContent {

    /* renamed from: a  reason: collision with root package name */
    private long f11184a;
    private String b;
    private ConnType c;
    private long d;

    public enum ConnType {
        RELAY_CONN,
        P2P_INTRANET_CONN,
        P2P_INTERNET_CONN
    }

    public ConnectionContent(long j, String str, ConnType connType) {
        this.f11184a = j;
        this.b = str;
        this.c = connType;
        this.d = -1;
    }

    public ConnectionContent(long j, String str, ConnType connType, long j2) {
        this.f11184a = j;
        this.b = str;
        this.c = connType;
        this.d = j2;
    }

    public long a() {
        return this.f11184a;
    }

    public void a(long j) {
        this.f11184a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public ConnType c() {
        return this.c;
    }

    public void a(ConnType connType) {
        this.c = connType;
    }

    public long d() {
        return this.d;
    }

    public void b(long j) {
        this.d = j;
    }
}
