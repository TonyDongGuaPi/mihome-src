package org.greenrobot.eventbus.util;

public class ThrowableFailureEvent implements HasExecutionScope {

    /* renamed from: a  reason: collision with root package name */
    protected final Throwable f3511a;
    protected final boolean b;
    private Object c;

    public ThrowableFailureEvent(Throwable th) {
        this.f3511a = th;
        this.b = false;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.f3511a = th;
        this.b = z;
    }

    public Throwable b() {
        return this.f3511a;
    }

    public boolean c() {
        return this.b;
    }

    public Object a() {
        return this.c;
    }

    public void a(Object obj) {
        this.c = obj;
    }
}
