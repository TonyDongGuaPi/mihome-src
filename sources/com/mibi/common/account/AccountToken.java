package com.mibi.common.account;

public final class AccountToken {

    /* renamed from: a  reason: collision with root package name */
    private final String f7441a;
    private final String b;
    private final String c;
    private final String d;

    public AccountToken(String str, String str2, String str3, String str4) {
        this.f7441a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f7441a;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.b;
    }
}
