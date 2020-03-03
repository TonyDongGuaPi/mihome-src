package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.common.BitArray;
import java.util.Map;

public final class UPCAReader extends UPCEANReader {

    /* renamed from: a  reason: collision with root package name */
    private final UPCEANReader f1703a = new EAN13Reader();

    public Result a(int i, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return a(this.f1703a.a(i, bitArray, iArr, map));
    }

    public Result a(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return a(this.f1703a.a(i, bitArray, map));
    }

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return a(this.f1703a.a(binaryBitmap));
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        return a(this.f1703a.a(binaryBitmap, map));
    }

    /* access modifiers changed from: package-private */
    public BarcodeFormat b() {
        return BarcodeFormat.UPC_A;
    }

    /* access modifiers changed from: protected */
    public int a(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        return this.f1703a.a(bitArray, iArr, sb);
    }

    private static Result a(Result result) throws FormatException {
        String a2 = result.a();
        if (a2.charAt(0) == '0') {
            return new Result(a2.substring(1), (byte[]) null, result.c(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }
}
