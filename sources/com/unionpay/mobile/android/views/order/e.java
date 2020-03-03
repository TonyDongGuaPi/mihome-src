package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.widget.AdapterView;

final class e implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9753a;

    e(b bVar) {
        this.f9753a = bVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f9753a.c(i);
    }
}
