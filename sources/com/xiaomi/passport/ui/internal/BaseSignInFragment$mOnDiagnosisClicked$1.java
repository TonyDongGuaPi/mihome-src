package com.xiaomi.passport.ui.internal;

import android.view.View;
import com.xiaomi.passport.ui.diagnosis.DiagnosisLauncher;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class BaseSignInFragment$mOnDiagnosisClicked$1 implements View.OnClickListener {
    final /* synthetic */ BaseSignInFragment this$0;

    BaseSignInFragment$mOnDiagnosisClicked$1(BaseSignInFragment baseSignInFragment) {
        this.this$0 = baseSignInFragment;
    }

    public final void onClick(View view) {
        DiagnosisLauncher access$getMDiagnosisLauncher$p = this.this$0.mDiagnosisLauncher;
        if (access$getMDiagnosisLauncher$p != null) {
            access$getMDiagnosisLauncher$p.onClick();
        }
    }
}
