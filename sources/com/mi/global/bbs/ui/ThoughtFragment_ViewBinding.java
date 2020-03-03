package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class ThoughtFragment_ViewBinding implements Unbinder {
    private ThoughtFragment target;

    @UiThread
    public ThoughtFragment_ViewBinding(ThoughtFragment thoughtFragment, View view) {
        this.target = thoughtFragment;
        thoughtFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.fragment_following_progress, "field 'mProgress'", ProgressBar.class);
        thoughtFragment.frameRecycleView = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fragment_following_content, "field 'frameRecycleView'", FrameLayout.class);
        thoughtFragment.mRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", ObservableRecyclerView.class);
        thoughtFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        thoughtFragment.followingTopIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_icon, "field 'followingTopIcon'", ImageView.class);
        thoughtFragment.followingTopPost = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_post, "field 'followingTopPost'", ImageView.class);
        thoughtFragment.followingTopPostLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.post_layout, "field 'followingTopPostLayout'", RelativeLayout.class);
    }

    @CallSuper
    public void unbind() {
        ThoughtFragment thoughtFragment = this.target;
        if (thoughtFragment != null) {
            this.target = null;
            thoughtFragment.mProgress = null;
            thoughtFragment.frameRecycleView = null;
            thoughtFragment.mRecycleView = null;
            thoughtFragment.mRefreshView = null;
            thoughtFragment.followingTopIcon = null;
            thoughtFragment.followingTopPost = null;
            thoughtFragment.followingTopPostLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
