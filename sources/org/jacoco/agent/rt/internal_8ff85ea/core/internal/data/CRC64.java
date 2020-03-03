package org.jacoco.agent.rt.internal_8ff85ea.core.internal.data;

public final class CRC64 {

    /* renamed from: a  reason: collision with root package name */
    private static final long f3619a = -2882303761517117440L;
    private static final long[] b = new long[256];

    static {
        for (int i = 0; i < 256; i++) {
            long j = (long) i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (j & 1) == 1 ? (j >>> 1) ^ f3619a : j >>> 1;
            }
            b[i] = j;
        }
    }

    public static long a(byte[] bArr) {
        long j = 0;
        for (byte b2 : bArr) {
            j = (j >>> 8) ^ b[(b2 ^ ((int) j)) & 255];
        }
        return j;
    }

    private CRC64() {
    }
}
