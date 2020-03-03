package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class QATrendingFragment_ViewBinding implements Unbinder {
    private QATrendingFragment target;

    @UiThread
    public QATrendingFragment_ViewBinding(QATrendingFragment qATrendingFragment, View view) {
        this.target = qATrendingFragment;
        qATrendingFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        qATrendingFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        QATrendingFragment qATrendingFragment = this.target;
        if (qATrendingFragment != null) {
            this.target = null;
            qATrendingFragment.mRefreshView = null;
            qATrendingFragment.mRecycleView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
