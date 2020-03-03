package com.xiaomi.smarthome.miio.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleGatewayNestedScrollingParent;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class BleGatewayActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private BleGatewayActivity f11681a;

    @UiThread
    public BleGatewayActivity_ViewBinding(BleGatewayActivity bleGatewayActivity) {
        this(bleGatewayActivity, bleGatewayActivity.getWindow().getDecorView());
    }

    @UiThread
    public BleGatewayActivity_ViewBinding(BleGatewayActivity bleGatewayActivity, View view) {
        this.f11681a = bleGatewayActivity;
        bleGatewayActivity.mBackBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackBt'", ImageView.class);
        bleGatewayActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        bleGatewayActivity.mHeadDescView = (TextView) Utils.findRequiredViewAsType(view, R.id.desc, "field 'mHeadDescView'", TextView.class);
        bleGatewayActivity.mHeadImageExtend = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.head_image_extend, "field 'mHeadImageExtend'", SimpleDraweeView.class);
        bleGatewayActivity.mHeadImageShrink = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.head_image_shrink, "field 'mHeadImageShrink'", SimpleDraweeView.class);
        bleGatewayActivity.mHeadAnimImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_anim_image, "field 'mHeadAnimImage'", ImageView.class);
        bleGatewayActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.list, "field 'mRecyclerView'", RecyclerView.class);
        bleGatewayActivity.mNestedScrollView = (BleGatewayNestedScrollingParent) Utils.findRequiredViewAsType(view, R.id.root, "field 'mNestedScrollView'", BleGatewayNestedScrollingParent.class);
        bleGatewayActivity.mEmptyViewContainer = Utils.findRequiredView(view, R.id.empty_view_container, "field 'mEmptyViewContainer'");
        bleGatewayActivity.mEmptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'mEmptyView'");
        bleGatewayActivity.mFirstEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'mFirstEmptyText'", TextView.class);
        bleGatewayActivity.mSecondEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text_2, "field 'mSecondEmptyText'", TextView.class);
        bleGatewayActivity.mMeshGatewayGuideView = (TextView) Utils.findRequiredViewAsType(view, R.id.mesh_gateway_guide, "field 'mMeshGatewayGuideView'", TextView.class);
        bleGatewayActivity.mBleSwitchBtn = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.btn_ble_switch, "field 'mBleSwitchBtn'", SwitchButton.class);
        bleGatewayActivity.mBleSwitch = (CardView) Utils.findRequiredViewAsType(view, R.id.ble_switch, "field 'mBleSwitch'", CardView.class);
        bleGatewayActivity.mBleSwitchTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ble, "field 'mBleSwitchTitle'", TextView.class);
        bleGatewayActivity.mViewListLayer = Utils.findRequiredView(view, R.id.view_list_layer, "field 'mViewListLayer'");
    }

    @CallSuper
    public void unbind() {
        BleGatewayActivity bleGatewayActivity = this.f11681a;
        if (bleGatewayActivity != null) {
            this.f11681a = null;
            bleGatewayActivity.mBackBt = null;
            bleGatewayActivity.mTitle = null;
            bleGatewayActivity.mHeadDescView = null;
            bleGatewayActivity.mHeadImageExtend = null;
            bleGatewayActivity.mHeadImageShrink = null;
            bleGatewayActivity.mHeadAnimImage = null;
            bleGatewayActivity.mRecyclerView = null;
            bleGatewayActivity.mNestedScrollView = null;
            bleGatewayActivity.mEmptyViewContainer = null;
            bleGatewayActivity.mEmptyView = null;
            bleGatewayActivity.mFirstEmptyText = null;
            bleGatewayActivity.mSecondEmptyText = null;
            bleGatewayActivity.mMeshGatewayGuideView = null;
            bleGatewayActivity.mBleSwitchBtn = null;
            bleGatewayActivity.mBleSwitch = null;
            bleGatewayActivity.mBleSwitchTitle = null;
            bleGatewayActivity.mViewListLayer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
