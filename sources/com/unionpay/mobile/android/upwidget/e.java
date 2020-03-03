package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.xiaomi.payment.data.MibiConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class e extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f9718a;
    private Context b;
    private String c = null;
    private String d = null;
    private int e = 1;
    private List<Map<String, Object>> f;
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> g = new ArrayList<>();
    private View.OnClickListener h = new f(this);

    public e(Context context, List<Map<String, Object>> list, String str) {
        this.b = context;
        this.f = list;
        this.c = str;
        this.e = 0;
        this.f9718a = c.a(this.b).a(1015, -1, -1);
    }

    private boolean b() {
        return this.c != null && !TextUtils.isEmpty(this.c);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        r3 = r2.f.get(r3).get(com.mi.global.shop.model.Tags.MiHome.AVAILABLE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(int r3) {
        /*
            r2 = this;
            int r0 = r2.a()
            int r3 = r3 - r0
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.f
            r1 = 1
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.f
            int r0 = r0.size()
            if (r3 != r0) goto L_0x0014
            return r1
        L_0x0014:
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.f
            java.lang.Object r3 = r0.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r0 = "available"
            java.lang.Object r3 = r3.get(r0)
            if (r3 == 0) goto L_0x002b
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            if (r0 != r3) goto L_0x002b
            r1 = 0
        L_0x002b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upwidget.e.b(int):boolean");
    }

    public final int a() {
        return b() ? 1 : 0;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final int getCount() {
        int i = 0;
        if (this.f == null) {
            return 0;
        }
        int size = this.f.size() + a();
        if (this.d != null && !TextUtils.isEmpty(this.d)) {
            i = 1;
        }
        return size + i;
    }

    public final Object getItem(int i) {
        if (i == 0 || this.f == null || i >= this.f.size()) {
            return null;
        }
        return this.f.get(i - a());
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = i;
        getItem(i);
        Map map = this.f.get(i2 - a());
        List list = (List) map.get(QuickTimeAtomTypes.h);
        List list2 = (List) map.get(MibiConstants.gf);
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout = new RelativeLayout(this.b);
        int i3 = b.g;
        relativeLayout.setPadding(i3, i3, i3, i3);
        linearLayout.addView(relativeLayout);
        LinearLayout linearLayout2 = new LinearLayout(this.b);
        linearLayout2.setBackgroundColor(-3419943);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
        ImageView imageView = new ImageView(this.b);
        imageView.setVisibility(4);
        imageView.setId(imageView.hashCode());
        TextView textView = new TextView(this.b);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText((String) map.get("style"));
        textView.setTextSize(b.i);
        textView.setTextColor(-10066330);
        int a2 = g.a(this.b, 20.0f);
        int i4 = this.e == i2 ? 1008 : 1007;
        int a3 = g.a(this.b, 20.0f);
        Drawable a4 = c.a(this.b).a(i4, a2, a2);
        if (b(i)) {
            imageView.setVisibility(0);
        }
        imageView.setBackgroundDrawable(a4);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a3, a3);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(9, -1);
        relativeLayout.addView(imageView, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(15, -1);
        int hashCode = imageView.hashCode();
        boolean z = true;
        layoutParams3.addRule(1, hashCode);
        layoutParams3.leftMargin = b.g;
        relativeLayout.addView(textView, layoutParams3);
        LinearLayout linearLayout3 = new LinearLayout(this.b);
        linearLayout3.setOrientation(1);
        int i5 = 5;
        linearLayout3.setGravity(5);
        linearLayout3.setId(linearLayout3.hashCode());
        LinearLayout linearLayout4 = new LinearLayout(this.b);
        linearLayout4.setOrientation(1);
        linearLayout4.setGravity(5);
        linearLayout4.setId(linearLayout4.hashCode());
        int i6 = 0;
        while (i6 < list.size()) {
            TextView textView2 = new TextView(this.b);
            textView2.setSingleLine(z);
            textView2.setEllipsize(TextUtils.TruncateAt.END);
            textView2.setText((CharSequence) list.get(i6));
            textView2.setTextSize(b.k);
            textView2.setTextColor(-6710887);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams4.gravity = i5;
            linearLayout3.addView(textView2, layoutParams4);
            TextView textView3 = new TextView(this.b);
            textView3.setSingleLine(true);
            textView3.setEllipsize(TextUtils.TruncateAt.END);
            textView3.setText((CharSequence) list2.get(i6));
            textView3.setTextSize(b.k);
            textView3.setTextColor(-6710887);
            linearLayout4.addView(textView3, layoutParams4);
            i6++;
            i5 = 5;
            z = true;
        }
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(g.a(this.b, 120.0f), -2);
        layoutParams5.addRule(11, -1);
        relativeLayout.addView(linearLayout4, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(g.a(this.b, 100.0f), -2);
        layoutParams6.addRule(0, linearLayout4.getId());
        relativeLayout.addView(linearLayout3, layoutParams6);
        layoutParams.rightMargin = i3;
        layoutParams.leftMargin = i3;
        linearLayout.addView(linearLayout2, layoutParams);
        return linearLayout;
    }

    public final boolean isEnabled(int i) {
        if ((!b() || i != 0) && b(i)) {
            return super.isEnabled(i);
        }
        return false;
    }
}
