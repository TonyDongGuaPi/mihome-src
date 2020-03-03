package miuipub.util.concurrent;

import java.util.Iterator;
import miuipub.util.concurrent.Queue;

public class ConcurrentLinkedQueue<T> implements Queue<T> {

    /* renamed from: a  reason: collision with root package name */
    private final java.util.concurrent.ConcurrentLinkedQueue<T> f3053a = new java.util.concurrent.ConcurrentLinkedQueue<>();

    public int d() {
        return -1;
    }

    public boolean b(T t) {
        return this.f3053a.offer(t);
    }

    public T g() {
        return this.f3053a.poll();
    }

    public boolean a(T t) {
        return this.f3053a.remove(t);
    }

    public int a(Queue.Predicate<T> predicate) {
        Iterator<T> it = this.f3053a.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (predicate.a(it.next())) {
                it.remove();
                i++;
            }
        }
        return i;
    }

    public int b() {
        int size = this.f3053a.size();
        this.f3053a.clear();
        return size;
    }

    public boolean c() {
        return this.f3053a.isEmpty();
    }
}
