package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class MyFavorActivity_ViewBinding implements Unbinder {
    private MyFavorActivity target;

    @UiThread
    public MyFavorActivity_ViewBinding(MyFavorActivity myFavorActivity) {
        this(myFavorActivity, myFavorActivity.getWindow().getDecorView());
    }

    @UiThread
    public MyFavorActivity_ViewBinding(MyFavorActivity myFavorActivity, View view) {
        this.target = myFavorActivity;
        myFavorActivity.mRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", ObservableRecyclerView.class);
        myFavorActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        myFavorActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_my_favor_progress, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        MyFavorActivity myFavorActivity = this.target;
        if (myFavorActivity != null) {
            this.target = null;
            myFavorActivity.mRecycleView = null;
            myFavorActivity.mRefreshView = null;
            myFavorActivity.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
