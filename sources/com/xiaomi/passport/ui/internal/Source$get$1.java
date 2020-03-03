package com.xiaomi.passport.ui.internal;

import com.xiaomi.passport.ui.internal.Result;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "V", "it", "Lcom/xiaomi/passport/ui/internal/Result;", "invoke"}, k = 3, mv = {1, 1, 10})
final class Source$get$1 extends Lambda implements Function1<Result<V>, Unit> {
    final /* synthetic */ Function1 $fail;
    final /* synthetic */ Function1 $success;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Source$get$1(Function1 function1, Function1 function12) {
        super(1);
        this.$success = function1;
        this.$fail = function12;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Result) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Result<V> result) {
        Intrinsics.f(result, "it");
        if (result instanceof Result.Success) {
            Function1 function1 = this.$success;
            V value = result.getValue();
            if (value == null) {
                Intrinsics.a();
            }
            function1.invoke(value);
        } else if (result instanceof Result.Failure) {
            Function1 function12 = this.$fail;
            Throwable tr = result.getTr();
            if (tr == null) {
                Intrinsics.a();
            }
            function12.invoke(tr);
        }
    }
}
