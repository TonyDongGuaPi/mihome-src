package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.NotificationAuthTask;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\bH\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\bH\u0014J\u0018\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xiaomi/passport/ui/internal/NotificationActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mWebView", "Landroid/webkit/WebView;", "getResources", "Landroid/content/res/Resources;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNeedReLogin", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPause", "setAddAccountResultAndFinish", "resultCode", "", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "NotificationAuthTaskCallback", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class NotificationActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private WebView mWebView;

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

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        setContentView(R.layout.passport_webview_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.a();
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 == null) {
            Intrinsics.a();
        }
        supportActionBar2.setDisplayShowHomeEnabled(true);
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 == null) {
            Intrinsics.a();
        }
        supportActionBar3.setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) _$_findCachedViewById(R.id.close_btn);
        Intrinsics.b(textView, "close_btn");
        textView.setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.close_btn)).setOnClickListener(new NotificationActivity$onCreate$1(this));
        String stringExtra = getIntent().getStringExtra("notification_url");
        this.mWebView = new NotificationActivity$onCreate$2(this, this);
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        webView.loadUrl(stringExtra);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        WebView webView2 = this.mWebView;
        if (webView2 == null) {
            Intrinsics.c("mWebView");
        }
        webView2.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.webview_container);
        WebView webView3 = this.mWebView;
        if (webView3 == null) {
            Intrinsics.c("mWebView");
        }
        relativeLayout.addView(webView3);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\f"}, d2 = {"Lcom/xiaomi/passport/ui/internal/NotificationActivity$NotificationAuthTaskCallback;", "Lcom/xiaomi/passport/ui/internal/NotificationAuthTask$NotificationAuthUICallback;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "setActivity", "call", "", "result", "Lcom/xiaomi/accountsdk/account/data/NotificationAuthResult;", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class NotificationAuthTaskCallback implements NotificationAuthTask.NotificationAuthUICallback {
        @NotNull
        private Activity activity;

        public NotificationAuthTaskCallback(@NotNull Activity activity2) {
            Intrinsics.f(activity2, "activity");
            this.activity = activity2;
        }

        @NotNull
        public final Activity getActivity() {
            return this.activity;
        }

        public final void setActivity(@NotNull Activity activity2) {
            Intrinsics.f(activity2, "<set-?>");
            this.activity = activity2;
        }

        public void call(@Nullable NotificationAuthResult notificationAuthResult) {
            if (!this.activity.isFinishing()) {
                Intent intent = new Intent();
                if (notificationAuthResult != null) {
                    intent.putExtra("notification_auth_end", notificationAuthResult);
                    this.activity.setResult(-1, intent);
                } else {
                    this.activity.setResult(0, intent);
                }
                this.activity.finish();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setAddAccountResultAndFinish(int i, AccountInfo accountInfo) {
        Parcelable parcelableExtra = getIntent().getParcelableExtra("accountAuthenticatorResponse");
        Intrinsics.b(parcelableExtra, "intent.getParcelableExtr…T_AUTHENTICATOR_RESPONSE)");
        AuthenticatorUtil.handleAccountAuthenticatorResponse(parcelableExtra, AccountHelper.getAccountAuthenticatorResponseResult(i, accountInfo, getIntent().getBooleanExtra("need_retry_on_authenticator_response_result", false)));
        finish();
    }

    /* access modifiers changed from: private */
    public final void onNeedReLogin() {
        setResult(0);
        finish();
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        if (webView.canGoBack()) {
            WebView webView2 = this.mWebView;
            if (webView2 == null) {
                Intrinsics.c("mWebView");
            }
            webView2.goBack();
            return;
        }
        super.onBackPressed();
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
