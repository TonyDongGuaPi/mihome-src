package com.xiaomi.smarthome.scenenew.actiivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class DeviceSceneEmptyActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceSceneEmptyActivity f21807a;

    @UiThread
    public DeviceSceneEmptyActivity_ViewBinding(DeviceSceneEmptyActivity deviceSceneEmptyActivity) {
        this(deviceSceneEmptyActivity, deviceSceneEmptyActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeviceSceneEmptyActivity_ViewBinding(DeviceSceneEmptyActivity deviceSceneEmptyActivity, View view) {
        this.f21807a = deviceSceneEmptyActivity;
        deviceSceneEmptyActivity.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
        deviceSceneEmptyActivity.mReturnIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnIV'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        DeviceSceneEmptyActivity deviceSceneEmptyActivity = this.f21807a;
        if (deviceSceneEmptyActivity != null) {
            this.f21807a = null;
            deviceSceneEmptyActivity.mTitleTV = null;
            deviceSceneEmptyActivity.mReturnIV = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
