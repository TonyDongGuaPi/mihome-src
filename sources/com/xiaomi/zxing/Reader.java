package com.xiaomi.zxing;

import java.util.Map;

public interface Reader {
    Result a(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException;

    Result a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    void a();
}
