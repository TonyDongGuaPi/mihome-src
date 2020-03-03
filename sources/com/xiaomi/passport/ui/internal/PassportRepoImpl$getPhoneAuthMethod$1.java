package com.xiaomi.passport.ui.internal;

import com.xiaomi.passport.data.LoginPreference;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/xiaomi/passport/ui/internal/PhoneAuthMethod;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PassportRepoImpl$getPhoneAuthMethod$1 extends Lambda implements Function0<PhoneAuthMethod> {
    final /* synthetic */ PhoneWrapper $phone;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportRepoImpl$getPhoneAuthMethod$1(PhoneWrapper phoneWrapper) {
        super(0);
        this.$phone = phoneWrapper;
    }

    @NotNull
    public final PhoneAuthMethod invoke() {
        if (this.$phone.getActivateInfo() != null) {
            return PhoneAuthMethod.SMS;
        }
        try {
            Object obj = PhoneLoginController.getPhoneLoginConfigOnLine(this.$phone.getPhone(), (String) null, (PhoneLoginController.PhoneLoginConfigCallback) null).get();
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.data.LoginPreference");
            } else if (((LoginPreference) obj).phoneLoginType == LoginPreference.PhoneLoginType.password) {
                return PhoneAuthMethod.PSW;
            } else {
                return PhoneAuthMethod.SMS;
            }
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                Intrinsics.a();
            }
            throw cause;
        }
    }
}
