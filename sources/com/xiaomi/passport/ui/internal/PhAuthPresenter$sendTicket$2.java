package com.xiaomi.passport.ui.internal;

import android.widget.Toast;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneNumException;
import com.xiaomi.accountsdk.account.exception.ReachLimitException;
import com.xiaomi.accountsdk.account.exception.TokenExpiredException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhAuthPresenter$sendTicket$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ PhoneWrapper $phone;
    final /* synthetic */ PhAuthPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhAuthPresenter$sendTicket$2(PhAuthPresenter phAuthPresenter, PhoneWrapper phoneWrapper) {
        super(1);
        this.this$0 = phAuthPresenter;
        this.$phone = phoneWrapper;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.f(th, "it");
        this.this$0.getView().dismissProgress();
        if (th instanceof CaptchaException) {
            this.this$0.getView().showCaptcha(((CaptchaException) th).getCaptcha(), this.$phone);
        } else if (th instanceof IOException) {
            this.this$0.getView().showNetworkError((IOException) th);
        } else if (th instanceof ReachLimitException) {
            this.this$0.getView().showPhoneNumError(R.string.passport_send_too_many_sms);
        } else if (th instanceof InvalidPhoneNumException) {
            this.this$0.getView().showPhoneNumError(R.string.passport_error_phone_error);
        } else if (th instanceof TokenExpiredException) {
            this.this$0.invalidateCathePhoneNum(this.this$0.getContext(), this.$phone);
            Toast.makeText(this.this$0.getContext(), R.string.passport_activate_token_expired, 0).show();
            this.this$0.getView().clearPhonePopList();
        } else {
            AccountLog.e(this.this$0.TAG, "", th);
            this.this$0.getView().showUnKnowError(th);
        }
    }
}
