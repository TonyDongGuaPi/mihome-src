package com.xiaomi.smarthome.operation.js_sdk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.operation.js_sdk.base.BaseWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.WebTitleBarView;

public class CommonWebViewFragment extends BaseWebViewFragment {
    protected static final String ARG_TITLE = "arg_title";
    protected static final String ARG_URL = "arg_url";
    protected static final String ARG_USE_TITLE_BAR = "arg_use_title_bar";
    private static final String TAG = "CommonWebViewFragment";
    private View mRootView;
    private String mTitle = "";
    private String mUrl = "";
    private boolean mUseTitleBar = false;
    private CommonWebView mWebView;
    private WebTitleBarView mWebViewTitleBar;

    public static CommonWebViewFragment newInstance(String str, String str2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_URL, str);
        bundle.putString(ARG_TITLE, str2);
        bundle.putBoolean(ARG_USE_TITLE_BAR, z);
        CommonWebViewFragment commonWebViewFragment = new CommonWebViewFragment();
        commonWebViewFragment.setArguments(bundle);
        return commonWebViewFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.choiceness_scene_web_view, viewGroup, false);
            this.mWebView = (CommonWebView) this.mRootView.findViewById(R.id.webview);
            this.mWebViewTitleBar = (WebTitleBarView) this.mRootView.findViewById(R.id.web_title_bar);
            TitleBarUtil.a((View) this.mWebViewTitleBar);
            Bundle arguments = getArguments();
            if (arguments == null) {
                return this.mRootView;
            }
            this.mUrl = arguments.getString(ARG_URL);
            if (!isValidUrl(this.mUrl)) {
                return this.mRootView;
            }
            this.mTitle = arguments.getString(ARG_TITLE);
            this.mUseTitleBar = arguments.getBoolean(ARG_USE_TITLE_BAR);
        }
        return this.mRootView;
    }

    public CommonWebView getWebView() {
        return this.mWebView;
    }

    /* access modifiers changed from: protected */
    public WebTitleBarView getWebViewTitleBar() {
        return this.mWebViewTitleBar;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.mUrl;
    }

    public String getDefaultTitle() {
        return this.mTitle;
    }

    public boolean isUseTitleBar() {
        return this.mUseTitleBar;
    }
}
