package org.jacoco.agent.rt.internal_8ff85ea.core.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Java9Support {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3618a = 53;

    private Java9Support() {
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 8);
        bArr[i + 1] = (byte) i2;
    }

    private static short a(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public static boolean a(byte[] bArr) {
        return a(bArr, 6) == 53;
    }

    public static byte[] b(byte[] bArr) {
        return a(bArr) ? c(bArr) : bArr;
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        a(bArr2, 6, 52);
        return bArr2;
    }

    public static void d(byte[] bArr) {
        a(bArr, 6, 53);
    }
}
