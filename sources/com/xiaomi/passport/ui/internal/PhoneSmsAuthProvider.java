package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.ui.internal.PhAuthContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0012H\u0014J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0016"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhoneSmsAuthProvider;", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "()V", "getFragment", "Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;", "sid", "", "setPresenter", "", "fragment", "signInOrSignUpWithChoice", "Lcom/xiaomi/passport/ui/internal/Source;", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "credential", "Lcom/xiaomi/passport/ui/internal/ChoosePhoneSmsAuthCredential;", "signInWithAuthCredential", "context", "Landroid/content/Context;", "Lcom/xiaomi/passport/ui/internal/AuthCredential;", "trySignInWithAuthCredential", "authCredential", "Lcom/xiaomi/passport/ui/internal/PhoneSmsAuthCredential;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhoneSmsAuthProvider extends BaseAuthProvider {
    public PhoneSmsAuthProvider() {
        super(PassportUI.PHONE_SMS_AUTH_PROVIDER);
    }

    public void setPresenter(@NotNull String str, @NotNull BaseSignInFragment baseSignInFragment) {
        Intrinsics.f(str, "sid");
        Intrinsics.f(baseSignInFragment, "fragment");
        PhAuthFragment phAuthFragment = (PhAuthFragment) baseSignInFragment;
        Context context = phAuthFragment.getContext();
        if (context == null) {
            Intrinsics.a();
        }
        phAuthFragment.setPresenter(new PhAuthPresenter(context, str, (PhAuthContract.View) baseSignInFragment, (String) null, 8, (DefaultConstructorMarker) null));
    }

    @NotNull
    public BaseSignInFragment getFragment(@NotNull String str) {
        Intrinsics.f(str, "sid");
        return PhAuthFragment.Companion.newInstance(str);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Source<AccountInfo> signInWithAuthCredential(@NotNull Context context, @NotNull AuthCredential authCredential) {
        Intrinsics.f(context, "context");
        Intrinsics.f(authCredential, "credential");
        if (authCredential instanceof ChoosePhoneSmsAuthCredential) {
            return signInOrSignUpWithChoice((ChoosePhoneSmsAuthCredential) authCredential);
        }
        if (authCredential instanceof PhoneSmsAuthCredential) {
            return trySignInWithAuthCredential((PhoneSmsAuthCredential) authCredential);
        }
        throw new IllegalStateException("not support originAuthCredential:" + authCredential);
    }

    private final Source<AccountInfo> signInOrSignUpWithChoice(ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        if (choosePhoneSmsAuthCredential.getSignIn()) {
            return Source.Companion.from(new PhoneSmsAuthProvider$signInOrSignUpWithChoice$1(this, choosePhoneSmsAuthCredential));
        }
        return Source.Companion.from(new PhoneSmsAuthProvider$signInOrSignUpWithChoice$2(this, choosePhoneSmsAuthCredential));
    }

    private final Source<AccountInfo> trySignInWithAuthCredential(PhoneSmsAuthCredential phoneSmsAuthCredential) {
        return Source.Companion.from(new PhoneSmsAuthProvider$trySignInWithAuthCredential$1(this, phoneSmsAuthCredential)).next(new PhoneSmsAuthProvider$trySignInWithAuthCredential$2(this, phoneSmsAuthCredential));
    }
}
