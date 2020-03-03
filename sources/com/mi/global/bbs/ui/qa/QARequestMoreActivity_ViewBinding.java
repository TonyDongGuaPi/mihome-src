package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class QARequestMoreActivity_ViewBinding implements Unbinder {
    private QARequestMoreActivity target;

    @UiThread
    public QARequestMoreActivity_ViewBinding(QARequestMoreActivity qARequestMoreActivity) {
        this(qARequestMoreActivity, qARequestMoreActivity.getWindow().getDecorView());
    }

    @UiThread
    public QARequestMoreActivity_ViewBinding(QARequestMoreActivity qARequestMoreActivity, View view) {
        this.target = qARequestMoreActivity;
        qARequestMoreActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        qARequestMoreActivity.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        QARequestMoreActivity qARequestMoreActivity = this.target;
        if (qARequestMoreActivity != null) {
            this.target = null;
            qARequestMoreActivity.mRefreshView = null;
            qARequestMoreActivity.mRecycleView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
