package com.mi.global.shop.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.cache.WebCache;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.webview.BaseWebChromeClient;
import com.mi.global.shop.webview.BaseWebViewClient;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.webview.WebViewHelper;
import com.mi.global.shop.widget.BaseWebView;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.pulltorefresh.SimplePullToRefreshLayout;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;

public class MainTabLazyWebFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public static final String i = "MainTabLazyWebFragment";
    private static final String j = "extra_fragment_http_url";
    private static final String k = "extra_fragment_provider_url";

    /* renamed from: a  reason: collision with root package name */
    protected BaseWebView f7004a;
    protected ProgressBar b;
    public float c;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    public SimplePullToRefreshLayout g;
    private WebViewLoadingListener l;
    private View m;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus o;
    private View p;
    private ViewStub q;
    /* access modifiers changed from: private */
    public boolean r;

    public interface WebViewLoadingListener {
        void a();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.d = z;
        if (this.e && (this.d && (!this.f))) {
            try {
                this.q.inflate();
                b(this.p);
                this.f = true;
            } catch (Exception e2) {
                if (e2.getMessage() == null || !e2.getMessage().contains("MissingWebViewPackageException")) {
                    MiToast.a(getContext(), (CharSequence) getContext().getResources().getString(R.string.loading_error), 0);
                } else {
                    MiToast.a(getContext(), (CharSequence) getContext().getResources().getString(R.string.webview_tips_uploaing), 0);
                }
            }
        }
    }

    public void b(View view) {
        this.f7004a = (BaseWebView) view.findViewById(R.id.browser);
        WebViewHelper.a((WebView) this.f7004a);
        this.b = (ProgressBar) view.findViewById(R.id.browser_progress_bar);
        this.o = (EmptyLoadingViewPlus) view.findViewById(R.id.loading);
        this.g = (SimplePullToRefreshLayout) view.findViewById(R.id.home_fragment_pulltorefreshlayout);
        this.g.setOnRefreshListener(new SimplePullToRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (MainTabLazyWebFragment.this.f7004a != null) {
                    boolean unused = MainTabLazyWebFragment.this.r = true;
                    MainTabLazyWebFragment.this.c();
                }
                MainTabLazyWebFragment.this.g.onRefreshComplete();
            }
        });
        this.o.setPullToRefreshLayout(this.g);
        this.o.startLoading(false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f7004a.setWebViewClient(new MainTabWebViewClient());
            this.f7004a.setWebChromeClient(new MainTabWebChromeClient());
            WebViewCookieManager.a();
            this.n = arguments.getString(j);
            this.r = false;
            c();
            this.m = getActivity().findViewById(R.id.title_mi_logo);
            this.m.setVisibility(0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.c = a((WebView) this.f7004a);
        bundle.putFloat("mProgressToRestore", this.c);
        super.onSaveInstanceState(bundle);
        if (this.f7004a != null) {
            this.f7004a.saveState(bundle);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.c = bundle.getFloat("mProgressToRestore");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.p = layoutInflater.inflate(R.layout.shop_main_tab_web_fragment_view_stub, viewGroup, false);
        this.q = (ViewStub) this.p.findViewById(R.id.view_stub);
        return this.p;
    }

    public void onDestroy() {
        this.c = a((WebView) this.f7004a);
        if (this.f7004a != null) {
            this.f7004a.stopLoading();
            this.f7004a.removeAllViews();
            this.f7004a.destroy();
        }
        if (this.g != null) {
            this.g.removeAllViews();
            this.g = null;
        }
        super.onDestroy();
    }

    public float a(WebView webView) {
        if (this.f7004a == null) {
            return 0.0f;
        }
        return (((float) webView.getScrollY()) - ((float) webView.getTop())) / ((float) webView.getContentHeight());
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.e = true;
        if (!this.f && this.d) {
            setUserVisibleHint(true);
        }
    }

    public void onRefresh() {
        if (isVisible() && this.f7004a != null) {
            this.f7004a.reload();
        }
    }

    public void onResume() {
        LogUtil.b(i, "on resume");
        super.onResume();
        ((BaseActivity) getActivity()).updateCartAndAccount();
    }

    public void a() {
        if (this.f7004a != null) {
            this.f7004a.reload();
        }
        this.c = 0.0f;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public static MainTabLazyWebFragment a(String str, String str2) {
        MainTabLazyWebFragment mainTabLazyWebFragment = new MainTabLazyWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(j, str);
        bundle.putString(k, str2);
        mainTabLazyWebFragment.setArguments(bundle);
        return mainTabLazyWebFragment;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f7004a != null) {
            this.f7004a.loadUrl(this.n);
        }
    }

    @SuppressLint({"DefaultLocale"})
    private class MainTabWebViewClient extends BaseWebViewClient {
        private MainTabWebViewClient() {
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            String[] a2 = WebCache.a(str, true);
            if (a2 == null || !MainTabLazyWebFragment.this.isAdded()) {
                return super.shouldInterceptRequest(webView, str);
            }
            return a(MainTabLazyWebFragment.i, webView, str, a2);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String b = MainTabLazyWebFragment.i;
            LogUtil.b(b, "shouldOverrideUrlLoading Get URL:" + str);
            if (a(MainTabLazyWebFragment.this.getActivity(), str) || b(MainTabLazyWebFragment.this.getActivity(), str) || c(MainTabLazyWebFragment.this.getActivity(), str)) {
                return true;
            }
            if (str.equalsIgnoreCase(ConnectionHelper.an())) {
                MainTabLazyWebFragment.this.getActivity().finish();
                return true;
            } else if (str.contains("app/category/")) {
                webView.loadUrl(str);
                return true;
            } else {
                Intent intent = new Intent(webView.getContext(), WebActivity.class);
                intent.putExtra("url", str);
                webView.getContext().startActivity(intent);
                return true;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            String b = MainTabLazyWebFragment.i;
            LogUtil.b(b, "progress:onPageFinished," + str);
            if (!MainTabLazyWebFragment.this.r && Utils.Network.isNetWorkConnected(MainTabLazyWebFragment.this.getActivity())) {
                boolean unused = MainTabLazyWebFragment.this.r = true;
                MainTabLazyWebFragment.this.f7004a.loadUrl(MainTabLazyWebFragment.this.n);
                String b2 = MainTabLazyWebFragment.i;
                LogUtil.b(b2, "mWebView.getUrl():" + MainTabLazyWebFragment.this.f7004a.getUrl());
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            String b = MainTabLazyWebFragment.i;
            LogUtil.b(b, "failingUrl:" + str2);
        }
    }

    private class MainTabWebChromeClient extends BaseWebChromeClient {
        private MainTabWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (MainTabLazyWebFragment.this.o != null && i >= 80) {
                MainTabLazyWebFragment.this.o.stopLoading(true);
                MainTabLazyWebFragment.this.o.onFinish();
                LogUtil.b(MainTabLazyWebFragment.i, String.valueOf(i));
            }
        }
    }
}
