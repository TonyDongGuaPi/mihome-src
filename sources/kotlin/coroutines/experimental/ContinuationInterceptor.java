package kotlin.coroutines.experimental;

import com.xiaomi.smarthome.homeroom.HomeManager;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/experimental/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", "T", "continuation", "Key", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
@SinceKotlin(version = "1.1")
public interface ContinuationInterceptor extends CoroutineContext.Element {

    /* renamed from: a  reason: collision with root package name */
    public static final Key f2764a = Key.f2765a;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static final class DefaultImpls {
        public static <R> R a(ContinuationInterceptor continuationInterceptor, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            Intrinsics.f(function2, HomeManager.v);
            return CoroutineContext.Element.DefaultImpls.a(continuationInterceptor, r, function2);
        }

        @Nullable
        public static <E extends CoroutineContext.Element> E a(ContinuationInterceptor continuationInterceptor, @NotNull CoroutineContext.Key<E> key) {
            Intrinsics.f(key, "key");
            return CoroutineContext.Element.DefaultImpls.a((CoroutineContext.Element) continuationInterceptor, key);
        }

        @NotNull
        public static CoroutineContext a(ContinuationInterceptor continuationInterceptor, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.f(coroutineContext, "context");
            return CoroutineContext.Element.DefaultImpls.a((CoroutineContext.Element) continuationInterceptor, coroutineContext);
        }

        @NotNull
        public static CoroutineContext b(ContinuationInterceptor continuationInterceptor, @NotNull CoroutineContext.Key<?> key) {
            Intrinsics.f(key, "key");
            return CoroutineContext.Element.DefaultImpls.b(continuationInterceptor, key);
        }
    }

    @NotNull
    <T> Continuation<T> a(@NotNull Continuation<? super T> continuation);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/experimental/ContinuationInterceptor$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "()V", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
    public static final class Key implements CoroutineContext.Key<ContinuationInterceptor> {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Key f2765a = new Key();

        private Key() {
        }
    }
}
