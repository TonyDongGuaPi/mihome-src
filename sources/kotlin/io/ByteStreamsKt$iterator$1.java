package kotlin.io;

import java.io.BufferedInputStream;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u0002J\b\u0010\b\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007¨\u0006\u0015"}, d2 = {"kotlin/io/ByteStreamsKt$iterator$1", "Lkotlin/collections/ByteIterator;", "finished", "", "getFinished", "()Z", "setFinished", "(Z)V", "nextByte", "", "getNextByte", "()I", "setNextByte", "(I)V", "nextPrepared", "getNextPrepared", "setNextPrepared", "hasNext", "", "prepareNext", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class ByteStreamsKt$iterator$1 extends ByteIterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BufferedInputStream f2787a;
    private int b = -1;
    private boolean c;
    private boolean d;

    ByteStreamsKt$iterator$1(BufferedInputStream bufferedInputStream) {
        this.f2787a = bufferedInputStream;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final int c() {
        return this.b;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean d() {
        return this.c;
    }

    public final void b(boolean z) {
        this.d = z;
    }

    public final boolean e() {
        return this.d;
    }

    private final void f() {
        if (!this.c && !this.d) {
            this.b = this.f2787a.read();
            boolean z = true;
            this.c = true;
            if (this.b != -1) {
                z = false;
            }
            this.d = z;
        }
    }

    public boolean hasNext() {
        f();
        return !this.d;
    }

    public byte b() {
        f();
        if (!this.d) {
            byte b2 = (byte) this.b;
            this.c = false;
            return b2;
        }
        throw new NoSuchElementException("Input stream is over.");
    }
}
