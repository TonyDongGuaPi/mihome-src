package com.xiaomi.smarthome.operation.js_sdk.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.drew.lang.annotations.NotNull;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.AndroidBug5497Workaround;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.AppBackFrontObserver;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.LifeCycleEvent;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.WebViewLifeCycleDispatcher;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseFragmentWebViewActivity extends BaseActivity implements CommonWebViewFragmentBridge {
    static final String TAG = "OperationCommonWebView";
    private final AppBackFrontObserver mAppBackFrontObserver = new AppBackFrontObserver();
    private AndroidBug5497Workaround mBug5497Workaround;
    /* access modifiers changed from: private */
    public WebViewLifeCycleDispatcher mWebViewLifeCycleDispatcher = null;

    /* access modifiers changed from: protected */
    public abstract int getFragmentContainerId();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBug5497Workaround = AndroidBug5497Workaround.a();
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        installLifecycleDispatcher();
        try {
            this.mBug5497Workaround.a((Activity) this);
            this.mBug5497Workaround.a(true);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mBug5497Workaround.b();
    }

    private void installLifecycleDispatcher() {
        if (this.mWebViewLifeCycleDispatcher == null) {
            this.mWebViewLifeCycleDispatcher = new WebViewLifeCycleDispatcher();
            getLifecycle().a(this.mAppBackFrontObserver);
            this.mAppBackFrontObserver.a((AppBackFrontObserver.OnAppRunStateChangeListener) new AppBackFrontObserver.OnAppRunStateChangeListener() {
                public void a() {
                    if (BaseFragmentWebViewActivity.this.mWebViewLifeCycleDispatcher != null) {
                        BaseFragmentWebViewActivity.this.mWebViewLifeCycleDispatcher.a(LifeCycleEvent.APP_RESUME, BaseFragmentWebViewActivity.this.getLatestFragment(), (String) null);
                    }
                }

                public void b() {
                    if (BaseFragmentWebViewActivity.this.mWebViewLifeCycleDispatcher != null) {
                        BaseFragmentWebViewActivity.this.mWebViewLifeCycleDispatcher.a(LifeCycleEvent.APP_PAUSE, BaseFragmentWebViewActivity.this.getLatestFragment(), (String) null);
                    }
                }
            });
        }
    }

    public void popWindow(String str) {
        List<CommonWebViewFragment> filterdFragments = getFilterdFragments();
        if (filterdFragments.size() == 1) {
            finish();
            return;
        }
        super.onBackPressed();
        if (filterdFragments.size() > 1) {
            CommonWebViewFragment commonWebViewFragment = filterdFragments.get(filterdFragments.size() - 2);
            if (this.mWebViewLifeCycleDispatcher != null) {
                this.mWebViewLifeCycleDispatcher.a(LifeCycleEvent.PAGE_RESUME, commonWebViewFragment, str);
            }
        }
    }

    private List<CommonWebViewFragment> getFilterdFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Fragment next : fragments) {
            if (next instanceof CommonWebViewFragment) {
                arrayList.add((CommonWebViewFragment) next);
            }
        }
        return arrayList;
    }

    public void pushWindow(String str) {
        try {
            String optString = new JSONObject(str).optString("url");
            if (TextUtils.isEmpty(optString)) {
                LogUtil.a(TAG, "pushWindow: invalid url: " + str);
                return;
            }
            CommonWebViewFragment newInstance = CommonWebViewFragment.newInstance(optString, "", true);
            List<CommonWebViewFragment> filterdFragments = getFilterdFragments();
            CommonWebViewFragment commonWebViewFragment = !filterdFragments.isEmpty() ? filterdFragments.get(filterdFragments.size() - 1) : null;
            openNewWindow(commonWebViewFragment, newInstance);
            if (this.mWebViewLifeCycleDispatcher != null) {
                this.mWebViewLifeCycleDispatcher.a(LifeCycleEvent.PAGE_PAUSE, commonWebViewFragment, (String) null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void openNewWindow(CommonWebViewFragment commonWebViewFragment, @NotNull CommonWebViewFragment commonWebViewFragment2) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        String valueOf = String.valueOf(supportFragmentManager.getBackStackEntryCount());
        if (commonWebViewFragment != commonWebViewFragment2) {
            beginTransaction.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_in_right, R.anim.fragment_out_right);
            beginTransaction.addToBackStack(valueOf);
            if (commonWebViewFragment2.isAdded()) {
                if (commonWebViewFragment != null) {
                    beginTransaction.hide(commonWebViewFragment);
                }
                beginTransaction.show(commonWebViewFragment2).commit();
                return;
            }
            if (commonWebViewFragment != null) {
                beginTransaction.hide(commonWebViewFragment);
            }
            beginTransaction.add(getFragmentContainerId(), commonWebViewFragment2, valueOf).commit();
        }
    }

    public void onBackPressed() {
        BaseFragment baseFragment;
        List<CommonWebViewFragment> filterdFragments = getFilterdFragments();
        if (filterdFragments.size() > 0 && (baseFragment = filterdFragments.get(filterdFragments.size() - 1)) != null && baseFragment.onBackPressed()) {
            return;
        }
        if (filterdFragments.size() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void onBackButtonClick() {
        onBackPressed();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.mAppBackFrontObserver.a(i);
    }

    /* access modifiers changed from: protected */
    public CommonWebViewFragment getLatestFragment() {
        List<CommonWebViewFragment> filterdFragments = getFilterdFragments();
        if (filterdFragments.isEmpty()) {
            return null;
        }
        return filterdFragments.get(filterdFragments.size() - 1);
    }
}
