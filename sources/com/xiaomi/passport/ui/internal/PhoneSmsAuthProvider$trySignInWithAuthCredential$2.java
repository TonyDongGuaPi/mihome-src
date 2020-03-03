package com.xiaomi.passport.ui.internal;

import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "it", "Lcom/xiaomi/accountsdk/account/data/RegisterUserInfo;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhoneSmsAuthProvider$trySignInWithAuthCredential$2 extends Lambda implements Function1<RegisterUserInfo, AccountInfo> {
    final /* synthetic */ PhoneSmsAuthCredential $authCredential;
    final /* synthetic */ PhoneSmsAuthProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhoneSmsAuthProvider$trySignInWithAuthCredential$2(PhoneSmsAuthProvider phoneSmsAuthProvider, PhoneSmsAuthCredential phoneSmsAuthCredential) {
        super(1);
        this.this$0 = phoneSmsAuthProvider;
        this.$authCredential = phoneSmsAuthCredential;
    }

    @NotNull
    public final AccountInfo invoke(@NotNull RegisterUserInfo registerUserInfo) {
        Intrinsics.f(registerUserInfo, "it");
        RegisterUserInfo.RegisterStatus registerStatus = registerUserInfo.status;
        if (registerStatus != null) {
            switch (registerStatus) {
                case STATUS_NOT_REGISTERED:
                    return this.this$0.getPassportRepo().signUpWithPhone(new ChoosePhoneSmsAuthCredential(this.$authCredential, registerUserInfo, false));
                case STATUS_REGISTERED_NOT_RECYCLED:
                    return this.this$0.getPassportRepo().signInWithPhone(new ChoosePhoneSmsAuthCredential(this.$authCredential, registerUserInfo, true));
            }
        }
        throw new PhoneRecycleException(this.$authCredential, registerUserInfo);
    }
}
