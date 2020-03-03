package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class ai extends b {
    private TextView r = null;
    private View.OnClickListener s = new aj(this);

    public ai(Context context) {
        super(context);
        this.f = 11;
        this.k = a();
        b();
        super.d();
        c();
    }

    /* access modifiers changed from: private */
    public void r() {
        this.f9608a.I.f = "success";
        j();
    }

    public final void a(JSONObject jSONObject) {
        i();
        this.f9608a.T = j.d(jSONObject, "open_rules");
        if (this.f9608a.T == null || this.f9608a.T.length() <= 0) {
            b(2);
        } else {
            d(10);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(getContext(), this.f9608a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        int i = a.d;
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-1114114);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(0, i, 0, i);
        linearLayout.setId(linearLayout.hashCode());
        TextView textView = new TextView(this.d);
        textView.setText(this.f9608a.B);
        textView.setTextSize(24.0f);
        textView.setTextColor(-15365480);
        textView.setGravity(1);
        textView.getPaint().setFakeBoldText(true);
        linearLayout.addView(textView);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(0);
        linearLayout2.setBackgroundColor(-6958338);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 2);
        layoutParams.addRule(14, -1);
        int i2 = a.d;
        layoutParams.bottomMargin = i2;
        layoutParams.topMargin = i2;
        linearLayout.addView(linearLayout2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(10, -1);
        this.m.addView(linearLayout, layoutParams2);
        LinearLayout linearLayout3 = new LinearLayout(this.d);
        linearLayout3.setPadding(i, i, i, i);
        linearLayout3.setOrientation(1);
        linearLayout3.setId(linearLayout3.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, linearLayout.getId());
        this.m.addView(linearLayout3, layoutParams3);
        TextView textView2 = new TextView(this.d);
        textView2.setTextSize(18.0f);
        textView2.setText(this.f9608a.S);
        textView2.setTextColor(-10066330);
        textView2.setGravity(3);
        linearLayout3.addView(textView2, new RelativeLayout.LayoutParams(-1, -2));
        this.r = new TextView(this.d);
        this.r.setText(c.bD.E);
        this.r.setTextSize(22.0f);
        this.r.setTextColor(h.a(-1, -730710, -730710, -6745));
        this.r.setGravity(17);
        this.r.setOnClickListener(this.s);
        int i3 = a.n;
        this.r.setBackgroundDrawable(this.c.a(2008, -1, -1));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, i3);
        layoutParams4.addRule(3, linearLayout3.getId());
        layoutParams4.addRule(12, -1);
        layoutParams4.bottomMargin = a.b;
        layoutParams4.topMargin = a.b;
        int i4 = a.d;
        layoutParams4.rightMargin = i4;
        layoutParams4.leftMargin = i4;
        this.m.addView(this.r, layoutParams4);
    }

    public final void k() {
        r();
    }
}
