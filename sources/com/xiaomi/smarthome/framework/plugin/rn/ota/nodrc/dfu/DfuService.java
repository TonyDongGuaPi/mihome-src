package com.xiaomi.smarthome.framework.plugin.rn.ota.nodrc.dfu;

import android.app.Activity;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import no.nordicsemi.android.dfu.DfuBaseService;

public class DfuService extends DfuBaseService {
    /* access modifiers changed from: protected */
    public boolean isDebug() {
        return true;
    }

    /* access modifiers changed from: protected */
    public Class<? extends Activity> getNotificationTarget() {
        RnPluginLog.a("rn-nodrc-dfu, DfuService-->getNotificationTarget()..." + getClass().getName());
        return NotificationActivity.class;
    }
}
