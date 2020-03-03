package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class HotTopicFragment_ViewBinding implements Unbinder {
    private HotTopicFragment target;

    @UiThread
    public HotTopicFragment_ViewBinding(HotTopicFragment hotTopicFragment, View view) {
        this.target = hotTopicFragment;
        hotTopicFragment.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        hotTopicFragment.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        hotTopicFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        HotTopicFragment hotTopicFragment = this.target;
        if (hotTopicFragment != null) {
            this.target = null;
            hotTopicFragment.commonRecycleView = null;
            hotTopicFragment.commonRefreshView = null;
            hotTopicFragment.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
