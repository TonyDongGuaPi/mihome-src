package kotlin.coroutines.experimental.migration;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.xiaomi.smarthome.homeroom.HomeManager;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lkotlin/coroutines/experimental/migration/ExperimentalContinuationInterceptorMigration;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "interceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "(Lkotlin/coroutines/ContinuationInterceptor;)V", "getInterceptor", "()Lkotlin/coroutines/ContinuationInterceptor;", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", "T", "continuation", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
final class ExperimentalContinuationInterceptorMigration implements ContinuationInterceptor {
    @NotNull
    private final kotlin.coroutines.ContinuationInterceptor b;

    public ExperimentalContinuationInterceptorMigration(@NotNull kotlin.coroutines.ContinuationInterceptor continuationInterceptor) {
        Intrinsics.f(continuationInterceptor, BindingXConstants.h);
        this.b = continuationInterceptor;
    }

    public <R> R a(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        Intrinsics.f(function2, HomeManager.v);
        return ContinuationInterceptor.DefaultImpls.a(this, r, function2);
    }

    @Nullable
    public <E extends CoroutineContext.Element> E a(@NotNull CoroutineContext.Key<E> key) {
        Intrinsics.f(key, "key");
        return ContinuationInterceptor.DefaultImpls.a((ContinuationInterceptor) this, key);
    }

    @NotNull
    public CoroutineContext a(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.f(coroutineContext, "context");
        return ContinuationInterceptor.DefaultImpls.a((ContinuationInterceptor) this, coroutineContext);
    }

    @NotNull
    public final kotlin.coroutines.ContinuationInterceptor b() {
        return this.b;
    }

    @NotNull
    public CoroutineContext b(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        return ContinuationInterceptor.DefaultImpls.b(this, key);
    }

    @NotNull
    public CoroutineContext.Key<?> a() {
        return ContinuationInterceptor.f2764a;
    }

    @NotNull
    public <T> Continuation<T> a(@NotNull Continuation<? super T> continuation) {
        Intrinsics.f(continuation, "continuation");
        return CoroutinesMigrationKt.a(this.b.a(CoroutinesMigrationKt.a(continuation)));
    }
}