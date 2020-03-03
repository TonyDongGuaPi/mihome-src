package com.unionpay.mobile.android.pro.views;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.model.a;
import com.unionpay.mobile.android.model.b;

final class w implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ v f9701a;

    w(v vVar) {
        this.f9701a = vVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 2000:
                if (message.obj != null) {
                    a aVar = (a) message.obj;
                    if (aVar == null) {
                        return true;
                    }
                    this.f9701a.a(aVar);
                    return true;
                }
                this.f9701a.a(this.f9701a.f9608a.ap, false);
                return true;
            case 2001:
                if ("1003100020".equalsIgnoreCase((String) message.obj)) {
                    if (!b.bm) {
                        return true;
                    }
                    this.f9701a.s();
                    return true;
                } else if (b.bm) {
                    this.f9701a.e(this.f9701a.f9608a.ap, "fail");
                    return true;
                } else {
                    this.f9701a.a(this.f9701a.f9608a.ap, false);
                    return true;
                }
            default:
                return true;
        }
    }
}
