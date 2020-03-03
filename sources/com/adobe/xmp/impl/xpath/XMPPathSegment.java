package com.adobe.xmp.impl.xpath;

public class XMPPathSegment {

    /* renamed from: a  reason: collision with root package name */
    private String f702a;
    private int b;
    private boolean c;
    private int d;

    public XMPPathSegment(String str) {
        this.f702a = str;
    }

    public XMPPathSegment(String str, int i) {
        this.f702a = str;
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(String str) {
        this.f702a = str;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public String b() {
        return this.f702a;
    }

    public void b(int i) {
        this.d = i;
    }

    public boolean c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public String toString() {
        switch (this.b) {
            case 1:
            case 2:
            case 3:
            case 4:
                return this.f702a;
            case 5:
            case 6:
                return this.f702a;
            default:
                return this.f702a;
        }
    }
}
