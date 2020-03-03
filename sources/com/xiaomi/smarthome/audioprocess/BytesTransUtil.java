package com.xiaomi.smarthome.audioprocess;

import java.io.PrintStream;
import java.nio.ByteOrder;

public class BytesTransUtil {

    /* renamed from: a  reason: collision with root package name */
    private static BytesTransUtil f13752a;
    private String b = "BytesTransUtil";

    private BytesTransUtil() {
    }

    public static BytesTransUtil a() {
        if (f13752a == null) {
            f13752a = new BytesTransUtil();
        }
        return f13752a;
    }

    public boolean b() {
        return ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public byte[] a(short s, boolean z) {
        byte[] bArr = new byte[2];
        if (z) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                bArr[length] = (byte) (s & 255);
                s = (short) (s >> 8);
            }
        } else {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (s & 255);
                s = (short) (s >> 8);
            }
        }
        return bArr;
    }

    public byte[] a(int i, boolean z) {
        byte[] bArr = new byte[4];
        if (z) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                bArr[length] = (byte) (i & 255);
                i >>= 8;
            }
        } else {
            System.out.println("1");
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr[i2] = (byte) (i & 255);
                i >>= 8;
            }
        }
        return bArr;
    }

    public byte[] a(long j, boolean z) {
        byte[] bArr = new byte[8];
        if (z) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                bArr[length] = (byte) ((int) (j & 255));
                j >>= 8;
            }
        } else {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) ((int) (j & 255));
                j >>= 8;
            }
        }
        return bArr;
    }

    /* JADX WARNING: type inference failed for: r4v6, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public short a(byte[] r3, boolean r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0034
            int r0 = r3.length
            r1 = 2
            if (r0 > r1) goto L_0x002c
            r0 = 0
            if (r4 == 0) goto L_0x0019
            r4 = 0
        L_0x000a:
            int r1 = r3.length
            if (r0 >= r1) goto L_0x002b
            int r4 = r4 << 8
            short r4 = (short) r4
            byte r1 = r3[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r4 = r4 | r1
            short r4 = (short) r4
            int r0 = r0 + 1
            goto L_0x000a
        L_0x0019:
            int r4 = r3.length
            int r4 = r4 + -1
        L_0x001c:
            if (r4 < 0) goto L_0x002a
            int r0 = r0 << 8
            short r0 = (short) r0
            byte r1 = r3[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r0 = r0 | r1
            short r0 = (short) r0
            int r4 = r4 + -1
            goto L_0x001c
        L_0x002a:
            r4 = r0
        L_0x002b:
            return r4
        L_0x002c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "byte array size > 2 !"
            r3.<init>(r4)
            throw r3
        L_0x0034:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "byte array is null!"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.audioprocess.BytesTransUtil.a(byte[], boolean):short");
    }

    public int b(byte[] bArr, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (bArr.length <= 4) {
            int i = 0;
            if (z) {
                byte b2 = 0;
                while (i < bArr.length) {
                    b2 = (b2 << 8) | (bArr[i] & 255);
                    i++;
                }
                return b2;
            }
            for (int length = bArr.length - 1; length >= 0; length--) {
                i = (i << 8) | (bArr[length] & 255);
            }
            return i;
        } else {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
    }

    public long c(byte[] bArr, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (bArr.length <= 8) {
            long j = 0;
            if (z) {
                for (byte b2 : bArr) {
                    j = (j << 8) | ((long) (b2 & 255));
                }
            } else {
                for (int length = bArr.length - 1; length >= 0; length--) {
                    j = (j << 8) | ((long) (bArr[length] & 255));
                }
            }
            return j;
        } else {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
    }

    public byte[] a(int i) {
        return a(i, b());
    }

    public byte[] a(short s) {
        return a(s, b());
    }

    public byte[] a(long j) {
        return a(j, b());
    }

    public int a(byte[] bArr) {
        return b(bArr, b());
    }

    public short b(byte[] bArr) {
        return a(bArr, b());
    }

    public long c(byte[] bArr) {
        return c(bArr, b());
    }

    public short[] d(byte[] bArr) {
        short[] sArr = new short[(bArr.length / 2)];
        for (int i = 0; i < sArr.length; i++) {
            byte[] bArr2 = new byte[2];
            for (int i2 = 0; i2 < 2; i2++) {
                bArr2[i2] = bArr[(i * 2) + i2];
            }
            sArr[i] = b(bArr2);
        }
        return sArr;
    }

    public byte[] a(short[] sArr) {
        byte[] bArr = new byte[(sArr.length * 2)];
        for (int i = 0; i < sArr.length; i++) {
            byte[] a2 = a(sArr[i]);
            for (int i2 = 0; i2 < 2; i2++) {
                bArr[(i * 2) + i2] = a2[i2];
            }
        }
        return bArr;
    }

    public int[] e(byte[] bArr) {
        int[] iArr = new int[(bArr.length / 4)];
        for (int i = 0; i < iArr.length; i++) {
            byte[] bArr2 = new byte[4];
            for (int i2 = 0; i2 < 4; i2++) {
                bArr2[i2] = bArr[(i * 4) + i2];
            }
            iArr[i] = a(bArr2);
            PrintStream printStream = System.out;
            printStream.println("2out->" + iArr[i]);
        }
        return iArr;
    }

    public byte[] a(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        for (int i = 0; i < iArr.length; i++) {
            byte[] a2 = a(iArr[i]);
            PrintStream printStream = System.out;
            printStream.println("1out->" + iArr[i]);
            for (int i2 = 0; i2 < 4; i2++) {
                bArr[(i * 4) + i2] = a2[i2];
            }
        }
        return bArr;
    }

    public long[] f(byte[] bArr) {
        long[] jArr = new long[(bArr.length / 8)];
        for (int i = 0; i < jArr.length; i++) {
            byte[] bArr2 = new byte[8];
            for (int i2 = 0; i2 < 8; i2++) {
                bArr2[i2] = bArr[(i * 8) + i2];
            }
            jArr[i] = c(bArr2);
        }
        return jArr;
    }

    public byte[] a(long[] jArr) {
        byte[] bArr = new byte[(jArr.length * 8)];
        for (int i = 0; i < jArr.length; i++) {
            byte[] a2 = a(jArr[i]);
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[(i * 8) + i2] = a2[i2];
            }
        }
        return bArr;
    }
}