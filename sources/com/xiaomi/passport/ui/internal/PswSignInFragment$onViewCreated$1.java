package com.xiaomi.passport.ui.internal;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PswSignInContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PswSignInFragment$onViewCreated$1 implements View.OnClickListener {
    final /* synthetic */ PswSignInFragment this$0;

    PswSignInFragment$onViewCreated$1(PswSignInFragment pswSignInFragment) {
        this.this$0 = pswSignInFragment;
    }

    public final void onClick(View view) {
        String str;
        CheckBox checkBox = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_agree_something);
        Intrinsics.b(checkBox, "cb_agree_something");
        if (checkBox.isChecked()) {
            if (this.this$0.getMSignInUserId() != null) {
                str = this.this$0.getMSignInUserId();
            } else {
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) this.this$0._$_findCachedViewById(R.id.userId);
                Intrinsics.b(autoCompleteTextView, "userId");
                str = autoCompleteTextView.getText().toString();
            }
            TextInputEditText textInputEditText = (TextInputEditText) this.this$0._$_findCachedViewById(R.id.password);
            Intrinsics.b(textInputEditText, "password");
            String obj = textInputEditText.getText().toString();
            if (TextUtils.isEmpty(str)) {
                PswSignInFragment pswSignInFragment = this.this$0;
                String string = this.this$0.getString(R.string.passport_empty_user_name);
                Intrinsics.b(string, "getString(R.string.passport_empty_user_name)");
                pswSignInFragment.showUserNameError(string);
            } else if (TextUtils.isEmpty(obj)) {
                PswSignInFragment pswSignInFragment2 = this.this$0;
                String string2 = this.this$0.getString(R.string.passport_empty_password);
                Intrinsics.b(string2, "getString(R.string.passport_empty_password)");
                pswSignInFragment2.showPswError(string2);
            } else {
                PswSignInContract.Presenter presenter = this.this$0.getPresenter();
                if (str == null) {
                    Intrinsics.a();
                }
                presenter.signInIdAndPsw(str, obj);
            }
        } else {
            TextInputLayout textInputLayout = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.tv_user_agreement_tip);
            Intrinsics.b(textInputLayout, "tv_user_agreement_tip");
            textInputLayout.setError(this.this$0.getString(R.string.passport_error_user_agreement_error));
        }
    }
}
