package com.unionpay.mobile.android.pro.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;

final class z implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f9702a;

    z(y yVar) {
        this.f9702a = yVar;
    }

    public final void onClick(View view) {
        if (!this.f9702a.n()) {
            this.f9702a.A.d();
            a.C0077a a2 = this.f9702a.A.a();
            if (!a2.a()) {
                this.f9702a.a(a2.b);
            } else if (this.f9702a.x != null && !this.f9702a.x.e()) {
                this.f9702a.a(this.f9702a.x.b());
            } else if (this.f9702a.w == null || this.f9702a.w.e()) {
                this.f9702a.b.a(c.bD.U);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9702a.q);
                sb.append("_apply");
                if (this.f9702a.f9608a.br) {
                    y.a(this.f9702a, this.f9702a.f9608a.bs, this.f9702a.A.a().b, y.i(this.f9702a));
                } else {
                    y.a(this.f9702a, this.f9702a.f9608a.q.get(this.f9702a.f9608a.N), this.f9702a.A.a().b, y.i(this.f9702a));
                }
            } else {
                this.f9702a.a(this.f9702a.w.b());
            }
        }
    }
}
