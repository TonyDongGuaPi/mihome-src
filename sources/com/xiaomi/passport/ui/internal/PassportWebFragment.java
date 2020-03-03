package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.xiaomi.passport.ui.settings.utils.AccountSmsVerifyCodeReceiver;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J(\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PassportWebFragment;", "Landroid/support/v4/app/Fragment;", "Lcom/xiaomi/passport/ui/internal/WebViewBack;", "()V", "mProgressHolder", "Lcom/xiaomi/passport/ui/internal/ProgressHolder;", "mWebView", "Landroid/webkit/WebView;", "canGoBack", "", "goBack", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PassportWebFragment extends Fragment implements WebViewBack {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ProgressHolder mProgressHolder = new ProgressHolder();
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

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PassportWebFragment$Companion;", "", "()V", "newInstance", "Lcom/xiaomi/passport/ui/internal/PassportWebFragment;", "url", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PassportWebFragment newInstance(@NotNull String str) {
            Intrinsics.f(str, "url");
            PassportWebFragment passportWebFragment = new PassportWebFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            passportWebFragment.setArguments(bundle);
            return passportWebFragment;
        }
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
        this.mWebView = new PassportWebFragment$onCreateView$1(this, context);
        ProgressHolder progressHolder = this.mProgressHolder;
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.a();
        }
        progressHolder.showProgress(context2);
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        webView.loadUrl((String) arguments.get("url"));
        WebView webView2 = this.mWebView;
        if (webView2 == null) {
            Intrinsics.c("mWebView");
        }
        return webView2;
    }

    public void goBack() {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        webView.goBack();
    }

    public boolean canGoBack() {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.c("mWebView");
        }
        return webView.canGoBack();
    }
}
