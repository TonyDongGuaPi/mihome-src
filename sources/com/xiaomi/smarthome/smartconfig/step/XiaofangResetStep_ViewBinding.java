package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;

public class XiaofangResetStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private XiaofangResetStep f22720a;

    @UiThread
    public XiaofangResetStep_ViewBinding(XiaofangResetStep xiaofangResetStep, View view) {
        this.f22720a = xiaofangResetStep;
        xiaofangResetStep.mWebView = (SmartHomeWebView) Utils.findRequiredViewAsType(view, R.id.kuailian_reset_web_view, "field 'mWebView'", SmartHomeWebView.class);
        xiaofangResetStep.mButton = (Button) Utils.findRequiredViewAsType(view, R.id.next_btn, "field 'mButton'", Button.class);
        xiaofangResetStep.mCheck = (CheckBox) Utils.findRequiredViewAsType(view, R.id.check_box_confirm, "field 'mCheck'", CheckBox.class);
        xiaofangResetStep.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        xiaofangResetStep.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        XiaofangResetStep xiaofangResetStep = this.f22720a;
        if (xiaofangResetStep != null) {
            this.f22720a = null;
            xiaofangResetStep.mWebView = null;
            xiaofangResetStep.mButton = null;
            xiaofangResetStep.mCheck = null;
            xiaofangResetStep.mReturnBtn = null;
            xiaofangResetStep.mTitle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
