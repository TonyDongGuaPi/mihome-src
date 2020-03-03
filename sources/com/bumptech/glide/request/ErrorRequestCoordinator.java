package com.bumptech.glide.request;

import android.support.annotation.Nullable;

public final class ErrorRequestCoordinator implements Request, RequestCoordinator {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final RequestCoordinator f5058a;
    private Request b;
    private Request c;

    public ErrorRequestCoordinator(@Nullable RequestCoordinator requestCoordinator) {
        this.f5058a = requestCoordinator;
    }

    public void a(Request request, Request request2) {
        this.b = request;
        this.c = request2;
    }

    public void a() {
        if (!this.b.c()) {
            this.b.a();
        }
    }

    public void b() {
        this.b.b();
        if (this.c.c()) {
            this.c.b();
        }
    }

    public boolean c() {
        return (this.b.g() ? this.c : this.b).c();
    }

    public boolean d() {
        return (this.b.g() ? this.c : this.b).d();
    }

    public boolean e() {
        return (this.b.g() ? this.c : this.b).e();
    }

    public boolean f() {
        return (this.b.g() ? this.c : this.b).f();
    }

    public boolean g() {
        return this.b.g() && this.c.g();
    }

    public void h() {
        this.b.h();
        this.c.h();
    }

    public boolean a(Request request) {
        if (!(request instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator errorRequestCoordinator = (ErrorRequestCoordinator) request;
        if (!this.b.a(errorRequestCoordinator.b) || !this.c.a(errorRequestCoordinator.c)) {
            return false;
        }
        return true;
    }

    public boolean b(Request request) {
        return j() && g(request);
    }

    private boolean j() {
        return this.f5058a == null || this.f5058a.b(this);
    }

    public boolean c(Request request) {
        return l() && g(request);
    }

    public boolean d(Request request) {
        return k() && g(request);
    }

    private boolean k() {
        return this.f5058a == null || this.f5058a.d(this);
    }

    private boolean l() {
        return this.f5058a == null || this.f5058a.c(this);
    }

    private boolean g(Request request) {
        return request.equals(this.b) || (this.b.g() && request.equals(this.c));
    }

    public boolean i() {
        return m() || e();
    }

    private boolean m() {
        return this.f5058a != null && this.f5058a.i();
    }

    public void e(Request request) {
        if (this.f5058a != null) {
            this.f5058a.e(this);
        }
    }

    public void f(Request request) {
        if (!request.equals(this.c)) {
            if (!this.c.c()) {
                this.c.a();
            }
        } else if (this.f5058a != null) {
            this.f5058a.f(this);
        }
    }
}
