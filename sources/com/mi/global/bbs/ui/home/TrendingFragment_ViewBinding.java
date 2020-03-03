package com.mi.global.bbs.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class TrendingFragment_ViewBinding implements Unbinder {
    private TrendingFragment target;

    @UiThread
    public TrendingFragment_ViewBinding(TrendingFragment trendingFragment, View view) {
        this.target = trendingFragment;
        trendingFragment.mHomeRecycle = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.sticky_recycle, "field 'mHomeRecycle'", ObservableRecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        TrendingFragment trendingFragment = this.target;
        if (trendingFragment != null) {
            this.target = null;
            trendingFragment.mHomeRecycle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
