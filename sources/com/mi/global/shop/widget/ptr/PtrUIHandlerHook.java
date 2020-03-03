package com.mi.global.shop.widget.ptr;

public abstract class PtrUIHandlerHook implements Runnable {
    private static final byte b = 0;
    private static final byte c = 1;
    private static final byte d = 2;

    /* renamed from: a  reason: collision with root package name */
    private Runnable f7253a;
    private byte e = 0;

    public void a() {
        a((Runnable) null);
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            this.f7253a = runnable;
        }
        switch (this.e) {
            case 0:
                this.e = 1;
                run();
                return;
            case 2:
                c();
                return;
            default:
                return;
        }
    }

    public void b() {
        this.e = 0;
    }

    public void c() {
        if (this.f7253a != null) {
            this.f7253a.run();
        }
        this.e = 2;
    }

    public void b(Runnable runnable) {
        this.f7253a = runnable;
    }
}
