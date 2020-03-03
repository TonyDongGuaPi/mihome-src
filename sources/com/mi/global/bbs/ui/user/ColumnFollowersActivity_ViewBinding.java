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

public class ColumnFollowersActivity_ViewBinding implements Unbinder {
    private ColumnFollowersActivity target;

    @UiThread
    public ColumnFollowersActivity_ViewBinding(ColumnFollowersActivity columnFollowersActivity) {
        this(columnFollowersActivity, columnFollowersActivity.getWindow().getDecorView());
    }

    @UiThread
    public ColumnFollowersActivity_ViewBinding(ColumnFollowersActivity columnFollowersActivity, View view) {
        this.target = columnFollowersActivity;
        columnFollowersActivity.mActivityFollowersProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_followers_progress, "field 'mActivityFollowersProgress'", ProgressBar.class);
        columnFollowersActivity.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        columnFollowersActivity.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        ColumnFollowersActivity columnFollowersActivity = this.target;
        if (columnFollowersActivity != null) {
            this.target = null;
            columnFollowersActivity.mActivityFollowersProgress = null;
            columnFollowersActivity.mCommonRecycleView = null;
            columnFollowersActivity.mCommonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
