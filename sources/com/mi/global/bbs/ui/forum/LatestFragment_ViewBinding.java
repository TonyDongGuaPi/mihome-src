package com.mi.global.bbs.ui.forum;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class LatestFragment_ViewBinding implements Unbinder {
    private LatestFragment target;

    @UiThread
    public LatestFragment_ViewBinding(LatestFragment latestFragment, View view) {
        this.target = latestFragment;
        latestFragment.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        latestFragment.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        latestFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        LatestFragment latestFragment = this.target;
        if (latestFragment != null) {
            this.target = null;
            latestFragment.commonRecycleView = null;
            latestFragment.commonRefreshView = null;
            latestFragment.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
