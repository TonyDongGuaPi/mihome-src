package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class o implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9729a;

    o(j jVar) {
        this.f9729a = jVar;
    }

    public final void onClick(View view) {
        if (this.f9729a.n != this.f9729a.d) {
            Object obj = this.f9729a.g.get(this.f9729a.n);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        int unused = this.f9729a.n = this.f9729a.o;
        int unused2 = this.f9729a.p = this.f9729a.d;
        Iterator it = this.f9729a.x.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
