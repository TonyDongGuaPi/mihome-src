package com.xiaomi.passport.ui.internal;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.passport.ui.internal.Result;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "V", "run"}, k = 3, mv = {1, 1, 10})
final class Source$getAsync$1 implements Runnable {
    final /* synthetic */ Function1 $observer;
    final /* synthetic */ Source this$0;

    Source$getAsync$1(Source source, Function1 function1) {
        this.this$0 = source;
        this.$observer = function1;
    }

    public final void run() {
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            final Object process = this.this$0.process();
            handler.post(new Runnable(this) {
                final /* synthetic */ Source$getAsync$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    this.this$0.$observer.invoke(new Result.Success(process));
                }
            });
        } catch (Throwable th) {
            handler.post(new Runnable(this) {
                final /* synthetic */ Source$getAsync$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    this.this$0.$observer.invoke(new Result.Failure(th));
                }
            });
        }
    }
}
