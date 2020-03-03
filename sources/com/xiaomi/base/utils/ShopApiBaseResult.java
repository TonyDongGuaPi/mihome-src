package com.xiaomi.base.utils;

import com.taobao.weex.el.parse.Operators;

public class ShopApiBaseResult {

    /* renamed from: a  reason: collision with root package name */
    public String f10038a;
    public long b;
    public String c;
    public String d;
    public StringBuilder e;

    public ShopApiBaseResult(Builder builder) {
        this.d = builder.d;
        this.b = builder.b;
        this.c = builder.c;
        this.f10038a = builder.f10039a;
        this.e = builder.e;
    }

    public boolean a(boolean z) {
        return this.b == 0 && (z || (this.e != null && this.e.length() > 0));
    }

    public String toString() {
        return "ShopApiBaseResult{mCloseLink='" + this.f10038a + Operators.SINGLE_QUOTE + ", mDescription='" + this.c + Operators.SINGLE_QUOTE + ", mInfo='" + this.d + Operators.SINGLE_QUOTE + ", mCode=" + this.b + ",mJsonData=" + this.e + Operators.BLOCK_END;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f10039a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public StringBuilder e;

        public ShopApiBaseResult a() {
            return new ShopApiBaseResult(this);
        }

        public Builder a(String str) {
            this.f10039a = str;
            return this;
        }

        public Builder a(long j) {
            this.b = j;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder a(StringBuilder sb) {
            this.e = sb;
            return this;
        }
    }
}
