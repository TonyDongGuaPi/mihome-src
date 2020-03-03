package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AppSettingsItem;
import com.mi.global.bbs.view.CircleImageView;

public class MineFragment_ViewBinding implements Unbinder {
    private MineFragment target;
    private View view2131493358;
    private View view2131493359;
    private View view2131493360;
    private View view2131493361;
    private View view2131493364;
    private View view2131493367;
    private View view2131493368;
    private View view2131493372;
    private View view2131493375;

    @UiThread
    public MineFragment_ViewBinding(final MineFragment mineFragment, View view) {
        this.target = mineFragment;
        mineFragment.userItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_item_icon, "field 'userItemIcon'", CircleImageView.class);
        mineFragment.fragmentMeRefresh = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.fragment_me_refresh, "field 'fragmentMeRefresh'", SwipeRefreshLayout.class);
        mineFragment.fragmentMeName = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_name, "field 'fragmentMeName'", TextView.class);
        mineFragment.fragmentMePointsText = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_points_text, "field 'fragmentMePointsText'", TextView.class);
        mineFragment.fragmentMePointsSubtext = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_points_subtext, "field 'fragmentMePointsSubtext'", TextView.class);
        mineFragment.fragmentMeMedalText = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_medal_text, "field 'fragmentMeMedalText'", TextView.class);
        mineFragment.fragmentMeMedalSubtext = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_medal_subtext, "field 'fragmentMeMedalSubtext'", TextView.class);
        mineFragment.fragmentMeScroll = (ScrollView) Utils.findRequiredViewAsType(view, R.id.fragment_me_scroll, "field 'fragmentMeScroll'", ScrollView.class);
        mineFragment.fragmentMeMoment = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_me_moment, "field 'fragmentMeMoment'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fragment_me_points_layout, "field 'fragmentMePointsLayout' and method 'onClick'");
        mineFragment.fragmentMePointsLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.fragment_me_points_layout, "field 'fragmentMePointsLayout'", LinearLayout.class);
        this.view2131493368 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.fragment_me_medal_layout, "field 'fragmentMeMedalLayout' and method 'onClick'");
        mineFragment.fragmentMeMedalLayout = (LinearLayout) Utils.castView(findRequiredView2, R.id.fragment_me_medal_layout, "field 'fragmentMeMedalLayout'", LinearLayout.class);
        this.view2131493361 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.fragment_me_missions, "field 'fragmentMeMission' and method 'onClick'");
        mineFragment.fragmentMeMission = (AppSettingsItem) Utils.castView(findRequiredView3, R.id.fragment_me_missions, "field 'fragmentMeMission'", AppSettingsItem.class);
        this.view2131493364 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.fragment_me_check_in, "field 'fragmentMeCheckIn' and method 'onClick'");
        mineFragment.fragmentMeCheckIn = (AppSettingsItem) Utils.castView(findRequiredView4, R.id.fragment_me_check_in, "field 'fragmentMeCheckIn'", AppSettingsItem.class);
        this.view2131493359 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.fragment_me_notify, "field 'fragmentMeNotify' and method 'onClick'");
        mineFragment.fragmentMeNotify = (AppSettingsItem) Utils.castView(findRequiredView5, R.id.fragment_me_notify, "field 'fragmentMeNotify'", AppSettingsItem.class);
        this.view2131493367 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.fragment_me_favor, "field 'fragmentMeFavor' and method 'onClick'");
        mineFragment.fragmentMeFavor = (AppSettingsItem) Utils.castView(findRequiredView6, R.id.fragment_me_favor, "field 'fragmentMeFavor'", AppSettingsItem.class);
        this.view2131493360 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.fragment_me_threads, "field 'fragmentMeThreads' and method 'onClick'");
        mineFragment.fragmentMeThreads = (AppSettingsItem) Utils.castView(findRequiredView7, R.id.fragment_me_threads, "field 'fragmentMeThreads'", AppSettingsItem.class);
        this.view2131493375 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.fragment_me_reply, "field 'fragmentMeReply' and method 'onClick'");
        mineFragment.fragmentMeReply = (AppSettingsItem) Utils.castView(findRequiredView8, R.id.fragment_me_reply, "field 'fragmentMeReply'", AppSettingsItem.class);
        this.view2131493372 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
        mineFragment.mFragmentMeRoot = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fragment_me_root, "field 'mFragmentMeRoot'", FrameLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, R.id.fragment_me_center, "method 'onClick'");
        this.view2131493358 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mineFragment.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MineFragment mineFragment = this.target;
        if (mineFragment != null) {
            this.target = null;
            mineFragment.userItemIcon = null;
            mineFragment.fragmentMeRefresh = null;
            mineFragment.fragmentMeName = null;
            mineFragment.fragmentMePointsText = null;
            mineFragment.fragmentMePointsSubtext = null;
            mineFragment.fragmentMeMedalText = null;
            mineFragment.fragmentMeMedalSubtext = null;
            mineFragment.fragmentMeScroll = null;
            mineFragment.fragmentMeMoment = null;
            mineFragment.fragmentMePointsLayout = null;
            mineFragment.fragmentMeMedalLayout = null;
            mineFragment.fragmentMeMission = null;
            mineFragment.fragmentMeCheckIn = null;
            mineFragment.fragmentMeNotify = null;
            mineFragment.fragmentMeFavor = null;
            mineFragment.fragmentMeThreads = null;
            mineFragment.fragmentMeReply = null;
            mineFragment.mFragmentMeRoot = null;
            this.view2131493368.setOnClickListener((View.OnClickListener) null);
            this.view2131493368 = null;
            this.view2131493361.setOnClickListener((View.OnClickListener) null);
            this.view2131493361 = null;
            this.view2131493364.setOnClickListener((View.OnClickListener) null);
            this.view2131493364 = null;
            this.view2131493359.setOnClickListener((View.OnClickListener) null);
            this.view2131493359 = null;
            this.view2131493367.setOnClickListener((View.OnClickListener) null);
            this.view2131493367 = null;
            this.view2131493360.setOnClickListener((View.OnClickListener) null);
            this.view2131493360 = null;
            this.view2131493375.setOnClickListener((View.OnClickListener) null);
            this.view2131493375 = null;
            this.view2131493372.setOnClickListener((View.OnClickListener) null);
            this.view2131493372 = null;
            this.view2131493358.setOnClickListener((View.OnClickListener) null);
            this.view2131493358 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
