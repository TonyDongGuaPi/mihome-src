package com.xiaomi.zxing.qrcode.detector;

public final class FinderPatternInfo {

    /* renamed from: a  reason: collision with root package name */
    private final FinderPattern f1771a;
    private final FinderPattern b;
    private final FinderPattern c;

    public FinderPatternInfo(FinderPattern[] finderPatternArr) {
        this.f1771a = finderPatternArr[0];
        this.b = finderPatternArr[1];
        this.c = finderPatternArr[2];
    }

    public FinderPattern a() {
        return this.f1771a;
    }

    public FinderPattern b() {
        return this.b;
    }

    public FinderPattern c() {
        return this.c;
    }
}
