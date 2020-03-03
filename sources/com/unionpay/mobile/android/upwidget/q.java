package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.o;
import org.json.JSONObject;

public final class q extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private String f9731a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private RelativeLayout k;
    private Button l;
    private boolean m;
    private Context n;
    private float o;
    private View.OnClickListener p;
    private View.OnClickListener q;
    private String r;
    private TextView s;
    private String t;
    private a u;

    public interface a {
        void a(String str, String str2);

        void a(String str, boolean z);
    }

    public q(Context context, JSONObject jSONObject, String str) {
        this(context, jSONObject, str, (byte) 0);
    }

    private q(Context context, JSONObject jSONObject, String str, byte b2) {
        super(context);
        this.f9731a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = 0.0f;
        this.p = new r(this);
        this.q = new s(this);
        this.n = context;
        this.o = 16.0f;
        this.t = str;
        this.f9731a = j.a(jSONObject, "name");
        this.b = j.a(jSONObject, "type");
        this.c = j.a(jSONObject, "value");
        this.d = j.a(jSONObject, "label");
        this.e = j.a(jSONObject, "href_label");
        this.f = j.a(jSONObject, "href_url");
        this.g = j.a(jSONObject, "href_title");
        this.h = j.a(jSONObject, "checked");
        this.i = j.a(jSONObject, "required");
        this.j = j.a(jSONObject, "error_info");
        this.r = j.a(jSONObject, "ckb_style");
        this.k = new RelativeLayout(this.n);
        addView(this.k, new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
        if (a(this.d)) {
            this.s = new TextView(this.n);
            this.s.setId(this.s.hashCode());
            this.s.setText(this.d);
            this.s.setTextSize(this.o);
            this.s.setTextColor(-16777216);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(15, -1);
            this.k.addView(this.s, layoutParams);
        }
        this.l = new Button(this.n);
        this.l.setId(this.l.hashCode());
        if (!a(this.h) || !this.h.equalsIgnoreCase("0")) {
            this.m = false;
        } else {
            this.m = true;
        }
        this.l.setOnClickListener(this.p);
        c();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(g.a(this.n, 60.0f), g.a(this.n, 34.0f));
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        this.k.addView(this.l, layoutParams2);
        if (this.u != null) {
            this.u.a(this.b, this.m);
        }
        if (a(this.e) && a(this.f)) {
            TextView textView = new TextView(this.n);
            textView.setText(Html.fromHtml(this.e));
            textView.setTextSize(b.l);
            textView.setOnClickListener(this.q);
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(1, this.s.getId());
            layoutParams3.addRule(15, -1);
            layoutParams3.leftMargin = g.a(this.n, 10.0f);
            this.k.addView(textView, layoutParams3);
        }
    }

    static /* synthetic */ void a(q qVar) {
        qVar.m = !qVar.m;
        String str = qVar.m ? Constants.Name.Y : "n";
        String[] strArr = o.g;
        new String[1][0] = str;
        if (qVar.u != null) {
            qVar.u.a(qVar.b, qVar.m);
        }
        qVar.c();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    static /* synthetic */ void b(q qVar) {
        if (qVar.u != null) {
            qVar.u.a(qVar.e, qVar.f);
        }
    }

    private void c() {
        if (this.l != null) {
            this.l.setBackgroundDrawable(c.a(this.n).a(this.m ? 1010 : 1009, g.a(this.n, 60.0f), g.a(this.n, 34.0f)));
        }
    }

    public final void a() {
        if (this.s != null) {
            this.s.setTextColor(-13421773);
        }
    }

    public final void a(float f2) {
        if (this.s != null) {
            this.s.setTextSize(f2);
        }
    }

    public final void a(a aVar) {
        this.u = aVar;
    }

    public final void a(boolean z) {
        this.m = z;
        c();
    }

    public final boolean b() {
        if (!a(this.i) || !this.i.equalsIgnoreCase("0")) {
            return true;
        }
        return this.m;
    }
}
