package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class ColumnAllActivity_ViewBinding implements Unbinder {
    private ColumnAllActivity target;

    @UiThread
    public ColumnAllActivity_ViewBinding(ColumnAllActivity columnAllActivity) {
        this(columnAllActivity, columnAllActivity.getWindow().getDecorView());
    }

    @UiThread
    public ColumnAllActivity_ViewBinding(ColumnAllActivity columnAllActivity, View view) {
        this.target = columnAllActivity;
        columnAllActivity.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        columnAllActivity.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        ColumnAllActivity columnAllActivity = this.target;
        if (columnAllActivity != null) {
            this.target = null;
            columnAllActivity.commonRecycleView = null;
            columnAllActivity.commonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
