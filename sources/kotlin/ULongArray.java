package kotlin;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lkotlin/ULongArray;", "", "Lkotlin/ULong;", "size", "", "constructor-impl", "(I)[J", "storage", "", "([J)[J", "getSize-impl", "([J)I", "storage$annotations", "()V", "contains", "", "element", "contains-VKZWuLQ", "([JJ)Z", "containsAll", "elements", "containsAll-impl", "([JLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([JI)J", "hashCode", "isEmpty", "isEmpty-impl", "([J)Z", "iterator", "Lkotlin/collections/ULongIterator;", "iterator-impl", "([J)Lkotlin/collections/ULongIterator;", "set", "", "value", "set-k8EXiF4", "([JIJ)V", "toString", "", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class ULongArray implements Collection<ULong>, KMappedMarker {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final long[] f2687a;

    public static boolean a(long[] jArr, @Nullable Object obj) {
        return (obj instanceof ULongArray) && Intrinsics.a((Object) jArr, (Object) ((ULongArray) obj).d());
    }

    public static final boolean a(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.f(jArr, "p1");
        Intrinsics.f(jArr2, "p2");
        throw null;
    }

    @PublishedApi
    public static /* synthetic */ void c() {
    }

    @NotNull
    @PublishedApi
    public static long[] d(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "storage");
        return jArr;
    }

    @NotNull
    public static String f(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + Operators.BRACKET_END_STR;
    }

    public static int g(long[] jArr) {
        if (jArr != null) {
            return Arrays.hashCode(jArr);
        }
        return 0;
    }

    public int a() {
        return a(this.f2687a);
    }

    public boolean a(long j) {
        return a(this.f2687a, j);
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public ULongIterator iterator() {
        return b(this.f2687a);
    }

    public boolean b(long j) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return a(this.f2687a, (Collection<ULong>) collection);
    }

    @NotNull
    public final /* synthetic */ long[] d() {
        return this.f2687a;
    }

    public boolean equals(Object obj) {
        return a(this.f2687a, obj);
    }

    public int hashCode() {
        return g(this.f2687a);
    }

    public boolean isEmpty() {
        return c(this.f2687a);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return CollectionToArray.a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return CollectionToArray.a(this, tArr);
    }

    public String toString() {
        return f(this.f2687a);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return a(((ULong) obj).b());
        }
        return false;
    }

    public final int size() {
        return a();
    }

    @PublishedApi
    private /* synthetic */ ULongArray(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "storage");
        this.f2687a = jArr;
    }

    @NotNull
    public static long[] a(int i) {
        return d(new long[i]);
    }

    public static final long a(long[] jArr, int i) {
        return ULong.b(jArr[i]);
    }

    public static final void a(long[] jArr, int i, long j) {
        jArr[i] = j;
    }

    public static int a(long[] jArr) {
        return jArr.length;
    }

    @NotNull
    public static ULongIterator b(long[] jArr) {
        return new Iterator(jArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ULongArray$Iterator;", "Lkotlin/collections/ULongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextULong", "Lkotlin/ULong;", "()J", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static final class Iterator extends ULongIterator {

        /* renamed from: a  reason: collision with root package name */
        private int f2688a;
        private final long[] b;

        public Iterator(@NotNull long[] jArr) {
            Intrinsics.f(jArr, EventData.d);
            this.b = jArr;
        }

        public boolean hasNext() {
            return this.f2688a < this.b.length;
        }

        public long a() {
            if (this.f2688a < this.b.length) {
                long[] jArr = this.b;
                int i = this.f2688a;
                this.f2688a = i + 1;
                return ULong.b(jArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.f2688a));
        }
    }

    public static boolean a(long[] jArr, long j) {
        return ArraysKt.b(jArr, j);
    }

    public static boolean a(long[] jArr, @NotNull Collection<ULong> collection) {
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        Iterable<ULong> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (ULong b : iterable) {
            if (!ArraysKt.b(jArr, b.b())) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(long[] jArr) {
        return jArr.length == 0;
    }
}
