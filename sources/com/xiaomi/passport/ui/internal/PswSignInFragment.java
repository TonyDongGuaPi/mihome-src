package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.PswSignInContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001c\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010 \u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u0005H\u0016J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u0005H\u0016J \u0010)\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u001fH\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006/"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PswSignInFragment;", "Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;", "Lcom/xiaomi/passport/ui/internal/PswSignInContract$View;", "()V", "mSignInUserId", "", "getMSignInUserId", "()Ljava/lang/String;", "setMSignInUserId", "(Ljava/lang/String;)V", "phoneAuthProvider", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "getPhoneAuthProvider", "()Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "setPhoneAuthProvider", "(Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;)V", "presenter", "Lcom/xiaomi/passport/ui/internal/PswSignInContract$Presenter;", "getPresenter", "()Lcom/xiaomi/passport/ui/internal/PswSignInContract$Presenter;", "setPresenter", "(Lcom/xiaomi/passport/ui/internal/PswSignInContract$Presenter;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "showCaptcha", "captcha", "Lcom/xiaomi/passport/ui/internal/Captcha;", "authCredential", "Lcom/xiaomi/passport/ui/internal/IdPswBaseAuthCredential;", "showPswError", "msg", "showUserNameError", "showVStep2Code", "step1Token", "metaLoginData", "Lcom/xiaomi/accountsdk/account/data/MetaLoginData;", "specifyUserId", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PswSignInFragment extends BaseSignInFragment implements PswSignInContract.View {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    @Nullable
    private String mSignInUserId;
    @NotNull
    private BaseAuthProvider phoneAuthProvider = PassportUI.INSTANCE.getBaseAuthProvider(PassportUI.PHONE_SMS_AUTH_PROVIDER);
    @NotNull
    public PswSignInContract.Presenter presenter;

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

    public PswSignInFragment() {
        super(PassportUI.ID_PSW_AUTH_PROVIDER);
    }

    @NotNull
    public final PswSignInContract.Presenter getPresenter() {
        PswSignInContract.Presenter presenter2 = this.presenter;
        if (presenter2 == null) {
            Intrinsics.c("presenter");
        }
        return presenter2;
    }

    public final void setPresenter(@NotNull PswSignInContract.Presenter presenter2) {
        Intrinsics.f(presenter2, "<set-?>");
        this.presenter = presenter2;
    }

    @NotNull
    public final BaseAuthProvider getPhoneAuthProvider() {
        return this.phoneAuthProvider;
    }

    public final void setPhoneAuthProvider(@NotNull BaseAuthProvider baseAuthProvider) {
        Intrinsics.f(baseAuthProvider, "<set-?>");
        this.phoneAuthProvider = baseAuthProvider;
    }

    @Nullable
    public final String getMSignInUserId() {
        return this.mSignInUserId;
    }

    public final void setMSignInUserId(@Nullable String str) {
        this.mSignInUserId = str;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PswSignInFragment$Companion;", "", "()V", "newInstance", "Lcom/xiaomi/passport/ui/internal/PswSignInFragment;", "sid", "", "userId", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PswSignInFragment newInstance(@NotNull String str) {
            Intrinsics.f(str, "sid");
            return newInstance(str, (String) null);
        }

        @NotNull
        public final PswSignInFragment newInstance(@NotNull String str, @Nullable String str2) {
            Intrinsics.f(str, "sid");
            PswSignInFragment pswSignInFragment = new PswSignInFragment();
            Bundle bundle = new Bundle();
            bundle.putString("sid", str);
            bundle.putString("userId", str2);
            pswSignInFragment.setArguments(bundle);
            return pswSignInFragment;
        }
    }

    @Nullable
    public View onCreateView(@NotNull @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.f(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fg_psw_signin, viewGroup, false);
    }

    public void onViewCreated(@NotNull @NonNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        super.onViewCreated(view, bundle);
        Context context = getContext();
        PswSignInContract.Presenter presenter2 = this.presenter;
        if (presenter2 == null) {
            Intrinsics.c("presenter");
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367043, presenter2.getSignedInUserIds());
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) _$_findCachedViewById(R.id.userId);
        Intrinsics.b(autoCompleteTextView, "userId");
        autoCompleteTextView.setThreshold(0);
        ((AutoCompleteTextView) _$_findCachedViewById(R.id.userId)).setAdapter(arrayAdapter);
        ((Button) _$_findCachedViewById(R.id.sign_in_btn)).setOnClickListener(new PswSignInFragment$onViewCreated$1(this));
        ((TextView) _$_findCachedViewById(R.id.action_find_psw)).setOnClickListener(new PswSignInFragment$onViewCreated$2(this));
        ((TextView) _$_findCachedViewById(R.id.action_goto_siginup_from_psw)).setOnClickListener(new PswSignInFragment$onViewCreated$3(this));
        ((TextView) _$_findCachedViewById(R.id.action_ph_ticket_signin)).setOnClickListener(new PswSignInFragment$onViewCreated$4(this));
        ((CheckBox) _$_findCachedViewById(R.id.cb_agree_something)).setOnCheckedChangeListener(new PswSignInFragment$onViewCreated$5(this));
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        this.mSignInUserId = arguments.getString("userId");
        if (this.mSignInUserId != null) {
            specifyUserId();
        }
    }

    private final void specifyUserId() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.sign_in_user_id_text);
        Intrinsics.b(textView, "sign_in_user_id_text");
        textView.setVisibility(0);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.sign_in_user_id_text);
        Intrinsics.b(textView2, "sign_in_user_id_text");
        textView2.setText(getString(R.string.passport_user_id_intro, this.mSignInUserId));
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.userId_wapper);
        Intrinsics.b(textInputLayout, "userId_wapper");
        textInputLayout.setVisibility(8);
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.action_ph_ticket_signin);
        Intrinsics.b(textView3, "action_ph_ticket_signin");
        textView3.setVisibility(8);
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.action_goto_siginup_from_psw);
        Intrinsics.b(textView4, "action_goto_siginup_from_psw");
        textView4.setVisibility(8);
        hideSns();
    }

    public void showUserNameError(@NotNull String str) {
        Intrinsics.f(str, "msg");
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.password_wapper);
        if (textInputLayout != null) {
            textInputLayout.setErrorEnabled(false);
        }
        TextInputLayout textInputLayout2 = (TextInputLayout) _$_findCachedViewById(R.id.userId_wapper);
        if (textInputLayout2 != null) {
            textInputLayout2.setError(str);
        }
    }

    public void showPswError(@NotNull String str) {
        Intrinsics.f(str, "msg");
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.userId_wapper);
        if (textInputLayout != null) {
            textInputLayout.setErrorEnabled(false);
        }
        TextInputLayout textInputLayout2 = (TextInputLayout) _$_findCachedViewById(R.id.password_wapper);
        if (textInputLayout2 != null) {
            textInputLayout2.setError(str);
        }
    }

    public void showCaptcha(@NotNull Captcha captcha, @NotNull IdPswBaseAuthCredential idPswBaseAuthCredential) {
        Intrinsics.f(captcha, "captcha");
        Intrinsics.f(idPswBaseAuthCredential, "authCredential");
        CommonErrorHandler mCommonErrorHandler = getMCommonErrorHandler();
        Context context = getContext();
        if (context == null) {
            Intrinsics.a();
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.b(layoutInflater, "layoutInflater");
        mCommonErrorHandler.showCaptcha(context, layoutInflater, captcha, new PswSignInFragment$showCaptcha$1(this, idPswBaseAuthCredential));
    }

    public void showVStep2Code(@NotNull IdPswBaseAuthCredential idPswBaseAuthCredential, @NotNull String str, @NotNull MetaLoginData metaLoginData) {
        Intrinsics.f(idPswBaseAuthCredential, "authCredential");
        Intrinsics.f(str, "step1Token");
        Intrinsics.f(metaLoginData, "metaLoginData");
        View inflate = getLayoutInflater().inflate(R.layout.dg_vcode_layout, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.cb_add_to_trust_device);
        if (findViewById != null) {
            CheckBox checkBox = (CheckBox) findViewById;
            View findViewById2 = inflate.findViewById(R.id.v_code_input);
            if (findViewById2 != null) {
                EditText editText = (EditText) findViewById2;
                Context context = getContext();
                if (context == null) {
                    Intrinsics.a();
                }
                new AlertDialog.Builder(context).setTitle(R.string.v_code_title).setView(inflate).setPositiveButton(17039370, (DialogInterface.OnClickListener) new PswSignInFragment$showVStep2Code$dialog$1(this, idPswBaseAuthCredential, str, metaLoginData, editText, checkBox)).create().show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
    }
}
