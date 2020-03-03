package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.common.BitArray;

final class UPCEANExtensionSupport {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1707a = {1, 1, 2};
    private final UPCEANExtension2Support b = new UPCEANExtension2Support();
    private final UPCEANExtension5Support c = new UPCEANExtension5Support();

    UPCEANExtensionSupport() {
    }

    /* access modifiers changed from: package-private */
    public Result a(int i, BitArray bitArray, int i2) throws NotFoundException {
        int[] a2 = UPCEANReader.a(bitArray, i2, false, f1707a);
        try {
            return this.c.a(i, bitArray, a2);
        } catch (ReaderException unused) {
            return this.b.a(i, bitArray, a2);
        }
    }
}
