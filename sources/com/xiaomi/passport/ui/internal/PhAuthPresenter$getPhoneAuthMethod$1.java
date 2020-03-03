package com.xiaomi.passport.ui.internal;

import com.xiaomi.passport.ui.internal.PhAuthContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/xiaomi/passport/ui/internal/PhoneAuthMethod;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhAuthPresenter$getPhoneAuthMethod$1 extends Lambda implements Function1<PhoneAuthMethod, Unit> {
    final /* synthetic */ PhoneWrapper $phone;
    final /* synthetic */ PhAuthPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhAuthPresenter$getPhoneAuthMethod$1(PhAuthPresenter phAuthPresenter, PhoneWrapper phoneWrapper) {
        super(1);
        this.this$0 = phAuthPresenter;
        this.$phone = phoneWrapper;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PhoneAuthMethod) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull PhoneAuthMethod phoneAuthMethod) {
        Intrinsics.f(phoneAuthMethod, "it");
        this.this$0.getView().dismissProgress();
        switch (phoneAuthMethod) {
            case SMS:
                PhAuthContract.Presenter.DefaultImpls.sendTicket$default(this.this$0, this.$phone, (CaptchaCode) null, 2, (Object) null);
                return;
            case PSW:
                PhAuthContract.View view = this.this$0.getView();
                String phone = this.$phone.getPhone();
                if (phone == null) {
                    Intrinsics.a();
                }
                view.gotoPswSignIn(phone);
                return;
            default:
                return;
        }
    }
}
