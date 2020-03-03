package com.bumptech.glide.request;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

public class ThumbnailRequestCoordinator implements Request, RequestCoordinator {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final RequestCoordinator f5062a;
    private Request b;
    private Request c;
    private boolean d;

    @VisibleForTesting
    ThumbnailRequestCoordinator() {
        this((RequestCoordinator) null);
    }

    public ThumbnailRequestCoordinator(@Nullable RequestCoordinator requestCoordinator) {
        this.f5062a = requestCoordinator;
    }

    public void a(Request request, Request request2) {
        this.b = request;
        this.c = request2;
    }

    public boolean b(Request request) {
        return j() && (request.equals(this.b) || !this.b.e());
    }

    private boolean j() {
        return this.f5062a == null || this.f5062a.b(this);
    }

    public boolean c(Request request) {
        return l() && request.equals(this.b) && !i();
    }

    public boolean d(Request request) {
        return k() && request.equals(this.b);
    }

    private boolean k() {
        return this.f5062a == null || this.f5062a.d(this);
    }

    private boolean l() {
        return this.f5062a == null || this.f5062a.c(this);
    }

    public boolean i() {
        return m() || e();
    }

    public void e(Request request) {
        if (!request.equals(this.c)) {
            if (this.f5062a != null) {
                this.f5062a.e(this);
            }
            if (!this.c.d()) {
                this.c.b();
            }
        }
    }

    public void f(Request request) {
        if (request.equals(this.b) && this.f5062a != null) {
            this.f5062a.f(this);
        }
    }

    private boolean m() {
        return this.f5062a != null && this.f5062a.i();
    }

    public void a() {
        this.d = true;
        if (!this.b.d() && !this.c.c()) {
            this.c.a();
        }
        if (this.d && !this.b.c()) {
            this.b.a();
        }
    }

    public void b() {
        this.d = false;
        this.c.b();
        this.b.b();
    }

    public boolean c() {
        return this.b.c();
    }

    public boolean d() {
        return this.b.d() || this.c.d();
    }

    public boolean e() {
        return this.b.e() || this.c.e();
    }

    public boolean f() {
        return this.b.f();
    }

    public boolean g() {
        return this.b.g();
    }

    public void h() {
        this.b.h();
        this.c.h();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.bumptech.glide.request.Request r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.bumptech.glide.request.ThumbnailRequestCoordinator
            r1 = 0
            if (r0 == 0) goto L_0x002f
            com.bumptech.glide.request.ThumbnailRequestCoordinator r4 = (com.bumptech.glide.request.ThumbnailRequestCoordinator) r4
            com.bumptech.glide.request.Request r0 = r3.b
            if (r0 != 0) goto L_0x0010
            com.bumptech.glide.request.Request r0 = r4.b
            if (r0 != 0) goto L_0x002e
            goto L_0x001a
        L_0x0010:
            com.bumptech.glide.request.Request r0 = r3.b
            com.bumptech.glide.request.Request r2 = r4.b
            boolean r0 = r0.a(r2)
            if (r0 == 0) goto L_0x002e
        L_0x001a:
            com.bumptech.glide.request.Request r0 = r3.c
            if (r0 != 0) goto L_0x0023
            com.bumptech.glide.request.Request r4 = r4.c
            if (r4 != 0) goto L_0x002e
            goto L_0x002d
        L_0x0023:
            com.bumptech.glide.request.Request r0 = r3.c
            com.bumptech.glide.request.Request r4 = r4.c
            boolean r4 = r0.a(r4)
            if (r4 == 0) goto L_0x002e
        L_0x002d:
            r1 = 1
        L_0x002e:
            return r1
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.ThumbnailRequestCoordinator.a(com.bumptech.glide.request.Request):boolean");
    }
}
