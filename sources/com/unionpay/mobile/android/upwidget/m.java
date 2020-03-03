package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class m implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9727a;

    m(j jVar) {
        this.f9727a = jVar;
    }

    public final void onClick(View view) {
        Iterator it = this.f9727a.v.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
