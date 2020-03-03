package com.xiaomi.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState {

    /* renamed from: a  reason: collision with root package name */
    private int f1723a = 0;
    private State b = State.NUMERIC;

    private enum State {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    CurrentParsingState() {
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f1723a;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.f1723a = i;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        this.f1723a += i;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b == State.ALPHA;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.b == State.NUMERIC;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.b == State.ISO_IEC_646;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b = State.NUMERIC;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.b = State.ALPHA;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.b = State.ISO_IEC_646;
    }
}
