package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SnsAuthActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mProgressHolder", "Lcom/xiaomi/passport/ui/internal/ProgressHolder;", "mWebView", "Landroid/webkit/WebView;", "getMWebView", "()Landroid/webkit/WebView;", "setMWebView", "(Landroid/webkit/WebView;)V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class SnsAuthActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ProgressHolder mProgressHolder = new ProgressHolder();
    @NotNull
    public WebView mWebView;

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

    @NotNull
    public final WebView getMWebView() {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        return webView;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.f(webView, "<set-?>");
        this.mWebView = webView;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
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
        ((TextView) _$_findCachedViewById(R.id.close_btn)).setOnClickListener(new SnsAuthActivity$onCreate$1(this));
        Context context = this;
        this.mWebView = new SnsAuthActivity$onCreate$2(this, context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        webView.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.webview_container);
        WebView webView2 = this.mWebView;
        if (webView2 == null) {
            Intrinsics.c("mWebView");
        }
        relativeLayout.addView(webView2);
        this.mProgressHolder.showProgress(context);
        WebView webView3 = this.mWebView;
        if (webView3 == null) {
            Intrinsics.c("mWebView");
        }
        webView3.loadUrl(getIntent().getStringExtra("url"));
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
}
