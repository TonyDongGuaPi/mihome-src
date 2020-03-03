package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class WatchBigPicActivity_ViewBinding implements Unbinder {
    private WatchBigPicActivity target;

    @UiThread
    public WatchBigPicActivity_ViewBinding(WatchBigPicActivity watchBigPicActivity) {
        this(watchBigPicActivity, watchBigPicActivity.getWindow().getDecorView());
    }

    @UiThread
    public WatchBigPicActivity_ViewBinding(WatchBigPicActivity watchBigPicActivity, View view) {
        this.target = watchBigPicActivity;
        watchBigPicActivity.mViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.mViewPager, "field 'mViewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        WatchBigPicActivity watchBigPicActivity = this.target;
        if (watchBigPicActivity != null) {
            this.target = null;
            watchBigPicActivity.mViewPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
