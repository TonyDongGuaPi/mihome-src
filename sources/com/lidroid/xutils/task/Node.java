package com.lidroid.xutils.task;

class Node<T> {

    /* renamed from: a  reason: collision with root package name */
    Node<T> f6356a;
    private boolean b = false;
    private PriorityObject<?> c;

    Node(T t) {
        a(t);
    }

    public Priority a() {
        return this.c.f6363a;
    }

    public T b() {
        if (this.c == null) {
            return null;
        }
        if (this.b) {
            return this.c;
        }
        return this.c.b;
    }

    public void a(T t) {
        if (t == null) {
            this.c = null;
        } else if (t instanceof PriorityObject) {
            this.c = (PriorityObject) t;
            this.b = true;
        } else {
            this.c = new PriorityObject<>(Priority.DEFAULT, t);
        }
    }
}
