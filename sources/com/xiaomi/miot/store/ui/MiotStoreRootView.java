package com.xiaomi.miot.store.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.xiaomi.miot.store.R;
import com.xiaomi.miot.store.common.MiotStoreWebView;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.youpin.log.LogUtils;
import java.util.HashMap;
import java.util.Map;

public class MiotStoreRootView extends FrameLayout implements RNAppStoreApiManager.OnInitialCompleteListener {
    static int gInstanceId = 1;
    private final String TAG = "MiotStoreRootView";
    boolean initialed = false;
    Bundle mBundle;
    int mInstanceId;
    boolean mIsResumed = false;
    private TextView mLoadingView;
    MiotStoreWebView mMiotStoreWebView;
    String mModule;
    ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;
    String mUrl = "";

    public MiotStoreRootView(Context context, String str, Bundle bundle) {
        super(context);
        int i = gInstanceId;
        gInstanceId = i + 1;
        this.mInstanceId = i;
        bundle = bundle == null ? new Bundle() : bundle;
        this.mModule = str;
        this.mBundle = bundle;
        bundle.putInt("instance", this.mInstanceId);
        initial();
    }

    public int getInstanceId() {
        return this.mInstanceId;
    }

    /* access modifiers changed from: package-private */
    public void initial() {
        if (this.mBundle != null) {
            this.mUrl = this.mBundle.getString("uri");
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            this.mUrl = "http://home.mi.com/shop/main";
        }
        LayoutInflater from = LayoutInflater.from(getContext());
        if (!RNAppStoreApiManager.a().h()) {
            View inflate = from.inflate(R.layout.webview_layout, (ViewGroup) null);
            this.mMiotStoreWebView = (MiotStoreWebView) inflate.findViewById(R.id.web_view);
            this.mMiotStoreWebView.loadUrl(this.mUrl);
            addView(inflate);
            return;
        }
        this.mReactRootView = new ReactRootView(getContext());
        this.mLoadingView = new TextView(getContext());
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.mLoadingView, new FrameLayout.LayoutParams(-2, -2, 17));
        addView(this.mReactRootView, new FrameLayout.LayoutParams(-1, -1, 17));
        this.mLoadingView.setText(R.string.miot_store_loading_view_text);
        this.mLoadingView.setTextColor(Color.parseColor("#D5D5D5"));
        this.mLoadingView.setTextSize(1, 14.6f);
        this.mReactRootView.setOnSizeChangedListener(new SizeMonitoringFrameLayout.OnSizeChangedListener() {
            public void onSizeChanged(int i, int i2, int i3, int i4) {
                MiotStoreRootView.this.sendPageSize(i, i2);
            }
        });
        this.mLoadingView.setVisibility(0);
        this.mReactRootView.setVisibility(4);
        RNAppStoreApiManager.a().b((RNAppStoreApiManager.OnInitialCompleteListener) this);
    }

    public void onResume() {
        LogUtils.d("MiotStoreRootView", "onResume");
        this.mIsResumed = true;
        if (this.mMiotStoreWebView != null) {
            this.mMiotStoreWebView.onResume();
        } else if (this.mReactInstanceManager != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("instance", String.valueOf(getInstanceId()));
            hashMap.put("url", this.mUrl);
            RNAppStoreApiManager.a().a("willAppear", (Map<String, Object>) hashMap);
        }
    }

    public void onPause() {
        LogUtils.d("MiotStoreRootView", "onPause");
        this.mIsResumed = false;
        if (this.mMiotStoreWebView != null) {
            this.mMiotStoreWebView.onPause();
        } else if (this.mReactInstanceManager != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("instance", String.valueOf(getInstanceId()));
            hashMap.put("url", this.mUrl);
            RNAppStoreApiManager.a().a("willDisappear", (Map<String, Object>) hashMap);
        }
    }

    public void onDestroy() {
        LogUtils.d("MiotStoreRootView", ActivityInfo.TYPE_STR_ONDESTROY);
        if (this.mMiotStoreWebView != null) {
            this.mMiotStoreWebView.destroy();
            this.mMiotStoreWebView = null;
        }
        if (this.mReactRootView != null) {
            this.mReactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        RNAppStoreApiManager.a().a((RNAppStoreApiManager.OnInitialCompleteListener) this);
    }

    /* access modifiers changed from: package-private */
    public void sendPageSize(int i, int i2) {
        if (i != 0 && i2 != 0) {
            LogUtils.d("MiotStoreRootView", "sendPageSize");
            if (this.mReactInstanceManager != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("height", String.valueOf(i2));
                hashMap.put("width", String.valueOf(i));
                RNAppStoreApiManager.a().a("viewSizeChanged", (Map<String, Object>) hashMap);
            }
        }
    }

    public boolean onBackPress() {
        LogUtils.d("MiotStoreRootView", "onBackPress");
        if (this.mMiotStoreWebView != null && this.mMiotStoreWebView.canGoBack()) {
            this.mMiotStoreWebView.goBack();
            return true;
        } else if (this.mReactInstanceManager == null) {
            return false;
        } else {
            this.mReactInstanceManager.onBackPressed();
            return true;
        }
    }

    public void reloadRN() {
        String str;
        if (this.mBundle != null) {
            String string = this.mBundle.getString("uri");
            if (TextUtils.isEmpty(string) || !string.contains("?")) {
                str = string + "?reload=true";
            } else {
                str = string + "&reload=true";
            }
            this.mBundle.putString("uri", str);
        }
    }

    public void onInitialSuccess() {
        if (this.initialed) {
            LogUtils.d("MiotStoreRootView", "had initialed");
        } else if (RNAppStoreApiManager.a().h() && this.mReactRootView != null) {
            LogUtils.d("MiotStoreRootView", "onInitialSuccess");
            this.mReactInstanceManager = RNAppStoreApiManager.a().k();
            this.initialed = true;
            try {
                this.mReactRootView.startReactApplication(this.mReactInstanceManager, this.mModule, this.mBundle);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.postCatchedException(e);
            }
            this.mLoadingView.setVisibility(4);
            this.mReactRootView.setVisibility(0);
            if (this.mReactRootView != null) {
                sendPageSize(this.mReactRootView.getMeasuredWidth(), this.mReactRootView.getMeasuredHeight());
            }
            if (this.mIsResumed) {
                HashMap hashMap = new HashMap();
                hashMap.put("instance", String.valueOf(getInstanceId()));
                hashMap.put("url", this.mUrl);
                RNAppStoreApiManager.a().a("willAppear", (Map<String, Object>) hashMap);
            }
        }
    }

    public void onInitialFail() {
        LogUtils.d("MiotStoreRootView", "onInitialFail");
        if (RNAppStoreApiManager.a().h() && this.mReactRootView != null) {
            this.mLoadingView.setText(R.string.miot_store_loaded_failed_view_text);
            this.mLoadingView.setVisibility(0);
            this.mReactRootView.setVisibility(4);
        }
    }
}
