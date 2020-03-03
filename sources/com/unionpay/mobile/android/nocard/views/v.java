package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;

final class v implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f9636a;

    v(o oVar) {
        this.f9636a = oVar;
    }

    public final void onClick(View view) {
        if (!this.f9636a.n()) {
            this.f9636a.t.d();
            a.C0077a b = this.f9636a.t.b();
            if (!b.a()) {
                this.f9636a.a(b.b);
            } else if (this.f9636a.C == null || this.f9636a.C.e()) {
                String str = "";
                if (this.f9636a.D != null) {
                    a.C0077a b2 = this.f9636a.D.b();
                    if (!b2.a()) {
                        this.f9636a.a(b2.b);
                        return;
                    }
                    str = b2.b;
                }
                String str2 = b.b;
                if (o.b(str)) {
                    str2 = str2 + "," + str;
                }
                this.f9636a.b.a(c.bD.U);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9636a.q);
                sb.append("_apply");
                o.b(this.f9636a, str2);
            } else {
                this.f9636a.a(this.f9636a.C.b());
            }
        }
    }
}
