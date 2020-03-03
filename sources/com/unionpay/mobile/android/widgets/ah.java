package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.libra.Color;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class ah extends aa {
    /* access modifiers changed from: private */
    public a c;
    private String o;
    /* access modifiers changed from: private */
    public String p;

    public interface a {
        void e(String str);
    }

    public ah(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ah(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, i, jSONObject, str);
        this.c = null;
        this.o = null;
        this.p = null;
        this.o = j.a(jSONObject, "button_label");
        this.p = j.a(jSONObject, "button_action");
        this.b.a((InputFilter) new InputFilter.LengthFilter(11));
        this.b.a(2);
        if (this.o != null && this.o.length() > 0) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b.n);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(15, -1);
            this.b.setLayoutParams(layoutParams);
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setText(this.o);
            textView.setTextColor(Color.c);
            textView.setTextSize(b.k);
            textView.setOnClickListener(new ai(this));
            this.b.a(textView, new LinearLayout.LayoutParams(-2, -1));
        }
    }

    public final String a() {
        return this.b.b();
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    public final boolean b() {
        if (this.i) {
            return true;
        }
        return (this.j == null || TextUtils.isEmpty(this.j)) ? 11 == a().length() && a().startsWith("1") : a().matches(this.j);
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_input_phoneNO";
    }
}
