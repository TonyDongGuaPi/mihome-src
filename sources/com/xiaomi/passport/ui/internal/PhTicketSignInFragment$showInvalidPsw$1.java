package com.xiaomi.passport.ui.internal;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInFragment$showInvalidPsw$1 implements View.OnClickListener {
    final /* synthetic */ ChoosePhoneSmsAuthCredential $authCredential;
    final /* synthetic */ PhTicketSignInFragment this$0;

    PhTicketSignInFragment$showInvalidPsw$1(PhTicketSignInFragment phTicketSignInFragment, ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        this.this$0 = phTicketSignInFragment;
        this.$authCredential = choosePhoneSmsAuthCredential;
    }

    public final void onClick(View view) {
        ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential = this.$authCredential;
        TextInputEditText textInputEditText = (TextInputEditText) this.this$0._$_findCachedViewById(R.id.password);
        Intrinsics.b(textInputEditText, "password");
        choosePhoneSmsAuthCredential.setNewPsw(textInputEditText.getText().toString());
        PhTicketSignInFragment.access$getPresenter$p(this.this$0).chooseSignUp(this.$authCredential);
    }
}
