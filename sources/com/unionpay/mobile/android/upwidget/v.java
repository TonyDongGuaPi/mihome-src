package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.upwidget.UPScrollView;

final class v extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPScrollView f9736a;

    v(UPScrollView uPScrollView) {
        this.f9736a = uPScrollView;
    }

    public final void handleMessage(Message message) {
        int scrollY = this.f9736a.getScrollY();
        if (this.f9736a.b != scrollY) {
            int unused = this.f9736a.b = scrollY;
            this.f9736a.d.sendMessageDelayed(this.f9736a.d.obtainMessage(), 5);
        }
        if (this.f9736a.f9713a != null && this.f9736a.f9713a.get() != null) {
            ((UPScrollView.a) this.f9736a.f9713a.get()).e(scrollY);
        }
    }
}
