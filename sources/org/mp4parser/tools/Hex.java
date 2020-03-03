package org.mp4parser.tools;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class Hex {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f4108a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        return a(bArr, 0);
    }

    public static String a(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        StringBuilder sb = new StringBuilder();
        while (duplicate.remaining() > 0) {
            byte b = duplicate.get();
            sb.append(f4108a[(b & 240) >>> 4]);
            sb.append(f4108a[b & 15]);
        }
        return sb.toString();
    }

    public static String a(byte[] bArr, int i) {
        int length = bArr.length;
        char[] cArr = new char[((length << 1) + (i > 0 ? length / i : 0))];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i > 0 && i3 % i == 0 && i2 > 0) {
                cArr[i2] = '-';
                i2++;
            }
            int i4 = i2 + 1;
            cArr[i2] = f4108a[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = f4108a[bArr[i3] & 15];
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 2;
            byteArrayOutputStream.write(Integer.parseInt(str.substring(i, i2), 16));
            i = i2;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
