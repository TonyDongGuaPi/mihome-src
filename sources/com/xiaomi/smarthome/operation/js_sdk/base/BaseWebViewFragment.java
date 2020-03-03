package com.xiaomi.smarthome.operation.js_sdk.base;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.drew.lang.annotations.NotNull;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.IWebPageAction;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.WebTitleBarView;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;

public abstract class BaseWebViewFragment extends BaseFragment {
    private static final String TAG = "CommonWebViewFragment";

    public String getDefaultTitle() {
        return "";
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract String getUrl();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract CommonWebView getWebView();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract WebTitleBarView getWebViewTitleBar();

    public boolean isUseTitleBar() {
        return true;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        WebTitleBarView webViewTitleBar = getWebViewTitleBar();
        final CommonWebView webView = getWebView();
        if (isUseTitleBar()) {
            webViewTitleBar.setVisibility(0);
            webViewTitleBar.setDefaultTitle(getDefaultTitle());
            webView.setTitleBarImpl(webViewTitleBar);
            webViewTitleBar.setTitleBarActionReceiver(new WebTitleBarView.TitleBarClientAdapter(webViewTitleBar.getDefaultTitleBarActionReceiver(webView, getActivity())) {
                public void a() {
                    CommonWebViewFragmentBridge commonWebViewFragmentBridge = BaseWebViewFragment.this.getCommonWebViewFragmentBridge();
                    if (commonWebViewFragmentBridge != null) {
                        commonWebViewFragmentBridge.onBackButtonClick();
                    }
                }
            });
        } else {
            webViewTitleBar.setVisibility(8);
        }
        webView.setWebPageActionImpl(new IWebPageAction() {
            public void a(String str) {
                CommonWebViewFragmentBridge commonWebViewFragmentBridge = BaseWebViewFragment.this.getCommonWebViewFragmentBridge();
                if (commonWebViewFragmentBridge != null) {
                    commonWebViewFragmentBridge.popWindow(str);
                } else {
                    JsSdkUtils.d(webView, "unsupport method: popWindow at this page!");
                }
            }

            public void b(String str) {
                CommonWebViewFragmentBridge commonWebViewFragmentBridge = BaseWebViewFragment.this.getCommonWebViewFragmentBridge();
                if (commonWebViewFragmentBridge != null) {
                    commonWebViewFragmentBridge.pushWindow(str);
                } else {
                    JsSdkUtils.d(webView, "unsupport method: pushWindow at this page!");
                }
            }
        });
        webView.loadUrl(getUrl());
    }

    /* access modifiers changed from: protected */
    public CommonWebViewFragmentBridge getCommonWebViewFragmentBridge() {
        FragmentActivity activity = getActivity();
        if (activity instanceof CommonWebViewFragmentBridge) {
            return (CommonWebViewFragmentBridge) activity;
        }
        return null;
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this);
        getWebView().onResume();
    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this);
        getWebView().onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        CommonWebView webView = getWebView();
        if (webView != null) {
            try {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
                webView.loadUrl("");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            ViewParent parent = webView.getParent();
            webView.removeAllViews();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(webView);
            }
            webView.destroy();
        }
    }

    public boolean onBackPressed() {
        return getWebView().onBackPressed() || super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public boolean isValidUrl(String str) {
        try {
            if (TextUtils.isEmpty(Uri.parse(str).getHost())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.d(TAG, "onCreateView: illegal url: " + str + " error: " + Log.getStackTraceString(e));
            return false;
        }
    }
}
