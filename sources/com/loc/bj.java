package com.loc;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

public abstract class bj {
    int c = 20000;
    int d = 20000;
    Proxy e = null;

    public abstract Map<String, String> a();

    public final void a(int i) {
        this.c = i;
    }

    public final void a(Proxy proxy) {
        this.e = proxy;
    }

    public abstract Map<String, String> b();

    public final void b(int i) {
        this.d = i;
    }

    public abstract String c();

    public byte[] d() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String k() {
        return "";
    }

    /* access modifiers changed from: protected */
    public final boolean l() {
        return !TextUtils.isEmpty(k());
    }

    public boolean m() {
        return false;
    }
}
