package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004"}, d2 = {"kotlin/sequences/SequencesKt___SequencesKt$minus$4", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class SequencesKt___SequencesKt$minus$4 implements Sequence<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Sequence f2885a;
    final /* synthetic */ Sequence b;

    SequencesKt___SequencesKt$minus$4(Sequence<? extends T> sequence, Sequence sequence2) {
        this.f2885a = sequence;
        this.b = sequence2;
    }

    @NotNull
    public Iterator<T> a() {
        HashSet o = SequencesKt.o(this.b);
        if (o.isEmpty()) {
            return this.f2885a.a();
        }
        return SequencesKt.k(this.f2885a, new SequencesKt___SequencesKt$minus$4$iterator$1(o)).a();
    }
}
