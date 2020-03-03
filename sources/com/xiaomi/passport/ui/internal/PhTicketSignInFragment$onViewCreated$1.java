package com.xiaomi.passport.ui.internal;

import android.view.View;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PhTicketSignInContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInFragment$onViewCreated$1 implements View.OnClickListener {
    final /* synthetic */ PhTicketSignInFragment this$0;

    PhTicketSignInFragment$onViewCreated$1(PhTicketSignInFragment phTicketSignInFragment) {
        this.this$0 = phTicketSignInFragment;
    }

    public final void onClick(View view) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.action_get_ph_ticket);
        if (textView != null) {
            textView.setClickable(false);
        }
        if (this.this$0.mPhoneWrapper != null) {
            PhTicketSignInContract.Presenter access$getPresenter$p = PhTicketSignInFragment.access$getPresenter$p(this.this$0);
            PhoneWrapper access$getMPhoneWrapper$p = this.this$0.mPhoneWrapper;
            if (access$getMPhoneWrapper$p == null) {
                Intrinsics.a();
            }
            PhTicketSignInContract.Presenter.DefaultImpls.sendTicket$default(access$getPresenter$p, access$getMPhoneWrapper$p, (CaptchaCode) null, 2, (Object) null);
        }
    }
}
