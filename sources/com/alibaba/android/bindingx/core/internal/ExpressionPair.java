package com.alibaba.android.bindingx.core.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class ExpressionPair {

    /* renamed from: a  reason: collision with root package name */
    public final String f757a;
    public final String b;

    public ExpressionPair(String str, String str2) {
        this.f757a = str;
        this.b = str2;
    }

    public static ExpressionPair a(@Nullable String str, @Nullable String str2) {
        return new ExpressionPair(str, str2);
    }

    public static boolean a(@Nullable ExpressionPair expressionPair) {
        return expressionPair != null && !TextUtils.isEmpty(expressionPair.b) && !"{}".equals(expressionPair.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExpressionPair expressionPair = (ExpressionPair) obj;
        if (this.f757a == null ? expressionPair.f757a != null : !this.f757a.equals(expressionPair.f757a)) {
            return false;
        }
        if (this.b != null) {
            return this.b.equals(expressionPair.b);
        }
        if (expressionPair.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f757a != null ? this.f757a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }
}
