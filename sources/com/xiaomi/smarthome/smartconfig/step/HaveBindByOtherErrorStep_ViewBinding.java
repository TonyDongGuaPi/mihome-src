package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class HaveBindByOtherErrorStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private HaveBindByOtherErrorStep f22611a;

    @UiThread
    public HaveBindByOtherErrorStep_ViewBinding(HaveBindByOtherErrorStep haveBindByOtherErrorStep, View view) {
        this.f22611a = haveBindByOtherErrorStep;
        haveBindByOtherErrorStep.mOkBtn = Utils.findRequiredView(view, R.id.ok_btn, "field 'mOkBtn'");
    }

    @CallSuper
    public void unbind() {
        HaveBindByOtherErrorStep haveBindByOtherErrorStep = this.f22611a;
        if (haveBindByOtherErrorStep != null) {
            this.f22611a = null;
            haveBindByOtherErrorStep.mOkBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
