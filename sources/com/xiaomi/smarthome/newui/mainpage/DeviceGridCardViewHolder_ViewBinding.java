package com.xiaomi.smarthome.newui.mainpage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class DeviceGridCardViewHolder_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceGridCardViewHolder f20670a;

    @UiThread
    public DeviceGridCardViewHolder_ViewBinding(DeviceGridCardViewHolder deviceGridCardViewHolder, View view) {
        this.f20670a = deviceGridCardViewHolder;
        deviceGridCardViewHolder.roomNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_room_name, "field 'roomNameTv'", TextView.class);
        deviceGridCardViewHolder.deviceNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_name, "field 'deviceNameTv'", TextView.class);
        deviceGridCardViewHolder.iv = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'iv'", SimpleDraweeView.class);
        deviceGridCardViewHolder.switchIvContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.shortcut_container, "field 'switchIvContainer'", FrameLayout.class);
        deviceGridCardViewHolder.switchIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.shortcut, "field 'switchIv'", ImageView.class);
        deviceGridCardViewHolder.descSplit = Utils.findRequiredView(view, R.id.tv_device_state_split, "field 'descSplit'");
        deviceGridCardViewHolder.descDeviceState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_state, "field 'descDeviceState'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DeviceGridCardViewHolder deviceGridCardViewHolder = this.f20670a;
        if (deviceGridCardViewHolder != null) {
            this.f20670a = null;
            deviceGridCardViewHolder.roomNameTv = null;
            deviceGridCardViewHolder.deviceNameTv = null;
            deviceGridCardViewHolder.iv = null;
            deviceGridCardViewHolder.switchIvContainer = null;
            deviceGridCardViewHolder.switchIv = null;
            deviceGridCardViewHolder.descSplit = null;
            deviceGridCardViewHolder.descDeviceState = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
