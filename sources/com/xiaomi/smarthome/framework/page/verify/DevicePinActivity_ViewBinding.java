package com.xiaomi.smarthome.framework.page.verify;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.verify.view.PinInputView;
import com.xiaomi.smarthome.framework.page.verify.view.PinSoftKeyboard;

public class DevicePinActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DevicePinActivity f17037a;
    private View b;

    @UiThread
    public DevicePinActivity_ViewBinding(DevicePinActivity devicePinActivity) {
        this(devicePinActivity, devicePinActivity.getWindow().getDecorView());
    }

    @UiThread
    public DevicePinActivity_ViewBinding(final DevicePinActivity devicePinActivity, View view) {
        this.f17037a = devicePinActivity;
        devicePinActivity.vTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'vTitle'", TextView.class);
        devicePinActivity.vDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_inputs_desc, "field 'vDesc'", TextView.class);
        devicePinActivity.vPinInputView = (PinInputView) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_inputs, "field 'vPinInputView'", PinInputView.class);
        devicePinActivity.vPinSoftKeyboard = (PinSoftKeyboard) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_softkeyboard, "field 'vPinSoftKeyboard'", PinSoftKeyboard.class);
        devicePinActivity.vSubHint = (TextView) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_sub_hint, "field 'vSubHint'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'onClickBack'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                devicePinActivity.onClickBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DevicePinActivity devicePinActivity = this.f17037a;
        if (devicePinActivity != null) {
            this.f17037a = null;
            devicePinActivity.vTitle = null;
            devicePinActivity.vDesc = null;
            devicePinActivity.vPinInputView = null;
            devicePinActivity.vPinSoftKeyboard = null;
            devicePinActivity.vSubHint = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
