package com.huawei.hms.api;

import android.os.Handler;
import android.os.Message;
import com.huawei.hms.support.log.a;

class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ HuaweiApiClientImpl f5851a;

    b(HuaweiApiClientImpl huaweiApiClientImpl) {
        this.f5851a = huaweiApiClientImpl;
    }

    public boolean handleMessage(Message message) {
        if (message == null || message.what != 2) {
            return false;
        }
        a.d("HuaweiApiClientImpl", "In connect, bind core service time out");
        if (this.f5851a.f.get() == 5) {
            this.f5851a.a(1);
            if (this.f5851a.l != null) {
                this.f5851a.l.onConnectionFailed(new ConnectionResult(6));
            }
        }
        return true;
    }
}
