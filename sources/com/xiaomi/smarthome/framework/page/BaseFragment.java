package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class BaseFragment extends Fragment implements BaseFragmentInterface {
    private final String TAG = getClass().getSimpleName();
    public Context mContext;
    public String mPageId = "";
    protected volatile boolean mPageSelected = false;

    public boolean onBackPressed() {
        return false;
    }

    public void refreshTitleBar() {
    }

    /* access modifiers changed from: protected */
    public boolean titleBarSettled() {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!titleBarSettled()) {
            TitleBarUtil.a((Activity) getActivity(), view);
        }
        if (bundle != null) {
            this.mPageId = bundle.getString("iid");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    public void onPageSelected() {
        this.mPageSelected = true;
    }

    public void onPageDeselected() {
        this.mPageSelected = false;
    }

    public FragmentActivity getValidActivity() {
        FragmentActivity activity = super.getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return activity;
        }
        return null;
    }

    public boolean isValid() {
        return isAdded() && !isDetached() && getValidActivity() != null;
    }
}
