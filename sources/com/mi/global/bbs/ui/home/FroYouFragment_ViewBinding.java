package com.mi.global.bbs.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class FroYouFragment_ViewBinding implements Unbinder {
    private FroYouFragment target;

    @UiThread
    public FroYouFragment_ViewBinding(FroYouFragment froYouFragment, View view) {
        this.target = froYouFragment;
        froYouFragment.mHomeRecycle = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.sticky_recycle, "field 'mHomeRecycle'", ObservableRecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        FroYouFragment froYouFragment = this.target;
        if (froYouFragment != null) {
            this.target = null;
            froYouFragment.mHomeRecycle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
