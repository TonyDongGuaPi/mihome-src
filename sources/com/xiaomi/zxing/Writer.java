package com.xiaomi.zxing;

import com.xiaomi.zxing.common.BitMatrix;
import java.util.Map;

public interface Writer {
    BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException;

    BitMatrix a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException;
}
