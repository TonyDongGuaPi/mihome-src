package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.o;
import org.json.JSONObject;

public final class a extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private String f9714a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private Button j;
    private boolean k;
    private Context l;
    private float m;
    private View.OnClickListener n;
    private String o;
    private TextView p;
    private String q;

    public a(Context context, JSONObject jSONObject, View.OnClickListener onClickListener, String str) {
        this(context, jSONObject, onClickListener, str, (byte) 0);
    }

    private a(Context context, JSONObject jSONObject, View.OnClickListener onClickListener, String str, byte b2) {
        super(context);
        this.f9714a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = 0.0f;
        this.n = new b(this);
        this.l = context;
        this.m = 16.0f;
        this.q = str;
        this.f9714a = j.a(jSONObject, "name");
        this.b = j.a(jSONObject, "value");
        this.c = j.a(jSONObject, "label");
        this.d = j.a(jSONObject, "href_label");
        this.e = j.a(jSONObject, "href_url");
        this.f = j.a(jSONObject, "href_title");
        this.g = j.a(jSONObject, "checked");
        this.h = j.a(jSONObject, "required");
        this.i = j.a(jSONObject, "error_info");
        this.o = j.a(jSONObject, "ckb_style");
        this.j = new Button(this.l);
        if (!a(this.g) || !this.g.equalsIgnoreCase("0")) {
            this.k = false;
        } else {
            this.k = true;
        }
        this.j.setOnClickListener(this.n);
        g();
        f();
        int a2 = g.a(this.l, 20.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a2, a2);
        layoutParams.gravity = 16;
        addView(this.j, layoutParams);
        if (a(this.c)) {
            this.p = new TextView(this.l);
            this.p.setText(this.c);
            this.p.setTextSize(this.m);
            this.p.setTextColor(-16777216);
            this.p.setOnClickListener(this.n);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 16;
            layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
            addView(this.p, layoutParams2);
        }
        if (a(this.d) && a(this.e)) {
            TextView textView = new TextView(this.l);
            textView.setText(Html.fromHtml(this.d));
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
            String.format("<u>%s</u>", new Object[]{this.d});
            textView.setTextSize(this.m);
            textView.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 16;
            addView(textView, layoutParams3);
        }
    }

    static /* synthetic */ void a(a aVar) {
        aVar.k = !aVar.k;
        String str = aVar.k ? Constants.Name.Y : "n";
        String[] strArr = o.g;
        new String[1][0] = str;
        aVar.g();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    private boolean f() {
        return "small".equalsIgnoreCase(this.o);
    }

    private void g() {
        if (this.j != null) {
            int i2 = this.k ? 1008 : 1007;
            int a2 = f() ? g.a(this.l, 15.0f) : com.unionpay.mobile.android.global.a.w;
            this.j.setBackgroundDrawable(c.a(this.l).a(i2, a2, a2));
        }
    }

    public final String a() {
        return String.format("\"%s\":\"%s\"", new Object[]{this.f9714a, this.k ? this.b : ""});
    }

    public final String b() {
        return this.i;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final boolean e() {
        if (!a(this.h) || !this.h.equalsIgnoreCase("0")) {
            return true;
        }
        return this.k;
    }
}
