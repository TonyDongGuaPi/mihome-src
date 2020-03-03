package com.xiaomi.passport.ui.internal;

import com.xiaomi.accountsdk.account.data.AccountInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInPresenter$signInWithAuthCredential$1 extends Lambda implements Function1<AccountInfo, Unit> {
    final /* synthetic */ PhoneSmsAuthCredential $authCredential;
    final /* synthetic */ PhTicketSignInPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhTicketSignInPresenter$signInWithAuthCredential$1(PhTicketSignInPresenter phTicketSignInPresenter, PhoneSmsAuthCredential phoneSmsAuthCredential) {
        super(1);
        this.this$0 = phTicketSignInPresenter;
        this.$authCredential = phoneSmsAuthCredential;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AccountInfo) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull AccountInfo accountInfo) {
        Intrinsics.f(accountInfo, "it");
        this.this$0.getView().dismissProgress();
        this.this$0.getView().loginSuccess(accountInfo);
        if (this.$authCredential.getPhone().getActivateInfo() != null) {
            this.this$0.invalidateCachePhoneNum(this.this$0.getContext(), this.$authCredential.getPhone());
        }
    }
}
