package com.mi.global.bbs.ui.plate;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.SlidingTabStripView;

public class PlateActivity_ViewBinding implements Unbinder {
    private PlateActivity target;

    @UiThread
    public PlateActivity_ViewBinding(PlateActivity plateActivity) {
        this(plateActivity, plateActivity.getWindow().getDecorView());
    }

    @UiThread
    public PlateActivity_ViewBinding(PlateActivity plateActivity, View view) {
        this.target = plateActivity;
        plateActivity.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        plateActivity.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        plateActivity.forumItemTopStrip = (SlidingTabStripView) Utils.findRequiredViewAsType(view, R.id.forum_item_top_strip, "field 'forumItemTopStrip'", SlidingTabStripView.class);
    }

    @CallSuper
    public void unbind() {
        PlateActivity plateActivity = this.target;
        if (plateActivity != null) {
            this.target = null;
            plateActivity.commonRecycleView = null;
            plateActivity.commonRefreshView = null;
            plateActivity.forumItemTopStrip = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
