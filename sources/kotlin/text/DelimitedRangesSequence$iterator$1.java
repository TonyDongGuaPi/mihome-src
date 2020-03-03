package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\t\u0010\u0019\u001a\u00020\u001aH\u0002J\t\u0010\u001b\u001a\u00020\u0002H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DelimitedRangesSequence f2902a;
    private int b = -1;
    private int c;
    private int d;
    @Nullable
    private IntRange e;
    private int f;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.f2902a = delimitedRangesSequence;
        this.c = RangesKt.a(delimitedRangesSequence.b, 0, delimitedRangesSequence.f2901a.length());
        this.d = this.c;
    }

    public final int a() {
        return this.b;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final int b() {
        return this.c;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final int c() {
        return this.d;
    }

    public final void c(int i) {
        this.d = i;
    }

    public final void a(@Nullable IntRange intRange) {
        this.e = intRange;
    }

    @Nullable
    public final IntRange d() {
        return this.e;
    }

    public final void d(int i) {
        this.f = i;
    }

    public final int e() {
        return this.f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
        if (r6.f < r6.f2902a.c) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void g() {
        /*
            r6 = this;
            int r0 = r6.d
            r1 = 0
            if (r0 >= 0) goto L_0x000e
            r6.b = r1
            r0 = 0
            kotlin.ranges.IntRange r0 = (kotlin.ranges.IntRange) r0
            r6.e = r0
            goto L_0x00a4
        L_0x000e:
            kotlin.text.DelimitedRangesSequence r0 = r6.f2902a
            int r0 = r0.c
            r2 = -1
            r3 = 1
            if (r0 <= 0) goto L_0x0027
            int r0 = r6.f
            int r0 = r0 + r3
            r6.f = r0
            int r0 = r6.f
            kotlin.text.DelimitedRangesSequence r4 = r6.f2902a
            int r4 = r4.c
            if (r0 >= r4) goto L_0x0035
        L_0x0027:
            int r0 = r6.d
            kotlin.text.DelimitedRangesSequence r4 = r6.f2902a
            java.lang.CharSequence r4 = r4.f2901a
            int r4 = r4.length()
            if (r0 <= r4) goto L_0x004b
        L_0x0035:
            int r0 = r6.c
            kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
            kotlin.text.DelimitedRangesSequence r4 = r6.f2902a
            java.lang.CharSequence r4 = r4.f2901a
            int r4 = kotlin.text.StringsKt.g(r4)
            r1.<init>(r0, r4)
            r6.e = r1
            r6.d = r2
            goto L_0x00a2
        L_0x004b:
            kotlin.text.DelimitedRangesSequence r0 = r6.f2902a
            kotlin.jvm.functions.Function2 r0 = r0.d
            kotlin.text.DelimitedRangesSequence r4 = r6.f2902a
            java.lang.CharSequence r4 = r4.f2901a
            int r5 = r6.d
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.invoke(r4, r5)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L_0x007b
            int r0 = r6.c
            kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
            kotlin.text.DelimitedRangesSequence r4 = r6.f2902a
            java.lang.CharSequence r4 = r4.f2901a
            int r4 = kotlin.text.StringsKt.g(r4)
            r1.<init>(r0, r4)
            r6.e = r1
            r6.d = r2
            goto L_0x00a2
        L_0x007b:
            java.lang.Object r2 = r0.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.component2()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r4 = r6.c
            kotlin.ranges.IntRange r4 = kotlin.ranges.RangesKt.b((int) r4, (int) r2)
            r6.e = r4
            int r2 = r2 + r0
            r6.c = r2
            int r2 = r6.c
            if (r0 != 0) goto L_0x009f
            r1 = 1
        L_0x009f:
            int r2 = r2 + r1
            r6.d = r2
        L_0x00a2:
            r6.b = r3
        L_0x00a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.g():void");
    }

    @NotNull
    /* renamed from: f */
    public IntRange next() {
        if (this.b == -1) {
            g();
        }
        if (this.b != 0) {
            IntRange intRange = this.e;
            if (intRange != null) {
                this.e = null;
                this.b = -1;
                return intRange;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.b == -1) {
            g();
        }
        return this.b == 1;
    }
}
