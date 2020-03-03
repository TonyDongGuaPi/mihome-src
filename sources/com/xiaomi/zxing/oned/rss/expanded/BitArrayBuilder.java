package com.xiaomi.zxing.oned.rss.expanded;

import com.xiaomi.zxing.common.BitArray;
import java.util.List;

final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    static BitArray a(List<ExpandedPair> list) {
        int size = (list.size() * 2) - 1;
        if (list.get(list.size() - 1).c() == null) {
            size--;
        }
        BitArray bitArray = new BitArray(size * 12);
        int a2 = list.get(0).c().a();
        int i = 0;
        for (int i2 = 11; i2 >= 0; i2--) {
            if (((1 << i2) & a2) != 0) {
                bitArray.b(i);
            }
            i++;
        }
        for (int i3 = 1; i3 < list.size(); i3++) {
            ExpandedPair expandedPair = list.get(i3);
            int a3 = expandedPair.b().a();
            int i4 = i;
            for (int i5 = 11; i5 >= 0; i5--) {
                if (((1 << i5) & a3) != 0) {
                    bitArray.b(i4);
                }
                i4++;
            }
            if (expandedPair.c() != null) {
                int a4 = expandedPair.c().a();
                for (int i6 = 11; i6 >= 0; i6--) {
                    if (((1 << i6) & a4) != 0) {
                        bitArray.b(i4);
                    }
                    i4++;
                }
            }
            i = i4;
        }
        return bitArray;
    }
}
