package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import java.util.Iterator;

final class k implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9725a;

    k(j jVar) {
        this.f9725a = jVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        view.setTag(Integer.valueOf(this.f9725a.o));
        if (this.f9725a.n != this.f9725a.d) {
            Object obj = this.f9725a.g.get(this.f9725a.n);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        Object obj2 = this.f9725a.g.get(this.f9725a.o);
        if (obj2 instanceof c) {
            ((c) obj2).a(i);
        }
        int unused = this.f9725a.n = this.f9725a.o;
        int unused2 = this.f9725a.p = i;
        Iterator it = this.f9725a.t.iterator();
        while (it.hasNext()) {
            ((AdapterView.OnItemClickListener) it.next()).onItemClick(adapterView, view, i, j);
        }
    }
}
