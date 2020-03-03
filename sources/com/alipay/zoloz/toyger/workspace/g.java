package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.zoloz.toyger.workspace.alert.AlertType;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerWorkspace f1224a;

    g(ToygerWorkspace toygerWorkspace) {
        this.f1224a = toygerWorkspace;
    }

    public void run() {
        BioLog.d("zolozTime", "sendresponse liveness fail!");
        this.f1224a.responseWithCode(this.f1224a.mAlertHelper.getAlertReturnCode(AlertType.ALERT_REMOTE_COMMAND_FAIL_RETRY));
    }
}
