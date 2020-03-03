package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "T", "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, k = 3, mv = {1, 1, 13})
public final class ComparisonsKt__ComparisonsKt$compareBy$3<T> implements Comparator<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Comparator f2740a;
    final /* synthetic */ Function1 b;

    public ComparisonsKt__ComparisonsKt$compareBy$3(Comparator comparator, Function1 function1) {
        this.f2740a = comparator;
        this.b = function1;
    }

    public final int compare(T t, T t2) {
        return this.f2740a.compare(this.b.invoke(t), this.b.invoke(t2));
    }
}
