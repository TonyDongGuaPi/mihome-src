package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a<\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00012\u001a\b\u0004\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\f\u0012\u0004\u0012\u00020\r0\u000bH\bø\u0001\u0000\u001a3\u0010\u000e\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u001a\b\u0004\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0004\u0012\u00020\r0\u000bHHø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a]\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016¢\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a&\u0010\u001a\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001b\u001a\u0002H\bH\b¢\u0006\u0002\u0010\u001c\u001a!\u0010\u001d\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\b\u001a>\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010!\u001aW\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016¢\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\"\u001b\u0010\u0000\u001a\u00020\u00018Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Continuation", "Lkotlin/coroutines/Continuation;", "T", "context", "resumeWith", "Lkotlin/Function1;", "Lkotlin/Result;", "", "suspendCoroutine", "block", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCoroutine", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resume", "value", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "resumeWithException", "exception", "", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
public final class ContinuationKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static /* synthetic */ void a() {
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> void a(@NotNull Continuation<? super T> continuation, T t) {
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m39constructorimpl(t));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> void a(@NotNull Continuation<? super T> continuation, Throwable th) {
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m39constructorimpl(ResultKt.a(th)));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Continuation<T> a(CoroutineContext coroutineContext, Function1<? super Result<? extends T>, Unit> function1) {
        return new ContinuationKt$Continuation$1(coroutineContext, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> Continuation<Unit> a(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.f(function1, "receiver$0");
        Intrinsics.f(continuation, "completion");
        return new SafeContinuation<>(IntrinsicsKt.a(IntrinsicsKt.a(function1, continuation)), IntrinsicsKt.b());
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <R, T> Continuation<Unit> a(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Intrinsics.f(function2, "receiver$0");
        Intrinsics.f(continuation, "completion");
        return new SafeContinuation<>(IntrinsicsKt.a(IntrinsicsKt.a(function2, r, continuation)), IntrinsicsKt.b());
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void b(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Intrinsics.f(function1, "receiver$0");
        Intrinsics.f(continuation, "completion");
        Continuation<Unit> a2 = IntrinsicsKt.a(IntrinsicsKt.a(function1, continuation));
        Unit unit = Unit.f2693a;
        Result.Companion companion = Result.Companion;
        a2.resumeWith(Result.m39constructorimpl(unit));
    }

    @SinceKotlin(version = "1.3")
    public static final <R, T> void b(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Intrinsics.f(function2, "receiver$0");
        Intrinsics.f(continuation, "completion");
        Continuation<Unit> a2 = IntrinsicsKt.a(IntrinsicsKt.a(function2, r, continuation));
        Unit unit = Unit.f2693a;
        Result.Companion companion = Result.Companion;
        a2.resumeWith(Result.m39constructorimpl(unit));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object c(Function1<? super Continuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        InlineMarker.a(0);
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.a(continuation));
        function1.invoke(safeContinuation);
        Object a2 = safeContinuation.a();
        if (a2 == IntrinsicsKt.b()) {
            DebugProbesKt.c(continuation);
        }
        InlineMarker.a(1);
        return a2;
    }

    private static final CoroutineContext b() {
        throw new NotImplementedError("Implemented as intrinsic");
    }
}
