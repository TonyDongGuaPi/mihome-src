package com.xiaomi.passport.ui.internal;

import android.widget.Toast;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PswSignInContract;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class PswSignInPresenter$signInWithAuthCredential$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ IdPswBaseAuthCredential $authCredential;
    final /* synthetic */ PswSignInPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PswSignInPresenter$signInWithAuthCredential$2(PswSignInPresenter pswSignInPresenter, IdPswBaseAuthCredential idPswBaseAuthCredential) {
        super(1);
        this.this$0 = pswSignInPresenter;
        this.$authCredential = idPswBaseAuthCredential;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.f(th, "it");
        this.this$0.getView().dismissProgress();
        if (th instanceof IOException) {
            AccountLog.e(this.this$0.TAG, "", th);
            this.this$0.getView().showNetworkError((IOException) th);
        } else if (th instanceof NeedNotificationException) {
            PswSignInContract.View view = this.this$0.getView();
            String notificationUrl = ((NeedNotificationException) th).getNotificationUrl();
            Intrinsics.b(notificationUrl, "it.notificationUrl");
            view.openNotificationUrl(notificationUrl);
        } else if (th instanceof NeedBindSnsException) {
            this.this$0.getView().gotoBindSnsFragment((NeedBindSnsException) th);
        } else if (th instanceof InvalidUserNameException) {
            String string = this.this$0.getContext().getString(R.string.passport_error_user_name);
            if (PassportUI.INSTANCE.getInternational()) {
                string = string + this.this$0.getContext().getString(R.string.passport_international_phone_password_login_tip);
            }
            PswSignInContract.View view2 = this.this$0.getView();
            Intrinsics.b(string, "msg");
            view2.showUserNameError(string);
        } else if (th instanceof InvalidCredentialException) {
            String string2 = this.this$0.getContext().getString(R.string.passport_bad_authentication);
            if (PassportUI.INSTANCE.getInternational()) {
                string2 = string2 + this.this$0.getContext().getString(R.string.passport_international_phone_password_login_tip);
            }
            PswSignInContract.View view3 = this.this$0.getView();
            Intrinsics.b(string2, "msg");
            view3.showPswError(string2);
        } else if (th instanceof CaptchaException) {
            this.this$0.getView().showCaptcha(((CaptchaException) th).getCaptcha(), this.$authCredential);
        } else if (th instanceof NeedVerificationException) {
            NeedVerificationException needVerificationException = (NeedVerificationException) th;
            if (needVerificationException.getStep1Token() == null) {
                Toast.makeText(this.this$0.getContext(), R.string.passport_v_code_error, 1).show();
                return;
            }
            PswSignInContract.View view4 = this.this$0.getView();
            IdPswBaseAuthCredential idPswBaseAuthCredential = this.$authCredential;
            String step1Token = needVerificationException.getStep1Token();
            Intrinsics.b(step1Token, "it.step1Token");
            MetaLoginData metaLoginData = needVerificationException.getMetaLoginData();
            Intrinsics.b(metaLoginData, "it.metaLoginData");
            view4.showVStep2Code(idPswBaseAuthCredential, step1Token, metaLoginData);
        } else {
            AccountLog.e(this.this$0.TAG, "", th);
            this.this$0.getView().showUnKnowError(th);
        }
    }
}
