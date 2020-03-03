package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import com.unionpay.mobile.android.utils.o;

final class am implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aj f9774a;

    am(aj ajVar) {
        this.f9774a = ajVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int intValue = ((Integer) view.getTag()).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9774a.s());
        sb.append("_select_reduce_activity");
        String[] strArr = o.h;
        Object[] objArr = {aj.a(this.f9774a, intValue, "type"), Integer.valueOf(i), this.f9774a.a(intValue, i, "label")};
        this.f9774a.a(intValue, i);
        if (this.f9774a.p != null) {
            this.f9774a.p.dismiss();
        }
    }
}
