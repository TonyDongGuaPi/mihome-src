package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u000b"}, d2 = {"kotlin/sequences/MergingSequence$iterator$1", "", "iterator1", "getIterator1", "()Ljava/util/Iterator;", "iterator2", "getIterator2", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class MergingSequence$iterator$1 implements Iterator<V>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MergingSequence f2874a;
    @NotNull
    private final Iterator<T1> b;
    @NotNull
    private final Iterator<T2> c;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    MergingSequence$iterator$1(MergingSequence mergingSequence) {
        this.f2874a = mergingSequence;
        this.b = mergingSequence.f2873a.a();
        this.c = mergingSequence.b.a();
    }

    @NotNull
    public final Iterator<T1> a() {
        return this.b;
    }

    @NotNull
    public final Iterator<T2> b() {
        return this.c;
    }

    public V next() {
        return this.f2874a.c.invoke(this.b.next(), this.c.next());
    }

    public boolean hasNext() {
        return this.b.hasNext() && this.c.hasNext();
    }
}
