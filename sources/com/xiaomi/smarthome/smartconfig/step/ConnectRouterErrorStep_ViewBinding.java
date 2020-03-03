package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ConnectRouterErrorStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ConnectRouterErrorStep f22591a;

    @UiThread
    public ConnectRouterErrorStep_ViewBinding(ConnectRouterErrorStep connectRouterErrorStep, View view) {
        this.f22591a = connectRouterErrorStep;
        connectRouterErrorStep.mMainIconContainer = Utils.findRequiredView(view, R.id.base_ui_main_icon, "field 'mMainIconContainer'");
        connectRouterErrorStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        connectRouterErrorStep.mInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mInfo'", TextView.class);
        connectRouterErrorStep.mInfoSubTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mInfoSubTitle'", TextView.class);
        connectRouterErrorStep.mBottomContainer = Utils.findRequiredView(view, R.id.bottom_btn_container, "field 'mBottomContainer'");
        connectRouterErrorStep.mLeftBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.left_btn, "field 'mLeftBtn'", TextView.class);
        connectRouterErrorStep.mRightBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.right_btn, "field 'mRightBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ConnectRouterErrorStep connectRouterErrorStep = this.f22591a;
        if (connectRouterErrorStep != null) {
            this.f22591a = null;
            connectRouterErrorStep.mMainIconContainer = null;
            connectRouterErrorStep.mIcon = null;
            connectRouterErrorStep.mInfo = null;
            connectRouterErrorStep.mInfoSubTitle = null;
            connectRouterErrorStep.mBottomContainer = null;
            connectRouterErrorStep.mLeftBtn = null;
            connectRouterErrorStep.mRightBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
