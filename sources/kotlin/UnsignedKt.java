package kotlin;

import cn.com.fmsh.communication.core.MessageHead;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0001\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0007\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0001\u001a\"\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\"\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000f\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"uintCompare", "", "v1", "v2", "uintDivide", "Lkotlin/UInt;", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "ulongCompare", "", "ulongDivide", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToString", "", "v", "base", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "UnsignedKt")
public final class UnsignedKt {
    @PublishedApi
    public static final int a(long j, long j2) {
        return ((j ^ Long.MIN_VALUE) > (j2 ^ Long.MIN_VALUE) ? 1 : ((j ^ Long.MIN_VALUE) == (j2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @PublishedApi
    public static final int a(int i, int i2) {
        return Intrinsics.a(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int b(int i, int i2) {
        return UInt.b((int) ((((long) i) & MessageHead.SERIAL_MAK) / (((long) i2) & MessageHead.SERIAL_MAK)));
    }

    @PublishedApi
    public static final int c(int i, int i2) {
        return UInt.b((int) ((((long) i) & MessageHead.SERIAL_MAK) % (((long) i2) & MessageHead.SERIAL_MAK)));
    }

    @PublishedApi
    public static final long b(long j, long j2) {
        if (j2 < 0) {
            return a(j, j2) < 0 ? ULong.b(0) : ULong.b(1);
        }
        if (j >= 0) {
            return ULong.b(j / j2);
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (a(ULong.b(j - (j3 * j2)), ULong.b(j2)) < 0) {
            i = 0;
        }
        return ULong.b(j3 + ((long) i));
    }

    @PublishedApi
    public static final long c(long j, long j2) {
        if (j2 < 0) {
            return a(j, j2) < 0 ? j : ULong.b(j - j2);
        }
        if (j >= 0) {
            return ULong.b(j % j2);
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (a(ULong.b(j3), ULong.b(j2)) < 0) {
            j2 = 0;
        }
        return ULong.b(j3 - j2);
    }

    @NotNull
    public static final String a(long j) {
        return a(j, 10);
    }

    @NotNull
    public static final String a(long j, int i) {
        if (j >= 0) {
            String l = Long.toString(j, CharsKt.a(i));
            Intrinsics.b(l, "java.lang.Long.toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = (long) i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        String l2 = Long.toString(j3, CharsKt.a(i));
        Intrinsics.b(l2, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l2);
        String l3 = Long.toString(j4, CharsKt.a(i));
        Intrinsics.b(l3, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l3);
        return sb.toString();
    }
}
