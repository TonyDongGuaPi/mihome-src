package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;

final class t implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ p f9804a;

    t(p pVar) {
        this.f9804a = pVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f9804a.a(i);
        this.f9804a.p.dismiss();
    }
}
