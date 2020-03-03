package com.xiaomi.passport.ui.internal;

import android.content.DialogInterface;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInFragment$chooseToSignInOrSignUp$dialog$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ PhoneSmsAuthCredential $authCredential;
    final /* synthetic */ RegisterUserInfo $userInfo;
    final /* synthetic */ PhTicketSignInFragment this$0;

    PhTicketSignInFragment$chooseToSignInOrSignUp$dialog$2(PhTicketSignInFragment phTicketSignInFragment, PhoneSmsAuthCredential phoneSmsAuthCredential, RegisterUserInfo registerUserInfo) {
        this.this$0 = phTicketSignInFragment;
        this.$authCredential = phoneSmsAuthCredential;
        this.$userInfo = registerUserInfo;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PhTicketSignInFragment.access$getPresenter$p(this.this$0).chooseSignIn(this.$authCredential, this.$userInfo);
    }
}