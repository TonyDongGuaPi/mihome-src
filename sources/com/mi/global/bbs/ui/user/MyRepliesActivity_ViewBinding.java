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

public class MyRepliesActivity_ViewBinding implements Unbinder {
    private MyRepliesActivity target;

    @UiThread
    public MyRepliesActivity_ViewBinding(MyRepliesActivity myRepliesActivity) {
        this(myRepliesActivity, myRepliesActivity.getWindow().getDecorView());
    }

    @UiThread
    public MyRepliesActivity_ViewBinding(MyRepliesActivity myRepliesActivity, View view) {
        this.target = myRepliesActivity;
        myRepliesActivity.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        myRepliesActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        myRepliesActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_my_favor_progress, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        MyRepliesActivity myRepliesActivity = this.target;
        if (myRepliesActivity != null) {
            this.target = null;
            myRepliesActivity.mRecycleView = null;
            myRepliesActivity.mRefreshView = null;
            myRepliesActivity.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
