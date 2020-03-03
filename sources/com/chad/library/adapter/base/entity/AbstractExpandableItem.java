package com.chad.library.adapter.base.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExpandableItem<T> implements IExpandable<T> {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f5138a = false;
    protected List<T> b;

    public boolean a() {
        return this.f5138a;
    }

    public void a(boolean z) {
        this.f5138a = z;
    }

    public List<T> b() {
        return this.b;
    }

    public boolean c() {
        return this.b != null && this.b.size() > 0;
    }

    public void a(List<T> list) {
        this.b = list;
    }

    public T a(int i) {
        if (!c() || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    public int a(T t) {
        if (this.b != null) {
            return this.b.indexOf(t);
        }
        return -1;
    }

    public void b(T t) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(t);
    }

    public void a(int i, T t) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            b(t);
        } else {
            this.b.add(i, t);
        }
    }

    public boolean c(T t) {
        return this.b != null && this.b.contains(t);
    }

    public boolean d(T t) {
        return this.b != null && this.b.remove(t);
    }

    public boolean b(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return false;
        }
        this.b.remove(i);
        return true;
    }
}
