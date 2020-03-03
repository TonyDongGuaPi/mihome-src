package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;

final class i implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9794a;

    i(g gVar) {
        this.f9794a = gVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9794a.s());
        sb.append("_select_certtype");
        this.f9794a.a(i);
        if (this.f9794a.r != null) {
            this.f9794a.r.dismiss();
        }
    }
}
