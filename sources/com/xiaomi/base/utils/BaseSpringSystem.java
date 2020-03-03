package com.xiaomi.base.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BaseSpringSystem {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Spring> f10015a = new CopyOnWriteArraySet();
    private boolean b = true;
    private final CopyOnWriteArraySet<SpringSystemListener> c = new CopyOnWriteArraySet<>();
    private final SpringLooper d;
    private final Map<String, Spring> e = new HashMap();

    public BaseSpringSystem(SpringLooper springLooper) {
        if (springLooper != null) {
            this.d = springLooper;
            springLooper.a(this);
            return;
        }
        throw new IllegalArgumentException("springLooper is required");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        Spring spring = this.e.get(str);
        if (spring != null) {
            this.f10015a.add(spring);
            if (c()) {
                this.b = false;
                this.d.a();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
    }

    public void a(SpringSystemListener springSystemListener) {
        if (springSystemListener != null) {
            this.c.add(springSystemListener);
            return;
        }
        throw new IllegalArgumentException("newListener is required");
    }

    /* access modifiers changed from: package-private */
    public void a(double d2) {
        for (Spring next : this.f10015a) {
            if (next.p()) {
                next.a(d2 / 1000.0d);
            } else {
                this.f10015a.remove(next);
            }
        }
    }

    public Spring a() {
        Spring spring = new Spring(this);
        b(spring);
        return spring;
    }

    /* access modifiers changed from: package-private */
    public void a(Spring spring) {
        if (spring != null) {
            this.f10015a.remove(spring);
            this.e.remove(spring.e());
            return;
        }
        throw new IllegalArgumentException("spring is required");
    }

    public List<Spring> b() {
        List list;
        Collection<Spring> values = this.e.values();
        if (values instanceof List) {
            list = (List) values;
        } else {
            list = new ArrayList(values);
        }
        return Collections.unmodifiableList(list);
    }

    public boolean c() {
        return this.b;
    }

    public Spring b(String str) {
        if (str != null) {
            return this.e.get(str);
        }
        throw new IllegalArgumentException("id is required");
    }

    public void b(double d2) {
        Iterator<SpringSystemListener> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
        a(d2);
        if (this.f10015a.isEmpty()) {
            this.b = true;
        }
        Iterator<SpringSystemListener> it2 = this.c.iterator();
        while (it2.hasNext()) {
            it2.next().a(this);
        }
        if (this.b) {
            this.d.b();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Spring spring) {
        if (spring == null) {
            throw new IllegalArgumentException("spring is required");
        } else if (!this.e.containsKey(spring.e())) {
            this.e.put(spring.e(), spring);
        } else {
            throw new IllegalArgumentException("spring is already registered");
        }
    }

    public void d() {
        this.c.clear();
    }

    public void b(SpringSystemListener springSystemListener) {
        if (springSystemListener != null) {
            this.c.remove(springSystemListener);
            return;
        }
        throw new IllegalArgumentException("listenerToRemove is required");
    }
}
