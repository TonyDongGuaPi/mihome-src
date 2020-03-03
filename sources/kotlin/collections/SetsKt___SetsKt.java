package kotlin.collections;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/SetsKt")
class SetsKt___SetsKt extends SetsKt__SetsKt {
    @NotNull
    public static final <T> Set<T> a(@NotNull Set<? extends T> set, T t) {
        Intrinsics.f(set, "receiver$0");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.a(set.size()));
        boolean z = false;
        for (Object next : set) {
            boolean z2 = true;
            if (!z && Intrinsics.a(next, (Object) t)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(next);
            }
        }
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> a(@NotNull Set<? extends T> set, @NotNull T[] tArr) {
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(tArr, MessengerShareContentUtility.ELEMENTS);
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.b(linkedHashSet, tArr);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> a(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(iterable, MessengerShareContentUtility.ELEMENTS);
        Iterable iterable2 = set;
        Collection<? extends T> a2 = CollectionsKt.a(iterable, (Iterable<? extends T>) iterable2);
        if (a2.isEmpty()) {
            return CollectionsKt.t(iterable2);
        }
        if (a2 instanceof Set) {
            Collection linkedHashSet = new LinkedHashSet();
            for (Object next : iterable2) {
                if (!a2.contains(next)) {
                    linkedHashSet.add(next);
                }
            }
            return (Set) linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(set);
        linkedHashSet2.removeAll(a2);
        return linkedHashSet2;
    }

    @NotNull
    public static final <T> Set<T> a(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> sequence) {
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(sequence, MessengerShareContentUtility.ELEMENTS);
        LinkedHashSet linkedHashSet = new LinkedHashSet(set);
        CollectionsKt.b(linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> c(@NotNull Set<? extends T> set, T t) {
        return SetsKt.a(set, t);
    }

    @NotNull
    public static final <T> Set<T> b(@NotNull Set<? extends T> set, T t) {
        Intrinsics.f(set, "receiver$0");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.a(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(t);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> b(@NotNull Set<? extends T> set, @NotNull T[] tArr) {
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(tArr, MessengerShareContentUtility.ELEMENTS);
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.a(set.size() + tArr.length));
        linkedHashSet.addAll(set);
        CollectionsKt.a(linkedHashSet, tArr);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> b(@NotNull Set<? extends T> set, @NotNull Iterable<? extends T> iterable) {
        int i;
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(iterable, MessengerShareContentUtility.ELEMENTS);
        Integer a2 = CollectionsKt.a(iterable);
        if (a2 != null) {
            i = set.size() + a2.intValue();
        } else {
            i = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.a(i));
        linkedHashSet.addAll(set);
        CollectionsKt.a(linkedHashSet, iterable);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> b(@NotNull Set<? extends T> set, @NotNull Sequence<? extends T> sequence) {
        Intrinsics.f(set, "receiver$0");
        Intrinsics.f(sequence, MessengerShareContentUtility.ELEMENTS);
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.a(set.size() * 2));
        linkedHashSet.addAll(set);
        CollectionsKt.a(linkedHashSet, sequence);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> d(@NotNull Set<? extends T> set, T t) {
        return SetsKt.b(set, t);
    }
}
