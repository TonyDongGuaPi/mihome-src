package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;
import java.util.HashMap;

final class l implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9691a;

    l(k kVar) {
        this.f9691a = kVar;
    }

    public final void onClick(View view) {
        if (!this.f9691a.n()) {
            this.f9691a.y.d();
            if (this.f9691a.L != null && !this.f9691a.L.e()) {
                this.f9691a.a(this.f9691a.L.b());
            } else if (this.f9691a.K != null && !this.f9691a.K.e()) {
                this.f9691a.a(this.f9691a.K.b());
            } else if (this.f9691a.f9608a.p != null) {
                a.C0077a a2 = this.f9691a.y.a();
                if (!a2.a()) {
                    this.f9691a.a(a2.b);
                    return;
                }
                Context unused = this.f9691a.d;
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9691a.q);
                sb.append("_apply");
                this.f9691a.b.a(c.bD.U);
                int unused2 = this.f9691a.v = 101;
                this.f9691a.b(this.f9691a.y.a().b, (HashMap<String, String>) this.f9691a.t());
            }
        }
    }
}
