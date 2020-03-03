package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.common.BitArray;

final class AI013103decoder extends AI013x0xDecoder {
    /* access modifiers changed from: protected */
    public int a(int i) {
        return i;
    }

    AI013103decoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb, int i) {
        sb.append("(3103)");
    }
}
