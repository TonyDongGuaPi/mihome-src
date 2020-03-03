package com.xiaomi.smarthome.newui.adapter.main_grid;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.widget.GridItemAnimView;

public class BaseDeviceGridCardViewHolder_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private BaseDeviceGridCardViewHolder f20419a;

    @UiThread
    public BaseDeviceGridCardViewHolder_ViewBinding(BaseDeviceGridCardViewHolder baseDeviceGridCardViewHolder, View view) {
        this.f20419a = baseDeviceGridCardViewHolder;
        baseDeviceGridCardViewHolder.roomNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_room_name, "field 'roomNameTv'", TextView.class);
        baseDeviceGridCardViewHolder.deviceNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_name, "field 'deviceNameTv'", TextView.class);
        baseDeviceGridCardViewHolder.iv = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'iv'", SimpleDraweeView.class);
        baseDeviceGridCardViewHolder.switchIvContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.shortcut_container, "field 'switchIvContainer'", FrameLayout.class);
        baseDeviceGridCardViewHolder.switchIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.shortcut, "field 'switchIv'", ImageView.class);
        baseDeviceGridCardViewHolder.checkBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'checkBox'", CheckBox.class);
        baseDeviceGridCardViewHolder.isNew = (ImageView) Utils.findRequiredViewAsType(view, R.id.isnew, "field 'isNew'", ImageView.class);
        baseDeviceGridCardViewHolder.offlineTipIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.offline_tip_iv, "field 'offlineTipIv'", ImageView.class);
        baseDeviceGridCardViewHolder.descSplit = Utils.findRequiredView(view, R.id.tv_device_state_split, "field 'descSplit'");
        baseDeviceGridCardViewHolder.descDeviceState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_state, "field 'descDeviceState'", TextView.class);
        baseDeviceGridCardViewHolder.gridItemAnimView = (GridItemAnimView) Utils.findRequiredViewAsType(view, R.id.grid_anim_view, "field 'gridItemAnimView'", GridItemAnimView.class);
    }

    @CallSuper
    public void unbind() {
        BaseDeviceGridCardViewHolder baseDeviceGridCardViewHolder = this.f20419a;
        if (baseDeviceGridCardViewHolder != null) {
            this.f20419a = null;
            baseDeviceGridCardViewHolder.roomNameTv = null;
            baseDeviceGridCardViewHolder.deviceNameTv = null;
            baseDeviceGridCardViewHolder.iv = null;
            baseDeviceGridCardViewHolder.switchIvContainer = null;
            baseDeviceGridCardViewHolder.switchIv = null;
            baseDeviceGridCardViewHolder.checkBox = null;
            baseDeviceGridCardViewHolder.isNew = null;
            baseDeviceGridCardViewHolder.offlineTipIv = null;
            baseDeviceGridCardViewHolder.descSplit = null;
            baseDeviceGridCardViewHolder.descDeviceState = null;
            baseDeviceGridCardViewHolder.gridItemAnimView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
