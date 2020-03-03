package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;

public abstract class AbstractExpandedDecoder {

    /* renamed from: a  reason: collision with root package name */
    private final BitArray f1720a;
    private final GeneralAppIdDecoder b;

    public abstract String a() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(BitArray bitArray) {
        this.f1720a = bitArray;
        this.b = new GeneralAppIdDecoder(bitArray);
    }

    /* access modifiers changed from: protected */
    public final BitArray b() {
        return this.f1720a;
    }

    /* access modifiers changed from: protected */
    public final GeneralAppIdDecoder c() {
        return this.b;
    }

    public static AbstractExpandedDecoder a(BitArray bitArray) {
        if (bitArray.a(1)) {
            return new AI01AndOtherAIs(bitArray);
        }
        if (!bitArray.a(2)) {
            return new AnyAIDecoder(bitArray);
        }
        switch (GeneralAppIdDecoder.a(bitArray, 1, 4)) {
            case 4:
                return new AI013103decoder(bitArray);
            case 5:
                return new AI01320xDecoder(bitArray);
            default:
                switch (GeneralAppIdDecoder.a(bitArray, 1, 5)) {
                    case 12:
                        return new AI01392xDecoder(bitArray);
                    case 13:
                        return new AI01393xDecoder(bitArray);
                    default:
                        switch (GeneralAppIdDecoder.a(bitArray, 1, 7)) {
                            case 56:
                                return new AI013x0x1xDecoder(bitArray, "310", "11");
                            case 57:
                                return new AI013x0x1xDecoder(bitArray, "320", "11");
                            case 58:
                                return new AI013x0x1xDecoder(bitArray, "310", "13");
                            case 59:
                                return new AI013x0x1xDecoder(bitArray, "320", "13");
                            case 60:
                                return new AI013x0x1xDecoder(bitArray, "310", "15");
                            case 61:
                                return new AI013x0x1xDecoder(bitArray, "320", "15");
                            case 62:
                                return new AI013x0x1xDecoder(bitArray, "310", "17");
                            case 63:
                                return new AI013x0x1xDecoder(bitArray, "320", "17");
                            default:
                                throw new IllegalStateException("unknown decoder: " + bitArray);
                        }
                }
        }
    }
}
