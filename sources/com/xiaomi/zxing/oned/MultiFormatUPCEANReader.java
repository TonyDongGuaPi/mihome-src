package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    private final UPCEANReader[] f1702a;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> map) {
        Collection collection;
        if (map == null) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new EAN13Reader());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new UPCAReader());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new EAN8Reader());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new UPCEReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new EAN13Reader());
            arrayList.add(new EAN8Reader());
            arrayList.add(new UPCEReader());
        }
        this.f1702a = (UPCEANReader[]) arrayList.toArray(new UPCEANReader[arrayList.size()]);
    }

    public Result a(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        Collection collection;
        int[] a2 = UPCEANReader.a(bitArray);
        UPCEANReader[] uPCEANReaderArr = this.f1702a;
        boolean z = false;
        int i2 = 0;
        while (i2 < uPCEANReaderArr.length) {
            try {
                Result a3 = uPCEANReaderArr[i2].a(i, bitArray, a2, map);
                boolean z2 = a3.d() == BarcodeFormat.EAN_13 && a3.a().charAt(0) == '0';
                if (map == null) {
                    collection = null;
                } else {
                    collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                }
                if (collection == null || collection.contains(BarcodeFormat.UPC_A)) {
                    z = true;
                }
                if (!z2 || !z) {
                    return a3;
                }
                Result result = new Result(a3.a().substring(1), a3.b(), a3.c(), BarcodeFormat.UPC_A);
                result.a(a3.e());
                return result;
            } catch (ReaderException unused) {
                i2++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void a() {
        for (UPCEANReader a2 : this.f1702a) {
            a2.a();
        }
    }
}
