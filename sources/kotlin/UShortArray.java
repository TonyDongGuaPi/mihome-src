package kotlin;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.UShortIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "size", "", "constructor-impl", "(I)[S", "storage", "", "([S)[S", "getSize-impl", "([S)I", "storage$annotations", "()V", "contains", "", "element", "contains-xj2QHRw", "([SS)Z", "containsAll", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([SI)S", "hashCode", "isEmpty", "isEmpty-impl", "([S)Z", "iterator", "Lkotlin/collections/UShortIterator;", "iterator-impl", "([S)Lkotlin/collections/UShortIterator;", "set", "", "value", "set-01HTLdE", "([SIS)V", "toString", "", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UShortArray implements Collection<UShort>, KMappedMarker {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final short[] f2691a;

    public static boolean a(short[] sArr, @Nullable Object obj) {
        return (obj instanceof UShortArray) && Intrinsics.a((Object) sArr, (Object) ((UShortArray) obj).d());
    }

    public static final boolean a(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.f(sArr, "p1");
        Intrinsics.f(sArr2, "p2");
        throw null;
    }

    @PublishedApi
    public static /* synthetic */ void c() {
    }

    @NotNull
    @PublishedApi
    public static short[] d(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "storage");
        return sArr;
    }

    @NotNull
    public static String f(short[] sArr) {
        return "UShortArray(storage=" + Arrays.toString(sArr) + Operators.BRACKET_END_STR;
    }

    public static int g(short[] sArr) {
        if (sArr != null) {
            return Arrays.hashCode(sArr);
        }
        return 0;
    }

    public int a() {
        return a(this.f2691a);
    }

    public boolean a(short s) {
        return a(this.f2691a, s);
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public UShortIterator iterator() {
        return b(this.f2691a);
    }

    public boolean b(short s) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return a(this.f2691a, (Collection<UShort>) collection);
    }

    @NotNull
    public final /* synthetic */ short[] d() {
        return this.f2691a;
    }

    public boolean equals(Object obj) {
        return a(this.f2691a, obj);
    }

    public int hashCode() {
        return g(this.f2691a);
    }

    public boolean isEmpty() {
        return c(this.f2691a);
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
        return f(this.f2691a);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return a(((UShort) obj).b());
        }
        return false;
    }

    public final int size() {
        return a();
    }

    @PublishedApi
    private /* synthetic */ UShortArray(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "storage");
        this.f2691a = sArr;
    }

    @NotNull
    public static short[] a(int i) {
        return d(new short[i]);
    }

    public static final short a(short[] sArr, int i) {
        return UShort.b(sArr[i]);
    }

    public static final void a(short[] sArr, int i, short s) {
        sArr[i] = s;
    }

    public static int a(short[] sArr) {
        return sArr.length;
    }

    @NotNull
    public static UShortIterator b(short[] sArr) {
        return new Iterator(sArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/UShortArray$Iterator;", "Lkotlin/collections/UShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextUShort", "Lkotlin/UShort;", "()S", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static final class Iterator extends UShortIterator {

        /* renamed from: a  reason: collision with root package name */
        private int f2692a;
        private final short[] b;

        public Iterator(@NotNull short[] sArr) {
            Intrinsics.f(sArr, EventData.d);
            this.b = sArr;
        }

        public boolean hasNext() {
            return this.f2692a < this.b.length;
        }

        public short a() {
            if (this.f2692a < this.b.length) {
                short[] sArr = this.b;
                int i = this.f2692a;
                this.f2692a = i + 1;
                return UShort.b(sArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.f2692a));
        }
    }

    public static boolean a(short[] sArr, short s) {
        return ArraysKt.b(sArr, s);
    }

    public static boolean a(short[] sArr, @NotNull Collection<UShort> collection) {
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        Iterable<UShort> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (UShort b : iterable) {
            if (!ArraysKt.b(sArr, b.b())) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(short[] sArr) {
        return sArr.length == 0;
    }
}
