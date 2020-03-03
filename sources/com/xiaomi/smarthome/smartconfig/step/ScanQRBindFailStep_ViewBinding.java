package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ScanQRBindFailStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ScanQRBindFailStep f22682a;

    @UiThread
    public ScanQRBindFailStep_ViewBinding(ScanQRBindFailStep scanQRBindFailStep, View view) {
        this.f22682a = scanQRBindFailStep;
        scanQRBindFailStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        scanQRBindFailStep.mMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mMainTitle'", TextView.class);
        scanQRBindFailStep.mSubMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mSubMainTitle'", TextView.class);
        scanQRBindFailStep.mConfim = (Button) Utils.findRequiredViewAsType(view, R.id.btn_1, "field 'mConfim'", Button.class);
    }

    @CallSuper
    public void unbind() {
        ScanQRBindFailStep scanQRBindFailStep = this.f22682a;
        if (scanQRBindFailStep != null) {
            this.f22682a = null;
            scanQRBindFailStep.mIcon = null;
            scanQRBindFailStep.mMainTitle = null;
            scanQRBindFailStep.mSubMainTitle = null;
            scanQRBindFailStep.mConfim = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
