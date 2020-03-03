package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.utils.j;

final class f implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9546a;

    f(e eVar) {
        this.f9546a = eVar;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.f9546a.i.removeMessages(4);
            j.b("uppay", "msg error");
            e.a(this.f9546a, message.arg1, (String) message.obj);
            return false;
        } else if (i == 4) {
            j.b("uppay", "timeout");
            e.a(this.f9546a, message.arg1, UPSEInfoResp.ERROR_TIMEOUT);
            UPQuerySEPayInfoCallback unused = this.f9546a.b = null;
            return false;
        } else if (i != 4000) {
            return false;
        } else {
            this.f9546a.i.removeMessages(4);
            e.a(this.f9546a, (Bundle) message.obj);
            return false;
        }
    }
}
