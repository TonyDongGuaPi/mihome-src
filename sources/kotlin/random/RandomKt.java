package kotlin.random;

import com.xiaomi.infra.galaxy.fds.Common;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\r\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0014\u0010\u0010\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a\u0014\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0000¨\u0006\u0014"}, d2 = {"Random", "Lkotlin/random/Random;", "seed", "", "", "boundsErrorMessage", "", "from", "", "until", "checkRangeBounds", "", "", "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "takeUpperBits", "bitCount", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
public final class RandomKt {
    public static final int a(int i, int i2) {
        return (i >>> (32 - i2)) & ((-i2) >> 31);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random a(int i) {
        return new XorWowRandom(i, i >> 31);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final Random a(long j) {
        return new XorWowRandom((int) j, (int) (j >> 32));
    }

    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull Random random, @NotNull IntRange intRange) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        if (intRange.e()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + intRange);
        } else if (intRange.b() < Integer.MAX_VALUE) {
            return random.a(intRange.a(), intRange.b() + 1);
        } else {
            if (intRange.a() > Integer.MIN_VALUE) {
                return random.a(intRange.a() - 1, intRange.b()) + 1;
            }
            return random.b();
        }
    }

    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull Random random, @NotNull LongRange longRange) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(longRange, Common.v);
        if (longRange.e()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + longRange);
        } else if (longRange.b() < Long.MAX_VALUE) {
            return random.a(longRange.g().longValue(), longRange.i().longValue() + 1);
        } else {
            if (longRange.g().longValue() > Long.MIN_VALUE) {
                return random.a(longRange.g().longValue() - 1, longRange.i().longValue()) + 1;
            }
            return random.c();
        }
    }

    public static final void b(int i, int i2) {
        if (!(i2 > i)) {
            throw new IllegalArgumentException(a((Object) Integer.valueOf(i), (Object) Integer.valueOf(i2)).toString());
        }
    }

    public static final void a(long j, long j2) {
        if (!(j2 > j)) {
            throw new IllegalArgumentException(a((Object) Long.valueOf(j), (Object) Long.valueOf(j2)).toString());
        }
    }

    public static final void a(double d, double d2) {
        if (!(d2 > d)) {
            throw new IllegalArgumentException(a((Object) Double.valueOf(d), (Object) Double.valueOf(d2)).toString());
        }
    }

    @NotNull
    public static final String a(@NotNull Object obj, @NotNull Object obj2) {
        Intrinsics.f(obj, "from");
        Intrinsics.f(obj2, "until");
        return "Random range is empty: [" + obj + ", " + obj2 + ").";
    }
}