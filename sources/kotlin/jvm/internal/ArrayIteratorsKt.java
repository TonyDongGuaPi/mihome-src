package kotlin.jvm.internal;

import com.mi.mistatistic.sdk.data.EventData;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\t\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r\u001a\u000e\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000f\u001a\u000e\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011¨\u0006\u0012"}, d2 = {"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
public final class ArrayIteratorsKt {
    @NotNull
    public static final ByteIterator a(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, EventData.d);
        return new ArrayByteIterator(bArr);
    }

    @NotNull
    public static final CharIterator a(@NotNull char[] cArr) {
        Intrinsics.f(cArr, EventData.d);
        return new ArrayCharIterator(cArr);
    }

    @NotNull
    public static final ShortIterator a(@NotNull short[] sArr) {
        Intrinsics.f(sArr, EventData.d);
        return new ArrayShortIterator(sArr);
    }

    @NotNull
    public static final IntIterator a(@NotNull int[] iArr) {
        Intrinsics.f(iArr, EventData.d);
        return new ArrayIntIterator(iArr);
    }

    @NotNull
    public static final LongIterator a(@NotNull long[] jArr) {
        Intrinsics.f(jArr, EventData.d);
        return new ArrayLongIterator(jArr);
    }

    @NotNull
    public static final FloatIterator a(@NotNull float[] fArr) {
        Intrinsics.f(fArr, EventData.d);
        return new ArrayFloatIterator(fArr);
    }

    @NotNull
    public static final DoubleIterator a(@NotNull double[] dArr) {
        Intrinsics.f(dArr, EventData.d);
        return new ArrayDoubleIterator(dArr);
    }

    @NotNull
    public static final BooleanIterator a(@NotNull boolean[] zArr) {
        Intrinsics.f(zArr, EventData.d);
        return new ArrayBooleanIterator(zArr);
    }
}
