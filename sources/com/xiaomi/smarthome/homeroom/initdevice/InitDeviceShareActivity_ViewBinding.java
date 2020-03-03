package com.xiaomi.smarthome.homeroom.initdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class InitDeviceShareActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InitDeviceShareActivity f18302a;

    @UiThread
    public InitDeviceShareActivity_ViewBinding(InitDeviceShareActivity initDeviceShareActivity) {
        this(initDeviceShareActivity, initDeviceShareActivity.getWindow().getDecorView());
    }

    @UiThread
    public InitDeviceShareActivity_ViewBinding(InitDeviceShareActivity initDeviceShareActivity, View view) {
        this.f18302a = initDeviceShareActivity;
        initDeviceShareActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        initDeviceShareActivity.mFinishBtn = Utils.findRequiredView(view, R.id.module_a_3_left_text, "field 'mFinishBtn'");
        initDeviceShareActivity.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_title, "field 'mTitleTv'", TextView.class);
        initDeviceShareActivity.mDeviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_img, "field 'mDeviceImg'", SimpleDraweeView.class);
        initDeviceShareActivity.mGoNextBtn = (Button) Utils.findRequiredViewAsType(view, R.id.go_next, "field 'mGoNextBtn'", Button.class);
        initDeviceShareActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        initDeviceShareActivity.mShareTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.share_to, "field 'mShareTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InitDeviceShareActivity initDeviceShareActivity = this.f18302a;
        if (initDeviceShareActivity != null) {
            this.f18302a = null;
            initDeviceShareActivity.mTitleBar = null;
            initDeviceShareActivity.mFinishBtn = null;
            initDeviceShareActivity.mTitleTv = null;
            initDeviceShareActivity.mDeviceImg = null;
            initDeviceShareActivity.mGoNextBtn = null;
            initDeviceShareActivity.mRecyclerView = null;
            initDeviceShareActivity.mShareTitle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
