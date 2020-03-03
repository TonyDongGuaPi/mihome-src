package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class XiaoxunErrorStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private XiaoxunErrorStep f22723a;

    @UiThread
    public XiaoxunErrorStep_ViewBinding(XiaoxunErrorStep xiaoxunErrorStep, View view) {
        this.f22723a = xiaoxunErrorStep;
        xiaoxunErrorStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        xiaoxunErrorStep.mMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mMainTitle'", TextView.class);
        xiaoxunErrorStep.mSubMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mSubMainTitle'", TextView.class);
        xiaoxunErrorStep.mLeftBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.left_btn, "field 'mLeftBtn'", TextView.class);
        xiaoxunErrorStep.mRightBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.right_btn, "field 'mRightBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        XiaoxunErrorStep xiaoxunErrorStep = this.f22723a;
        if (xiaoxunErrorStep != null) {
            this.f22723a = null;
            xiaoxunErrorStep.mIcon = null;
            xiaoxunErrorStep.mMainTitle = null;
            xiaoxunErrorStep.mSubMainTitle = null;
            xiaoxunErrorStep.mLeftBtn = null;
            xiaoxunErrorStep.mRightBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
