package com.xiaomi.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject {

    /* renamed from: a  reason: collision with root package name */
    static final char f1724a = '$';
    private final char b;

    DecodedChar(int i, char c) {
        super(i);
        this.b = c;
    }

    /* access modifiers changed from: package-private */
    public char a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b == '$';
    }
}
