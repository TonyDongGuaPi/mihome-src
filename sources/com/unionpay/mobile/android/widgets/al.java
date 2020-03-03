package com.unionpay.mobile.android.widgets;

import android.view.View;
import com.unionpay.mobile.android.utils.o;

final class al implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aj f9773a;

    al(aj ajVar) {
        this.f9773a = ajVar;
    }

    public final void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9773a.s());
        sb.append("_select_reduce_activity");
        String[] strArr = o.h;
        Object[] objArr = {aj.a(this.f9773a, intValue, "type"), 0, this.f9773a.a(intValue, 0, "label")};
        this.f9773a.a(intValue, 0);
        if (this.f9773a.p != null) {
            this.f9773a.p.dismiss();
        }
    }
}
