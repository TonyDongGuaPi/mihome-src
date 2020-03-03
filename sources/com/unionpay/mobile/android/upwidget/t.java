package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;

final class t extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPRadiationView f9734a;

    t(UPRadiationView uPRadiationView) {
        this.f9734a = uPRadiationView;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            UPRadiationView.a(this.f9734a);
            this.f9734a.invalidate();
            if (this.f9734a.f9711a != null && this.f9734a.f9711a.size() > 0) {
                this.f9734a.e.sendEmptyMessageDelayed(0, 50);
            }
        }
    }
}
