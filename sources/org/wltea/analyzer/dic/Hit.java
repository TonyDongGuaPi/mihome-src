package org.wltea.analyzer.dic;

public class Hit {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4206a = 0;
    private static final int b = 1;
    private static final int c = 16;
    private int d = 0;
    private DictSegment e;
    private int f;
    private int g;

    public boolean a() {
        return (this.d & 1) > 0;
    }

    public void b() {
        this.d |= 1;
    }

    public boolean c() {
        return (this.d & 16) > 0;
    }

    public void d() {
        this.d |= 16;
    }

    public boolean e() {
        return this.d == 0;
    }

    public void f() {
        this.d = 0;
    }

    public DictSegment g() {
        return this.e;
    }

    public void a(DictSegment dictSegment) {
        this.e = dictSegment;
    }

    public int h() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int i() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }
}
