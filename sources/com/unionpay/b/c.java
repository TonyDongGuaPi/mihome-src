package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.utils.j;

final class c implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9543a;

    c(b bVar) {
        this.f9543a = bVar;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.f9543a.i.removeMessages(4);
            j.b("uppay", "msg error");
            b.a(this.f9543a, message.arg1, (String) message.obj);
            return false;
        } else if (i == 4) {
            j.b("uppay", "timeout");
            b.a(this.f9543a, message.arg1, UPSEInfoResp.ERROR_TIMEOUT);
            UPQuerySEPayInfoCallback unused = this.f9543a.b = null;
            return false;
        } else if (i != 4000) {
            return false;
        } else {
            this.f9543a.i.removeMessages(4);
            b.a(this.f9543a, (Bundle) message.obj);
            return false;
        }
    }
}
