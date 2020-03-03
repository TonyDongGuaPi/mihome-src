package com.xiaomi.passport.ui.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "captchaCode", "", "lastIck", "invoke"}, k = 3, mv = {1, 1, 10})
final class PswSignInFragment$showCaptcha$1 extends Lambda implements Function2<String, String, Unit> {
    final /* synthetic */ IdPswBaseAuthCredential $authCredential;
    final /* synthetic */ PswSignInFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PswSignInFragment$showCaptcha$1(PswSignInFragment pswSignInFragment, IdPswBaseAuthCredential idPswBaseAuthCredential) {
        super(2);
        this.this$0 = pswSignInFragment;
        this.$authCredential = idPswBaseAuthCredential;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (String) obj2);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "captchaCode");
        Intrinsics.f(str2, "lastIck");
        this.$authCredential.addCaptcha(str, str2);
        this.this$0.getPresenter().signInWithAuthCredential(this.$authCredential);
    }
}
