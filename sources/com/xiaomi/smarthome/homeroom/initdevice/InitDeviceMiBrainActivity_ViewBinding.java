package com.xiaomi.smarthome.homeroom.initdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class InitDeviceMiBrainActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InitDeviceMiBrainActivity f18270a;

    @UiThread
    public InitDeviceMiBrainActivity_ViewBinding(InitDeviceMiBrainActivity initDeviceMiBrainActivity) {
        this(initDeviceMiBrainActivity, initDeviceMiBrainActivity.getWindow().getDecorView());
    }

    @UiThread
    public InitDeviceMiBrainActivity_ViewBinding(InitDeviceMiBrainActivity initDeviceMiBrainActivity, View view) {
        this.f18270a = initDeviceMiBrainActivity;
        initDeviceMiBrainActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        initDeviceMiBrainActivity.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        initDeviceMiBrainActivity.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTv'", TextView.class);
        initDeviceMiBrainActivity.mDeviceNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.device_name_tv, "field 'mDeviceNameTv'", TextView.class);
        initDeviceMiBrainActivity.mDeviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_img, "field 'mDeviceImg'", SimpleDraweeView.class);
        initDeviceMiBrainActivity.mSkipTv = Utils.findRequiredView(view, R.id.skip, "field 'mSkipTv'");
        initDeviceMiBrainActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ctrl_xiaoai, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        InitDeviceMiBrainActivity initDeviceMiBrainActivity = this.f18270a;
        if (initDeviceMiBrainActivity != null) {
            this.f18270a = null;
            initDeviceMiBrainActivity.mTitleBar = null;
            initDeviceMiBrainActivity.mReturnBtn = null;
            initDeviceMiBrainActivity.mTitleTv = null;
            initDeviceMiBrainActivity.mDeviceNameTv = null;
            initDeviceMiBrainActivity.mDeviceImg = null;
            initDeviceMiBrainActivity.mSkipTv = null;
            initDeviceMiBrainActivity.mRecyclerView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
