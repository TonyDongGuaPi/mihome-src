package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.widgets.as;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bd extends b {
    private TextView r = null;
    private View.OnClickListener s = new be(this);
    private a t;

    public class a extends LinearLayout {
        private Context b;

        public a(Context context) {
            super(context);
            this.b = context;
            setOrientation(1);
        }

        private void a(JSONArray jSONArray) {
            if (jSONArray != null && jSONArray.length() > 0) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                LinearLayout linearLayout = new LinearLayout(bd.this.d);
                linearLayout.setOrientation(1);
                linearLayout.setBackgroundColor(-1);
                addView(linearLayout, layoutParams);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, b.n);
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String a2 = j.a(jSONObject, "label");
                        String a3 = j.a(jSONObject, "url");
                        LinearLayout linearLayout2 = new LinearLayout(bd.this.d);
                        linearLayout2.setBackgroundColor(-3419943);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 1);
                        if (i != 0) {
                            layoutParams3.leftMargin = g.a(bd.this.d, 12.0f);
                        }
                        linearLayout.addView(linearLayout2, layoutParams3);
                        Context context = this.b;
                        RelativeLayout relativeLayout = new RelativeLayout(this.b);
                        relativeLayout.setBackgroundDrawable(bd.this.c.a(2014, -1, -1));
                        relativeLayout.setOnClickListener(new bg(this, i, a2, a3));
                        TextView textView = new TextView(context);
                        textView.setText(a2);
                        textView.setTextSize(b.k);
                        textView.setTextColor(-13421773);
                        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                        layoutParams4.addRule(15, -1);
                        layoutParams4.addRule(9, -1);
                        layoutParams4.leftMargin = g.a(context, 12.0f);
                        relativeLayout.addView(textView, layoutParams4);
                        int a4 = g.a(context, 20.0f);
                        Drawable a5 = bd.this.c.a(1002, -1, -1);
                        ImageView imageView = new ImageView(context);
                        imageView.setBackgroundDrawable(a5);
                        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(a4, a4);
                        layoutParams5.addRule(15, -1);
                        layoutParams5.addRule(11, -1);
                        layoutParams5.rightMargin = g.a(context, 12.0f);
                        relativeLayout.addView(imageView, layoutParams5);
                        linearLayout.addView(relativeLayout, layoutParams2);
                        if (i == jSONArray.length() - 1) {
                            LinearLayout linearLayout3 = new LinearLayout(bd.this.d);
                            linearLayout3.setBackgroundColor(-3419943);
                            linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, 1));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public final void a(JSONObject jSONObject) {
            removeAllViews();
            String a2 = j.a(jSONObject, "label");
            if (bd.b(a2)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.leftMargin = g.a(bd.this.d, 12.0f);
                layoutParams.bottomMargin = com.unionpay.mobile.android.global.a.f;
                TextView textView = new TextView(this.b);
                textView.setText(a2);
                textView.setTextSize(b.k);
                textView.setTextColor(-25009);
                addView(textView, layoutParams);
            }
            a(j.d(jSONObject, "options"));
        }
    }

    public bd(Context context) {
        super(context);
        e();
        this.q = "result";
        this.f9608a.aq = null;
        if (this.f9608a.aV) {
            this.e.c("bingopromotion", "");
        }
    }

    /* access modifiers changed from: private */
    public void r() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9608a.aQ);
        String sb2 = sb.toString();
        k.c("functionEx", sb2);
        if (this.f9608a.aQ == l.b.intValue() || this.f9608a.aQ == l.c.intValue()) {
            PreferenceUtils.c(this.d, sb2);
        }
        this.f9608a.I.f = "success";
        j();
    }

    public final void a(JSONObject jSONObject) {
        JSONObject c = j.c(jSONObject, "luck_draw");
        if (c != null) {
            this.t.setVisibility(0);
            this.t.a(c);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(getContext(), this.f9608a.A, this);
        ayVar.a();
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.m.invalidate();
        if (this.o != null) {
            this.o.setBackgroundColor(-1052684);
        }
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setBackgroundColor(-1052684);
        relativeLayout.setId(relativeLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.d;
        this.m.addView(relativeLayout, layoutParams);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.d);
        relativeLayout2.setId(relativeLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d;
        relativeLayout.addView(relativeLayout2, layoutParams2);
        int i = com.unionpay.mobile.android.global.a.n;
        Drawable a2 = this.c.a(2008, -1, -1);
        this.r = new TextView(this.d);
        this.r.setId(this.r.hashCode());
        this.r.setText(c.bD.E);
        this.r.setTextSize(b.i);
        this.r.setTextColor(o());
        this.r.setGravity(17);
        this.r.setOnClickListener(this.s);
        this.r.setBackgroundDrawable(a2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i);
        int a3 = g.a(this.d, 12.0f);
        layoutParams3.rightMargin = a3;
        layoutParams3.leftMargin = a3;
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        relativeLayout2.addView(this.r, layoutParams3);
        this.t = new a(this.d);
        this.t.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(3, relativeLayout2.getId());
        layoutParams4.topMargin = g.a(this.d, 20.0f);
        relativeLayout.addView(this.t, layoutParams4);
    }

    /* access modifiers changed from: protected */
    public final void f() {
        SpannableString spannableString;
        String str;
        String str2;
        String[] split;
        int i = 2;
        if (this.f9608a.H != null) {
            LinearLayout linearLayout = new LinearLayout(this.d);
            linearLayout.setOrientation(1);
            int a2 = g.a(this.d, 10.0f);
            if (!TextUtils.isEmpty(this.f9608a.B)) {
                TextView textView = new TextView(this.d);
                textView.setText(this.f9608a.B);
                textView.setTextSize(24.0f);
                textView.setTextColor(-15365480);
                textView.setGravity(1);
                textView.setPadding(0, com.unionpay.mobile.android.global.a.d, 0, 0);
                textView.getPaint().setFakeBoldText(true);
                linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
                LinearLayout linearLayout2 = new LinearLayout(this.d);
                linearLayout2.setOrientation(0);
                linearLayout2.setBackgroundColor(-6958338);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
                int i2 = com.unionpay.mobile.android.global.a.d;
                layoutParams.bottomMargin = i2;
                layoutParams.topMargin = i2;
                linearLayout.addView(linearLayout2, layoutParams);
                this.l.addView(linearLayout);
            }
            JSONArray jSONArray = this.f9608a.H;
            if (jSONArray != null) {
                if (jSONArray.length() < 2) {
                    i = jSONArray.length();
                }
                LinearLayout a3 = bh.a(this.d, jSONArray, 0, i);
                a3.setBackgroundColor(0);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.rightMargin = a2;
                layoutParams2.leftMargin = a2;
                linearLayout.addView(a3, layoutParams2);
                LinearLayout a4 = bh.a(this.d, jSONArray, i, jSONArray.length());
                a4.setBackgroundColor(0);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.rightMargin = a2;
                layoutParams3.leftMargin = a2;
                this.l.addView(a4, layoutParams3);
            }
            as asVar = new as(this.d);
            asVar.setId(asVar.hashCode());
            this.l.setOnClickListener(new bf(this));
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.bottomMargin = 0;
            this.l.addView(asVar, layoutParams4);
            return;
        }
        int a5 = g.a(this.d, 10.0f);
        LinearLayout linearLayout3 = new LinearLayout(this.d);
        linearLayout3.setPadding(a5, a5, a5, a5);
        linearLayout3.setOrientation(1);
        linearLayout3.setBackgroundColor(-1);
        this.l.addView(linearLayout3);
        LinearLayout linearLayout4 = new LinearLayout(this.d);
        linearLayout4.setOrientation(0);
        linearLayout3.addView(linearLayout4);
        int a6 = g.a(this.d, 25.0f);
        ImageView imageView = new ImageView(this.d);
        imageView.setBackgroundDrawable(this.c.a(1035, -1, -1));
        linearLayout4.addView(imageView, new LinearLayout.LayoutParams(a6, a6));
        LinearLayout linearLayout5 = new LinearLayout(this.d);
        linearLayout5.setOrientation(1);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.leftMargin = a5;
        linearLayout4.addView(linearLayout5, layoutParams5);
        String str3 = this.f9608a.aR;
        if (!i.b(str3)) {
            int indexOf = str3.indexOf(60);
            String substring = -1 != indexOf ? str3.substring(0, indexOf) : null;
            int indexOf2 = str3.indexOf(62) + 1;
            String substring2 = -1 != indexOf2 ? str3.substring(indexOf2) : null;
            int i3 = indexOf + 1;
            int i4 = indexOf2 - 1;
            if (!(-1 == indexOf || i3 >= i4 || indexOf2 == -1)) {
                String substring3 = str3.substring(i3, i4);
                if (!i.b(substring3) && (split = substring3.split("#")) != null && split.length == 2) {
                    str = split[0];
                    str2 = split[1];
                    spannableString = i.a(substring, str, str2, substring2);
                }
            }
            str2 = null;
            str = null;
            spannableString = i.a(substring, str, str2, substring2);
        } else {
            spannableString = null;
        }
        if (spannableString != null) {
            TextView textView2 = new TextView(this.d);
            textView2.setTextSize(20.0f);
            textView2.setText(spannableString);
            linearLayout5.addView(textView2);
        }
        if (b(this.f9608a.aS)) {
            TextView textView3 = new TextView(this.d);
            textView3.setTextSize(b.l);
            textView3.setText(this.f9608a.aS);
            textView3.setTextColor(-10066330);
            linearLayout5.addView(textView3);
        }
        if (b(this.f9608a.aT)) {
            TextView textView4 = new TextView(this.d);
            textView4.setTextSize(b.l);
            textView4.setTextColor(-10066330);
            textView4.setText(this.f9608a.aT);
            linearLayout5.addView(textView4);
        }
        int a7 = g.a(this.d, 5.0f);
        LinearLayout linearLayout6 = new LinearLayout(this.d);
        linearLayout6.setOrientation(1);
        linearLayout6.setBackgroundColor(-1052684);
        linearLayout6.setPadding(a7, a7, a7, a7);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.topMargin = g.a(this.d, 10.0f);
        linearLayout3.addView(linearLayout6, layoutParams6);
        linearLayout6.setVisibility(8);
        if ("0".equals(this.f9608a.aW) && b(this.f9608a.aX)) {
            TextView textView5 = new TextView(this.d);
            textView5.setTextSize(b.l);
            textView5.setText(this.f9608a.aX);
            linearLayout6.addView(textView5);
            linearLayout6.setVisibility(0);
        }
        Drawable a8 = this.c.a(1026, -1, -1);
        LinearLayout linearLayout7 = new LinearLayout(this.d);
        if (a8 != null) {
            linearLayout7.setBackgroundDrawable(a8);
        }
        this.l.addView(linearLayout7, new LinearLayout.LayoutParams(-1, g.a(this.d, 2.0f)));
    }

    public final void k() {
        r();
    }
}
