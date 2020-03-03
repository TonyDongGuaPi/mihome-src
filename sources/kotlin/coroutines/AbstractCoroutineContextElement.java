package kotlin.coroutines;

import com.xiaomi.smarthome.homeroom.HomeManager;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
@SinceKotlin(version = "1.3")
public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final CoroutineContext.Key<?> f2757a;

    public AbstractCoroutineContextElement(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        this.f2757a = key;
    }

    @NotNull
    public CoroutineContext.Key<?> a() {
        return this.f2757a;
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        Intrinsics.f(function2, HomeManager.v);
        return CoroutineContext.Element.DefaultImpls.a(this, r, function2);
    }

    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        Intrinsics.f(key, "key");
        return CoroutineContext.Element.DefaultImpls.a((CoroutineContext.Element) this, key);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        return CoroutineContext.Element.DefaultImpls.b(this, key);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.f(coroutineContext, "context");
        return CoroutineContext.Element.DefaultImpls.a((CoroutineContext.Element) this, coroutineContext);
    }
}