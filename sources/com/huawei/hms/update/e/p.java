package com.huawei.hms.update.e;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class p extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f5932a;

    p(o oVar) {
        this.f5932a = oVar;
    }

    public void handleMessage(Message message) {
        Bundle bundle = (Bundle) message.obj;
        switch (message.what) {
            case 101:
                this.f5932a.a(bundle);
                return;
            case 102:
                this.f5932a.b(bundle);
                return;
            case 103:
                this.f5932a.c(bundle);
                return;
            default:
                return;
        }
    }
}
