package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import com.unionpay.mobile.android.utils.o;

final class r implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ p f9802a;

    r(p pVar) {
        this.f9802a = pVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9802a.s());
        sb.append("_select_installment");
        String[] strArr = o.f;
        new Object[1][0] = Integer.valueOf(i);
        this.f9802a.a(i);
        if (this.f9802a.q != null) {
            this.f9802a.q.dismiss();
        }
    }
}
