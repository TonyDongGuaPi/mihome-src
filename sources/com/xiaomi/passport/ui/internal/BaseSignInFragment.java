package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.diagnosis.DiagnosisLauncher;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0004J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u001c\u0010\u0018\u001a\u00020\u00162\b\b\u0001\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;", "Lcom/xiaomi/passport/ui/internal/SignInFragment;", "provider", "", "(Ljava/lang/String;)V", "defaultCountryCodeWithPrefix", "getDefaultCountryCodeWithPrefix", "()Ljava/lang/String;", "setDefaultCountryCodeWithPrefix", "mAuthProvider", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "mDiagnosisLauncher", "Lcom/xiaomi/passport/ui/diagnosis/DiagnosisLauncher;", "mOnDiagnosisClicked", "Landroid/view/View$OnClickListener;", "mUserLicenseUtils", "Lcom/xiaomi/passport/ui/internal/UserLicenseUtils;", "getProvider", "span1", "Landroid/text/style/ClickableSpan;", "span2", "hideSns", "", "onDestroyView", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setAgreementText", "showBindTitle", "showSns", "passportui_release"}, k = 1, mv = {1, 1, 10})
public abstract class BaseSignInFragment extends SignInFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private String defaultCountryCodeWithPrefix;
    private BaseAuthProvider mAuthProvider = PassportUI.INSTANCE.getBaseAuthProvider(this.provider);
    /* access modifiers changed from: private */
    public DiagnosisLauncher mDiagnosisLauncher;
    private final View.OnClickListener mOnDiagnosisClicked = new BaseSignInFragment$mOnDiagnosisClicked$1(this);
    private final UserLicenseUtils mUserLicenseUtils = new UserLicenseUtils();
    @NotNull
    private final String provider;
    private ClickableSpan span1;
    private ClickableSpan span2;

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

    public BaseSignInFragment(@NotNull String str) {
        Intrinsics.f(str, "provider");
        this.provider = str;
    }

    @NotNull
    public final String getProvider() {
        return this.provider;
    }

    @Nullable
    public final String getDefaultCountryCodeWithPrefix() {
        return this.defaultCountryCodeWithPrefix;
    }

    public final void setDefaultCountryCodeWithPrefix(@Nullable String str) {
        this.defaultCountryCodeWithPrefix = str;
    }

    public void onViewCreated(@NotNull @NonNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        super.onViewCreated(view, bundle);
        this.mDiagnosisLauncher = new DiagnosisLauncher(getActivity());
        BaseAuthProvider baseAuthProvider = this.mAuthProvider;
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        String string = arguments.getString("sid");
        Intrinsics.b(string, "arguments!!.getString(\"sid\")");
        baseAuthProvider.setPresenter(string, this);
        TextView textView = (TextView) _$_findCachedViewById(R.id.signin_title);
        Intrinsics.b(textView, "signin_title");
        CharSequence text = textView.getText();
        Intrinsics.b(text, "signin_title.text");
        if (!TextUtils.isEmpty(StringsKt.b(text))) {
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.mi_logo);
            Intrinsics.b(imageView, "mi_logo");
            imageView.setVisibility(8);
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.signin_title);
            Intrinsics.b(textView2, "signin_title");
            textView2.setVisibility(0);
        }
        ((TextView) _$_findCachedViewById(R.id.signin_title)).setOnClickListener(this.mOnDiagnosisClicked);
        ((ImageView) _$_findCachedViewById(R.id.mi_logo)).setOnClickListener(this.mOnDiagnosisClicked);
        if (SNSAuthProvider.Companion.isBindingSnsAccount()) {
            showBindTitle();
            hideSns();
        } else {
            showSns();
        }
        setAgreementText();
    }

    public void onDestroyView() {
        super.onDestroyView();
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_user_agreement);
        CharSequence text = textView != null ? textView.getText() : null;
        if (text != null) {
            ((Spannable) text).removeSpan(this.span1);
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_user_agreement);
            CharSequence text2 = textView2 != null ? textView2.getText() : null;
            if (text2 != null) {
                ((Spannable) text2).removeSpan(this.span2);
                ClickableSpan clickableSpan = null;
                this.span1 = clickableSpan;
                this.span2 = clickableSpan;
                _$_clearFindViewByIdCache();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.text.Spannable");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.text.Spannable");
    }

    private final void showBindTitle() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.mi_logo);
        Intrinsics.b(imageView, "mi_logo");
        imageView.setVisibility(8);
        TextView textView = (TextView) _$_findCachedViewById(R.id.signin_title);
        Intrinsics.b(textView, "signin_title");
        textView.setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.signin_title)).setText(R.string.bind_sign_in_title);
    }

    /* access modifiers changed from: protected */
    public final void hideSns() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.sns_layout);
        Intrinsics.b(linearLayout, "sns_layout");
        linearLayout.setVisibility(4);
    }

    private final void showSns() {
        int i = 0;
        for (AuthProvider next : PassportUI.INSTANCE.getMProviders$passportui_release()) {
            if (next instanceof SNSAuthProvider) {
                View inflate = getLayoutInflater().inflate(R.layout.sns_item, (ViewGroup) null);
                View findViewById = inflate.findViewById(R.id.sns_image);
                if (findViewById != null) {
                    ImageView imageView = (ImageView) findViewById;
                    SNSAuthProvider sNSAuthProvider = (SNSAuthProvider) next;
                    imageView.setImageResource(sNSAuthProvider.getIconRes());
                    ((LinearLayout) _$_findCachedViewById(R.id.sns_list_layout)).addView(inflate);
                    imageView.setOnClickListener(new BaseSignInFragment$showSns$1(this, next));
                    i++;
                    int tintColor = sNSAuthProvider.getTintColor();
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                }
            }
        }
        if (i <= 0) {
            hideSns();
        }
    }

    private final void setAgreementText() {
        String str;
        UserLicenseUtils userLicenseUtils = this.mUserLicenseUtils;
        Context context = getContext();
        if (context == null) {
            Intrinsics.a();
        }
        HashMap<String, URLLicense> uRLLicenses = userLicenseUtils.getURLLicenses(context);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i = 0;
        int i2 = 0;
        for (Map.Entry entry : uRLLicenses.entrySet()) {
            i++;
            if (i == 1) {
                Context context2 = getContext();
                if (context2 == null) {
                    Intrinsics.a();
                }
                str = context2.getString(R.string.passport_user_agreement_prefix);
            } else if (i == uRLLicenses.size()) {
                StringBuilder sb = new StringBuilder();
                sb.append("  ");
                Context context3 = getContext();
                if (context3 == null) {
                    Intrinsics.a();
                }
                sb.append(context3.getString(R.string.and));
                sb.append("  ");
                str = sb.toString();
            } else {
                str = " ,  ";
            }
            spannableStringBuilder.append(str);
            int length = i2 + str.length();
            String readableText = ((URLLicense) entry.getValue()).getReadableText();
            spannableStringBuilder.append(readableText);
            int length2 = readableText.length() + length;
            spannableStringBuilder.setSpan(new BaseSignInFragment$setAgreementText$1(this, entry), length, length2, 33);
            i2 = length2;
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_user_agreement);
        Intrinsics.b(textView, "tv_user_agreement");
        textView.setText(spannableStringBuilder);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_user_agreement);
        Intrinsics.b(textView2, "tv_user_agreement");
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
