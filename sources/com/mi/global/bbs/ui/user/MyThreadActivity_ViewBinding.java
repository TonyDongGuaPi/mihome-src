package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class MyThreadActivity_ViewBinding implements Unbinder {
    private MyThreadActivity target;

    @UiThread
    public MyThreadActivity_ViewBinding(MyThreadActivity myThreadActivity) {
        this(myThreadActivity, myThreadActivity.getWindow().getDecorView());
    }

    @UiThread
    public MyThreadActivity_ViewBinding(MyThreadActivity myThreadActivity, View view) {
        this.target = myThreadActivity;
        myThreadActivity.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        myThreadActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        myThreadActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_my_favor_progress, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        MyThreadActivity myThreadActivity = this.target;
        if (myThreadActivity != null) {
            this.target = null;
            myThreadActivity.mRecycleView = null;
            myThreadActivity.mRefreshView = null;
            myThreadActivity.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
