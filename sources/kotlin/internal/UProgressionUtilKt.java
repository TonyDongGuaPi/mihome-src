package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
public final class UProgressionUtilKt {
    private static final int b(int i, int i2, int i3) {
        int c = UnsignedKt.c(i, i3);
        int c2 = UnsignedKt.c(i2, i3);
        return UInt.b(UnsignedKt.a(c, c2) >= 0 ? c - c2 : UInt.b(c - c2) + i3);
    }

    private static final long b(long j, long j2, long j3) {
        long c = UnsignedKt.c(j, j3);
        long c2 = UnsignedKt.c(j2, j3);
        return ULong.b(UnsignedKt.a(c, c2) >= 0 ? c - c2 : ULong.b(c - c2) + j3);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final int a(int i, int i2, int i3) {
        if (i3 > 0) {
            if (UnsignedKt.a(i, i2) >= 0) {
                return i2;
            }
            return UInt.b(i2 - b(i2, i, UInt.b(i3)));
        } else if (i3 < 0) {
            return UnsignedKt.a(i, i2) <= 0 ? i2 : UInt.b(i2 + b(i, i2, UInt.b(-i3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final long a(long j, long j2, long j3) {
        if (j3 > 0) {
            if (UnsignedKt.a(j, j2) >= 0) {
                return j2;
            }
            return ULong.b(j2 - b(j2, j, ULong.b(j3)));
        } else if (j3 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else if (UnsignedKt.a(j, j2) <= 0) {
            return j2;
        } else {
            return ULong.b(j2 + b(j, j2, ULong.b(-j3)));
        }
    }
}
