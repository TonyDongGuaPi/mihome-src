package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.ui.internal.PhTicketSignInContract;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020%H\u0016J\u0018\u0010$\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0016\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0005J\u0018\u0010*\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(H\u0002J\u001a\u0010+\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0018\u0010.\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0005H\u0016J\u0010\u0010/\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002R\u0016\u0010\n\u001a\u00020\u00058\u0002XD¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00060"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhTicketSignInPresenter;", "Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$Presenter;", "context", "Landroid/content/Context;", "sid", "", "view", "Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$View;", "name", "(Landroid/content/Context;Ljava/lang/String;Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$View;Ljava/lang/String;)V", "TAG", "TAG$annotations", "()V", "getContext", "()Landroid/content/Context;", "getName", "()Ljava/lang/String;", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "getPassportRepo", "()Lcom/xiaomi/passport/ui/internal/PassportRepo;", "setPassportRepo", "(Lcom/xiaomi/passport/ui/internal/PassportRepo;)V", "provider", "Lcom/xiaomi/passport/ui/internal/AuthProvider;", "getProvider", "()Lcom/xiaomi/passport/ui/internal/AuthProvider;", "getSid", "getView", "()Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$View;", "chooseSignIn", "", "authCredential", "Lcom/xiaomi/passport/ui/internal/PhoneSmsAuthCredential;", "userInfo", "Lcom/xiaomi/accountsdk/account/data/RegisterUserInfo;", "chooseSignUp", "Lcom/xiaomi/passport/ui/internal/ChoosePhoneSmsAuthCredential;", "createAuthCredential", "phone", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "ticket", "invalidateCachePhoneNum", "sendTicket", "captchaCode", "Lcom/xiaomi/passport/ui/internal/CaptchaCode;", "signInPhoneAndTicket", "signInWithAuthCredential", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhTicketSignInPresenter implements PhTicketSignInContract.Presenter {
    /* access modifiers changed from: private */
    public final String TAG;
    @NotNull
    private final Context context;
    @NotNull
    private final String name;
    @NotNull
    private PassportRepo passportRepo;
    @Nullable
    private final AuthProvider provider;
    @NotNull
    private final String sid;
    @NotNull
    private final PhTicketSignInContract.View view;

    private static /* synthetic */ void TAG$annotations() {
    }

    public PhTicketSignInPresenter(@NotNull Context context2, @NotNull String str, @NotNull PhTicketSignInContract.View view2, @NotNull String str2) {
        Intrinsics.f(context2, "context");
        Intrinsics.f(str, "sid");
        Intrinsics.f(view2, "view");
        Intrinsics.f(str2, "name");
        this.context = context2;
        this.sid = str;
        this.view = view2;
        this.name = str2;
        this.TAG = "PhTicketSignIn";
        this.provider = PassportUI.INSTANCE.getProvider(this.name);
        this.passportRepo = new PassportRepoImpl();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PhTicketSignInPresenter(Context context2, String str, PhTicketSignInContract.View view2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, str, view2, (i & 8) != 0 ? PassportUI.PHONE_SMS_AUTH_PROVIDER : str2);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getSid() {
        return this.sid;
    }

    @NotNull
    public final PhTicketSignInContract.View getView() {
        return this.view;
    }

    @Nullable
    public final AuthProvider getProvider() {
        return this.provider;
    }

    @NotNull
    public final PassportRepo getPassportRepo() {
        return this.passportRepo;
    }

    public final void setPassportRepo(@NotNull PassportRepo passportRepo2) {
        Intrinsics.f(passportRepo2, "<set-?>");
        this.passportRepo = passportRepo2;
    }

    public void sendTicket(@NotNull PhoneWrapper phoneWrapper, @Nullable CaptchaCode captchaCode) {
        Intrinsics.f(phoneWrapper, "phone");
        this.passportRepo.sendPhoneTicket(phoneWrapper, captchaCode).get(new PhTicketSignInPresenter$sendTicket$1(this), new PhTicketSignInPresenter$sendTicket$2(this, phoneWrapper));
    }

    public void signInPhoneAndTicket(@NotNull PhoneWrapper phoneWrapper, @NotNull String str) {
        Intrinsics.f(phoneWrapper, "phone");
        Intrinsics.f(str, SmartConfigDataProvider.l);
        if (TextUtils.isEmpty(str)) {
            this.view.showInvalidTicket();
        } else {
            signInWithAuthCredential(createAuthCredential(phoneWrapper, str));
        }
    }

    public void chooseSignUp(@NotNull PhoneSmsAuthCredential phoneSmsAuthCredential, @NotNull RegisterUserInfo registerUserInfo) {
        Intrinsics.f(phoneSmsAuthCredential, "authCredential");
        Intrinsics.f(registerUserInfo, "userInfo");
        signInWithAuthCredential(new ChoosePhoneSmsAuthCredential(phoneSmsAuthCredential, registerUserInfo, false));
    }

    public void chooseSignUp(@NotNull ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        Intrinsics.f(choosePhoneSmsAuthCredential, "authCredential");
        signInWithAuthCredential(choosePhoneSmsAuthCredential);
    }

    public void chooseSignIn(@NotNull PhoneSmsAuthCredential phoneSmsAuthCredential, @NotNull RegisterUserInfo registerUserInfo) {
        Intrinsics.f(phoneSmsAuthCredential, "authCredential");
        Intrinsics.f(registerUserInfo, "userInfo");
        signInWithAuthCredential(new ChoosePhoneSmsAuthCredential(phoneSmsAuthCredential, registerUserInfo, true));
    }

    private final void signInWithAuthCredential(PhoneSmsAuthCredential phoneSmsAuthCredential) {
        this.view.showProgress();
        AuthProvider authProvider = this.provider;
        if (authProvider == null) {
            Intrinsics.a();
        }
        authProvider.signInAndStoreAccount(this.context, phoneSmsAuthCredential).get(new PhTicketSignInPresenter$signInWithAuthCredential$1(this, phoneSmsAuthCredential), new PhTicketSignInPresenter$signInWithAuthCredential$2(this, phoneSmsAuthCredential));
    }

    @NotNull
    public final PhoneSmsAuthCredential createAuthCredential(@NotNull PhoneWrapper phoneWrapper, @NotNull String str) {
        Intrinsics.f(phoneWrapper, "phone");
        Intrinsics.f(str, SmartConfigDataProvider.l);
        return new PhoneSmsAuthCredential(phoneWrapper, str, this.sid);
    }

    /* access modifiers changed from: private */
    public final void invalidateCachePhoneNum(Context context2, PhoneWrapper phoneWrapper) {
        PassportRepo passportRepo2 = this.passportRepo;
        ActivatorPhoneInfo activateInfo = phoneWrapper.getActivateInfo();
        if (activateInfo == null) {
            Intrinsics.a();
        }
        passportRepo2.invalidateCachePhoneNum(context2, activateInfo.slotId);
    }
}
