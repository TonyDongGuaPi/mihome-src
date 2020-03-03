package com.xiaomi.zxing.oned.rss;

final class Pair extends DataCharacter {

    /* renamed from: a  reason: collision with root package name */
    private final FinderPattern f1714a;
    private int b;

    Pair(int i, int i2, FinderPattern finderPattern) {
        super(i, i2);
        this.f1714a = finderPattern;
    }

    /* access modifiers changed from: package-private */
    public FinderPattern c() {
        return this.f1714a;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b++;
    }
}
