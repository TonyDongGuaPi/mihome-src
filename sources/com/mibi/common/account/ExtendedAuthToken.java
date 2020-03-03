package com.mibi.common.account;

import android.text.TextUtils;

public class ExtendedAuthToken {
    private static final String c = ",";

    /* renamed from: a  reason: collision with root package name */
    public final String f7443a;
    public final String b;

    private ExtendedAuthToken(String str, String str2) {
        this.f7443a = str;
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
        if (split.length != 2 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return new ExtendedAuthToken(split[0], split[1]);
    }

    public String a() {
        return this.f7443a + "," + this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtendedAuthToken extendedAuthToken = (ExtendedAuthToken) obj;
        if (this.f7443a != null) {
            if (!this.f7443a.equals(extendedAuthToken.f7443a)) {
                return false;
            }
        } else if (extendedAuthToken.f7443a != null) {
            return false;
        }
        if (this.b != null) {
            if (this.b.equals(extendedAuthToken.b)) {
                return true;
            }
        } else if (extendedAuthToken.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f7443a != null ? this.f7443a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }
}
