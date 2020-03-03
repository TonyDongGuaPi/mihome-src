package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import java.math.BigInteger;
import javax.annotation.Nullable;

@GwtCompatible
final class MathPreconditions {
    static int checkPositive(@Nullable String str, int i) {
        if (i > 0) {
            return i;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        StringBuilder sb = new StringBuilder(valueOf.length() + 26);
        sb.append(valueOf);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static long checkPositive(@Nullable String str, long j) {
        if (j > 0) {
            return j;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        StringBuilder sb = new StringBuilder(valueOf.length() + 35);
        sb.append(valueOf);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static BigInteger checkPositive(@Nullable String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        String valueOf2 = String.valueOf(String.valueOf(bigInteger));
        StringBuilder sb = new StringBuilder(valueOf.length() + 15 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" (");
        sb.append(valueOf2);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static int checkNonNegative(@Nullable String str, int i) {
        if (i >= 0) {
            return i;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        StringBuilder sb = new StringBuilder(valueOf.length() + 27);
        sb.append(valueOf);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static long checkNonNegative(@Nullable String str, long j) {
        if (j >= 0) {
            return j;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        StringBuilder sb = new StringBuilder(valueOf.length() + 36);
        sb.append(valueOf);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static BigInteger checkNonNegative(@Nullable String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        String valueOf2 = String.valueOf(String.valueOf(bigInteger));
        StringBuilder sb = new StringBuilder(valueOf.length() + 16 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" (");
        sb.append(valueOf2);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static double checkNonNegative(@Nullable String str, double d) {
        if (d >= 0.0d) {
            return d;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        StringBuilder sb = new StringBuilder(valueOf.length() + 40);
        sb.append(valueOf);
        sb.append(" (");
        sb.append(d);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    static void checkRoundingUnnecessary(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static void checkInRange(boolean z) {
        if (!z) {
            throw new ArithmeticException("not in range");
        }
    }

    static void checkNoOverflow(boolean z) {
        if (!z) {
            throw new ArithmeticException("overflow");
        }
    }

    private MathPreconditions() {
    }
}
