package org.xutils.common.task;

class PriorityRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    long f4218a;
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
