package org.greenrobot.greendao.query;

import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.InternalQueryDaoAccess;

public class LazyList<E> implements Closeable, List<E> {

    /* renamed from: a  reason: collision with root package name */
    private final InternalQueryDaoAccess<E> f3536a;
    private final Cursor b;
    private final List<E> c;
    /* access modifiers changed from: private */
    public final int d;
    private final ReentrantLock e;
    private volatile int f;

    protected class LazyIterator implements CloseableListIterator<E> {
        private int b;
        private final boolean c;

        public LazyIterator(int i, boolean z) {
            this.b = i;
            this.c = z;
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        public boolean hasPrevious() {
            return this.b > 0;
        }

        public int nextIndex() {
            return this.b;
        }

        public E previous() {
            if (this.b > 0) {
                this.b--;
                return LazyList.this.get(this.b);
            }
            throw new NoSuchElementException();
        }

        public int previousIndex() {
            return this.b - 1;
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return this.b < LazyList.this.d;
        }

        public E next() {
            if (this.b < LazyList.this.d) {
                E e = LazyList.this.get(this.b);
                this.b++;
                if (this.b == LazyList.this.d && this.c) {
                    close();
                }
                return e;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void close() {
            LazyList.this.close();
        }
    }

    LazyList(InternalQueryDaoAccess<E> internalQueryDaoAccess, Cursor cursor, boolean z) {
        this.b = cursor;
        this.f3536a = internalQueryDaoAccess;
        this.d = cursor.getCount();
        if (z) {
            this.c = new ArrayList(this.d);
            for (int i = 0; i < this.d; i++) {
                this.c.add((Object) null);
            }
        } else {
            this.c = null;
        }
        if (this.d == 0) {
            cursor.close();
        }
        this.e = new ReentrantLock();
    }

    public void a() {
        b();
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            get(i);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.c == null) {
            throw new DaoException("This operation only works with cached lazy lists");
        }
    }

    public E a(int i) {
        if (this.c != null) {
            return this.c.get(i);
        }
        return null;
    }

    public void close() {
        this.b.close();
    }

    public boolean c() {
        return this.b.isClosed();
    }

    public int d() {
        return this.f;
    }

    public boolean e() {
        return this.f == this.d;
    }

    public boolean add(E e2) {
        throw new UnsupportedOperationException();
    }

    public void add(int i, E e2) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        a();
        return this.c.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        a();
        return this.c.containsAll(collection);
    }

    public E get(int i) {
        if (this.c != null) {
            E e2 = this.c.get(i);
            if (e2 == null) {
                this.e.lock();
                try {
                    e2 = this.c.get(i);
                    if (e2 == null) {
                        e2 = b(i);
                        this.c.set(i, e2);
                        this.f++;
                        if (this.f == this.d) {
                            this.b.close();
                        }
                    }
                } finally {
                    this.e.unlock();
                }
            }
            return e2;
        }
        this.e.lock();
        try {
            return b(i);
        } finally {
            this.e.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public E b(int i) {
        if (this.b.moveToPosition(i)) {
            E a2 = this.f3536a.a(this.b, 0, true);
            if (a2 != null) {
                return a2;
            }
            throw new DaoException("Loading of entity failed (null) at position " + i);
        }
        throw new DaoException("Could not move to cursor location " + i);
    }

    public int indexOf(Object obj) {
        a();
        return this.c.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.d == 0;
    }

    public Iterator<E> iterator() {
        return new LazyIterator(0, false);
    }

    public int lastIndexOf(Object obj) {
        a();
        return this.c.lastIndexOf(obj);
    }

    /* renamed from: f */
    public CloseableListIterator<E> listIterator() {
        return new LazyIterator(0, false);
    }

    public CloseableListIterator<E> g() {
        return new LazyIterator(0, true);
    }

    public ListIterator<E> listIterator(int i) {
        return new LazyIterator(i, false);
    }

    public E remove(int i) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public E set(int i, E e2) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.d;
    }

    public List<E> subList(int i, int i2) {
        b();
        for (int i3 = i; i3 < i2; i3++) {
            get(i3);
        }
        return this.c.subList(i, i2);
    }

    public Object[] toArray() {
        a();
        return this.c.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        a();
        return this.c.toArray(tArr);
    }
}
