package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class f implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9719a;

    f(e eVar) {
        this.f9719a = eVar;
    }

    public final void onClick(View view) {
        Iterator it = this.f9719a.g.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
