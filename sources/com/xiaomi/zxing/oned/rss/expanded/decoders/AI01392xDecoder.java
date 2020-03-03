package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

final class AI01392xDecoder extends AI01decoder {
    private static final int b = 8;
    private static final int c = 2;

    AI01392xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String a() throws NotFoundException, FormatException {
        if (b().a() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int a2 = c().a(48, 2);
            sb.append("(392");
            sb.append(a2);
            sb.append(Operators.BRACKET_END);
            sb.append(c().a(50, (String) null).a());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
