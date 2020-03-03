package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;

final class f implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9688a;

    f(a aVar) {
        this.f9688a = aVar;
    }

    public final void onClick(View view) {
        if (!this.f9688a.n()) {
            this.f9688a.x.d();
            if (this.f9688a.F) {
                a.p(this.f9688a);
            } else if (this.f9688a.f9608a.p != null) {
                a.C0077a a2 = this.f9688a.x.a();
                if (!a2.a()) {
                    this.f9688a.a(a2.b);
                    return;
                }
                Context unused = this.f9688a.d;
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9688a.q);
                sb.append("_apply");
                this.f9688a.b.a(c.bD.U);
                int unused2 = this.f9688a.u = 101;
                this.f9688a.a(this.f9688a.f9608a.p);
            }
        }
    }
}
