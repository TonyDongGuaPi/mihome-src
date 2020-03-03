package com.xiaomi.youpin.common.thread;

public class PriorityRunnableProxy extends PriorityRunnable {

    /* renamed from: a  reason: collision with root package name */
    private final Runnable f23241a;

    public PriorityRunnableProxy(Runnable runnable, Priority priority) {
        super(priority);
        this.f23241a = runnable;
    }

    public void run() {
        if (this.f23241a != null) {
            this.f23241a.run();
        }
    }
}
