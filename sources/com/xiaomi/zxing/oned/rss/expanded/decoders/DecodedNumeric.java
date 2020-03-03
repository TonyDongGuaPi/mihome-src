package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.FormatException;

final class DecodedNumeric extends DecodedObject {

    /* renamed from: a  reason: collision with root package name */
    static final int f1726a = 10;
    private final int b;
    private final int c;

    DecodedNumeric(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.b = i2;
        this.c = i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return (this.b * 10) + this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.b == 10;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.c == 10;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.b == 10 || this.c == 10;
    }
}
