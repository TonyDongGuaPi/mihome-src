package kotlin.ranges;

import cn.com.fmsh.communication.core.MessageHead;
import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.ULongProgression;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\nø\u0001\u0000¢\u0006\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0007H\nø\u0001\u0000¢\u0006\u0002\b\b\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0004ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\t\u001a\u00020\u0011*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014H\u0004ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0015\u0010\u0017\u001a\u00020\u0007*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u001c\u0010\u0017\u001a\u00020\u0007*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\f\u0010\u001d\u001a\u00020\n*\u00020\nH\u0007\u001a\f\u0010\u001d\u001a\u00020\u0011*\u00020\u0011H\u0007\u001a\u0015\u0010\u001e\u001a\u00020\n*\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0004\u001a\u0015\u0010\u001e\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001e\u001a\u00020 H\u0004\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0004ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a\u001f\u0010!\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0004ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u001f\u0010!\u001a\u00020\u0002*\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014H\u0004ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"contains", "", "Lkotlin/ranges/UIntRange;", "element", "Lkotlin/UInt;", "contains-biwQdVI", "Lkotlin/ranges/ULongRange;", "Lkotlin/ULong;", "contains-GYNo2lE", "downTo", "Lkotlin/ranges/UIntProgression;", "Lkotlin/UByte;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "Lkotlin/UShort;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/ranges/URangesKt")
class URangesKt___URangesKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int a(@NotNull UIntRange uIntRange) {
        return URangesKt.a(uIntRange, (Random) Random.b);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long a(@NotNull ULongRange uLongRange) {
        return URangesKt.a(uLongRange, (Random) Random.b);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.f(uIntRange, "receiver$0");
        Intrinsics.f(random, "random");
        try {
            return URandomKt.a(random, uIntRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.f(uLongRange, "receiver$0");
        Intrinsics.f(random, "random");
        try {
            return URandomKt.a(random, uLongRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean a(@NotNull UIntRange uIntRange, UInt uInt) {
        Intrinsics.f(uIntRange, "receiver$0");
        return uInt != null && uIntRange.a(uInt.b());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean a(@NotNull ULongRange uLongRange, ULong uLong) {
        Intrinsics.f(uLongRange, "receiver$0");
        return uLong != null && uLongRange.a(uLong.b());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression a(byte b, byte b2) {
        return UIntProgression.f2852a.a(UInt.b(b & 255), UInt.b(b2 & 255), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression a(int i, int i2) {
        return UIntProgression.f2852a.a(i, i2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression a(long j, long j2) {
        return ULongProgression.f2854a.a(j, j2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression a(short s, short s2) {
        return UIntProgression.f2852a.a(UInt.b(s & 65535), UInt.b(s2 & 65535), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression a(@NotNull UIntProgression uIntProgression) {
        Intrinsics.f(uIntProgression, "receiver$0");
        return UIntProgression.f2852a.a(uIntProgression.b(), uIntProgression.a(), -uIntProgression.c());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression a(@NotNull ULongProgression uLongProgression) {
        Intrinsics.f(uLongProgression, "receiver$0");
        return ULongProgression.f2854a.a(uLongProgression.b(), uLongProgression.a(), -uLongProgression.c());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression a(@NotNull UIntProgression uIntProgression, int i) {
        Intrinsics.f(uIntProgression, "receiver$0");
        RangesKt.a(i > 0, (Number) Integer.valueOf(i));
        UIntProgression.Companion companion = UIntProgression.f2852a;
        int a2 = uIntProgression.a();
        int b = uIntProgression.b();
        if (uIntProgression.c() <= 0) {
            i = -i;
        }
        return companion.a(a2, b, i);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression a(@NotNull ULongProgression uLongProgression, long j) {
        Intrinsics.f(uLongProgression, "receiver$0");
        RangesKt.a(j > 0, (Number) Long.valueOf(j));
        ULongProgression.Companion companion = ULongProgression.f2854a;
        long a2 = uLongProgression.a();
        long b = uLongProgression.b();
        if (uLongProgression.c() <= 0) {
            j = -j;
        }
        return companion.a(a2, b, j);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntRange b(byte b, byte b2) {
        return new UIntRange(UInt.b(b & 255), UInt.b(UInt.b(b2 & 255) - 1), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntRange b(int i, int i2) {
        if (UnsignedKt.a(i2, 0) <= 0) {
            return UIntRange.b.a();
        }
        return new UIntRange(i, UInt.b(i2 - 1), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongRange b(long j, long j2) {
        if (UnsignedKt.a(j2, 0) <= 0) {
            return ULongRange.b.a();
        }
        return new ULongRange(j, ULong.b(j2 - ULong.b(((long) 1) & MessageHead.SERIAL_MAK)), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntRange b(short s, short s2) {
        return new UIntRange(UInt.b(s & 65535), UInt.b(UInt.b(s2 & 65535) - 1), (DefaultConstructorMarker) null);
    }
}
