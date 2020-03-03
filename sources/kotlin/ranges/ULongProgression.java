package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.collections.ULongIterator;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\t\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst", "()J", "J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/ULongIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public class ULongProgression implements Iterable<ULong>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f2854a = new Companion((DefaultConstructorMarker) null);
    private final long b;
    private final long c;
    private final long d;

    public /* synthetic */ ULongProgression(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    private ULongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (j3 != Long.MIN_VALUE) {
            this.b = j;
            this.c = UProgressionUtilKt.a(j, j2, j3);
            this.d = j3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
    }

    public final long a() {
        return this.b;
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return this.d;
    }

    @NotNull
    /* renamed from: d */
    public ULongIterator iterator() {
        return new ULongProgressionIterator(this.b, this.c, this.d, (DefaultConstructorMarker) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0014 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean e() {
        /*
            r6 = this;
            long r0 = r6.d
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r0 = 0
            r1 = 1
            if (r4 <= 0) goto L_0x0016
            long r2 = r6.b
            long r4 = r6.c
            int r2 = kotlin.UnsignedKt.a((long) r2, (long) r4)
            if (r2 <= 0) goto L_0x0021
        L_0x0014:
            r0 = 1
            goto L_0x0021
        L_0x0016:
            long r2 = r6.b
            long r4 = r6.c
            int r2 = kotlin.UnsignedKt.a((long) r2, (long) r4)
            if (r2 >= 0) goto L_0x0021
            goto L_0x0014
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ULongProgression.e():boolean");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ULongProgression) {
            if (!e() || !((ULongProgression) obj).e()) {
                ULongProgression uLongProgression = (ULongProgression) obj;
                if (!(this.b == uLongProgression.b && this.c == uLongProgression.c && this.d == uLongProgression.d)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (e()) {
            return -1;
        }
        return (((((int) ULong.b(this.b ^ ULong.b(this.b >>> 32))) * 31) + ((int) ULong.b(this.c ^ ULong.b(this.c >>> 32)))) * 31) + ((int) (this.d ^ (this.d >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j;
        if (this.d > 0) {
            sb = new StringBuilder();
            sb.append(ULong.c(this.b));
            sb.append("..");
            sb.append(ULong.c(this.c));
            sb.append(" step ");
            j = this.d;
        } else {
            sb = new StringBuilder();
            sb.append(ULong.c(this.b));
            sb.append(" downTo ");
            sb.append(ULong.c(this.c));
            sb.append(" step ");
            j = -this.d;
        }
        sb.append(j);
        return sb.toString();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ranges/ULongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ULongProgression a(long j, long j2, long j3) {
            return new ULongProgression(j, j2, j3, (DefaultConstructorMarker) null);
        }
    }
}
