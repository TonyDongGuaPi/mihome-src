package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity target;

    @UiThread
    public MainActivity_ViewBinding(MainActivity mainActivity) {
        this(mainActivity, mainActivity.getWindow().getDecorView());
    }

    @UiThread
    public MainActivity_ViewBinding(MainActivity mainActivity, View view) {
        this.target = mainActivity;
        mainActivity.container = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.content_container, "field 'container'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        MainActivity mainActivity = this.target;
        if (mainActivity != null) {
            this.target = null;
            mainActivity.container = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
