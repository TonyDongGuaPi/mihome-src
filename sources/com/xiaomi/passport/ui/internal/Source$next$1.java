package com.xiaomi.passport.ui.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"com/xiaomi/passport/ui/internal/Source$next$1", "Lcom/xiaomi/passport/ui/internal/Source;", "(Lcom/xiaomi/passport/ui/internal/Source;Lkotlin/jvm/functions/Function1;)V", "process", "()Ljava/lang/Object;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class Source$next$1 extends Source<T> {
    final /* synthetic */ Function1 $func1;
    final /* synthetic */ Source this$0;

    Source$next$1(Source source, Function1 function1) {
        this.this$0 = source;
        this.$func1 = function1;
    }

    public T process() {
        return this.$func1.invoke(this.this$0.process());
    }
}
