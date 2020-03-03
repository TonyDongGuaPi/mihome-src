package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class MIUIActivity_ViewBinding implements Unbinder {
    private MIUIActivity target;

    @UiThread
    public MIUIActivity_ViewBinding(MIUIActivity mIUIActivity) {
        this(mIUIActivity, mIUIActivity.getWindow().getDecorView());
    }

    @UiThread
    public MIUIActivity_ViewBinding(MIUIActivity mIUIActivity, View view) {
        this.target = mIUIActivity;
        mIUIActivity.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        mIUIActivity.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        MIUIActivity mIUIActivity = this.target;
        if (mIUIActivity != null) {
            this.target = null;
            mIUIActivity.mCommonRecycleView = null;
            mIUIActivity.mCommonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
