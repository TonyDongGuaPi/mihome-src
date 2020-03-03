package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

public class TarUtils {

    /* renamed from: a  reason: collision with root package name */
    static final ZipEncoding f3243a = ZipEncodingHelper.a((String) null);
    static final ZipEncoding b = new ZipEncoding() {
        public boolean a(String str) {
            return true;
        }

        public ByteBuffer b(String str) {
            int length = str.length();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) str.charAt(i);
            }
            return ByteBuffer.wrap(bArr);
        }

        public String a(byte[] bArr) {
            StringBuilder sb = new StringBuilder(bArr.length);
            for (byte b : bArr) {
                if (b == 0) {
                    break;
                }
                sb.append((char) (b & 255));
            }
            return sb.toString();
        }
    };
    private static final int c = 255;

    private TarUtils() {
    }

    public static long a(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i2 >= 2) {
            long j = 0;
            if (bArr[i] == 0) {
                return 0;
            }
            int i4 = i;
            while (i4 < i3 && bArr[i4] == 32) {
                i4++;
            }
            byte b2 = bArr[i3 - 1];
            while (i4 < i3 && (b2 == 0 || b2 == 32)) {
                i3--;
                b2 = bArr[i3 - 1];
            }
            while (i4 < i3) {
                byte b3 = bArr[i4];
                if (b3 < 48 || b3 > 55) {
                    throw new IllegalArgumentException(a(bArr, i, i2, i4, b3));
                }
                j = (j << 3) + ((long) (b3 - 48));
                i4++;
            }
            return j;
        }
        throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
    }

    public static long b(byte[] bArr, int i, int i2) {
        if ((bArr[i] & 128) == 0) {
            return a(bArr, i, i2);
        }
        boolean z = bArr[i] == -1;
        if (i2 < 9) {
            return a(bArr, i, i2, z);
        }
        return b(bArr, i, i2, z);
    }

    private static long a(byte[] bArr, int i, int i2, boolean z) {
        if (i2 < 9) {
            long j = 0;
            for (int i3 = 1; i3 < i2; i3++) {
                j = (j << 8) + ((long) (bArr[i + i3] & 255));
            }
            if (z) {
                double d = (double) (i2 - 1);
                Double.isNaN(d);
                j = (j - 1) ^ (((long) Math.pow(2.0d, d * 8.0d)) - 1);
            }
            return z ? -j : j;
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number" + " exceeds maximum signed long" + " value");
    }

    private static long b(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 1, bArr2, 0, i3);
        BigInteger bigInteger = new BigInteger(bArr2);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1)).not();
        }
        if (bigInteger.bitLength() <= 63) {
            return z ? -bigInteger.longValue() : bigInteger.longValue();
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number" + " exceeds maximum signed long" + " value");
    }

    public static boolean a(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    private static String a(byte[] bArr, int i, int i2, int i3, byte b2) {
        String replaceAll = new String(bArr, i, i2).replaceAll("\u0000", "{NUL}");
        return "Invalid byte " + b2 + " at offset " + (i3 - i) + " in '" + replaceAll + "' len=" + i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        return a(r1, r2, r3, b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(byte[] r1, int r2, int r3) {
        /*
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = f3243a     // Catch:{ IOException -> 0x0007 }
            java.lang.String r0 = a((byte[]) r1, (int) r2, (int) r3, (org.apache.commons.compress.archivers.zip.ZipEncoding) r0)     // Catch:{ IOException -> 0x0007 }
            return r0
        L_0x0007:
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = b     // Catch:{ IOException -> 0x000e }
            java.lang.String r1 = a((byte[]) r1, (int) r2, (int) r3, (org.apache.commons.compress.archivers.zip.ZipEncoding) r0)     // Catch:{ IOException -> 0x000e }
            return r1
        L_0x000e:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.c(byte[], int, int):java.lang.String");
    }

    public static String a(byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        while (i2 > 0 && bArr[(i + i2) - 1] == 0) {
            i2--;
        }
        if (i2 <= 0) {
            return "";
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return zipEncoding.a(bArr2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        return a(r1, r2, r3, r4, b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.lang.String r1, byte[] r2, int r3, int r4) {
        /*
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = f3243a     // Catch:{ IOException -> 0x0007 }
            int r0 = a((java.lang.String) r1, (byte[]) r2, (int) r3, (int) r4, (org.apache.commons.compress.archivers.zip.ZipEncoding) r0)     // Catch:{ IOException -> 0x0007 }
            return r0
        L_0x0007:
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = b     // Catch:{ IOException -> 0x000e }
            int r1 = a((java.lang.String) r1, (byte[]) r2, (int) r3, (int) r4, (org.apache.commons.compress.archivers.zip.ZipEncoding) r0)     // Catch:{ IOException -> 0x000e }
            return r1
        L_0x000e:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.a(java.lang.String, byte[], int, int):int");
    }

    public static int a(String str, byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int length = str.length();
        ByteBuffer b2 = zipEncoding.b(str);
        while (b2.limit() > i2 && length > 0) {
            length--;
            b2 = zipEncoding.b(str.substring(0, length));
        }
        int limit = b2.limit() - b2.position();
        System.arraycopy(b2.array(), b2.arrayOffset(), bArr, i, limit);
        while (limit < i2) {
            bArr[i + limit] = 0;
            limit++;
        }
        return i + i2;
    }

    public static void a(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2 - 1;
        if (j == 0) {
            i3 = i4 - 1;
            bArr[i4 + i] = 48;
        } else {
            long j2 = j;
            while (i4 >= 0 && j2 != 0) {
                bArr[i + i4] = (byte) (((byte) ((int) (7 & j2))) + 48);
                j2 >>>= 3;
                i4--;
            }
            if (j2 == 0) {
                i3 = i4;
            } else {
                throw new IllegalArgumentException(j + "=" + Long.toOctalString(j) + " will not fit in octal number buffer of length " + i2);
            }
        }
        while (i3 >= 0) {
            bArr[i + i3] = 48;
            i3--;
        }
    }

    public static int b(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        a(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        bArr[i3 + 1 + i] = 0;
        return i + i2;
    }

    public static int c(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        a(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        return i + i2;
    }

    public static int d(long j, byte[] bArr, int i, int i2) {
        long j2 = i2 == 8 ? TarConstants.n : TarConstants.r;
        boolean z = j < 0;
        if (!z && j <= j2) {
            return c(j, bArr, i, i2);
        }
        if (i2 < 9) {
            a(j, bArr, i, i2, z);
        }
        b(j, bArr, i, i2, z);
        bArr[i] = (byte) (z ? 255 : 128);
        return i + i2;
    }

    private static void a(long j, byte[] bArr, int i, int i2, boolean z) {
        int i3 = (i2 - 1) * 8;
        long j2 = 1 << i3;
        long abs = Math.abs(j);
        if (abs < j2) {
            if (z) {
                abs = ((abs ^ (j2 - 1)) | ((long) (255 << i3))) + 1;
            }
            for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
                bArr[i4] = (byte) ((int) abs);
                abs >>= 8;
            }
            return;
        }
        throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
    }

    private static void b(long j, byte[] bArr, int i, int i2, boolean z) {
        byte[] byteArray = BigInteger.valueOf(j).toByteArray();
        int length = byteArray.length;
        int i3 = (i2 + i) - length;
        int i4 = 0;
        System.arraycopy(byteArray, 0, bArr, i3, length);
        if (z) {
            i4 = 255;
        }
        byte b2 = (byte) i4;
        while (true) {
            i++;
            if (i < i3) {
                bArr[i] = b2;
            } else {
                return;
            }
        }
    }

    public static int e(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        a(j, bArr, i, i3);
        bArr[i3 + i] = 0;
        bArr[i3 + 1 + i] = 32;
        return i + i2;
    }

    public static long a(byte[] bArr) {
        long j = 0;
        for (byte b2 : bArr) {
            j += (long) (b2 & 255);
        }
        return j;
    }

    public static boolean b(byte[] bArr) {
        long a2 = a(bArr, 148, 8);
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            if (148 <= i && i < 156) {
                b2 = 32;
            }
            j2 += (long) (b2 & 255);
            j += (long) b2;
        }
        if (a2 == j2 || a2 == j) {
            return true;
        }
        return false;
    }
}
