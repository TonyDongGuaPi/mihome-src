package com.xiaomi.passport.ui.internal;

import android.support.design.widget.TextInputLayout;
import android.widget.CompoundButton;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 10})
final class PhAuthFragment$onViewCreated$3 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ PhAuthFragment this$0;

    PhAuthFragment$onViewCreated$3(PhAuthFragment phAuthFragment) {
        this.this$0 = phAuthFragment;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            TextInputLayout textInputLayout = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.user_agreement_error_tip);
            Intrinsics.b(textInputLayout, "user_agreement_error_tip");
            textInputLayout.setError("");
        }
    }
}
