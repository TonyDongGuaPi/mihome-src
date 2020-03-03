package com.xiaomi.zxing.aztec.encoder;

import com.xiaomi.zxing.common.BitArray;
import kotlin.text.Typography;

final class SimpleToken extends Token {
    private final short b;
    private final short c;

    SimpleToken(Token token, int i, int i2) {
        super(token);
        this.b = (short) i;
        this.c = (short) i2;
    }

    /* access modifiers changed from: package-private */
    public void a(BitArray bitArray, byte[] bArr) {
        bitArray.c(this.b, this.c);
    }

    public String toString() {
        short s = (this.b & ((1 << this.c) - 1)) | (1 << this.c);
        return Typography.d + Integer.toBinaryString(s | (1 << this.c)).substring(1) + Typography.e;
    }
}
