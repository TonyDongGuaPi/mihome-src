package com.xiaomi.smarthome.miio.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ViewPager;

public class BleGatewayGuideActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private BleGatewayGuideActivity f11696a;

    @UiThread
    public BleGatewayGuideActivity_ViewBinding(BleGatewayGuideActivity bleGatewayGuideActivity) {
        this(bleGatewayGuideActivity, bleGatewayGuideActivity.getWindow().getDecorView());
    }

    @UiThread
    public BleGatewayGuideActivity_ViewBinding(BleGatewayGuideActivity bleGatewayGuideActivity, View view) {
        this.f11696a = bleGatewayGuideActivity;
        bleGatewayGuideActivity.mViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
        bleGatewayGuideActivity.mFirstIndicator = Utils.findRequiredView(view, R.id.first_indicator, "field 'mFirstIndicator'");
        bleGatewayGuideActivity.mSecondIndicator = Utils.findRequiredView(view, R.id.second_indicator, "field 'mSecondIndicator'");
    }

    @CallSuper
    public void unbind() {
        BleGatewayGuideActivity bleGatewayGuideActivity = this.f11696a;
        if (bleGatewayGuideActivity != null) {
            this.f11696a = null;
            bleGatewayGuideActivity.mViewPager = null;
            bleGatewayGuideActivity.mFirstIndicator = null;
            bleGatewayGuideActivity.mSecondIndicator = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
