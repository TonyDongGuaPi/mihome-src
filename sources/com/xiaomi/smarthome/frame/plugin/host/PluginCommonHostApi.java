package com.xiaomi.smarthome.frame.plugin.host;

import android.app.Dialog;
import android.view.Window;
import com.xiaomi.smarthome.device.api.XmPluginCommonApi;
import com.xiaomi.smarthome.sdk.R;

public abstract class PluginCommonHostApi extends XmPluginCommonApi {
    public PluginCommonHostApi() {
        XmPluginCommonApi.sXmPluginHostApi = this;
    }

    public void setWindowAnimations(Dialog dialog) {
        dialog.getWindow().setWindowAnimations(R.style.V5_Animation_Dialog);
    }

    public void setMenuDialogWindowAnimations(Window window) {
        window.setWindowAnimations(R.style.V5_Animation_MenuDialog);
    }
}
