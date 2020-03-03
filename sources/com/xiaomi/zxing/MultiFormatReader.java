package com.xiaomi.zxing;

import com.xiaomi.zxing.aztec.AztecReader;
import com.xiaomi.zxing.datamatrix.DataMatrixReader;
import com.xiaomi.zxing.maxicode.MaxiCodeReader;
import com.xiaomi.zxing.oned.MultiFormatOneDReader;
import com.xiaomi.zxing.pdf417.PDF417Reader;
import com.xiaomi.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatReader implements Reader {

    /* renamed from: a  reason: collision with root package name */
    private Map<DecodeHintType, ?> f1627a;
    private Reader[] b;

    public Result a(BinaryBitmap binaryBitmap) throws NotFoundException {
        a((Map<DecodeHintType, ?>) null);
        return c(binaryBitmap);
    }

    public Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        a(map);
        return c(binaryBitmap);
    }

    public Result b(BinaryBitmap binaryBitmap) throws NotFoundException {
        if (this.b == null) {
            a((Map<DecodeHintType, ?>) null);
        }
        return c(binaryBitmap);
    }

    public void a(Map<DecodeHintType, ?> map) {
        Collection collection;
        this.f1627a = map;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        if (map == null) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (!collection.contains(BarcodeFormat.UPC_A) && !collection.contains(BarcodeFormat.UPC_E) && !collection.contains(BarcodeFormat.EAN_13) && !collection.contains(BarcodeFormat.EAN_8) && !collection.contains(BarcodeFormat.CODABAR) && !collection.contains(BarcodeFormat.CODE_39) && !collection.contains(BarcodeFormat.CODE_93) && !collection.contains(BarcodeFormat.CODE_128) && !collection.contains(BarcodeFormat.ITF) && !collection.contains(BarcodeFormat.RSS_14) && !collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = false;
            }
            if (z && !z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new QRCodeReader());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new DataMatrixReader());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new AztecReader());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new PDF417Reader());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new MaxiCodeReader());
            }
            if (z && z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            arrayList.add(new QRCodeReader());
            arrayList.add(new DataMatrixReader());
            arrayList.add(new AztecReader());
            arrayList.add(new PDF417Reader());
            arrayList.add(new MaxiCodeReader());
            if (z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        this.b = (Reader[]) arrayList.toArray(new Reader[arrayList.size()]);
    }

    public void a() {
        if (this.b != null) {
            for (Reader a2 : this.b) {
                a2.a();
            }
        }
    }

    private Result c(BinaryBitmap binaryBitmap) throws NotFoundException {
        if (this.b != null) {
            Reader[] readerArr = this.b;
            int length = readerArr.length;
            int i = 0;
            while (i < length) {
                try {
                    return readerArr[i].a(binaryBitmap, this.f1627a);
                } catch (ReaderException unused) {
                    i++;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
