package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class QANewFragment_ViewBinding implements Unbinder {
    private QANewFragment target;

    @UiThread
    public QANewFragment_ViewBinding(QANewFragment qANewFragment, View view) {
        this.target = qANewFragment;
        qANewFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        qANewFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        QANewFragment qANewFragment = this.target;
        if (qANewFragment != null) {
            this.target = null;
            qANewFragment.mRefreshView = null;
            qANewFragment.mRecycleView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
