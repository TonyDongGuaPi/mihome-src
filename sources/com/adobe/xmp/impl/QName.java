package com.adobe.xmp.impl;

public class QName {

    /* renamed from: a  reason: collision with root package name */
    private String f687a;
    private String b;

    public QName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.f687a = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            this.f687a = "";
        }
        this.b = str;
    }

    public QName(String str, String str2) {
        this.f687a = str;
        this.b = str2;
    }

    public boolean a() {
        return this.f687a != null && this.f687a.length() > 0;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.f687a;
    }
}
