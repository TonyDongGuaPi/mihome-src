package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"kotlin/collections/AbstractMap$values$1", "Lkotlin/collections/AbstractCollection;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class AbstractMap$values$1 extends AbstractCollection<V> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AbstractMap f2702a;

    AbstractMap$values$1(AbstractMap abstractMap) {
        this.f2702a = abstractMap;
    }

    public boolean contains(Object obj) {
        return this.f2702a.containsValue(obj);
    }

    @NotNull
    public Iterator<V> iterator() {
        return new AbstractMap$values$1$iterator$1(this.f2702a.entrySet().iterator());
    }

    public int a() {
        return this.f2702a.size();
    }
}
