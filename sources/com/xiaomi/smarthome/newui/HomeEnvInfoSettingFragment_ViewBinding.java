package com.xiaomi.smarthome.newui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;

public class HomeEnvInfoSettingFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private HomeEnvInfoSettingFragment f20290a;

    @UiThread
    public HomeEnvInfoSettingFragment_ViewBinding(HomeEnvInfoSettingFragment homeEnvInfoSettingFragment, View view) {
        this.f20290a = homeEnvInfoSettingFragment;
        homeEnvInfoSettingFragment.mReturnImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnImageView'", ImageView.class);
        homeEnvInfoSettingFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        homeEnvInfoSettingFragment.mPullRefresh = (DevicePtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefresh'", DevicePtrFrameLayout.class);
        homeEnvInfoSettingFragment.mTextTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTextTitle'", TextView.class);
        homeEnvInfoSettingFragment.mMenuIcom = Utils.findRequiredView(view, R.id.home_change_icon, "field 'mMenuIcom'");
        homeEnvInfoSettingFragment.mEmpty = (TextView) Utils.findRequiredViewAsType(view, R.id.empty, "field 'mEmpty'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HomeEnvInfoSettingFragment homeEnvInfoSettingFragment = this.f20290a;
        if (homeEnvInfoSettingFragment != null) {
            this.f20290a = null;
            homeEnvInfoSettingFragment.mReturnImageView = null;
            homeEnvInfoSettingFragment.mRecyclerView = null;
            homeEnvInfoSettingFragment.mPullRefresh = null;
            homeEnvInfoSettingFragment.mTextTitle = null;
            homeEnvInfoSettingFragment.mMenuIcom = null;
            homeEnvInfoSettingFragment.mEmpty = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
