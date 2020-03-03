package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a\f\u0010\u000b\u001a\u00020\u0006*\u00020\u0005H\u0007\u001a\f\u0010\f\u001a\u00020\t*\u00020\bH\u0007\u001a^\u0010\r\u001a\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0010\"\u0004\b\u0002\u0010\u0011*\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eH\u0000\u001aL\u0010\r\u001a\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0011*\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013H\u0000\u001a:\u0010\r\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\"\u0004\b\u0000\u0010\u0011*\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014H\u0000¨\u0006\u0015"}, d2 = {"toContinuation", "Lkotlin/coroutines/Continuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "toContinuationInterceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "toCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "toExperimentalContinuation", "toExperimentalContinuationInterceptor", "toExperimentalCoroutineContext", "toExperimentalSuspendFunction", "Lkotlin/Function3;", "T1", "T2", "R", "", "Lkotlin/Function2;", "Lkotlin/Function1;", "kotlin-stdlib_coroutines"}, k = 2, mv = {1, 1, 13})
public final class CoroutinesMigrationKt {
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> Continuation<T> a(@NotNull kotlin.coroutines.Continuation<? super T> continuation) {
        Continuation<T> a2;
        Intrinsics.f(continuation, "receiver$0");
        ContinuationMigration continuationMigration = (ContinuationMigration) (!(continuation instanceof ContinuationMigration) ? null : continuation);
        return (continuationMigration == null || (a2 = continuationMigration.a()) == null) ? new ExperimentalContinuationMigration<>(continuation) : a2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> kotlin.coroutines.Continuation<T> a(@NotNull Continuation<? super T> continuation) {
        kotlin.coroutines.Continuation<T> a2;
        Intrinsics.f(continuation, "receiver$0");
        ExperimentalContinuationMigration experimentalContinuationMigration = (ExperimentalContinuationMigration) (!(continuation instanceof ExperimentalContinuationMigration) ? null : continuation);
        return (experimentalContinuationMigration == null || (a2 = experimentalContinuationMigration.a()) == null) ? new ContinuationMigration<>(continuation) : a2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final CoroutineContext a(@NotNull kotlin.coroutines.CoroutineContext coroutineContext) {
        CoroutineContext coroutineContext2;
        Intrinsics.f(coroutineContext, "receiver$0");
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.f2758a);
        ContextMigration contextMigration = (ContextMigration) coroutineContext.get(ContextMigration.f2775a);
        kotlin.coroutines.CoroutineContext minusKey = coroutineContext.minusKey(ContinuationInterceptor.f2758a).minusKey(ContextMigration.f2775a);
        if (contextMigration == null || (coroutineContext2 = contextMigration.b()) == null) {
            coroutineContext2 = EmptyCoroutineContext.f2766a;
        }
        if (minusKey != kotlin.coroutines.EmptyCoroutineContext.INSTANCE) {
            coroutineContext2 = coroutineContext2.a((CoroutineContext) new ExperimentalContextMigration(minusKey));
        }
        return continuationInterceptor == null ? coroutineContext2 : coroutineContext2.a((CoroutineContext) a(continuationInterceptor));
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final kotlin.coroutines.CoroutineContext a(@NotNull CoroutineContext coroutineContext) {
        kotlin.coroutines.CoroutineContext coroutineContext2;
        Intrinsics.f(coroutineContext, "receiver$0");
        kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor = (kotlin.coroutines.experimental.ContinuationInterceptor) coroutineContext.a(kotlin.coroutines.experimental.ContinuationInterceptor.f2764a);
        ExperimentalContextMigration experimentalContextMigration = (ExperimentalContextMigration) coroutineContext.a(ExperimentalContextMigration.f2777a);
        CoroutineContext b = coroutineContext.b(kotlin.coroutines.experimental.ContinuationInterceptor.f2764a).b(ExperimentalContextMigration.f2777a);
        if (experimentalContextMigration == null || (coroutineContext2 = experimentalContextMigration.b()) == null) {
            coroutineContext2 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE;
        }
        if (b != EmptyCoroutineContext.f2766a) {
            coroutineContext2 = coroutineContext2.plus(new ContextMigration(b));
        }
        return continuationInterceptor == null ? coroutineContext2 : coroutineContext2.plus(a(continuationInterceptor));
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final kotlin.coroutines.experimental.ContinuationInterceptor a(@NotNull ContinuationInterceptor continuationInterceptor) {
        kotlin.coroutines.experimental.ContinuationInterceptor b;
        Intrinsics.f(continuationInterceptor, "receiver$0");
        ContinuationInterceptorMigration continuationInterceptorMigration = (ContinuationInterceptorMigration) (!(continuationInterceptor instanceof ContinuationInterceptorMigration) ? null : continuationInterceptor);
        return (continuationInterceptorMigration == null || (b = continuationInterceptorMigration.b()) == null) ? new ExperimentalContinuationInterceptorMigration(continuationInterceptor) : b;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ContinuationInterceptor a(@NotNull kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor) {
        ContinuationInterceptor b;
        Intrinsics.f(continuationInterceptor, "receiver$0");
        ExperimentalContinuationInterceptorMigration experimentalContinuationInterceptorMigration = (ExperimentalContinuationInterceptorMigration) (!(continuationInterceptor instanceof ExperimentalContinuationInterceptorMigration) ? null : continuationInterceptor);
        return (experimentalContinuationInterceptorMigration == null || (b = experimentalContinuationInterceptorMigration.b()) == null) ? new ContinuationInterceptorMigration(continuationInterceptor) : b;
    }

    @NotNull
    public static final <R> Function1<Continuation<? super R>, Object> a(@NotNull Function1<? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.f(function1, "receiver$0");
        return new ExperimentalSuspendFunction0Migration<>(function1);
    }

    @NotNull
    public static final <T1, R> Function2<T1, Continuation<? super R>, Object> a(@NotNull Function2<? super T1, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.f(function2, "receiver$0");
        return new ExperimentalSuspendFunction1Migration<>(function2);
    }

    @NotNull
    public static final <T1, T2, R> Function3<T1, T2, Continuation<? super R>, Object> a(@NotNull Function3<? super T1, ? super T2, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.f(function3, "receiver$0");
        return new ExperimentalSuspendFunction2Migration<>(function3);
    }
}
