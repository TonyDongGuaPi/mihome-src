package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;

public class ConfigStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ConfigStep f22588a;

    @UiThread
    public ConfigStep_ViewBinding(ConfigStep configStep, View view) {
        this.f22588a = configStep;
        configStep.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        configStep.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        configStep.mTitlebarTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitlebarTv'", TextView.class);
        configStep.mCommonBindView = (CommonBindView) Utils.findRequiredViewAsType(view, R.id.common_bind_view, "field 'mCommonBindView'", CommonBindView.class);
    }

    @CallSuper
    public void unbind() {
        ConfigStep configStep = this.f22588a;
        if (configStep != null) {
            this.f22588a = null;
            configStep.mTitleBar = null;
            configStep.mReturnBtn = null;
            configStep.mTitlebarTv = null;
            configStep.mCommonBindView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
