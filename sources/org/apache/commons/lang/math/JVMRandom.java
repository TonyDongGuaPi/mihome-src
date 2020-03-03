package org.apache.commons.lang.math;

import java.util.Random;

public final class JVMRandom extends Random {

    /* renamed from: a  reason: collision with root package name */
    private static final Random f3392a = new Random();
    private static final long serialVersionUID = 1;
    private boolean constructed;

    private static int a(long j) {
        int i = 0;
        long j2 = j;
        while (j >= 0) {
            if (j2 == 0) {
                return i;
            }
            i++;
            j <<= 1;
            j2 >>= 1;
        }
        return 64 - i;
    }

    public JVMRandom() {
        this.constructed = false;
        this.constructed = true;
    }

    public synchronized void setSeed(long j) {
        if (this.constructed) {
            throw new UnsupportedOperationException();
        }
    }

    public synchronized double nextGaussian() {
        throw new UnsupportedOperationException();
    }

    public void nextBytes(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public int nextInt() {
        return nextInt(Integer.MAX_VALUE);
    }

    public int nextInt(int i) {
        return f3392a.nextInt(i);
    }

    public long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    public static long nextLong(long j) {
        long a2;
        long j2;
        if (j <= 0) {
            throw new IllegalArgumentException("Upper bound for nextInt must be positive");
        } else if (((-j) & j) == j) {
            return a() >> (63 - a(j - 1));
        } else {
            do {
                a2 = a();
                j2 = a2 % j;
            } while ((a2 - j2) + (j - 1) < 0);
            return j2;
        }
    }

    public boolean nextBoolean() {
        return f3392a.nextBoolean();
    }

    public float nextFloat() {
        return f3392a.nextFloat();
    }

    public double nextDouble() {
        return f3392a.nextDouble();
    }

    private static long a() {
        return f3392a.nextLong() & Long.MAX_VALUE;
    }
}
