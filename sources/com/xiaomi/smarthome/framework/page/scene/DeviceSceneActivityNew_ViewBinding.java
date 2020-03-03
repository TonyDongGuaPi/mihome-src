package com.xiaomi.smarthome.framework.page.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class DeviceSceneActivityNew_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceSceneActivityNew f16974a;

    @UiThread
    public DeviceSceneActivityNew_ViewBinding(DeviceSceneActivityNew deviceSceneActivityNew) {
        this(deviceSceneActivityNew, deviceSceneActivityNew.getWindow().getDecorView());
    }

    @UiThread
    public DeviceSceneActivityNew_ViewBinding(DeviceSceneActivityNew deviceSceneActivityNew, View view) {
        this.f16974a = deviceSceneActivityNew;
        deviceSceneActivityNew.mViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
        deviceSceneActivityNew.mTabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'mTabLayout'", TabLayout.class);
        deviceSceneActivityNew.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
        deviceSceneActivityNew.mReturnIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnIV'", ImageView.class);
        deviceSceneActivityNew.mRightTitleIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'mRightTitleIV'", ImageView.class);
        deviceSceneActivityNew.mLoadingView = Utils.findRequiredView(view, R.id.loading, "field 'mLoadingView'");
    }

    @CallSuper
    public void unbind() {
        DeviceSceneActivityNew deviceSceneActivityNew = this.f16974a;
        if (deviceSceneActivityNew != null) {
            this.f16974a = null;
            deviceSceneActivityNew.mViewPager = null;
            deviceSceneActivityNew.mTabLayout = null;
            deviceSceneActivityNew.mTitleTV = null;
            deviceSceneActivityNew.mReturnIV = null;
            deviceSceneActivityNew.mRightTitleIV = null;
            deviceSceneActivityNew.mLoadingView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
