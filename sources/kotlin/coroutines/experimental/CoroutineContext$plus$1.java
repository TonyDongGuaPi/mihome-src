package kotlin.coroutines.experimental;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/experimental/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 1, 13})
final class CoroutineContext$plus$1 extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    @NotNull
    public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
        CombinedContext combinedContext;
        Intrinsics.f(coroutineContext, "acc");
        Intrinsics.f(element, BindingXConstants.i);
        CoroutineContext b = coroutineContext.b(element.a());
        if (b == EmptyCoroutineContext.f2766a) {
            return element;
        }
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) b.a(ContinuationInterceptor.f2764a);
        if (continuationInterceptor == null) {
            combinedContext = new CombinedContext(b, element);
        } else {
            CoroutineContext b2 = b.b(ContinuationInterceptor.f2764a);
            if (b2 == EmptyCoroutineContext.f2766a) {
                combinedContext = new CombinedContext(element, continuationInterceptor);
            } else {
                combinedContext = new CombinedContext(new CombinedContext(b2, element), continuationInterceptor);
            }
        }
        return combinedContext;
    }
}