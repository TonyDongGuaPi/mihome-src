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

public class UserActivitiesFragment_ViewBinding implements Unbinder {
    private UserActivitiesFragment target;

    @UiThread
    public UserActivitiesFragment_ViewBinding(UserActivitiesFragment userActivitiesFragment, View view) {
        this.target = userActivitiesFragment;
        userActivitiesFragment.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        userActivitiesFragment.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        userActivitiesFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        UserActivitiesFragment userActivitiesFragment = this.target;
        if (userActivitiesFragment != null) {
            this.target = null;
            userActivitiesFragment.commonRecycleView = null;
            userActivitiesFragment.commonRefreshView = null;
            userActivitiesFragment.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
