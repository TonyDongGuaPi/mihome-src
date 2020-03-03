package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.j;

final class h implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9623a;

    h(g gVar) {
        this.f9623a = gVar;
    }

    public final void onClick(View view) {
        if (!this.f9623a.n()) {
            this.f9623a.v.d();
            a.C0077a b = this.f9623a.v.b();
            if (!b.a()) {
                this.f9623a.a(b.b);
            } else if (this.f9623a.t == null || this.f9623a.t.e()) {
                this.f9623a.b.a(c.bD.U);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9623a.q);
                sb.append("_open_apply");
                if (!this.f9623a.w) {
                    this.f9623a.e.c(j.a(this.f9623a.f9608a.C, "action"), b.b);
                    int unused = this.f9623a.s = 102;
                    return;
                }
                this.f9623a.s();
                int unused2 = this.f9623a.s = 104;
            } else {
                this.f9623a.a(this.f9623a.t.b());
            }
        }
    }
}
