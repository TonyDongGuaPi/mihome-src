package com.xiaomi.passport.ui.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInPresenter$sendTicket$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ PhTicketSignInPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhTicketSignInPresenter$sendTicket$1(PhTicketSignInPresenter phTicketSignInPresenter) {
        super(1);
        this.this$0 = phTicketSignInPresenter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull String str) {
        Intrinsics.f(str, "it");
        this.this$0.getView().sendTicketSuccess();
    }
}
