package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/collections/DoubleIterator;", "invoke"}, k = 3, mv = {1, 1, 13})
final class ArraysKt___ArraysKt$withIndex$7 extends Lambda implements Function0<DoubleIterator> {
    final /* synthetic */ double[] $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$7(double[] dArr) {
        super(0);
        this.$this_withIndex = dArr;
    }

    @NotNull
    public final DoubleIterator invoke() {
        return ArrayIteratorsKt.a(this.$this_withIndex);
    }
}
