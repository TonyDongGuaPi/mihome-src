package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ApConnectFailed2Step_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ApConnectFailed2Step f22409a;

    @UiThread
    public ApConnectFailed2Step_ViewBinding(ApConnectFailed2Step apConnectFailed2Step, View view) {
        this.f22409a = apConnectFailed2Step;
        apConnectFailed2Step.mSettingWifiBtn = (Button) Utils.findRequiredViewAsType(view, R.id.next_btn, "field 'mSettingWifiBtn'", Button.class);
        apConnectFailed2Step.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        apConnectFailed2Step.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        apConnectFailed2Step.mTitlebarTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitlebarTv'", TextView.class);
        apConnectFailed2Step.mTvFailHint = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fail_hint, "field 'mTvFailHint'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ApConnectFailed2Step apConnectFailed2Step = this.f22409a;
        if (apConnectFailed2Step != null) {
            this.f22409a = null;
            apConnectFailed2Step.mSettingWifiBtn = null;
            apConnectFailed2Step.mTitleBar = null;
            apConnectFailed2Step.mReturnBtn = null;
            apConnectFailed2Step.mTitlebarTv = null;
            apConnectFailed2Step.mTvFailHint = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
