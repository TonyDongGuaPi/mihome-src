package com.lidroid.xutils.task;

public class PriorityRunnable extends PriorityObject<Runnable> implements Runnable {
    public PriorityRunnable(Priority priority, Runnable runnable) {
        super(priority, runnable);
    }

    public void run() {
        ((Runnable) this.b).run();
    }
}
