package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\t\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"kotlin/sequences/FilteringSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "nextState", "", "getNextState", "()I", "setNextState", "(I)V", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class FilteringSequence$iterator$1 implements Iterator<T>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FilteringSequence f2866a;
    @NotNull
    private final Iterator<T> b;
    private int c = -1;
    @Nullable
    private T d;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        this.f2866a = filteringSequence;
        this.b = filteringSequence.f2865a.a();
    }

    @NotNull
    public final Iterator<T> a() {
        return this.b;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final int b() {
        return this.c;
    }

    public final void a(@Nullable T t) {
        this.d = t;
    }

    @Nullable
    public final T c() {
        return this.d;
    }

    private final void d() {
        while (this.b.hasNext()) {
            T next = this.b.next();
            if (((Boolean) this.f2866a.c.invoke(next)).booleanValue() == this.f2866a.b) {
                this.d = next;
                this.c = 1;
                return;
            }
        }
        this.c = 0;
    }

    public T next() {
        if (this.c == -1) {
            d();
        }
        if (this.c != 0) {
            T t = this.d;
            this.d = null;
            this.c = -1;
            return t;
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.c == -1) {
            d();
        }
        return this.c == 1;
    }
}