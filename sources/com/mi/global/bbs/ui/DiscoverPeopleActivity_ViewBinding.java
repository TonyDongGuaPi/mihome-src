package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class DiscoverPeopleActivity_ViewBinding implements Unbinder {
    private DiscoverPeopleActivity target;

    @UiThread
    public DiscoverPeopleActivity_ViewBinding(DiscoverPeopleActivity discoverPeopleActivity) {
        this(discoverPeopleActivity, discoverPeopleActivity.getWindow().getDecorView());
    }

    @UiThread
    public DiscoverPeopleActivity_ViewBinding(DiscoverPeopleActivity discoverPeopleActivity, View view) {
        this.target = discoverPeopleActivity;
        discoverPeopleActivity.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        discoverPeopleActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        discoverPeopleActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_my_favor_progress, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        DiscoverPeopleActivity discoverPeopleActivity = this.target;
        if (discoverPeopleActivity != null) {
            this.target = null;
            discoverPeopleActivity.mRecycleView = null;
            discoverPeopleActivity.mRefreshView = null;
            discoverPeopleActivity.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
