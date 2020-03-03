package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class ForumFragment_ViewBinding implements Unbinder {
    private ForumFragment target;

    @UiThread
    public ForumFragment_ViewBinding(ForumFragment forumFragment, View view) {
        this.target = forumFragment;
        forumFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.fragment_forum_progress, "field 'mProgress'", ProgressBar.class);
        forumFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        forumFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        forumFragment.mFragmentForumRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.fragment_forum_root, "field 'mFragmentForumRoot'", LinearLayout.class);
        forumFragment.columns = view.getContext().getResources().getInteger(R.integer.num_columns);
    }

    @CallSuper
    public void unbind() {
        ForumFragment forumFragment = this.target;
        if (forumFragment != null) {
            this.target = null;
            forumFragment.mProgress = null;
            forumFragment.mRecycleView = null;
            forumFragment.mRefreshView = null;
            forumFragment.mFragmentForumRoot = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
