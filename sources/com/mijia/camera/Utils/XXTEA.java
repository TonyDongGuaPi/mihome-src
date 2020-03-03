package com.mijia.camera.Utils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class XXTEA {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7913a = -1640531527;

    public static IntBuffer a(IntBuffer intBuffer, IntBuffer intBuffer2) {
        if (intBuffer2.limit() != 4) {
            throw new IllegalArgumentException("XXTEA needs a 128-bits key");
        } else if (intBuffer.limit() < 2) {
            return intBuffer;
        } else {
            int limit = intBuffer.limit() - 1;
            int i = intBuffer.get(limit);
            int limit2 = (52 / intBuffer.limit()) + 6;
            int i2 = 0;
            while (true) {
                i2 -= 1640531527;
                int i3 = (i2 >>> 2) & 3;
                int i4 = i;
                int i5 = 0;
                while (i5 < limit) {
                    int i6 = i5 + 1;
                    int i7 = intBuffer.get(i6);
                    i4 = ((((i4 >>> 5) ^ (i7 << 2)) + ((i7 >>> 3) ^ (i4 << 4))) ^ ((i7 ^ i2) + (i4 ^ intBuffer2.get((i5 & 3) ^ i3)))) + intBuffer.get(i5);
                    intBuffer.put(i5, i4);
                    i5 = i6;
                }
                int i8 = intBuffer.get(0);
                int i9 = ((((i4 >>> 5) ^ (i8 << 2)) + ((i8 >>> 3) ^ (i4 << 4))) ^ ((i8 ^ i2) + (intBuffer2.get(i3 ^ (i5 & 3)) ^ i4))) + intBuffer.get(limit);
                intBuffer.put(i5, i9);
                limit2--;
                if (limit2 <= 0) {
                    return intBuffer;
                }
                i = i9;
            }
        }
    }

    public static int[] a(int[] iArr, int[] iArr2) {
        a(IntBuffer.wrap(iArr), IntBuffer.wrap(iArr2));
        return iArr;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        a(ByteBuffer.wrap(bArr), ByteBuffer.wrap(bArr2));
        return bArr;
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        a(byteBuffer.asIntBuffer(), byteBuffer2.asIntBuffer());
        return byteBuffer;
    }

    public static IntBuffer b(IntBuffer intBuffer, IntBuffer intBuffer2) {
        int[] iArr = new int[(intBuffer.limit() - intBuffer.position())];
        intBuffer.get(iArr);
        return a(IntBuffer.wrap(iArr), intBuffer2);
    }

    public static int[] b(int[] iArr, int[] iArr2) {
        return b(IntBuffer.wrap(iArr), IntBuffer.wrap(iArr2)).array();
    }

    public static ByteBuffer b(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[(byteBuffer.limit() - byteBuffer.position())];
        byteBuffer.get(bArr);
        return a(ByteBuffer.wrap(bArr), byteBuffer2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return b(ByteBuffer.wrap(bArr), ByteBuffer.wrap(bArr2)).array();
    }

    public static IntBuffer c(IntBuffer intBuffer, IntBuffer intBuffer2) {
        if (intBuffer2.limit() != 4) {
            throw new IllegalArgumentException("XXTEA needs a 128-bits key");
        } else if (intBuffer.limit() < 2) {
            return intBuffer;
        } else {
            int i = intBuffer.get(0);
            int limit = ((52 / intBuffer.limit()) + 6) * f7913a;
            int limit2 = intBuffer.limit();
            do {
                int i2 = (limit >>> 2) & 3;
                int limit3 = intBuffer.limit() - 1;
                while (limit3 > 0) {
                    int i3 = intBuffer.get(limit3 - 1);
                    i = intBuffer.get(limit3) - (((i ^ limit) + (i3 ^ intBuffer2.get((limit3 & 3) ^ i2))) ^ (((i3 >>> 5) ^ (i << 2)) + ((i >>> 3) ^ (i3 << 4))));
                    intBuffer.put(limit3, i);
                    limit3--;
                }
                int i4 = intBuffer.get(limit2 - 1);
                i = intBuffer.get(0) - (((i ^ limit) + (intBuffer2.get(i2 ^ (limit3 & 3)) ^ i4)) ^ (((i4 >>> 5) ^ (i << 2)) + ((i >>> 3) ^ (i4 << 4))));
                intBuffer.put(0, i);
                limit += 1640531527;
            } while (limit != 0);
            return intBuffer;
        }
    }

    public static int[] c(int[] iArr, int[] iArr2) {
        c(IntBuffer.wrap(iArr), IntBuffer.wrap(iArr2));
        return iArr;
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        c(ByteBuffer.wrap(bArr), ByteBuffer.wrap(bArr2));
        return bArr;
    }

    public static ByteBuffer c(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        c(byteBuffer.asIntBuffer(), byteBuffer2.asIntBuffer());
        return byteBuffer;
    }

    public static IntBuffer d(IntBuffer intBuffer, IntBuffer intBuffer2) {
        int[] iArr = new int[(intBuffer.limit() - intBuffer.position())];
        intBuffer.get(iArr);
        return c(IntBuffer.wrap(iArr), intBuffer2);
    }

    public static int[] d(int[] iArr, int[] iArr2) {
        return d(IntBuffer.wrap(iArr), IntBuffer.wrap(iArr2)).array();
    }

    public static ByteBuffer d(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[(byteBuffer.limit() - byteBuffer.position())];
        byteBuffer.get(bArr);
        return c(ByteBuffer.wrap(bArr), byteBuffer2);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        return d(ByteBuffer.wrap(bArr), ByteBuffer.wrap(bArr2)).array();
    }

    public boolean a(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
