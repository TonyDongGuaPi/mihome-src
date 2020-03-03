package com.xiaomi.youpin.share.config;

import android.content.Context;
import com.tencent.mm.opensdk.openapi.IWXAPI;

public class ShareConfig {

    /* renamed from: a  reason: collision with root package name */
    private Context f1587a;
    private boolean b;
    private boolean c;
    private String d;
    private String e;
    private IWXAPI f;
    private ShareDependency g;

    private ShareConfig(Builder builder) {
        this.f1587a = builder.f1588a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
    }

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public IWXAPI b() {
        return this.f;
    }

    public void a(IWXAPI iwxapi) {
        this.f = iwxapi;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public ShareDependency d() {
        return this.g;
    }

    public void a(ShareDependency shareDependency) {
        this.g = shareDependency;
    }

    public Context e() {
        return this.f1587a;
    }

    public void a(Context context) {
        this.f1587a = context;
    }

    public boolean f() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean g() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Context f1588a;
        boolean b = true;
        boolean c = true;
        String d;
        String e;
        IWXAPI f;
        ShareDependency g;

        public Builder(Context context) {
            this.f1588a = context.getApplicationContext();
        }

        public Builder a(boolean z) {
            this.b = z;
            return this;
        }

        public Builder b(boolean z) {
            this.c = z;
            return this;
        }

        public Builder a(String str) {
            this.d = str;
            return this;
        }

        public Builder b(String str) {
            this.e = str;
            return this;
        }

        public Builder a(IWXAPI iwxapi) {
            this.f = iwxapi;
            return this;
        }

        public Builder a(ShareDependency shareDependency) {
            this.g = shareDependency;
            return this;
        }

        public ShareConfig a() {
            return new ShareConfig(this);
        }
    }
}
