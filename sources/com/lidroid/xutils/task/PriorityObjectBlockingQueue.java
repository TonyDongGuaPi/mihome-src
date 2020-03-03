package com.lidroid.xutils.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityObjectBlockingQueue<E> extends AbstractQueue<E> implements Serializable, BlockingQueue<E> {
    private static final long serialVersionUID = -6903933977591709194L;

    /* renamed from: a  reason: collision with root package name */
    private transient Node<E> f6364a;
    private final int capacity;
    private final AtomicInteger count;
    transient Node<E> head;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock putLock;
    private final ReentrantLock takeLock;

    private void a() {
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void b() {
        ReentrantLock reentrantLock = this.putLock;
        reentrantLock.lock();
        try {
            this.notFull.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private synchronized E a(Node<E> node) {
        if (node == null) {
            return c();
        }
        b(node);
        return null;
    }

    private void b(Node<E> node) {
        boolean z;
        Node node2 = this.head;
        while (true) {
            if (node2.f6356a == null) {
                z = false;
                break;
            }
            Node<T> node3 = node2.f6356a;
            if (node3.a().ordinal() > node.a().ordinal()) {
                node2.f6356a = node;
                node.f6356a = node3;
                z = true;
                break;
            }
            node2 = node2.f6356a;
        }
        if (!z) {
            this.f6364a.f6356a = node;
            this.f6364a = node;
        }
    }

    private E c() {
        Node<E> node = this.head;
        Node node2 = node.f6356a;
        node.f6356a = node;
        this.head = node2;
        E b = node2.b();
        node2.a(null);
        return b;
    }

    /* access modifiers changed from: package-private */
    public void fullyLock() {
        this.putLock.lock();
        this.takeLock.lock();
    }

    /* access modifiers changed from: package-private */
    public void fullyUnlock() {
        this.takeLock.unlock();
        this.putLock.unlock();
    }

    public PriorityObjectBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public PriorityObjectBlockingQueue(int i) {
        this.count = new AtomicInteger();
        this.takeLock = new ReentrantLock();
        this.notEmpty = this.takeLock.newCondition();
        this.putLock = new ReentrantLock();
        this.notFull = this.putLock.newCondition();
        if (i > 0) {
            this.capacity = i;
            Node<E> node = new Node<>(null);
            this.head = node;
            this.f6364a = node;
            return;
        }
        throw new IllegalArgumentException();
    }

    public PriorityObjectBlockingQueue(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.putLock;
        reentrantLock.lock();
        int i = 0;
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (i != this.capacity) {
                    a(new Node(next));
                    i++;
                } else {
                    throw new IllegalStateException("Queue full");
                }
            }
            this.count.set(i);
        } finally {
            reentrantLock.unlock();
        }
    }

    public int size() {
        return this.count.get();
    }

    public int remainingCapacity() {
        return this.capacity - this.count.get();
    }

    public void put(E e) throws InterruptedException {
        if (e != null) {
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.putLock;
            AtomicInteger atomicInteger = this.count;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.capacity) {
                try {
                    this.notFull.await();
                } finally {
                    reentrantLock.unlock();
                }
            }
            a(node);
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.capacity) {
                this.notFull.signal();
            }
            if (andIncrement == 0) {
                a();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    /* JADX INFO: finally extract failed */
    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e != null) {
            long nanos = timeUnit.toNanos(j);
            ReentrantLock reentrantLock = this.putLock;
            AtomicInteger atomicInteger = this.count;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.capacity) {
                try {
                    if (nanos <= 0) {
                        reentrantLock.unlock();
                        return false;
                    }
                    nanos = this.notFull.awaitNanos(nanos);
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            a(new Node(e));
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.capacity) {
                this.notFull.signal();
            }
            reentrantLock.unlock();
            if (andIncrement != 0) {
                return true;
            }
            a();
            return true;
        }
        throw new NullPointerException();
    }

    public boolean offer(E e) {
        if (e != null) {
            AtomicInteger atomicInteger = this.count;
            if (atomicInteger.get() == this.capacity) {
                return false;
            }
            int i = -1;
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.putLock;
            reentrantLock.lock();
            try {
                if (atomicInteger.get() < this.capacity) {
                    a(node);
                    i = atomicInteger.getAndIncrement();
                    if (i + 1 < this.capacity) {
                        this.notFull.signal();
                    }
                }
                if (i == 0) {
                    a();
                }
                if (i >= 0) {
                    return true;
                }
                return false;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    public E take() throws InterruptedException {
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.notEmpty.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E a2 = a((Node) null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            b();
        }
        return a2;
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        E a2 = a((Node) null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            b();
        }
        return a2;
    }

    /* JADX INFO: finally extract failed */
    public E poll() {
        AtomicInteger atomicInteger = this.count;
        E e = null;
        if (atomicInteger.get() == 0) {
            return null;
        }
        int i = -1;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() > 0) {
                e = a((Node) null);
                i = atomicInteger.getAndDecrement();
                if (i > 1) {
                    this.notEmpty.signal();
                }
            }
            reentrantLock.unlock();
            if (i == this.capacity) {
                b();
            }
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public E peek() {
        if (this.count.get() == 0) {
            return null;
        }
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            Node node = this.head.f6356a;
            if (node == null) {
                return null;
            }
            E b = node.b();
            reentrantLock.unlock();
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void unlink(Node<E> node, Node<E> node2) {
        node.a(null);
        node2.f6356a = node.f6356a;
        if (this.f6364a == node) {
            this.f6364a = node2;
        }
        if (this.count.getAndDecrement() == this.capacity) {
            this.notFull.signal();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node<E> node = this.head;
            Node<T> node2 = node.f6356a;
            while (true) {
                Node<T> node3 = node2;
                Node<E> node4 = node;
                node = node3;
                if (node == null) {
                    fullyUnlock();
                    return false;
                } else if (obj.equals(node.b())) {
                    unlink(node, node4);
                    fullyUnlock();
                    return true;
                } else {
                    node2 = node.f6356a;
                }
            }
        } catch (Throwable th) {
            fullyUnlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node node = this.head;
            do {
                node = node.f6356a;
                if (node == null) {
                    fullyUnlock();
                    return false;
                }
            } while (!obj.equals(node.b()));
            fullyUnlock();
            return true;
        } catch (Throwable th) {
            fullyUnlock();
            throw th;
        }
    }

    public Object[] toArray() {
        fullyLock();
        try {
            Object[] objArr = new Object[this.count.get()];
            int i = 0;
            Node<T> node = this.head.f6356a;
            while (node != null) {
                int i2 = i + 1;
                objArr[i] = node.b();
                node = node.f6356a;
                i = i2;
            }
            return objArr;
        } finally {
            fullyUnlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        fullyLock();
        try {
            int i = this.count.get();
            if (tArr.length < i) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
            }
            int i2 = 0;
            Node<T> node = this.head.f6356a;
            while (node != null) {
                int i3 = i2 + 1;
                tArr[i2] = node.b();
                node = node.f6356a;
                i2 = i3;
            }
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        } finally {
            fullyUnlock();
        }
    }

    public void clear() {
        fullyLock();
        try {
            Node<E> node = this.head;
            while (true) {
                Node<T> node2 = node.f6356a;
                if (node2 == null) {
                    break;
                }
                node.f6356a = node;
                node2.a(null);
                node = node2;
            }
            this.head = this.f6364a;
            if (this.count.getAndSet(0) == this.capacity) {
                this.notFull.signal();
            }
        } finally {
            fullyUnlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        Node<T> node;
        int i2;
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection != this) {
            boolean z = false;
            if (i <= 0) {
                return 0;
            }
            ReentrantLock reentrantLock = this.takeLock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.count.get());
                node = this.head;
                i2 = 0;
                while (i2 < min) {
                    Node<T> node2 = node.f6356a;
                    collection.add(node2.b());
                    node2.a(null);
                    node.f6356a = node;
                    i2++;
                    node = node2;
                }
                if (i2 > 0) {
                    this.head = node;
                    if (this.count.getAndAdd(-i2) == this.capacity) {
                        z = true;
                    }
                }
                reentrantLock.unlock();
                if (z) {
                    b();
                }
                return min;
            } catch (Throwable th) {
                reentrantLock.unlock();
                if (0 != 0) {
                    b();
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Node<E> b;
        private Node<E> c;
        private E d;

        Itr() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                this.b = PriorityObjectBlockingQueue.this.head.f6356a;
                if (this.b != null) {
                    this.d = this.b.b();
                }
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        public boolean hasNext() {
            return this.b != null;
        }

        private Node<E> a(Node<E> node) {
            Node<T> node2;
            while (true) {
                node2 = node.f6356a;
                if (node2 == node) {
                    return PriorityObjectBlockingQueue.this.head.f6356a;
                }
                if (node2 == null || node2.b() != null) {
                    return node2;
                }
                node = node2;
            }
            return node2;
        }

        public E next() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                if (this.b != null) {
                    E e = this.d;
                    this.c = this.b;
                    this.b = a(this.b);
                    this.d = this.b == null ? null : this.b.b();
                    return e;
                }
                throw new NoSuchElementException();
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        public void remove() {
            if (this.c != null) {
                PriorityObjectBlockingQueue.this.fullyLock();
                try {
                    Node<E> node = this.c;
                    this.c = null;
                    Node<E> node2 = PriorityObjectBlockingQueue.this.head;
                    Node<T> node3 = node2.f6356a;
                    while (true) {
                        Node<T> node4 = node3;
                        Node<E> node5 = node2;
                        node2 = node4;
                        if (node2 == null) {
                            break;
                        } else if (node2 == node) {
                            PriorityObjectBlockingQueue.this.unlink(node2, node5);
                            break;
                        } else {
                            node3 = node2.f6356a;
                        }
                    }
                } finally {
                    PriorityObjectBlockingQueue.this.fullyUnlock();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        fullyLock();
        try {
            objectOutputStream.defaultWriteObject();
            Node node = this.head;
            while (true) {
                node = node.f6356a;
                if (node == null) {
                    objectOutputStream.writeObject((Object) null);
                    return;
                }
                objectOutputStream.writeObject(node.b());
            }
        } finally {
            fullyUnlock();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count.set(0);
        Node<E> node = new Node<>(null);
        this.head = node;
        this.f6364a = node;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}
