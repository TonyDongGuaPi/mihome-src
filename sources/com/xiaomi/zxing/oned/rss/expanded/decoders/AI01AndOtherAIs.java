package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder {
    private static final int b = 4;

    AI01AndOtherAIs(BitArray bitArray) {
        super(bitArray);
    }

    public String a() throws NotFoundException, FormatException {
        StringBuilder sb = new StringBuilder();
        sb.append("(01)");
        int length = sb.length();
        sb.append(c().a(4, 4));
        a(sb, 8, length);
        return c().a(sb, 48);
    }
}
