package com.xiaomi.passport.ui.internal;

import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.EditText;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 10})
final class PswSignInFragment$showVStep2Code$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ IdPswBaseAuthCredential $authCredential;
    final /* synthetic */ CheckBox $cb_trust;
    final /* synthetic */ MetaLoginData $metaLoginData;
    final /* synthetic */ String $step1Token;
    final /* synthetic */ EditText $vcode_inpout;
    final /* synthetic */ PswSignInFragment this$0;

    PswSignInFragment$showVStep2Code$dialog$1(PswSignInFragment pswSignInFragment, IdPswBaseAuthCredential idPswBaseAuthCredential, String str, MetaLoginData metaLoginData, EditText editText, CheckBox checkBox) {
        this.this$0 = pswSignInFragment;
        this.$authCredential = idPswBaseAuthCredential;
        this.$step1Token = str;
        this.$metaLoginData = metaLoginData;
        this.$vcode_inpout = editText;
        this.$cb_trust = checkBox;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.getPresenter().signInVStep2(this.$authCredential.getId(), this.$step1Token, this.$metaLoginData, this.$vcode_inpout.getText().toString(), this.$cb_trust.isChecked());
    }
}
