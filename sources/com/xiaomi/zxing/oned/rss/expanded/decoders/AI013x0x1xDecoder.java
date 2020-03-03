package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int b = 8;
    private static final int c = 20;
    private static final int d = 16;
    private final String e;
    private final String f;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.e = str2;
        this.f = str;
    }

    public String a() throws NotFoundException {
        if (b().a() == 84) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            b(sb, 48, 20);
            c(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void c(StringBuilder sb, int i) {
        int a2 = c().a(i, 16);
        if (a2 != 38400) {
            sb.append(Operators.BRACKET_START);
            sb.append(this.e);
            sb.append(Operators.BRACKET_END);
            int i2 = a2 % 32;
            int i3 = a2 / 32;
            int i4 = (i3 % 12) + 1;
            int i5 = i3 / 12;
            if (i5 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i5);
            if (i4 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i4);
            if (i2 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb, int i) {
        sb.append(Operators.BRACKET_START);
        sb.append(this.f);
        sb.append(i / 100000);
        sb.append(Operators.BRACKET_END);
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        return i % 100000;
    }
}
