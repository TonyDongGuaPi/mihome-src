package com.xiaomi.passport.ui.internal;

import android.os.Bundle;
import android.view.View;
import com.xiaomi.passport.ui.internal.SignInContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PhAuthFragment$onViewCreated$2 implements View.OnClickListener {
    final /* synthetic */ PhAuthFragment this$0;

    PhAuthFragment$onViewCreated$2(PhAuthFragment phAuthFragment) {
        this.this$0 = phAuthFragment;
    }

    public final void onClick(View view) {
        PhAuthFragment phAuthFragment = this.this$0;
        BaseAuthProvider access$getIdPswAuthProvider$p = this.this$0.idPswAuthProvider;
        Bundle arguments = this.this$0.getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        String string = arguments.getString("sid");
        Intrinsics.b(string, "arguments!!.getString(\"sid\")");
        SignInContract.View.DefaultImpls.gotoFragment$default(phAuthFragment, access$getIdPswAuthProvider$p.getFragment(string, this.this$0.getDefaultCountryCodeWithPrefix()), false, 2, (Object) null);
    }
}
