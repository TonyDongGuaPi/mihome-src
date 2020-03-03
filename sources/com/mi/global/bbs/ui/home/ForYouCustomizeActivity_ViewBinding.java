package com.mi.global.bbs.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class ForYouCustomizeActivity_ViewBinding implements Unbinder {
    private ForYouCustomizeActivity target;

    @UiThread
    public ForYouCustomizeActivity_ViewBinding(ForYouCustomizeActivity forYouCustomizeActivity) {
        this(forYouCustomizeActivity, forYouCustomizeActivity.getWindow().getDecorView());
    }

    @UiThread
    public ForYouCustomizeActivity_ViewBinding(ForYouCustomizeActivity forYouCustomizeActivity, View view) {
        this.target = forYouCustomizeActivity;
        forYouCustomizeActivity.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        forYouCustomizeActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        forYouCustomizeActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_my_favor_progress, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        ForYouCustomizeActivity forYouCustomizeActivity = this.target;
        if (forYouCustomizeActivity != null) {
            this.target = null;
            forYouCustomizeActivity.mRecycleView = null;
            forYouCustomizeActivity.mRefreshView = null;
            forYouCustomizeActivity.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
