package com.xiaomi.passport.ui.internal;

import android.os.AsyncTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lcom/xiaomi/passport/ui/internal/WorkThread;", "T", "", "()V", "doInWorkThread", "", "work", "Lkotlin/Function0;", "callback", "Lkotlin/Function1;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WorkThread<T> {
    public final void doInWorkThread(@NotNull Function0<? extends T> function0, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.f(function0, "work");
        Intrinsics.f(function1, "callback");
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new WorkThread$doInWorkThread$1(function0, function1));
    }
}
