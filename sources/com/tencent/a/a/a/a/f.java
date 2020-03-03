package com.tencent.a.a.a.a;

import android.content.Context;

public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    protected Context f8975a = null;

    protected f(Context context) {
        this.f8975a = context;
    }

    public final void a(c cVar) {
        if (cVar != null) {
            String cVar2 = cVar.toString();
            if (a()) {
                a(h.d(cVar2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public abstract boolean a();

    /* access modifiers changed from: protected */
    public abstract String b();

    public final c c() {
        String c = a() ? h.c(b()) : null;
        if (c != null) {
            return c.a(c);
        }
        return null;
    }
}
