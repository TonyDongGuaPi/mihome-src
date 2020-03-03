package org.aspectj.runtime.internal.cflowstack;

import java.util.Stack;

public class ThreadStackFactoryImpl implements ThreadStackFactory {

    /* renamed from: org.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl$1  reason: invalid class name */
    class AnonymousClass1 {
    }

    private static class ThreadStackImpl extends ThreadLocal implements ThreadStack {
        private ThreadStackImpl() {
        }

        ThreadStackImpl(AnonymousClass1 r1) {
            this();
        }

        public Object initialValue() {
            return new Stack();
        }

        public Stack a() {
            return (Stack) get();
        }

        public void b() {
            remove();
        }
    }

    public ThreadStack a() {
        return new ThreadStackImpl((AnonymousClass1) null);
    }

    private static class ThreadCounterImpl extends ThreadLocal implements ThreadCounter {
        private ThreadCounterImpl() {
        }

        ThreadCounterImpl(AnonymousClass1 r1) {
            this();
        }

        public Object initialValue() {
            return new Counter();
        }

        public Counter e() {
            return (Counter) get();
        }

        public void d() {
            remove();
        }

        public void a() {
            e().f3463a++;
        }

        public void b() {
            Counter e = e();
            e.f3463a--;
        }

        public boolean c() {
            return e().f3463a != 0;
        }

        static class Counter {

            /* renamed from: a  reason: collision with root package name */
            protected int f3463a = 0;

            Counter() {
            }
        }
    }

    public ThreadCounter b() {
        return new ThreadCounterImpl((AnonymousClass1) null);
    }
}
