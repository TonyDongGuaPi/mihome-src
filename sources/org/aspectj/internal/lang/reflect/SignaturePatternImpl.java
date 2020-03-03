package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.SignaturePattern;

public class SignaturePatternImpl implements SignaturePattern {

    /* renamed from: a  reason: collision with root package name */
    private String f3449a;

    public SignaturePatternImpl(String str) {
        this.f3449a = str;
    }

    public String a() {
        return this.f3449a;
    }

    public String toString() {
        return a();
    }
}
