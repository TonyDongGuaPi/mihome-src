package kotlin.jvm.internal;

import com.mi.mistatistic.sdk.data.EventData;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayCharIterator;", "Lkotlin/collections/CharIterator;", "array", "", "([C)V", "index", "", "hasNext", "", "nextChar", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class ArrayCharIterator extends CharIterator {

    /* renamed from: a  reason: collision with root package name */
    private int f2802a;
    private final char[] b;

    public ArrayCharIterator(@NotNull char[] cArr) {
        Intrinsics.f(cArr, EventData.d);
        this.b = cArr;
    }

    public boolean hasNext() {
        return this.f2802a < this.b.length;
    }

    public char b() {
        try {
            char[] cArr = this.b;
            int i = this.f2802a;
            this.f2802a = i + 1;
            return cArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f2802a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}