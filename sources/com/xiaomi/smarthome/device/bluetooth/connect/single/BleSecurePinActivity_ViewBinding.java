package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.verify.view.PinInputView;
import com.xiaomi.smarthome.framework.page.verify.view.PinSoftKeyboard;

public class BleSecurePinActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private BleSecurePinActivity f15215a;

    @UiThread
    public BleSecurePinActivity_ViewBinding(BleSecurePinActivity bleSecurePinActivity) {
        this(bleSecurePinActivity, bleSecurePinActivity.getWindow().getDecorView());
    }

    @UiThread
    public BleSecurePinActivity_ViewBinding(BleSecurePinActivity bleSecurePinActivity, View view) {
        this.f15215a = bleSecurePinActivity;
        bleSecurePinActivity.mMessageView = (TextView) Utils.findRequiredViewAsType(view, R.id.message, "field 'mMessageView'", TextView.class);
        bleSecurePinActivity.mCheckBoxContainer = Utils.findRequiredView(view, R.id.ratio_container, "field 'mCheckBoxContainer'");
        bleSecurePinActivity.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ratio_btn, "field 'mCheckBox'", CheckBox.class);
        bleSecurePinActivity.mCancelView = Utils.findRequiredView(view, R.id.cancel, "field 'mCancelView'");
        bleSecurePinActivity.vPinInputView = (PinInputView) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_inputs, "field 'vPinInputView'", PinInputView.class);
        bleSecurePinActivity.vPinSoftKeyboard = (PinSoftKeyboard) Utils.findRequiredViewAsType(view, R.id.xiaomi_sm_pin_softkeyboard, "field 'vPinSoftKeyboard'", PinSoftKeyboard.class);
    }

    @CallSuper
    public void unbind() {
        BleSecurePinActivity bleSecurePinActivity = this.f15215a;
        if (bleSecurePinActivity != null) {
            this.f15215a = null;
            bleSecurePinActivity.mMessageView = null;
            bleSecurePinActivity.mCheckBoxContainer = null;
            bleSecurePinActivity.mCheckBox = null;
            bleSecurePinActivity.mCancelView = null;
            bleSecurePinActivity.vPinInputView = null;
            bleSecurePinActivity.vPinSoftKeyboard = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
