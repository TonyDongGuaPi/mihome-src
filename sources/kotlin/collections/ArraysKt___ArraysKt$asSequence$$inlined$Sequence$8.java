package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004¸\u0006\u0000"}, d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$8 implements Sequence<Boolean> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean[] f2721a;

    public ArraysKt___ArraysKt$asSequence$$inlined$Sequence$8(boolean[] zArr) {
        this.f2721a = zArr;
    }

    @NotNull
    public Iterator<Boolean> a() {
        return ArrayIteratorsKt.a(this.f2721a);
    }
}
