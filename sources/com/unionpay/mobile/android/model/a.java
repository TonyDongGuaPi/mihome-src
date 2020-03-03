package com.unionpay.mobile.android.model;

public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private int f9575a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;

    public a() {
        this.f9575a = 2;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    public a(int i, String str, String str2, String str3, int i2) {
        this.f9575a = 2;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.b = i;
        this.f9575a = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    private a(String str, String str2, String str3) {
        this(0, str, str2, str3, 2);
    }

    public a(String str, String str2, String str3, byte b2) {
        this(str, str2, str3);
    }

    public final String a() {
        return this.c;
    }

    public final void a(String str) {
        this.f = str;
    }

    public final String b() {
        return this.e;
    }

    public final int c() {
        return this.b;
    }

    public final int d() {
        return this.f9575a;
    }

    public final String e() {
        if (this.b == 0) {
            return this.d;
        }
        return String.format("<font color=\"#FF0000\">%s</font>", new Object[]{this.f});
    }

    public final String toString() {
        return this.c + "," + this.d + "," + this.e;
    }
}
