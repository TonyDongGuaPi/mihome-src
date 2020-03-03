package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001e\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlin/coroutines/experimental/migration/ContinuationMigration;", "T", "Lkotlin/coroutines/Continuation;", "continuation", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlin/coroutines/experimental/Continuation;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getContinuation", "()Lkotlin/coroutines/experimental/Continuation;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
final class ContinuationMigration<T> implements Continuation<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final CoroutineContext f2776a = CoroutinesMigrationKt.a(this.b.getContext());
    @NotNull
    private final kotlin.coroutines.experimental.Continuation<T> b;

    public ContinuationMigration(@NotNull kotlin.coroutines.experimental.Continuation<? super T> continuation) {
        Intrinsics.f(continuation, "continuation");
        this.b = continuation;
    }

    @NotNull
    public final kotlin.coroutines.experimental.Continuation<T> a() {
        return this.b;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.f2776a;
    }

    public void resumeWith(@NotNull Object obj) {
        if (Result.m45isSuccessimpl(obj)) {
            this.b.resume(obj);
        }
        Throwable r2 = Result.m42exceptionOrNullimpl(obj);
        if (r2 != null) {
            this.b.resumeWithException(r2);
        }
    }
}
