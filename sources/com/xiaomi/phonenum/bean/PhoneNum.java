package com.xiaomi.phonenum.bean;

import android.os.Bundle;
import com.xiaomi.account.openauth.AuthorizeActivityBase;

public class PhoneNum {

    /* renamed from: a  reason: collision with root package name */
    public final int f12553a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final String h;
    public final String i;
    public final String j;
    public final int k;
    public final String l;
    public final int m;

    private PhoneNum(Builder builder) {
        this.f12553a = builder.f12554a;
        this.c = builder.c;
        this.e = builder.d;
        this.f = builder.e;
        this.b = builder.b;
        this.g = builder.f;
        this.h = builder.g;
        this.d = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", this.f12553a);
        bundle.putString("errorMsg", this.b);
        bundle.putString("number", this.c);
        bundle.putString("numberHash", this.d);
        bundle.putString("iccid", this.e);
        bundle.putString("token", this.f);
        bundle.putBoolean("isVerified", this.g);
        bundle.putString("updateTime", this.h);
        bundle.putString("copywriter", this.i);
        bundle.putString(AuthorizeActivityBase.KEY_OPERATORLINK, this.j);
        bundle.putString("traceId", this.l);
        bundle.putInt("subId", this.k);
        bundle.putInt("phoneLevel", this.m);
        return bundle;
    }

    public String toString() {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", this.f12553a);
        bundle.putString("errorMsg", this.b);
        bundle.putString("number", this.c);
        bundle.putString("traceId", this.l);
        bundle.putInt("subId", this.k);
        return bundle.toString();
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f12554a = Error.NONE.code;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public boolean f = false;
        /* access modifiers changed from: private */
        public String g = String.valueOf(System.currentTimeMillis());
        /* access modifiers changed from: private */
        public String h;
        /* access modifiers changed from: private */
        public String i;
        /* access modifiers changed from: private */
        public String j;
        /* access modifiers changed from: private */
        public int k = -1;
        /* access modifiers changed from: private */
        public String l;
        /* access modifiers changed from: private */
        public int m;

        public Builder a(Bundle bundle) {
            this.f12554a = bundle.getInt("errorCode");
            this.b = bundle.getString("errorMsg");
            this.c = bundle.getString("number");
            this.h = bundle.getString("numberHash");
            this.d = bundle.getString("iccid");
            this.e = bundle.getString("token");
            this.f = bundle.getBoolean("isVerified");
            this.g = bundle.getString("updateTime");
            this.i = bundle.getString("copywriter");
            this.j = bundle.getString(AuthorizeActivityBase.KEY_OPERATORLINK);
            this.l = bundle.getString("traceId");
            this.k = bundle.getInt("subId");
            this.m = bundle.getInt("phoneLevel");
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder b(String str) {
            this.h = str;
            return this;
        }

        public Builder c(String str) {
            this.b = str;
            return this;
        }

        public Builder d(String str) {
            this.e = str;
            return this;
        }

        public Builder e(String str) {
            this.d = str;
            return this;
        }

        public Builder a(int i2) {
            this.f12554a = i2;
            return this;
        }

        public Builder a(boolean z) {
            this.f = z;
            return this;
        }

        public Builder f(String str) {
            this.g = str;
            return this;
        }

        public Builder g(String str) {
            this.i = str;
            return this;
        }

        public Builder h(String str) {
            this.j = str;
            return this;
        }

        public Builder i(String str) {
            this.l = str;
            return this;
        }

        public Builder b(int i2) {
            this.k = i2;
            return this;
        }

        public Builder c(int i2) {
            this.m = i2;
            return this;
        }

        public PhoneNum a() {
            if (this.b == null) {
                this.b = "" + Error.codeToError(this.f12554a);
            } else {
                this.b = "" + Error.codeToError(this.f12554a) + " : " + this.b;
            }
            return new PhoneNum(this);
        }
    }
}
