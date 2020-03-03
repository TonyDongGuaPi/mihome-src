package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class FindDeviceFailedFinalStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private FindDeviceFailedFinalStep f22604a;

    @UiThread
    public FindDeviceFailedFinalStep_ViewBinding(FindDeviceFailedFinalStep findDeviceFailedFinalStep, View view) {
        this.f22604a = findDeviceFailedFinalStep;
        findDeviceFailedFinalStep.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        findDeviceFailedFinalStep.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        findDeviceFailedFinalStep.mTitlebarTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitlebarTv'", TextView.class);
        findDeviceFailedFinalStep.mDeviceImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_image, "field 'mDeviceImage'", SimpleDraweeView.class);
        findDeviceFailedFinalStep.mTips = (TextView) Utils.findRequiredViewAsType(view, R.id.tips, "field 'mTips'", TextView.class);
        findDeviceFailedFinalStep.mButton1 = Utils.findRequiredView(view, R.id.button1, "field 'mButton1'");
        findDeviceFailedFinalStep.mButton2 = Utils.findRequiredView(view, R.id.button2, "field 'mButton2'");
        findDeviceFailedFinalStep.mButton3 = Utils.findRequiredView(view, R.id.button3, "field 'mButton3'");
        findDeviceFailedFinalStep.mButtonName_1 = (TextView) Utils.findRequiredViewAsType(view, R.id.button1_name, "field 'mButtonName_1'", TextView.class);
        findDeviceFailedFinalStep.mButtonName_3 = (TextView) Utils.findRequiredViewAsType(view, R.id.button3_name, "field 'mButtonName_3'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        FindDeviceFailedFinalStep findDeviceFailedFinalStep = this.f22604a;
        if (findDeviceFailedFinalStep != null) {
            this.f22604a = null;
            findDeviceFailedFinalStep.mTitleBar = null;
            findDeviceFailedFinalStep.mReturnBtn = null;
            findDeviceFailedFinalStep.mTitlebarTv = null;
            findDeviceFailedFinalStep.mDeviceImage = null;
            findDeviceFailedFinalStep.mTips = null;
            findDeviceFailedFinalStep.mButton1 = null;
            findDeviceFailedFinalStep.mButton2 = null;
            findDeviceFailedFinalStep.mButton3 = null;
            findDeviceFailedFinalStep.mButtonName_1 = null;
            findDeviceFailedFinalStep.mButtonName_3 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
