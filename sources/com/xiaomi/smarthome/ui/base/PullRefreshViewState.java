package com.xiaomi.smarthome.ui.base;

public class PullRefreshViewState implements ViewState {
    boolean c;
    Error d;
    boolean e;
    Error f;

    public boolean c() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public Error d() {
        return this.d;
    }

    public void a(Error error) {
        this.d = error;
    }

    public boolean e() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public Error f() {
        return this.f;
    }

    public void b(Error error) {
        this.f = error;
    }
}
