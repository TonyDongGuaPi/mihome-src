package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\t\u001a\u00020\nH\u0002J\t\u0010\u000b\u001a\u00020\nH\u0002J\u000e\u0010\f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\rR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0004¨\u0006\u000e"}, d2 = {"kotlin/sequences/FlatteningSequence$iterator$1", "", "itemIterator", "getItemIterator", "()Ljava/util/Iterator;", "setItemIterator", "(Ljava/util/Iterator;)V", "iterator", "getIterator", "ensureItemIterator", "", "hasNext", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class FlatteningSequence$iterator$1 implements Iterator<E>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FlatteningSequence f2868a;
    @NotNull
    private final Iterator<T> b;
    @Nullable
    private Iterator<? extends E> c;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    FlatteningSequence$iterator$1(FlatteningSequence flatteningSequence) {
        this.f2868a = flatteningSequence;
        this.b = flatteningSequence.f2867a.a();
    }

    @NotNull
    public final Iterator<T> a() {
        return this.b;
    }

    public final void a(@Nullable Iterator<? extends E> it) {
        this.c = it;
    }

    @Nullable
    public final Iterator<E> b() {
        return this.c;
    }

    public E next() {
        if (c()) {
            Iterator<? extends E> it = this.c;
            if (it == null) {
                Intrinsics.a();
            }
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        return c();
    }

    private final boolean c() {
        Iterator<? extends E> it = this.c;
        if (it != null && !it.hasNext()) {
            this.c = null;
        }
        while (this.c == null) {
            if (!this.b.hasNext()) {
                return false;
            }
            Iterator<? extends E> it2 = (Iterator) this.f2868a.c.invoke(this.f2868a.b.invoke(this.b.next()));
            if (it2.hasNext()) {
                this.c = it2;
                return true;
            }
        }
        return true;
    }
}
