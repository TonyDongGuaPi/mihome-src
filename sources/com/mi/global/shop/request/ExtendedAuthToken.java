package com.mi.global.shop.request;

import android.text.TextUtils;

public class ExtendedAuthToken {
    private static final String c = ",";

    /* renamed from: a  reason: collision with root package name */
    public final String f6932a;
    public final String b;

    private ExtendedAuthToken(String str, String str2) {
        this.f6932a = str;
        this.b = str2;
    }

    public static ExtendedAuthToken a(String str, String str2) {
        return new ExtendedAuthToken(str, str2);
    }

    public static ExtendedAuthToken a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        if (split.length != 2) {
            return null;
        }
        return new ExtendedAuthToken(split[0], split[1]);
    }

    public String a() {
        return this.f6932a + "," + this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtendedAuthToken extendedAuthToken = (ExtendedAuthToken) obj;
        if (this.f6932a == null ? extendedAuthToken.f6932a == null : this.f6932a.equals(extendedAuthToken.f6932a)) {
            return this.b == null ? extendedAuthToken.b == null : this.b.equals(extendedAuthToken.b);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f6932a != null ? this.f6932a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }
}
