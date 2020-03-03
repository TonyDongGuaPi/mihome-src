package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class MySubcriptionFragment_ViewBinding implements Unbinder {
    private MySubcriptionFragment target;

    @UiThread
    public MySubcriptionFragment_ViewBinding(MySubcriptionFragment mySubcriptionFragment, View view) {
        this.target = mySubcriptionFragment;
        mySubcriptionFragment.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        mySubcriptionFragment.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        mySubcriptionFragment.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.common_progress_view, "field 'mProgress'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        MySubcriptionFragment mySubcriptionFragment = this.target;
        if (mySubcriptionFragment != null) {
            this.target = null;
            mySubcriptionFragment.commonRecycleView = null;
            mySubcriptionFragment.commonRefreshView = null;
            mySubcriptionFragment.mProgress = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
