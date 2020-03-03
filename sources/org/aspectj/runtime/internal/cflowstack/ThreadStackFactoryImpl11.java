package org.aspectj.runtime.internal.cflowstack;

public class ThreadStackFactoryImpl11 implements ThreadStackFactory {
    public ThreadStack a() {
        return new ThreadStackImpl11();
    }

    public ThreadCounter b() {
        return new ThreadCounterImpl11();
    }
}
