package com.xiaomi.passport.ui.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PhTicketSignInContract;
import com.xiaomi.passport.ui.settings.utils.AccountSmsVerifyCodeReceiver;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 82\u00020\u00012\u00020\u00022\u00020\u0003:\u00018B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0017J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J(\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0001\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010'\u001a\u00020\u0016H\u0016J\u001c\u0010(\u001a\u00020\u00162\b\u0010)\u001a\u0004\u0018\u00010\u001d2\b\u0010*\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010+\u001a\u00020\u0016H\u0016J\u001c\u0010,\u001a\u00020\u00162\b\b\u0001\u0010-\u001a\u00020\"2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010.\u001a\u00020\u0016H\u0016J\u0018\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\fH\u0016J\u0018\u00103\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u0002042\u0006\u00105\u001a\u00020\u0014H\u0016J\b\u00106\u001a\u00020\u0016H\u0016J\u0010\u00106\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u0014H\u0016J\u0010\u00107\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u000204H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhTicketSignInFragment;", "Lcom/xiaomi/passport/ui/internal/SignInFragment;", "Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$View;", "Lcom/xiaomi/passport/ui/settings/utils/AccountSmsVerifyCodeReceiver$SmsVerifyCodeMessageListener;", "()V", "mIsCountingDown", "", "getMIsCountingDown", "()Z", "setMIsCountingDown", "(Z)V", "mPhoneWrapper", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "mSmsReceiver", "Lcom/xiaomi/passport/ui/settings/utils/AccountSmsVerifyCodeReceiver;", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "presenter", "Lcom/xiaomi/passport/ui/internal/PhTicketSignInContract$Presenter;", "sendTicketSuccessCount", "", "chooseToSignInOrSignUp", "", "authCredential", "Lcom/xiaomi/passport/ui/internal/PhoneSmsAuthCredential;", "userInfo", "Lcom/xiaomi/accountsdk/account/data/RegisterUserInfo;", "enableSendTicketBtn", "getDisplayNickname", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onReceived", "message", "verifyCode", "onResume", "onViewCreated", "view", "sendTicketSuccess", "showCaptcha", "captcha", "Lcom/xiaomi/passport/ui/internal/Captcha;", "phone", "showInvalidPsw", "Lcom/xiaomi/passport/ui/internal/ChoosePhoneSmsAuthCredential;", "msg", "showInvalidTicket", "showSetPsw", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhTicketSignInFragment extends SignInFragment implements PhTicketSignInContract.View, AccountSmsVerifyCodeReceiver.SmsVerifyCodeMessageListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private boolean mIsCountingDown;
    /* access modifiers changed from: private */
    public PhoneWrapper mPhoneWrapper;
    private AccountSmsVerifyCodeReceiver mSmsReceiver;
    private PassportRepo passportRepo = new PassportRepoImpl();
    /* access modifiers changed from: private */
    public PhTicketSignInContract.Presenter presenter;
    private int sendTicketSuccessCount;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public static final /* synthetic */ PhTicketSignInContract.Presenter access$getPresenter$p(PhTicketSignInFragment phTicketSignInFragment) {
        PhTicketSignInContract.Presenter presenter2 = phTicketSignInFragment.presenter;
        if (presenter2 == null) {
            Intrinsics.c("presenter");
        }
        return presenter2;
    }

    public final boolean getMIsCountingDown() {
        return this.mIsCountingDown;
    }

    public final void setMIsCountingDown(boolean z) {
        this.mIsCountingDown = z;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhTicketSignInFragment$Companion;", "", "()V", "newInstance", "Lcom/xiaomi/passport/ui/internal/PhTicketSignInFragment;", "sid", "", "phone", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PhTicketSignInFragment newInstance(@NotNull String str, @NotNull PhoneWrapper phoneWrapper) {
            Intrinsics.f(str, "sid");
            Intrinsics.f(phoneWrapper, "phone");
            PhTicketSignInFragment phTicketSignInFragment = new PhTicketSignInFragment();
            Bundle bundle = new Bundle();
            bundle.putString("sid", str);
            bundle.putParcelable("phone", phoneWrapper);
            phTicketSignInFragment.setArguments(bundle);
            return phTicketSignInFragment;
        }
    }

    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        this.mSmsReceiver = new AccountSmsVerifyCodeReceiver(this);
        getActivity().registerReceiver(this.mSmsReceiver, intentFilter);
    }

    public void onReceived(@Nullable String str, @Nullable String str2) {
        if (str2 != null) {
            ((TextInputEditText) _$_findCachedViewById(R.id.ticket)).setText(str2);
        }
    }

    public void onPause() {
        if (this.mSmsReceiver != null) {
            getActivity().unregisterReceiver(this.mSmsReceiver);
            this.mSmsReceiver = null;
        }
        super.onPause();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AccountSmsVerifyCodeReceiver.tryRequestSmsPermission(getActivity());
    }

    @Nullable
    public View onCreateView(@NotNull @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.f(layoutInflater, "inflater");
        Context context = getContext();
        if (context == null) {
            Intrinsics.a();
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        String string = arguments.getString("sid");
        Intrinsics.b(string, "arguments!!.getString(\"sid\")");
        this.presenter = new PhTicketSignInPresenter(context, string, this, (String) null, 8, (DefaultConstructorMarker) null);
        Bundle arguments2 = getArguments();
        if (arguments2 == null) {
            Intrinsics.a();
        }
        this.mPhoneWrapper = (PhoneWrapper) arguments2.getParcelable("phone");
        return layoutInflater.inflate(R.layout.fg_ph_ticket_signin, viewGroup, false);
    }

    public void onViewCreated(@NotNull @NonNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        super.onViewCreated(view, bundle);
        TextView textView = (TextView) _$_findCachedViewById(R.id.phone_text);
        Intrinsics.b(textView, "phone_text");
        int i = R.string.passport_sms_phone_intro;
        Object[] objArr = new Object[1];
        PhoneWrapper phoneWrapper = this.mPhoneWrapper;
        objArr[0] = phoneWrapper != null ? phoneWrapper.getPhoneOrMarkPhone() : null;
        textView.setText(getString(i, objArr));
        ((TextView) _$_findCachedViewById(R.id.action_get_ph_ticket)).setOnClickListener(new PhTicketSignInFragment$onViewCreated$1(this));
        ((Button) _$_findCachedViewById(R.id.ph_sign_in_btn)).setOnClickListener(new PhTicketSignInFragment$onViewCreated$2(this));
        sendTicketSuccess();
    }

    public void sendTicketSuccess() {
        if (!this.mIsCountingDown) {
            TextInputEditText textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.ticket);
            if (textInputEditText != null) {
                textInputEditText.requestFocus();
            }
            TextInputEditText textInputEditText2 = (TextInputEditText) _$_findCachedViewById(R.id.ticket);
            if (textInputEditText2 != null) {
                textInputEditText2.setText("");
            }
            this.sendTicketSuccessCount++;
            int i = this.sendTicketSuccessCount * 60;
            new PhTicketSignInFragment$sendTicketSuccess$1(this, i, (long) (i * 1000), 1000).start();
            this.mIsCountingDown = true;
        }
    }

    public void enableSendTicketBtn() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.action_get_ph_ticket);
        if (textView != null) {
            textView.setClickable(true);
        }
    }

    public void showCaptcha(@NotNull Captcha captcha, @NotNull PhoneWrapper phoneWrapper) {
        Intrinsics.f(captcha, "captcha");
        Intrinsics.f(phoneWrapper, "phone");
        CommonErrorHandler mCommonErrorHandler = getMCommonErrorHandler();
        Context context = getContext();
        if (context == null) {
            Intrinsics.a();
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.b(layoutInflater, "layoutInflater");
        mCommonErrorHandler.showCaptcha(context, layoutInflater, captcha, new PhTicketSignInFragment$showCaptcha$1(this, phoneWrapper));
    }

    public void showInvalidTicket() {
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.ticket_wrapper);
        if (textInputLayout != null) {
            textInputLayout.setError(getString(R.string.ticket_invalid));
        }
    }

    public void showInvalidTicket(int i) {
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.ticket_wrapper);
        if (textInputLayout != null) {
            textInputLayout.setError(getString(i));
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void chooseToSignInOrSignUp(@NotNull PhoneSmsAuthCredential phoneSmsAuthCredential, @NotNull RegisterUserInfo registerUserInfo) {
        Intrinsics.f(phoneSmsAuthCredential, "authCredential");
        Intrinsics.f(registerUserInfo, "userInfo");
        View inflate = getLayoutInflater().inflate(R.layout.dg_choose_to_signin_signup, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.text_view_user_info);
        if (findViewById != null) {
            ((TextView) findViewById).setText("" + getString(R.string.nick_name) + Operators.CONDITION_IF_MIDDLE + getDisplayNickname(registerUserInfo) + 10 + "" + getString(R.string.phone_number) + Operators.CONDITION_IF_MIDDLE + registerUserInfo.phone);
            Context context = getContext();
            if (context == null) {
                Intrinsics.a();
            }
            new AlertDialog.Builder(context).setTitle(R.string.isornot_your_mi_account).setView(inflate).setNegativeButton(R.string.choose_to_signup, (DialogInterface.OnClickListener) new PhTicketSignInFragment$chooseToSignInOrSignUp$dialog$1(this, phoneSmsAuthCredential, registerUserInfo)).setPositiveButton(R.string.choose_to_signin, (DialogInterface.OnClickListener) new PhTicketSignInFragment$chooseToSignInOrSignUp$dialog$2(this, phoneSmsAuthCredential, registerUserInfo)).create().show();
            if (!TextUtils.isEmpty(registerUserInfo.avatarAddress)) {
                this.passportRepo.loadImage(registerUserInfo.avatarAddress).getSuccess(new PhTicketSignInFragment$chooseToSignInOrSignUp$1(inflate));
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
    }

    private final String getDisplayNickname(RegisterUserInfo registerUserInfo) {
        if (TextUtils.isEmpty(registerUserInfo.userName)) {
            return registerUserInfo.maskedUserId;
        }
        return registerUserInfo.userName;
    }

    public void showSetPsw(@NotNull ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential) {
        Intrinsics.f(choosePhoneSmsAuthCredential, "authCredential");
        TextView textView = (TextView) _$_findCachedViewById(R.id.sign_in_user_id_text);
        Intrinsics.b(textView, "sign_in_user_id_text");
        textView.setVisibility(0);
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.password_wapper);
        Intrinsics.b(textInputLayout, "password_wapper");
        textInputLayout.setVisibility(0);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.phone_text);
        Intrinsics.b(textView2, "phone_text");
        textView2.setVisibility(8);
        TextInputLayout textInputLayout2 = (TextInputLayout) _$_findCachedViewById(R.id.ticket_wrapper);
        Intrinsics.b(textInputLayout2, "ticket_wrapper");
        textInputLayout2.setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.ph_sign_in_btn)).setOnClickListener(new PhTicketSignInFragment$showSetPsw$1(this, choosePhoneSmsAuthCredential));
    }

    public void showInvalidPsw(@NotNull ChoosePhoneSmsAuthCredential choosePhoneSmsAuthCredential, int i) {
        Intrinsics.f(choosePhoneSmsAuthCredential, "authCredential");
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.password_wapper);
        Intrinsics.b(textInputLayout, "password_wapper");
        textInputLayout.setError(getString(i));
        ((Button) _$_findCachedViewById(R.id.ph_sign_in_btn)).setOnClickListener(new PhTicketSignInFragment$showInvalidPsw$1(this, choosePhoneSmsAuthCredential));
    }
}
