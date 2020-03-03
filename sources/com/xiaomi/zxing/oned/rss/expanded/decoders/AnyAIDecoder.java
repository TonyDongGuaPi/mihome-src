package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

final class AnyAIDecoder extends AbstractExpandedDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1721a = 5;

    AnyAIDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String a() throws NotFoundException, FormatException {
        return c().a(new StringBuilder(), 5);
    }
}
