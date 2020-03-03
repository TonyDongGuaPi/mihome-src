package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"kotlin/sequences/SubSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "position", "", "getPosition", "()I", "setPosition", "(I)V", "drop", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class SubSequence$iterator$1 implements Iterator<T>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SubSequence f2889a;
    @NotNull
    private final Iterator<T> b;
    private int c;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    SubSequence$iterator$1(SubSequence subSequence) {
        this.f2889a = subSequence;
        this.b = subSequence.f2888a.a();
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

    private final void c() {
        while (this.c < this.f2889a.b && this.b.hasNext()) {
            this.b.next();
            this.c++;
        }
    }

    public boolean hasNext() {
        c();
        return this.c < this.f2889a.c && this.b.hasNext();
    }

    public T next() {
        c();
        if (this.c < this.f2889a.c) {
            this.c++;
            return this.b.next();
        }
        throw new NoSuchElementException();
    }
}
