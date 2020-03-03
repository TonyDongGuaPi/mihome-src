package com.xiaomi.zxing.multi;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.Result;
import java.util.Map;

public interface MultipleBarcodeReader {
    Result[] a_(BinaryBitmap binaryBitmap) throws NotFoundException;

    Result[] a_(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException;
}
