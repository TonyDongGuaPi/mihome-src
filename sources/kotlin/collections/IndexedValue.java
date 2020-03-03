package kotlin.collections;

import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lkotlin/collections/IndexedValue;", "T", "", "index", "", "value", "(ILjava/lang/Object;)V", "getIndex", "()I", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "equals", "", "other", "hashCode", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class IndexedValue<T> {

    /* renamed from: a  reason: collision with root package name */
    private final int f2729a;
    private final T b;

    @NotNull
    public static /* synthetic */ IndexedValue a(IndexedValue indexedValue, int i, T t, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = indexedValue.f2729a;
        }
        if ((i2 & 2) != 0) {
            t = indexedValue.b;
        }
        return indexedValue.a(i, t);
    }

    @NotNull
    public final IndexedValue<T> a(int i, T t) {
        return new IndexedValue<>(i, t);
    }

    public final int c() {
        return this.f2729a;
    }

    public final T d() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof IndexedValue) {
                IndexedValue indexedValue = (IndexedValue) obj;
                if (!(this.f2729a == indexedValue.f2729a) || !Intrinsics.a((Object) this.b, (Object) indexedValue.b)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.f2729a * 31;
        T t = this.b;
        return i + (t != null ? t.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "IndexedValue(index=" + this.f2729a + ", value=" + this.b + Operators.BRACKET_END_STR;
    }

    public IndexedValue(int i, T t) {
        this.f2729a = i;
        this.b = t;
    }

    public final int a() {
        return this.f2729a;
    }

    public final T b() {
        return this.b;
    }
}
