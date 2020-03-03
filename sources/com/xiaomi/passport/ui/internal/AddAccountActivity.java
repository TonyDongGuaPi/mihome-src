package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.AddAccountListener;
import com.xiaomi.passport.ui.internal.PhoneNumUtil;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0016J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0016J\"\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u0012\u0010(\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010)\u001a\u00020\u0012H\u0016J\u0012\u0010*\u001a\u00020\u00122\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u0012H\u0014J\b\u0010.\u001a\u00020\u0012H\u0014J\u0010\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0016J\u0010\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u000bH\u0002J\b\u00104\u001a\u00020\u0012H\u0002J\u001a\u00105\u001a\u00020\u00122\u0006\u0010%\u001a\u00020$2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u0010\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u0019H\u0002J\b\u00108\u001a\u00020\u0012H\u0002J\u0010\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u00020\u0012H\u0002J\u0018\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/xiaomi/passport/ui/internal/AddAccountActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lcom/xiaomi/passport/ui/internal/AddAccountListener;", "()V", "defaultAuthProvider", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "mCommonErrorHandler", "Lcom/xiaomi/passport/ui/internal/CommonErrorHandler;", "mProgressHolder", "Lcom/xiaomi/passport/ui/internal/ProgressHolder;", "mSid", "", "mSnsBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "mSnsDirectlySignInType", "mWebAuth", "Lcom/xiaomi/passport/ui/internal/WebAuth;", "dismissProgress", "", "getCurrentFragment", "Landroid/support/v4/app/Fragment;", "getResources", "Landroid/content/res/Resources;", "goBack", "closeWebView", "", "gotoFragment", "fragment", "addToBackStack", "isSnsDirectlySignInMode", "loginCancelled", "loginSuccess", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onAttachFragment", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onSnsResultReturned", "success", "onSupportNavigateUp", "overrideDefaultAuthProvider", "defaultAuthProviderName", "registerBroadcast", "setAddAccountResultAndFinish", "setAllContentVisibility", "visible", "setLoginCancelledResult", "showNetworkError", "e", "Ljava/io/IOException;", "showProgress", "signInWithSnsCredential", "it", "Lcom/xiaomi/passport/ui/internal/SNSAuthProvider;", "authCredential", "Lcom/xiaomi/passport/ui/internal/SNSAuthCredential;", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class AddAccountActivity extends AppCompatActivity implements AddAccountListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public BaseAuthProvider defaultAuthProvider = PassportUI.INSTANCE.getDefaultBaseAuthProvider();
    /* access modifiers changed from: private */
    public final CommonErrorHandler mCommonErrorHandler = new CommonErrorHandler();
    private final ProgressHolder mProgressHolder = new ProgressHolder();
    /* access modifiers changed from: private */
    public String mSid;
    private BroadcastReceiver mSnsBroadcastReceiver;
    private String mSnsDirectlySignInType;
    /* access modifiers changed from: private */
    public WebAuth mWebAuth = new WebAuth();

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xiaomi/passport/ui/internal/AddAccountActivity$Companion;", "", "()V", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public static final /* synthetic */ String access$getMSid$p(AddAccountActivity addAccountActivity) {
        String str = addAccountActivity.mSid;
        if (str == null) {
            Intrinsics.c("mSid");
        }
        return str;
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        Object obj;
        super.onCreate(bundle);
        registerBroadcast();
        setContentView(R.layout.add_account_main);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.a();
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(false);
        String stringExtra = getIntent().getStringExtra("service_id");
        if (stringExtra == null) {
            stringExtra = "passport";
        }
        this.mSid = stringExtra;
        String stringExtra2 = getIntent().getStringExtra(PassportUI.EXTRA_DEFAULT_AUTH_PROVIDER);
        String stringExtra3 = getIntent().getStringExtra(PassportUI.EXTRA_DEFAULT_PHONE_COUNTRY_CODE);
        if (stringExtra2 != null) {
            overrideDefaultAuthProvider(stringExtra2);
        }
        if (getCurrentFragment() == null) {
            BaseAuthProvider baseAuthProvider = this.defaultAuthProvider;
            String str = this.mSid;
            if (str == null) {
                Intrinsics.c("mSid");
            }
            PhoneNumUtil.CountryPhoneNumData smartGetCountryCodeData = CountryCodeUtilsExtensionKt.smartGetCountryCodeData(this, stringExtra3);
            AddAccountListener.DefaultImpls.gotoFragment$default(this, baseAuthProvider.getFragment(str, smartGetCountryCodeData != null ? CountryCodeUtilsExtensionKt.getCountryCodeWithPrefix(smartGetCountryCodeData) : null), false, 2, (Object) null);
        }
        this.mSnsDirectlySignInType = getIntent().getStringExtra(PassportUI.EXTRA_SNS_SIGN_IN);
        if (this.mSnsDirectlySignInType != null) {
            Collection arrayList = new ArrayList();
            for (Object next : PassportUI.INSTANCE.getMProviders$passportui_release()) {
                if (next instanceof SNSAuthProvider) {
                    arrayList.add(next);
                }
            }
            Iterator it = ((List) arrayList).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.a((Object) ((SNSAuthProvider) obj).getName(), (Object) this.mSnsDirectlySignInType)) {
                    break;
                }
            }
            SNSAuthProvider sNSAuthProvider = (SNSAuthProvider) obj;
            if (sNSAuthProvider != null) {
                Activity activity = this;
                String str2 = this.mSid;
                if (str2 == null) {
                    Intrinsics.c("mSid");
                }
                sNSAuthProvider.startLogin(activity, str2);
                setAllContentVisibility(false);
                return;
            }
            Toast.makeText(this, R.string.passport_access_denied, 1).show();
            setLoginCancelledResult();
        }
    }

    private final void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter(SNSAuthProvider.ACTION_PASSPORT_SNS_EVENTS);
        this.mSnsBroadcastReceiver = new AddAccountActivity$registerBroadcast$1(this);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        BroadcastReceiver broadcastReceiver = this.mSnsBroadcastReceiver;
        if (broadcastReceiver == null) {
            Intrinsics.c("mSnsBroadcastReceiver");
        }
        instance.registerReceiver(broadcastReceiver, intentFilter);
    }

    private final void setAllContentVisibility(boolean z) {
        View findViewById = findViewById(16908290);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 0 : 4);
        }
    }

    private final void overrideDefaultAuthProvider(String str) {
        Collection arrayList = new ArrayList();
        Iterator it = PassportUI.INSTANCE.getMProviders$passportui_release().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            AuthProvider authProvider = (AuthProvider) next;
            if (Intrinsics.a((Object) authProvider.getName(), (Object) str) && (authProvider instanceof BaseAuthProvider)) {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        AuthProvider authProvider2 = (AuthProvider) CollectionsKt.c((List) arrayList, 0);
        if (authProvider2 != null) {
            this.defaultAuthProvider = (BaseAuthProvider) authProvider2;
        }
    }

    public void onAttachFragment(@Nullable Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment == null || !(fragment instanceof WebViewBack)) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.close_btn);
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.close_btn);
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.close_btn);
        if (textView3 != null) {
            textView3.setOnClickListener(new AddAccountActivity$onAttachFragment$1(this));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SNSAuthCredential authCredential = SNSAuthProvider.Companion.getAuthCredential();
        if (authCredential != null) {
            SNSAuthProvider.Companion.invalidAuthCredential();
            AuthProvider provider = PassportUI.INSTANCE.getProvider((AuthCredential) authCredential);
            if (provider != null) {
                signInWithSnsCredential((SNSAuthProvider) provider, authCredential);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.SNSAuthProvider");
        }
    }

    /* access modifiers changed from: private */
    public final boolean isSnsDirectlySignInMode() {
        return !TextUtils.isEmpty(this.mSnsDirectlySignInType);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        dismissProgress();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        BroadcastReceiver broadcastReceiver = this.mSnsBroadcastReceiver;
        if (broadcastReceiver == null) {
            Intrinsics.c("mSnsBroadcastReceiver");
        }
        instance.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        boolean z;
        super.onActivityResult(i, i2, intent);
        Collection arrayList = new ArrayList();
        for (Object next : PassportUI.INSTANCE.getMProviders$passportui_release()) {
            if (next instanceof SNSAuthProvider) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        Iterator it = ((List) arrayList).iterator();
        while (true) {
            boolean z2 = false;
            if (!it.hasNext()) {
                break;
            }
            Object next2 = it.next();
            if (((SNSAuthProvider) next2).getRequestCode() == i) {
                z2 = true;
            }
            if (z2) {
                arrayList2.add(next2);
            }
        }
        Collection arrayList3 = new ArrayList();
        for (Object next3 : (List) arrayList2) {
            SNSAuthProvider sNSAuthProvider = (SNSAuthProvider) next3;
            sNSAuthProvider.onActivityResult(this, i, i2, intent);
            SNSAuthCredential authCredential = SNSAuthProvider.Companion.getAuthCredential();
            onSnsResultReturned(authCredential != null);
            if (authCredential != null) {
                SNSAuthProvider.Companion.invalidAuthCredential();
                signInWithSnsCredential(sNSAuthProvider, authCredential);
                z = true;
            } else {
                z = false;
            }
            if (z) {
                arrayList3.add(next3);
            }
        }
        List list = (List) arrayList3;
    }

    /* access modifiers changed from: private */
    public final void onSnsResultReturned(boolean z) {
        if (isSnsDirectlySignInMode() && z) {
            setAllContentVisibility(true);
        }
    }

    private final void signInWithSnsCredential(SNSAuthProvider sNSAuthProvider, SNSAuthCredential sNSAuthCredential) {
        showProgress();
        sNSAuthProvider.signInAndStoreAccount(this, sNSAuthCredential).get(new AddAccountActivity$signInWithSnsCredential$1(this), new AddAccountActivity$signInWithSnsCredential$2(this));
    }

    public void loginSuccess(@NotNull AccountInfo accountInfo) {
        Intrinsics.f(accountInfo, "accountInfo");
        setAddAccountResultAndFinish(-1, accountInfo);
    }

    public void loginCancelled() {
        setLoginCancelledResult();
    }

    private final void showProgress() {
        this.mProgressHolder.showProgress(this);
    }

    /* access modifiers changed from: private */
    public final void dismissProgress() {
        this.mProgressHolder.dismissProgress();
    }

    /* access modifiers changed from: private */
    public final void showNetworkError(IOException iOException) {
        this.mCommonErrorHandler.onIOError(iOException, this, (ConstraintLayout) _$_findCachedViewById(R.id.fragment_main));
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        goBack(false);
    }

    public void goBack(boolean z) {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null && (currentFragment instanceof WebViewBack)) {
            WebViewBack webViewBack = (WebViewBack) currentFragment;
            if (webViewBack.canGoBack() && !z) {
                webViewBack.goBack();
                return;
            }
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.b(supportFragmentManager, "supportFragmentManager");
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.close_btn);
            if (textView != null) {
                textView.setVisibility(8);
            }
            getSupportFragmentManager().popBackStack();
            return;
        }
        setLoginCancelledResult();
    }

    /* access modifiers changed from: private */
    public final void setLoginCancelledResult() {
        setAddAccountResultAndFinish(0, (AccountInfo) null);
    }

    private final void setAddAccountResultAndFinish(int i, AccountInfo accountInfo) {
        AuthenticatorUtil.handleAccountAuthenticatorResponse(getIntent().getParcelableExtra("accountAuthenticatorResponse"), AccountHelper.getAccountAuthenticatorResponseResult(i, accountInfo, getIntent().getBooleanExtra("need_retry_on_authenticator_response_result", false)));
        SNSAuthProvider.Companion.invalidBindParameter();
        setResult(i);
        if (!isFinishing()) {
            finish();
        }
    }

    public void gotoFragment(@NotNull Fragment fragment, boolean z) {
        Intrinsics.f(fragment, "fragment");
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment);
        if (z) {
            replace = replace.addToBackStack((String) null);
        }
        replace.commitAllowingStateLoss();
    }

    private final Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @NotNull
    public Resources getResources() {
        if (Build.VERSION.SDK_INT >= 24) {
            Context applicationContext = getApplicationContext();
            Intrinsics.b(applicationContext, "applicationContext");
            Resources resources = applicationContext.getResources();
            Intrinsics.b(resources, "applicationContext.resources");
            return resources;
        }
        Resources resources2 = super.getResources();
        Intrinsics.b(resources2, "super.getResources()");
        return resources2;
    }
}
