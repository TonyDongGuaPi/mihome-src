package com.mi.global.bbs.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class OnlineFragment_ViewBinding implements Unbinder {
    private OnlineFragment target;

    @UiThread
    public OnlineFragment_ViewBinding(OnlineFragment onlineFragment, View view) {
        this.target = onlineFragment;
        onlineFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        onlineFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        onlineFragment.emptyLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.frame_online_empty_layout, "field 'emptyLayout'", RelativeLayout.class);
        onlineFragment.progressView = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress, "field 'progressView'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        OnlineFragment onlineFragment = this.target;
        if (onlineFragment != null) {
            this.target = null;
            onlineFragment.mRefreshView = null;
            onlineFragment.mRecycleView = null;
            onlineFragment.emptyLayout = null;
            onlineFragment.progressView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
