package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.upwidget.w;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class af extends b implements a.b {
    private TextView r = null;
    private View.OnClickListener s = null;
    private a t = null;
    private int u = 0;

    public af(Context context) {
        super(context);
        this.f = 12;
        this.s = new ag(this);
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(af afVar) {
        afVar.u = 1;
        a.C0077a b = afVar.t.b();
        if (b.a()) {
            afVar.j = false;
            afVar.b.a(c.bD.U);
            afVar.e.m(b.b);
            return;
        }
        afVar.a(b.b);
    }

    public final void a(a.C0077a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        if (this.u == 1) {
            this.b.c();
            f.c(this.f9608a, jSONObject);
            int b = f.b(this.f9608a, jSONObject);
            if (b != 0) {
                b(b);
                return;
            }
            if (this.t != null) {
                this.t.f();
            }
            d(13);
        }
    }

    public final void a(boolean z) {
        if (this.r != null) {
            this.r.setEnabled(!z);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(this.d, c.bD.m, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.o.a((UPScrollView.a) this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
        this.t = new a(this.d, this.f9608a.X, this, "");
        boolean z = true;
        this.t.setOrientation(1);
        this.t.setId(this.t.hashCode());
        this.m.addView(this.t, layoutParams);
        w a2 = w.a(this.d, this.f9608a.Y, this.c.a(1017, -1, -1));
        if (a2 != null) {
            a2.setId(a2.hashCode());
            a2.a(new ah(this, a2.a()));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(3, this.t.getId());
            int i = com.unionpay.mobile.android.global.a.d;
            layoutParams2.bottomMargin = i;
            layoutParams2.topMargin = i;
            layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
            this.m.addView(a2, layoutParams2);
        }
        this.r = new TextView(this.d);
        this.r.setText(c.bD.n);
        this.r.setTextSize(b.i);
        this.r.setTextColor(o());
        this.r.setGravity(17);
        TextView textView = this.r;
        if (this.t != null && !this.t.e()) {
            z = false;
        }
        textView.setEnabled(z);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.r.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.r.setOnClickListener(this.s);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams3.addRule(3, a2 != null ? a2.getId() : this.t.getId());
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
        this.m.addView(this.r, layoutParams3);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final void r() {
    }
}
