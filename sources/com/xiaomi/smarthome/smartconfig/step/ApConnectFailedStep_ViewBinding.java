package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ApConnectFailedStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ApConnectFailedStep f22412a;

    @UiThread
    public ApConnectFailedStep_ViewBinding(ApConnectFailedStep apConnectFailedStep, View view) {
        this.f22412a = apConnectFailedStep;
        apConnectFailedStep.mManualView = Utils.findRequiredView(view, R.id.base_ui_connect_manually, "field 'mManualView'");
        apConnectFailedStep.mSettingWifiBtn = (Button) Utils.findRequiredViewAsType(view, R.id.next_btn, "field 'mSettingWifiBtn'", Button.class);
        apConnectFailedStep.mManualIcon = (TextView) Utils.findRequiredViewAsType(view, R.id.manual_connect_icon, "field 'mManualIcon'", TextView.class);
        apConnectFailedStep.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        apConnectFailedStep.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        apConnectFailedStep.mTitlebarTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitlebarTv'", TextView.class);
        apConnectFailedStep.mManualText = (TextView) Utils.findRequiredViewAsType(view, R.id.manual_text, "field 'mManualText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ApConnectFailedStep apConnectFailedStep = this.f22412a;
        if (apConnectFailedStep != null) {
            this.f22412a = null;
            apConnectFailedStep.mManualView = null;
            apConnectFailedStep.mSettingWifiBtn = null;
            apConnectFailedStep.mManualIcon = null;
            apConnectFailedStep.mTitleBar = null;
            apConnectFailedStep.mReturnBtn = null;
            apConnectFailedStep.mTitlebarTv = null;
            apConnectFailedStep.mManualText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
