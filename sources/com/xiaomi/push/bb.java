package com.xiaomi.push;

import java.util.LinkedList;

public class bb {

    /* renamed from: a  reason: collision with root package name */
    private LinkedList<a> f12644a = new LinkedList<>();

    public static class a {
        /* access modifiers changed from: private */
        public static final bb d = new bb();

        /* renamed from: a  reason: collision with root package name */
        public int f12645a;
        public String b;
        public Object c;

        a(int i, Object obj) {
            this.f12645a = i;
            this.c = obj;
        }
    }

    public static bb a() {
        return a.d;
    }

    private void d() {
        if (this.f12644a.size() > 100) {
            this.f12644a.removeFirst();
        }
    }

    public synchronized void a(Object obj) {
        this.f12644a.add(new a(0, obj));
        d();
    }

    public synchronized int b() {
        return this.f12644a.size();
    }

    public synchronized LinkedList<a> c() {
        LinkedList<a> linkedList;
        linkedList = this.f12644a;
        this.f12644a = new LinkedList<>();
        return linkedList;
    }
}
