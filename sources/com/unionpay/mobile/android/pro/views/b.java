package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.hce.a;
import org.json.JSONObject;

final class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9684a;

    b(a aVar) {
        this.f9684a = aVar;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 2004) {
            this.f9684a.H.removeMessages(2006);
            Bundle bundle = (Bundle) message.obj;
            if (bundle == null) {
                return true;
            }
            if (bundle.getBoolean("success")) {
                try {
                    if (!a.d(new JSONObject(a.a(bundle.getString("result"), this.f9684a.C.f())))) {
                        this.f9684a.a(this.f9684a.f9608a.ap, false);
                        return false;
                    }
                    a.a(this.f9684a, this.f9684a.x.a().b, a.f(this.f9684a));
                    return true;
                } catch (Exception e) {
                    this.f9684a.a(this.f9684a.f9608a.ap, false);
                    e.printStackTrace();
                    return false;
                }
            } else {
                this.f9684a.a(this.f9684a.f9608a.ap, false);
                return true;
            }
        } else if (i != 2006) {
            return true;
        } else {
            this.f9684a.a(this.f9684a.f9608a.ap, false);
            return true;
        }
    }
}
