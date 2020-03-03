package kotlin.jvm.internal;

import com.mi.mistatistic.sdk.data.EventData;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.DoubleIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayDoubleIterator;", "Lkotlin/collections/DoubleIterator;", "array", "", "([D)V", "index", "", "hasNext", "", "nextDouble", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class ArrayDoubleIterator extends DoubleIterator {

    /* renamed from: a  reason: collision with root package name */
    private int f2803a;
    private final double[] b;

    public ArrayDoubleIterator(@NotNull double[] dArr) {
        Intrinsics.f(dArr, EventData.d);
        this.b = dArr;
    }

    public boolean hasNext() {
        return this.f2803a < this.b.length;
    }

    public double b() {
        try {
            double[] dArr = this.b;
            int i = this.f2803a;
            this.f2803a = i + 1;
            return dArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f2803a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
