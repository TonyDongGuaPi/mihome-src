package kotlin;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.UIntIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lkotlin/UIntArray;", "", "Lkotlin/UInt;", "size", "", "constructor-impl", "(I)[I", "storage", "", "([I)[I", "getSize-impl", "([I)I", "storage$annotations", "()V", "contains", "", "element", "contains-WZ4Q5Ns", "([II)Z", "containsAll", "elements", "containsAll-impl", "([ILjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([II)I", "hashCode", "isEmpty", "isEmpty-impl", "([I)Z", "iterator", "Lkotlin/collections/UIntIterator;", "iterator-impl", "([I)Lkotlin/collections/UIntIterator;", "set", "", "value", "set-VXSXFK8", "([III)V", "toString", "", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UIntArray implements Collection<UInt>, KMappedMarker {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final int[] f2684a;

    public static boolean a(int[] iArr, @Nullable Object obj) {
        return (obj instanceof UIntArray) && Intrinsics.a((Object) iArr, (Object) ((UIntArray) obj).d());
    }

    public static final boolean a(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.f(iArr, "p1");
        Intrinsics.f(iArr2, "p2");
        throw null;
    }

    @PublishedApi
    public static /* synthetic */ void c() {
    }

    @NotNull
    @PublishedApi
    public static int[] d(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "storage");
        return iArr;
    }

    @NotNull
    public static String f(int[] iArr) {
        return "UIntArray(storage=" + Arrays.toString(iArr) + Operators.BRACKET_END_STR;
    }

    public static int g(int[] iArr) {
        if (iArr != null) {
            return Arrays.hashCode(iArr);
        }
        return 0;
    }

    public int a() {
        return a(this.f2684a);
    }

    public boolean a(int i) {
        return b(this.f2684a, i);
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public UIntIterator iterator() {
        return b(this.f2684a);
    }

    public boolean c(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return a(this.f2684a, (Collection<UInt>) collection);
    }

    @NotNull
    public final /* synthetic */ int[] d() {
        return this.f2684a;
    }

    public boolean equals(Object obj) {
        return a(this.f2684a, obj);
    }

    public int hashCode() {
        return g(this.f2684a);
    }

    public boolean isEmpty() {
        return c(this.f2684a);
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
        return f(this.f2684a);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return a(((UInt) obj).b());
        }
        return false;
    }

    public final int size() {
        return a();
    }

    @PublishedApi
    private /* synthetic */ UIntArray(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "storage");
        this.f2684a = iArr;
    }

    @NotNull
    public static int[] b(int i) {
        return d(new int[i]);
    }

    public static final int a(int[] iArr, int i) {
        return UInt.b(iArr[i]);
    }

    public static final void a(int[] iArr, int i, int i2) {
        iArr[i] = i2;
    }

    public static int a(int[] iArr) {
        return iArr.length;
    }

    @NotNull
    public static UIntIterator b(int[] iArr) {
        return new Iterator(iArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/UIntArray$Iterator;", "Lkotlin/collections/UIntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextUInt", "Lkotlin/UInt;", "()I", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static final class Iterator extends UIntIterator {

        /* renamed from: a  reason: collision with root package name */
        private int f2685a;
        private final int[] b;

        public Iterator(@NotNull int[] iArr) {
            Intrinsics.f(iArr, EventData.d);
            this.b = iArr;
        }

        public boolean hasNext() {
            return this.f2685a < this.b.length;
        }

        public int a() {
            if (this.f2685a < this.b.length) {
                int[] iArr = this.b;
                int i = this.f2685a;
                this.f2685a = i + 1;
                return UInt.b(iArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.f2685a));
        }
    }

    public static boolean b(int[] iArr, int i) {
        return ArraysKt.b(iArr, i);
    }

    public static boolean a(int[] iArr, @NotNull Collection<UInt> collection) {
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        Iterable<UInt> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (UInt b : iterable) {
            if (!ArraysKt.b(iArr, b.b())) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(int[] iArr) {
        return iArr.length == 0;
    }
}
