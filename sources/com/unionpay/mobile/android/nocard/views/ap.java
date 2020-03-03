package com.unionpay.mobile.android.nocard.views;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.views.order.o;
import java.util.HashMap;
import java.util.Map;

final class ap implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ao f9598a;

    ap(ao aoVar) {
        this.f9598a = aoVar;
    }

    public final boolean handleMessage(Message message) {
        k.c("uppay", "mHceHandler. handleMessage");
        this.f9598a.v();
        b.bl = true;
        if (this.f9598a.f9608a.aP == l.e.intValue()) {
            this.f9598a.r.clear();
            this.f9598a.r.remove(new HashMap());
            if (b.bb == null || b.bb.size() <= 0) {
                this.f9598a.m();
                this.f9598a.f9608a.aP = l.f9759a.intValue();
                this.f9598a.a(c.bD.bq, this.f9598a.f9608a.bh, this.f9598a.f9608a.aE, true);
            } else {
                int size = b.bb.size();
                for (int i = 0; i < size; i++) {
                    d dVar = b.bb.get(i);
                    Map a2 = ao.b(dVar);
                    this.f9598a.r.add(a2);
                    if (i == 0) {
                        o b = this.f9598a.w;
                        b.b(dVar.b() + dVar.c() + " " + a2.get("text2"));
                    }
                }
                this.f9598a.w.setVisibility(0);
                if (this.f9598a.x != null) {
                    this.f9598a.x.setVisibility(8);
                }
            }
        }
        return true;
    }
}
