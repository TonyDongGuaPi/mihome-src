package com.xiaomi.zxing.oned.rss.expanded.decoders;

final class DecodedInformation extends DecodedObject {

    /* renamed from: a  reason: collision with root package name */
    private final String f1725a;
    private final int b;
    private final boolean c;

    DecodedInformation(int i, String str) {
        super(i);
        this.f1725a = str;
        this.c = false;
        this.b = 0;
    }

    DecodedInformation(int i, String str, int i2) {
        super(i);
        this.c = true;
        this.b = i2;
        this.f1725a = str;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.f1725a;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.b;
    }
}
