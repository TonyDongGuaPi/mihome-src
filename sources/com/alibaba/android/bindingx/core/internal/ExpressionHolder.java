package com.alibaba.android.bindingx.core.internal;

import java.util.Collections;
import java.util.Map;

final class ExpressionHolder {

    /* renamed from: a  reason: collision with root package name */
    String f756a;
    String b;
    ExpressionPair c;
    String d;
    String e;
    Map<String, Object> f;

    ExpressionHolder(String str, String str2, ExpressionPair expressionPair, String str3, String str4, Map<String, Object> map) {
        this.f756a = str;
        this.b = str2;
        this.c = expressionPair;
        this.d = str3;
        this.e = str4;
        if (map == null) {
            this.f = Collections.emptyMap();
        } else {
            this.f = Collections.unmodifiableMap(map);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExpressionHolder expressionHolder = (ExpressionHolder) obj;
        if (this.f756a == null ? expressionHolder.f756a != null : !this.f756a.equals(expressionHolder.f756a)) {
            return false;
        }
        if (this.c == null ? expressionHolder.c != null : !this.c.equals(expressionHolder.c)) {
            return false;
        }
        if (this.d == null ? expressionHolder.d != null : !this.d.equals(expressionHolder.d)) {
            return false;
        }
        if (this.e == null ? expressionHolder.e != null : !this.e.equals(expressionHolder.e)) {
            return false;
        }
        if (this.f != null) {
            return this.f.equals(expressionHolder.f);
        }
        if (expressionHolder.f == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((this.f756a != null ? this.f756a.hashCode() : 0) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }
}
