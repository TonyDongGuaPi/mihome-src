package miuipub.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import miuipub.util.concurrent.Queue;

public class ConcurrentRingQueue<T> implements Queue<T> {

    /* renamed from: a  reason: collision with root package name */
    private int f3054a;
    private final boolean b;
    private final boolean c;
    private final AtomicInteger d = new AtomicInteger(0);
    private volatile Node<T> e = new Node<>();
    private final AtomicInteger f = new AtomicInteger(0);
    private volatile Node<T> g = this.e;
    private volatile int h;

    private static class Node<T> {

        /* renamed from: a  reason: collision with root package name */
        T f3055a;
        Node<T> b;

        private Node() {
        }
    }

    public ConcurrentRingQueue(int i, boolean z, boolean z2) {
        this.f3054a = i;
        this.b = z;
        this.c = z2;
        Node<T> node = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            node.b = new Node<>();
            node = node.b;
        }
        node.b = this.e;
    }

    public boolean b(T t) {
        if (t == null) {
            return false;
        }
        while (true) {
            if (this.f.get() == 0 && this.f.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.e;
        Node<T> node2 = this.g;
        int i = this.h;
        boolean z = true;
        if (node2.b != node) {
            node2.f3055a = t;
            if (node2.b.b != node && this.c && i > 0) {
                node2.b = node2.b.b;
                this.h = i - 1;
            }
            this.g = node2.b;
        } else if (this.b || i < 0) {
            node2.b = new Node<>();
            node2.b.b = node;
            node2.f3055a = t;
            this.h = i + 1;
            this.g = node2.b;
        } else {
            z = false;
        }
        this.f.set(0);
        return z;
    }

    public T g() {
        while (true) {
            if (this.d.get() == 0 && this.d.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.e;
        Node<T> node2 = this.g;
        Node<T> node3 = node;
        T t = null;
        while (t == null && node3 != node2) {
            t = node3.f3055a;
            node3.f3055a = null;
            node3 = node3.b;
            node2 = this.g;
        }
        if (t != null) {
            this.e = node3;
        }
        this.d.set(0);
        return t;
    }

    public boolean a(T t) {
        boolean z;
        if (t == null) {
            return false;
        }
        while (true) {
            if (this.d.get() == 0 && this.d.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.e;
        while (true) {
            if (node == this.g) {
                z = false;
                break;
            } else if (t.equals(node.f3055a)) {
                node.f3055a = null;
                z = true;
                break;
            } else {
                node = node.b;
            }
        }
        this.d.set(0);
        return z;
    }

    public int a(Queue.Predicate<T> predicate) {
        if (predicate == null) {
            return 0;
        }
        while (true) {
            if (this.d.get() == 0 && this.d.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        int i = 0;
        for (Node<T> node = this.e; node != this.g; node = node.b) {
            if (predicate.a(node.f3055a)) {
                node.f3055a = null;
                i++;
            }
        }
        this.d.set(0);
        return i;
    }

    public int b() {
        while (true) {
            if (this.d.get() == 0 && this.d.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.e;
        int i = 0;
        while (node != this.g) {
            node.f3055a = null;
            i++;
            node = node.b;
        }
        this.e = node;
        this.d.set(0);
        return i;
    }

    public boolean c() {
        return this.g == this.e;
    }

    public int d() {
        int i = this.h;
        return i > 0 ? this.f3054a + i : this.f3054a;
    }

    public void a(int i) {
        if (!this.b && i > 0) {
            while (true) {
                if (this.f.get() != 0 || !this.f.compareAndSet(0, -1)) {
                    Thread.yield();
                } else {
                    this.h = -i;
                    this.f3054a += i;
                    this.f.set(0);
                    return;
                }
            }
        }
    }

    public void b(int i) {
        if (this.c && i > 0) {
            while (true) {
                if (this.f.get() != 0 || !this.f.compareAndSet(0, -1)) {
                    Thread.yield();
                } else {
                    this.f3054a -= i;
                    this.h = i;
                    this.f.set(0);
                    return;
                }
            }
        }
    }
}
