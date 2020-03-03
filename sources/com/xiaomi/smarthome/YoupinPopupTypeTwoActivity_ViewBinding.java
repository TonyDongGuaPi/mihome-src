package com.xiaomi.smarthome;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

public class YoupinPopupTypeTwoActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private YoupinPopupTypeTwoActivity f13541a;

    @UiThread
    public YoupinPopupTypeTwoActivity_ViewBinding(YoupinPopupTypeTwoActivity youpinPopupTypeTwoActivity) {
        this(youpinPopupTypeTwoActivity, youpinPopupTypeTwoActivity.getWindow().getDecorView());
    }

    @UiThread
    public YoupinPopupTypeTwoActivity_ViewBinding(YoupinPopupTypeTwoActivity youpinPopupTypeTwoActivity, View view) {
        this.f13541a = youpinPopupTypeTwoActivity;
        youpinPopupTypeTwoActivity.mRootView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.root_view, "field 'mRootView'", RelativeLayout.class);
        youpinPopupTypeTwoActivity.mYoupinSdv = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.youpin_sdv, "field 'mYoupinSdv'", SimpleDraweeView.class);
    }

    @CallSuper
    public void unbind() {
        YoupinPopupTypeTwoActivity youpinPopupTypeTwoActivity = this.f13541a;
        if (youpinPopupTypeTwoActivity != null) {
            this.f13541a = null;
            youpinPopupTypeTwoActivity.mRootView = null;
            youpinPopupTypeTwoActivity.mYoupinSdv = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
