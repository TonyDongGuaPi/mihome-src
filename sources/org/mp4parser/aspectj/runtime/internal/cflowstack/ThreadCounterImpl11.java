package org.mp4parser.aspectj.runtime.internal.cflowstack;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ThreadCounterImpl11 implements ThreadCounter {
    private static final int e = 20000;
    private static final int f = 100;

    /* renamed from: a  reason: collision with root package name */
    private Hashtable f3764a = new Hashtable();
    private Thread b;
    private Counter c;
    private int d = 0;

    public void d() {
    }

    static class Counter {

        /* renamed from: a  reason: collision with root package name */
        protected int f3765a = 0;

        Counter() {
        }
    }

    private synchronized Counter e() {
        if (Thread.currentThread() != this.b) {
            this.b = Thread.currentThread();
            this.c = (Counter) this.f3764a.get(this.b);
            if (this.c == null) {
                this.c = new Counter();
                this.f3764a.put(this.b, this.c);
            }
            this.d++;
            if (this.d > Math.max(100, 20000 / Math.max(1, this.f3764a.size()))) {
                ArrayList<Thread> arrayList = new ArrayList<>();
                Enumeration keys = this.f3764a.keys();
                while (keys.hasMoreElements()) {
                    Thread thread = (Thread) keys.nextElement();
                    if (!thread.isAlive()) {
                        arrayList.add(thread);
                    }
                }
                for (Thread remove : arrayList) {
                    this.f3764a.remove(remove);
                }
                this.d = 0;
            }
        }
        return this.c;
    }

    public void a() {
        e().f3765a++;
    }

    public void b() {
        Counter e2 = e();
        e2.f3765a--;
    }

    public boolean c() {
        return e().f3765a != 0;
    }
}
