package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class ColumnDetailActivity_ViewBinding implements Unbinder {
    private ColumnDetailActivity target;

    @UiThread
    public ColumnDetailActivity_ViewBinding(ColumnDetailActivity columnDetailActivity) {
        this(columnDetailActivity, columnDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public ColumnDetailActivity_ViewBinding(ColumnDetailActivity columnDetailActivity, View view) {
        this.target = columnDetailActivity;
        columnDetailActivity.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        columnDetailActivity.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        ColumnDetailActivity columnDetailActivity = this.target;
        if (columnDetailActivity != null) {
            this.target = null;
            columnDetailActivity.commonRecycleView = null;
            columnDetailActivity.commonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
