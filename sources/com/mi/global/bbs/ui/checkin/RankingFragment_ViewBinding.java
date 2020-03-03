package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class RankingFragment_ViewBinding implements Unbinder {
    private RankingFragment target;

    @UiThread
    public RankingFragment_ViewBinding(RankingFragment rankingFragment, View view) {
        this.target = rankingFragment;
        rankingFragment.mCommonProgressView = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mCommonProgressView'", ProgressBar.class);
        rankingFragment.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        rankingFragment.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        RankingFragment rankingFragment = this.target;
        if (rankingFragment != null) {
            this.target = null;
            rankingFragment.mCommonProgressView = null;
            rankingFragment.mCommonRecycleView = null;
            rankingFragment.mCommonRefreshView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
