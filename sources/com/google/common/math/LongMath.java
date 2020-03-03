package com.google.common.math;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.tencent.smtt.sdk.TbsListener;
import java.math.RoundingMode;
import org.apache.http.HttpStatus;

@GwtCompatible(emulated = true)
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, OlympusCameraSettingsMakernoteDirectory.Q, 684, HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, OlympusImageProcessingMakernoteDirectory.I, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @GwtIncompatible("TODO")
    @VisibleForTesting
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, 18, 18, 18, 18, 17, 17, 17, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    @GwtIncompatible("TODO")
    @VisibleForTesting
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    static boolean fitsInInt(long j) {
        return ((long) ((int) j)) == j;
    }

    public static boolean isPowerOfTwo(long j) {
        boolean z = false;
        boolean z2 = j > 0;
        if ((j & (j - 1)) == 0) {
            z = true;
        }
        return z2 & z;
    }

    @VisibleForTesting
    static int lessThanBranchFree(long j, long j2) {
        return (int) ((((j - j2) ^ -1) ^ -1) >>> 63);
    }

    public static long mean(long j, long j2) {
        return (j & j2) + ((j ^ j2) >> 1);
    }

    /* renamed from: com.google.common.math.LongMath$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.math.RoundingMode[] r0 = java.math.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$math$RoundingMode = r0
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UNNECESSARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001f }
                java.math.RoundingMode r1 = java.math.RoundingMode.DOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x002a }
                java.math.RoundingMode r1 = java.math.RoundingMode.FLOOR     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.math.RoundingMode r1 = java.math.RoundingMode.CEILING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x004b }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_UP     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0062 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.AnonymousClass1.<clinit>():void");
        }
    }

    public static int log2(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
                return (63 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, j);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j);
    }

    @GwtIncompatible("TODO")
    public static int log10(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j);
        int log10Floor = log10Floor(j);
        long j2 = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j == j2);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return log10Floor + lessThanBranchFree(j2, j);
            case 6:
            case 7:
            case 8:
                return log10Floor + lessThanBranchFree(halfPowersOf10[log10Floor], j);
            default:
                throw new AssertionError();
        }
        return log10Floor;
    }

    @GwtIncompatible("TODO")
    static int log10Floor(long j) {
        byte b = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j)];
        return b - lessThanBranchFree(j, powersOf10[b]);
    }

    @GwtIncompatible("TODO")
    public static long pow(long j, int i) {
        MathPreconditions.checkNonNegative(Constants.XMLNode.KEY_EXPONENT, i);
        if (-2 > j || j > 2) {
            long j2 = j;
            long j3 = 1;
            while (true) {
                switch (i) {
                    case 0:
                        return j3;
                    case 1:
                        return j3 * j2;
                    default:
                        j3 *= (i & 1) == 0 ? 1 : j2;
                        j2 *= j2;
                        i >>= 1;
                }
            }
        } else {
            switch ((int) j) {
                case -2:
                    if (i < 64) {
                        return (i & 1) == 0 ? 1 << i : -(1 << i);
                    }
                    return 0;
                case -1:
                    return (i & 1) == 0 ? 1 : -1;
                case 0:
                    return i == 0 ? 1 : 0;
                case 1:
                    return 1;
                case 2:
                    if (i < 64) {
                        return 1 << i;
                    }
                    return 0;
                default:
                    throw new AssertionError();
            }
        }
    }

    @GwtIncompatible("TODO")
    public static long sqrt(long j, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", j);
        if (fitsInInt(j)) {
            return (long) IntMath.sqrt((int) j, roundingMode);
        }
        long sqrt = (long) Math.sqrt((double) j);
        long j2 = sqrt * sqrt;
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (j2 == j) {
                    z = true;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return sqrt;
            case 2:
            case 3:
                return j < j2 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j > j2 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                if (j < j2) {
                    z = true;
                }
                long j3 = sqrt - (z ? 1 : 0);
                return j3 + ((long) lessThanBranchFree((j3 * j3) + j3, j));
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        if (r2 > 0) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r8 > 0) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        if (r8 < 0) goto L_0x0066;
     */
    @com.google.common.annotations.GwtIncompatible("TODO")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long divide(long r8, long r10, java.math.RoundingMode r12) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r12)
            long r0 = r8 / r10
            long r2 = r10 * r0
            long r2 = r8 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0010
            return r0
        L_0x0010:
            long r8 = r8 ^ r10
            r6 = 63
            long r8 = r8 >> r6
            int r8 = (int) r8
            r9 = 1
            r8 = r8 | r9
            int[] r6 = com.google.common.math.LongMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r7 = r12.ordinal()
            r6 = r6[r7]
            r7 = 0
            switch(r6) {
                case 1: goto L_0x005c;
                case 2: goto L_0x0065;
                case 3: goto L_0x0059;
                case 4: goto L_0x0066;
                case 5: goto L_0x0056;
                case 6: goto L_0x0029;
                case 7: goto L_0x0029;
                case 8: goto L_0x0029;
                default: goto L_0x0023;
            }
        L_0x0023:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x0029:
            long r2 = java.lang.Math.abs(r2)
            long r10 = java.lang.Math.abs(r10)
            long r10 = r10 - r2
            long r2 = r2 - r10
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0051
            java.math.RoundingMode r10 = java.math.RoundingMode.HALF_UP
            if (r12 != r10) goto L_0x003d
            r10 = 1
            goto L_0x003e
        L_0x003d:
            r10 = 0
        L_0x003e:
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_EVEN
            if (r12 != r11) goto L_0x0044
            r11 = 1
            goto L_0x0045
        L_0x0044:
            r11 = 0
        L_0x0045:
            r2 = 1
            long r2 = r2 & r0
            int r12 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r9 = 0
        L_0x004e:
            r9 = r9 & r11
            r9 = r9 | r10
            goto L_0x0066
        L_0x0051:
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 <= 0) goto L_0x0065
            goto L_0x0066
        L_0x0056:
            if (r8 <= 0) goto L_0x0065
            goto L_0x0066
        L_0x0059:
            if (r8 >= 0) goto L_0x0065
            goto L_0x0066
        L_0x005c:
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r9 = 0
        L_0x0062:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r9)
        L_0x0065:
            r9 = 0
        L_0x0066:
            if (r9 == 0) goto L_0x006a
            long r8 = (long) r8
            long r0 = r0 + r8
        L_0x006a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.divide(long, long, java.math.RoundingMode):long");
    }

    @GwtIncompatible("TODO")
    public static int mod(long j, int i) {
        return (int) mod(j, (long) i);
    }

    @GwtIncompatible("TODO")
    public static long mod(long j, long j2) {
        if (j2 > 0) {
            long j3 = j % j2;
            return j3 >= 0 ? j3 : j3 + j2;
        }
        throw new ArithmeticException("Modulus must be positive");
    }

    public static long gcd(long j, long j2) {
        MathPreconditions.checkNonNegative("a", j);
        MathPreconditions.checkNonNegative("b", j2);
        if (j == 0) {
            return j2;
        }
        if (j2 == 0) {
            return j;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j3 = j >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros2;
        while (j3 != j4) {
            long j5 = j3 - j4;
            long j6 = (j5 >> 63) & j5;
            long j7 = (j5 - j6) - j6;
            j4 += j6;
            j3 = j7 >> Long.numberOfTrailingZeros(j7);
        }
        return j3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    @GwtIncompatible("TODO")
    public static long checkedAdd(long j, long j2) {
        long j3 = j + j2;
        int i = ((j2 ^ j) > 0 ? 1 : ((j2 ^ j) == 0 ? 0 : -1));
        boolean z = false;
        boolean z2 = i < 0;
        if ((j ^ j3) >= 0) {
            z = true;
        }
        MathPreconditions.checkNoOverflow(z2 | z);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedSubtract(long j, long j2) {
        long j3 = j - j2;
        int i = ((j2 ^ j) > 0 ? 1 : ((j2 ^ j) == 0 ? 0 : -1));
        boolean z = false;
        boolean z2 = i >= 0;
        if ((j ^ j3) >= 0) {
            z = true;
        }
        MathPreconditions.checkNoOverflow(z2 | z);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedMultiply(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(j ^ -1) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(-1 ^ j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        boolean z = false;
        MathPreconditions.checkNoOverflow(numberOfLeadingZeros >= 64);
        MathPreconditions.checkNoOverflow((j >= 0) | (j2 != Long.MIN_VALUE));
        long j3 = j * j2;
        if (j == 0 || j3 / j == j2) {
            z = true;
        }
        MathPreconditions.checkNoOverflow(z);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedPow(long j, int i) {
        MathPreconditions.checkNonNegative(Constants.XMLNode.KEY_EXPONENT, i);
        boolean z = false;
        long j2 = 1;
        if ((j >= -2) && (j <= 2)) {
            switch ((int) j) {
                case -2:
                    if (i < 64) {
                        z = true;
                    }
                    MathPreconditions.checkNoOverflow(z);
                    return (i & 1) == 0 ? 1 << i : -1 << i;
                case -1:
                    if ((i & 1) == 0) {
                        return 1;
                    }
                    return -1;
                case 0:
                    if (i == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    if (i < 63) {
                        z = true;
                    }
                    MathPreconditions.checkNoOverflow(z);
                    return 1 << i;
                default:
                    throw new AssertionError();
            }
        } else {
            while (true) {
                switch (i) {
                    case 0:
                        return j2;
                    case 1:
                        return checkedMultiply(j2, j);
                    default:
                        if ((i & 1) != 0) {
                            j2 = checkedMultiply(j2, j);
                        }
                        i >>= 1;
                        if (i > 0) {
                            MathPreconditions.checkNoOverflow(j <= FLOOR_SQRT_MAX_LONG);
                            j *= j;
                        }
                }
            }
        }
    }

    @GwtIncompatible("TODO")
    public static long factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        if (i < factorials.length) {
            return factorials[i];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int i, int i2) {
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", i2);
        int i3 = 2;
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", Integer.valueOf(i2), Integer.valueOf(i));
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return (long) i;
            default:
                if (i < factorials.length) {
                    return factorials[i] / (factorials[i2] * factorials[i - i2]);
                }
                if (i2 >= biggestBinomials.length || i > biggestBinomials[i2]) {
                    return Long.MAX_VALUE;
                }
                if (i2 >= biggestSimpleBinomials.length || i > biggestSimpleBinomials[i2]) {
                    long j = (long) i;
                    int log2 = log2(j, RoundingMode.CEILING);
                    long j2 = 1;
                    long j3 = 1;
                    long j4 = j;
                    int i4 = i - 1;
                    int i5 = log2;
                    while (i3 <= i2) {
                        i5 += log2;
                        if (i5 < 63) {
                            j4 *= (long) i4;
                            j3 *= (long) i3;
                        } else {
                            long multiplyFraction = multiplyFraction(j2, j4, j3);
                            i5 = log2;
                            j3 = (long) i3;
                            j4 = (long) i4;
                            j2 = multiplyFraction;
                        }
                        i3++;
                        i4--;
                    }
                    return multiplyFraction(j2, j4, j3);
                }
                int i6 = i - 1;
                long j5 = (long) i;
                while (i3 <= i2) {
                    j5 = (j5 * ((long) i6)) / ((long) i3);
                    i6--;
                    i3++;
                }
                return j5;
        }
    }

    static long multiplyFraction(long j, long j2, long j3) {
        if (j == 1) {
            return j2 / j3;
        }
        long gcd = gcd(j, j3);
        return (j / gcd) * (j2 / (j3 / gcd));
    }

    private LongMath() {
    }
}
