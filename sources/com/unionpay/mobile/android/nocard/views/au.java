package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;

final class au implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ at f9602a;

    au(at atVar) {
        this.f9602a = atVar;
    }

    public final void onClick(View view) {
        if (!this.f9602a.n()) {
            String str = "";
            if (this.f9602a.A != null) {
                this.f9602a.A.d();
                a.C0077a b = this.f9602a.A.b();
                if (!b.a()) {
                    this.f9602a.a(b.b);
                    return;
                }
                str = b.b;
            }
            this.f9602a.B.d();
            a.C0077a b2 = this.f9602a.B.b();
            if (!b2.a()) {
                this.f9602a.a(b2.b);
            } else if (this.f9602a.y != null && !this.f9602a.y.e()) {
                this.f9602a.a(this.f9602a.y.b());
            } else if (this.f9602a.x == null || this.f9602a.x.e()) {
                String str2 = b2.b;
                if (at.b(str)) {
                    str2 = str2 + "," + str;
                }
                this.f9602a.b.a(c.bD.U);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9602a.q);
                sb.append("_apply");
                if (this.f9602a.f9608a.E == null || this.f9602a.f9608a.E.length() <= 0) {
                    this.f9602a.e(str2);
                } else {
                    at.a(this.f9602a, this.f9602a.C, str2);
                }
            } else {
                this.f9602a.a(this.f9602a.x.b());
            }
        }
    }
}
