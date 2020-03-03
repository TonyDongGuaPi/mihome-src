package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", "", "last", "step", "", "(CCI)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextChar", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class CharProgressionIterator extends CharIterator {

    /* renamed from: a  reason: collision with root package name */
    private final int f2844a;
    private boolean b;
    private int c;
    private final int d;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CharProgressionIterator(char r3, char r4, int r5) {
        /*
            r2 = this;
            r2.<init>()
            r2.d = r5
            r2.f2844a = r4
            int r5 = r2.d
            r0 = 0
            r1 = 1
            if (r5 <= 0) goto L_0x0011
            if (r3 > r4) goto L_0x0014
        L_0x000f:
            r0 = 1
            goto L_0x0014
        L_0x0011:
            if (r3 < r4) goto L_0x0014
            goto L_0x000f
        L_0x0014:
            r2.b = r0
            boolean r4 = r2.b
            if (r4 == 0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            int r3 = r2.f2844a
        L_0x001d:
            r2.c = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.CharProgressionIterator.<init>(char, char, int):void");
    }

    public final int c() {
        return this.d;
    }

    public boolean hasNext() {
        return this.b;
    }

    public char b() {
        int i = this.c;
        if (i != this.f2844a) {
            this.c += this.d;
        } else if (this.b) {
            this.b = false;
        } else {
            throw new NoSuchElementException();
        }
        return (char) i;
    }
}
