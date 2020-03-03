package kotlin.random;

import cn.com.fmsh.communication.core.MessageHead;
import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.infra.galaxy.fds.Common;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
public final class URandomKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull Random random) {
        Intrinsics.f(random, "receiver$0");
        return UInt.b(random.b());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull Random random, int i) {
        Intrinsics.f(random, "receiver$0");
        return a(random, 0, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull Random random, int i, int i2) {
        Intrinsics.f(random, "receiver$0");
        a(i, i2);
        return UInt.b(random.a(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull Random random, @NotNull UIntRange uIntRange) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(uIntRange, Common.v);
        if (uIntRange.e()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + uIntRange);
        } else if (UnsignedKt.a(uIntRange.b(), -1) < 0) {
            return a(random, uIntRange.a(), UInt.b(uIntRange.b() + 1));
        } else {
            if (UnsignedKt.a(uIntRange.a(), 0) > 0) {
                return UInt.b(a(random, UInt.b(uIntRange.a() - 1), uIntRange.b()) + 1);
            }
            return a(random);
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long b(@NotNull Random random) {
        Intrinsics.f(random, "receiver$0");
        return ULong.b(random.c());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull Random random, long j) {
        Intrinsics.f(random, "receiver$0");
        return a(random, 0, j);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull Random random, long j, long j2) {
        Intrinsics.f(random, "receiver$0");
        a(j, j2);
        return ULong.b(random.a(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull Random random, @NotNull ULongRange uLongRange) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(uLongRange, Common.v);
        if (uLongRange.e()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + uLongRange);
        } else if (UnsignedKt.a(uLongRange.b(), -1) < 0) {
            return a(random, uLongRange.a(), ULong.b(uLongRange.b() + ULong.b(MessageHead.SERIAL_MAK & ((long) 1))));
        } else {
            if (UnsignedKt.a(uLongRange.a(), 0) <= 0) {
                return b(random);
            }
            long a2 = uLongRange.a();
            long j = MessageHead.SERIAL_MAK & ((long) 1);
            return ULong.b(a(random, ULong.b(a2 - ULong.b(j)), uLongRange.b()) + ULong.b(j));
        }
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] a(@NotNull Random random, @NotNull byte[] bArr) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(bArr, EventData.d);
        random.a(bArr);
        return bArr;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] b(@NotNull Random random, int i) {
        Intrinsics.f(random, "receiver$0");
        return UByteArray.d(random.c(i));
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ byte[] a(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.a(bArr);
        }
        return a(random, bArr, i, i2);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] a(@NotNull Random random, @NotNull byte[] bArr, int i, int i2) {
        Intrinsics.f(random, "receiver$0");
        Intrinsics.f(bArr, EventData.d);
        random.a(bArr, i, i2);
        return bArr;
    }

    @ExperimentalUnsignedTypes
    public static final void a(int i, int i2) {
        if (!(UnsignedKt.a(i2, i) > 0)) {
            throw new IllegalArgumentException(RandomKt.a((Object) UInt.c(i), (Object) UInt.c(i2)).toString());
        }
    }

    @ExperimentalUnsignedTypes
    public static final void a(long j, long j2) {
        if (!(UnsignedKt.a(j2, j) > 0)) {
            throw new IllegalArgumentException(RandomKt.a((Object) ULong.c(j), (Object) ULong.c(j2)).toString());
        }
    }
}
