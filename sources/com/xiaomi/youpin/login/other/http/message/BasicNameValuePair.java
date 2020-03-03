package com.xiaomi.youpin.login.other.http.message;

import com.xiaomi.youpin.login.other.http.NameValuePair;
import com.xiaomi.youpin.login.other.http.util.CharArrayBuffer;
import com.xiaomi.youpin.login.other.http.util.LangUtils;

public class BasicNameValuePair implements NameValuePair, Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private final String f23588a;
    private final String b;

    public BasicNameValuePair(String str, String str2) {
        if (str != null) {
            this.f23588a = str;
            this.b = str2;
            return;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    public String a() {
        return this.f23588a;
    }

    public String b() {
        return this.b;
    }

    public String toString() {
        int length = this.f23588a.length();
        if (this.b != null) {
            length += this.b.length() + 1;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(length);
        charArrayBuffer.a(this.f23588a);
        if (this.b != null) {
            charArrayBuffer.a("=");
            charArrayBuffer.a(this.b);
        }
        return charArrayBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair basicNameValuePair = (BasicNameValuePair) obj;
        if (!this.f23588a.equals(basicNameValuePair.f23588a) || !LangUtils.a((Object) this.b, (Object) basicNameValuePair.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return LangUtils.a(LangUtils.a(17, (Object) this.f23588a), (Object) this.b);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
