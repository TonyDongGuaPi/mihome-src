package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.c;
import com.unionpay.mobile.android.upwidget.g;
import com.unionpay.mobile.android.utils.k;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b extends AbstractMethod {
    private TextView A;
    private boolean B = false;
    private int C = l.b.intValue();
    /* access modifiers changed from: private */
    public JSONObject g;
    private JSONArray h;
    private boolean i;
    private com.unionpay.mobile.android.upviews.a j;
    private List<Map<String, Object>> k;
    private Drawable l;
    /* access modifiers changed from: private */
    public PopupWindow m;
    private g n;
    private c o;
    private String p;
    private final View.OnClickListener q = new c(this);
    private final View.OnClickListener r = new d(this);
    private final AdapterView.OnItemClickListener s = new e(this);
    private a t;
    private int u = -1;
    private int v = 1;
    private C0079b w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    private class a {

        /* renamed from: a  reason: collision with root package name */
        View f9750a;
        TextView b;

        private a() {
        }

        /* synthetic */ a(b bVar, byte b2) {
            this();
        }
    }

    /* renamed from: com.unionpay.mobile.android.views.order.b$b  reason: collision with other inner class name */
    public interface C0079b {
        int a();

        int a(int i);

        int b(int i);
    }

    public b(Context context, List<Map<String, Object>> list, String str) {
        super(context);
        this.k = list;
        this.p = str;
        this.o = new c(this.b, this.k, com.unionpay.mobile.android.languages.c.bD.bh, this.p, com.unionpay.mobile.android.languages.c.bD.bi, this.v, 0);
        this.o.a(this.q);
        this.n = new g(this.b, this.o);
        this.n.a(this.s);
        this.n.a(this.r);
    }

    static /* synthetic */ void a(b bVar, View view) {
        if (bVar.m == null) {
            bVar.m = new PopupWindow(bVar.n, -1, -1, true);
            bVar.m.setBackgroundDrawable(new ColorDrawable(-1342177280));
            bVar.m.update();
        }
        bVar.m.showAtLocation(view, 80, 0, 0);
    }

    /* access modifiers changed from: private */
    public final void c(int i2) {
        int c = i2 - this.o.c();
        if (i2 != 0) {
            if (this.k != null && i2 == this.k.size() + this.o.c()) {
                k.a("direct", " new ");
                if (this.w != null) {
                    this.w.a();
                }
            } else if (!this.o.b() || !this.o.c(i2)) {
                this.v = i2;
                this.o.a(this.v);
                k.a("direct", " pay with " + i2);
                if (this.t != null) {
                    this.t.b.setText(this.o.b(this.v));
                }
                if (this.w != null) {
                    this.w.b(c);
                }
            } else {
                k.a("direct", " delete " + i2);
                i();
                if (this.w != null) {
                    this.u = c;
                    this.w.a(c);
                }
            }
            this.m.dismiss();
        }
    }

    private boolean h() {
        return this.i || this.k == null || this.k.size() == 0;
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.o != null) {
            this.o.a();
            String str = this.o.b() ? com.unionpay.mobile.android.languages.c.bD.bj : com.unionpay.mobile.android.languages.c.bD.bh;
            String str2 = this.o.b() ? com.unionpay.mobile.android.languages.c.bD.bk : com.unionpay.mobile.android.languages.c.bD.bi;
            this.o.a(str);
            this.o.b(str2);
            this.o.notifyDataSetChanged();
        }
    }

    public final b a(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        this.x = drawable;
        this.y = drawable2;
        this.z = drawable3;
        return this;
    }

    public final b a(C0079b bVar) {
        this.w = bVar;
        return this;
    }

    public final b a(JSONArray jSONArray) {
        this.h = jSONArray;
        return this;
    }

    public final b a(JSONObject jSONObject) {
        this.g = jSONObject;
        if (this.A != null) {
            this.A.setText(Html.fromHtml(a(this.g, "label")));
        }
        return this;
    }

    public final void a(int i2) {
        int size = this.k != null ? this.k.size() : 0;
        if (size > 0 && this.u >= 0 && this.u < size) {
            this.k.remove(this.u);
            this.u = -1;
            this.o.notifyDataSetChanged();
        }
        c(i2 + this.o.c());
    }

    public final void a(RelativeLayout relativeLayout) {
        TextView textView = new TextView(this.b);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-13421773);
        textView.setText(this.c);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.g.a(this.b, 10.0f);
        relativeLayout.addView(textView, layoutParams);
        if (TextUtils.isEmpty(this.c)) {
            relativeLayout.setVisibility(8);
        }
        if (h()) {
            String a2 = a(this.g, "label");
            this.A = new TextView(this.b);
            this.A.setOnClickListener(new f(this));
            if (!a(a2)) {
                this.A.setText(Html.fromHtml(a2));
            }
            a(this.A);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(11, -1);
            layoutParams2.rightMargin = com.unionpay.mobile.android.utils.g.a(this.b, 10.0f);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(this.A, layoutParams2);
        }
    }

    public final int b() {
        return this.C;
    }

    public final b b(Drawable drawable) {
        this.l = drawable;
        return this;
    }

    public final b b(boolean z2) {
        this.B = z2;
        return this;
    }

    public final void b(int i2) {
        this.C = i2;
    }

    public final void b(RelativeLayout relativeLayout) {
        if (h() || this.B) {
            if (this.B) {
                g();
            }
            this.j = new com.unionpay.mobile.android.upviews.a(this.b, this.h, this, "bankpay");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            relativeLayout.addView(this.j, layoutParams);
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        relativeLayout.addView(linearLayout, layoutParams2);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.b);
        relativeLayout2.setId(relativeLayout2.hashCode());
        relativeLayout2.setBackgroundDrawable(this.l);
        relativeLayout2.setOnClickListener(new g(this));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
        layoutParams3.addRule(3, linearLayout.getId());
        relativeLayout.addView(relativeLayout2, layoutParams3);
        ImageView imageView = new ImageView(this.b);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.b).a(1002, -1, -1));
        int a2 = com.unionpay.mobile.android.utils.g.a(this.b, 15.0f);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams4.addRule(11, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.rightMargin = com.unionpay.mobile.android.utils.g.a(this.b, 10.0f);
        relativeLayout2.addView(imageView, layoutParams4);
        TextView textView = new TextView(this.b);
        textView.setText(this.o.b(this.v));
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-10066330);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(9, -1);
        layoutParams5.addRule(15, -1);
        layoutParams5.addRule(0, imageView.getId());
        layoutParams5.leftMargin = com.unionpay.mobile.android.utils.g.a(this.b, 10.0f);
        relativeLayout2.addView(textView, layoutParams5);
        LinearLayout linearLayout2 = new LinearLayout(this.b);
        linearLayout2.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams6.bottomMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams6.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(linearLayout2, layoutParams6);
        this.t = new a(this, (byte) 0);
        this.t.f9750a = relativeLayout2;
        this.t.b = textView;
    }

    public final void b(String str) {
        if (this.t != null) {
            this.t.b.setText(str);
        }
    }

    public final a.C0077a c() {
        if (this.j != null) {
            return this.j.b();
        }
        return null;
    }

    public final void c(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(8);
    }

    public final int d() {
        return this.v - this.o.c();
    }

    public final b d(String str) {
        this.c = str;
        return this;
    }

    public final b e(String str) {
        this.d = str;
        return this;
    }

    public final String e() {
        return this.d;
    }

    public final void f(String str) {
        this.o.b(str);
    }

    public final boolean f() {
        return this.j == null || this.j.e();
    }

    public final void r() {
    }
}
