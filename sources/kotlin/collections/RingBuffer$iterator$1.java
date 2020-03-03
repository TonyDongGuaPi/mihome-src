package kotlin.collections;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"kotlin/collections/RingBuffer$iterator$1", "Lkotlin/collections/AbstractIterator;", "count", "", "index", "computeNext", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class RingBuffer$iterator$1 extends AbstractIterator<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ RingBuffer f2736a;
    private int b;
    private int c;

    RingBuffer$iterator$1(RingBuffer ringBuffer) {
        this.f2736a = ringBuffer;
        this.b = ringBuffer.size();
        this.c = ringBuffer.c;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.b == 0) {
            b();
            return;
        }
        a(this.f2736a.b[this.c]);
        this.c = (this.c + 1) % this.f2736a.c();
        this.b--;
    }
}
