package com.xiaomi.stat;

import java.lang.Thread;

public class al implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private e f23021a;
    private Thread.UncaughtExceptionHandler b;
    private boolean c = true;

    public al(e eVar) {
        this.f23021a = eVar;
    }

    public void a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof al)) {
            this.b = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (this.c) {
            this.f23021a.a(th, (String) null, false);
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
