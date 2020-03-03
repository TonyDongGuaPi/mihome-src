package com.xiaomi.youpin.login.other.cookie;

import android.content.Context;

public class CookieConfig {

    /* renamed from: a  reason: collision with root package name */
    private Context f23575a;

    private CookieConfig(Builder builder) {
        this.f23575a = builder.f23576a;
    }

    public Context a() {
        return this.f23575a;
    }

    public void a(Context context) {
        this.f23575a = context;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Context f23576a;

        public Builder(Context context) {
            this.f23576a = context.getApplicationContext();
        }

        public CookieConfig a() {
            return new CookieConfig(this);
        }
    }
}
