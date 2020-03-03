package kotlin.coroutines.experimental;

import com.alipay.mobile.common.logging.api.LogCategory;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0001\u0018\u0000 \u0015*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u0015\u0016B\u0015\b\u0011\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004B\u001f\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0001J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "delegate", "(Lkotlin/coroutines/experimental/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "result", "getResult", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "Companion", "Fail", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
public final class SafeContinuation<T> implements Continuation<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f2767a = new Companion((DefaultConstructorMarker) null);
    private static final Object d = new Object();
    private static final Object e = new Object();
    private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> f = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "b");
    private volatile Object b;
    private final Continuation<T> c;

    public SafeContinuation(@NotNull Continuation<? super T> continuation, @Nullable Object obj) {
        Intrinsics.f(continuation, "delegate");
        this.c = continuation;
        this.b = obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @PublishedApi
    public SafeContinuation(@NotNull Continuation<? super T> continuation) {
        this(continuation, d);
        Intrinsics.f(continuation, "delegate");
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.c.getContext();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002RZ\u0010\u0003\u001aF\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001 \u0006*\"\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00040\u00048\u0002X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/experimental/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT$annotations", "RESUMED", "UNDECIDED", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void a() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Fail;", "", "exception", "", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
    private static final class Fail {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        private final Throwable f2768a;

        public Fail(@NotNull Throwable th) {
            Intrinsics.f(th, LogCategory.CATEGORY_EXCEPTION);
            this.f2768a = th;
        }

        @NotNull
        public final Throwable a() {
            return this.f2768a;
        }
    }

    public void resume(T t) {
        while (true) {
            Object obj = this.b;
            if (obj == d) {
                if (f.compareAndSet(this, d, t)) {
                    return;
                }
            } else if (obj != IntrinsicsKt.b()) {
                throw new IllegalStateException("Already resumed");
            } else if (f.compareAndSet(this, IntrinsicsKt.b(), e)) {
                this.c.resume(t);
                return;
            }
        }
    }

    public void resumeWithException(@NotNull Throwable th) {
        Intrinsics.f(th, LogCategory.CATEGORY_EXCEPTION);
        while (true) {
            Object obj = this.b;
            if (obj == d) {
                if (f.compareAndSet(this, d, new Fail(th))) {
                    return;
                }
            } else if (obj != IntrinsicsKt.b()) {
                throw new IllegalStateException("Already resumed");
            } else if (f.compareAndSet(this, IntrinsicsKt.b(), e)) {
                this.c.resumeWithException(th);
                return;
            }
        }
    }

    @Nullable
    @PublishedApi
    public final Object a() {
        Object obj = this.b;
        if (obj == d) {
            if (f.compareAndSet(this, d, IntrinsicsKt.b())) {
                return IntrinsicsKt.b();
            }
            obj = this.b;
        }
        if (obj == e) {
            return IntrinsicsKt.b();
        }
        if (!(obj instanceof Fail)) {
            return obj;
        }
        throw ((Fail) obj).a();
    }
}
