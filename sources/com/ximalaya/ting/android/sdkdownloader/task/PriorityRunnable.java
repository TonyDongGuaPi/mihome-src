package com.ximalaya.ting.android.sdkdownloader.task;

class PriorityRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    long f2370a;
    public final Priority b;
    private final Runnable c;

    public PriorityRunnable(Priority priority, Runnable runnable) {
        this.b = priority == null ? Priority.DEFAULT : priority;
        this.c = runnable;
    }

    public final void run() {
        this.c.run();
    }
}
