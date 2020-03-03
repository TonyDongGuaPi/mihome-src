package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class BaseSignInFragment$showSns$1 implements View.OnClickListener {
    final /* synthetic */ AuthProvider $provider;
    final /* synthetic */ BaseSignInFragment this$0;

    BaseSignInFragment$showSns$1(BaseSignInFragment baseSignInFragment, AuthProvider authProvider) {
        this.this$0 = baseSignInFragment;
        this.$provider = authProvider;
    }

    public final void onClick(View view) {
        if (this.this$0.getActivity() != null) {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity == null) {
                Intrinsics.a();
            }
            if (!activity.isFinishing()) {
                SNSAuthProvider sNSAuthProvider = (SNSAuthProvider) this.$provider;
                FragmentActivity activity2 = this.this$0.getActivity();
                if (activity2 == null) {
                    Intrinsics.a();
                }
                Activity activity3 = activity2;
                Bundle arguments = this.this$0.getArguments();
                if (arguments == null) {
                    Intrinsics.a();
                }
                String string = arguments.getString("sid");
                Intrinsics.b(string, "arguments!!.getString(\"sid\")");
                sNSAuthProvider.startLogin(activity3, string);
            }
        }
    }
}
