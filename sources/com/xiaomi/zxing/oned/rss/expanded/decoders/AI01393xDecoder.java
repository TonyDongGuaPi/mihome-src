package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

final class AI01393xDecoder extends AI01decoder {
    private static final int b = 8;
    private static final int c = 2;
    private static final int d = 10;

    AI01393xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String a() throws NotFoundException, FormatException {
        if (b().a() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int a2 = c().a(48, 2);
            sb.append("(393");
            sb.append(a2);
            sb.append(Operators.BRACKET_END);
            int a3 = c().a(50, 10);
            if (a3 / 100 == 0) {
                sb.append('0');
            }
            if (a3 / 10 == 0) {
                sb.append('0');
            }
            sb.append(a3);
            sb.append(c().a(60, (String) null).a());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
