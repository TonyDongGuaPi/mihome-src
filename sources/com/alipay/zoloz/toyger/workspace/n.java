package com.alipay.zoloz.toyger.workspace;

import com.alipay.zoloz.toyger.workspace.alert.AlertType;

class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ m f1231a;

    n(m mVar) {
        this.f1231a = mVar;
    }

    public void run() {
        this.f1231a.f1230a.mAlertHelper.alert(AlertType.ALERT_TIMEOUT);
    }
}
