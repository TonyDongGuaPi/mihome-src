package org.mp4parser.aspectj.runtime.internal.cflowstack;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

public class ThreadStackImpl11 implements ThreadStack {
    private static final int e = 20000;
    private static final int f = 100;

    /* renamed from: a  reason: collision with root package name */
    private Hashtable f3767a = new Hashtable();
    private Thread b;
    private Stack c;
    private int d = 0;

    public void b() {
    }

    public synchronized Stack a() {
        if (Thread.currentThread() != this.b) {
            this.b = Thread.currentThread();
            this.c = (Stack) this.f3767a.get(this.b);
            if (this.c == null) {
                this.c = new Stack();
                this.f3767a.put(this.b, this.c);
            }
            this.d++;
            if (this.d > Math.max(100, 20000 / Math.max(1, this.f3767a.size()))) {
                Stack stack = new Stack();
                Enumeration keys = this.f3767a.keys();
                while (keys.hasMoreElements()) {
                    Thread thread = (Thread) keys.nextElement();
                    if (!thread.isAlive()) {
                        stack.push(thread);
                    }
                }
                Enumeration elements = stack.elements();
                while (elements.hasMoreElements()) {
                    this.f3767a.remove((Thread) elements.nextElement());
                }
                this.d = 0;
            }
        }
        return this.c;
    }
}
