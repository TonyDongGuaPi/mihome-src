package com.unionpay.mobile.android.hce;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.miui.tsmclient.util.Constants;
import com.unionpay.mobile.android.utils.k;

final class g implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9566a;

    g(f fVar) {
        this.f9566a = fVar;
    }

    public final boolean handleMessage(Message message) {
        String str;
        switch (message.what) {
            case 2001:
                f.a(this.f9566a);
                this.f9566a.b();
                return false;
            case 2002:
                str = (String) message.obj;
                break;
            case 2003:
                Bundle bundle = (Bundle) message.obj;
                if (bundle == null) {
                    return false;
                }
                String string = bundle.getString(Constants.KEY_PACKAGE_NAME);
                boolean z = bundle.getBoolean("success");
                String string2 = bundle.getString("result");
                String string3 = bundle.getString("reserved");
                k.c("yitong", "result: " + string2);
                d dVar = (d) this.f9566a.u.get(string);
                if (dVar == null) {
                    dVar = new d(string);
                }
                if (z) {
                    dVar.a(string2);
                    dVar.b(string3);
                }
                dVar.e();
                this.f9566a.u.put(string, dVar);
                f.a(this.f9566a, string);
                return false;
            case 2005:
                break;
            case 2006:
                Object obj = message.obj;
                break;
            default:
                return false;
        }
        str = (String) message.obj;
        d dVar2 = (d) this.f9566a.u.get(str);
        l lVar = (l) this.f9566a.v.get(str);
        dVar2.f();
        this.f9566a.u.put(str, dVar2);
        lVar.e();
        this.f9566a.v.put(str, lVar);
        f.a(this.f9566a, str);
        return false;
    }
}
