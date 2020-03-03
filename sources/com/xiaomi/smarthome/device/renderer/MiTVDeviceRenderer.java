package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ViewUtils;

public class MiTVDeviceRenderer extends DeviceRenderer {
    /* access modifiers changed from: protected */
    public void a(Context context, MLAlertDialog mLAlertDialog, String str) {
        mLAlertDialog.setView(ViewUtils.a(context, mLAlertDialog));
    }

    /* access modifiers changed from: protected */
    public void a(Device device, ViewHolder viewHolder) {
        if (!((MiTVDevice) device).d() || !device.isOnline) {
            viewHolder.addBtn.setVisibility(4);
        } else {
            viewHolder.addBtn.setVisibility(0);
        }
        viewHolder.addBtn.setText(R.string.button_bind_mitv);
    }
}
