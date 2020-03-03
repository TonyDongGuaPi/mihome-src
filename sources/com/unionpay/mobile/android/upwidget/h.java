package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import java.util.Iterator;

final class h implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9721a;

    h(g gVar) {
        this.f9721a = gVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Iterator it = this.f9721a.c.iterator();
        while (it.hasNext()) {
            ((AdapterView.OnItemClickListener) it.next()).onItemClick(adapterView, view, i, j);
        }
    }
}
