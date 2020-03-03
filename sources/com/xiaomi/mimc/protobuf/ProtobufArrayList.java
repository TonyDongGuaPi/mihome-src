package com.xiaomi.mimc.protobuf;

import java.util.ArrayList;
import java.util.List;

final class ProtobufArrayList<E> extends AbstractProtobufList<E> {
    private static final ProtobufArrayList<Object> b = new ProtobufArrayList<>();
    private final List<E> c;

    static {
        b.b();
    }

    public static <E> ProtobufArrayList<E> d() {
        return b;
    }

    ProtobufArrayList() {
        this(new ArrayList(10));
    }

    private ProtobufArrayList(List<E> list) {
        this.c = list;
    }

    /* renamed from: a */
    public ProtobufArrayList<E> e(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.c);
            return new ProtobufArrayList<>(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public void add(int i, E e) {
        c();
        this.c.add(i, e);
        this.modCount++;
    }

    public E get(int i) {
        return this.c.get(i);
    }

    public E remove(int i) {
        c();
        E remove = this.c.remove(i);
        this.modCount++;
        return remove;
    }

    public E set(int i, E e) {
        c();
        E e2 = this.c.set(i, e);
        this.modCount++;
        return e2;
    }

    public int size() {
        return this.c.size();
    }
}
