package com.xiaomi.zxing.aztec.encoder;

import com.xiaomi.zxing.common.BitArray;

abstract class Token {

    /* renamed from: a  reason: collision with root package name */
    static final Token f1644a = new SimpleToken((Token) null, 0, 0);
    private final Token b;

    /* access modifiers changed from: package-private */
    public abstract void a(BitArray bitArray, byte[] bArr);

    Token(Token token) {
        this.b = token;
    }

    /* access modifiers changed from: package-private */
    public final Token a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final Token a(int i, int i2) {
        return new SimpleToken(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    public final Token b(int i, int i2) {
        return new BinaryShiftToken(this, i, i2);
    }
}
