package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class UserCenterActivity_ViewBinding implements Unbinder {
    private UserCenterActivity target;
    private View view2131494188;
    private View view2131494189;
    private View view2131494191;
    private View view2131494192;
    private View view2131494194;
    private View view2131494196;
    private View view2131494199;
    private View view2131494202;
    private View view2131494205;
    private View view2131494207;
    private View view2131494218;

    @UiThread
    public UserCenterActivity_ViewBinding(UserCenterActivity userCenterActivity) {
        this(userCenterActivity, userCenterActivity.getWindow().getDecorView());
    }

    @UiThread
    public UserCenterActivity_ViewBinding(final UserCenterActivity userCenterActivity, View view) {
        this.target = userCenterActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.user_center_top_bg, "field 'userCenterTopBg' and method 'onClick'");
        userCenterActivity.userCenterTopBg = (ImageView) Utils.castView(findRequiredView, R.id.user_center_top_bg, "field 'userCenterTopBg'", ImageView.class);
        this.view2131494207 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.user_item_icon, "field 'userItemIcon' and method 'onClick'");
        userCenterActivity.userItemIcon = (HeadLogoView) Utils.castView(findRequiredView2, R.id.user_item_icon, "field 'userItemIcon'", HeadLogoView.class);
        this.view2131494218 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.user_center_name_text, "field 'userCenterNameText' and method 'onClick'");
        userCenterActivity.userCenterNameText = (TextView) Utils.castView(findRequiredView3, R.id.user_center_name_text, "field 'userCenterNameText'", TextView.class);
        this.view2131494199 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.user_center_group_text, "field 'userCenterGroupText' and method 'onClick'");
        userCenterActivity.userCenterGroupText = (TextView) Utils.castView(findRequiredView4, R.id.user_center_group_text, "field 'userCenterGroupText'", TextView.class);
        this.view2131494196 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        userCenterActivity.userCenterMedalContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.user_center_medal_container, "field 'userCenterMedalContainer'", LinearLayout.class);
        userCenterActivity.userCenterFollowingTx = (TextView) Utils.findRequiredViewAsType(view, R.id.user_center_following_tx, "field 'userCenterFollowingTx'", TextView.class);
        userCenterActivity.userCenterFollowerTx = (TextView) Utils.findRequiredViewAsType(view, R.id.user_center_follower_tx, "field 'userCenterFollowerTx'", TextView.class);
        userCenterActivity.userCenterThreadsTx = (TextView) Utils.findRequiredViewAsType(view, R.id.user_center_threads_tx, "field 'userCenterThreadsTx'", TextView.class);
        userCenterActivity.userCenterRepliesTx = (TextView) Utils.findRequiredViewAsType(view, R.id.user_center_replies_tx, "field 'userCenterRepliesTx'", TextView.class);
        userCenterActivity.userCenterNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.user_center_nag, "field 'userCenterNagTab'", PagerSlidingTabStrip.class);
        userCenterActivity.userCenterPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.user_center_pager, "field 'userCenterPager'", ViewPager.class);
        userCenterActivity.userCenterNavigationContainer = (AbsorbNavigationLayout) Utils.findRequiredViewAsType(view, R.id.user_center_navigation_container, "field 'userCenterNavigationContainer'", AbsorbNavigationLayout.class);
        userCenterActivity.userCenterBottomFollowBt = (TextView) Utils.findRequiredViewAsType(view, R.id.user_center_bottom_follow_bt, "field 'userCenterBottomFollowBt'", TextView.class);
        userCenterActivity.userCenterBottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.user_center_bottom_layout, "field 'userCenterBottomLayout'", LinearLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.user_center_follow_tab, "field 'userCenterFollowTab' and method 'onClick'");
        userCenterActivity.userCenterFollowTab = (FrameLayout) Utils.castView(findRequiredView5, R.id.user_center_follow_tab, "field 'userCenterFollowTab'", FrameLayout.class);
        this.view2131494191 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        userCenterActivity.userCenterTopLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.user_center_top_layout, "field 'userCenterTopLayout'", LinearLayout.class);
        userCenterActivity.userCenterTopBgFrame = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.user_center_top_bg_frame, "field 'userCenterTopBgFrame'", FrameLayout.class);
        View findRequiredView6 = Utils.findRequiredView(view, R.id.user_center_column_bt, "field 'mUserCenterColumnBt' and method 'onClick'");
        userCenterActivity.mUserCenterColumnBt = (TextView) Utils.castView(findRequiredView6, R.id.user_center_column_bt, "field 'mUserCenterColumnBt'", TextView.class);
        this.view2131494189 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.user_center_following, "method 'onClick'");
        this.view2131494194 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.user_center_follower, "method 'onClick'");
        this.view2131494192 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.user_center_threads, "method 'onClick'");
        this.view2131494205 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.user_center_replies, "method 'onClick'");
        this.view2131494202 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.user_center_chat_tab, "method 'onClick'");
        this.view2131494188 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userCenterActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        UserCenterActivity userCenterActivity = this.target;
        if (userCenterActivity != null) {
            this.target = null;
            userCenterActivity.userCenterTopBg = null;
            userCenterActivity.userItemIcon = null;
            userCenterActivity.userCenterNameText = null;
            userCenterActivity.userCenterGroupText = null;
            userCenterActivity.userCenterMedalContainer = null;
            userCenterActivity.userCenterFollowingTx = null;
            userCenterActivity.userCenterFollowerTx = null;
            userCenterActivity.userCenterThreadsTx = null;
            userCenterActivity.userCenterRepliesTx = null;
            userCenterActivity.userCenterNagTab = null;
            userCenterActivity.userCenterPager = null;
            userCenterActivity.userCenterNavigationContainer = null;
            userCenterActivity.userCenterBottomFollowBt = null;
            userCenterActivity.userCenterBottomLayout = null;
            userCenterActivity.userCenterFollowTab = null;
            userCenterActivity.userCenterTopLayout = null;
            userCenterActivity.userCenterTopBgFrame = null;
            userCenterActivity.mUserCenterColumnBt = null;
            this.view2131494207.setOnClickListener((View.OnClickListener) null);
            this.view2131494207 = null;
            this.view2131494218.setOnClickListener((View.OnClickListener) null);
            this.view2131494218 = null;
            this.view2131494199.setOnClickListener((View.OnClickListener) null);
            this.view2131494199 = null;
            this.view2131494196.setOnClickListener((View.OnClickListener) null);
            this.view2131494196 = null;
            this.view2131494191.setOnClickListener((View.OnClickListener) null);
            this.view2131494191 = null;
            this.view2131494189.setOnClickListener((View.OnClickListener) null);
            this.view2131494189 = null;
            this.view2131494194.setOnClickListener((View.OnClickListener) null);
            this.view2131494194 = null;
            this.view2131494192.setOnClickListener((View.OnClickListener) null);
            this.view2131494192 = null;
            this.view2131494205.setOnClickListener((View.OnClickListener) null);
            this.view2131494205 = null;
            this.view2131494202.setOnClickListener((View.OnClickListener) null);
            this.view2131494202 = null;
            this.view2131494188.setOnClickListener((View.OnClickListener) null);
            this.view2131494188 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
