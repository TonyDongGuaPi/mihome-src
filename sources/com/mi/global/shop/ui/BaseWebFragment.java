package com.mi.global.shop.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.mi.global.shop.R;
import com.mi.global.shop.webview.WebViewHelper;
import com.mi.global.shop.widget.BaseWebView;
import com.mi.log.LogUtil;

public abstract class BaseWebFragment extends BaseFragment {
    private static final String d = "BaseWebFragment";

    /* renamed from: a  reason: collision with root package name */
    protected BaseWebView f6961a;
    protected ProgressBar b;
    public float c;
    private WebViewLoadingListener e;

    public interface WebViewLoadingListener {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract int a();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(a(), viewGroup, false);
        this.f6961a = (BaseWebView) inflate.findViewById(R.id.browser);
        WebViewHelper.a((WebView) this.f6961a);
        this.b = (ProgressBar) inflate.findViewById(R.id.browser_progress_bar);
        return inflate;
    }

    public void a(WebViewLoadingListener webViewLoadingListener) {
        this.e = webViewLoadingListener;
    }

    public boolean b() {
        if (!this.f6961a.canGoBack()) {
            return false;
        }
        this.f6961a.goBack();
        return true;
    }

    public void c() {
        if (this.f6961a != null) {
            this.f6961a.reload();
        }
        this.c = 0.0f;
    }

    public void a(String str) {
        String str2 = d;
        LogUtil.b(str2, "loadUrl: " + str);
        this.f6961a.loadUrl(str);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        this.c = a((WebView) this.f6961a);
        this.f6961a.stopLoading();
        this.f6961a.removeAllViews();
        this.f6961a.destroy();
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.c = a((WebView) this.f6961a);
        bundle.putFloat("mProgressToRestore", this.c);
        super.onSaveInstanceState(bundle);
        this.f6961a.saveState(bundle);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.c = bundle.getFloat("mProgressToRestore");
        }
    }

    public float a(WebView webView) {
        return (((float) webView.getScrollY()) - ((float) webView.getTop())) / ((float) webView.getContentHeight());
    }
}
