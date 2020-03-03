package com.xiaomi.passport.ui.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PhAuthContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 22\u00020\u00012\u00020\u0002:\u00012B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\"\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0017J(\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0001\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u0015H\u0016J\u001c\u0010+\u001a\u00020\u00152\b\b\u0001\u0010,\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0017J\u0018\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u001eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u00063"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhAuthFragment;", "Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;", "Lcom/xiaomi/passport/ui/internal/PhAuthContract$View;", "()V", "idPswAuthProvider", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "mPhoneViewWrapper", "Lcom/xiaomi/passport/ui/internal/PhoneViewWrapper;", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "getPassportRepo", "()Lcom/xiaomi/passport/ui/internal/PassportRepo;", "setPassportRepo", "(Lcom/xiaomi/passport/ui/internal/PassportRepo;)V", "presenter", "Lcom/xiaomi/passport/ui/internal/PhAuthContract$Presenter;", "getPresenter", "()Lcom/xiaomi/passport/ui/internal/PhAuthContract$Presenter;", "setPresenter", "(Lcom/xiaomi/passport/ui/internal/PhAuthContract$Presenter;)V", "clearPhonePopList", "", "gotoPswSignIn", "userId", "", "gotoTicketSignIn", "phone", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showCaptcha", "captcha", "Lcom/xiaomi/passport/ui/internal/Captcha;", "showPhoneNumError", "msgRes", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhAuthFragment extends BaseSignInFragment implements PhAuthContract.View {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public BaseAuthProvider idPswAuthProvider = PassportUI.INSTANCE.getBaseAuthProvider(PassportUI.ID_PSW_AUTH_PROVIDER);
    /* access modifiers changed from: private */
    public PhoneViewWrapper mPhoneViewWrapper;
    @NotNull
    private PassportRepo passportRepo = new PassportRepoImpl();
    @NotNull
    public PhAuthContract.Presenter presenter;

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

    public PhAuthFragment() {
        super(PassportUI.PHONE_SMS_AUTH_PROVIDER);
    }

    @NotNull
    public final PhAuthContract.Presenter getPresenter() {
        PhAuthContract.Presenter presenter2 = this.presenter;
        if (presenter2 == null) {
            Intrinsics.c("presenter");
        }
        return presenter2;
    }

    public final void setPresenter(@NotNull PhAuthContract.Presenter presenter2) {
        Intrinsics.f(presenter2, "<set-?>");
        this.presenter = presenter2;
    }

    @NotNull
    public final PassportRepo getPassportRepo() {
        return this.passportRepo;
    }

    public final void setPassportRepo(@NotNull PassportRepo passportRepo2) {
        Intrinsics.f(passportRepo2, "<set-?>");
        this.passportRepo = passportRepo2;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhAuthFragment$Companion;", "", "()V", "newInstance", "Lcom/xiaomi/passport/ui/internal/PhAuthFragment;", "sid", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PhAuthFragment newInstance(@NotNull String str) {
            Intrinsics.f(str, "sid");
            PhAuthFragment phAuthFragment = new PhAuthFragment();
            Bundle bundle = new Bundle();
            bundle.putString("sid", str);
            phAuthFragment.setArguments(bundle);
            return phAuthFragment;
        }
    }

    @Nullable
    public View onCreateView(@NotNull @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.f(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fg_ph_auth_method, viewGroup, false);
    }

    @SuppressLint({"SetTextI18n"})
    public void onViewCreated(@NotNull @NonNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        super.onViewCreated(view, bundle);
        ((Button) _$_findCachedViewById(R.id.ph_sign_in_btn)).setOnClickListener(new PhAuthFragment$onViewCreated$1(this));
        ((TextView) _$_findCachedViewById(R.id.action_goto_psw_signin)).setOnClickListener(new PhAuthFragment$onViewCreated$2(this));
        ((CheckBox) _$_findCachedViewById(R.id.cb_agree_something)).setOnCheckedChangeListener(new PhAuthFragment$onViewCreated$3(this));
        ((TextView) _$_findCachedViewById(R.id.passport_country_code_text)).setOnClickListener(new PhAuthFragment$onViewCreated$4(this));
        if (getDefaultCountryCodeWithPrefix() != null) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.passport_country_code_text);
            Intrinsics.b(textView, "passport_country_code_text");
            textView.setText(getDefaultCountryCodeWithPrefix());
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.passport_country_code_text);
            Intrinsics.b(textView2, "passport_country_code_text");
            if (TextUtils.isEmpty(textView2.getText().toString())) {
                TextView textView3 = (TextView) _$_findCachedViewById(R.id.passport_country_code_text);
                Intrinsics.b(textView3, "passport_country_code_text");
                textView3.setText(PassportUI.CHINA_COUNTRY_CODE);
            }
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        String string = arguments.getString("sid");
        Intrinsics.b(string, "arguments!!.getString(\"sid\")");
        Context context = getContext();
        if (context == null) {
            Intrinsics.a();
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) _$_findCachedViewById(R.id.phone);
        Intrinsics.b(autoCompleteTextView, "phone");
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.passport_country_code_text);
        Intrinsics.b(textView4, "passport_country_code_text");
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.delete_phone);
        Intrinsics.b(imageView, "delete_phone");
        this.mPhoneViewWrapper = new PhoneViewWrapper(string, context, autoCompleteTextView, textView4, imageView, (TextView) _$_findCachedViewById(R.id.passport_operator_license));
    }

    @SuppressLint({"SetTextI18n"})
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2001 && i2 == -1) {
            if (intent == null) {
                Intrinsics.a();
            }
            String stringExtra = intent.getStringExtra("code");
            TextView textView = (TextView) _$_findCachedViewById(R.id.passport_country_code_text);
            Intrinsics.b(textView, "passport_country_code_text");
            textView.setText('+' + stringExtra);
        }
    }

    public void onDestroyView() {
        PhoneViewWrapper phoneViewWrapper = this.mPhoneViewWrapper;
        if (phoneViewWrapper != null) {
            phoneViewWrapper.destroy();
        }
        this.mPhoneViewWrapper = null;
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void clearPhonePopList() {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) _$_findCachedViewById(R.id.phone);
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setText("");
        }
        AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) _$_findCachedViewById(R.id.phone);
        if (autoCompleteTextView2 != null) {
            autoCompleteTextView2.setEnabled(true);
        }
    }

    public void gotoTicketSignIn(@NotNull PhoneWrapper phoneWrapper) {
        Intrinsics.f(phoneWrapper, "phone");
        gotoFragment(PhTicketSignInFragment.Companion.newInstance(phoneWrapper.getSid(), phoneWrapper), true);
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
        mCommonErrorHandler.showCaptcha(context, layoutInflater, captcha, new PhAuthFragment$showCaptcha$1(this, phoneWrapper));
    }

    public void showPhoneNumError(int i) {
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.phone_wrapper);
        if (textInputLayout != null) {
            textInputLayout.setError(getString(i));
        }
    }

    public void gotoPswSignIn(@NotNull String str) {
        Intrinsics.f(str, "userId");
        BaseAuthProvider baseAuthProvider = this.idPswAuthProvider;
        if (baseAuthProvider != null) {
            IdPswAuthProvider idPswAuthProvider2 = (IdPswAuthProvider) baseAuthProvider;
            Bundle arguments = getArguments();
            if (arguments == null) {
                Intrinsics.a();
            }
            String string = arguments.getString("sid");
            Intrinsics.b(string, "arguments!!.getString(\"sid\")");
            gotoFragment(idPswAuthProvider2.getFragmentWithUserId(string, str), true);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.IdPswAuthProvider");
    }
}
