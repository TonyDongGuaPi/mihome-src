package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.widget.AdapterView;
import com.unionpay.mobile.android.nocard.views.o;

final class ad implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o.b f9589a;

    ad(o.b bVar) {
        this.f9589a = bVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f9589a.b.dismiss();
        if (this.f9589a.j != null && i - this.f9589a.c.c() < this.f9589a.j.size()) {
            int unused = this.f9589a.g = i;
            this.f9589a.c.a(this.f9589a.g);
        }
        if (this.f9589a.k != null) {
            this.f9589a.k.a(i - this.f9589a.c.c());
        }
    }
}
