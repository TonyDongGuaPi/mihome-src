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

public class HomeEnvInfoFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private HomeEnvInfoFragment f20285a;

    @UiThread
    public HomeEnvInfoFragment_ViewBinding(HomeEnvInfoFragment homeEnvInfoFragment, View view) {
        this.f20285a = homeEnvInfoFragment;
        homeEnvInfoFragment.mReturnImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnImageView'", ImageView.class);
        homeEnvInfoFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        homeEnvInfoFragment.mPullRefresh = (DevicePtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefresh'", DevicePtrFrameLayout.class);
        homeEnvInfoFragment.mGroupTitle = Utils.findRequiredView(view, R.id.title_group, "field 'mGroupTitle'");
        homeEnvInfoFragment.mMenuIcom = Utils.findRequiredView(view, R.id.home_change_icon, "field 'mMenuIcom'");
        homeEnvInfoFragment.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        homeEnvInfoFragment.mMaskView = Utils.findRequiredView(view, R.id.mask, "field 'mMaskView'");
        homeEnvInfoFragment.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTv'", TextView.class);
        homeEnvInfoFragment.mEmpty = (TextView) Utils.findRequiredViewAsType(view, R.id.empty, "field 'mEmpty'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HomeEnvInfoFragment homeEnvInfoFragment = this.f20285a;
        if (homeEnvInfoFragment != null) {
            this.f20285a = null;
            homeEnvInfoFragment.mReturnImageView = null;
            homeEnvInfoFragment.mRecyclerView = null;
            homeEnvInfoFragment.mPullRefresh = null;
            homeEnvInfoFragment.mGroupTitle = null;
            homeEnvInfoFragment.mMenuIcom = null;
            homeEnvInfoFragment.mTitleBar = null;
            homeEnvInfoFragment.mMaskView = null;
            homeEnvInfoFragment.mTitleTv = null;
            homeEnvInfoFragment.mEmpty = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
