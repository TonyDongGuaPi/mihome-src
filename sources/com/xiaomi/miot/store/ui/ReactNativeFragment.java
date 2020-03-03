package com.xiaomi.miot.store.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.bridge.WXBridgeManager;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.ref.WeakReference;

public class ReactNativeFragment extends Fragment {
    static final String TAG = "ReactNativeFragment";
    Bundle mBundle;
    protected MiotStoreRootView mMiotStoreRootView;
    String mModule;

    public MiotStoreRootView createRootView(Context context, String str, Bundle bundle) {
        LogUtils.d(TAG, "createRootView");
        this.mMiotStoreRootView = new MiotStoreRootView(context, str, bundle);
        return this.mMiotStoreRootView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtils.d(TAG, "onCreateView");
        if (this.mMiotStoreRootView == null) {
            this.mBundle = getArguments();
            this.mModule = this.mBundle == null ? "MiotStore" : this.mBundle.getString(WXBridgeManager.MODULE);
            if (this.mBundle == null) {
                this.mBundle = new Bundle();
            }
            Context activity = getActivity();
            WeakReference weakReference = new WeakReference(getActivity());
            if (weakReference.get() != null) {
                activity = (Activity) weakReference.get();
            }
            this.mMiotStoreRootView = createRootView(activity, this.mModule, this.mBundle);
        }
        return this.mMiotStoreRootView;
    }

    public boolean onBackPressed() {
        return this.mMiotStoreRootView.onBackPress();
    }

    public void onStart() {
        super.onStart();
        TitleBarUtil.a(getActivity().getWindow());
    }

    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
        if (this.mMiotStoreRootView != null) {
            this.mMiotStoreRootView.onResume();
        }
    }

    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
        if (this.mMiotStoreRootView != null) {
            this.mMiotStoreRootView.onPause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mMiotStoreRootView != null) {
            this.mMiotStoreRootView.onDestroy();
        }
    }
}
