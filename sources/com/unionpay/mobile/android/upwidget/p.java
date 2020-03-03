package com.unionpay.mobile.android.upwidget;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Iterator;
import org.json.JSONObject;

final class p implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9730a;

    p(j jVar) {
        this.f9730a = jVar;
    }

    public final void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != this.f9730a.o) {
            this.f9730a.a(intValue);
            if (this.f9730a.e && !TextUtils.isEmpty(this.f9730a.f[intValue].d)) {
                view.setTag(this.f9730a.f[intValue].d);
                Iterator it = this.f9730a.w.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                this.f9730a.a((LinearLayout) this.f9730a.f[intValue].c, true, "正在查询。。。", (JSONObject) null, (c) null);
                boolean unused = this.f9730a.e = false;
            }
            view.setTag(Integer.valueOf(intValue));
        }
    }
}
