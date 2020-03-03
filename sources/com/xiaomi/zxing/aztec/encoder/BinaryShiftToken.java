package com.xiaomi.zxing.aztec.encoder;

import com.xiaomi.zxing.common.BitArray;
import kotlin.text.Typography;

final class BinaryShiftToken extends Token {
    private final short b;
    private final short c;

    BinaryShiftToken(Token token, int i, int i2) {
        super(token);
        this.b = (short) i;
        this.c = (short) i2;
    }

    public void a(BitArray bitArray, byte[] bArr) {
        for (int i = 0; i < this.c; i++) {
            if (i == 0 || (i == 31 && this.c <= 62)) {
                bitArray.c(31, 5);
                if (this.c > 62) {
                    bitArray.c(this.c - 31, 16);
                } else if (i == 0) {
                    bitArray.c(Math.min(this.c, 31), 5);
                } else {
                    bitArray.c(this.c - 31, 5);
                }
            }
            bitArray.c(bArr[this.b + i], 8);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.b);
        sb.append("::");
        sb.append((this.b + this.c) - 1);
        sb.append(Typography.e);
        return sb.toString();
    }
}
