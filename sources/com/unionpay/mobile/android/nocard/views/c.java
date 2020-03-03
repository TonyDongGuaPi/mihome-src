package com.unionpay.mobile.android.nocard.views;

import android.view.ViewTreeObserver;

final class c implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9619a;

    c(b bVar) {
        this.f9619a = bVar;
    }

    public final boolean onPreDraw() {
        this.f9619a.r.getViewTreeObserver().removeOnPreDrawListener(this);
        int unused = this.f9619a.u = this.f9619a.r.getMeasuredHeight();
        int unused2 = this.f9619a.v = this.f9619a.r.getTop();
        return true;
    }
}
