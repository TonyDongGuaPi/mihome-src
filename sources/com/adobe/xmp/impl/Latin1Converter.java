package com.adobe.xmp.impl;

import java.io.UnsupportedEncodingException;

public class Latin1Converter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f685a = 0;
    private static final int b = 11;

    private Latin1Converter() {
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        if (!"UTF-8".equals(byteBuffer.c())) {
            return byteBuffer;
        }
        byte[] bArr = new byte[8];
        ByteBuffer byteBuffer2 = new ByteBuffer((byteBuffer.b() * 4) / 3);
        int i = 0;
        char c = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < byteBuffer.b()) {
            int b2 = byteBuffer.b(i);
            if (c == 11) {
                if (i2 <= 0 || (b2 & 192) != 128) {
                    byteBuffer2.a(a(bArr[0]));
                    i -= i3;
                } else {
                    int i4 = i3 + 1;
                    bArr[i3] = (byte) b2;
                    i2--;
                    if (i2 == 0) {
                        byteBuffer2.a(bArr, 0, i4);
                    } else {
                        i3 = i4;
                    }
                }
                c = 0;
                i3 = 0;
            } else if (b2 < 127) {
                byteBuffer2.a((byte) b2);
            } else if (b2 >= 192) {
                int i5 = -1;
                int i6 = b2;
                while (i5 < 8 && (i6 & 128) == 128) {
                    i5++;
                    i6 <<= 1;
                }
                bArr[i3] = (byte) b2;
                i3++;
                i2 = i5;
                c = 11;
            } else {
                byteBuffer2.a(a((byte) b2));
            }
            i++;
        }
        if (c == 11) {
            for (int i7 = 0; i7 < i3; i7++) {
                byteBuffer2.a(a(bArr[i7]));
            }
        }
        return byteBuffer2;
    }

    private static byte[] a(byte b2) {
        byte b3 = b2 & 255;
        if (b3 >= 128) {
            if (b3 == 129 || b3 == 141 || b3 == 143 || b3 == 144 || b3 == 157) {
                return new byte[]{32};
            }
            try {
                return new String(new byte[]{b2}, "cp1252").getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new byte[]{b2};
    }
}
