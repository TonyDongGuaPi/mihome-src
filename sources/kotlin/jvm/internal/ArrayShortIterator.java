package kotlin.jvm.internal;

import com.mi.mistatistic.sdk.data.EventData;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\n\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayShortIterator;", "Lkotlin/collections/ShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextShort", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class ArrayShortIterator extends ShortIterator {

    /* renamed from: a  reason: collision with root package name */
    private int f2808a;
    private final short[] b;

    public ArrayShortIterator(@NotNull short[] sArr) {
        Intrinsics.f(sArr, EventData.d);
        this.b = sArr;
    }

    public boolean hasNext() {
        return this.f2808a < this.b.length;
    }

    public short b() {
        try {
            short[] sArr = this.b;
            int i = this.f2808a;
            this.f2808a = i + 1;
            return sArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f2808a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
