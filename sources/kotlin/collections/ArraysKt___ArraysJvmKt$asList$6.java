package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$6", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Double;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class ArraysKt___ArraysJvmKt$asList$6 extends AbstractList<Double> implements RandomAccess {
    final /* synthetic */ double[] b;

    ArraysKt___ArraysJvmKt$asList$6(double[] dArr) {
        this.b = dArr;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Double) {
            return a(((Number) obj).doubleValue());
        }
        return false;
    }

    public final int indexOf(Object obj) {
        if (obj instanceof Double) {
            return b(((Number) obj).doubleValue());
        }
        return -1;
    }

    public final int lastIndexOf(Object obj) {
        if (obj instanceof Double) {
            return c(((Number) obj).doubleValue());
        }
        return -1;
    }

    public int a() {
        return this.b.length;
    }

    public boolean isEmpty() {
        return this.b.length == 0;
    }

    public boolean a(double d) {
        return ArraysKt.b(this.b, d);
    }

    @NotNull
    /* renamed from: a */
    public Double get(int i) {
        return Double.valueOf(this.b[i]);
    }

    public int b(double d) {
        return ArraysKt.c(this.b, d);
    }

    public int c(double d) {
        return ArraysKt.d(this.b, d);
    }
}
