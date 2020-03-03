package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;

final class al implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ak f9594a;

    al(ak akVar) {
        this.f9594a = akVar;
    }

    public final void onClick(View view) {
        if (!this.f9594a.n()) {
            this.f9594a.v.d();
            a.C0077a b = this.f9594a.v.b();
            if (!b.a()) {
                this.f9594a.a(b.b);
            } else if (this.f9594a.t == null || this.f9594a.t.e()) {
                String str = b.b;
                this.f9594a.b.a(c.bD.U);
                this.f9594a.e.k(str);
                int unused = this.f9594a.r = 102;
            } else {
                this.f9594a.a(this.f9594a.t.b());
            }
        }
    }
}
