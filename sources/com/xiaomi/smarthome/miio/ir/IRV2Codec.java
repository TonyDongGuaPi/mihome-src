package com.xiaomi.smarthome.miio.ir;

import com.xiaomi.smarthome.library.crypto.Base64Coder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class IRV2Codec {
    public static int[] a(String str) {
        return ByteBuffer.wrap(Base64Coder.a(str)).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().array();
    }

    public static String a(byte[] bArr) {
        return Base64Coder.a(bArr);
    }
}
