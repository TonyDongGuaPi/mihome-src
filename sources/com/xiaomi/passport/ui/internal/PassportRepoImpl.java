package com.xiaomi.passport.ui.internal;

import android.accounts.Account;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.PhoneTicketLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTokenRegisterParams;
import com.xiaomi.accountsdk.account.data.QueryPhoneInfoParams;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneNumException;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0002J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0\u00132\u0006\u0010\u0011\u001a\u00020\nH\u0002J$\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004H\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000f2\u0006\u0010\r\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010 \u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J \u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010&\u001a\u00020\u001d2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u000f2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020,2\u0006\u0010&\u001a\u000200H\u0016J\u0016\u00101\u001a\b\u0012\u0004\u0012\u00020,0\u000f2\u0006\u0010-\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020,2\u0006\u0010&\u001a\u000200H\u0016¨\u00064"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PassportRepoImpl;", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "()V", "atLeast2True", "", "b1", "b2", "b3", "checkPasswordPattern", "password", "", "checkPhone", "", "phone", "getCaptchaImage", "Lcom/xiaomi/passport/ui/internal/Source;", "Lcom/xiaomi/passport/ui/internal/Captcha;", "url", "getCaptchaImageNullSafe", "Landroid/util/Pair;", "Landroid/graphics/Bitmap;", "getLocalActivatorPhone", "", "Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;", "context", "Landroid/content/Context;", "useLocalCache", "getPhoneAuthMethod", "Lcom/xiaomi/passport/ui/internal/PhoneAuthMethod;", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "getXiaomiAccount", "Landroid/accounts/Account;", "invalidateCachePhoneNum", "slotId", "", "loadImage", "queryPhoneUserInfo", "Lcom/xiaomi/accountsdk/account/data/RegisterUserInfo;", "authCredential", "Lcom/xiaomi/passport/ui/internal/PhoneSmsAuthCredential;", "sendPhoneTicket", "captchaCode", "Lcom/xiaomi/passport/ui/internal/CaptchaCode;", "signInIdAndPsw", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "credential", "Lcom/xiaomi/passport/ui/internal/IdPswAuthCredential;", "signInWithPhone", "Lcom/xiaomi/passport/ui/internal/ChoosePhoneSmsAuthCredential;", "signInWithVStep2code", "Lcom/xiaomi/passport/ui/internal/IdPswVStep2AuthCredential;", "signUpWithPhone", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PassportRepoImpl implements PassportRepo {
    private final boolean atLeast2True(boolean z, boolean z2, boolean z3) {
        return (z && z2) || (z && z3) || (z2 && z3);
    }

    @NotNull
    public Source<PhoneAuthMethod> getPhoneAuthMethod(@NotNull PhoneWrapper phoneWrapper) {
        Intrinsics.f(phoneWrapper, "phone");
        return Source.Companion.from(new PassportRepoImpl$getPhoneAuthMethod$1(phoneWrapper));
    }

    @NotNull
    public Source<AccountInfo> signInIdAndPsw(@NotNull IdPswAuthCredential idPswAuthCredential) {
        Intrinsics.f(idPswAuthCredential, "credential");
        return Source.Companion.from(new PassportRepoImpl$signInIdAndPsw$1(this, idPswAuthCredential));
    }

    @NotNull
    public Source<AccountInfo> signInWithVStep2code(@NotNull IdPswVStep2AuthCredential idPswVStep2AuthCredential) {
        Intrinsics.f(idPswVStep2AuthCredential, "credential");
        return Source.Companion.from(new PassportRepoImpl$signInWithVStep2code$1(this, idPswVStep2AuthCredential));
    }

    /* access modifiers changed from: private */
    public final void checkPhone(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new InvalidPhoneNumException("empty phone");
        }
    }

    @NotNull
    public Source<String> sendPhoneTicket(@NotNull PhoneWrapper phoneWrapper, @Nullable CaptchaCode captchaCode) {
        Intrinsics.f(phoneWrapper, "authCredential");
        return Source.Companion.from(new PassportRepoImpl$sendPhoneTicket$1(this, phoneWrapper, captchaCode));
    }

    @NotNull
    public Source<Captcha> getCaptchaImage(@NotNull String str) {
        Intrinsics.f(str, "url");
        return Source.Companion.from(new PassportRepoImpl$getCaptchaImage$1(this, str));
    }

    /* access modifiers changed from: private */
    public final Pair<Bitmap, String> getCaptchaImageNullSafe(String str) {
        Pair<Bitmap, String> captchaImage = XMPassport.getCaptchaImage(str);
        if (captchaImage == null) {
            captchaImage = Pair.create((Object) null, "");
        }
        Intrinsics.b(captchaImage, "captcha");
        return captchaImage;
    }

    @NotNull
    public Source<Bitmap> loadImage(@Nullable String str) {
        return Source.Companion.from(new PassportRepoImpl$loadImage$1(str));
    }

    @NotNull
    public AccountInfo signInWithPhone(@NotNull ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        Intrinsics.f(choosePhoneSmsAuthCredential, "authCredential");
        PhoneTicketLoginParams.Builder serviceId = new PhoneTicketLoginParams.Builder().serviceId(choosePhoneSmsAuthCredential.getSid());
        if (choosePhoneSmsAuthCredential.getPhone().getPhone() != null) {
            serviceId.phoneTicketToken(choosePhoneSmsAuthCredential.getPhone().getPhone(), choosePhoneSmsAuthCredential.getUserInfo().ticketToken);
        } else {
            serviceId.activatorPhoneTicket(choosePhoneSmsAuthCredential.getPhone().getActivateInfo(), choosePhoneSmsAuthCredential.getTicket());
        }
        try {
            Object obj = new PhoneLoginController().ticketLogin(serviceId.build(), new PassportRepoImpl$signInWithPhone$future$1()).get();
            if (obj != null) {
                return (AccountInfo) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.accountsdk.account.data.AccountInfo");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                Intrinsics.a();
            }
            throw cause;
        }
    }

    @NotNull
    public AccountInfo signUpWithPhone(@NotNull ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        Intrinsics.f(choosePhoneSmsAuthCredential, "authCredential");
        PhoneTokenRegisterParams.Builder serviceId = new PhoneTokenRegisterParams.Builder().serviceId(choosePhoneSmsAuthCredential.getSid());
        if (choosePhoneSmsAuthCredential.getUserInfo().registerPwd) {
            if (choosePhoneSmsAuthCredential.getNewPsw() == null) {
                throw new NeedSetPswException(choosePhoneSmsAuthCredential);
            } else if (checkPasswordPattern(choosePhoneSmsAuthCredential.getNewPsw())) {
                serviceId.password(choosePhoneSmsAuthCredential.getNewPsw());
            } else {
                throw new SetPswIllegalException(choosePhoneSmsAuthCredential);
            }
        }
        if (choosePhoneSmsAuthCredential.getPhone().getPhone() != null) {
            serviceId.phoneTicketToken(choosePhoneSmsAuthCredential.getPhone().getPhone(), choosePhoneSmsAuthCredential.getUserInfo().ticketToken);
        } else {
            serviceId.phoneHashActivatorToken(choosePhoneSmsAuthCredential.getPhone().getActivateInfo());
        }
        try {
            Object obj = new PhoneLoginController().register(serviceId.build(), new PassportRepoImpl$signUpWithPhone$future$1()).get();
            if (obj != null) {
                return (AccountInfo) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.accountsdk.account.data.AccountInfo");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                Intrinsics.a();
            }
            throw cause;
        }
    }

    public final boolean checkPasswordPattern(@Nullable String str) {
        int length;
        if (str == null || (length = str.length()) < 8 || length > 16) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt > '~') {
                return false;
            }
            if (('a' <= charAt && 'z' >= charAt) || ('A' <= charAt && 'Z' >= charAt)) {
                z = true;
            } else if ('0' <= charAt && '9' >= charAt) {
                z2 = true;
            } else {
                z3 = true;
            }
        }
        return atLeast2True(z, z2, z3);
    }

    @NotNull
    public RegisterUserInfo queryPhoneUserInfo(@NotNull PhoneSmsAuthCredential phoneSmsAuthCredential) {
        Intrinsics.f(phoneSmsAuthCredential, "authCredential");
        QueryPhoneInfoParams.Builder builder = new QueryPhoneInfoParams.Builder();
        if (phoneSmsAuthCredential.getPhone().getPhone() != null) {
            checkPhone(phoneSmsAuthCredential.getPhone().getPhone());
            builder.phoneTicket(phoneSmsAuthCredential.getPhone().getPhone(), phoneSmsAuthCredential.getTicket());
        } else {
            builder.phoneHashActivatorToken(phoneSmsAuthCredential.getPhone().getActivateInfo());
        }
        try {
            Object obj = new PhoneLoginController().queryPhoneUserInfo(builder.build(), new PassportRepoImpl$queryPhoneUserInfo$future$1()).get();
            if (obj != null) {
                return (RegisterUserInfo) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.accountsdk.account.data.RegisterUserInfo");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                Intrinsics.a();
            }
            throw cause;
        }
    }

    @NotNull
    public Source<List<ActivatorPhoneInfo>> getLocalActivatorPhone(@NotNull Context context, boolean z) {
        Intrinsics.f(context, "context");
        return Source.Companion.from(new PassportRepoImpl$getLocalActivatorPhone$1(context, z));
    }

    public void invalidateCachePhoneNum(@NotNull Context context, int i) {
        Intrinsics.f(context, "context");
        Source.Companion.from(new PassportRepoImpl$invalidateCachePhoneNum$1(context, i)).getSuccess(PassportRepoImpl$invalidateCachePhoneNum$2.INSTANCE);
    }

    @Nullable
    public Account getXiaomiAccount(@NotNull Context context) {
        Intrinsics.f(context, "context");
        MiAccountManager miAccountManager = MiAccountManager.get(context);
        Intrinsics.b(miAccountManager, "MiAccountManager.get(context)");
        return miAccountManager.getXiaomiAccount();
    }
}
