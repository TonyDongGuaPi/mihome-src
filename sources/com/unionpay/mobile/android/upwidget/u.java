package com.unionpay.mobile.android.upwidget;

import android.view.ViewTreeObserver;

final class u implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPScrollView f9735a;

    u(UPScrollView uPScrollView) {
        this.f9735a = uPScrollView;
    }

    public final void onGlobalLayout() {
        this.f9735a.d.sendMessageDelayed(this.f9735a.d.obtainMessage(), 5);
    }
}
