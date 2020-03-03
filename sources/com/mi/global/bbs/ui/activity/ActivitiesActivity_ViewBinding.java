package com.mi.global.bbs.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class ActivitiesActivity_ViewBinding implements Unbinder {
    private ActivitiesActivity target;

    @UiThread
    public ActivitiesActivity_ViewBinding(ActivitiesActivity activitiesActivity) {
        this(activitiesActivity, activitiesActivity.getWindow().getDecorView());
    }

    @UiThread
    public ActivitiesActivity_ViewBinding(ActivitiesActivity activitiesActivity, View view) {
        this.target = activitiesActivity;
        activitiesActivity.activityHomeTopBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.activity_home_top_img, "field 'activityHomeTopBg'", ImageView.class);
        activitiesActivity.activityHomeNameText = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_home_name_text, "field 'activityHomeNameText'", TextView.class);
        activitiesActivity.activityHomeDes = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_home_des, "field 'activityHomeDes'", TextView.class);
        activitiesActivity.activityHomeNavigationContainer = (AbsorbNavigationLayout) Utils.findRequiredViewAsType(view, R.id.activity_home_navigation_container, "field 'activityHomeNavigationContainer'", AbsorbNavigationLayout.class);
        activitiesActivity.activityHomeTopLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.activity_home_top_layout, "field 'activityHomeTopLayout'", LinearLayout.class);
        activitiesActivity.newsForumNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.fragment_activity_nag, "field 'newsForumNagTab'", PagerSlidingTabStrip.class);
        activitiesActivity.newsForumPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.fragment_activity_pager, "field 'newsForumPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        ActivitiesActivity activitiesActivity = this.target;
        if (activitiesActivity != null) {
            this.target = null;
            activitiesActivity.activityHomeTopBg = null;
            activitiesActivity.activityHomeNameText = null;
            activitiesActivity.activityHomeDes = null;
            activitiesActivity.activityHomeNavigationContainer = null;
            activitiesActivity.activityHomeTopLayout = null;
            activitiesActivity.newsForumNagTab = null;
            activitiesActivity.newsForumPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
