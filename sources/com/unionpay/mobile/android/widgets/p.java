package com.unionpay.mobile.android.widgets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.mi.global.shop.model.Tags;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upwidget.e;
import com.unionpay.mobile.android.upwidget.q;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.xiaomi.payment.data.MibiConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class p extends z {

    /* renamed from: a  reason: collision with root package name */
    private final View.OnClickListener f9800a = new q(this);
    private final AdapterView.OnItemClickListener b = new r(this);
    private JSONArray c = j.d(this.n, "new_instalments");
    private List<Map<String, Object>> o;
    /* access modifiers changed from: private */
    public AlertDialog p;
    /* access modifiers changed from: private */
    public PopupWindow q;
    private e r;
    private int s = 1;
    private TextView t;
    private q u;
    private TextView v;
    private String w;
    private RelativeLayout x;
    private boolean y = false;
    private boolean z = true;

    public p(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.w = j.a(jSONObject, "label");
        if (a(this.w)) {
            this.w = c.bD.bg;
        }
        this.o = b(this.c);
        this.r = new e(this.d, this.o, "");
        a(this.m);
    }

    private String a(int i, String str) {
        Object b2 = j.b(this.c, i);
        return b2 != null ? j.a((JSONObject) b2, str) : "";
    }

    private JSONObject a(String str, String str2, String str3) {
        JSONObject c2;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("label", str2);
            jSONObject.put("checked", str3);
            jSONObject.put("ckb_style", "small");
            jSONObject.put("required", "0");
            if ("instalment".equals(str) && (c2 = j.c(this.n, "url")) != null) {
                jSONObject.put("href_label", j.a(c2, "label"));
                jSONObject.put("href_url", j.a(c2, "href"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        this.s = i;
        int a2 = i - this.r.a();
        this.r.a(this.s);
        if (this.t != null) {
            this.t.setText(a(a2, "label"));
        }
    }

    private void a(RelativeLayout relativeLayout) {
        Drawable a2 = com.unionpay.mobile.android.resource.c.a(this.d).a(2014, -1, -1);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        String a3 = j.a(this.n, "type");
        if ("instalment".equals(a3)) {
            layoutParams.leftMargin = g.a(this.d, 10.0f);
        }
        relativeLayout.addView(linearLayout, layoutParams);
        this.x = new RelativeLayout(this.d);
        this.x.setId(this.x.hashCode());
        this.x.setBackgroundDrawable(a2);
        this.x.setOnClickListener(new s(this));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, a.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(3, linearLayout.getId());
        relativeLayout.addView(this.x, layoutParams2);
        ImageView imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.d).a(1002, -1, -1));
        int a4 = g.a(this.d, 15.0f);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a4, a4);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.rightMargin = g.a(this.d, 10.0f);
        this.x.addView(imageView, layoutParams3);
        this.t = new TextView(this.d);
        this.t.setTextSize(b.k);
        this.t.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.t.setSingleLine(true);
        this.t.setTextColor(-10066330);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(0, imageView.getId());
        layoutParams4.leftMargin = g.a(this.d, 10.0f);
        layoutParams4.rightMargin = layoutParams4.leftMargin;
        this.x.addView(this.t, layoutParams4);
        if (!"instalment".equals(a3)) {
            LinearLayout linearLayout2 = new LinearLayout(this.d);
            linearLayout2.setBackgroundColor(-3419943);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, 1);
            layoutParams5.bottomMargin = a.f;
            layoutParams5.addRule(3, this.x.getId());
            relativeLayout.addView(linearLayout2, layoutParams5);
        }
        b(g());
        a(this.r.a());
    }

    static /* synthetic */ Dialog b(p pVar) {
        if (pVar.p == null || pVar.z) {
            pVar.z = false;
            pVar.p = new AlertDialog.Builder(pVar.d).setInverseBackgroundForced(false).create();
            AlertDialog alertDialog = pVar.p;
            LinearLayout linearLayout = new LinearLayout(pVar.d);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            int a2 = g.a(pVar.d, 1.0f);
            RelativeLayout relativeLayout = new RelativeLayout(pVar.d);
            int i = b.g;
            relativeLayout.setPadding(i, i, i, i);
            linearLayout.addView(relativeLayout);
            LinearLayout linearLayout2 = new LinearLayout(pVar.d);
            linearLayout2.setBackgroundColor(-3419943);
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, 1));
            TextView textView = new TextView(pVar.d);
            textView.setText(pVar.w);
            textView.setTextSize(b.i);
            textView.setTextColor(-13421773);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            relativeLayout.addView(textView, layoutParams);
            relativeLayout.setBackgroundColor(-986892);
            new LinearLayout.LayoutParams(-1, -2);
            ListView listView = new ListView(pVar.d);
            listView.setDivider((Drawable) null);
            listView.setAdapter(pVar.r);
            listView.setDividerHeight(a2);
            listView.setOnItemClickListener(new t(pVar));
            new LinearLayout.LayoutParams(-1, -2);
            linearLayout.addView(listView);
            alertDialog.setView(linearLayout, -1, -1, -1, -1);
            pVar.p.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        return pVar.p;
    }

    private List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            hashMap.put("label", a(i, "label"));
            hashMap.put("style", a(i, "rel_value_style"));
            String[] split = a(i, "rel_value").split(PaymentOptionsDecoder.pipeSeparator);
            ArrayList arrayList2 = new ArrayList(split.length);
            ArrayList arrayList3 = new ArrayList(split.length);
            for (String split2 : split) {
                String[] split3 = split2.split(":");
                arrayList2.add(split3[0]);
                arrayList3.add(split3[1]);
            }
            hashMap.put(QuickTimeAtomTypes.h, arrayList2);
            hashMap.put(MibiConstants.gf, arrayList3);
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public final String a() {
        String a2 = a(this.s - this.r.a(), "value");
        if (this.u != null && !this.u.b()) {
            a2 = null;
        }
        k.c("uppay", n() + " : " + a2);
        return a2;
    }

    public final void a(q.a aVar) {
        this.u.a(aVar);
    }

    public final void a(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            this.z = true;
            this.c = jSONArray;
            this.o = b(jSONArray);
            this.r = new e(this.d, this.o, "");
            a(this.m);
        }
    }

    public final void a(boolean z2) {
        this.y = z2;
    }

    /* access modifiers changed from: protected */
    public final boolean a(LinearLayout linearLayout, String str) {
        if (a(str)) {
            return true;
        }
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setBackgroundColor(-1);
        linearLayout2.setOrientation(1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, a.n));
        String a2 = j.a(this.n, "type");
        if ("instalment".equals(a2)) {
            LinearLayout linearLayout3 = new LinearLayout(this.d);
            linearLayout3.setId(linearLayout3.hashCode());
            linearLayout3.setBackgroundColor(-3419943);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
            layoutParams.leftMargin = g.a(this.d, 10.0f);
            linearLayout2.addView(linearLayout3, layoutParams);
        }
        JSONObject a3 = a(a2, str, j.a(this.n, "checked"));
        Context context = this.d;
        this.u = new q(context, a3, s() + "_agree_installment");
        this.u.a();
        this.u.a(b.k);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, a.n);
        layoutParams2.gravity = 16;
        int a4 = g.a(this.d, 10.0f);
        layoutParams2.rightMargin = a4;
        layoutParams2.leftMargin = a4;
        linearLayout2.addView(this.u, layoutParams2);
        return true;
    }

    public final void b(boolean z2) {
        this.u.a(z2);
        if (!z2) {
            this.y = z2;
        }
        int i = (!this.y || !z2) ? 8 : 0;
        this.m.setVisibility(i);
        if (this.v == null) {
            return;
        }
        if (TextUtils.isEmpty(this.v.getText().toString())) {
            this.v.setVisibility(8);
        } else {
            this.v.setVisibility(i);
        }
    }

    public final boolean b() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean b_() {
        this.v = new TextView(this.d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = g.a(this.d, 10.0f);
        int a2 = g.a(this.d, 5.0f);
        layoutParams.bottomMargin = a2;
        layoutParams.topMargin = a2;
        this.v.setTextSize(b.k);
        addView(this.v, layoutParams);
        this.v.setVisibility(8);
        return true;
    }

    public final boolean c() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_select_installment";
    }

    public final boolean f() {
        String a2 = a(this.s - this.r.a(), Tags.MiHome.AVAILABLE);
        return TextUtils.isEmpty(a2) || !"1".equals(a2);
    }

    public final boolean g() {
        if (this.u != null) {
            return this.u.b();
        }
        return true;
    }
}
