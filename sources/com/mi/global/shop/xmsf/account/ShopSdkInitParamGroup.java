package com.mi.global.shop.xmsf.account;

import android.support.annotation.NonNull;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;

public class ShopSdkInitParamGroup {
    public static final String g = "community_sdk";
    public static final String h = "mihome_sdk";

    /* renamed from: a  reason: collision with root package name */
    public String f7310a;
    public String b;
    public ExtendedAuthToken c;
    public String d;
    public boolean e;
    public String f;

    private ShopSdkInitParamGroup(Builder builder) {
        this.e = false;
        this.f7310a = builder.b;
        this.b = builder.c;
        this.c = builder.d;
        this.d = builder.e;
        this.f = builder.f;
        this.e = builder.f7311a;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7311a = false;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public ExtendedAuthToken d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public String f;

        public Builder(@NonNull String str, @NonNull String str2) {
            this.f = str;
            this.e = str2;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder a(boolean z) {
            this.f7311a = z;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder a(ExtendedAuthToken extendedAuthToken) {
            this.d = extendedAuthToken;
            return this;
        }

        public Builder c(String str) {
            this.e = str;
            return this;
        }

        public Builder d(String str) {
            this.f = str;
            return this;
        }

        public ShopSdkInitParamGroup a() {
            return new ShopSdkInitParamGroup(this);
        }
    }
}
