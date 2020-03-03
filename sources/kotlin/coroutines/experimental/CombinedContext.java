package kotlin.coroutines.experimental;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.homeroom.HomeManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0000H\u0002J\u0013\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0014\u0010\u001e\u001a\u00020\u00012\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\""}, d2 = {"Lkotlin/coroutines/experimental/CombinedContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "left", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/CoroutineContext$Element;)V", "getElement", "()Lkotlin/coroutines/experimental/CoroutineContext$Element;", "getLeft", "()Lkotlin/coroutines/experimental/CoroutineContext;", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "kotlin-stdlib_coroutines"}, k = 1, mv = {1, 1, 13})
public final class CombinedContext implements CoroutineContext {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final CoroutineContext f2763a;
    @NotNull
    private final CoroutineContext.Element b;

    public CombinedContext(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
        Intrinsics.f(coroutineContext, "left");
        Intrinsics.f(element, BindingXConstants.i);
        this.f2763a = coroutineContext;
        this.b = element;
    }

    @NotNull
    public final CoroutineContext a() {
        return this.f2763a;
    }

    @NotNull
    public CoroutineContext a(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.f(coroutineContext, "context");
        return CoroutineContext.DefaultImpls.a(this, coroutineContext);
    }

    @NotNull
    public final CoroutineContext.Element b() {
        return this.b;
    }

    @Nullable
    public <E extends CoroutineContext.Element> E a(@NotNull CoroutineContext.Key<E> key) {
        Intrinsics.f(key, "key");
        CoroutineContext coroutineContext = this;
        while (true) {
            CombinedContext combinedContext = (CombinedContext) coroutineContext;
            E a2 = combinedContext.b.a(key);
            if (a2 != null) {
                return a2;
            }
            coroutineContext = combinedContext.f2763a;
            if (!(coroutineContext instanceof CombinedContext)) {
                return coroutineContext.a(key);
            }
        }
    }

    public <R> R a(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        Intrinsics.f(function2, HomeManager.v);
        return function2.invoke(this.f2763a.a(r, function2), this.b);
    }

    @NotNull
    public CoroutineContext b(@NotNull CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        if (this.b.a(key) != null) {
            return this.f2763a;
        }
        CoroutineContext b2 = this.f2763a.b(key);
        if (b2 == this.f2763a) {
            return this;
        }
        if (b2 == EmptyCoroutineContext.f2766a) {
            return this.b;
        }
        return new CombinedContext(b2, this.b);
    }

    private final int c() {
        if (this.f2763a instanceof CombinedContext) {
            return ((CombinedContext) this.f2763a).c() + 1;
        }
        return 2;
    }

    private final boolean a(CoroutineContext.Element element) {
        return Intrinsics.a((Object) a(element.a()), (Object) element);
    }

    private final boolean a(CombinedContext combinedContext) {
        while (a(combinedContext.b)) {
            CoroutineContext coroutineContext = combinedContext.f2763a;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else if (coroutineContext != null) {
                return a((CoroutineContext.Element) coroutineContext);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) obj;
                if (combinedContext.c() != c() || !combinedContext.a(this)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f2763a.hashCode() + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return Operators.ARRAY_START_STR + ((String) a("", CombinedContext$toString$1.INSTANCE)) + Operators.ARRAY_END_STR;
    }
}
