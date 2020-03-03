package kotlin.text;

import kotlin.Metadata;
import kotlin.collections.AbstractList;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"kotlin/text/MatcherMatchResult$groupValues$1", "Lkotlin/collections/AbstractList;", "", "size", "", "getSize", "()I", "get", "index", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class MatcherMatchResult$groupValues$1 extends AbstractList<String> {
    final /* synthetic */ MatcherMatchResult b;

    MatcherMatchResult$groupValues$1(MatcherMatchResult matcherMatchResult) {
        this.b = matcherMatchResult;
    }

    public int a(String str) {
        return super.indexOf(str);
    }

    public int b(String str) {
        return super.lastIndexOf(str);
    }

    public boolean c(String str) {
        return super.contains(str);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof String) {
            return c((String) obj);
        }
        return false;
    }

    public final int indexOf(Object obj) {
        if (obj instanceof String) {
            return a((String) obj);
        }
        return -1;
    }

    public final int lastIndexOf(Object obj) {
        if (obj instanceof String) {
            return b((String) obj);
        }
        return -1;
    }

    public int a() {
        return this.b.f2905a.groupCount() + 1;
    }

    @NotNull
    /* renamed from: a */
    public String get(int i) {
        String group = this.b.f2905a.group(i);
        return group != null ? group : "";
    }
}
