package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class FollowersActivity_ViewBinding implements Unbinder {
    private FollowersActivity target;

    @UiThread
    public FollowersActivity_ViewBinding(FollowersActivity followersActivity) {
        this(followersActivity, followersActivity.getWindow().getDecorView());
    }

    @UiThread
    public FollowersActivity_ViewBinding(FollowersActivity followersActivity, View view) {
        this.target = followersActivity;
        followersActivity.mActivityFollowersProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_followers_progress, "field 'mActivityFollowersProgress'", ProgressBar.class);
        followersActivity.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        followersActivity.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        FollowersActivity followersActivity = this.target;
        if (followersActivity != null) {
            this.target = null;
            followersActivity.mActivityFollowersProgress = null;
            followersActivity.mCommonRecycleView = null;
            followersActivity.mCommonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
