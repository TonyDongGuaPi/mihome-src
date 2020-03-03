package com.mi.global.shop.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.cache.WebCache;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.webview.BaseWebChromeClient;
import com.mi.global.shop.webview.BaseWebViewClient;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.ptr.PtrClassicFrameLayout;
import com.mi.global.shop.widget.ptr.PtrFrameLayout;
import com.mi.global.shop.widget.ptr.PtrHandler;
import com.mi.log.LogUtil;

public class MainTabWebFragment extends BaseWebFragment {
    /* access modifiers changed from: private */
    public static final String e = "MainTabWebFragment";
    private static final String f = "extra_fragment_http_url";
    private static final String g = "extra_fragment_provider_url";
    public PtrClassicFrameLayout d;
    private View i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus k;
    /* access modifiers changed from: private */
    public boolean l;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.k = (EmptyLoadingViewPlus) onCreateView.findViewById(R.id.loading);
        this.d = (PtrClassicFrameLayout) onCreateView.findViewById(R.id.home_fragment_pulltorefreshlayout);
        this.d.setInterceptEventWhileWorking(true);
        this.d.setPtrHandler(new PtrHandler() {
            public void a(PtrFrameLayout ptrFrameLayout) {
                if (MainTabWebFragment.this.f6961a != null) {
                    boolean unused = MainTabWebFragment.this.l = true;
                    MainTabWebFragment.this.g();
                }
            }

            public boolean a(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (MainTabWebFragment.this.f6961a.getVisibility() != 0 || MainTabWebFragment.this.f6961a.getScrollY() == 0) {
                    return true;
                }
                return false;
            }
        });
        this.k.setPullToRefreshLayout(this.d);
        this.k.startLoading(false);
        return onCreateView;
    }

    public void onRefresh() {
        if (isVisible() && this.f6961a != null) {
            this.f6961a.reload();
        }
    }

    public void onResume() {
        LogUtil.b(e, "on resume");
        super.onResume();
        ((BaseActivity) getActivity()).updateCartAndAccount();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f6961a.setWebViewClient(new MainTabWebViewClient());
            this.f6961a.setWebChromeClient(new MainTabWebChromeClient());
            WebViewCookieManager.a();
            this.j = arguments.getString(f);
            this.l = false;
            g();
            this.i = getActivity().findViewById(R.id.title_mi_logo);
            this.i.setVisibility(0);
        }
    }

    public static MainTabWebFragment a(String str, String str2) {
        MainTabWebFragment mainTabWebFragment = new MainTabWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(f, str);
        bundle.putString(g, str2);
        mainTabWebFragment.setArguments(bundle);
        return mainTabWebFragment;
    }

    /* access modifiers changed from: private */
    public void g() {
        this.f6961a.loadUrl(this.j);
        String str = e;
        LogUtil.b(str, "mWebView.getUrl():" + this.f6961a.getUrl());
    }

    /* access modifiers changed from: protected */
    public int a() {
        return R.layout.shop_maintab_web_fragment;
    }

    @SuppressLint({"DefaultLocale"})
    private class MainTabWebViewClient extends BaseWebViewClient {
        private MainTabWebViewClient() {
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            String[] a2 = WebCache.a(str, true);
            if (a2 == null || !MainTabWebFragment.this.isAdded()) {
                return super.shouldInterceptRequest(webView, str);
            }
            return a(MainTabWebFragment.e, webView, str, a2);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String f = MainTabWebFragment.e;
            LogUtil.b(f, "shouldOverrideUrlLoading Get URL:" + str);
            if (a(MainTabWebFragment.this.getActivity(), str) || b(MainTabWebFragment.this.getActivity(), str) || c(MainTabWebFragment.this.getActivity(), str)) {
                return true;
            }
            if (str.equalsIgnoreCase(ConnectionHelper.an())) {
                MainTabWebFragment.this.getActivity().finish();
                return true;
            }
            Intent intent = new Intent(webView.getContext(), WebActivity.class);
            intent.putExtra("url", str);
            webView.getContext().startActivity(intent);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            String f = MainTabWebFragment.e;
            LogUtil.b(f, "progress:onPageFinished," + str);
            if (!MainTabWebFragment.this.l && Utils.Network.isNetWorkConnected(MainTabWebFragment.this.getActivity())) {
                boolean unused = MainTabWebFragment.this.l = true;
                MainTabWebFragment.this.f6961a.loadUrl(MainTabWebFragment.this.j);
                String f2 = MainTabWebFragment.e;
                LogUtil.b(f2, "mWebView.getUrl():" + MainTabWebFragment.this.f6961a.getUrl());
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            String f = MainTabWebFragment.e;
            LogUtil.b(f, "failingUrl:" + str2);
        }
    }

    private class MainTabWebChromeClient extends BaseWebChromeClient {
        private MainTabWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (MainTabWebFragment.this.k != null && i >= 80) {
                MainTabWebFragment.this.k.stopLoading(true);
                MainTabWebFragment.this.k.onFinish();
                LogUtil.b(MainTabWebFragment.e, String.valueOf(i));
            }
        }
    }
}
