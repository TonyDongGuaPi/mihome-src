package com.unionpay.mobile.android.pro.views;

import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.pro.pboc.engine.a;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class i implements a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ h f9690a;

    i(h hVar) {
        this.f9690a = hVar;
    }

    public final void a(ArrayList<c> arrayList) {
        k.a("uppay", "deviceReady +++");
        if (arrayList != null && arrayList.size() > 0) {
            if (this.f9690a.s == null) {
                this.f9690a.s = new ArrayList(arrayList.size());
            }
            this.f9690a.s.addAll(arrayList);
        }
        this.f9690a.w();
        k.a("uppay", "deviceReady ---");
    }
}
