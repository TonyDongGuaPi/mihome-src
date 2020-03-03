package com.xiaomi.passport.ui.internal;

import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"com/xiaomi/passport/ui/internal/PassportRepoImpl$signInWithPhone$future$1", "Lcom/xiaomi/passport/uicontroller/PhoneLoginController$TicketLoginCallback;", "()V", "onLoginFailed", "", "p0", "Lcom/xiaomi/passport/uicontroller/PhoneLoginController$ErrorCode;", "p1", "", "p2", "", "onLoginSuccess", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "onNeedNotification", "onPhoneNumInvalid", "onTicketOrTokenInvalid", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PassportRepoImpl$signInWithPhone$future$1 implements PhoneLoginController.TicketLoginCallback {
    public void onLoginFailed(@Nullable PhoneLoginController.ErrorCode errorCode, @Nullable String str, boolean z) {
    }

    public void onLoginSuccess(@Nullable AccountInfo accountInfo) {
    }

    public void onNeedNotification(@Nullable String str, @Nullable String str2) {
    }

    public void onPhoneNumInvalid() {
    }

    public void onTicketOrTokenInvalid() {
    }

    PassportRepoImpl$signInWithPhone$future$1() {
    }
}
