package com.xiaomi.base.utils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpringChain implements SpringListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10042a = 10;
    private static final int b = 70;
    private static final int c = 6;
    private static final int d = 40;
    private static int e = 0;
    private static final SpringConfigRegistry f = SpringConfigRegistry.a();
    private final SpringConfig g;
    private int h;
    private final CopyOnWriteArrayList<SpringListener> i;
    private final SpringConfig j;
    private final SpringSystem k;
    private final CopyOnWriteArrayList<Spring> l;

    private SpringChain() {
        this(40, 6, 70, 10);
    }

    private SpringChain(int i2, int i3, int i4, int i5) {
        this.k = SpringSystem.e();
        this.i = new CopyOnWriteArrayList<>();
        this.l = new CopyOnWriteArrayList<>();
        this.h = -1;
        this.j = SpringConfig.b((double) i2, (double) i3);
        this.g = SpringConfig.b((double) i4, (double) i5);
        SpringConfigRegistry springConfigRegistry = f;
        SpringConfig springConfig = this.j;
        StringBuilder sb = new StringBuilder();
        sb.append("main spring ");
        int i6 = e;
        e = i6 + 1;
        sb.append(i6);
        springConfigRegistry.a(springConfig, sb.toString());
        SpringConfigRegistry springConfigRegistry2 = f;
        SpringConfig springConfig2 = this.g;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("attachment spring ");
        int i7 = e;
        e = i7 + 1;
        sb2.append(i7);
        springConfigRegistry2.a(springConfig2, sb2.toString());
    }

    public static SpringChain a() {
        return new SpringChain();
    }

    public static SpringChain a(int i2, int i3, int i4, int i5) {
        return new SpringChain(i2, i3, i4, i5);
    }

    public SpringChain a(SpringListener springListener) {
        this.l.add(this.k.a().a((SpringListener) this).a(this.g));
        this.i.add(springListener);
        return this;
    }

    public List<Spring> b() {
        return this.l;
    }

    public SpringConfig c() {
        return this.g;
    }

    public Spring d() {
        return this.l.get(this.h);
    }

    public SpringConfig e() {
        return this.j;
    }

    public void a(Spring spring) {
        this.i.get(this.l.indexOf(spring)).a(spring);
    }

    public void b(Spring spring) {
        this.i.get(this.l.indexOf(spring)).b(spring);
    }

    public void c(Spring spring) {
        this.i.get(this.l.indexOf(spring)).c(spring);
    }

    public void d(Spring spring) {
        int i2;
        int i3;
        int indexOf = this.l.indexOf(spring);
        SpringListener springListener = this.i.get(indexOf);
        if (indexOf == this.h) {
            i2 = indexOf - 1;
            i3 = indexOf + 1;
        } else if (indexOf < this.h) {
            i2 = indexOf - 1;
            i3 = -1;
        } else {
            i3 = indexOf > this.h ? indexOf + 1 : -1;
            i2 = -1;
        }
        if (i3 > -1 && i3 < this.l.size()) {
            this.l.get(i3).d(spring.c());
        }
        if (i2 > -1 && i2 < this.l.size()) {
            this.l.get(i2).d(spring.c());
        }
        springListener.d(spring);
    }

    public SpringChain a(int i2) {
        this.h = i2;
        if (this.l.get(this.h) == null) {
            return null;
        }
        for (Spring a2 : this.k.b()) {
            a2.a(this.g);
        }
        d().a(this.j);
        return null;
    }
}
