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
import com.xiaomi.smarthome.newui.widget.RippleLayout;

public class SWCameraStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SWCameraStep f22680a;

    @UiThread
    public SWCameraStep_ViewBinding(SWCameraStep sWCameraStep, View view) {
        this.f22680a = sWCameraStep;
        sWCameraStep.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        sWCameraStep.mIvReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mIvReturn'", ImageView.class);
        sWCameraStep.mTvTopDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.top_desc, "field 'mTvTopDesc'", TextView.class);
        sWCameraStep.mNextButton = (Button) Utils.findRequiredViewAsType(view, R.id.heard_qr_scaned, "field 'mNextButton'", Button.class);
        sWCameraStep.mTvSecDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.sec_desc, "field 'mTvSecDesc'", TextView.class);
        sWCameraStep.mRipple = (RippleLayout) Utils.findRequiredViewAsType(view, R.id.ripple_view, "field 'mRipple'", RippleLayout.class);
        sWCameraStep.mTvGoQR = (TextView) Utils.findRequiredViewAsType(view, R.id.go_qr, "field 'mTvGoQR'", TextView.class);
        sWCameraStep.mTvNothingHeared = (TextView) Utils.findRequiredViewAsType(view, R.id.nothing_heard, "field 'mTvNothingHeared'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SWCameraStep sWCameraStep = this.f22680a;
        if (sWCameraStep != null) {
            this.f22680a = null;
            sWCameraStep.mTitleBar = null;
            sWCameraStep.mIvReturn = null;
            sWCameraStep.mTvTopDesc = null;
            sWCameraStep.mNextButton = null;
            sWCameraStep.mTvSecDesc = null;
            sWCameraStep.mRipple = null;
            sWCameraStep.mTvGoQR = null;
            sWCameraStep.mTvNothingHeared = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
