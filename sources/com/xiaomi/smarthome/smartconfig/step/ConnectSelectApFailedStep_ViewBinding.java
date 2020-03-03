package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ConnectSelectApFailedStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ConnectSelectApFailedStep f22594a;

    @UiThread
    public ConnectSelectApFailedStep_ViewBinding(ConnectSelectApFailedStep connectSelectApFailedStep, View view) {
        this.f22594a = connectSelectApFailedStep;
        connectSelectApFailedStep.mIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_icon, "field 'mIcon'", ImageView.class);
        connectSelectApFailedStep.mMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_title, "field 'mMainTitle'", TextView.class);
        connectSelectApFailedStep.mSubMainTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.smart_config_common_main_sub_title, "field 'mSubMainTitle'", TextView.class);
        connectSelectApFailedStep.mLeftBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.left_btn, "field 'mLeftBtn'", TextView.class);
        connectSelectApFailedStep.mRightBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.right_btn, "field 'mRightBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ConnectSelectApFailedStep connectSelectApFailedStep = this.f22594a;
        if (connectSelectApFailedStep != null) {
            this.f22594a = null;
            connectSelectApFailedStep.mIcon = null;
            connectSelectApFailedStep.mMainTitle = null;
            connectSelectApFailedStep.mSubMainTitle = null;
            connectSelectApFailedStep.mLeftBtn = null;
            connectSelectApFailedStep.mRightBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
