package com.xiaomi.youpin.cookie;

import android.content.Context;

public class CookieConfig {

    /* renamed from: a  reason: collision with root package name */
    private Context f23303a;

    private CookieConfig(Builder builder) {
        this.f23303a = builder.f23304a;
    }

    public Context a() {
        return this.f23303a;
    }

    public void a(Context context) {
        this.f23303a = context;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Context f23304a;

        public Builder(Context context) {
            this.f23304a = context.getApplicationContext();
        }

        public CookieConfig a() {
            return new CookieConfig(this);
        }
    }
}
