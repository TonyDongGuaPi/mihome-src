package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

abstract class AI013x0xDecoder extends AI01weightDecoder {
    private static final int b = 5;
    private static final int c = 15;

    AI013x0xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String a() throws NotFoundException {
        if (b().a() == 60) {
            StringBuilder sb = new StringBuilder();
            b(sb, 5);
            b(sb, 45, 15);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
