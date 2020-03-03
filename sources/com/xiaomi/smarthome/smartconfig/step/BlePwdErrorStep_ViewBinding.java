package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class BlePwdErrorStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private BlePwdErrorStep f22492a;

    @UiThread
    public BlePwdErrorStep_ViewBinding(BlePwdErrorStep blePwdErrorStep, View view) {
        this.f22492a = blePwdErrorStep;
        blePwdErrorStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        blePwdErrorStep.mMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mMainTitle'", TextView.class);
        blePwdErrorStep.mSubMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mSubMainTitle'", TextView.class);
        blePwdErrorStep.mLeftBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.left_btn, "field 'mLeftBtn'", TextView.class);
        blePwdErrorStep.mRightBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.right_btn, "field 'mRightBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        BlePwdErrorStep blePwdErrorStep = this.f22492a;
        if (blePwdErrorStep != null) {
            this.f22492a = null;
            blePwdErrorStep.mIcon = null;
            blePwdErrorStep.mMainTitle = null;
            blePwdErrorStep.mSubMainTitle = null;
            blePwdErrorStep.mLeftBtn = null;
            blePwdErrorStep.mRightBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
