package com.mi.global.bbs.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class InCityFragment_ViewBinding implements Unbinder {
    private InCityFragment target;

    @UiThread
    public InCityFragment_ViewBinding(InCityFragment inCityFragment, View view) {
        this.target = inCityFragment;
        inCityFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        inCityFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
        inCityFragment.emptyLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.frame_incity_empty_layout, "field 'emptyLayout'", RelativeLayout.class);
    }

    @CallSuper
    public void unbind() {
        InCityFragment inCityFragment = this.target;
        if (inCityFragment != null) {
            this.target = null;
            inCityFragment.mRefreshView = null;
            inCityFragment.mRecycleView = null;
            inCityFragment.emptyLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
