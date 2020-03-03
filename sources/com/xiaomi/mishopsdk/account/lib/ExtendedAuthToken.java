package com.xiaomi.mishopsdk.account.lib;

import android.text.TextUtils;

public final class ExtendedAuthToken {
    public static final String SP = ",";
    public final String authToken;
    public final String security;

    private ExtendedAuthToken(String str, String str2) {
        this.authToken = str;
        this.security = str2;
    }

    public static ExtendedAuthToken build(String str, String str2) {
        return new ExtendedAuthToken(str, str2);
    }

    public static ExtendedAuthToken parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        if (split.length != 2 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return new ExtendedAuthToken(split[0], split[1]);
    }

    public String toPlain() {
        return this.authToken + "," + this.security;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtendedAuthToken extendedAuthToken = (ExtendedAuthToken) obj;
        if (this.authToken == null ? extendedAuthToken.authToken == null : this.authToken.equals(extendedAuthToken.authToken)) {
            return this.security == null ? extendedAuthToken.security == null : this.security.equals(extendedAuthToken.security);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.authToken != null ? this.authToken.hashCode() : 0) * 31;
        if (this.security != null) {
            i = this.security.hashCode();
        }
        return hashCode + i;
    }
}
