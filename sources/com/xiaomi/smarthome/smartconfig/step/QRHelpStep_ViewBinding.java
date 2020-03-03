package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class QRHelpStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private QRHelpStep f22658a;

    @UiThread
    public QRHelpStep_ViewBinding(QRHelpStep qRHelpStep, View view) {
        this.f22658a = qRHelpStep;
        qRHelpStep.mOkButton = (Button) Utils.findRequiredViewAsType(view, R.id.ok_btn, "field 'mOkButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        QRHelpStep qRHelpStep = this.f22658a;
        if (qRHelpStep != null) {
            this.f22658a = null;
            qRHelpStep.mOkButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
