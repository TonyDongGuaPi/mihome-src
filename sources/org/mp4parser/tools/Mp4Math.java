package org.mp4parser.tools;

public class Mp4Math {
    public static long a(long j, long j2) {
        while (true) {
            long j3 = j;
            j = j2;
            long j4 = j3;
            if (j <= 0) {
                return j4;
            }
            j2 = j4 % j;
        }
    }

    public static int a(int i, int i2) {
        while (true) {
            int i3 = i2;
            int i4 = i;
            i = i3;
            if (i <= 0) {
                return i4;
            }
            i2 = i4 % i;
        }
    }

    public static long b(long j, long j2) {
        return j * (j2 / a(j, j2));
    }

    public static long a(long[] jArr) {
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            j = b(j, jArr[i]);
        }
        return j;
    }

    public static int b(int i, int i2) {
        return i * (i2 / a(i, i2));
    }
}
