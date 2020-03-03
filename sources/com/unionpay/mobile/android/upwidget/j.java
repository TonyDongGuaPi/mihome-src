package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.libra.Color;
import com.mi.global.shop.model.Tags;
import com.mi.mistatistic.sdk.data.EventData;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class j extends LinearLayout {
    private View.OnClickListener A = new m(this);
    private View.OnClickListener B = new n(this);
    private View.OnClickListener C = new o(this);
    private View.OnClickListener D = new p(this);

    /* renamed from: a  reason: collision with root package name */
    private Context f9723a;
    private JSONArray b;
    private int c;
    /* access modifiers changed from: private */
    public int d;
    /* access modifiers changed from: private */
    public boolean e = true;
    /* access modifiers changed from: private */
    public a[] f;
    /* access modifiers changed from: private */
    public ArrayList<Object> g;
    private LinearLayout h;
    private HorizontalScrollView i;
    private k j = null;
    /* access modifiers changed from: private */
    public ad k = null;
    /* access modifiers changed from: private */
    public TextView l = null;
    /* access modifiers changed from: private */
    public TextView m = null;
    /* access modifiers changed from: private */
    public int n = 0;
    /* access modifiers changed from: private */
    public int o = 0;
    /* access modifiers changed from: private */
    public int p = -1;
    private int q;
    private int r;
    private String s;
    /* access modifiers changed from: private */
    public ArrayList<AdapterView.OnItemClickListener> t = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> u = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> v = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> w = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> x = new ArrayList<>();
    private AdapterView.OnItemClickListener y = new k(this);
    private View.OnClickListener z = new l(this);

    private class a {

        /* renamed from: a  reason: collision with root package name */
        TextView f9724a;
        LinearLayout b;
        View c;
        String d;

        private a() {
        }

        /* synthetic */ a(j jVar, byte b2) {
            this();
        }
    }

    public j(Context context, JSONArray jSONArray, int i2, String str) {
        super(context);
        this.f9723a = context;
        this.b = jSONArray;
        this.o = i2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.f9723a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.q = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        ((Activity) this.f9723a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
        this.r = displayMetrics2.heightPixels;
        this.s = str;
        if (this.b != null) {
            FrameLayout frameLayout = new FrameLayout(this.f9723a);
            RelativeLayout relativeLayout = new RelativeLayout(this.f9723a);
            frameLayout.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (this.r / 3) * 2);
            layoutParams.addRule(12, -1);
            LinearLayout linearLayout = new LinearLayout(this.f9723a);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            linearLayout.setId(linearLayout.hashCode());
            relativeLayout.addView(linearLayout, layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f9723a);
            layoutParams2.addRule(10, -1);
            layoutParams2.addRule(2, linearLayout.getId());
            relativeLayout.addView(linearLayout2, layoutParams2);
            linearLayout2.setOnClickListener(this.z);
            this.h = new LinearLayout(this.f9723a);
            this.h.setBackgroundColor(-1);
            this.h.setOrientation(0);
            linearLayout.addView(this.h, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, g.a(this.f9723a, 1.0f));
            LinearLayout linearLayout3 = new LinearLayout(this.f9723a);
            linearLayout3.setBackgroundColor(Color.d);
            linearLayout.addView(linearLayout3, layoutParams3);
            this.i = new HorizontalScrollView(this.f9723a);
            this.i.setBackgroundColor(-1052684);
            linearLayout.addView(this.i, new LinearLayout.LayoutParams(-2, -1));
            int a2 = g.a(this.f9723a, 40.0f);
            ImageView imageView = new ImageView(this.f9723a);
            imageView.setBackgroundDrawable(c.a(this.f9723a).a(PhotoshopDirectory.G, -1, -1));
            imageView.setOnClickListener(this.z);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(a2, a2);
            layoutParams4.gravity = 85;
            layoutParams4.rightMargin = g.a(this.f9723a, 10.0f);
            layoutParams4.bottomMargin = ((this.r / 3) * 2) - (a2 / 2);
            frameLayout.addView(imageView, layoutParams4);
            addView(frameLayout);
            a();
        }
    }

    private View a(LinearLayout linearLayout, JSONObject jSONObject) {
        c cVar = new c(this.f9723a, b(com.unionpay.mobile.android.utils.j.d(jSONObject, "options")), "", "", "", this.p, 1);
        this.g.add(cVar);
        ListView listView = new ListView(this.f9723a);
        listView.setDivider((Drawable) null);
        listView.setAdapter(cVar);
        listView.setOnItemClickListener(this.y);
        listView.setCacheColorHint(-1);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -1));
        return listView;
    }

    private static String a(JSONArray jSONArray, int i2, String str) {
        Object b2 = com.unionpay.mobile.android.utils.j.b(jSONArray, i2);
        return b2 != null ? com.unionpay.mobile.android.utils.j.a((JSONObject) b2, str) : "";
    }

    private void a() {
        View view;
        int length = this.b.length();
        this.f = new a[length];
        for (int i2 = 0; i2 < length; i2++) {
            this.f[i2] = new a(this, (byte) 0);
            if (this.f[i2].f9724a == null) {
                this.f[i2].f9724a = new TextView(this.f9723a);
            }
            if (this.f[i2].b == null) {
                this.f[i2].b = new LinearLayout(this.f9723a);
            }
            if (this.f[i2].c == null) {
                this.f[i2].c = new ListView(this.f9723a);
            }
            if (this.f[i2].d == null) {
                this.f[i2].d = "";
            }
        }
        this.g = new ArrayList<>(this.b.length());
        LinearLayout linearLayout = new LinearLayout(this.f9723a);
        linearLayout.setOrientation(0);
        this.i.addView(linearLayout, new LinearLayout.LayoutParams(-2, -1));
        for (int i3 = 0; i3 < this.b.length(); i3++) {
            JSONObject jSONObject = (JSONObject) com.unionpay.mobile.android.utils.j.b(this.b, i3);
            String a2 = com.unionpay.mobile.android.utils.j.a(jSONObject, "action");
            String a3 = com.unionpay.mobile.android.utils.j.a(jSONObject, "label");
            RelativeLayout relativeLayout = new RelativeLayout(this.f9723a);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            layoutParams.leftMargin = g.a(this.f9723a, 10.0f);
            this.h.addView(relativeLayout, layoutParams);
            int a4 = g.a(this.f9723a, 10.0f);
            TextView textView = new TextView(this.f9723a);
            textView.setText(a3);
            textView.setTextSize(b.k);
            textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            textView.setSingleLine(true);
            textView.setTextColor(-10066330);
            textView.setPadding(a4, 0, a4, 0);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(textView, layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(((int) textView.getPaint().measureText(a3)) + a4 + a4, g.a(this.f9723a, 2.0f));
            layoutParams3.addRule(12, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f9723a);
            linearLayout2.setBackgroundColor(-16730965);
            if (this.o != i3) {
                linearLayout2.setVisibility(8);
            }
            relativeLayout.addView(linearLayout2, layoutParams3);
            relativeLayout.setTag(Integer.valueOf(i3));
            relativeLayout.setOnClickListener(this.D);
            this.f[i3].f9724a = textView;
            this.f[i3].b = linearLayout2;
            this.f[i3].d = a2;
            if (this.o == i3) {
                this.p = 0;
            } else {
                this.p = -1;
            }
            String a5 = com.unionpay.mobile.android.utils.j.a(jSONObject, "type");
            if ("coupon".equals(a5)) {
                this.d = i3;
                view = b(linearLayout, jSONObject);
            } else {
                if ("point".equals(a5)) {
                    this.c = i3;
                } else if (!"upoint".equals(a5)) {
                    view = a(linearLayout, jSONObject);
                }
                view = c(linearLayout, jSONObject);
            }
            this.f[i3].c = view;
            this.f[i3].c.setVisibility(8);
        }
        a(this.o);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.f[this.o].b.setVisibility(8);
        this.f[this.o].f9724a.setTextColor(-16777216);
        this.f[this.o].c.setVisibility(8);
        this.f[i2].b.setVisibility(0);
        this.f[i2].f9724a.setTextColor(-16730965);
        this.f[i2].c.setVisibility(0);
        this.o = i2;
    }

    /* access modifiers changed from: private */
    public void a(LinearLayout linearLayout, boolean z2, String str, JSONObject jSONObject, c cVar) {
        linearLayout.removeAllViews();
        ListView listView = new ListView(this.f9723a);
        listView.setDivider((Drawable) null);
        listView.setAdapter(cVar);
        listView.setOnItemClickListener(this.y);
        this.g.add(cVar);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -2));
        if (cVar != null) {
            ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).gravity = 48;
        }
        if (z2) {
            int i2 = com.unionpay.mobile.android.global.a.p;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
            layoutParams.bottomMargin = g.a(this.f9723a, 12.0f);
            layoutParams.gravity = 17;
            linearLayout.addView(new ProgressBar(this.f9723a), layoutParams);
        }
        TextView textView = new TextView(this.f9723a);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
            textView.setTextSize(b.k);
            textView.setTextColor(-13421773);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 17;
            linearLayout.addView(textView, layoutParams2);
        }
        if (jSONObject != null) {
            TextView textView2 = new TextView(this.f9723a);
            textView2.setText(com.unionpay.mobile.android.utils.j.a(jSONObject, "label"));
            textView2.setTextSize(b.i);
            textView2.setTextColor(h.a(b.b, b.c, b.c, b.d));
            textView2.setGravity(17);
            textView2.setEnabled(true);
            int i3 = com.unionpay.mobile.android.global.a.n;
            textView2.setBackgroundDrawable(c.a(this.f9723a).a(2008, -1, -1));
            float measureText = textView.getPaint().measureText(str);
            textView2.setOnClickListener(this.A);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams((int) measureText, i3);
            int i4 = com.unionpay.mobile.android.global.a.f;
            layoutParams3.bottomMargin = i4;
            layoutParams3.topMargin = i4;
            int a2 = g.a(this.f9723a, 10.0f);
            layoutParams3.rightMargin = a2;
            layoutParams3.leftMargin = a2;
            linearLayout.addView(textView2, layoutParams3);
        }
    }

    private View b(LinearLayout linearLayout, JSONObject jSONObject) {
        JSONObject jSONObject2;
        RelativeLayout relativeLayout = new RelativeLayout(this.f9723a);
        ListView listView = new ListView(this.f9723a);
        JSONObject jSONObject3 = null;
        listView.setDivider((Drawable) null);
        listView.setAdapter((ListAdapter) null);
        this.g.add(listView);
        JSONArray d2 = com.unionpay.mobile.android.utils.j.d(jSONObject, "rules");
        if (d2 == null || d2.length() <= 0) {
            jSONObject2 = null;
        } else {
            JSONObject jSONObject4 = null;
            jSONObject2 = null;
            for (int i2 = 0; i2 < d2.length(); i2++) {
                Object b2 = com.unionpay.mobile.android.utils.j.b(d2, i2);
                if (b2 != null) {
                    JSONObject jSONObject5 = (JSONObject) b2;
                    String a2 = com.unionpay.mobile.android.utils.j.a(jSONObject5, "type");
                    if ("coupon_code".equals(a2)) {
                        jSONObject4 = jSONObject5;
                    } else if (EventData.b.equals(a2)) {
                        jSONObject2 = jSONObject5;
                    }
                }
            }
            jSONObject3 = jSONObject4;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(10, -1);
        relativeLayout.addView(listView, layoutParams);
        int i3 = com.unionpay.mobile.android.global.a.I - (com.unionpay.mobile.android.global.a.f * 4);
        this.j = new k(this.f9723a, i3, jSONObject3, this.s);
        this.j.setId(this.j.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams2.addRule(10, -1);
        int a3 = g.a(this.f9723a, 10.0f);
        layoutParams2.topMargin = a3;
        layoutParams2.rightMargin = a3;
        layoutParams2.leftMargin = a3;
        relativeLayout.addView(this.j, layoutParams2);
        this.k = new ad(this.f9723a, i3, jSONObject2, this.s);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams3.addRule(3, this.j.getId());
        int a4 = g.a(this.f9723a, 10.0f);
        layoutParams3.topMargin = a4;
        layoutParams3.rightMargin = a4;
        layoutParams3.leftMargin = a4;
        relativeLayout.addView(this.k, layoutParams3);
        this.l = new TextView(this.f9723a);
        this.l.setTextSize(b.k);
        this.l.setTextColor(-10066330);
        this.l.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams4.addRule(3, this.j.getId());
        int a5 = g.a(this.f9723a, 10.0f);
        layoutParams4.topMargin = a5;
        layoutParams4.rightMargin = a5;
        layoutParams4.leftMargin = a5;
        relativeLayout.addView(this.l, layoutParams4);
        JSONObject c2 = com.unionpay.mobile.android.utils.j.c(jSONObject, "use_button");
        LinearLayout linearLayout2 = new LinearLayout(this.f9723a);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, g.a(this.f9723a, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(this.f9723a);
        linearLayout3.setBackgroundColor(Color.d);
        linearLayout2.addView(linearLayout3, layoutParams5);
        this.m = new TextView(this.f9723a);
        this.m.setText(com.unionpay.mobile.android.utils.j.a(c2, "label"));
        this.m.setTextSize(b.i);
        this.m.setTextColor(h.a(b.b, b.c, b.c, b.d));
        this.m.setGravity(17);
        this.m.setEnabled(false);
        int i4 = com.unionpay.mobile.android.global.a.n;
        this.m.setBackgroundDrawable(c.a(this.f9723a).a(2008, -1, -1));
        this.m.setTag(Integer.valueOf(this.d));
        this.m.setOnClickListener(this.C);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, i4);
        int i5 = com.unionpay.mobile.android.global.a.f;
        layoutParams6.bottomMargin = i5;
        layoutParams6.topMargin = i5;
        int a6 = g.a(this.f9723a, 10.0f);
        layoutParams6.rightMargin = a6;
        layoutParams6.leftMargin = a6;
        linearLayout2.addView(this.m, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams7.addRule(12, -1);
        relativeLayout.addView(linearLayout2, layoutParams7);
        linearLayout.addView(relativeLayout, new LinearLayout.LayoutParams(this.q, -2));
        return relativeLayout;
    }

    private static List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            HashMap hashMap = new HashMap();
            hashMap.put("text1", a(jSONArray, i2, "label"));
            hashMap.put("text2", "");
            hashMap.put("editable", Boolean.FALSE);
            String a2 = a(jSONArray, i2, Tags.MiHome.AVAILABLE);
            boolean booleanValue = Boolean.TRUE.booleanValue();
            if (!TextUtils.isEmpty(a2) && "1".equals(a2)) {
                booleanValue = Boolean.FALSE.booleanValue();
            }
            hashMap.put(Tags.MiHome.AVAILABLE, Boolean.valueOf(booleanValue));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private View c(LinearLayout linearLayout, JSONObject jSONObject) {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout2 = new LinearLayout(this.f9723a);
        linearLayout2.setOrientation(1);
        String a2 = com.unionpay.mobile.android.utils.j.a(jSONObject, "tip");
        String a3 = com.unionpay.mobile.android.utils.j.a(jSONObject, "empty_info");
        JSONObject c2 = com.unionpay.mobile.android.utils.j.c(jSONObject, "button");
        if (c2 != null) {
            a(linearLayout2, false, a2, c2, (c) null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        } else if (!"upoint".equals(com.unionpay.mobile.android.utils.j.a(jSONObject, "type"))) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        } else if (a3 == null || TextUtils.isEmpty(a3)) {
            return a(linearLayout, jSONObject);
        } else {
            a(linearLayout2, false, a3, (JSONObject) null, (c) null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        }
        layoutParams.gravity = 17;
        linearLayout2.setGravity(17);
        linearLayout.addView(linearLayout2, layoutParams);
        return linearLayout2;
    }

    public final void a(View.OnClickListener onClickListener) {
        this.u.add(onClickListener);
    }

    public final void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.t.add(onItemClickListener);
    }

    public final void a(JSONArray jSONArray) {
        Object b2 = com.unionpay.mobile.android.utils.j.b(jSONArray, 0);
        if (b2 != null) {
            this.l.setText(com.unionpay.mobile.android.utils.j.a((JSONObject) b2, "label"));
            this.l.setVisibility(0);
            this.k.setVisibility(8);
        }
        this.m.setEnabled(true);
    }

    public final void a(JSONArray jSONArray, String str) {
        c cVar;
        if (jSONArray == null || jSONArray.length() <= 0) {
            cVar = null;
        } else {
            cVar = new c(this.f9723a, b(jSONArray), "", "", "", -1, 1);
            this.g.add(this.c, cVar);
        }
        a((LinearLayout) this.f[this.c].c, false, str, (JSONObject) null, cVar);
    }

    public final void b(View.OnClickListener onClickListener) {
        this.v.add(onClickListener);
    }

    public final void c(View.OnClickListener onClickListener) {
        this.w.add(onClickListener);
    }

    public final void d(View.OnClickListener onClickListener) {
        this.x.add(onClickListener);
    }

    public final void e(View.OnClickListener onClickListener) {
        if (this.j != null) {
            this.j.a(onClickListener);
            this.j.b(this.B);
        }
    }
}
