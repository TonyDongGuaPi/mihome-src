package com.xiaomi.passport.ui.internal;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PhAuthContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PhAuthFragment$onViewCreated$1 implements View.OnClickListener {
    final /* synthetic */ PhAuthFragment this$0;

    PhAuthFragment$onViewCreated$1(PhAuthFragment phAuthFragment) {
        this.this$0 = phAuthFragment;
    }

    public final void onClick(View view) {
        CheckBox checkBox = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_agree_something);
        Intrinsics.b(checkBox, "cb_agree_something");
        if (checkBox.isChecked()) {
            PhAuthContract.Presenter presenter = this.this$0.getPresenter();
            PhoneViewWrapper access$getMPhoneViewWrapper$p = this.this$0.mPhoneViewWrapper;
            presenter.getPhoneAuthMethod(access$getMPhoneViewWrapper$p != null ? access$getMPhoneViewWrapper$p.getPhoneWrapper() : null);
            return;
        }
        TextInputLayout textInputLayout = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.user_agreement_error_tip);
        Intrinsics.b(textInputLayout, "user_agreement_error_tip");
        textInputLayout.setError(this.this$0.getString(R.string.passport_error_user_agreement_error));
    }
}
