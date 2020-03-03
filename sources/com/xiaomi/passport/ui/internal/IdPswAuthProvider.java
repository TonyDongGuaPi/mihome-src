package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.ui.internal.PswSignInContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014¨\u0006\u0013"}, d2 = {"Lcom/xiaomi/passport/ui/internal/IdPswAuthProvider;", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "()V", "getFragment", "Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;", "sid", "", "getFragmentWithUserId", "userId", "setPresenter", "", "fragment", "signInWithAuthCredential", "Lcom/xiaomi/passport/ui/internal/Source;", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "context", "Landroid/content/Context;", "credential", "Lcom/xiaomi/passport/ui/internal/AuthCredential;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class IdPswAuthProvider extends BaseAuthProvider {
    public IdPswAuthProvider() {
        super(PassportUI.ID_PSW_AUTH_PROVIDER);
    }

    public void setPresenter(@NotNull String str, @NotNull BaseSignInFragment baseSignInFragment) {
        Intrinsics.f(str, "sid");
        Intrinsics.f(baseSignInFragment, "fragment");
        PswSignInFragment pswSignInFragment = (PswSignInFragment) baseSignInFragment;
        Context context = pswSignInFragment.getContext();
        if (context == null) {
            Intrinsics.a();
        }
        pswSignInFragment.setPresenter(new PswSignInPresenter(context, str, (PswSignInContract.View) baseSignInFragment, (String) null, 8, (DefaultConstructorMarker) null));
    }

    @NotNull
    public BaseSignInFragment getFragment(@NotNull String str) {
        Intrinsics.f(str, "sid");
        return PswSignInFragment.Companion.newInstance(str);
    }

    @NotNull
    public final BaseSignInFragment getFragmentWithUserId(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "sid");
        Intrinsics.f(str2, "userId");
        return PswSignInFragment.Companion.newInstance(str, str2);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Source<AccountInfo> signInWithAuthCredential(@NotNull Context context, @NotNull AuthCredential authCredential) {
        Intrinsics.f(context, "context");
        Intrinsics.f(authCredential, "credential");
        if (authCredential instanceof IdPswAuthCredential) {
            return getPassportRepo().signInIdAndPsw((IdPswAuthCredential) authCredential);
        }
        if (authCredential instanceof IdPswVStep2AuthCredential) {
            return getPassportRepo().signInWithVStep2code((IdPswVStep2AuthCredential) authCredential);
        }
        throw new IllegalStateException("not support originAuthCredential:" + authCredential);
    }
}
