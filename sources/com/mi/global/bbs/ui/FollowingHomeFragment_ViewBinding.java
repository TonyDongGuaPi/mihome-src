package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class FollowingHomeFragment_ViewBinding implements Unbinder {
    private FollowingHomeFragment target;

    @UiThread
    public FollowingHomeFragment_ViewBinding(FollowingHomeFragment followingHomeFragment, View view) {
        this.target = followingHomeFragment;
        followingHomeFragment.followingHomeNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.following_home_nag, "field 'followingHomeNagTab'", PagerSlidingTabStrip.class);
        followingHomeFragment.followingHomePager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.following_home_pager, "field 'followingHomePager'", ViewPager.class);
        followingHomeFragment.toolbarAgent = Utils.findRequiredView(view, R.id.toolbar_agent, "field 'toolbarAgent'");
        followingHomeFragment.followingHomeNavigationContainer = (AbsorbNavigationLayout) Utils.findRequiredViewAsType(view, R.id.following_home_navigation_container, "field 'followingHomeNavigationContainer'", AbsorbNavigationLayout.class);
        followingHomeFragment.recommend = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.recommend, "field 'recommend'", RelativeLayout.class);
        followingHomeFragment.followingHomeToolBar = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.following_home_toolbar, "field 'followingHomeToolBar'", RelativeLayout.class);
        followingHomeFragment.backBtn = Utils.findRequiredView(view, R.id.btn_back, "field 'backBtn'");
    }

    @CallSuper
    public void unbind() {
        FollowingHomeFragment followingHomeFragment = this.target;
        if (followingHomeFragment != null) {
            this.target = null;
            followingHomeFragment.followingHomeNagTab = null;
            followingHomeFragment.followingHomePager = null;
            followingHomeFragment.toolbarAgent = null;
            followingHomeFragment.followingHomeNavigationContainer = null;
            followingHomeFragment.recommend = null;
            followingHomeFragment.followingHomeToolBar = null;
            followingHomeFragment.backBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
