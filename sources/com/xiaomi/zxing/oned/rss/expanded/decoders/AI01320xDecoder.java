package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    /* access modifiers changed from: protected */
    public int a(int i) {
        return i < 10000 ? i : i - 10000;
    }

    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
