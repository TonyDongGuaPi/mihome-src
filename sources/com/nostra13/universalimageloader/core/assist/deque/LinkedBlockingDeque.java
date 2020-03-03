package com.nostra13.universalimageloader.core.assist.deque;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements BlockingDeque<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;

    /* renamed from: a  reason: collision with root package name */
    private transient int f8477a;
    private final int capacity;
    transient Node<E> first;
    transient Node<E> last;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    static final class Node<E> {

        /* renamed from: a  reason: collision with root package name */
        E f8479a;
        Node<E> b;
        Node<E> c;

        Node(E e) {
            this.f8479a = e;
        }
    }

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int i) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        if (i > 0) {
            this.capacity = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public LinkedBlockingDeque(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!b(new Node(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean a(Node<E> node) {
        if (this.f8477a >= this.capacity) {
            return false;
        }
        Node<E> node2 = this.first;
        node.c = node2;
        this.first = node;
        if (this.last == null) {
            this.last = node;
        } else {
            node2.b = node;
        }
        this.f8477a++;
        this.notEmpty.signal();
        return true;
    }

    private boolean b(Node<E> node) {
        if (this.f8477a >= this.capacity) {
            return false;
        }
        Node<E> node2 = this.last;
        node.b = node2;
        this.last = node;
        if (this.first == null) {
            this.first = node;
        } else {
            node2.c = node;
        }
        this.f8477a++;
        this.notEmpty.signal();
        return true;
    }

    private E a() {
        Node<E> node = this.first;
        if (node == null) {
            return null;
        }
        Node<E> node2 = node.c;
        E e = node.f8479a;
        node.f8479a = null;
        node.c = node;
        this.first = node2;
        if (node2 == null) {
            this.last = null;
        } else {
            node2.b = null;
        }
        this.f8477a--;
        this.notFull.signal();
        return e;
    }

    private E b() {
        Node<E> node = this.last;
        if (node == null) {
            return null;
        }
        Node<E> node2 = node.b;
        E e = node.f8479a;
        node.f8479a = null;
        node.b = node;
        this.last = node2;
        if (node2 == null) {
            this.first = null;
        } else {
            node2.c = null;
        }
        this.f8477a--;
        this.notFull.signal();
        return e;
    }

    /* access modifiers changed from: package-private */
    public void unlink(Node<E> node) {
        Node<E> node2 = node.b;
        Node<E> node3 = node.c;
        if (node2 == null) {
            a();
        } else if (node3 == null) {
            b();
        } else {
            node2.c = node3;
            node3.b = node2;
            node.f8479a = null;
            this.f8477a--;
            this.notFull.signal();
        }
    }

    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean offerFirst(E e) {
        if (e != null) {
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                return a(node);
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean offerLast(E e) {
        if (e != null) {
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                return b(node);
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    public void putFirst(E e) throws InterruptedException {
        if (e != null) {
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            while (!a(node)) {
                try {
                    this.notFull.await();
                } finally {
                    reentrantLock.unlock();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public void putLast(E e) throws InterruptedException {
        if (e != null) {
            Node node = new Node(e);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            while (!b(node)) {
                try {
                    this.notFull.await();
                } finally {
                    reentrantLock.unlock();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        if (e != null) {
            Node node = new Node(e);
            long nanos = timeUnit.toNanos(j);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            while (true) {
                try {
                    if (a(node)) {
                        z = true;
                        break;
                    } else if (nanos <= 0) {
                        z = false;
                        break;
                    } else {
                        nanos = this.notFull.awaitNanos(nanos);
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            return z;
        }
        throw new NullPointerException();
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        if (e != null) {
            Node node = new Node(e);
            long nanos = timeUnit.toNanos(j);
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            while (true) {
                try {
                    if (b(node)) {
                        z = true;
                        break;
                    } else if (nanos <= 0) {
                        z = false;
                        break;
                    } else {
                        nanos = this.notFull.awaitNanos(nanos);
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            return z;
        }
        throw new NullPointerException();
    }

    public E removeFirst() {
        E pollFirst = pollFirst();
        if (pollFirst != null) {
            return pollFirst;
        }
        throw new NoSuchElementException();
    }

    public E removeLast() {
        E pollLast = pollLast();
        if (pollLast != null) {
            return pollLast;
        }
        throw new NoSuchElementException();
    }

    public E pollFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return a();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return b();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E takeFirst() throws InterruptedException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                E a2 = a();
                if (a2 != null) {
                    return a2;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E takeLast() throws InterruptedException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                E b = b();
                if (b != null) {
                    return b;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E pollFirst(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                E a2 = a();
                if (a2 != null) {
                    reentrantLock.unlock();
                    return a2;
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.notEmpty.awaitNanos(nanos);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E pollLast(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                E b = b();
                if (b != null) {
                    reentrantLock.unlock();
                    return b;
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.notEmpty.awaitNanos(nanos);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E getFirst() {
        E peekFirst = peekFirst();
        if (peekFirst != null) {
            return peekFirst;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E peekLast = peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        throw new NoSuchElementException();
    }

    public E peekFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.first == null ? null : this.first.f8479a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E peekLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.last == null ? null : this.last.f8479a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node<E> node = this.first; node != null; node = node.c) {
                if (obj.equals(node.f8479a)) {
                    unlink(node);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node<E> node = this.last; node != null; node = node.b) {
                if (obj.equals(node.f8479a)) {
                    unlink(node);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public void put(E e) throws InterruptedException {
        putLast(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return offerLast(e, j, timeUnit);
    }

    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E take() throws InterruptedException {
        return takeFirst();
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return pollFirst(j, timeUnit);
    }

    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.capacity - this.f8477a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection != this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.f8477a);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.first.f8479a);
                    a();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }

    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return this.f8477a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Node<E> node = this.first; node != null; node = node.c) {
                if (obj.equals(node.f8479a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.f8477a];
            int i = 0;
            Node<E> node = this.first;
            while (node != null) {
                int i2 = i + 1;
                objArr[i] = node.f8479a;
                node = node.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int length = tArr.length;
            T[] tArr2 = tArr;
            if (length < this.f8477a) {
                tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f8477a);
            }
            int i = 0;
            Node<E> node = this.first;
            while (node != null) {
                tArr2[i] = node.f8479a;
                node = node.c;
                i++;
            }
            if (tArr2.length > i) {
                tArr2[i] = null;
            }
            return tArr2;
        } finally {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Node<E> node = this.first;
            if (node == null) {
                return XMPConst.ai;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Operators.ARRAY_START);
            while (true) {
                E e = node.f8479a;
                if (e == this) {
                    e = "(this Collection)";
                }
                sb.append(e);
                node = node.c;
                if (node == null) {
                    sb.append(Operators.ARRAY_END);
                    String sb2 = sb.toString();
                    reentrantLock.unlock();
                    return sb2;
                }
                sb.append(',');
                sb.append(' ');
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Node<E> node = this.first;
            while (node != null) {
                node.f8479a = null;
                Node<E> node2 = node.c;
                node.b = null;
                node.c = null;
                node = node2;
            }
            this.last = null;
            this.first = null;
            this.f8477a = 0;
            this.notFull.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    public Iterator<E> descendingIterator() {
        return new DescendingItr();
    }

    private abstract class AbstractItr implements Iterator<E> {

        /* renamed from: a  reason: collision with root package name */
        Node<E> f8478a;
        E b;
        private Node<E> d;

        /* access modifiers changed from: package-private */
        public abstract Node<E> a();

        /* access modifiers changed from: package-private */
        public abstract Node<E> a(Node<E> node);

        AbstractItr() {
            ReentrantLock reentrantLock = LinkedBlockingDeque.this.lock;
            reentrantLock.lock();
            try {
                this.f8478a = a();
                this.b = this.f8478a == null ? null : this.f8478a.f8479a;
            } finally {
                reentrantLock.unlock();
            }
        }

        private Node<E> b(Node<E> node) {
            while (true) {
                Node<E> a2 = a(node);
                if (a2 == null) {
                    return null;
                }
                if (a2.f8479a != null) {
                    return a2;
                }
                if (a2 == node) {
                    return a();
                }
                node = a2;
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            ReentrantLock reentrantLock = LinkedBlockingDeque.this.lock;
            reentrantLock.lock();
            try {
                this.f8478a = b(this.f8478a);
                this.b = this.f8478a == null ? null : this.f8478a.f8479a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.f8478a != null;
        }

        public E next() {
            if (this.f8478a != null) {
                this.d = this.f8478a;
                E e = this.b;
                b();
                return e;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            Node<E> node = this.d;
            if (node != null) {
                this.d = null;
                ReentrantLock reentrantLock = LinkedBlockingDeque.this.lock;
                reentrantLock.lock();
                try {
                    if (node.f8479a != null) {
                        LinkedBlockingDeque.this.unlink(node);
                    }
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private class Itr extends LinkedBlockingDeque<E>.AbstractItr {
        private Itr() {
            super();
        }

        /* access modifiers changed from: package-private */
        public Node<E> a() {
            return LinkedBlockingDeque.this.first;
        }

        /* access modifiers changed from: package-private */
        public Node<E> a(Node<E> node) {
            return node.c;
        }
    }

    private class DescendingItr extends LinkedBlockingDeque<E>.AbstractItr {
        private DescendingItr() {
            super();
        }

        /* access modifiers changed from: package-private */
        public Node<E> a() {
            return LinkedBlockingDeque.this.last;
        }

        /* access modifiers changed from: package-private */
        public Node<E> a(Node<E> node) {
            return node.b;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (Node<E> node = this.first; node != null; node = node.c) {
                objectOutputStream.writeObject(node.f8479a);
            }
            objectOutputStream.writeObject((Object) null);
        } finally {
            reentrantLock.unlock();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.f8477a = 0;
        this.first = null;
        this.last = null;
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
