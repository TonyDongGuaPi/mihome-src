package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.TouchImageView;

public class WatchImageFragment_ViewBinding implements Unbinder {
    private WatchImageFragment target;

    @UiThread
    public WatchImageFragment_ViewBinding(WatchImageFragment watchImageFragment, View view) {
        this.target = watchImageFragment;
        watchImageFragment.relPhotoView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_photo_view, "field 'relPhotoView'", RelativeLayout.class);
        watchImageFragment.mPhotoView = (TouchImageView) Utils.findRequiredViewAsType(view, R.id.photo_view, "field 'mPhotoView'", TouchImageView.class);
    }

    @CallSuper
    public void unbind() {
        WatchImageFragment watchImageFragment = this.target;
        if (watchImageFragment != null) {
            this.target = null;
            watchImageFragment.relPhotoView = null;
            watchImageFragment.mPhotoView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
