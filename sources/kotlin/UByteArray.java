package kotlin;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.UByteIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "storage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([BI)B", "hashCode", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "Lkotlin/collections/UByteIterator;", "iterator-impl", "([B)Lkotlin/collections/UByteIterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UByteArray implements Collection<UByte>, KMappedMarker {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f2681a;

    public static boolean a(byte[] bArr, @Nullable Object obj) {
        return (obj instanceof UByteArray) && Intrinsics.a((Object) bArr, (Object) ((UByteArray) obj).d());
    }

    public static final boolean a(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.f(bArr, "p1");
        Intrinsics.f(bArr2, "p2");
        throw null;
    }

    @PublishedApi
    public static /* synthetic */ void c() {
    }

    @NotNull
    @PublishedApi
    public static byte[] d(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "storage");
        return bArr;
    }

    @NotNull
    public static String f(byte[] bArr) {
        return "UByteArray(storage=" + Arrays.toString(bArr) + Operators.BRACKET_END_STR;
    }

    public static int g(byte[] bArr) {
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    public int a() {
        return a(this.f2681a);
    }

    public boolean a(byte b) {
        return a(this.f2681a, b);
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public UByteIterator iterator() {
        return b(this.f2681a);
    }

    public boolean b(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return a(this.f2681a, (Collection<UByte>) collection);
    }

    @NotNull
    public final /* synthetic */ byte[] d() {
        return this.f2681a;
    }

    public boolean equals(Object obj) {
        return a(this.f2681a, obj);
    }

    public int hashCode() {
        return g(this.f2681a);
    }

    public boolean isEmpty() {
        return c(this.f2681a);
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
        return f(this.f2681a);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return a(((UByte) obj).b());
        }
        return false;
    }

    public final int size() {
        return a();
    }

    @PublishedApi
    private /* synthetic */ UByteArray(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "storage");
        this.f2681a = bArr;
    }

    @NotNull
    public static byte[] a(int i) {
        return d(new byte[i]);
    }

    public static final byte a(byte[] bArr, int i) {
        return UByte.b(bArr[i]);
    }

    public static final void a(byte[] bArr, int i, byte b) {
        bArr[i] = b;
    }

    public static int a(byte[] bArr) {
        return bArr.length;
    }

    @NotNull
    public static UByteIterator b(byte[] bArr) {
        return new Iterator(bArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextUByte", "Lkotlin/UByte;", "()B", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static final class Iterator extends UByteIterator {

        /* renamed from: a  reason: collision with root package name */
        private int f2682a;
        private final byte[] b;

        public Iterator(@NotNull byte[] bArr) {
            Intrinsics.f(bArr, EventData.d);
            this.b = bArr;
        }

        public boolean hasNext() {
            return this.f2682a < this.b.length;
        }

        public byte a() {
            if (this.f2682a < this.b.length) {
                byte[] bArr = this.b;
                int i = this.f2682a;
                this.f2682a = i + 1;
                return UByte.b(bArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(this.f2682a));
        }
    }

    public static boolean a(byte[] bArr, byte b) {
        return ArraysKt.b(bArr, b);
    }

    public static boolean a(byte[] bArr, @NotNull Collection<UByte> collection) {
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        Iterable<UByte> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (UByte b : iterable) {
            if (!ArraysKt.b(bArr, b.b())) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(byte[] bArr) {
        return bArr.length == 0;
    }
}
