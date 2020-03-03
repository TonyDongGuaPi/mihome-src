package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;

public class FollowingFragment_ViewBinding implements Unbinder {
    private FollowingFragment target;
    private View view2131493267;
    private View view2131493735;
    private View view2131494130;

    @UiThread
    public FollowingFragment_ViewBinding(final FollowingFragment followingFragment, View view) {
        this.target = followingFragment;
        followingFragment.mFollowNotifyContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.follow_notify_container, "field 'mFollowNotifyContainer'", FrameLayout.class);
        followingFragment.mFollowNotifyText = (FontTextView) Utils.findRequiredViewAsType(view, R.id.follow_notify_text, "field 'mFollowNotifyText'", FontTextView.class);
        followingFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.fragment_following_progress, "field 'mProgress'", ProgressBar.class);
        followingFragment.frameNoLogin = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.frame_following_login, "field 'frameNoLogin'", LinearLayout.class);
        followingFragment.frameRecycleView = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fragment_following_content, "field 'frameRecycleView'", FrameLayout.class);
        followingFragment.mRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", ObservableRecyclerView.class);
        followingFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        followingFragment.toolbarAgent = Utils.findRequiredView(view, R.id.toolbar_agent, "field 'toolbarAgent'");
        followingFragment.rvFollowingDone = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_following_done, "field 'rvFollowingDone'", RelativeLayout.class);
        followingFragment.tvFollowingCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_following_count, "field 'tvFollowingCount'", TextView.class);
        followingFragment.rightButton = (ImageView) Utils.findRequiredViewAsType(view, R.id.new_follow_user, "field 'rightButton'", ImageView.class);
        followingFragment.leftButton = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_post_right, "field 'leftButton'", ImageView.class);
        followingFragment.followingTopIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_icon, "field 'followingTopIcon'", ImageView.class);
        followingFragment.followingTopPost = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_post, "field 'followingTopPost'", ImageView.class);
        followingFragment.followingTopPostLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.post_layout, "field 'followingTopPostLayout'", RelativeLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.follow_login_bt, "method 'onClick'");
        this.view2131493267 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                followingFragment.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.new_follow_user_layout, "method 'onClick'");
        this.view2131493735 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                followingFragment.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_following_done, "method 'onClick'");
        this.view2131494130 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                followingFragment.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        FollowingFragment followingFragment = this.target;
        if (followingFragment != null) {
            this.target = null;
            followingFragment.mFollowNotifyContainer = null;
            followingFragment.mFollowNotifyText = null;
            followingFragment.mProgress = null;
            followingFragment.frameNoLogin = null;
            followingFragment.frameRecycleView = null;
            followingFragment.mRecycleView = null;
            followingFragment.mRefreshView = null;
            followingFragment.toolbarAgent = null;
            followingFragment.rvFollowingDone = null;
            followingFragment.tvFollowingCount = null;
            followingFragment.rightButton = null;
            followingFragment.leftButton = null;
            followingFragment.followingTopIcon = null;
            followingFragment.followingTopPost = null;
            followingFragment.followingTopPostLayout = null;
            this.view2131493267.setOnClickListener((View.OnClickListener) null);
            this.view2131493267 = null;
            this.view2131493735.setOnClickListener((View.OnClickListener) null);
            this.view2131493735 = null;
            this.view2131494130.setOnClickListener((View.OnClickListener) null);
            this.view2131494130 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
