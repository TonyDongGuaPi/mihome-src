package com.xiaomi.passport.ui.internal;

import com.xiaomi.accountsdk.utils.AccountLog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "V", "it", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class Source$getSuccess$1 extends Lambda implements Function1<Throwable, Unit> {
    public static final Source$getSuccess$1 INSTANCE = new Source$getSuccess$1();

    Source$getSuccess$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.f(th, "it");
        if (SourceTool.Companion.getENABLE_TEST()) {
            th.printStackTrace();
        } else {
            AccountLog.e("Source", "request fail", th);
        }
    }
}
