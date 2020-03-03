package com.xiaomi.passport.ui.internal;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "run"}, k = 3, mv = {1, 1, 10})
final class WorkThread$doInWorkThread$1 implements Runnable {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ Function0 $work;

    WorkThread$doInWorkThread$1(Function0 function0, Function1 function1) {
        this.$work = function0;
        this.$callback = function1;
    }

    public final void run() {
        final Object invoke = this.$work.invoke();
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ WorkThread$doInWorkThread$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.$callback.invoke(invoke);
            }
        });
    }
}
