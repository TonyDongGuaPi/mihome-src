package kotlin.sequences;

import java.util.Enumeration;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\b¨\u0006\u0004"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/sequences/SequencesKt")
class SequencesKt__SequencesJVMKt extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> a(@NotNull Enumeration<T> enumeration) {
        return SequencesKt.a(CollectionsKt.a(enumeration));
    }
}