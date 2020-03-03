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

public class HotFragment_ViewBinding implements Unbinder {
    private HotFragment target;

    @UiThread
    public HotFragment_ViewBinding(HotFragment hotFragment, View view) {
        this.target = hotFragment;
        hotFragment.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        hotFragment.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        hotFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        HotFragment hotFragment = this.target;
        if (hotFragment != null) {
            this.target = null;
            hotFragment.commonRecycleView = null;
            hotFragment.commonRefreshView = null;
            hotFragment.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
