package com.ximalaya.ting.android.opensdk.auth.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

public abstract class d {
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final String f = "key_url";
    public static final String g = "key_specify_title";
    protected Context h;
    protected String i;
    protected String j;

    public abstract void a(Activity activity);

    /* access modifiers changed from: protected */
    public abstract void a(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void b(Bundle bundle);

    public d(Context context) {
        this.h = context.getApplicationContext();
    }

    public final void c(Bundle bundle) {
        this.i = bundle.getString(f);
        this.j = bundle.getString(g);
        a(bundle);
    }

    public final Bundle d() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.i)) {
            bundle.putString(f, this.i);
        }
        if (!TextUtils.isEmpty(this.j)) {
            bundle.putString(g, this.j);
        }
        b(bundle);
        return bundle;
    }

    public final void a(String str) {
        this.i = str;
    }

    public final String e() {
        return this.i;
    }

    public final void b(String str) {
        this.j = str;
    }

    public final String f() {
        return this.j;
    }
}
