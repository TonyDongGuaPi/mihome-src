package com.xiaomi.smarthome.device.authorization.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class DeviceAuthSlaveListActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceAuthSlaveListActivity f15050a;

    @UiThread
    public DeviceAuthSlaveListActivity_ViewBinding(DeviceAuthSlaveListActivity deviceAuthSlaveListActivity) {
        this(deviceAuthSlaveListActivity, deviceAuthSlaveListActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeviceAuthSlaveListActivity_ViewBinding(DeviceAuthSlaveListActivity deviceAuthSlaveListActivity, View view) {
        this.f15050a = deviceAuthSlaveListActivity;
        deviceAuthSlaveListActivity.moduleA4ReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'moduleA4ReturnBtn'", ImageView.class);
        deviceAuthSlaveListActivity.mThirdOkButton = (TextView) Utils.findRequiredViewAsType(view, R.id.back_btn, "field 'mThirdOkButton'", TextView.class);
        deviceAuthSlaveListActivity.mTitleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTextView'", TextView.class);
        deviceAuthSlaveListActivity.commonWhiteEmptyView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.common_white_empty_view, "field 'commonWhiteEmptyView'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        DeviceAuthSlaveListActivity deviceAuthSlaveListActivity = this.f15050a;
        if (deviceAuthSlaveListActivity != null) {
            this.f15050a = null;
            deviceAuthSlaveListActivity.moduleA4ReturnBtn = null;
            deviceAuthSlaveListActivity.mThirdOkButton = null;
            deviceAuthSlaveListActivity.mTitleTextView = null;
            deviceAuthSlaveListActivity.commonWhiteEmptyView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
