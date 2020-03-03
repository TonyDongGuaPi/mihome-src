package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementations;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u0011\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"kotlin/text/MatcherMatchResult$groups$1", "Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/collections/AbstractCollection;", "Lkotlin/text/MatchGroup;", "size", "", "getSize", "()I", "get", "index", "name", "", "isEmpty", "", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MatcherMatchResult f2906a;

    public boolean isEmpty() {
        return false;
    }

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.f2906a = matcherMatchResult;
    }

    public boolean a(MatchGroup matchGroup) {
        return super.contains(matchGroup);
    }

    public final boolean contains(Object obj) {
        if (obj != null ? obj instanceof MatchGroup : true) {
            return a((MatchGroup) obj);
        }
        return false;
    }

    public int a() {
        return this.f2906a.f2905a.groupCount() + 1;
    }

    @NotNull
    public Iterator<MatchGroup> iterator() {
        return SequencesKt.u(CollectionsKt.I(CollectionsKt.a((Collection<?>) this)), new MatcherMatchResult$groups$1$iterator$1(this)).a();
    }

    @Nullable
    public MatchGroup a(int i) {
        MatchResult a2 = this.f2906a.f2905a;
        Intrinsics.b(a2, "matchResult");
        IntRange a3 = RegexKt.b(a2, i);
        if (a3.g().intValue() < 0) {
            return null;
        }
        String group = this.f2906a.f2905a.group(i);
        Intrinsics.b(group, "matchResult.group(index)");
        return new MatchGroup(group, a3);
    }

    @Nullable
    public MatchGroup a(@NotNull String str) {
        Intrinsics.f(str, "name");
        PlatformImplementations platformImplementations = PlatformImplementationsKt.f2786a;
        MatchResult a2 = this.f2906a.f2905a;
        Intrinsics.b(a2, "matchResult");
        return platformImplementations.a(a2, str);
    }
}
