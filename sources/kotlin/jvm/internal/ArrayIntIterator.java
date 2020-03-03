package kotlin.jvm.internal;

import com.mi.mistatistic.sdk.data.EventData;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/jvm/internal/ArrayIntIterator;", "Lkotlin/collections/IntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextInt", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class ArrayIntIterator extends IntIterator {

    /* renamed from: a  reason: collision with root package name */
    private int f2805a;
    private final int[] b;

    public ArrayIntIterator(@NotNull int[] iArr) {
        Intrinsics.f(iArr, EventData.d);
        this.b = iArr;
    }

    public boolean hasNext() {
        return this.f2805a < this.b.length;
    }

    public int b() {
        try {
            int[] iArr = this.b;
            int i = this.f2805a;
            this.f2805a = i + 1;
            return iArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f2805a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
