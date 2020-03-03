package com.xiaomi.zxing.oned.rss.expanded.decoders;

final class BlockParsedResult {

    /* renamed from: a  reason: collision with root package name */
    private final DecodedInformation f1722a;
    private final boolean b;

    BlockParsedResult(boolean z) {
        this((DecodedInformation) null, z);
    }

    BlockParsedResult(DecodedInformation decodedInformation, boolean z) {
        this.b = z;
        this.f1722a = decodedInformation;
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation a() {
        return this.f1722a;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b;
    }
}
