package com.xiaomi.smarthome.device.utils;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExceptionList<E> implements List<E> {

    /* renamed from: a  reason: collision with root package name */
    ArrayList<E> f15471a;
    HashSet<E> b;
    ArrayList<E> c;
    boolean d;
    ModifyListener<E> e;

    public interface ModifyListener<E> {
        void a(ArrayList<E> arrayList);
    }

    @Deprecated
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Deprecated
    @NonNull
    public Iterator<E> iterator() {
        return null;
    }

    @Deprecated
    public ListIterator<E> listIterator() {
        return null;
    }

    @Deprecated
    @NonNull
    public ListIterator<E> listIterator(int i) {
        return null;
    }

    @Deprecated
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Deprecated
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Deprecated
    @NonNull
    public List<E> subList(int i, int i2) {
        return null;
    }

    @Deprecated
    @NonNull
    public <T> T[] toArray(T[] tArr) {
        return null;
    }

    public void a(ModifyListener<E> modifyListener) {
        this.e = modifyListener;
    }

    public ExceptionList() {
        this(10);
    }

    public ExceptionList(int i) {
        this.d = false;
        if (i >= 0) {
            this.f15471a = new ArrayList<>(i);
            this.b = new HashSet<>(i);
            return;
        }
        throw new IllegalArgumentException("Illegal Capacity: " + i);
    }

    public ExceptionList(Collection<E> collection) {
        this.d = false;
        this.f15471a = new ArrayList<>(collection);
        this.b = new HashSet<>(collection.size());
    }

    public void a() {
        this.c = new ArrayList<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            this.c.add(get(i));
        }
    }

    public void b() {
        this.d = c();
        if (this.d && this.e != null) {
            this.e.a(this.c);
        }
    }

    public boolean c() {
        int size = size();
        if (size != this.c.size()) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (get(i) != this.c.get(i)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<E> d() {
        return this.f15471a;
    }

    public HashSet<E> e() {
        return this.b;
    }

    private boolean a(int i) {
        return this.b.contains(this.f15471a.get(i));
    }

    private int b(int i) {
        if (this.b == null || this.b.size() <= 0) {
            return i;
        }
        try {
            if (a(i)) {
                return -1;
            }
            int i2 = i;
            for (int i3 = 0; i3 < i; i3++) {
                if (a(i3)) {
                    i2--;
                }
            }
            return i2;
        } catch (Exception unused) {
            return -1;
        }
    }

    private int c(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            return -1;
        }
        for (int i2 = i; i2 < size; i2++) {
            if (b(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public void add(int i, E e2) {
        int c2 = c(i);
        if (c2 >= 0) {
            this.f15471a.add(c2, e2);
        }
    }

    public boolean add(E e2) {
        return this.f15471a.add(e2);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        int c2 = c(i);
        if (c2 < 0) {
            return false;
        }
        return this.f15471a.addAll(c2, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        return this.f15471a.addAll(collection);
    }

    public void clear() {
        this.f15471a.clear();
    }

    public boolean contains(Object obj) {
        if (this.b.contains(obj)) {
            return false;
        }
        return this.f15471a.contains(obj);
    }

    public E get(int i) {
        int c2;
        if (i < 0 || i >= size() || (c2 = c(i)) < 0) {
            return null;
        }
        return this.f15471a.get(c2);
    }

    public int indexOf(Object obj) {
        int indexOf;
        if (!this.b.contains(obj) && (indexOf = this.f15471a.indexOf(obj)) >= 0) {
            return b(indexOf);
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    private class Itr implements Iterator<E> {

        /* renamed from: a  reason: collision with root package name */
        int f15472a = 0;

        private Itr() {
        }

        public boolean hasNext() {
            return this.f15472a >= 0 && this.f15472a < ExceptionList.this.size();
        }

        public E next() {
            E e = ExceptionList.this.get(this.f15472a);
            this.f15472a++;
            return e;
        }

        public void remove() {
            ExceptionList.this.remove(this.f15472a);
        }
    }

    public int lastIndexOf(Object obj) {
        int lastIndexOf;
        if (!this.b.contains(obj) && (lastIndexOf = this.f15471a.lastIndexOf(obj)) >= 0) {
            return b(lastIndexOf);
        }
        return -1;
    }

    public E remove(int i) {
        int c2;
        if (i < 0 || i >= size() || (c2 = c(i)) < 0) {
            return null;
        }
        return this.f15471a.remove(c2);
    }

    public boolean remove(Object obj) {
        if (this.b.contains(obj)) {
            return false;
        }
        return this.f15471a.remove(obj);
    }

    public E set(int i, E e2) {
        int c2;
        if (i < 0 || i >= size() || (c2 = c(i)) < 0) {
            return null;
        }
        return this.f15471a.set(c2, e2);
    }

    public int size() {
        int size = this.f15471a.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (this.b.contains(this.f15471a.get(i2))) {
                i++;
            }
        }
        return size - i;
    }

    @Deprecated
    @NonNull
    public Object[] toArray() {
        return new Object[0];
    }
}
