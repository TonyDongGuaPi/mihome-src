package com.xiaomi.smarthome.device.renderer;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class ViewHolder_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ViewHolder f15414a;

    @UiThread
    public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
        this.f15414a = viewHolder;
        viewHolder.ckbEdit = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'ckbEdit'", CheckBox.class);
        viewHolder.avatar = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'avatar'", SimpleDraweeView.class);
        viewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", TextView.class);
        viewHolder.nameStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.name_status, "field 'nameStatus'", TextView.class);
        viewHolder.addBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.add_sub_device, "field 'addBtn'", TextView.class);
        viewHolder.progress_bar = Utils.findRequiredView(view, R.id.device_progress_bar, "field 'progress_bar'");
        viewHolder.mStatusImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.device_status_img, "field 'mStatusImage'", ImageView.class);
        viewHolder.mSwitchBtn = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.device_switch, "field 'mSwitchBtn'", SwitchButton.class);
        viewHolder.mSwitchBtnLayout = Utils.findRequiredView(view, R.id.device_switch_layout, "field 'mSwitchBtnLayout'");
        viewHolder.mLLLoading = (ImageView) Utils.findRequiredViewAsType(view, R.id.loading, "field 'mLLLoading'", ImageView.class);
        viewHolder.mLLLoadingContainer = Utils.findRequiredView(view, R.id.loading_container, "field 'mLLLoadingContainer'");
    }

    @CallSuper
    public void unbind() {
        ViewHolder viewHolder = this.f15414a;
        if (viewHolder != null) {
            this.f15414a = null;
            viewHolder.ckbEdit = null;
            viewHolder.avatar = null;
            viewHolder.name = null;
            viewHolder.nameStatus = null;
            viewHolder.addBtn = null;
            viewHolder.progress_bar = null;
            viewHolder.mStatusImage = null;
            viewHolder.mSwitchBtn = null;
            viewHolder.mSwitchBtnLayout = null;
            viewHolder.mLLLoading = null;
            viewHolder.mLLLoadingContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
