package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/DropSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class DropSequence<T> implements DropTakeSequence<T>, Sequence<T> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Sequence<T> f2860a;
    /* access modifiers changed from: private */
    public final int b;

    public DropSequence(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.f(sequence, "sequence");
        this.f2860a = sequence;
        this.b = i;
        if (!(this.b >= 0)) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + this.b + '.').toString());
        }
    }

    @NotNull
    public Sequence<T> a(int i) {
        int i2 = this.b + i;
        return i2 < 0 ? new DropSequence<>(this, i) : new DropSequence<>(this.f2860a, i2);
    }

    @NotNull
    public Sequence<T> b(int i) {
        int i2 = this.b + i;
        return i2 < 0 ? new TakeSequence<>(this, i) : new SubSequence<>(this.f2860a, this.b, i2);
    }

    @NotNull
    public Iterator<T> a() {
        return new DropSequence$iterator$1(this);
    }
}
