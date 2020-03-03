package com.amap.openapi;

import android.content.Context;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile b f4619a;
    private c b = new c();
    private volatile boolean c = false;

    private b() {
    }

    public static b a() {
        if (f4619a == null) {
            synchronized (b.class) {
                if (f4619a == null) {
                    f4619a = new b();
                }
            }
        }
        return f4619a;
    }

    public synchronized void a(Context context, d dVar) {
        if (!this.c) {
            this.b.a(context, dVar);
            this.c = true;
        }
    }

    public void a(f fVar) {
        this.b.a(fVar);
    }

    public synchronized void b() {
        if (this.c) {
            this.b.a();
            this.c = false;
        }
    }

    public void b(f fVar) {
        this.b.b(fVar);
    }
}
