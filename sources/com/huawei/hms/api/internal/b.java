package com.huawei.hms.api.internal;

import android.os.Handler;
import android.os.Message;
import com.huawei.hms.support.log.a;

class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f5857a;

    b(a aVar) {
        this.f5857a = aVar;
    }

    public boolean handleMessage(Message message) {
        if (message == null || message.what != 2) {
            return false;
        }
        a.d("BindingFailedResolution", "In connect, bind core try timeout");
        this.f5857a.b(false);
        return true;
    }
}
