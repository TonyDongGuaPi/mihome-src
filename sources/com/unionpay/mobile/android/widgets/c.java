package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;

final class c implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9790a;

    c(a aVar) {
        this.f9790a = aVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9790a.s());
        sb.append("_select_areacode");
        a.a(this.f9790a, i);
        if (this.f9790a.r != null) {
            this.f9790a.r.dismiss();
        }
    }
}
