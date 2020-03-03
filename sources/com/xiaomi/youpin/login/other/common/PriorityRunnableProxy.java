package com.xiaomi.youpin.login.other.common;

public class PriorityRunnableProxy extends PriorityRunnable {

    /* renamed from: a  reason: collision with root package name */
    private final Runnable f23569a;

    public PriorityRunnableProxy(Runnable runnable, Priority priority) {
        super(priority);
        this.f23569a = runnable;
    }

    public void run() {
        if (this.f23569a != null) {
            this.f23569a.run();
        }
    }
}
