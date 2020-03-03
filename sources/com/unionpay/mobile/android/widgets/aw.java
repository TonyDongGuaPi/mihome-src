package com.unionpay.mobile.android.widgets;

import android.view.ViewTreeObserver;
import com.unionpay.mobile.android.utils.k;

final class aw implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPWidget f9779a;

    aw(UPWidget uPWidget) {
        this.f9779a = uPWidget;
    }

    public final void onGlobalLayout() {
        k.a("uppay", "onGlobalLayout() +++");
        int height = this.f9779a.x().getRootView().getHeight() - this.f9779a.x().getHeight();
        if (height <= UPWidget.o && height <= UPWidget.o) {
            this.f9779a.l();
        }
        k.a("uppay", "onGlobalLayout() ---");
    }
}
